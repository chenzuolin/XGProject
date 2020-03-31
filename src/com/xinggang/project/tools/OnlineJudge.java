package com.xinggang.project.tools;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.service.InteriorUserService;

//判断是否在线
public class OnlineJudge extends TimerTask {

	@SuppressWarnings("unused")
	private ServletContext context = null;

	ApplicationContext ap = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	InteriorUserService interiorUserService = (InteriorUserService) ap
			.getBean("interiorUserService");
	SessionUserListener user = new SessionUserListener();

	public OnlineJudge(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		try {

			@SuppressWarnings("static-access")
			Iterator<String> list = user.getSessionMapKeySetIt();
			List<InteriorUser> iulist = interiorUserService.getAll();
			int size = iulist.size();// 记录开始的值
			while (list.hasNext()) {

				@SuppressWarnings("static-access")
				HttpSession session = user.getSessionMap().get(list.next());
				for (int i = 0; i < iulist.size(); i++) {
					if (iulist.get(i).getIuserLoginName()
							.equals(session.getAttribute("loginName"))) {
						InteriorUser iu = interiorUserService
								.getInteriorUserId(iulist.get(i).getIuserId());//
						iu.setIuserOnline(0);
						interiorUserService.update(iu);
						System.out.println("对应的id是："
								+ iulist.get(i).getIuserId());
						System.out.println("在线的用户是："
								+ iulist.get(i).getIuserLoginName());
						iulist.remove(i);
						i--;
					}
				}
			}
			System.out.println("前面的值是："+size+",后面的值是："+iulist.size());
			if (size != iulist.size()) {
				for (int i = 0; i < iulist.size(); i++) {
					InteriorUser iu = interiorUserService
							.getInteriorUserId(iulist.get(i).getIuserId());//
					iu.setIuserOnline(1);
					iu.setIuserWork(1);
					interiorUserService.update(iu);
				}
			}
		} catch (Exception e) {
			System.out.println("跑出异常");
		}
	}

}
