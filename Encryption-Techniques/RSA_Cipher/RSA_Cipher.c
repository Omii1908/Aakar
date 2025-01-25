#include <stdio.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>

void generateRSAKeys() {
    RSA *rsa = RSA_generate_key(2048, RSA_F4, NULL, NULL);
    FILE *privateFile = fopen("private.pem", "wb");
    FILE *publicFile = fopen("public.pem", "wb");

    PEM_write_RSAPrivateKey(privateFile, rsa, NULL, NULL, 0, NULL, NULL);
    PEM_write_RSAPublicKey(publicFile, rsa);

    fclose(privateFile);
    fclose(publicFile);
    RSA_free(rsa);

    printf("RSA keys generated and saved to private.pem and public.pem\n");
}

void rsaEncryptDecrypt(const char *text) {
    FILE *publicFile = fopen("public.pem", "rb");
    FILE *privateFile = fopen("private.pem", "rb");

    RSA *rsaPublic = PEM_read_RSAPublicKey(publicFile, NULL, NULL, NULL);
    RSA *rsaPrivate = PEM_read_RSAPrivateKey(privateFile, NULL, NULL, NULL);

    char encrypted[256];
    char decrypted[256];

    int encryptedLen = RSA_public_encrypt(strlen(text) + 1, (unsigned char *)text, (unsigned char *)encrypted, rsaPublic, RSA_PKCS1_OAEP_PADDING);
    if (encryptedLen == -1) {
        printf("Encryption failed: %s\n", ERR_error_string(ERR_get_error(), NULL));
        return;
    }
    printf("Encrypted Text (Hex): ");
    for (int i = 0; i < encryptedLen; i++) {
        printf("%02x ", (unsigned char)encrypted[i]);
    }
    printf("\n");

    int decryptedLen = RSA_private_decrypt(encryptedLen, (unsigned char *)encrypted, (unsigned char *)decrypted, rsaPrivate, RSA_PKCS1_OAEP_PADDING);
    if (decryptedLen == -1) {
        printf("Decryption failed: %s\n", ERR_error_string(ERR_get_error(), NULL));
        return;
    }
    printf("Decrypted Text: %s\n", decrypted);

    RSA_free(rsaPublic);
    RSA_free(rsaPrivate);
    fclose(publicFile);
    fclose(privateFile);
}

int main() {
    const char *text = "HelloRSA";

    generateRSAKeys();
    rsaEncryptDecrypt(text);

    return 0;
}
