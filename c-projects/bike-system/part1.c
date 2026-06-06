/*
 * 共享单车系统 - 完整修复版本
 * 修复了区域信息显示-1的问题，包含所有必要的函数定义
 */

#include "common.h"
#include "repair.h"
#include "tsp.h"
#define MAP_W 80
#define MAP_H 25

 // 全局变量
Zone** g_zones = NULL;
int    g_zone_cnt = 0;
Bike** g_bikes = NULL;
int    g_bike_cnt = 0;
Order** g_orders = NULL;
int    g_order_cnt = 0;

/* 保存所有 Zone 到二进制文件 */
void save_zones(void)
{
    FILE* fp = fopen(ZONE_FILE, "wb");
    if (!fp)
    {
        perror(ZONE_FILE);
        return;
    }

    fwrite(&g_zone_cnt, sizeof(int), 1, fp);

    for (int i = 0; i < g_zone_cnt; i++)
    {
        Zone* z = g_zones[i];
        fwrite(z, sizeof(Zone), 1, fp);
    }
    fclose(fp);
}

/* 加载单车 */
void load_bikes(void) {
    FILE* fp = fopen(BIKE_FILE, "rb");
    if (!fp) return;

    fread(&g_bike_cnt, sizeof(int), 1, fp);
    g_bikes = realloc(g_bikes, g_bike_cnt * sizeof(Bike*));

    for (int i = 0; i < g_bike_cnt; i++) {
        g_bikes[i] = malloc(sizeof(Bike));

        int zone_id;
        fread(&zone_id, sizeof(int), 1, fp);
        fread(g_bikes[i], sizeof(Bike), 1, fp);

        g_bikes[i]->loc = NULL;
        if (zone_id != -1) {                 // -1 表示损坏车
            for (int z = 0; z < g_zone_cnt; z++) {
                if (g_zones[z]->id == zone_id) {
                    g_bikes[i]->loc = g_zones[z];
                    /* 只把可用车放回区域数组 */
                    Zone* zt = g_zones[z];
                    zt->bike_top++;
                    zt->bikes = realloc(zt->bikes, zt->bike_top * sizeof(Bike*));
                    zt->bikes[zt->bike_top - 1] = g_bikes[i];
                    zt->cnt++;
                    break;
                }
            }
        }
    }
    fclose(fp);
}

/* 保存单车 */
void save_bikes(void) {
    FILE* fp = fopen(BIKE_FILE, "wb");
    if (!fp) { perror(BIKE_FILE); return; }

    fwrite(&g_bike_cnt, sizeof(int), 1, fp);
    for (int i = 0; i < g_bike_cnt; i++) {
        Bike* b = g_bikes[i];

        /* 损坏车写 -1，可用车写真实区域 id */
        int zone_id = (b->status == DEFECT || !b->loc) ? -1 : b->loc->id;
        fwrite(&zone_id, sizeof(int), 1, fp);

        Bike temp = *b;
        temp.loc = NULL;          // 指针不存盘
        fwrite(&temp, sizeof(Bike), 1, fp);
    }
    fclose(fp);
}


/* 保存所有 Order 到二进制文件 */
void save_orders(void)
{
    FILE* fp = fopen(ORDER_FILE, "wb");
    if (!fp)
    {
        perror(ORDER_FILE);
        return;
    }

    // 写数量
    fwrite(&g_order_cnt, sizeof(int), 1, fp);

    // 逐个写，存id
    for (int i = 0; i < g_order_cnt; i++) {
        Order o = *g_orders[i];              // 结构体副本
        o.bp = (Bike*)(uintptr_t)(o.bp->id); // 把指针换成 id
        o.from = (Zone*)(uintptr_t)(o.from->id);
        o.to = (Zone*)(uintptr_t)(o.to->id);
        fwrite(&o, sizeof(Order), 1, fp);
    }
    fclose(fp);
    /*// 验证区域分配结果
    int null_zones = 0;
    for (int i = 0; i < g_bike_cnt; i++) {
        if (g_bikes[i]->loc == NULL) {
            null_zones++;
            printf("警告：单车 %d 未能分配到有效区域\n", g_bikes[i]->id);
        }
    }

    if (null_zones > 0) {
        printf("警告：共有 %d 辆单车区域信息丢失\n", null_zones);
    }*/
    /* ====== 仅调试：损坏车统计 ====== */
#if 0          /* 改成 1 就重新开启调试信息 */
    int null_zones = 0;
    for (int i = 0; i < g_bike_cnt; i++) {
        if (g_bikes[i]->loc == NULL) {
            null_zones++;
            printf("调试：损坏车 %d 未关联区域（正常现象）\n", g_bikes[i]->id);
        }
    }
    if (null_zones > 0)
        printf("调试：共有 %d 辆损坏车（区域=-1）\n", null_zones);
#endif
    /* ================================= */
}

/* 从二进制文件加载所有 Order，并重建指针 */
void load_orders(void)
{
    FILE* fp = fopen(ORDER_FILE, "rb");
    if (!fp) return;

    fread(&g_order_cnt, sizeof(int), 1, fp);
    g_orders = realloc(g_orders, g_order_cnt * sizeof(Order*));
    if (!g_orders) { perror("realloc orders"); exit(EXIT_FAILURE); }

    for (int i = 0; i < g_order_cnt; i++)
    {
        Order tmp, * o = malloc(sizeof(Order));
        fread(&tmp, sizeof(Order), 1, fp);

        *o = tmp;
        // 指针重建
        int bike_id = (int)(uintptr_t)(o->bp);
        int from_id = (int)(uintptr_t)(o->from);
        int to_id = (int)(uintptr_t)(o->to);

        // 查找单车
        for (int j = 0; j < g_bike_cnt; j++)
            if (g_bikes[j]->id == bike_id) { o->bp = g_bikes[j]; break; }

        // 查找区域
        for (int j = 0; j < g_zone_cnt; j++) {
            if (g_zones[j]->id == from_id) o->from = g_zones[j];
            if (g_zones[j]->id == to_id) o->to = g_zones[j];
        }
        g_orders[i] = o;
    }
    fclose(fp);
}

/* 创建示例数据 */
void create_sample_data(void) {
    // 区域初始化
    g_zone_cnt = 10;
    g_zones = malloc(g_zone_cnt * sizeof(Zone*));
    if (g_zones == NULL) {
        perror("malloc zones failed");
        exit(EXIT_FAILURE);
    }

    // 初始化每个区域
    for (int i = 0; i < g_zone_cnt; i++) {
        Zone* z = malloc(sizeof(Zone));
        if (z == NULL) {
            perror("malloc zone failed");
            exit(EXIT_FAILURE);
        }

        z->id = i + 1;
        z->x = rand() % 1000;
        z->y = rand() % 1000;
        z->cap = 20 + rand() % 21;  // 每个区域容量为20-40
        z->cnt = 0;
        z->bike_top = 0;
        z->bikes = NULL;
        g_zones[i] = z;

        printf("区域 %d 初始化成功，位置: (%.1f, %.1f), 容量: %d\n",
            z->id, z->x, z->y, z->cap);
    }

    // 单车初始化
    g_bike_cnt = 200;
    g_bikes = malloc(g_bike_cnt * sizeof(Bike*));
    if (g_bikes == NULL) {
        perror("malloc bikes failed");
        exit(EXIT_FAILURE);
    }
    /* 初始化每辆单车 */
    for (int i = 0; i < g_bike_cnt; i++) {
        Bike* b = malloc(sizeof(Bike));
        b->id = 1001 + i;

        if (rand() % 10 == 0) {          // 10% 损坏
            b->status = DEFECT;
            b->loc = NULL;            // 无区域
        }
        else {                         // 90% 可用
            b->status = AVAIL;
            int zid = rand() % g_zone_cnt;
            b->loc = g_zones[zid];

            /* 只把可用车放进区域数组 */
            Zone* z = g_zones[zid];
            z->bike_top++;
            z->bikes = realloc(z->bikes, z->bike_top * sizeof(Bike*));
            z->bikes[z->bike_top - 1] = b;
            z->cnt++;
        }
        b->last_used = time(NULL) - rand() % 3600;
        g_bikes[i] = b;
    }
        
    

    printf("\n初始化完成：%d 辆单车已分配到 %d 个区域\n", g_bike_cnt, g_zone_cnt);
}

/* ASCII 地图可视化 */
void draw_ascii_map(void)
{
    /* 顶刻度：十位数 + 个位数，每 10 列空一格，方便读 */
    printf("   ");
    for (int x = 0; x < MAP_W; x++) {
        if (x % 10 == 0) printf("%d", (x / 10) % 10); // 十位数
        else printf(" ");
    }
    printf("\n   ");
    for (int x = 0; x < MAP_W; x++) {
        if (x % 10 == 0) printf("0");   // 个位补 0
        else printf("%d", x % 10);
    }
    printf("\n");
    // 1. 清空白画布
    char canvas[MAP_H][MAP_W + 1];
    for (int y = 0; y < MAP_H; ++y)
        for (int x = 0; x < MAP_W; ++x)
            canvas[y][x] = '.';
    for (int y = 0; y < MAP_H; ++y)
        canvas[y][MAP_W] = '\0';

    // 2. 把每个 Zone 映射到画布坐标
    for (int i = 0; i < g_zone_cnt; ++i)
    {
        Zone* z = g_zones[i];
        int cx = (int)(z->x / 1000.0 * (MAP_W - 1));
        int cy = (int)(z->y / 1000.0 * (MAP_H - 1));
        if (cx >= 0 && cx < MAP_W && cy >= 0 && cy < MAP_H)
        {
            char ch = '0' + z->cnt / 5;
            if (ch > '9') ch = '9';
            canvas[cy][cx] = ch;
        }
    }

    /* 3. 打印画布 + 行号 */
    for (int y = 0; y < MAP_H; ++y) {
        printf("%2d ", y);          // 行号 0-24
        printf("%s\n", canvas[y]);  
    }
    /* 4. 文字摘要 */
    puts("\n=== 区域快照 ===");
    for (int i = 0; i < g_zone_cnt; ++i) {
        Zone* z = g_zones[i];
        int bar = z->cnt * 10 / (z->cap ? z->cap : 1); // 0~10
        printf("%2d区 [%c] 车:%2d 容:%2d  ", z->id, " .oO#"[bar > 9 ? 4 : bar / 3], z->cnt, z->cap);
        if (z->cnt < z->cap * 0.5) printf("→缺车");
        else if (z->cnt > z->cap * 0.9) printf("→爆满");
        else printf("→正常");
        puts("");
    }
    puts("图中数字=车辆数/5（最大9），符号见上表。");
}
void execute_orders(void)
{
    if (g_order_cnt == 0) {
        puts("当前无待执行订单。");
        return;
    }

    for (int i = 0; i < g_order_cnt; ++i) {
        Order* o = g_orders[i];
        Bike* b = o->bp;
        Zone* src = o->from;
        Zone* dst = o->to;

        /* ===== 1. 从源区域移除：新建数组 ===== */
        Bike** new_src = malloc((src->bike_top - 1) * sizeof(Bike*));
        int src_idx = 0;
        for (int k = 0; k < src->bike_top; k++)
            if (src->bikes[k] != b) new_src[src_idx++] = src->bikes[k];
        free(src->bikes);
        src->bikes = new_src;
        src->bike_top = src_idx;
        src->cnt = src_idx;

        /* ===== 2. 加入目标区域：新建数组 ===== */
        Bike** new_dst = malloc((dst->bike_top + 1) * sizeof(Bike*));
        memcpy(new_dst, dst->bikes, dst->bike_top * sizeof(Bike*));
        new_dst[dst->bike_top] = b;
        free(dst->bikes);
        dst->bikes = new_dst;
        dst->bike_top++;
        dst->cnt++;

        /* 3. 更新单车位置 */
        b->loc = dst;
        printf("单车 %d 已从区域 %d 搬运到区域 %d\n", b->id, src->id, dst->id);
    }

    /* 4. 清空订单 */
    for (int i = 0; i < g_order_cnt; i++) free(g_orders[i]);
    free(g_orders);
    g_orders = NULL;
    g_order_cnt = 0;
    puts("所有订单已执行完毕。");
}
/* 菜单显示 */
void menu(void) {
    puts("\n=== 共享单车管理系统 ===");
    puts("1) 显示所有区域信息");
    puts("2) 生成运输计划");
    puts("3) 查看单车状态");
    puts("4) 绘制地图");
    puts("5) 报修坏车");
    puts("6) 修好入区");
    puts("7) 生成并优化调度计划");
    puts("8) 执行当前运输订单");
    puts("0) 退出系统");
}

/* 显示区域信息 */
void print_zones(void) {
    printf("\n=== 区域信息 ===\n");
    for (int i = 0; i < g_zone_cnt; i++) {
        Zone* z = g_zones[i];
        printf("区域 %d: 位置(%.1f, %.1f), 容量 %d, 当前 %d 辆车\n",
            z->id, z->x, z->y, z->cap, z->cnt);
    }
}

/* 显示运输订单 */
void print_orders(void)
{
    puts("\n=== 运输订单 ===");
    for (int i = 0; i < g_order_cnt; i++) {
        Order* o = g_orders[i];
        printf("订单 %-3d: 单车 %d  区域%d -> 区域%d  距离 %.1f\n",
            o->id, o->bp->id, o->from->id, o->to->id, o->cost);
    }
}
/* 距离计算函数 */
double distance(Zone* a, Zone* b) {
    return hypot(a->x - b->x, a->y - b->y);
}
/* 生成运输计划 */
static int* used;   // 标记已派车辆

void build_transport_plan(void)
{
    /* 0. 清空旧订单 */
    if (g_orders) {
        for (int i = 0; i < g_order_cnt; i++) free(g_orders[i]);
        free(g_orders); g_orders = NULL;
    }
    g_order_cnt = 0;

    /* 1. 分类：爆满区 / 缺车区 */
    Zone* overflow[50]; int ocnt = 0;
    Zone* shortage[50]; int scnt = 0;

    for (int i = 0; i < g_zone_cnt; i++) {
        Zone* z = g_zones[i];
        /* 搬出阈值：利用率 > 90 % */
        if (z->cnt > z->cap * OVER_THRESHOLD)                       /* 爆满 */
            overflow[ocnt++] = z;
        else if (z->cnt < z->cap * UNDER_THRESHOLD)            /* 缺车阈值 70 % */
            shortage[scnt++] = z;
    }

    if (!ocnt || !scnt) {
        puts(">>> 当前无需调度（无爆满或缺车区）<<<");
        return;
    }

    /* 2. 爆满区按“过剩量”降序 */
    for (int i = 0; i < ocnt - 1; i++) {
        for (int j = i + 1; j < ocnt; j++) {
            int over_i = overflow[i]->cnt - overflow[i]->cap;
            int over_j = overflow[j]->cnt - overflow[j]->cap;
            if (over_j > over_i) { Zone* t = overflow[i]; overflow[i] = overflow[j]; overflow[j] = t; }
        }
    }

    /* 3. 依次处理每个爆满区 */
    for (int i = 0; i < ocnt; i++) {
        Zone* src = overflow[i];

        /* 计算可搬车辆数（用副本，不动真实 cnt） */
        int canMove = src->cnt - (int)(src->cap * OVER_THRESHOLD); // 想留 90 %
        if (canMove < 1) canMove = 1;
        if (canMove > 2) canMove = 2;                      // 上限 2 辆（可调）

        int moved = 0;
        while (moved < canMove && src->cnt > src->cap * OVER_THRESHOLD) {
            /* 找一辆可用好车 */
            Bike* pick = NULL;
            for (int k = 0; k < src->bike_top; k++) {
                Bike* b = src->bikes[k];
                if (b && b->status == AVAIL) { pick = b; break; }
            }
            if (!pick) break;

            /* 找“最缺车”的缺车区 */
            Zone* best = NULL;
            int bestGap = -1;
            for (int j = 0; j < scnt; j++) {
                int gap = shortage[j]->cap - shortage[j]->cnt;
                if (gap > bestGap) { bestGap = gap; best = shortage[j]; }
            }
            if (!best) break;

            /* 生成订单 */
            Order* o = malloc(sizeof(Order));
            o->id = ++g_order_cnt;
            o->bp = pick;
            o->from = src;
            o->to = best;
            o->cost = distance(src, best);
            g_orders = realloc(g_orders, g_order_cnt * sizeof(Order*));
            g_orders[g_order_cnt - 1] = o;

            moved++;
        }
    }
        
   
}


void run_daily_scheduler(void)
{
    //build_transport_plan();          // 先生成订单

    /* ===== 体检：把脏订单直接踢掉 ===== */
    int valid = 0;
    for (int i = 0; i < g_order_cnt; i++) {
        Order* o = g_orders[i];
        if (!o || !o->bp || !o->from || !o->to)
        {
            if (o)
            {
                free(o);
            }                  // 释放脏订单
            continue;
        }
        g_orders[valid++] = o;        // 压缩到前面
    }
    g_order_cnt = valid;              // 新长度

    if (g_order_cnt == 0) {
        puts(">>> 无有效订单，跳过优化 <<<");
        return;
    }

    /* 现在才进入优化 */
    tsp_2opt_with_report();
    print_orders();
    save_orders();
}


void cleanup_corrupt_data(void) {
    FILE* fp = fopen("bikes.dat", "rb");
    if (!fp) return;
    Bike b;
    int zone_id;
    if (fread(&zone_id, sizeof(int), 1, fp) != 1 ||
        fread(&b, sizeof(Bike), 1, fp) != 1 ||
        b.id < 1000 || b.id > 10000) {
        fclose(fp);
        puts("检测到脏数据，正在清理并重新初始化...");
        remove("zones.dat");
        remove("bikes.dat");
        remove("orders.dat");
        return;
    }
    fclose(fp);
}
/* 主函数 */
int main() {
    srand(time(NULL));
    srand((unsigned)time(NULL));

    // 1. 清空所有全局指针
    g_zones = NULL;
    g_zone_cnt = 0;
    g_bikes = NULL;
    g_bike_cnt = 0;
    g_orders = NULL;
    g_order_cnt = 0;

    // 2. 无论如何都重新生成干净数据
    puts("=== 初始化中，生成全新示例数据 ===");
    create_sample_data();
    save_zones();
    save_bikes();
    save_orders();
    int cmd, bike_id, zone_id;
    while (1) {
        menu();
        printf("> ");
        fflush(stdout);

        if (scanf("%d", &cmd) != 1) break;

        switch (cmd) {
        case 1:
            print_zones();
            break;

        case 2:
            build_transport_plan();
            print_orders();
            save_orders();
            break;

        case 3:
            view_bike_status();
            break;

        case 4:
            draw_ascii_map();
            break;

        case 5:
            printf("输入要报修的车号: ");
            scanf("%d", &bike_id);
            report_defect(bike_id);
            save_bikes();
            break;

        case 6:
            printf("输入修好的车号和放回区域号: ");
            scanf("%d%d", &bike_id, &zone_id);
            finish_repair(bike_id, zone_id);
            save_bikes();
            break;

        case 7:
            run_daily_scheduler();
            save_bikes();
            save_orders();
            break;
        case 8:
            draw_ascii_map();          // ① 先给纯净图
            print_orders();            // ② 再给订单列表（原来9的后半段）
            execute_orders();          // ③ 真正搬车
            save_bikes();
            break;
        case 0:
            puts("感谢使用，再见！");
            // 保存数据
            save_zones();
            save_bikes();
            save_orders();
            goto cleanup;

        default:
            puts("无效的命令，请重新输入！");
        }
    }

cleanup:
    /* 释放全部堆内存 */
    for (int i = 0; i < g_zone_cnt; i++) {
        free(g_zones[i]->bikes);
        free(g_zones[i]);
    }
    for (int i = 0; i < g_bike_cnt; i++) free(g_bikes[i]);
    for (int i = 0; i < g_order_cnt; i++) free(g_orders[i]);
    free(g_zones);
    free(g_bikes);
    free(g_orders);

    return 0;
}