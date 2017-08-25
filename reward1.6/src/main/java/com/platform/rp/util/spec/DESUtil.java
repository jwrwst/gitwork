package com.platform.rp.util.spec;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
         
/** 
  * 3DES加密工具类 
  */ 
public class DESUtil {  
     // 向量  
     private final static String iv = "01234567" ;  
     // 加解密统一使用的编码方式  
     private final static String encoding = "utf-8" ;  
         
     /** 
      * 3DES加密 
      *  
      * @param plainText 普通文本 
      * @return 
      * @throws Exception  
      */ 
     public static String encode(String plainText,String secretKey) throws Exception {  
    	 if(secretKey!=null&&secretKey.length()>24){
    		 secretKey=secretKey.substring(0,24);
    	 }
    	 if(secretKey!=null&&secretKey.length()<24){
    		 StringBuffer buf=new StringBuffer(secretKey);
             buf.append("000000000000000000000000");
             buf.setLength(24);
             secretKey=buf.toString();
    	 }
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
         byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
         return Base64.encode(encryptData);  
     }  
         
     /** 
      * 3DES解密 
      *  
      * @param encryptText 加密文本 
      * @return 
      * @throws Exception 
      */ 
     public static String decode(String encryptText,String secretKey) throws Exception {  
    	 if(secretKey!=null&&secretKey.length()>24){
    		 secretKey=secretKey.substring(0,24);
    	 }
    	 if(secretKey!=null&&secretKey.length()<24){
    		 StringBuffer buf=new StringBuffer(secretKey);
             buf.append("000000000000000000000000");
             buf.setLength(24);
             secretKey=buf.toString();
    	 }
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());   
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
         
         byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));  
         
         return new String(decryptData, encoding);  
     }  
      
    
      
     public static void main(String[] args) throws Exception{
         String plainText = "测试。。。。";
         String password="aaa";
         StringBuffer buf=new StringBuffer(password);
         buf.append("000000000000000000000");
         buf.setLength(24);
         password=buf.toString();
         System.out.println(password);
         System.out.println(password.length());
         String encryptText = DESUtil.encode(plainText,password);
         System.out.println(encryptText);
         System.out.println(DESUtil.decode(encryptText,password));
 
          
     }
}
