#include <stdio.h>
#include <stdlib.h>

void bucketSort(float arr[], int n) {
    int i, j, k;
    int bucketCount = 10;  // Number of buckets
    int bucketSizes[bucketCount];
    float **buckets = malloc(bucketCount * sizeof(float *));

    // Initialize bucket sizes and memory for buckets
    for (i = 0; i < bucketCount; i++) {
        buckets[i] = malloc(n * sizeof(float));
        bucketSizes[i] = 0;
    }

    printf("Before sorting (Bucket Sort): ");
    for (i = 0; i < n; i++) {
        printf("%.2f ", arr[i]);
    }
    printf("\n");

    // Distribute elements into buckets
    for (i = 0; i < n; i++) {
        int index = arr[i] * bucketCount;
        buckets[index][bucketSizes[index]++] = arr[i];
    }

    // Sort each bucket individually using insertion sort
    for (i = 0; i < bucketCount; i++) {
        for (j = 1; j < bucketSizes[i]; j++) {
            float key = buckets[i][j];
            k = j - 1;
            while (k >= 0 && buckets[i][k] > key) {
                buckets[i][k + 1] = buckets[i][k];
                k--;
            }
            buckets[i][k + 1] = key;
        }
    }

    // Merge the sorted elements back into the original array
    k = 0;
    for (i = 0; i < bucketCount; i++) {
        for (j = 0; j < bucketSizes[i]; j++) {
            arr[k++] = buckets[i][j];
        }
    }

    printf("After sorting (Bucket Sort): ");
    for (i = 0; i < n; i++) {
        printf("%.2f ", arr[i]);
    }
    printf("\n");

    // Free allocated memory for buckets
    for (i = 0; i < bucketCount; i++) {
        free(buckets[i]);
    }
    free(buckets);
}

int main() {
    float arr[] = {0.42, 0.32, 0.23, 0.57, 0.12, 0.75, 0.88};
    int n = 7;

    bucketSort(arr, n);
    return 0;
}
