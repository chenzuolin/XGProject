package com.xinggang.project.tools;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.service.AccountsService;

//每天去执行滞纳金的工具类
public class AutoTimer extends TimerTask {

	@SuppressWarnings("unused")
	private ServletContext context = null;
	private static int x = 0;

	public AutoTimer(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		try {

			if (x != 0) {
				ApplicationContext ap = new ClassPathXmlApplicationContext(
						"applicationContext.xml");
				AccountsService accountsService = (AccountsService) ap
						.getBean("accountsService");
				accountsService.saveZidong();// 自动向滞纳金表中添加没有交费的订单，

				PageRow pr = new PageRow();
				pr.qingling();// 24小时清零
				pr.chukuqingling();//出库编号清零
				pr.piciqingling();//批次编号清零
				pr.guohuqingling();// 过户编号清零
				pr.rizhiqingling();// 日志编号清零
				pr.zhinajinqingling();// 滞纳金编号清零
				pr.rukuzongqingkong();// 入库总订单清0
				pr.nuokuqingling();// 挪库编号清零
				pr.rukuzongqingkong();// 入库操作编号清空
				pr.pankuqingling();// 将盘库编号清零
				pr.kehukucunqingling();// 将客户货物库存编号24小时清零
				pr.qingKongRuCaoN();// 将操作表中调用第二次num清空
				pr.JinRiGangJiaQingLing();// 将今日钢价清零
				pr.duandaoqingling();//短倒编号清零
			}
			x = 1;
		} catch (Exception e) {
		}
	}

}
