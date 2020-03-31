package com.xinggang.project.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanJieFilter implements Filter {

	public void destroy() {
		// 销毁
		System.out.println("filter销毁！");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		// 监听
		// 进行强转
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String url = request.getRequestURI() + "?" + request.getQueryString();
		// 每一次进来保存一下操作的时间
		if (url.indexOf("loginAll.do?flag=goLoginAll&ff=ajax") == -1
				&& url.indexOf("tidingsAction.do?flag=CountOrderForm") == -1) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			session.setAttribute("nowDate", df.format(new Date()));
		}
		// 如果操作的用户已经登录的时候放行，如果没有登录则返回到登录的页面\powers.do?flag=downApp
		if (session.getAttribute("loginName") != null
				|| request.getRequestURI().indexOf("loginAll.do") != -1
				|| request.getRequestURI().indexOf("CreateCode") != -1
				|| url.indexOf("powers.do?flag=downApp") != -1
				|| url.indexOf("tidingsAction.do?flag=TongJiXGKHSFC") != -1
				|| url.indexOf("tidingsAction.do?flag=TongJiXGLSKC") != -1
				|| url.indexOf("shiftStock.do?flag=QueryZhuanChu") != -1
				|| url.indexOf("shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu") != -1
				|| url.indexOf("inputSeed.do?flag=getRuKuLiShi") != -1
				|| url.indexOf("exportSeed.do?flag=getChuKuLiShi") != -1
				|| url.indexOf("tidingsAction.do?flag=TongJiXGKHKC") != -1
				|| url.indexOf("tarehouse.do?flag=selectAjaxKuwei") != -1
				|| url.indexOf("shift.do?flag=ShiftgetAll") != -1
				|| url.indexOf("tarehouseGoods.do?flag=selectHouseGoodsAjax") != -1
				|| url.indexOf("client.do?flag=selectClient") != -1
				|| url.indexOf("loginLog.do?flag=getAllLLog") != -1
				|| url.indexOf("interiorUserDuty.do?flag=selectInteriorUserDuty") != -1
				|| url.indexOf("functions.do?flag=goSelectFunctions") != -1
				|| url.indexOf("powers.do?flag=selectPowersAll") != -1
				|| url.indexOf("exportOperate.do?flag=getGuoBang") != -1) {
			chain.doFilter(request, response);
			return;
		} else {
			String path = request.getContextPath();
			String basePath = req.getScheme() + "://" + req.getServerName()
					+ ":" + req.getServerPort() + path;
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setHeader("Prama", "no-cache");
			response.sendRedirect(basePath
					+ "/loginAll.do?flag=goLoginAll&ff=a");
			return;
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 初始化
		System.out.println("filter开始初始化！");

	}

}
