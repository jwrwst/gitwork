package com.platform.rp.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author tangjun 创建日期：2014年12月3日
 */
public class NumberUtil {

    public static String formatNum(int num, int len) {
        return String.format("%0" + len + "d", num);
    }

    /**
     * 将String类型转换成int型。<BR>
     * null、空文字列("")的时候，变为0。
     * 
     * @param _text
     *            输入的文字列
     * @return
     */
    public static int StringToInt(String _text) {

        if (StringUtils.isEmpty(_text)) {
            return 0;
        } else {
            try {
                return Integer.valueOf(_text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

}
