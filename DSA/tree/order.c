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

// Pre-order traversal (Root, Left, Right)
void preOrderTraversal(struct Node* root) {
    if (root != NULL) {
        printf("%d ", root->data);  // Visit root
        preOrderTraversal(root->left);  // Traverse left subtree
        preOrderTraversal(root->right);  // Traverse right subtree
    }
}

// In-order traversal (Left, Root, Right)
void inOrderTraversal(struct Node* root) {
    if (root != NULL) {
        inOrderTraversal(root->left);  // Traverse left subtree
        printf("%d ", root->data);  // Visit root
        inOrderTraversal(root->right);  // Traverse right subtree
    }
}

// Post-order traversal (Left, Right, Root)
void postOrderTraversal(struct Node* root) {
    if (root != NULL) {
        postOrderTraversal(root->left);  // Traverse left subtree
        postOrderTraversal(root->right);  // Traverse right subtree
        printf("%d ", root->data);  // Visit root
    }
}

// Function to display the tree using different traversals
void displayTree(struct Node* root) {
    printf("Pre-order Traversal: ");
    preOrderTraversal(root);
    printf("\n");

    printf("In-order Traversal: ");
    inOrderTraversal(root);
    printf("\n");

    printf("Post-order Traversal: ");
    postOrderTraversal(root);
    printf("\n");
}

// Menu-driven function
int main() {
    struct Node* root = NULL;
    int choice, data;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a node\n");
        printf("2. Display the tree (All traversals)\n");
        printf("3. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter data to insert: ");
                scanf("%d", &data);
                root = insertNode(root, data);
                break;

            case 2:
                printf("Tree Traversals:\n");
                displayTree(root);
                break;

            case 3:
                printf("Exiting...\n");
                return 0;

            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
