#ifndef COMMON_H
#define COMMON_H
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

#define ZONE_FILE  "zones.dat"
#define BIKE_FILE  "bikes.dat"
#define ORDER_FILE "orders.dat"
#define OVER_THRESHOLD  0.90   /* 达到容量的 90 % 就可以搬出 */
#define UNDER_THRESHOLD 0.70   // 已存在可保留
typedef struct Zone Zone;
typedef struct Bike Bike;
typedef struct Order Order;
double shortage_score(Zone* z);// 加这一行
struct Zone {
    int  id;
    double x, y;
    int    cap;
    int    cnt;
    Bike** bikes;
    int    bike_top;
};
//单车状态用枚举
typedef enum { AVAIL = 0, USING, DEFECT } BikeStatus;

struct Bike {
    int   id;
    BikeStatus status;
    Zone* loc;
    time_t last_used;
};

struct Order {
    int id;
    Bike* bp;
    Zone* from;
    Zone* to;
    double cost;
};



/* 放 extern 声明*/
extern Zone** g_zones;
extern int    g_zone_cnt;
extern Bike** g_bikes;
extern int    g_bike_cnt;
extern Order** g_orders;
extern int    g_order_cnt;
static inline double shortage_score(Zone* z) {
    return (double)(z->cap - z->cnt);   // 绝对空位数
}

#endif /* COMMON_H */
