#include <stdio.h>
#define RANGE 100

void countingSort(int arr[], int n) {
    int count[RANGE] = {0}, output[n], i;

    printf("Before sorting (Counting Sort): ");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    // Count occurrences of each element
    for (i = 0; i < n; i++) {
        count[arr[i]]++;
    }

    // Update count array to store positions
    for (i = 1; i < RANGE; i++) {
        count[i] += count[i - 1];
    }

    // Build the sorted array
    for (i = n - 1; i >= 0; i--) {
        output[count[arr[i]] - 1] = arr[i];
        count[arr[i]]--;
    }

    // Copy to original array
    for (i = 0; i < n; i++) {
        arr[i] = output[i];
    }

    printf("After sorting (Counting Sort): ");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    int arr[] = {4, 2, 2, 8, 3, 3, 1};
    int n = 7;

    countingSort(arr, n);
    return 0;
}
