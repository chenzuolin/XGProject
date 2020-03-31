package com.xinggang.project.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	private String email;// 发送的邮件地址
	private String content;// 发送的验证码，内容

	public Sendmail(String email, String content) {
		this.email = email;
		this.content = content;
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
			Message message = createEmail(session, email, content);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
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
	public Message createEmail(Session session, String email, String content)
			throws Exception {

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				email));
		message.setSubject("鑫港物流仓储管理系统");

		String info = content;
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}

}