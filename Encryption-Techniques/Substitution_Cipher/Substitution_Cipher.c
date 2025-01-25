#include <stdio.h>
#include <string.h>

void encrypt(char *text, char *key) {
    for (int i = 0; text[i] != '\0'; i++) {
        if (text[i] >= 'A' && text[i] <= 'Z') {
            text[i] = key[text[i] - 'A'];
        } else if (text[i] >= 'a' && text[i] <= 'z') {
            text[i] = key[text[i] - 'a'] + 32;
        }
    }
}

void decrypt(char *text, char *key) {
    char inverseKey[26];
    for (int i = 0; i < 26; i++) {
        inverseKey[key[i] - 'A'] = 'A' + i;
    }

    for (int i = 0; text[i] != '\0'; i++) {
        if (text[i] >= 'A' && text[i] <= 'Z') {
            text[i] = inverseKey[text[i] - 'A'];
        } else if (text[i] >= 'a' && text[i] <= 'z') {
            text[i] = inverseKey[text[i] - 'a'] + 32;
        }
    }
}

int main() {
    char text[100];
    char key[27];

    printf("Enter the substitution key (26 unique uppercase letters): ");
    scanf("%s", key);
    printf("Enter the text: ");
    scanf(" %[^\n]s", text);

    encrypt(text, key);
    printf("Encrypted text: %s\n", text);

    decrypt(text, key);
    printf("Decrypted text: %s\n", text);

    return 0;
}
