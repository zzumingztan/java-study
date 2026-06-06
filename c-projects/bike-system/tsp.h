#ifndef TSP_H
#define TSP_H
#include "common.h"

/* 输入：g_orders 数组，共 g_order_cnt 条待搬运任务
   输出：重排 g_orders 顺序，使总路程最短（近似）*/
void tsp_2opt(void);

#endif