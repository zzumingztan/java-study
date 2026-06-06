#include <stdio.h>
#include <time.h>
#include "repair.h"
#include "common.h"

// 把单车从区域里移除
static void remove_from_zone(Bike* b)
{
    if (!b || !b->loc) return;
    Zone* z = b->loc;
    for (int i = 0; i < z->bike_top; ++i) 
    {
        if (z->bikes[i] == b) 
        {
            z->bikes[i] = z->bikes[z->bike_top - 1];
            z->bike_top--;
            z->bikes = realloc(z->bikes, z->bike_top * sizeof(Bike*));
            z->cnt--;
            b->loc = NULL;
            break;
        }
    }
}

// 把单车放回区域
static void add_to_zone(Bike* b, Zone* z)
{
    z->bike_top++;
    z->bikes = realloc(z->bikes, z->bike_top * sizeof(Bike*));
    z->bikes[z->bike_top - 1] = b;
    z->cnt++;
    b->loc = z;
}

// 1. 报修：状态→DEFECT 并移出区域 
int report_defect(int bike_id)
{
    Bike* b = NULL;
    for (int i = 0; i < g_bike_cnt; ++i)
        if (g_bikes[i]->id == bike_id) 
        { b = g_bikes[i]; break; }

    if (!b) { puts("单车编号不存在"); return -1; }
    if (b->status == DEFECT) { puts("已是坏车，无需重复报修"); return -1; }

    remove_from_zone(b);
    b->status = DEFECT;
    b->loc = NULL;              /* 暂不放任何区域 */
    printf("报修成功：单车 %d 已标记为坏车并移出停放区\n", bike_id);
    return 0;
}

// 2. 维修完成：状态→AVAIL 并放回指定区域 
int finish_repair(int bike_id, int zone_id)
{
    Bike* b = NULL;
    for (int i = 0; i < g_bike_cnt; ++i)
        if (g_bikes[i]->id == bike_id) { b = g_bikes[i]; break; }

    if (!b) { puts("单车编号不存在"); return -1; }
    if (b->status != DEFECT) { puts("该车并非坏车"); return -1; }

    Zone* z = NULL;
    for (int i = 0; i < g_zone_cnt; ++i)
        if (g_zones[i]->id == zone_id) { z = g_zones[i]; break; }
    if (!z) { puts("目标区域不存在"); return -1; }

    b->status = AVAIL;
    b->last_used = time(NULL);
    add_to_zone(b, z);

    printf("维修入库：单车 %d 已修好并放回区域 %d\n", bike_id, zone_id);
    return 0;
}