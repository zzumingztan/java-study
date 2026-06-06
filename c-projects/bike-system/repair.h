#ifndef REPAIR_H
#define REPAIR_H
#include "common.h"

// 用户报修：把某辆车标记为 DEFECT 并从区域里移走 
int report_defect(int bike_id);

//维修完成：把车修好并放回指定区域 
int finish_repair(int bike_id, int zone_id);

#endif
