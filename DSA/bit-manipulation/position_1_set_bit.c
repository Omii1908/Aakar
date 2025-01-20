#include <stdio.h>
int findFirstSetBit(int n) {
    if (n == 0) return -1; // No set bits
    return __builtin_ffs(n); // GCC built-in function
}
int main() {
    int num = 18; // Example number
    printf("Position of first set bit in %d is %d\n", num, findFirstSetBit(num));
    return 0;
}
