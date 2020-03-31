package com.xinggang.project.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.InteriorUser;

public class SessionUserListener implements HttpSessionListener {

	// key为sessionId，value为HttpSession，使用static，定义静态变量，使之程序运行时，一直存在内存中。
	private static java.util.Map<String, HttpSession> sessionMap = new java.util.concurrent.ConcurrentHashMap<String, HttpSession>(
			1);

	/**
	 * HttpSessionListener中的方法，在创建session
	 */
	public void sessionCreated(HttpSessionEvent event) {

	}

	/**
	 * HttpSessionListener中的方法，回收session时,删除sessionMap中对应的session
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		getSessionMap().remove(event.getSession().getId());
		removeSession(event.getSession().getId());
	}

	/**
	 * 得到在线用户会话集合
	 */
	public static List<HttpSession> getUserSessions() {
		List<HttpSession> list = new ArrayList<HttpSession>();
		Iterator<String> iterator = getSessionMapKeySetIt();
		int x=0;
		while (iterator.hasNext()) {
			x++;
			String key = iterator.next();
			HttpSession session = getSessionMap().get(key);
			list.add(session);
			if(x>400){
				break;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			System.out
					.println("得到session的集合得到session的集合得到session的集合得到session的集合得到session的集合得到session的集合得到session的集合"
							+ list.get(i));
		}

		return list;

	}

	/**
	 * 得到用户对应会话map，key为用户ID,value为会话ID
	 */
	public static Map<String, String> getUserSessionMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> iter = getSessionMapKeySetIt();
		int i=0;
		while (iter.hasNext()) {
			i++;
			String sessionId = iter.next();
			System.out
					.println("得到回话id得到回话id得到回话id得到回话id得到回话id得到回话id得到回话id得到回话id得到回话idsession:"
							+ sessionId);
			HttpSession session = getSessionMap().get(sessionId);
			InteriorUser user = (InteriorUser) session.getAttribute("iulist");
			Client client = (Client) session.getAttribute("client");

			if (user != null) {
				map.put(user.getIuserLoginName(), sessionId);
			}

			if (client != null) {
				map.put(client.getClientLoginName(), sessionId);
			}
			
			if(i>300){
				break;
			}
		}
		return map;
	}

	/**
	 * 移除用户Session
	 */
	public synchronized static void removeUserSession(String userId) {
		Map<String, String> userSessionMap = getUserSessionMap();

		if (userSessionMap.containsKey(userId)) {
			System.out
					.println("移除用户session移除用户session移除用户session移除用户session移除用户sessionidsession:"
							+ userId);
			String sessionId = userSessionMap.get(userId);
			getSessionMap().get(sessionId).invalidate();
			getSessionMap().remove(sessionId);
		}
	}

	/**
	 * 增加用户到session集合中
	 */
	public static void addUserSession(HttpSession session) {
		getSessionMap().put(session.getId(), session);
	}

	/**
	 * 移除一个session
	 */
	public static void removeSession(String sessionID) {
		getSessionMap().remove(sessionID);
	}

	public static boolean containsKey(String key) {
		return getSessionMap().containsKey(key);
	}

	/**
	 * 判断该用户是否已重复登录，使用 同步方法，只允许一个线程进入，才好验证是否重复登录
	 * 
	 * @param user
	 * @return
	 */

	/* 判断内部人员 */
	public static String checkIfHasLogin(InteriorUser user) {
		Iterator<String> iter = getSessionMapKeySetIt();
		System.out.println("内部人员的判断"+iter.hasNext());
		int i=0;
		while (iter.hasNext()) {
			i++;
			System.out.println(i);
			String sessionId = iter.next();
			System.out.println("内部人员的判断"+sessionId);
			HttpSession session = getSessionMap().get(sessionId);
			InteriorUser sessionuser = (InteriorUser) session
					.getAttribute("iulist");
			if (sessionuser != null) {
				System.out.println("内部人员的判断"+sessionuser.getIuserLoginName());
				if (sessionuser.getIuserLoginName().equals(
						user.getIuserLoginName())) {
					session.removeAttribute("iulist");
					session.removeAttribute("loginName");
					session.setAttribute("err", "该账号在其他地方登陆！");
					return "该账号在其他地方登陆！";
				}
			}
				
			
			System.out
					.println("内部用户判断内部用户判断内部用户判断内部用户判断内部用户判断内部用户判断内部用户判断sessionidsession:"
							+ user.getIuserLoginName());
			
			if(i>200){
				return "";
			}
		}

		return "";
	}

	// 判断客户是否已经登录
	public static String checkIfHasLogin(Client client) {
		Iterator<String> iter = getSessionMapKeySetIt();
		int i=0; 
		while (iter.hasNext()) {
			i++;
			String sessionId = iter.next();
			HttpSession session = getSessionMap().get(sessionId);
			Client sessionuser = (Client) session.getAttribute("client");
			if (sessionuser != null) {
				if (sessionuser.getClientLoginName().equals(
						client.getClientLoginName())) {
					session.removeAttribute("client");
					session.removeAttribute("loginName");
					session.setAttribute("err", "该账号在其他地方登陆！");
					return "该账号在其他地方登陆";
				}
			}
			
			if(i>200){
				return "";
			}
		}

		System.out.println("客户判断客户判断客户判断客户判断客户判断客户判断客户判断客户判断客户判断idsession:"
				+ client.getClientLoginName());
		return "";
	}

	/**
	 * 获取在线的sessionMap
	 */
	public static Map<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	/**
	 * 获取在线sessionMap中的SessionId
	 */
	public static Iterator<String> getSessionMapKeySetIt() {
		return getSessionMap().keySet().iterator();
	}
}
