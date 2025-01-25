#include <stdio.h>
#include <stdbool.h>

// Function to calculate even parity
int calculateParity(int data[], int size) {
    int parity = 0;
    for (int i = 0; i < size; i++) {
        parity ^= data[i]; // XOR operation for parity calculation
    }
    return parity;
}

// Function to display the data array
void displayData(int data[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", data[i]);
    }
    printf("\n");
}

int main() {
    int data[8]; // Array to hold the 7 data bits + 1 parity bit
    int size = 7; // Number of data bits

    printf("Enter 7 data bits (0 or 1):\n");
    for (int i = 0; i < size; i++) {
        scanf("%d", &data[i]);
    }

    // Calculate parity bit
    int parityBit = calculateParity(data, size);
    data[size] = parityBit; // Append parity bit

    printf("\nTransmitted data with parity bit: ");
    displayData(data, size + 1);

    // Simulating receiving data
    int receivedData[8];
    printf("\nEnter received 8 bits (including parity bit):\n");
    for (int i = 0; i < size + 1; i++) {
        scanf("%d", &receivedData[i]);
    }

    // Check for error in received data
    int receivedParity = calculateParity(receivedData, size + 1);
    if (receivedParity == 0) {
        printf("\nNo error detected in the received data.\n");
    } else {
        printf("\nError detected in the received data.\n");
    }

    return 0;
}
