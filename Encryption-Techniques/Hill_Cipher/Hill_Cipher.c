#include <stdio.h>
#include <string.h>

void multiplyMatrix(int key[2][2], int text[2], int result[2]) {
    for (int i = 0; i < 2; i++) {
        result[i] = 0;
        for (int j = 0; j < 2; j++) {
            result[i] += key[i][j] * text[j];
        }
        result[i] %= 26;
    }
}

void encryptHillCipher(char *text, int key[2][2]) {
    int length = strlen(text);
    int plaintext[2], ciphertext[2];

    for (int i = 0; i < length; i += 2) {
        plaintext[0] = text[i] - 'A';
        plaintext[1] = text[i + 1] - 'A';
        multiplyMatrix(key, plaintext, ciphertext);
        text[i] = ciphertext[0] + 'A';
        text[i + 1] = ciphertext[1] + 'A';
    }
}

void decryptHillCipher(char *text, int inverseKey[2][2]) {
    int length = strlen(text);
    int ciphertext[2], plaintext[2];

    for (int i = 0; i < length; i += 2) {
        ciphertext[0] = text[i] - 'A';
        ciphertext[1] = text[i + 1] - 'A';
        multiplyMatrix(inverseKey, ciphertext, plaintext);
        text[i] = plaintext[0] + 'A';
        text[i + 1] = plaintext[1] + 'A';
    }
}

int main() {
    char text[100];
    int key[2][2] = {{5, 17}, {4, 15}};
    int inverseKey[2][2] = {{15, 9}, {4, 5}}; // Pre-calculated inverse of key mod 26

    printf("Enter the text (in multiples of 2): ");
    scanf("%s", text);

    encryptHillCipher(text, key);
    printf("Encrypted Text: %s\n", text);

    decryptHillCipher(text, inverseKey);
    printf("Decrypted Text: %s\n", text);

    return 0;
}
