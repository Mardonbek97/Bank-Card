package com.example.bankrest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
public class EncryptionService {
    private SecretKeySpec secretKey;
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public EncryptionService(@Value("${encryption.key}") String key) throws Exception {
        byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16);
        this.secretKey = new SecretKeySpec(keyBytes, "AES");
        this.encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        this.decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        this.encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        this.decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    public String encrypt(String plain) {
        try {
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(plain.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String encrypted) {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(encrypted)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String mask(String encryptedCardNumber) {
        String plain = decrypt(encryptedCardNumber);
        if (plain.length() < 4) return "****";
        String last4 = plain.substring(plain.length() - 4);
        return "**** **** **** " + last4;
    }
}
