package com.xinggang.project.tools;

import java.util.UUID;

public class MyTools {
	public static String getNewFileName(String str) {
		int beginIndex = str.lastIndexOf(".");
		String newStr = UUID.randomUUID().toString()
				+ str.substring(beginIndex, str.length());
		return newStr;
	}
}
