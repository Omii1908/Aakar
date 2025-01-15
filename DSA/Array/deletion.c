#include <stdio.h>

int main() {
    int arr[100], n, pos, i;

    // Input array size
    printf("Enter number of elements in the array: ");
    scanf("%d", &n);

    // Input array elements
    printf("Enter %d elements:\n", n);
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Input position to delete
    printf("Enter the position to delete (1 to %d): ", n);
    scanf("%d", &pos);

    // Shift elements to the left to fill the gap
    for (i = pos - 1; i < n - 1; i++) {
        arr[i] = arr[i + 1];
    }
    n--;

    // Display updated array
    printf("Array after deletion:\n");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }

    return 0;
}
