#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int weight;
    int value;
    double ratio;
} Item;

int compare(const void *a, const void *b) {
    return ((Item *)b)->ratio > ((Item *)a)->ratio ? 1 : -1;
}

double fractionalKnapsack(int capacity, Item items[], int n) {
    qsort(items, n, sizeof(Item), compare);

    double totalValue = 0.0;
    for (int i = 0; i < n; i++) {
        if (capacity >= items[i].weight) {
            capacity -= items[i].weight;
            totalValue += items[i].value;
        } else {
            totalValue += items[i].value * ((double)capacity / items[i].weight);
            break;
        }
    }
    return totalValue;
}

int main() {
    Item items[] = {{10, 60, 0}, {20, 100, 0}, {30, 120, 0}};
    int n = sizeof(items) / sizeof(items[0]);

    for (int i = 0; i < n; i++) {
        items[i].ratio = (double)items[i].value / items[i].weight;
    }

    int capacity = 50;
    printf("Maximum value in Knapsack = %.2f\n", fractionalKnapsack(capacity, items, n));
    return 0;
}
