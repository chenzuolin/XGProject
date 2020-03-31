package com.xinggang.project.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Functions;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Powers;
import com.xinggang.project.form.LoginAllForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.FunctionsService;
import com.xinggang.project.service.InteriorUserService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.service.PowersService;
import com.xinggang.project.tools.SendTextMessage;
import com.xinggang.project.tools.SessionUserListener;

/**
 * 登录action
 * 
 * @author Administrator
 * 
 */

public class LoginAllAction extends DispatchAction {

	// 客户service
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	// 功能表service
	FunctionsService functionsService = null;

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	// 权限service
	PowersService powersService = null;

	public void setPowersService(PowersService powersService) {
		this.powersService = powersService;
	}

	// 内部人员service
	private InteriorUserService interiorUserService;

	public void setInteriorUserService(InteriorUserService interiorUserService) {
		this.interiorUserService = interiorUserService;
	}

	// 登录日志表service
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	// 登录
	public ActionForward goLoginAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JSONArray array = new JSONArray();
		if (request.getParameter("ff").toString().equals("ajax")) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

			if (request.getSession().getAttribute("nowDate") != null) {
				if (df.parse(df.format(new Date())).getTime()
						- df.parse(
								request.getSession().getAttribute("nowDate")
										.toString()).getTime() >= 30 * 60 * 1000) {
					if (request.getSession().getAttribute("iulist") != null) {
						InteriorUser iuId = (InteriorUser) request.getSession()
								.getAttribute("iulist");
						InteriorUser iu = this.interiorUserService
								.getInteriorUserId(iuId.getIuserId());
						iu.setIuserOnline(1);
						iu.setIuserWork(1);
						this.interiorUserService.update(iu);

					}
					request.getSession().invalidate();
				}
			}
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();

			if (request.getSession().getAttribute("loginName") == null) {
				obj.put("result", "n");
				obj.put("duoci", request.getSession().getAttribute("err"));
				array.add(obj);

				// window.open('cangchu/page/login.jsp')

			} else {
				obj.put("result", "papa");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		if (request.getParameter("ff").toString().equals("zhuxiao")) {
			if (request.getSession().getAttribute("iulist") != null) {
				InteriorUser iuId = (InteriorUser) request.getSession()
						.getAttribute("iulist");
				InteriorUser iu = this.interiorUserService
						.getInteriorUserId(iuId.getIuserId());
				// iu.setIuserPassword(iu.getIuserPassword());
				System.out.println("password is " + iu.getIuserPassword());
				iu.setIuserOnline(1);
				iu.setIuserWork(1);
				this.interiorUserService.update(iu);

			}
			request.getSession().invalidate();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			if (request.getSession().getAttribute("loginName") == null) {
				obj.put("result", "n");
				array.add(obj);
				// window.open('cangchu/page/login.jsp')

			} else {
				obj.put("result", "papa");
				array.add(obj);
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}

		return null;
	}

	// 客户注销
	public ActionForward kehuZhuxiao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("goLoginAll");
	}

	// 客户登录
	public ActionForward loginAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LoginAllForm loginAllForm = (LoginAllForm) form;
		if (loginAllForm == null || loginAllForm.getLoginName() == null
				|| loginAllForm.getLoginName().toString().equals("")) {

			return mapping.findForward("goLoginAll");
		}

		String loginName = loginAllForm.getLoginName();// 得到登录名
		String loginPwd = loginAllForm.getLoginPwd();// 得到密码
		String code = loginAllForm.getCode();// 得到用户输入验证码
		String checkCode = (String) request.getSession().getAttribute(
				"checkcode");// 获取自动产生的验证码
		request.getSession().setAttribute("err", "");

		// 判断密码和用户名是否为空
		if (loginName.equals("") || loginPwd.equals("")) {
			request.setAttribute("err", "用户名或密码不能为空！");
			return mapping.findForward("goLoginAll");
		}

		// 验证验证码是否一致
		if (!code.equals(checkCode)) {
			request.setAttribute("err", "验证码错误!");
			return mapping.findForward("goLoginAll");
		}
		System.out.println("登录进来，验证已经合法");
		// 如果是客户登录
		List<Client> listClient = clientService.getClientLogin(loginName,
				loginPwd);
		// 当客户的账号户密码不匹配上时进入，判断是否是内部人员
		if (listClient.size() <= 0) {

			// 如果是内部人员
			List<InteriorUser> listIulist = interiorUserService.getLogin(
					loginName, loginPwd);
			if (listIulist.size() > 0) {

				System.out.println(listIulist.size() + "登录返回的结果大小");

				// 判断该内部人员的账号时候已经登录
				SessionUserListener.checkIfHasLogin(listIulist.get(0));
				System.out.println("判断内部人员的账号是重复否登录");
				// 创建存放功能集合
				List<Functions> listFunctions = new ArrayList<Functions>();
				InteriorUser iulist = listIulist.get(0);
				// 修改内部人员在线状态为在线
				this.interiorUserService.updateIuserOnline(iulist.getIuserId(),
						0);
				System.out.println("修改内部人员的在线状态");
				// 到登录日志表中添加数据,并得到登录日志编号
				String loginId = loginLogService.saveLogin(loginName, request,
						"内部登录");
				System.out.println("保存到登录日志中，登录日志的编号是" + loginId);
				// 保存登录日志编号
				request.getSession().setAttribute("loginId", loginId);
				// 保存登录名
				request.getSession().setAttribute("loginName", loginName);
				// 保存内部人员
				request.getSession().setAttribute("iulist", iulist);
				// 保存内部人员id
				request.getSession().setAttribute("iulistId",
						iulist.getIuserId());
				List<Functions> f = this.functionsService
						.getFunctionsName("多选货物");
				if (f.size() > 0) {
					List<Powers> p = this.powersService.getGongneng(f.get(0)
							.getFunctionId());
					if (p.size() > 0) {
						for (int i = 0; i < p.size(); i++) {
							if (p.get(i).getInteriorUserDuty()
									.getInteriorUserDutyName().equals("客户")) {
								request.getSession().setAttribute("duoxuan",
										"duoxuanhuowu");// 保存多选货物功能的权限
							}
						}
					} else {
						request.getSession().setAttribute("duoxuan", "null");// 保存多选货物功能的权限
					}
				}
				// 根据登录名查询职责,访问不同的权限
				int zhizeId = 0;
				try {
					// 得到职责id
					zhizeId = iulist.getInteriorUserDuty()
							.getInteriorUserDutyId();
					// 保存职责id,以备后用
					request.getSession().setAttribute("zhizeId", zhizeId);
					List<Powers> power = this.powersService.getZhize(zhizeId);// 通过职责查询权限；
					request.getSession().setAttribute("power", power);// 将权限保存到session中
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 根据职责id查询对应的权限，根据权限得到功能
				List<Powers> listPowers = powersService.getZhize(zhizeId);
				for (int i = 0; i < listPowers.size(); i++) {
					// 把查询出来的权限付给powers类
					Powers p = listPowers.get(i);
					// 根据功能id得到功能
					try {
						Functions functions = functionsService.getFunctionsId(p
								.getFunctions().getFunctionId());
						// 把功能保存到集合中
						listFunctions.add(functions);
						// 保存功能
						request.setAttribute("listFunctions", listFunctions);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				/* 封装session */
				if (SessionUserListener.containsKey(request.getSession()
						.getId())) {
					SessionUserListener.removeSession(request.getSession()
							.getId());
				}
				// 把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。
				SessionUserListener.addUserSession(request.getSession());

				// 转到内部人员主界面
				PrintWriter out = response.getWriter();
				out.print("<script>document.location.href='/XGProject/xxcc-sl/index.jsp';</script>");
				out.flush();
				out.close();
				return null;

			} else {
				request.setAttribute("err", "登录名或密码错误！");
				return mapping.findForward("goLoginAll");
			}
		} else {
			// 得到登录客户信息
			Client client = listClient.get(0);
			// 判断用户名或密码是否相同
			if (client.getClientLoginName().equals(loginName)
					&& client.getClientPassword().equals(loginPwd)) {

				// 判断该客户的账号时候已经登录
				SessionUserListener.checkIfHasLogin(listClient.get(0));
				// 到登录日志中添加数据
				String loginId = loginLogService.saveLogin(loginName, request,
						"客户登录");
				request.getSession().setAttribute("loginId", loginId);
				// 保存客户数据
				request.getSession().setAttribute("client", client);
				// 保存客户数据
				request.getSession().setAttribute("clientId",
						client.getClientId());
				// 保存登录名
				request.getSession().setAttribute("loginName", loginName);
				List<Functions> f = this.functionsService
						.getFunctionsName("多选货物");
				if (f.size() > 0) {
					List<Powers> p = this.powersService.getGongneng(f.get(0)
							.getFunctionId());
					if (p.size() > 0) {
						for (int i = 0; i < p.size(); i++) {
							if (p.get(i).getInteriorUserDuty()
									.getInteriorUserDutyName().equals("客户")) {
								request.getSession().setAttribute("duoxuan",
										"duoxuanhuowu");// 保存多选货物功能的权限
							}
						}
					} else {
						request.getSession().setAttribute("duoxuan", "null");// 保存多选货物功能的权限
					}
				}
				/* 封装session */
				if (SessionUserListener.containsKey(request.getSession()
						.getId())) {
					SessionUserListener.removeSession(request.getSession()
							.getId());
				}
				// 把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。
				SessionUserListener.addUserSession(request.getSession());
			} else {
				request.setAttribute("err", "客户登录名或密码错误！");
				return mapping.findForward("goLoginAll");
			}

		}
		// 如果是客户转到客户主页
		return mapping.findForward("goClientMainPageApp");
	}

	// 返回到内部人员主页面
	public ActionForward goInteriorUserMainPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("goInteriorUserMainPage");
	}

	// 当用户忘记密码的时候进行一邮箱的方式发送验证码进行验证
	public ActionForward ForgetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 在这里可以用ajax的方式进行首先验证客户输入的用户名是否正确
		LoginAllForm laf = (LoginAllForm) form;
		PrintWriter out = response.getWriter();
		// 首先判断输入的用户名是客户还是内部人员
		// 首先验证是不是客户
		List<Client> clist = this.clientService.getClientName(laf
				.getLoginName());
		// 生成发送的验证码
		// 定义一个字符数组
		int str = (int) ((Math.random() * 9 + 1) * 100000);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		// 保存当前发送雁阵吗的时间和验证码
		request.getSession().setAttribute("YZNowDate", df.format(new Date()));
		request.getSession().setAttribute("UpdateYZM", str);

		try {
			if (clist.size() > 0) {
				// 如果是客户取出对应的客户中的邮箱地址
				String tel = clist.get(0).getClientTel();
				SendTextMessage s = new SendTextMessage();
				String ok = s.SendMessage(tel, "您的验证码是：" + str
						+ "。请不要把验证码泄露给其他人。");
				out.print(ok);
				out.flush();
				out.close();
				return null;
			} else {
				// 如果不是客户那么就判断是不是内部人员
				List<InteriorUser> iulist = this.interiorUserService
						.getLoginName(laf.getLoginName());
				if (iulist.size() > 0) {
					// 取出内部人员保存的邮箱地址,预留字段一
					String tel = iulist.get(0).getIuserTel();
					SendTextMessage s = new SendTextMessage();
					String ok = s.SendMessage(tel, "您的验证码是：" + str
							+ "。请不要把验证码泄露给其他人。");
					out.print(ok);
					out.flush();
					out.close();
					return null;
				} else {
					// 否则，那就是输入的用户名是错误的
					out.print("error");// 那么就返回用户名错误
					out.flush();
					out.close();
					return null;
				}
			}
		} catch (Exception e) {
			out.print("defeated");// 发送失败
			out.flush();
			out.close();
			return null;
		}
	}

	// 判断输入的验证码是否正确，并且不能超过有效期
	public ActionForward AuthCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginAllForm laf = (LoginAllForm) form;
		PrintWriter out = response.getWriter();
		if (request.getSession().getAttribute("YZNowDate") == null) {
			out.print("error");
			out.flush();
			out.close();
			return null;
		}

		// 取出发送验证码的时间
		String date = request.getSession().getAttribute("YZNowDate").toString();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		// 如果当前时间减发送的时间大于等于5分钟，那么验证码失效
		if (df.parse(df.format(new Date())).getTime()
				- df.parse(date).getTime() >= 2 * 60 * 1000) {
			out.print("shixiao");// 判断为验证码失效
			out.flush();
			out.close();
			return null;
		}
		if (laf.getCode()
				.replace(" ", "")
				.toLowerCase()
				.equals(request.getSession().getAttribute("UpdateYZM")
						.toString().replace(" ", "").toLowerCase())) {
			out.print("success");
			out.flush();
			out.close();
		} else {
			out.print("error");
			out.flush();
			out.close();
		}
		return null;
	}

	// 进行修改密码
	public ActionForward updatePwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginAllForm laf = (LoginAllForm) form;
		PrintWriter out = response.getWriter();
		List<Client> clist = this.clientService.getClientName(laf
				.getLoginName());
		try {
			if (clist.size() > 0) {
				// 如果是客户取出对应的客户中的邮箱地址
				Client client = clist.get(0);
				client.setClientPassword(laf.getLoginPwd());// 设置密码
				boolean ok = this.clientService.update(client);
				if (ok) {
					out.print("chenggong");
					out.flush();
					out.close();
				} else {
					out.print("shibai");
					out.flush();
					out.close();
				}
				return null;
			} else {
				// 如果不是客户那么就判断是不是内部人员
				List<InteriorUser> iulist = this.interiorUserService
						.getLoginName(laf.getLoginName());
				if (iulist.size() > 0) {
					// 取出内部人员保存的邮箱地址,预留字段一
					InteriorUser iu = iulist.get(0);
					iu.setIuserPassword(laf.getLoginPwd());
					boolean ok = this.interiorUserService.update(iu);
					if (ok) {
						out.print("chenggong");
						out.flush();
						out.close();
					} else {
						out.print("shibai");
						out.flush();
						out.close();

					}
					return null;
				}
			}
		} catch (Exception e) {
			out.print("defeated");// 发送失败
			out.flush();
			out.close();
			return null;
		}
		return null;
	}

	// 手机app用ajax访问个人资料
	public ActionForward getAppGerenziliao(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		// 取出发送验证码的时间
		JSONArray array = new JSONArray();
		if (request.getSession().getAttribute("client") != null) {
			Client c = (Client) request.getSession().getAttribute("client");// 获取登录的类
			JSONObject obj = new JSONObject();
			obj.put("id", c.getClientId());// 编号id
			obj.put("fuzeren",
					c.getClientHuman() == null ? "无" : c.getClientHuman());// 负责人
			obj.put("tel", c.getClientTel() == null ? "无" : c.getClientTel());// 联系电话
			obj.put("loginName", c.getClientLoginName());// 登录名
			obj.put("gongsiname",
					c.getClientAbbreviation() == null ? "无" : c
							.getClientAbbreviation());// 公司的名称，在这里是简称
			obj.put("email",
					c.getClientEmail() == null ? "无" : c.getClientEmail());// 客户的电子邮件
			obj.put("zhucetime", c.getClientCreateTime());// 注册时间
			obj.put("password", c.getClientPassword());// 登录密码
			obj.put("shenfen",
					c.getClientStatusNumber() == null ? "无" : c
							.getClientStatusNumber());// 身份证号码
			obj.put("quancheng", c.getClientFirmName());// 单位名称全拼
			obj.put("address",
					c.getClientAddress() == null ? "无" : c.getClientAddress());// 单位地址
			obj.put("hetonghao", c.getClientContract());// 合同号
			obj.put("hetongbegin", c.getClientStartTime());// 合同的起始日期
			obj.put("hetongfinish", c.getClientFinishTime());// 合同的结束日期
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		} else {
			if (request.getSession().getAttribute("iulist") != null) {
				InteriorUser iu = (InteriorUser) request.getSession()
						.getAttribute("iulist");
				JSONObject obj = new JSONObject();
				array.add(obj);
				obj.put("loginName", iu.getIuserLoginName());
				obj.put("tel", iu.getIuserTel());
				obj.put("name", iu.getIuserName());
				obj.put("email", iu.getIuserDefinedOne());
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
		}
		return null;
	}

	// 手机app登录
	public ActionForward goAppLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LoginAllForm laf = (LoginAllForm) form;
		PrintWriter out = response.getWriter();
		// 首先进行判断是不是客户登录
		List<Client> clist = this.clientService.getClientLogin(
				laf.getLoginName(), laf.getLoginPwd());
		if (clist.size() > 0) {
			// 判断该客户的账号时候已经登录
			SessionUserListener.checkIfHasLogin(clist.get(0));
			// 到登录日志中添加数据
			String loginId = loginLogService.saveLogin(laf.getLoginName(),
					request, "客户登录");
			request.getSession().setAttribute("loginId", loginId);
			// 保存客户数据
			request.getSession().setAttribute("client", clist.get(0));
			// 保存客户数据
			request.getSession().setAttribute("clientId",
					clist.get(0).getClientId());
			// 保存登录名
			request.getSession().setAttribute("loginName", laf.getLoginName());
			List<Functions> f = this.functionsService.getFunctionsName("多选货物");
			if (f.size() > 0) {
				List<Powers> p = this.powersService.getGongneng(f.get(0)
						.getFunctionId());
				if (p.size() > 0) {
					for (int i = 0; i < p.size(); i++) {
						if (p.get(i).getInteriorUserDuty()
								.getInteriorUserDutyName().equals("客户")) {
							request.getSession().setAttribute("duoxuan",
									"duoxuanhuowu");// 保存多选货物功能的权限
						}
					}
				} else {
					request.getSession().setAttribute("duoxuan", "null");// 保存多选货物功能的权限
				}
			}
			/* 封装session */
			if (SessionUserListener.containsKey(request.getSession().getId())) {
				SessionUserListener.removeSession(request.getSession().getId());
			}
			// 把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。
			SessionUserListener.addUserSession(request.getSession());

			out.print("kehu");
			out.flush();
			out.close();
			return null;
		} else {
			// 判断是不是内部人员登录
			List<InteriorUser> listIulist = this.interiorUserService.getLogin(
					laf.getLoginName(), laf.getLoginPwd());
			if (listIulist.size() > 0) {
				SessionUserListener.checkIfHasLogin(listIulist.get(0));

				// 创建存放功能集合
				List<Functions> listFunctions = new ArrayList<Functions>();
				InteriorUser iulist = listIulist.get(0);
				iulist.setIuserWork(1);
				// 修改内部人员在线状态为在线
				interiorUserService.updateIuserOnline(iulist.getIuserId(), 0);
				// 到登录日志表中添加数据,并得到登录日志编号
				String loginId = loginLogService.saveLogin(laf.getLoginName(),
						request, "内部登录");
				// 保存登录日志编号
				request.getSession().setAttribute("loginId", loginId);
				// 保存登录名
				request.getSession().setAttribute("loginName",
						laf.getLoginName());
				// 保存内部人员
				request.getSession().setAttribute("iulist", iulist);
				// 保存内部人员id
				request.getSession().setAttribute("iulistId",
						iulist.getIuserId());
				List<Functions> f = this.functionsService
						.getFunctionsName("多选货物");
				if (f.size() > 0) {
					List<Powers> p = this.powersService.getGongneng(f.get(0)
							.getFunctionId());
					if (p.size() > 0) {
						for (int i = 0; i < p.size(); i++) {
							if (p.get(i).getInteriorUserDuty()
									.getInteriorUserDutyName().equals("客户")) {
								request.getSession().setAttribute("duoxuan",
										"duoxuanhuowu");// 保存多选货物功能的权限
							}
						}
					} else {
						request.getSession().setAttribute("duoxuan", "null");// 保存多选货物功能的权限
					}
				}
				// 根据登录名查询职责,访问不同的权限
				int zhizeId = 0;
				try {
					// 得到职责id
					zhizeId = iulist.getInteriorUserDuty()
							.getInteriorUserDutyId();
					// 保存职责id,以备后用
					request.getSession().setAttribute("zhizeId", zhizeId);
					List<Powers> power = this.powersService.getZhize(zhizeId);// 通过职责查询权限；
					request.getSession().setAttribute("power", power);// 将权限保存到session中
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 根据职责id查询对应的权限，根据权限得到功能
				List<Powers> listPowers = powersService.getZhize(zhizeId);
				for (int i = 0; i < listPowers.size(); i++) {
					// 把查询出来的权限付给powers类
					Powers p = listPowers.get(i);
					// 根据功能id得到功能
					try {
						Functions functions = functionsService.getFunctionsId(p
								.getFunctions().getFunctionId());
						// 把功能保存到集合中
						listFunctions.add(functions);
						// 保存功能
						request.setAttribute("listFunctions", listFunctions);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				/* 封装session */
				if (SessionUserListener.containsKey(request.getSession()
						.getId())) {
					SessionUserListener.removeSession(request.getSession()
							.getId());
				}
				// 把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。
				SessionUserListener.addUserSession(request.getSession());

				out.print("neibu");
				out.flush();
				out.close();
				return null;
			} else {
				out.print("error");
				out.flush();
				out.close();
			}
		}

		return null;
	}

}
