#include <stdio.h>
#include <stdbool.h>

bool isPrime(int num);

int main() {
    int testNumber = 17;
    if (isPrime(testNumber)) {
        printf("%d 是素数。\n", testNumber);
    } else {
        printf("%d 不是素数。\n", testNumber);
    }
    return 0;
}