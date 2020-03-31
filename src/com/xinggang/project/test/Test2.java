package com.xinggang.project.test;

import com.xinggang.project.dao.InputSeedDao;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

	public static void main(String[] args) {
		yiwei();
	}
	
	public static void yiwei(){
		int i=2;
		System.out.println(i<<2);//位运算 <<向左移两位 >>向右移两位
	}

	// 被踢下线中的循环测试
	public static void danxian() {
		int i = 0;
		while (true) {
			i++;
			System.out.println("第" + i + "个欢迎");
			if (i > 200) {
				break;
			}
		}

		DecimalFormat df = new DecimalFormat("############0.000");
		double x = Double.parseDouble(df.format(48.53 + 368.472));
		int y = (int) (x * 1000);
		double d = (double) y / 1000;
		System.out.println(d);
	}

	// java语言程序设计（基础篇）看到时练习
	public static void JavaTest() {
		char[] x = { 'h', 'e', 'l', 'l', 'o' };
		String message = new String(x);
		// 将字符数组赋值给字符串
		System.out.println(message);
		// 用遍历的方式输出字符数组
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i]);
		}
		System.out.println();
		String one = "Java";// String对象是不可变的，他的内容不可以改变
		one = "Hello Java";// 赋值后第一个String对象任然存在，但是不能访问它，应为变量one指向了新的对象
		System.out.println(one);

		String strone = "Java";
		String strtwo = new String("Java");
		String strthree = strone.intern();
		System.out.println("strone == strtwo is " + (strone == strtwo));// false
		System.out
				.println("strone equals strtwo is " + (strone.equals(strtwo)));// true
		System.out.println("strone == strthree is " + (strone == strthree));// true
		System.out.println(strthree);

		Integer number = 123456;
		String numbers = "123456";
		System.out.println("number equals numbers is "
				+ (number.equals(numbers)));// false

		char han = '中';
		System.out.println(han);

		int d = 5;
		double y = d;// int类型转换为double类型的时候可以自动转换
		double yy = 5;
		int dd = (int) yy;// double类型转换为int类型的时候要进行强转

		System.out.println(y + dd);
	}

	public static void testStr() {
		String str = "中华人民共和国";
		System.out.println("字符创str的长度是：" + str.length());
		System.out.println("判断str字符串的前缀：" + str.startsWith("中华"));// 返回true
		System.out.println("判断str字符串的后缀：" + str.endsWith("国"));
		String strs = "2017-02-03 12:21:15";
		System.out.println(str.substring(strs.indexOf(" "), strs.length()));
	}

	public void testJson() {
		timetest();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
		System.out.println(df.format(new Date()));

		JSONArray array = new JSONArray();
		for (int i = 0; i < 5; i++) {
			JSONObject obj = new JSONObject();
			obj.put("x", "是" + i);
			array.add(obj);
		}
		for (int i = 5; i < 10; i++) {
			JSONObject obj = new JSONObject();
			obj.put("x", "是" + i);
			array.add(obj);
		}

		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}

	public static void timetest() {
		String time = "2017-08-1";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// String day = time.substring(time.length()-2, time.length());

		try {
			// df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));
			// Date now = df.parse(String.valueOf(df.parse(time).getDay()));
			System.out.println(df.format(new Date(df.parse(time).getTime() - 1
					* 24 * 60 * 60 * 1000)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	// 配置您申请的KEY
	public static final String APPKEY = "*************************";

	// 测试第三方的短信发送平台
	public void TestThreeTerrace() {

	}

	public static void FileDemo() {
		// File file = new File("C:\\0.txt");
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		InputSeedDao esd = (InputSeedDao) ac.getBean("inputSeedDao");
		esd.getAppTodayTheOrder(3, "", "2017-10-02 23:59:59");
	}

	// 验证码测试
	public static void RandomYan() {
		// 定义一个字符数组
		String zimu[] = { "a", "A", "b", "9", "B", "4", "c", "C", "d", "D",
				"8", "e", "E", "f", "7", "F", "g", "G", "3", "h", "H", "J",
				"j", "L", "5", "k", "m", "M", "n", "N", "R", "P", "Q", "S",
				"6", "T", "V", "W", "X", "Y", "Z", "2" };
		Random rand = new Random();
		String str = "";
		for (int i = 0; i < 6; i++) {
			int num = rand.nextInt(42);
			if (str.toLowerCase().indexOf(zimu[num].toLowerCase()) != -1) {
				i--;
				continue;
			} else {
				str += zimu[num] + " ";
			}
		}

		/*
		 * Sendmail send = new
		 * Sendmail("2586190195@qq.com","此邮件为XXX物流系统找回密码的唯一凭证，验证码是："
		 * +str+"有效期是5分钟，请妥善保管！"); send.start();
		 */
		System.out.println(str);
		System.out.println(str.toLowerCase());
		System.out.println(str.replace(" ", ""));
	}

	// 文本变形
	public static void TextTransform() {
		// 将字符串里面的大写的字母转换为小写的
		String MaxStr = "Hello Word";
		System.out.println(MaxStr.toLowerCase());
		// 将字符串里面的小写的字母转换为大写的
		String MinStr = "Hellow Word";
		System.out.println(MinStr.toUpperCase());

		// 测试一个字符串中是否包含另一个字符串
		String str1 = "你好吗？";
		String str2 = "我是Jason！你好吗？";
		boolean ok = str2.indexOf(str1) == -1;
		System.out.println("ok的判断结果是：" + ok);// 输出的结果是false，那么证明是包含了

		// 截取字符串中的一定值
		String JieQuStr = "没有土豪爹，就只有堆代码了！";
		String result = JieQuStr.substring(0, 6);// 输出的结果是“没有土豪爹，”
		System.out.println(result);

		// 替换
		String replace = "Hello Word!";
		System.out.println(replace.replace("Word", "Jason"));// 区分大小写，如果将前面大写的W换成小写的w输出的结果就不是这样的了

		int x = 200 >> 100;
		System.out.println(x);
	}

	// java中移位的测试
	public static void test() {
		String str = "123";
		// 逻辑运算符&&和&基本是相等的，当时在这里如果换成了，&就会跑出异常NullPointerException
		// &&具有短路执行，当前面的第一判断条件为false的时候，后面的一个表达式相等于没有去判断，
		// 当两边的条件都为真的时候才会去执行

		if (str != null && !str.equals("")) {
			System.out.println("在这里应该是抛出了异常");
		}
		// 这个则会跑出异常，
		if (!str.equals("") && str != null) {
			System.out.println("在这里应该是抛出了异常");
		}

		// 这个则会跑出异常，&会两边都去做判断，所以也会跑出异常NullPointerException
		if (!str.equals("") & str != null) {
			System.out.println("在这里应该是抛出了异常");
		}
	}

}
