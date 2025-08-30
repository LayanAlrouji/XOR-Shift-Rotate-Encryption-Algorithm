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





Output example:


<img width="726" height="292" alt="image" src="https://github.com/user-attachments/assets/a77a2258-3846-49ba-9c71-fcf258bb2ee5" />



---

## 🖥️ Phase 3: Display the plain text, Encrypt your name, Display the cipher text.

Code: 


public class MessageEncryptor {
   
    
    // 1- Convert PlainText to binary:
    public static String toBinary(String plainText) {
        StringBuilder binaryMessage = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            for (int i = 7; i >= 0; i--) {
                binaryMessage.append((c >> i) & 1); // Append each bit to the binary string
            }
        }
        return binaryMessage.toString();
    }


    // 2- Shifting:
    public static String shifting(String binaryMessage, int shiftValue) {
        return binaryMessage.substring(shiftValue) + binaryMessage.substring(0, shiftValue);
    }

    
    // 3- XOR:
    public static String xoring(String binaryMessage, String key) {
        StringBuilder xored = new StringBuilder();
        for (int i = 0; i < binaryMessage.length(); i++) {
            // Convert the key character to binary
            char keyChar = key.charAt(i % key.length());
            String keyBit = String.format("%8s", Integer.toBinaryString(keyChar)).replace(' ', '0');
            char xoredBit = (binaryMessage.charAt(i) == keyBit.charAt(i % 8)) ? '0' : '1';
            xored.append(xoredBit);
        }
        return xored.toString();
    }


    // 4- Rotate:
    public static String rotateing(String binaryMessage, int placement) {
        int messageLength = binaryMessage.length();
        placement = placement % messageLength;
        return binaryMessage.substring(messageLength - placement) + binaryMessage.substring(0, messageLength - placement);
    }



// ---------------------- Main method ------------------------

   // Input the plaintext message:

   
        System.out.print("Enter a message: ");
        String plaintext = scanner.nextLine();

        
   // Input the key:

   
        System.out.print("Enter a STRING key: ");
        String key = scanner.nextLine();



   // Convert plaintext to binary:

   
        String binaryMessage = toBinary(plaintext);
        System.out.println("Binary message: " + binaryMessage);



   // Apply the encryption process (Shift -> XOR -> Rotate):

   
        String shifted = shifting(binaryMessage, 2);
        System.out.println("Shifted message: " + shifted);

        String xored = xoring(shifted, key);
        System.out.println("XORED message: " + xored);

        String encrypted = rotateing(xored, 2);
        System.out.println("The encrypted message: " + encrypted);






Output example:


<img width="1106" height="306" alt="image" src="https://github.com/user-attachments/assets/45d3306a-ce50-4fc5-89ae-1e2a60e58f38" />





---

## ⛓️ Phase 4: Apply hash function to achieve integrity.

Code:


// Inner HashGenerator class:


    public static class HashGenerator {
        // Generate hash of a given message:
        public static String generateHash(String message) {
            try {
                MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
                byte[] hashBytes = sha1.digest(message.getBytes());
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashBytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-1 algorithm not found!", e);
            }
        }
    } 




// ---------------------- Main method ------------------------

  // Generate SHA-1 hash of the plaintext:

  
        String sha1Hash = HashGenerator.generateHash(plaintext);
        System.out.println("SHA-1 hash of plaintext: " + sha1Hash);







Output example:


<img width="1106" height="44" alt="image" src="https://github.com/user-attachments/assets/1bc4011b-5a9c-46a8-9758-3b8ddee1cb17" />






---

## 🔓 Phase 5: Decrypt the cipher text to get the plaintext.

Code:


// Decrypt method:

    
    public static String decrypt(String encryptedMessage, String key, int shiftValue) {
        String rotatedBack = rotateing(encryptedMessage, encryptedMessage.length() - shiftValue);
        String xoredBack = xoring(rotatedBack, key);
        String shiftedBack = shifting(xoredBack, xoredBack.length() - shiftValue);
        return fromBinary(shiftedBack);
    }
    
    
    // Convert Decrypted message from binary to string:
    
    
    public static String fromBinary(String binaryMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < binaryMessage.length(); i += 8) {
            if (i + 8 > binaryMessage.length()) {
                break; // Prevent IndexOutOfBoundsException
            }
            String byteString = binaryMessage.substring(i, i + 8);
            decryptedMessage.append((char) Integer.parseInt(byteString, 2));
        }
        return decryptedMessage.toString();
    }



         
// ---------------------- Main method ------------------------

  // Decrypt the message:

  
        String decrypted = decrypt(encrypted, key, 2);
        System.out.println("Decrypted message: " + decrypted);








Output example:


<img width="1106" height="46" alt="image" src="https://github.com/user-attachments/assets/2628f2c3-1fbb-4deb-b88c-d712bf4d8b9e" />








---

## 🖥️ Full Output example: 


<img width="468" height="142" alt="image" src="https://github.com/user-attachments/assets/b84860cc-b3fc-4a0a-829d-fb905bc2983e" />


