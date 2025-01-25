Encryption Techniques

This folder contains various classical and modern encryption techniques implemented in C, including both symmetric and asymmetric encryption methods. Each encryption technique is accompanied by its decryption function for demonstration purposes.

Table of Contents

1. Symmetric vs Asymmetric Encryption


2. Encryption Techniques

Caesar Cipher

Monoalphabetic Cipher

Playfair Cipher

Vigenère Cipher

Rail Fence Cipher

Columnar Transposition Cipher

Advanced Encryption Standard (AES)

RSA Cipher

Hill Cipher

One-Time Pad (OTP)



3. How to Run the Programs



Symmetric vs Asymmetric Encryption

Symmetric Encryption

Symmetric encryption uses the same key for both encryption and decryption. The key must be kept secret and shared securely between the sender and the receiver. If the key is compromised, the security of the encrypted data is also compromised. Common symmetric encryption algorithms include the Caesar Cipher, AES, and the Vigenère Cipher.

Example Algorithms:

Caesar Cipher

Monoalphabetic Cipher

Playfair Cipher

Vigenère Cipher

Rail Fence Cipher

Columnar Transposition Cipher

One-Time Pad (OTP)


Asymmetric Encryption

Asymmetric encryption uses a pair of keys: a public key and a private key. The public key is used to encrypt the data, and the private key is used for decryption. This allows secure communication between parties without the need to share a secret key beforehand. The most common asymmetric encryption algorithm is RSA.

Example Algorithms:

RSA Cipher


Encryption Techniques

1. Caesar Cipher

The Caesar cipher is one of the simplest and most well-known encryption techniques. It works by shifting each letter of the plaintext by a certain number of positions in the alphabet. This cipher is a symmetric encryption technique.

Encryption: Shift each letter by a fixed number of positions.

Decryption: Shift each letter back by the same number of positions.


2. Monoalphabetic Cipher

The Monoalphabetic cipher is a substitution cipher in which each letter of the plaintext is replaced by another letter. A key (usually a permutation of the alphabet) is used to determine the mapping of letters.

Encryption: Each letter in the plaintext is replaced by a letter from the key.

Decryption: Each letter in the ciphertext is replaced by the corresponding letter from the key's inverse.


3. Playfair Cipher

The Playfair cipher is a digraph substitution cipher. It encrypts pairs of letters in the plaintext, using a 5x5 key matrix. It is more secure than simple substitution ciphers like Caesar.

Encryption: Encrypt pairs of letters using the key matrix.

Decryption: Decrypt pairs of letters using the key matrix.


4. Vigenère Cipher

The Vigenère cipher is a method of encrypting alphabetic text by using a series of Caesar ciphers based on the letters of a keyword.

Encryption: Each letter in the plaintext is shifted by the position of the corresponding letter in the keyword.

Decryption: Reverse the encryption process using the keyword.


5. Rail Fence Cipher

The Rail Fence cipher is a transposition cipher in which the text is written in a zigzag pattern across multiple rails (rows), and then read off row by row.

Encryption: Write the plaintext in a zigzag pattern across a set number of rails.

Decryption: Reverse the zigzag pattern and read off the ciphertext.


6. Columnar Transposition Cipher

The Columnar Transposition cipher rearranges the letters of the plaintext in a grid based on a keyword, then reads the columns in a specific order determined by the keyword.

Encryption: Rearrange the plaintext into a grid and read the columns based on the keyword.

Decryption: Reverse the column rearrangement to get the plaintext.


7. Advanced Encryption Standard (AES)

AES is a symmetric encryption algorithm widely used for secure data encryption. It uses a block cipher with key sizes of 128, 192, or 256 bits and operates on blocks of data.

Encryption: Data is encrypted in blocks using the key.

Decryption: Data is decrypted in blocks using the same key.


8. RSA Cipher

RSA is an asymmetric encryption algorithm that uses a pair of keys: a public key for encryption and a private key for decryption. It is widely used for secure data transmission.

Encryption: Encrypt data with the recipient’s public key.

Decryption: Decrypt data with the recipient’s private key.


9. Hill Cipher

The Hill cipher is a polygraphic substitution cipher that encrypts plaintext in blocks of n letters at a time, where n is the size of the matrix used for encryption.

Encryption: Use matrix multiplication to encrypt n-letter blocks.

Decryption: Use the inverse of the matrix to decrypt.


10. One-Time Pad (OTP)

The One-Time Pad (OTP) is a symmetric encryption technique that uses a random key as long as the plaintext. If used properly, it is theoretically unbreakable.

Encryption: XOR the plaintext with a random key of the same length.

Decryption: XOR the ciphertext with the same key to retrieve the plaintext.


How to Run the Programs

1. Clone the repository:

git clone https://github.com/your-username/encryption-techniques.git


2. Navigate to the directory:

cd encryption-techniques


3. Compile the C files:

For example, to compile the Caesar Cipher program:

gcc caesar_cipher.c -o caesar_cipher


4. Run the compiled program:

./caesar_cipher



Repeat the process for each encryption technique program in the repository.

Conclusion

This repository provides various encryption techniques, each with its encryption and decryption implementations. Whether you're learning about classical encryption or more modern techniques like AES and RSA, this collection can help you understand the underlying principles of cryptography.

Feel free to explore, modify, and experiment with these programs for learning and practical purposes.



