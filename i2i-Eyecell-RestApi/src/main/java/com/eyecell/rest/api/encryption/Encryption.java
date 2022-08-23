package com.eyecell.rest.api.encryption;

public class Encryption {
    private final int key = 6;

    public String encrypt(String string) {
        char[] chars = string.toCharArray();
        String encryptedString = "";
        for (char c : chars) {
            c += key;
            encryptedString += c;
        }
        return encryptedString;
    }

    public String Decryption(String string) {
        char[] chars = string.toCharArray();
        String decryptedString = "";
        for (char c : chars) {
            c -= key;
            decryptedString += c;
        }
        return decryptedString;
    }
}
