package com.xinggang.project.test;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.service.InteriorUserService;

/**
 * @ClassName: Sendmail
 * @Description: Sendmail类继承Thread，因此Sendmail就是一个线程类，这个线程类用于给指定的用户发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午10:43:48
 * 
 */
public class Sendmail extends Thread {
	// 用于给用户发送邮件的邮箱
	private String from = "fhj19930713@sohu.com";
	// 邮箱的用户名
	private String username = "fhj19930713";
	// 邮箱的密码
	private String password = "19930713fhj";
	// 发送邮件的服务器地址
	private String host = "smtp.sohu.com";
	
	private InteriorUser user;
	public Sendmail(InteriorUser user) {
		this.user = user;
	}

	/*
	 * 重写run方法的实现，在run方法中发送邮件给指定的用户
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session,user);
			ts.sendMessage(message, message.getAllRecipients());
			fanhui("success");
			ts.close();
		} catch (Exception e) {
			fanhui("error");
		}
	}
	
	public boolean fanhui(String str){
		System.out.println(str);
		if(str.equals("success")){
			System.out.println("成功");
			return true;
		}else{
			System.out.println("失败！");
			return false;
		}
	}
	/**
	 * @Method: createEmail
	 * @Description: 创建要发送的邮件
	 * @Anthor:孤傲苍狼
	 * 
	 * @param session
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Message createEmail(Session session, InteriorUser user) throws Exception {

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(user.getIuserDefinedOne()));
		message.setSubject("鑫港物流仓储管理系统");

		String info = "http://xg56.cn:85";
		message.setContent(info, "text/html;charset=UTF-8");
	    message.saveChanges();
		return message;
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		UUID uu = UUID.randomUUID();
		System.out.println("uu是："+uu);
		InteriorUserService esd = (InteriorUserService) ac.getBean("interiorUserService");
		InteriorUser user = esd.getInteriorUserId(5);
		Sendmail send = new Sendmail(user);
		
		// 启动线程，线程启动之后就会执行run方法来发送邮件
		send.start();
	}
}