package com.platform.rp.services.sms.external.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.platform.rp.services.sms.external.entity.SmsResult;

public class Main {
	
	public static void main(String[] args) throws HttpException, IOException {
				
		//String Text=URLEncoder.encode("您的验证码：8859【华信】","utf-8");
		String Text="您的验证码：8859【众赏】";
		
		
		//Sms.secretSend("【众赏科技】验证码为3dsadasd，请在页面中输入以完成验证，有问题请致电1234567890","13161695955");
		//String result  = Sms.secretSend("【众赏科技】验证码为8787，请在页面中输入以完成验证，有问题请致电13161695955","17701156967");
		//SmsResult result  = Sms.secretSend("【众赏科技】验证码为8787，请在页面中输入以完成验证，有问题请致电13161695955","11111111111");
		SmsResult result  = Sms.secretSend("【众赏科技】验证码为8787，请在页面中输入以完成验证，有问题请致电13161695955","11111111111");
	}

}
