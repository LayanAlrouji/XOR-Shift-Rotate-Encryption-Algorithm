# XOR-Shift-Rotate-Encryption-Algorithm
---

## 📘 Project Overview
implement new Encryption Algorithm bit oriented and apply integrity using hash function, apply the method using JAVA Language.

---

## 💡 Phase 1: Create the algorithm and explain (encryption-decryption) using block diagram.

We create new algorithm called ( XORShiftRotate ) which combines three algorithms shift, XOR, and rotation.  it’s provide a fundamental yet efficient way to encrypt and decrypt data.  These methods are not only easy to implement and understand but they also provide more secure and protect data from risks compared to other algorithms.

Block diagram:



<img width="468" height="201" alt="image" src="https://github.com/user-attachments/assets/c9c4a6a3-9925-4ead-80e2-5f54bde8ee15" />



Flow chart:



<img width="425" height="405" alt="image" src="https://github.com/user-attachments/assets/ba24b4fc-825e-4059-bd6a-9024290725c3" />


---

## 🔑 Phase 2: Mention whether will you use the keys or not in your algorithm, and If you will use keys, explain how to generate the keys, and then display the generated keys.

Yes, 
The user enters the key as a string directly. 
This enables users to select their preferred key.

Code:


        // Convert the key character to binary
         char keyChar = key.charAt(i % key.length());
         String keyBit = String.format("%8s", Integer.toBinaryString(keyChar)).replace(' ', '0');
         char xoredBit = (binaryMessage.charAt(i) == keyBit.charAt(i % 8)) ? '0' : '1';

         
     // ---------------------- Main method ------------------------

        // Input the key:
        System.out.print("Enter a STRING key: ");
        String key = scanner.nextLine();


         
---

## 🖥️ Phase 3: Display the plain text, Encrypt your name, Display the cipher text.


---

## ⛓️ Phase 4: Apply hash function to achieve integrity.

---

## 🔓 Phase 5: Decrypt the cipher text to get the plaintext.

