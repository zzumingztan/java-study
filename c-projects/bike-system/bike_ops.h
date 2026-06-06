#ifndef BIKE_OPS_H
#define BIKE_OPS_H

#include "common.h"   // 把 Zone、Bike 等结构体声明放在这里

/* 借车：用户在某区域借一辆可用单车 */
int borrow_bike(int zone_id);

/* 还车：用户把某辆车还到指定区域 */
int return_bike(int bike_id, int zone_id);

/*查看单车状态*/
void view_bike_status();
#endif
