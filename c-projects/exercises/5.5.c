#include <stdio.h>
#include <stdlib.h>

#define N 3  // 你可以根据需要修改矩阵的大小

// 函数声明
void printMatrix(double matrix[N][2*N]);
int inverseMatrix(double matrix[N][N], double inverse[N][N]);

int main() {
    double matrix[N][N] = {
        {9, 7, 2},
        {3, 9, 6},
        {8, 2, 6}
    };
    double inverse[N][N];

    if (inverseMatrix(matrix, inverse)) {int i,j;
        printf("Inverse of the matrix is:\n");
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                printf("%f ", inverse[i][j]);
            }
            printf("\n");
        }
    } else {
        printf("Inverse doesn't exist\n");
    }

    return 0;
}

// 打印矩阵
void printMatrix(double matrix[N][2*N]) {int i,j;
    for (i = 0; i < N; i++) {
        for (j = 0; j < 2 * N; j++) {
            printf("%f ", matrix[i][j]);
        }
        printf("\n");
    }
}

// 计算逆矩阵
int inverseMatrix(double matrix[N][N], double inverse[N][N]) {
    double temp[N][2*N];
    int i, j, k;

    // 初始化增广矩阵
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            temp[i][j] = matrix[i][j];
            temp[i][j + N] = (i == j) ? 1.0 : 0.0;
        }
    }

    // 行变换
    for (i = 0; i < N; i++) {
        // 寻找主元
        double pivot = temp[i][i];
        if (pivot == 0) {
            return 0;  // 逆矩阵不存在
        }
        for (j = 0; j < 2 * N; j++) {
            temp[i][j] /= pivot;
        }
        for (k = 0; k < N; k++) {
            if (k != i) {
                double factor = temp[k][i];
                for (j = 0; j < 2 * N; j++) {
                    temp[k][j] -= factor * temp[i][j];
                }
            }
        }
    }

    // 提取逆矩阵
    for (i = 0; i < N; i++) {
        for (j = 0; j < N; j++) {
            inverse[i][j] = temp[i][j + N];
        }
    }

    return 1;
}

