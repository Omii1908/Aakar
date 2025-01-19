#include <stdio.h>
#include <stdlib.h>

// Structure of a tree node
struct Node {
    int data;
    struct Node* left;
    struct Node* right;
};

// Function to create a new node
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
    return newNode;
}

// Function to insert a new node in the binary tree
struct Node* insertNode(struct Node* root, int data) {
    if (root == NULL) {
        return createNode(data);
    }
    if (data < root->data) {
        root->left = insertNode(root->left, data);
    } else {
        root->right = insertNode(root->right, data);
    }
    return root;
}

// Function to find the minimum value node in the tree
struct Node* findMin(struct Node* root) {
    while (root && root->left != NULL) {
        root = root->left;
    }
    return root;
}

// Function to delete a node from the binary tree
struct Node* deleteNode(struct Node* root, int data) {
    if (root == NULL) {
        return root;
    }
    if (data < root->data) {
        root->left = deleteNode(root->left, data);
    } else if (data > root->data) {
        root->right = deleteNode(root->right, data);
    } else {
        if (root->left == NULL) {
            struct Node* temp = root->right;
            free(root);
            return temp;
        } else if (root->right == NULL) {
            struct Node* temp = root->left;
            free(root);
            return temp;
        }

        struct Node* temp = findMin(root->right);
        root->data = temp->data;
        root->right = deleteNode(root->right, temp->data);
    }
    return root;
}

// Function to update the value of a node in the tree
void updateNode(struct Node* root, int oldData, int newData) {
    if (root == NULL) {
        return;
    }
    if (root->data == oldData) {
        root->data = newData;
    } else if (oldData < root->data) {
        updateNode(root->left, oldData, newData);
    } else {
        updateNode(root->right, oldData, newData);
    }
}

// Function for in-order traversal to sort the tree
void inorderTraversal(struct Node* root) {
    if (root != NULL) {
        inorderTraversal(root->left);
        printf("%d ", root->data);
        inorderTraversal(root->right);
    }
}

// Function to reverse the tree (in-order reverse)
void reverseInorderTraversal(struct Node* root) {
    if (root != NULL) {
        reverseInorderTraversal(root->right);
        printf("%d ", root->data);
        reverseInorderTraversal(root->left);
    }
}

// Function to display the tree (in-order)
void displayTree(struct Node* root) {
    if (root == NULL) {
        printf("The tree is empty.\n");
        return;
    }
    inorderTraversal(root);
    printf("\n");
}

// Menu-driven function
int main() {
    struct Node* root = NULL;
    int choice, data, oldData, newData;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a node\n");
        printf("2. Delete a node\n");
        printf("3. Update a node\n");
        printf("4. Sort the tree (In-order traversal)\n");
        printf("5. Reverse the tree (In-order reverse traversal)\n");
        printf("6. Display the tree\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter data to insert: ");
                scanf("%d", &data);
                root = insertNode(root, data);
                break;

            case 2:
                printf("Enter data to delete: ");
                scanf("%d", &data);
                root = deleteNode(root, data);
                break;

            case 3:
                printf("Enter old data to update: ");
                scanf("%d", &oldData);
                printf("Enter new data: ");
                scanf("%d", &newData);
                updateNode(root, oldData, newData);
                break;

            case 4:
                printf("Sorted Tree (In-order): ");
                inorderTraversal(root);
                printf("\n");
                break;

            case 5:
                printf("Reversed Tree (In-order reverse): ");
                reverseInorderTraversal(root);
                printf("\n");
                break;

            case 6:
                printf("Tree (In-order): ");
                displayTree(root);
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
