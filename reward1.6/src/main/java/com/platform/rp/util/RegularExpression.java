package com.platform.rp.util;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler; 
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class RegularExpression 
{
	static PatternCompiler compiler = new Perl5Compiler();
	static PatternMatcher matcher = new Perl5Matcher();
	
	//设定数字字符串匹配模式
	static String p_num = "\\d+";
	//设定整数字符串匹配模式
	static String p_zs = "^-?\\d+$";
	//设定英文字母字符串匹配模式
	static String p_eng = "^[A-Za-z]+$";	
	//设定数字和英文字母字符串匹配模式
	static String p_numeng = "^[A-Za-z0-9]+$";
	//设定数字、英文字母或下划线字符串匹配模式
	static String p_ne = "^\\w+$";
	//设定中文字符串匹配模式
	static String p_chinese = "[\u4e00-\u9fa5]";
	//设定邮件地址字符串匹配模式
	static String p_email = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	//设定IP地址字符串匹配模式	
	static String p_ipaddr = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.]"
		                   + "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.]"
			               + "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.]"
			               + "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
	//设定url地址字符串匹配模式
	static String p_url = "[a-zA-z]+://[^\\s]*";
	/*
	 * 设定国内电话号码字符串匹配模式
	 * 
	 * 正则表达式为：0\\d{2}-\\d{8}|0\\d{3}-\\d{7,8}
	 * 匹配格式为：010-12345678 , 0431-5195066 , 0431-85195066 
	 * 
	 * 正则表达式为：0\\d{2}-\\d{8}|0\\d{3}-\\d{7}
	 * 匹配格式为：010-12345678 , 0431-5195066
	 */
	static String p_phnoe = "0\\d{2}-\\d{8}|0\\d{3}-\\d{7,8}"; 
	//设定腾讯QQ号码字符串匹配模式
	static String p_qq = "[1-9][0-9]{4,}"; 
	//设定中国邮政编码字符串匹配模式
	static String p_zip = "[1-9]\\d{5}(?!\\d)"; 
	//设定身份证号码字符串匹配模式
	static String p_idcard = "(\\d{8}(01|02|03|04|05|06|07|08|09|10|11|12)" 
		                   + "(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24" 
		                   + "|25|26|27|28|29|30|31)\\d{3}|\\d{6}(19|20)\\d{2}(01|02|03|04|05|06|07|08|" 
		                   + "09|10|11|12)(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|" 
		                   + "21|22|23|24|25|26|27|28|29|30|31)\\d{3}(\\d|X))";
	/* 
	 * 设定日期字符串匹配模式
	 * 
	 * 正则表达式为：^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$
	 * 匹配格式为：1978-10-20 ， 1978-2-3
	 *
	 * 正则表达式为：^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$
	 * 匹配格式为：1978-10-20 12:12:12
	 */
	static String p_date = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|" 
		                 + "(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|" 
		                 + "(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)" 
		                 + "(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
	//设定非法字符匹配模式
	//本例已屏蔽非法字符包括[~][!][#][$][%][^][&][*][+][=][|][{][}][[][]]["]['][<][>][?][/]
	//static String p_fc = "^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$";这个字符串存在问题
	//static String p_fc = "~|!|%|&|\'|!|\"|<|>";基本字符屏蔽
	static String p_fc = "~|!|\\#|\\$|%|\\^|&|\\*|\\+|=|\\||{|}|\\[|\\]|\"|'|<|>|\\?|\\/";  //扩展字符屏蔽
	
	
	
	
	/**
	 * @see 校验是否为数字
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkNumber(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_num);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为整数
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkZS(String instr)
	{
		Pattern pattern = null;
		try
		{
			pattern = compiler.compile(p_zs);
			return matcher.matches(instr, pattern);
		} 
		catch(MalformedPatternException  e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为英文字母
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkEnglish(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_eng);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为数字或英文字母
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkNumAndEng(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_numeng);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为数字、英文字母或下划线
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkNum_Eng(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_ne);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @see 校验是否为中文字符
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkChinese(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_chinese);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为电子邮件地址格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkEmail(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_email);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为IP地址格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkIpaddr(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_ipaddr);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为url地址格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkUrl(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_url);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为电话号码格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkPhone(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_phnoe);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为QQ号码格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkQQ(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_qq);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为邮政编码格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkZIP(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_zip);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为身份证号码格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkIdCard(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_idcard);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为日期格式
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkDate(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_date);
			return matcher.matches(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @see 校验是否为非法字符
	 * @param instr 需要进行校验的字符串
	 */
	public static boolean checkForbiddenChar(String instr)
	{
		Pattern pattern = null;
		try 
		{
			pattern = compiler.compile(p_fc);
			return !matcher.contains(instr, pattern);
		} 
		catch (MalformedPatternException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] p)
	{
		try
		{
			System.out.println(RegularExpression.checkIpaddr("1921680250"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
