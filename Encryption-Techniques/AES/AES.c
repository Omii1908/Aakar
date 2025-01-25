#include <stdio.h>
#include <string.h>
#include <openssl/aes.h>

void encryptAES(const unsigned char *text, unsigned char *ciphertext, const unsigned char *key) {
    AES_KEY aesKey;
    AES_set_encrypt_key(key, 128, &aesKey);  // Using a 128-bit key
    AES_encrypt(text, ciphertext, &aesKey);
}

void decryptAES(const unsigned char *ciphertext, unsigned char *plaintext, const unsigned char *key) {
    AES_KEY aesKey;
    AES_set_decrypt_key(key, 128, &aesKey);
    AES_decrypt(ciphertext, plaintext, &aesKey);
}

int main() {
    unsigned char text[16] = "HelloAESExample"; // 16 bytes for AES block size
    unsigned char key[16] = "mysecurekey12345"; // 16 bytes for 128-bit key
    unsigned char ciphertext[16], decryptedtext[16];

    printf("Original Text: %s\n", text);

    // Encryption
    encryptAES(text, ciphertext, key);
    printf("Encrypted Text (Hex): ");
    for (int i = 0; i < 16; i++) {
        printf("%02x ", ciphertext[i]);
    }
    printf("\n");

    // Decryption
    decryptAES(ciphertext, decryptedtext, key);
    printf("Decrypted Text: %s\n", decryptedtext);

    return 0;
}
