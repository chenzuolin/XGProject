package com.xinggang.project.action;

import java.io.PrintWriter;
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
import com.xinggang.project.form.ClientForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.LoginLogService;
import com.xinggang.project.tools.PageRow;

/**
 * 客户类action
 * 
 * @author Administrator
 * 
 */
public class ClientAction extends DispatchAction {
	// 客户service
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	// 登录日志sevice
	private LoginLogService loginLogService;

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public ActionForward goAddClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("goAddClient");
	}

	// 添加客户信息
	public void addClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mess = "";
		boolean ok = false;
		PrintWriter out = response.getWriter();
		ClientForm clientForm = (ClientForm) form;
		try {
			// @SuppressWarnings("deprecation")
			// String keepFilePath = request.getRealPath("/userImg");
			// 根据客户登录名查询，如果有，不让注册
			List<Client> list = clientService.getClientName(clientForm
					.getClientLoginName());

			/*
			 * // 得到省份证图片的名字 FormFile formImgName =
			 * clientForm.getClientStatusImage();
			 */
			// 得到合同图片的名字
			// FormFile formHeTongImgName = clientForm.getClientDefinedOne();

			// int size = formImgName.getFileSize();
			// int size2 = formHeTongImgName.getFileSize();

			if (list.size() > 0) {
				mess = "该用户已存在！";
			} else {
				ok = this.clientService.saveClient(clientForm, "", "");
				if (ok) {
					mess = "注册成功！";
				} else {
					mess = "注册失败！";
				}
				// 文件上传大小为10M
				// (size > 2 * 1024 * 1024) ||
				/*
				 * if ((size2 > 2 * 1024 * 1024)) { mess = "图片过大！不能超过2M!"; }
				 * else { ok = clientService.fileIOUp(clientForm, keepFilePath);
				 * 
				 * if (ok) { // 获得登录日志编号 String loginId = (String)
				 * request.getSession() .getAttribute("loginId"); String
				 * loginName = (String) request.getSession()
				 * .getAttribute("loginName");
				 * loginLogService.updateLogin(loginId, loginName + "添加客户信息");
				 * mess = "添加成功！"; } else { mess = "添加失败！"; } }
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("添加客戶返回的結果是：" + mess);
		out.print("<script>alert('"
				+ mess
				+ "');document.location.href='/XGProject/client.do?flag=selectClient';</script>");

	}

	// 查询所有客户
	public ActionForward selectClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/* 设置响应头允许ajax跨域访问 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		ClientForm cf = (ClientForm) form;
		// 返回行数
		// PageRow pageRow = new PageRow();
		// 获得总页数
		int pageRow = 30;
		if (request.getParameter("pageRow") != null) {
			pageRow = Integer.parseInt(request.getParameter("pageRow").toString());
		}
		//通过客户的全称、简称、助记符进行筛选
		if(cf.getClientAbbreviation()==null){
			cf.setClientAbbreviation("");
		}
		//获得显示的总页数
		int pageCount = clientService.getPageCount(cf.getClientAbbreviation(),pageRow);
		request.setAttribute("pageCount", pageCount);
		// 获得当前页数
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 1;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		
		request.setAttribute("pageNow2", pageNow2);
		// 获得数据
		List<Client> clientlist = clientService.getGoodsByPage2(cf.getClientAbbreviation(),pageNow2,pageRow);
		request.setAttribute("clientlist", clientlist);
		if (request.getParameter("ff") != null) {
			if (request.getParameter("ff").toString().equals("ajax")) {
				PrintWriter out = response.getWriter();
				JSONArray array = new JSONArray();
				if (clientlist.size() > 0) {
					for (int i = 0; i < clientlist.size(); i++) {
						JSONObject obj = new JSONObject();
						obj.put("pageCount", pageCount);
						obj.put("pageNow", pageNow2);
						obj.put("id", clientlist.get(i).getClientId());// 客户编号
						obj.put("loginName", clientlist.get(i).getClientLoginName());// 客户登录名
						obj.put("createTime", clientlist.get(i).getClientCreateTime());// 客户注册时间
						obj.put("tel", clientlist.get(i).getClientTel());// 客户联系电话
						obj.put("quanping", clientlist.get(i).getClientFirmName());// 客户公司全屏
						obj.put("hetonghao", clientlist.get(i).getClientContract());// 合同号
						obj.put("fuzeren", clientlist.get(i).getClientHuman());// 对应的负责人
						obj.put("addres", clientlist.get(i).getClientAddress());// 地址
						array.add(obj);
					}
				}
				out.print(array.toString());
				out.flush();
				out.close();
				return null;
			}
		}
		return mapping.findForward("selectClient");
	}

	// 传入当前页数和总页数进行判断
	public int fenye(String pageNows, int pageCount) {
		int pageNow = 1;
		// 如果超过总页数
		if (Integer.parseInt(pageNows) > pageCount) {
			pageNow = pageCount;
		}
		// 点击下一页，则判断是否超过总页数,没有超过总页数
		if (Integer.parseInt(pageNows) > 0
				&& Integer.parseInt(pageNows) <= pageCount) {
			pageNow = Integer.parseInt(pageNows);
		}
		// 点击上一页，如果低于1页，则设为第1页
		if (Integer.parseInt(pageNows) <= 0) {
			pageNow = 1;
		}

		return pageNow;
	}

	// 根据条件查询所有客户
	public ActionForward goSelectClientTwo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 返回行数
		PageRow pageRow = new PageRow();
		// 根据条件获得总页数
		ClientForm clientForm = (ClientForm) form;
		int pageCount = clientService.getPageCountTwo(
				clientForm.getClientFirmName(),
				clientForm.getClientAbbreviation(), clientForm.getClientSign(),
				clientForm.getClientContract(), pageRow.getClientRow());
		request.setAttribute("pageCountTwo", pageCount);

		// 获得所有客户
		String s_pageNows = request.getParameter("pageNow");
		String yeshu = request.getParameter("yeshu");
		int pageNow2 = 0;
		// 获取用户输入页数
		if (s_pageNows != null) {
			pageNow2 = fenye(s_pageNows, pageCount);
		}
		if (yeshu != null) {
			pageNow2 = fenye(yeshu, pageCount);
		}
		request.setAttribute("pageNow2", pageNow2);
		// 获得数据
		List<Client> clientlistTwo = clientService.getClientByPage(
				clientForm.getClientFirmName(),
				clientForm.getClientAbbreviation(), clientForm.getClientSign(),
				clientForm.getClientContract(), pageNow2,
				pageRow.getClientRow());

		request.setAttribute("clientlistTwo", clientlistTwo);

		return mapping.findForward("selectClient");
	}

	// 去查看客户页面
	public ActionForward goChakanClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int clientId = Integer.parseInt(request.getParameter("id"));
		Client client = clientService.getClientId(clientId);
		request.setAttribute("client", client);
		return mapping.findForward("goChakanClient");
	}

	// 去修改客户页面
	public ActionForward goUpdateClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int clientId = Integer.parseInt(request.getParameter("id"));
		Client client = clientService.getClientId(clientId);
		request.setAttribute("client", client);
		return mapping.findForward("goUpdateClient");
	}

	// 修改
	public void updateClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		ClientForm clientForm = (ClientForm) form;
		boolean ok = clientService.updateClient(clientForm);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "修改客户信息");
			out.print("<script>alert('修改成功！');window.location.href='client.do?flag=selectClient';</script>");
		} else {
			out.print("<script>alert('修改失败！');window.location.href='client.do?flag=selectClient';</script>");
		}

	}

	// 客户修改信息app
	public void updateClientApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mess = "";
		PrintWriter out = response.getWriter();
		ClientForm clientForm = (ClientForm) form;

		Client c = clientService.getClientId(clientForm.getClientId());
		c.setClientAddress(clientForm.getClientAddress());
		c.setClientEmail(clientForm.getClientEmail());
		c.setClientTel(clientForm.getClientTel());

		boolean ok = clientService.update(c);
		if (ok) {
			// 获得登录日志编号
			String loginId = (String) request.getSession().getAttribute(
					"loginId");
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "修改客户信息");
			mess = "修改成功!";
		} else {
			mess = "修改失败!";
		}

		out.print("<script>alert('"
				+ mess
				+ "');window.location.href='client.do?flag=goClientZiliao';</script>");

	}

	// 去修改密码页面
	public ActionForward goUpdateClientPwd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Client client = (Client) request.getSession().getAttribute("client");
		request.setAttribute("client", client);
		return mapping.findForward("goUpdateClientPwd");
	}

	// 修改密码页面
	public ActionForward updateClientPwd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientForm clientForm = (ClientForm) form;
		Client client = clientService.getClientId(clientForm.getClientId());
		client.setClientLoginName(clientForm.getClientLoginName());
		client.setClientPassword(clientForm.getClientPassword());
		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		boolean ok = clientService.update(client);
		if (ok) {
			// 取出登录名
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "修改信息");
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
		return mapping.findForward("goUpdateClientPwd");
	}

	// 停用密码页面
	public void stopClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		Client client = clientService.getClientId(id);
		client.setClientCease(0);
		boolean ok = clientService.update(client);

		// 获得登录日志编号
		String loginId = (String) request.getSession().getAttribute("loginId");
		if (ok) {
			// 取出登录名
			String loginName = (String) request.getSession().getAttribute(
					"loginName");
			loginLogService.updateLogin(loginId, loginName + "停用客户");
			out.print("<script>alert('停用成功！');window.location.href='client.do?flag=selectClient';</script>");
		} else {
			out.print("<script>alert('停用失败！');window.location.href='client.do?flag=selectClient';</script>");
		}

	}

	// app去客户资料页面
	public ActionForward goClientZiliao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int clientid = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		Client client = clientService.getClientId(clientid);
		request.setAttribute("client", client);
		return mapping.findForward("goClientZiliao");
	}

	// 客户app去修改密码页面
	public void updatePwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PrintWriter out = response.getWriter();
		String mess = "";
		boolean ok = false;

		ClientForm clientForm = (ClientForm) form;

		int clientid = Integer.parseInt(request.getSession()
				.getAttribute("clientId").toString());
		Client client = clientService.getClientId(clientid);
		if (client.getClientPassword().equals(clientForm.getOldPwd())) {
			client.setClientPassword(clientForm.getClientPassword());
			ok = clientService.update(client);
			if (ok) {
				mess = "修改成功！";
			} else {
				mess = "修改失败！";
			}
		} else {
			mess = "当前密码无效！";
		}

		out.print("<script>alert('" + mess
				+ "');window.history.go(-1);</script>");

	}

	// 导出客户的查看全部
	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Client> clist = this.clientService.getAll();
		request.setAttribute("clist", clist);
		return mapping.findForward("goDaochu");
	}

	// 手机app客户资料修改,此处只修改对应的客户的信息
	public ActionForward getAppUpdateClient(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ClientForm cf = (ClientForm) form;
		PrintWriter out = response.getWriter();
		if (cf != null) {
			// 通过编号进行查询
			Client c = this.clientService.getClientId(cf.getClientId());
			if (c != null) {// 如果查询结果不为空的时候
				if (cf.getClientTel() != null && !cf.getClientTel().equals("")) {
					c.setClientTel(cf.getClientTel());// 修改联系电话
				}
				if (cf.getClientAddress() != null
						&& !cf.getClientAddress().equals("")) {
					c.setClientAddress(cf.getClientAddress());// 修改单位地址
				}
				if (cf.getClientEmail() != null
						&& !cf.getClientEmail().equals("")) {
					c.setClientEmail(cf.getClientEmail());// 修改邮箱
				}
				if (cf.getClientPassword() != null
						&& !cf.getClientPassword().equals("")) {
					c.setClientPassword(cf.getClientPassword());// 修改密码
				}
				boolean ok = this.clientService.update(c);
				if (ok) {
					out.print("ok");
					out.flush();
					out.close();
					return null;
				} else {
					out.print("shibai");
					out.flush();
					out.close();
					return null;
				}
			} else {
				// 如果当查询的结果为空的时候，返回修改失败
				out.print("shibai");
				out.flush();
				out.close();
				return null;
			}
		} else {
			// 判断当上传的数据为空的时候返回修改失败
			out.print("shibai");
			out.flush();
			out.close();
			return null;
		}
	}

	// 手机app中发起过户的时候查询对应的转入单位是否存在
	public ActionForward getAppZhuanRu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ClientForm cf = (ClientForm) form;
		PrintWriter out = response.getWriter();
		if (cf != null) {
			// 通过编号进行查询
			List<Client> clist = this.clientService.getClientDanwei(cf
					.getClientFirmName());
			if (clist.size() <= 0) {
				out.print("err");
				out.flush();
				out.close();
				return null;
			} else {
				out.print(clist.get(0).getClientId());
				out.flush();
				out.close();
				return null;
			}
		}
		return null;
	}

	// 当客户点击登录之后首页的同意协议之后，进行不再提醒的设置
	public ActionForward setXieYiNot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得同意的客户的编号
		Integer clientId = Integer.parseInt(request.getParameter("clientId"));
		PrintWriter out = response.getWriter();
		if (clientId != null) {
			// 通过客户的编号进行查询
			Client client = this.clientService.getClientId(clientId);
			client.setClientRemark("同意使用协议");
			this.clientService.update(client);
			request.getSession().setAttribute("client", client);
		}
		// 返回到对应的修改密码的界面
		out.print("<script>alert('由于您是初次登录，请您及时修改初始密码，保障您的账号安全！');document.location.href='/XGProject/app-web/password.jsp';</script>");
		out.flush();
		out.close();
		return null;
	}
}
