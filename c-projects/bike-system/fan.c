#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

//抽卡规则常量: 
#define BASE_PROB 0.8          
//基础五星概率（%）
#define SOFT_PITY_START 64     
//软保底递增起始发数,其中65-67,70-79每抽递增6%，
//68抽概率为25.6；69抽概率为35.6；70抽概率为42.6； 
#define SOFT_PITY_FULL 80      
//小保底必中发数
#define HARD_PITY 160          
//大保底必出UP发数
#define UP_RATE 50.0           
//小保底UP概率（%）
#define UP_CHAR1 "卜灵" 
#define UP_CHAR2 "散华"
#define UP_CHAR3 "丹瑾"
//当前UP四星角色（可替换） 
#define UP_CHAR "忌炎"
//当前UP五星角色（可替换）
#define UP_WEAPON ""
//当前UP武器 （可替换） 

//存储鸣潮四星角色列表
 char *four_star_roles[]=
{
    "卜灵", "散华", "莫特斐", "丹瑾", "白芷","灯灯","桃祈","秧秧","秋水","炽霞","渊武","釉瑚" 
	}; 

//存储鸣潮五星角色列表
 char *five_star_roles[] = 
{
    "卡卡罗", "安可", "凌阳", "鉴心", "维里奈"
};
void paixuchangzhu()
{
	for(int i=11;i>=0;i--)
    {
	   int h=11;
	   if (strcmp(four_star_roles[i], UP_CHAR1) == 0 ||
            strcmp(four_star_roles[i], UP_CHAR2) == 0 ||
            strcmp(four_star_roles[i], UP_CHAR3) == 0) 
	   {
            char *temp = four_star_roles[i];  //临时指针存地址
            four_star_roles[i] = four_star_roles[h];
            four_star_roles[h] = temp;
		h=h-1;
	    }
    } 
//0到8为未UP四星角色 
}

// 抽卡状态结构体（存储所有抽卡相关数据，避免全局变量）
typedef struct 
{
    int current_pity;    // 距离上次五星的次数 
    int current_pity1;    // 距离上次四星的次数 
    int total_pulls;     // 总抽卡次数
    int up_count;        // UP角色获得数
    int std_count;       // 常驻五星获得数
    int big_pity_flag;   // 大保底标记（1=下保底必出UP）
} jlck;

//  初始化抽卡状态（首次使用前调用）
void chushihua(jlck *state) 
{
    state->current_pity = 0;
    state->current_pity1 = 0;
    state->total_pulls = 0;
    state->up_count = 0;
    state->std_count = 0;
    state->big_pity_flag = 0;
}

//  计算当前垫刀对应的五星概率
static float gl1(int current_pity) 
{
    if (current_pity < SOFT_PITY_START) 
	{
        return BASE_PROB;
		//64发及以前：0.8%
    } 
	else if (current_pity <= 67 && current_pity>64) 
	{
        return BASE_PROB+(current_pity-SOFT_PITY_START)*6;
		//65-67之间，每次递增6%
    } 
    else if (current_pity=68)
    {
    	return 25.6;
	}
	else if (current_pity=69)
    {
    	return 35.6;
	}
	else if (current_pity=70)
    {
    	return 42.6;
	}
	else if (current_pity < 67) 
	{
        return 42.6+(current_pity-70)*6;
		//71-79之间，每次递增6%
    } 
	else 
	{
        return 100.0;
		//80发及以上必中
    }
}

//单抽调用函数 
int danchou(jlck *state, int *is_up) 
{
    *is_up=-1;
    state->total_pulls++;
    state->current_pity++;
    state->current_pity1++;
    
    float prob1=gl1(state->current_pity);  
	// 调用五星概率函数
    float sjs1=(rand()%10000)/100.0;
	// 生成0.00~99.99的随机数,用于五星角色计算 
	float sjs2=(rand()%10000)/100.0;
	// 生成0.00~99.99的随机数，用于四星角色计算 

    if (sjs1<=prob1) 
	{
        if (state->big_pity_flag) 
		{
            *is_up = 1;
            state->up_count++;
            state->big_pity_flag = 0;
            printf("第%d抽：★★★★★ 恭喜获得UP角色【%s】！\n", state->total_pulls, UP_CHAR);
        } 
		else 
		{
            float up_random=(rand() % 10000) / 100.0;
            if (up_random<=UP_RATE) 
			{
                *is_up = 1;
                state->up_count++;
                state->big_pity_flag = 0;
                printf("第%d抽：★★★★★ 恭喜获得UP角色【%s】！\n", state->total_pulls, UP_CHAR);
            } 
			else 
			{
                *is_up = 0;
                state->std_count++;
                state->big_pity_flag = 1;
                int up_random2=(rand() % 10000) / 5;
                switch(up_random2)
                {
                	case 0:
                		printf("第%d抽：★★★★★ 获得常驻五星%s！（歪了，下保底必出UP）\n", state->total_pulls,*five_star_roles[0]);
                		break;
                	case 1:
                		printf("第%d抽：★★★★★ 获得常驻五星%s！（歪了，下保底必出UP）\n", state->total_pulls,*five_star_roles[1]);
                		break;
					case 2:
                		printf("第%d抽：★★★★★ 获得常驻五星%s！（歪了，下保底必出UP）\n", state->total_pulls,*five_star_roles[2]);
                		break;	
					case 3:
                		printf("第%d抽：★★★★★ 获得常驻五星%s！（歪了，下保底必出UP）\n", state->total_pulls,*five_star_roles[3]);
                		break;	
					case 4:
                		printf("第%d抽：★★★★★ 获得常驻五星%s！（歪了，下保底必出UP）\n", state->total_pulls,*five_star_roles[4]);
                		break;	
							
				}
            }
        }
        state->current_pity = 0;
        return 1;
    } 
	else 
	{
		if(sjs2<=6 || state->current_pity1==10)
		{
			float up_random1=(rand() % 10000) / 100.0;
			if(up_random1<=50)
			{
			    int up_random3=(rand() % 10000) / 3;
			    switch(up_random3)
			    {
			    	case 0:
					printf("第%d抽：★★★★ 恭喜获得UP角色【%s】！\n", state->total_pulls, UP_CHAR1);
					break;
			    	case 1:
					printf("第%d抽：★★★★ 恭喜获得UP角色【%s】！\n", state->total_pulls, UP_CHAR2);
					break;
					case 2:
					printf("第%d抽：★★★★ 恭喜获得UP角色【%s】！\n", state->total_pulls, UP_CHAR3);
					break;	
				}	
			}
			else
			{
				int up_random4=(rand() % 10000) / 8;
			    switch(up_random4)
			    {
			    	case 0:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;
			    	case 1:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;	
			    	case 2:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;	
			    	case 3:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;	
			    	case 4:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;	
			    	case 5:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;	
			    	case 6:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;
					case 7:
			    		printf("第%d抽：★★★★ 恭喜获得角色【%s】！\n", state->total_pulls,*four_star_roles[up_random4] );
			    		break;		
				}
			}
			state->current_pity1=0; 
		}
		else
        printf("第%d抽：未获得四星及五星（当前垫刀：%d发，当前概率：%.1f%%）\n", 
               state->total_pulls, state->current_pity, prob1);  //新增概率显示
        return 0;
    }
}
int main() 
{
    srand((unsigned int)time(NULL)); // 初始化随机数种子
    jlck state;
    chushihua(&state); // 初始化抽卡状态
    paixuchangzhu(); // 调整四星角色顺序，UP角色放在前面

    int choice;
    printf("欢迎使用鸣潮抽卡模拟器！\n");
    printf("1. 单抽\n");
    printf("2. 十连抽\n");
    printf("3. 退出\n");

    while (1) 
    {
        printf("请选择操作（1-3）：");
        scanf("%d", &choice);

        if (choice == 1) 
        {
            int is_up;
            danchou(&state, &is_up);
        } 
        else if (choice == 2) 
        {
            for (int i = 0; i < 10; i++) 
            {
                int is_up;
                danchou(&state, &is_up);
            }
        } 
        else if (choice == 3) 
        {
            printf("感谢使用鸣潮抽卡模拟器！再见！\n");
            break;
        } 
        else 
        {
            printf("无效选择，请重新输入。\n");
        }
    }

    return 0;
}