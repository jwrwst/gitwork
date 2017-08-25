package com.platform.rp.framework.rest.jersey.client.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 编解码工具类
 * 
 * 
 */
public class CodecUtil {

    /**
     * MD5加密函数
     * 
     * @param key
     *            要加密的字符串
     * @return 加密后所得到的字符串
     * @throws UnsupportedEncodingException
     */
    public static String digestByMD5(String key)
            throws UnsupportedEncodingException {
        return String.valueOf(encodeHex(MD5(key.getBytes("UTF-8"))));
    }

    private static byte[] MD5(final byte[] data) {
        try {
            return MessageDigest.getInstance("MD5").digest(data);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }

    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
