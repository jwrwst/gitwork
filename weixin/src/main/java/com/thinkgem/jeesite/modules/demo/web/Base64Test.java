package com.thinkgem.jeesite.modules.demo.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.tree.BackedList;

import com.sun.mail.handlers.image_gif;
import com.thoughtworks.xstream.core.util.Base64Encoder;


public class Base64Test{
	
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    Base64Encoder encoder = new Base64Encoder();
	    return encoder.encode(data);
	}
	
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		Base64Encoder decoder = new Base64Encoder();
		try {
			// 解密
			byte[] b = decoder.decode(imgStr);
			for(int i = 0;i <b.length;i++ ){
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(getImageStr("d:\\1.png"));
		
		Set<String> set = new HashSet<String>();
		int pinpang = 9; //九个乒乓球
		int a = 0;//杯子A
		int b = 0;//杯子B
		int c = 0;//杯子C
		for(;a<=9;a++){
			for(int num = pinpang-a;num>=0;num--){
				b = num;
				c = pinpang-a-b;
				int[] intArray = {a,b,c};
				Arrays.sort(intArray);
				String str = "";
				for(int i : intArray){
					str+=i;
				}
				set.add(str);
			}
		}
		System.out.println(set.size()+"种");
//		for(String str:set){
//			System.out.println(str);
//		}
		
		//获取26个字母
		List<String> list = getENList();
		//计数器
		int number = 1;
		for(int i = 0;list.size()!=1;){
			if(list.size()==i){
				i = 0;
			}else{
				if(number == 5){
					System.out.println("字母:"+list.get(i)+"报数"+number+"出局");
					list.remove(i);
					number = 1;
					if(list.size()==i){
						i = 0;
					}
				}
				System.out.println("字母:"+list.get(i)+"报数"+number);
				i++;
				number++;
			}
		}
	}
	
	
	public static List<String> getENList(){
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		list.add("I");
		list.add("J");
		list.add("K");
		list.add("L");
		list.add("M");
		list.add("N");
		list.add("O");
		list.add("P");
		list.add("Q");
		list.add("I");
		list.add("S");
		list.add("T");
		list.add("U");
		list.add("V");
		list.add("W");
		list.add("X");
		list.add("Y");
		list.add("Z");
		return list;
	}
	
	
	
}