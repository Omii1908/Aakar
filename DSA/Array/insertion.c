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

    // Input position and element to insert
    printf("Enter the position to insert (1 to %d): ", n + 1);
    scanf("%d", &pos);
    printf("Enter the element to insert: ");
    scanf("%d", &element);

    // Shift elements to the right to make space
    for (i = n; i >= pos; i--) {
        arr[i] = arr[i - 1];
    }

    // Insert the element
    arr[pos - 1] = element;
    n++;

    // Display updated array
    printf("Array after insertion:\n");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }

    return 0;
}
