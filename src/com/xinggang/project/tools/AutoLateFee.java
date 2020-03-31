package com.xinggang.project.tools;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//自动去执行滞纳金的工具类
public class AutoLateFee implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent event) {
		PageRow.chakan();

		Timer timer = new Timer(true);
		timer.schedule(new AutoTimer(event.getServletContext()), 0,
				(24 * 60 * 60 * 1000));// 每24小时执行一次滞纳金的工具类

		timer.schedule(new OnlineJudge(event.getServletContext()), 0,
				(60 * 60 * 1000));// 执行判断是否在线

	}
}
