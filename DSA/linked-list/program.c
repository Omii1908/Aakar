#include <stdio.h>
#include <stdlib.h>

// Define the structure for a node
struct Node {
    int data;
    struct Node* next;
};

// Function to create a new node
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

// Function to insert a node at the end of the list
void insertNode(struct Node** head, int data) {
    struct Node* newNode = createNode(data);
    if (*head == NULL) {
        *head = newNode;
    } else {
        struct Node* temp = *head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
}

// Function to delete a node
void deleteNode(struct Node** head, int data) {
    struct Node* temp = *head;
    struct Node* prev = NULL;

    // If the node to be deleted is the head
    if (temp != NULL && temp->data == data) {
        *head = temp->next;
        free(temp);
        return;
    }

    // Search for the node to be deleted
    while (temp != NULL && temp->data != data) {
        prev = temp;
        temp = temp->next;
    }

    // If the data was not found
    if (temp == NULL) return;

    prev->next = temp->next;
    free(temp);
}

// Function to update a node's data
void updateNode(struct Node* head, int oldData, int newData) {
    struct Node* temp = head;
    while (temp != NULL) {
        if (temp->data == oldData) {
            temp->data = newData;
            return;
        }
        temp = temp->next;
    }
}

// Function to sort the linked list
void sortList(struct Node* head) {
    struct Node *i, *j;
    int temp;

    for (i = head; i != NULL; i = i->next) {
        for (j = i->next; j != NULL; j = j->next) {
            if (i->data > j->data) {
                temp = i->data;
                i->data = j->data;
                j->data = temp;
            }
        }
    }
}

// Function to reverse the linked list
void reverseList(struct Node** head) {
    struct Node* prev = NULL;
    struct Node* current = *head;
    struct Node* next = NULL;

    while (current != NULL) {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }

    *head = prev;
}

// Function to display the linked list
void displayList(struct Node* head) {
    struct Node* temp = head;
    if (temp == NULL) {
        printf("The list is empty.\n");
        return;
    }
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

// Menu-driven function
int main() {
    struct Node* head = NULL;
    int choice, data, oldData, newData;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a node\n");
        printf("2. Delete a node\n");
        printf("3. Update a node\n");
        printf("4. Sort the list\n");
        printf("5. Reverse the list\n");
        printf("6. Display the list\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter data to insert: ");
                scanf("%d", &data);
                insertNode(&head, data);
                break;

            case 2:
                printf("Enter data to delete: ");
                scanf("%d", &data);
                deleteNode(&head, data);
                break;

            case 3:
                printf("Enter old data to update: ");
                scanf("%d", &oldData);
                printf("Enter new data: ");
                scanf("%d", &newData);
                updateNode(head, oldData, newData);
                break;

            case 4:
                sortList(head);
                printf("List sorted successfully.\n");
                break;

            case 5:
                reverseList(&head);
                printf("List reversed successfully.\n");
                break;

            case 6:
                displayList(head);
                break;

            case 7:
                printf("Exiting...\n");
                exit(0);

            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
