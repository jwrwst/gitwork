package com.platform.rp.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public abstract class AttachmentUtils {

	public static String getUplaodPath(HttpServletRequest request, String fileName) {
		File file = new File(request.getContextPath() + File.separator + splice());
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return file.getPath().toString();
	}

	private static String splice() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONDAY) + File.separator + calendar.get(Calendar.DAY_OF_MONTH);
	}
}
