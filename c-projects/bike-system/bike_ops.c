#include <stdio.h>
#include <time.h>
#include "bike_ops.h"
#include "common.h"

/* 外部引用已在 common.h 里声明 */
extern Zone** g_zones;
extern int    g_zone_cnt;
extern Bike** g_bikes;
extern int    g_bike_cnt;

//借车：成功返回 0，失败返回 -1 
int borrow_bike(int zone_id)
{
    Zone* z = NULL;
    for (int i = 0; i < g_zone_cnt; i++) {
        if (g_zones[i]->id == zone_id) {
            z = g_zones[i];
            break;
        }
    }
    if (!z) { puts("区域不存在"); return -1; }

    for (int i = 0; i < z->bike_top; i++) {
        Bike* b = z->bikes[i];
        if (b && b->status == 'A') {
            b->status = 'U';                // 标记为使用中   
            b->last_used = time(NULL);      // 更新时间
            b->loc->cnt--;                  // 区域计数减 

            // 把该车从区域数组里“抽”出来，保持紧凑 
            z->bikes[i] = z->bikes[z->bike_top - 1];
            z->bike_top--;
            z->bikes = realloc(z->bikes, z->bike_top * sizeof(Bike*));
            printf("借车成功：单车 %d 已从区域 %d 借出\n", b->id, zone_id);
            return 0;
        }
    }
    puts("该区域暂无可借单车");
    return -1;
}

//还车：成功返回 0，失败返回 -1
int return_bike(int bike_id, int zone_id)
{
    Zone* z = NULL;
    for (int i = 0; i < g_zone_cnt; i++) {
        if (g_zones[i]->id == zone_id) {
            z = g_zones[i];
            break;
        }
    }
    if (!z) { puts("区域不存在"); return -1; }

    Bike* b = NULL;
    for (int i = 0; i < g_bike_cnt; i++) {
        if (g_bikes[i]->id == bike_id) {
            b = g_bikes[i];
            break;
        }
    }
    if (!b) { puts("单车编号不存在"); return -1; }
    if (b->status != 'U') { puts("该单车未被借出"); return -1; }

    // 更新状态 
    b->status = 'A';
    b->loc = z;
    z->cnt++;

    // 把单车指针塞进区域的 bikes[] 
    z->bike_top++;
    z->bikes = realloc(z->bikes, z->bike_top * sizeof(Bike*));
    z->bikes[z->bike_top - 1] = b;

    printf("还车成功：单车 %d 已归还至区域 %d\n", bike_id, zone_id);
    return 0;
}
// 查看单车状态
void view_bike_status() {
    printf("\n=== 当前单车状态 ===\n");
    for (int i = 0; i < g_bike_cnt; i++) {
        printf("单车 %d, 状态: %s, 所在区域: %d\n",
            g_bikes[i]->id,
            g_bikes[i]->status == AVAIL ? "可用" :
            (g_bikes[i]->status == USING ? "使用中" : "损坏"),
            g_bikes[i]->loc ? g_bikes[i]->loc->id : -1);
    }
}