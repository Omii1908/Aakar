#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

// Function to generate a random key
void generateKey(char *key, int length) {
    for (int i = 0; i < length; i++) {
        key[i] = 'A' + (rand() % 26);  // Generate random uppercase letters
    }
    key[length] = '\0';
}

// Function to encrypt plaintext using the key
void encryptOTP(const char *plaintext, const char *key, char *ciphertext) {
    int length = strlen(plaintext);
    for (int i = 0; i < length; i++) {
        ciphertext[i] = ((plaintext[i] - 'A') + (key[i] - 'A')) % 26 + 'A';
    }
    ciphertext[length] = '\0';
}

// Function to decrypt ciphertext using the key
void decryptOTP(const char *ciphertext, const char *key, char *plaintext) {
    int length = strlen(ciphertext);
    for (int i = 0; i < length; i++) {
        plaintext[i] = ((ciphertext[i] - 'A') - (key[i] - 'A') + 26) % 26 + 'A';
    }
    plaintext[length] = '\0';
}

int main() {
    char plaintext[100], key[100], ciphertext[100], decrypted[100];

    // Input plaintext (uppercase letters only)
    printf("Enter the plaintext (uppercase letters only): ");
    scanf("%s", plaintext);

    int length = strlen(plaintext);

    // Generate a random key of the same length
    srand(time(0));
    generateKey(key, length);
    printf("Generated Key: %s\n", key);

    // Encrypt the plaintext
    encryptOTP(plaintext, key, ciphertext);
    printf("Encrypted Text: %s\n", ciphertext);

    // Decrypt the ciphertext
    decryptOTP(ciphertext, key, decrypted);
    printf("Decrypted Text: %s\n", decrypted);

    return 0;
}
