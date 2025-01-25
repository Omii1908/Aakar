#include <stdio.h>

// Function to calculate checksum
unsigned int calculateChecksum(unsigned int data[], int size) {
    unsigned int sum = 0;

    // Sum all data blocks
    for (int i = 0; i < size; i++) {
        sum += data[i];
        // Wrap around if there's an overflow
        if (sum > 0xFFFF) {
            sum = (sum & 0xFFFF) + 1;
        }
    }

    // Return one's complement of the sum
    return ~sum & 0xFFFF;
}

int main() {
    int n;
    printf("Enter the number of data blocks: ");
    scanf("%d", &n);

    unsigned int data[n];
    printf("Enter %d data blocks (16-bit integers):\n", n);
    for (int i = 0; i < n; i++) {
        scanf("%u", &data[i]);
    }

    // Calculate checksum for transmission
    unsigned int checksum = calculateChecksum(data, n);
    printf("\nCalculated Checksum (to be sent): 0x%X\n", checksum);

    // Simulate receiving data
    unsigned int receivedData[n + 1];
    printf("\nEnter received data blocks (including checksum):\n");
    for (int i = 0; i < n + 1; i++) {
        scanf("%u", &receivedData[i]);
    }

    // Verify checksum at the receiver
    unsigned int receivedChecksum = calculateChecksum(receivedData, n + 1);

    if (receivedChecksum == 0) {
        printf("\nNo error detected in the received data.\n");
    } else {
        printf("\nError detected in the received data.\n");
    }

    return 0;
}
