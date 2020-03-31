package com.xinggang.project.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计工作量类,统计的是装卸工
 * 
 * @author Administrator
 * 
 */
public class StatisticsWorking {
	public int countInnerStr(String str, String patternStr) {
		int count = 0;

		final Pattern r = Pattern.compile(patternStr);

		final Matcher m = r.matcher(str);

		while (m.find()) {
			count++;
		}

		return count;
	}
}
