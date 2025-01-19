#include <stdio.h>
#include <stdlib.h>

// Define the structure of a graph node (adjacency list)
struct Node {
    int vertex;
    struct Node* next;
};

// Define the structure of a graph (using adjacency list)
struct Graph {
    int V;  // Number of vertices
    struct Node** adjList;  // Array of adjacency lists
};

// Create a new graph
struct Graph* createGraph(int V) {
    struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
    graph->V = V;
    graph->adjList = (struct Node**)malloc(V * sizeof(struct Node*));

    for (int i = 0; i < V; i++) {
        graph->adjList[i] = NULL;
    }
    return graph;
}

// Add an edge to the graph (for undirected graph)
void addEdge(struct Graph* graph, int src, int dest) {
    // Add edge from src to dest
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = dest;
    newNode->next = graph->adjList[src];
    graph->adjList[src] = newNode;

    // Add edge from dest to src (since it's an undirected graph)
    newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = src;
    newNode->next = graph->adjList[dest];
    graph->adjList[dest] = newNode;
}

// Depth First Search (DFS)
void DFSUtil(struct Graph* graph, int vertex, int* visited) {
    visited[vertex] = 1;
    printf("%d ", vertex);

    struct Node* adjList = graph->adjList[vertex];
    while (adjList != NULL) {
        int adjacentVertex = adjList->vertex;
        if (!visited[adjacentVertex]) {
            DFSUtil(graph, adjacentVertex, visited);
        }
        adjList = adjList->next;
    }
}

void DFS(struct Graph* graph, int startVertex) {
    int* visited = (int*)malloc(graph->V * sizeof(int));
    for (int i = 0; i < graph->V; i++) {
        visited[i] = 0;
    }
    DFSUtil(graph, startVertex, visited);
    free(visited);
}

// Breadth First Search (BFS)
void BFS(struct Graph* graph, int startVertex) {
    int* visited = (int*)malloc(graph->V * sizeof(int));
    for (int i = 0; i < graph->V; i++) {
        visited[i] = 0;
    }

    visited[startVertex] = 1;
    struct Node* queue = NULL;  // Using Linked List as Queue

    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = startVertex;
    newNode->next = queue;
    queue = newNode;

    while (queue != NULL) {
        int currentVertex = queue->vertex;
        printf("%d ", currentVertex);

        struct Node* temp = queue;
        queue = queue->next;
        free(temp);

        struct Node* adjList = graph->adjList[currentVertex];
        while (adjList != NULL) {
            int adjVertex = adjList->vertex;
            if (!visited[adjVertex]) {
                visited[adjVertex] = 1;

                newNode = (struct Node*)malloc(sizeof(struct Node));
                newNode->vertex = adjVertex;
                newNode->next = queue;
                queue = newNode;
            }
            adjList = adjList->next;
        }
    }
    free(visited);
}

// Check if the graph is connected
int isConnected(struct Graph* graph) {
    int* visited = (int*)malloc(graph->V * sizeof(int));
    for (int i = 0; i < graph->V; i++) {
        visited[i] = 0;
    }

    DFS(graph, 0);  // Assuming the graph is 0-indexed

    for (int i = 0; i < graph->V; i++) {
        if (visited[i] == 0) {
            return 0;  // Not connected
        }
    }
    return 1;  // Graph is connected
}

// Topological Sort (Only for Directed Acyclic Graph)
void topologicalSortUtil(struct Graph* graph, int vertex, int* visited, struct Node** stack) {
    visited[vertex] = 1;

    struct Node* adjList = graph->adjList[vertex];
    while (adjList != NULL) {
        int adjVertex = adjList->vertex;
        if (!visited[adjVertex]) {
            topologicalSortUtil(graph, adjVertex, visited, stack);
        }
        adjList = adjList->next;
    }

    // Push the vertex to the stack
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = vertex;
    newNode->next = *stack;
    *stack = newNode;
}

void topologicalSort(struct Graph* graph) {
    int* visited = (int*)malloc(graph->V * sizeof(int));
    for (int i = 0; i < graph->V; i++) {
        visited[i] = 0;
    }

    struct Node* stack = NULL;

    for (int i = 0; i < graph->V; i++) {
        if (!visited[i]) {
            topologicalSortUtil(graph, i, visited, &stack);
        }
    }

    // Print the topologically sorted order
    printf("Topological Sort: ");
    while (stack != NULL) {
        printf("%d ", stack->vertex);
        struct Node* temp = stack;
        stack = stack->next;
        free(temp);
    }
    printf("\n");

    free(visited);
}

int main() {
    struct Graph* graph = createGraph(5);

    addEdge(graph, 0, 1);
    addEdge(graph, 0, 4);
    addEdge(graph, 1, 2);
    addEdge(graph, 1, 3);
    addEdge(graph, 1, 4);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 4);

    printf("Depth First Search (DFS): ");
    DFS(graph, 0);
    printf("\n");

    printf("Breadth First Search (BFS): ");
    BFS(graph, 0);
    printf("\n");

    if (isConnected(graph)) {
        printf("The graph is connected.\n");
    } else {
        printf("The graph is not connected.\n");
    }

    topologicalSort(graph);

    return 0;
}
