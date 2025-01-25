#include <stdio.h>
#include <string.h>

// Function to perform XOR operation
void xorOperation(char dividend[], char divisor[], int start) {
    for (int i = 0; i < strlen(divisor); i++) {
        dividend[start + i] = (dividend[start + i] == divisor[i]) ? '0' : '1';
    }
}

// Function to perform CRC division
void calculateCRC(char data[], char divisor[], char crc[]) {
    int dataLen = strlen(data);
    int divisorLen = strlen(divisor);

    char dividend[100];
    strcpy(dividend, data); // Copy data to dividend

    for (int i = 0; i <= dataLen - divisorLen; i++) {
        if (dividend[i] == '1') {
            xorOperation(dividend, divisor, i); // Perform XOR when bit is 1
        }
    }

    // The remainder is the CRC
    strncpy(crc, &dividend[dataLen], divisorLen - 1);
    crc[divisorLen - 1] = '\0'; // Null-terminate the CRC string
}

int main() {
    char data[100], divisor[100], transmittedData[100], receivedData[100], crc[100];

    // Input data bits
    printf("Enter the data bits: ");
    scanf("%s", data);

    // Input divisor (polynomial)
    printf("Enter the divisor (polynomial): ");
    scanf("%s", divisor);

    // Append zero bits (length of divisor - 1) to the data
    int dataLen = strlen(data);
    int divisorLen = strlen(divisor);
    for (int i = 0; i < divisorLen - 1; i++) {
        data[dataLen + i] = '0';
    }
    data[dataLen + divisorLen - 1] = '\0'; // Null-terminate the data

    // Calculate CRC
    calculateCRC(data, divisor, crc);

    // Display transmitted data
    snprintf(transmittedData, sizeof(transmittedData), "%s%s", data, crc);
    printf("\nTransmitted data: %s\n", transmittedData);

    // Simulate receiving data
    printf("\nEnter received data: ");
    scanf("%s", receivedData);

    // Check for errors
    char receivedCRC[100];
    calculateCRC(receivedData, divisor, receivedCRC);

    int errorDetected = 0;
    for (int i = 0; i < strlen(receivedCRC); i++) {
        if (receivedCRC[i] != '0') {
            errorDetected = 1;
            break;
        }
    }

    if (errorDetected) {
        printf("\nError detected in the received data.\n");
    } else {
        printf("\nNo error detected in the received data.\n");
    }

    return 0;
}
