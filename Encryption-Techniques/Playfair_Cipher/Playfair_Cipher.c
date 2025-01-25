#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define SIZE 5

void createKeyMatrix(char key[], char keyMatrix[SIZE][SIZE]) {
    int visited[26] = {0};
    visited['J' - 'A'] = 1; // Treat 'I' and 'J' as the same
    int k = 0, row = 0, col = 0;

    for (int i = 0; key[i] != '\0'; i++) {
        if (!visited[toupper(key[i]) - 'A']) {
            keyMatrix[row][col] = toupper(key[i]);
            visited[toupper(key[i]) - 'A'] = 1;
            col++;
            if (col == SIZE) {
                col = 0;
                row++;
            }
        }
    }

    for (char c = 'A'; c <= 'Z'; c++) {
        if (!visited[c - 'A']) {
            keyMatrix[row][col] = c;
            visited[c - 'A'] = 1;
            col++;
            if (col == SIZE) {
                col = 0;
                row++;
            }
        }
    }
}

void displayMatrix(char keyMatrix[SIZE][SIZE]) {
    printf("Key Matrix:\n");
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%c ", keyMatrix[i][j]);
        }
        printf("\n");
    }
}

// Placeholder for encryption and decryption (Playfair cipher is more complex to implement)
void encryptPlayfair(char *text, char keyMatrix[SIZE][SIZE]) {
    printf("Encryption is not implemented.\n");
}

void decryptPlayfair(char *text, char keyMatrix[SIZE][SIZE]) {
    printf("Decryption is not implemented.\n");
}

int main() {
    char key[50], text[100];
    char keyMatrix[SIZE][SIZE];

    printf("Enter the key: ");
    scanf("%[^\n]s", key);
    getchar(); // Consume newline character
    printf("Enter the text: ");
    scanf("%[^\n]s", text);

    createKeyMatrix(key, keyMatrix);
    displayMatrix(keyMatrix);

    encryptPlayfair(text, keyMatrix);
    decryptPlayfair(text, keyMatrix);

    return 0;
}
