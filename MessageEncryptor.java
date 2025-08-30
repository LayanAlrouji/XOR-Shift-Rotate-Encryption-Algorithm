package messageencryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
// Encrypt class:
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
    } // End of inner HashGenerator class.

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
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
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

        // Generate SHA-1 hash of the plaintext:
        String sha1Hash = HashGenerator.generateHash(plaintext);
        System.out.println("SHA-1 hash of plaintext: " + sha1Hash);

        // Decrypt the message:
        String decrypted = decrypt(encrypted, key, 2);
        System.out.println("Decrypted message: " + decrypted);
    }
}
