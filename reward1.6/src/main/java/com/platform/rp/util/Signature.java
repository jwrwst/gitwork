package com.platform.rp.util;



import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * 签名工具
 * @author Devin Tan
 *
 */
public class Signature {

    private static final String ENCRYPTION_ALGORITHM = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    
    /**生成密钥
     * @param encryptionAlgorithm
     * @return
     * @throws Exception
     */
    public static String generateKey(String encryptionAlgorithm) throws Exception { 
	  KeyGenerator keyGenerator =KeyGenerator.getInstance(encryptionAlgorithm);
	  byte[] generateKey = keyGenerator.generateKey().getEncoded();
	  return DatatypeConverter.printHexBinary(generateKey);
      }
      
     public static void main(String[] args) {
	try {
//	    String initMacKey = generateKey(ENCRYPTION_ALGORITHM);
//	    System.out.println(initMacKey);
	    String initMacKey = "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB";
	    String content = "{\"reqssn\":\"1245\"}";
	    System.out.println(hmacSHA1Encrypt(content, initMacKey));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     * 
     * @param encryptText
     *            被签名的字符�?     * @param encryptKey
     *            密钥
     * @return 十六进制字符�?     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws UnsupportedEncodingException 
     * @throws Exception
     */
    public static String hmacSHA1Encrypt(String encryptText, String encryptKey)
	    throws NoSuchAlgorithmException, InvalidKeyException,
	    UnsupportedEncodingException {
	byte[] encryptKeyBytes = DatatypeConverter.parseHexBinary(encryptKey);
	SecretKey secretKey = new SecretKeySpec(encryptKeyBytes, ENCRYPTION_ALGORITHM);
	Mac mac = Mac.getInstance(ENCRYPTION_ALGORITHM);
	mac.init(secretKey);
	byte[] text = encryptText.getBytes(ENCODING);
	return DatatypeConverter.printHexBinary(mac.doFinal(text));
    }
}
