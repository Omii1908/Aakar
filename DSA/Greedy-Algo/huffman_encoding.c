#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    char data;
    int freq;
    struct Node *left, *right;
} Node;

typedef struct {
    Node **array;
    int size;
} MinHeap;

Node* createNode(char data, int freq) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->data = data;
    node->freq = freq;
    node->left = node->right = NULL;
    return node;
}

MinHeap* createMinHeap(int size) {
    MinHeap *minHeap = (MinHeap *)malloc(sizeof(MinHeap));
    minHeap->size = 0;
    minHeap->array = (Node **)malloc(size * sizeof(Node *));
    return minHeap;
}

void swapNodes(Node **a, Node **b) {
    Node *temp = *a;
    *a = *b;
    *b = temp;
}

void minHeapify(MinHeap *minHeap, int idx) {
    int smallest = idx, left = 2 * idx + 1, right = 2 * idx + 2;

    if (left < minHeap->size && minHeap->array[left]->freq < minHeap->array[smallest]->freq) {
        smallest = left;
    }
    if (right < minHeap->size && minHeap->array[right]->freq < minHeap->array[smallest]->freq) {
        smallest = right;
    }

    if (smallest != idx) {
        swapNodes(&minHeap->array[smallest], &minHeap->array[idx]);
        minHeapify(minHeap, smallest);
    }
}

Node* extractMin(MinHeap *minHeap) {
    Node *temp = minHeap->array[0];
    minHeap->array[0] = minHeap->array[minHeap->size - 1];
    --minHeap->size;
    minHeapify(minHeap, 0);
    return temp;
}

void insertMinHeap(MinHeap *minHeap, Node *node) {
    ++minHeap->size;
    int i = minHeap->size - 1;

    while (i && node->freq < minHeap->array[(i - 1) / 2]->freq) {
        minHeap->array[i] = minHeap->array[(i - 1) / 2];
        i = (i - 1) / 2;
    }
    minHeap->array[i] = node;
}

void buildMinHeap(MinHeap *minHeap) {
    for (int i = (minHeap->size - 2) / 2; i >= 0; i--) {
        minHeapify(minHeap, i);
    }
}

MinHeap* createAndBuildMinHeap(char data[], int freq[], int size) {
    MinHeap *minHeap = createMinHeap(size);
    for (int i = 0; i < size; i++) {
        minHeap->array[i] = createNode(data[i], freq[i]);
    }
    minHeap->size = size;
    buildMinHeap(minHeap);
    return minHeap;
}

Node* buildHuffmanTree(char data[], int freq[], int size) {
    Node *left, *right, *top;

    MinHeap *minHeap = createAndBuildMinHeap(data, freq, size);
    while (minHeap->size != 1) {
        left = extractMin(minHeap);
        right = extractMin(minHeap);

        top = createNode('$', left->freq + right->freq);
        top->left = left;
        top->right = right;

        insertMinHeap(minHeap, top);
    }
    return extractMin(minHeap);
}

void printCodes(Node *root, int arr[], int top) {
    if (root->left) {
        arr[top] = 0;
        printCodes(root->left, arr, top + 1);
    }

    if (root->right) {
        arr[top] = 1;
        printCodes(root->right, arr, top + 1);
    }

    if (!(root->left || root->right)) {
        printf("%c: ", root->data);
        for (int i = 0; i < top; i++) {
            printf("%d", arr[i]);
        }
        printf("\n");
    }
}

void HuffmanCodes(char data[], int freq[], int size) {
    Node *root = buildHuffmanTree(data, freq, size);
    int arr[100], top = 0;
    printCodes(root, arr, top);
}

int main() {
    char data[] = {'a', 'b', 'c', 'd', 'e', 'f'};
    int freq[] = {5, 9, 12, 13, 16, 45};
    int size = sizeof(data) / sizeof(data[0]);

    HuffmanCodes(data, freq, size);
    return 0;
}
