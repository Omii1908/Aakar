#include <stdio.h>

int main() {
    int arr[100], n, pos, element, i;

    // Input array size
    printf("Enter number of elements in the array: ");
    scanf("%d", &n);

    // Input array elements
    printf("Enter %d elements:\n", n);
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Input position and new value
    printf("Enter the position to update (1 to %d): ", n);
    scanf("%d", &pos);
    printf("Enter the new value: ");
    scanf("%d", &element);

    // Update the value
    arr[pos - 1] = element;

    // Display updated array
    printf("Array after updating:\n");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }

    return 0;
}
