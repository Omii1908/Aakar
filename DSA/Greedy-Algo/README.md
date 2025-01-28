Greedy Algorithms

This repository contains implementations of classic greedy algorithms in C. Greedy algorithms are a paradigm for problem-solving that builds up a solution step by step, always choosing the next step that offers the most immediate benefit. The idea is to find an optimal solution by making a series of local optimizations.

Algorithms Included

1. Activity Selection Problem


2. Fractional Knapsack Problem


3. Huffman Encoding




---

1. Activity Selection Problem

Problem Statement

Given n activities with start and end times, select the maximum number of activities that don't overlap. The goal is to find a schedule with the most activities.

Approach

Sort the activities by their ending time.

Iterate through the sorted activities and select an activity if its start time is greater than or equal to the finish time of the previously selected activity.


Key Points

Sorting ensures that we always consider the earliest finishing activity first.

Greedy choice: Always select the next activity that ends the earliest and doesn't overlap.


Input and Output

Input: A list of activities with start and end times.
Output: A subset of non-overlapping activities.

Example

Input:

Activities: [(1, 2), (3, 4), (0, 6), (5, 7), (8, 9), (5, 9)]

Output:

Selected Activities: (1, 2), (3, 4), (5, 7), (8, 9)


---

2. Fractional Knapsack Problem

Problem Statement

Given a set of items with weights and values, and a knapsack with a maximum weight capacity, determine the maximum value you can achieve by selecting items. You can take fractional amounts of an item if needed.

Approach

Calculate the value-to-weight ratio for each item.

Sort the items by this ratio in descending order.

Pick as much of the highest-ratio item as possible until the knapsack is full.


Key Points

Greedy choice: Always choose the item with the highest value-to-weight ratio first.

Fractional selection ensures that the knapsack is filled to capacity for maximum value.


Input and Output

Input: Items with weights and values, and the knapsack capacity.
Output: Maximum value achievable.

Example

Input:

Items: [(10, 60), (20, 100), (30, 120)]
Capacity: 50

Output:

Maximum Value: 240.00


---

3. Huffman Encoding

Problem Statement

Given a set of characters and their frequencies, construct a Huffman Tree and generate a binary encoding for each character. Huffman Encoding is a lossless data compression algorithm.

Approach

Build a priority queue of all characters sorted by frequency.

While there is more than one node in the queue:

Remove the two nodes with the smallest frequency.

Create a new internal node with these two nodes as children and a frequency equal to their sum.

Add this new node back to the queue.


Generate codes by traversing the Huffman Tree.


Key Points

Greedy choice: Always combine the two smallest frequencies first.

Huffman Encoding minimizes the average length of encoded strings.


Input and Output

Input: Characters and their frequencies.
Output: Binary encoding for each character.

Example

Input:

Characters: ['a', 'b', 'c', 'd', 'e', 'f']
Frequencies: [5, 9, 12, 13, 16, 45]

Output:

Huffman Codes:
f: 0
c: 100
d: 101
a: 1100
b: 1101
e: 111


---

How to Run

1. Clone this repository:

git clone https://github.com/omii1908/Manthan.git


2. Compile the desired C file:

gcc activity_selection.c -o activity_selection


3. Run the compiled program:

./activity_selection




---

Why Use Greedy Algorithms?

Greedy algorithms are efficient for problems where:

Local optimal choices lead to a globally optimal solution.

Problems have a greedy-choice property and an optimal substructure.


These algorithms are simple to implement and often have a time complexity of O(n log n) due to sorting.


---

Contributing

Contributions are welcome! If you have any other greedy algorithm examples, feel free to submit a pull request.


