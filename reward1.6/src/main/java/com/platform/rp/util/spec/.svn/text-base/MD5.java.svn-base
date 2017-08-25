package com.platform.rp.util.spec;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author TangJun
 *
 */
public class MD5 {
	public String md5(String s) {
        byte[] defaultBytes = s.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            s = hexString + "";
        } catch (NoSuchAlgorithmException ignored) {
        	
        }
        return hexString.toString();
    }
}