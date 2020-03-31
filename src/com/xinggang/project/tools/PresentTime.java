package com.xinggang.project.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

//获取当前时间的工具类
public class PresentTime {
	// 返回当前时间年-月-日 时：分：秒
	public String getTimes() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前时间的星期
	public String getXingqi() {
		SimpleDateFormat df = new SimpleDateFormat("EEEE");
		String date = df.format(new Date());
		return date;
	}

	// 增加编号中的年月日：年月日时分秒
	public String getDates() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		return date;
	}

	// 增加编号中的年月日：年月日
	public String getDatesNotTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前日期年-月-日
	public String getDatesNianYR() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前日期的年月
	public String getDateNianY() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前月
	public String getDateYue() {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前的年
	public String getDateNian() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String date = df.format(new Date());
		return date;
	}

	// 返回毫秒
	public String getHaoMiao() {
		SimpleDateFormat df = new SimpleDateFormat("SS");
		String date = df.format(new Date());
		return date;
	}

	// 返回当前月-1的时间
	public String getNowJianYi() {

		String dates = new PresentTime().getTimes();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat ydf = new SimpleDateFormat("MM");
		SimpleDateFormat ndf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sfmdf = new SimpleDateFormat("dd HH:mm:ss");
		try {
			int yue = Integer.parseInt(ydf.format(df.parse(dates)));
			int nian = Integer.parseInt(ndf.format(df.parse(dates)));
			if (yue == 1) {
				yue = 12;
				nian = nian - 1;
			} else {
				yue -= 1;
			}
			String yues = "";
			if (yue < 10) {
				yues = "0" + yue;
			} else {
				yues = String.valueOf(yue);
			}
			dates = nian + "-" + yues + "-" + sfmdf.format(df.parse(dates));
		} catch (Exception e) {

		}
		return dates;
	}
	
	// 返回当前月-1的时间
		public String getNowJianSan() {

			String dates = new PresentTime().getTimes();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat ydf = new SimpleDateFormat("MM");
			SimpleDateFormat ndf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sfmdf = new SimpleDateFormat("dd HH:mm:ss");
			try {
				int yue = Integer.parseInt(ydf.format(df.parse(dates)));
				int nian = Integer.parseInt(ndf.format(df.parse(dates)));
				if (yue == 1) {
					yue = 12;
					nian = nian - 1;
				} else {
					yue -= 1;
				}
				String yues = "";
				if (yue < 10) {
					yues = "0" + yue;
				} else {
					yues = String.valueOf(yue);
				}
				dates = nian + "-" + yues + "-" + sfmdf.format(df.parse(dates));
			} catch (Exception e) {

			}
			return dates;
		}
}
