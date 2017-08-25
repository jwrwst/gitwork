package com.thinkgem.jeesite.common.util;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	/**
	 * 中文转拼音 不带声调
	 * @date:2014年12月22日
	 * @user:Zhang Jingtao
	 * @return_type:List<String>
	 */
	public static List<String> getPinYin(String hanyu,Boolean upper) {
		if (hanyu==null||hanyu.length()==0) {
			return new ArrayList<String>();
		}
		List<String> chinesses = new ArrayList<String>();
		String[] cc = hanyu.split("");
		for (String s : cc) {
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
			char[] ch = s.trim().toCharArray();
			StringBuffer buffer = new StringBuffer("");
			try {
				for (int i = 0; i < ch.length; i++) {
					// unicode，bytes应该也可以.
					if (Character.toString(ch[i]).matches("[\u4e00-\u9fa5]+")) {
						String[] temp = PinyinHelper.toHanyuPinyinStringArray(
								ch[i], format);
						buffer.append(temp[0]);// :结果"?"已经查出，但是音调是3声时不显示myeclipse8.6和eclipse
						buffer.append("");
					} else {
						buffer.append(Character.toString(ch[i]));
					}
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			chinesses.add(upper? buffer.toString().toUpperCase():buffer.toString());
		}
		return chinesses;
	}
	
	public static void main(String[] args) {
		List<String> result = new ArrayList<String>();
		result = getPinYin("榊原",true);
		for (String s : result) {
			System.out.println(s);
		}
	
	}
}
