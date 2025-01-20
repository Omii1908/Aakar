#include <stdio.h>
int findUnique(int arr[], int n) {
    int result = 0;
    for (int i = 0; i < n; i++) {
        result ^= arr[i];
    }
    return result;
}
int main() {
    int arr[] = {2, 3, 5, 4, 5, 3, 2};
    int n = sizeof(arr) / sizeof(arr[0]);
    printf("The unique element is %d\n", findUnique(arr, n));
    return 0;
}
