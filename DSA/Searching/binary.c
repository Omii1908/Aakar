#include <stdio.h>

int main() {
    int arr[100], n, element, low, high, mid, i;

    // Input array size
    printf("Enter the number of elements: ");
    scanf("%d", &n);

    // Input sorted array elements
    printf("Enter %d sorted elements:\n", n);
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Input the element to search
    printf("Enter the element to search: ");
    scanf("%d", &element);

    // Perform binary search
    low = 0;
    high = n - 1;

    while (low <= high) {
        mid = (low + high) / 2;

        if (arr[mid] == element) {
            printf("Element found at position %d\n", mid + 1);
            return 0;
        }
        else if (arr[mid] < element) {
            low = mid + 1; // Focus on the right half
        }
        else {
            high = mid - 1; // Focus on the left half
        }
    }

    printf("Element not found in the array.\n");
    return 0;
}
