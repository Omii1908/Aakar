#include <stdio.h>
#include <string.h>

void xorCipher(char *text, char key) {
    for (int i = 0; text[i] != '\0'; i++) {
        text[i] = text[i] ^ key;
    }
}

int main() {
    char text[100];
    char key;

    printf("Enter the text: ");
    scanf("%[^\n]s", text);
    getchar(); // Consume newline character
    printf("Enter the key (a single character): ");
    scanf("%c", &key);

    xorCipher(text, key);
    printf("Encrypted text: %s\n", text);

    xorCipher(text, key);  // Decrypting with the same key
    printf("Decrypted text: %s\n", text);

    return 0;
}
