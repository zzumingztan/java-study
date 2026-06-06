#include "tsp.h"
#include <math.h>

/* 两点间的欧氏距离 */
static double dist(Zone* a, Zone* b) {
    return hypot(a->x - b->x, a->y - b->y);
}

/* 计算整条路径长度 */
static double route_len(Order** route, int n) {
    double len = 0;
    for (int i = 0; i < n - 1; ++i)
        len += dist(route[i]->from, route[i + 1]->from);
    return len;
}

/* 交换 i..j 段并返回新长度 */
static double swap_len(Order** r, int n, int i, int j) {
    double delta = 0;
    /* 断开的四段 */
    Zone* pi1 = (i == 0) ? NULL : r[i - 1]->from;
    Zone* pi = r[i]->from;
    Zone* pj = r[j]->from;
    Zone* pj1 = (j == n - 1) ? NULL : r[j + 1]->from;

    if (pi1) delta -= dist(pi1, pi) - dist(pi1, pj);
    if (pj1) delta -= dist(pj, pj1) - dist(pi, pj1);
    return delta;
}

void tsp_2opt(void) {
    if (g_order_cnt < 2) return;

    Order** route = g_orders;   // 就地重排
    int n = g_order_cnt;
    int improved = 1;

    while (improved) {
        improved = 0;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double delta = swap_len(route, n, i, j);
                if (delta < -1e-6) {          // 有改进
                    // 反转 i..j 段
                    for (int k = 0; k < (j - i + 1) / 2; ++k) {
                        Order* tmp = route[i + k];
                        route[i + k] = route[j - k];
                        route[j - k] = tmp;
                    }
                    improved = 1;
                }
            }
        }
    }
}
void tsp_2opt_with_report(void) {
    for (int i = 0; i < g_order_cnt; i++) {
        Order* o = g_orders[i];
        printf("订单 %d: 单车 %d 区域 %d -> 区域 %d 距离 %.1f\n", o->id, o->bp->id, o->from->id, o->to->id, o->cost);
    }

    int valid = 0;
    for (int i = 0; i < g_order_cnt; i++) {
        Order* o = g_orders[i];
        if (!o || !o->bp || !o->from || !o->to) {
            free(o);
            continue;
        }
        g_orders[valid++] = o;
    }
    g_order_cnt = valid;

    if (g_order_cnt < 2) {
        puts(">>> 订单不足 2 条，跳过优化 <<<");
        return;
    }

    double before = route_len(g_orders, g_order_cnt);
    if (g_order_cnt < 2) {
        puts(">>> 订单不足 2 条，无需路径优化 <<<");
        return;
    }
    if (before < 1e-6) {          // 基数为 0
        puts(">>> 订单起点相同，路径已最短 <<<");
        return;
    }
    printf("优化前路径长度：%.1f m\n", before);
    tsp_2opt();
    double after = route_len(g_orders, g_order_cnt);
    printf("优化后路径长度：%.1f m\n", after);
    printf("节省 %.1f%%\n", (before - after) / before * 100);
}