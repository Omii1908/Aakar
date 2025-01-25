#include <stdio.h>
#include <string.h>

void encryptRailFence(char *text, int key, char *encrypted) {
    int len = strlen(text);
    int row, col, dir = 1;
    int rail[key][len];

    memset(rail, -1, sizeof(rail));

    for (row = 0, col = 0; col < len; col++) {
        rail[row][col] = text[col];
        row += dir;
        if (row == 0 || row == key - 1)
            dir = -dir;
    }

    int index = 0;
    for (row = 0; row < key; row++) {
        for (col = 0; col < len; col++) {
            if (rail[row][col] != -1)
                encrypted[index++] = rail[row][col];
        }
    }
    encrypted[index] = '\0';
}

void decryptRailFence(char *encrypted, int key, char *decrypted) {
    int len = strlen(encrypted);
    int row, col, dir = 1;
    int rail[key][len];

    memset(rail, -1, sizeof(rail));

    for (row = 0, col = 0; col < len; col++) {
        rail[row][col] = 0;
        row += dir;
        if (row == 0 || row == key - 1)
            dir = -dir;
    }

    int index = 0;
    for (row = 0; row < key; row++) {
        for (col = 0; col < len; col++) {
            if (rail[row][col] == 0)
                rail[row][col] = encrypted[index++];
        }
    }

    for (row = 0, col = 0, dir = 1; col < len; col++) {
        decrypted[col] = rail[row][col];
        row += dir;
        if (row == 0 || row == key - 1)
            dir = -dir;
    }
    decrypted[len] = '\0';
}

int main() {
    char text[100], encrypted[100], decrypted[100];
    int key;

    printf("Enter the text: ");
    scanf("%[^\n]s", text);
    printf("Enter the key: ");
    scanf("%d", &key);

    encryptRailFence(text, key, encrypted);
    printf("Encrypted text: %s\n", encrypted);

    decryptRailFence(encrypted, key, decrypted);
    printf("Decrypted text: %s\n", decrypted);

    return 0;
}
