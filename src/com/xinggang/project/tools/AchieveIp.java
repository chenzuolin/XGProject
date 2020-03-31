package com.xinggang.project.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得ip地址工具类
 * 
 * @author Administrator
 * 
 */
public class AchieveIp {

	// 获得用户的ip地址，在action中传入request
	public String getIpAddress(HttpServletRequest request) {

		// 获取ip地址
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println(ip);
		// 因为有些有些登录是通过代理，所以取第一个（第一个为真是ip）
		int index = ip.indexOf(',');
		if (index != -1) {
			ip = ip.substring(0, index);
		}
		return ip;
	}
}
