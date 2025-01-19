#include <stdio.h>

#define MAX_SIZE 100

// Function to display the array
void displayArray(int arr[], int size) {
    if (size == 0) {
        printf("The array is empty.\n");
        return;
    }
    printf("Array: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Function to insert an element at the end of the array
void insertElement(int arr[], int* size, int data) {
    if (*size < MAX_SIZE) {
        arr[*size] = data;
        (*size)++;
    } else {
        printf("Array is full.\n");
    }
}

// Function to delete an element from the array
void deleteElement(int arr[], int* size, int data) {
    int i, found = 0;
    for (i = 0; i < *size; i++) {
        if (arr[i] == data) {
            found = 1;
            break;
        }
    }
    if (!found) {
        printf("Element not found.\n");
        return;
    }
    for (int j = i; j < *size - 1; j++) {
        arr[j] = arr[j + 1];
    }
    (*size)--;
}

// Function to update an element in the array
void updateElement(int arr[], int size, int oldData, int newData) {
    for (int i = 0; i < size; i++) {
        if (arr[i] == oldData) {
            arr[i] = newData;
            return;
        }
    }
    printf("Element not found.\n");
}

// Function to sort the array in ascending order
void sortArray(int arr[], int size) {
    int temp;
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    printf("Array sorted successfully.\n");
}

// Function to reverse the array
void reverseArray(int arr[], int size) {
    int temp;
    int start = 0;
    int end = size - 1;
    while (start < end) {
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }
    printf("Array reversed successfully.\n");
}

// Menu-driven function
int main() {
    int arr[MAX_SIZE];
    int size = 0;
    int choice, data, oldData, newData;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert an element\n");
        printf("2. Delete an element\n");
        printf("3. Update an element\n");
        printf("4. Sort the array\n");
        printf("5. Reverse the array\n");
        printf("6. Display the array\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter data to insert: ");
                scanf("%d", &data);
                insertElement(arr, &size, data);
                break;

            case 2:
                printf("Enter data to delete: ");
                scanf("%d", &data);
                deleteElement(arr, &size, data);
                break;

            case 3:
                printf("Enter old data to update: ");
                scanf("%d", &oldData);
                printf("Enter new data: ");
                scanf("%d", &newData);
                updateElement(arr, size, oldData, newData);
                break;

            case 4:
                sortArray(arr, size);
                break;

            case 5:
                reverseArray(arr, size);
                break;

            case 6:
                displayArray(arr, size);
                break;

            case 7:
                printf("Exiting...\n");
                return 0;

            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
