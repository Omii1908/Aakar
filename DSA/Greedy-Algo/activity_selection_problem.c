#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int start, end;
} Activity;

int compare(const void *a, const void *b) {
    return ((Activity *)a)->end - ((Activity *)b)->end;
}

void activitySelection(Activity activities[], int n) {
    qsort(activities, n, sizeof(Activity), compare);
    printf("Selected Activities:\n");

    int lastSelected = 0;
    printf("Activity (Start: %d, End: %d)\n", activities[lastSelected].start, activities[lastSelected].end);

    for (int i = 1; i < n; i++) {
        if (activities[i].start >= activities[lastSelected].end) {
            printf("Activity (Start: %d, End: %d)\n", activities[i].start, activities[i].end);
            lastSelected = i;
        }
    }
}

int main() {
    Activity activities[] = {{1, 2}, {3, 4}, {0, 6}, {5, 7}, {8, 9}, {5, 9}};
    int n = sizeof(activities) / sizeof(activities[0]);
    activitySelection(activities, n);
    return 0;
}
