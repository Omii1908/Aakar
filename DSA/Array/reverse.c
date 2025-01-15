#include <stdio.h>

int main() {
    int arr[100], n, i;

    // Input array size
    printf("Enter number of elements in the array: ");
    scanf("%d", &n);

    // Input array elements
    printf("Enter %d elements:\n", n);
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Display reversed array
    printf("Reversed array:\n");
    for (i = n - 1; i >= 0; i--) {
        printf("%d ", arr[i]);
    }

    return 0;
}
