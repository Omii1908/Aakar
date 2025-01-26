Error Detection

This folder contains C programs to demonstrate three fundamental methods for error detection in data transmission. These methods are used to identify errors in transmitted data to ensure reliability in communication systems.


---

Features

The program implements the following error detection techniques:

1. Parity Bit


2. Cyclic Redundancy Check (CRC)


3. Checksum



Each technique is explained in detail below.


---

Methods of Error Detection

1. Parity Bit

The parity bit is a simple error detection mechanism that appends an extra bit to the data to ensure the total number of 1s is either even (even parity) or odd (odd parity).

How It Works:

Transmitter calculates a parity bit based on the data.

Receiver recalculates the parity bit after transmission and compares it to the received parity bit.

If there's a mismatch, an error is detected.


Advantages:

Simple and easy to implement.


Disadvantages:

Can only detect single-bit errors, not multiple-bit errors.




---

2. Cyclic Redundancy Check (CRC)

CRC is a more advanced error detection technique that uses polynomial division to calculate a remainder (CRC code) appended to the data.

How It Works:

Transmitter divides the data by a generator polynomial and appends the remainder (CRC) to the data.

Receiver performs the same division. If the remainder is 0, no error is detected; otherwise, an error is detected.


Advantages:

Highly reliable for detecting burst errors.


Disadvantages:

Slightly more complex than parity bits.




---

3. Checksum

The checksum is calculated by summing up all the data blocks, taking the one's complement of the result, and appending it to the data.

How It Works:

Transmitter calculates a checksum by summing all data blocks and taking the one's complement of the sum.

Receiver sums all received blocks, including the checksum. If the result is 0x0000, no error is detected; otherwise, an error is detected.


Advantages:

Simple yet effective for detecting most errors.


Disadvantages:

May fail for some types of errors (e.g., if errors cancel out in the sum).




---

Usage Instructions

1. Clone this repository:

git clone https://github.com/Omii1908/Manthan/tree/main/Error-Detection


2. Navigate to the project directory:

cd error-detection


3. Compile the desired C program:

For Parity Bit:

gcc parity_bit.c -o parity_bit

For CRC:

gcc crc.c -o crc

For Checksum:

gcc checksum.c -o checksum



4. Run the program:

./parity_bit

or

./crc

or

./checksum




---

Example Outputs

Parity Bit:

Input:

Enter 7 data bits (0 or 1): 1 0 1 1 0 0 1

Output:

Transmitted data with parity bit: 1 0 1 1 0 0 1 0
Enter received 8 bits: 1 0 1 1 0 0 1 1
Error detected in the received data.

CRC:

Input:

Enter the data bits: 110101
Enter the divisor (polynomial): 1101

Output:

Transmitted data: 110101011
Enter received data: 110101010
Error detected in the received data.

Checksum:

Input:

Enter the number of data blocks: 3
Enter 3 data blocks (16-bit integers): 65280 255 65280

Output:

Calculated Checksum (to be sent): 0xFFFF
Enter received data blocks (including checksum): 65280 255 65280 65535
No error detected in the received data.


---

Requirements

GCC Compiler

A C-compatible system (Linux, Windows, macOS)



---

Author

Created by Om (Omii1908). For suggestions or contributions, feel free to open a pull request or contact me at omi844198@gmail.com.


