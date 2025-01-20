#include <stdio.h>
int main() {
    int a = 5, b = 3;
    printf("Bitwise AND: %d\n", a & b);
    printf("Bitwise OR: %d\n", a | b);
    printf("Bitwise XOR: %d\n", a ^ b);
    printf("Bitwise NOT: %d\n", ~a);
    printf("Left Shift (a << 1): %d\n", a << 1);
    printf("Right Shift (a >> 1): %d\n", a >> 1);
    return 0;
}
