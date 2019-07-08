package com.uni.pa.utility;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

    public String digest(String plainText) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(plainText.getBytes());
        String digestedValue = Base64.encodeBase64String(messageDigest.digest());
        return digestedValue;
    }
}