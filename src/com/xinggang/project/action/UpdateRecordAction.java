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
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.UpdateRecord;
import com.xinggang.project.form.UpdateRecordForm;
import com.xinggang.project.service.UpdateRecordService;
import com.xinggang.project.tools.PresentTime;

//订单修改记录action
public class UpdateRecordAction extends DispatchAction {
	// 订单修改记录service
	private UpdateRecordService updateRecordService;
	// 时间工具类
	private PresentTime pt = new PresentTime();

	public void setUpdateRecordService(UpdateRecordService updateRecordService) {
		this.updateRecordService = updateRecordService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.execute(mapping, form, request, response);
	}
	
	// 通过编号查询详情
	public ActionForward getXiangQing(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获得form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		if(urf!=null){
			UpdateRecord ur = this.updateRecordService.getUpdateRecordId(urf.getUrid());
			request.setAttribute("ur", ur);
		}
		return mapping.findForward("gocaozuo");
	}
	
	//查询全部的审批记录
	public ActionForward getShenPiJiLu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		if (urf.getBegin() == null) {
			urf.setBegin("");
		}
		if (urf.getFinish() == null || urf.getFinish().equals("")) {
			urf.setFinish(pt.getTimes());
		}
		if (urf.getUrfaqiren() == null) {
			urf.setUrfaqiren("");
		}
		
		List<UpdateRecord> urList = this.updateRecordService.getUrList(urf.getUrfaqiren(), urf.getBegin(), urf.getFinish());
		if(urList.size()<=0){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		
		for(UpdateRecord ur : urList){
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", ur.getUrid());//对应的审批编号
			obj.put("bianhao", ur.getUrzongid());//对应的总订单编号
			obj.put("caozuobh", ur.getUrcaozuoid());//对应的操作订单的编号
			obj.put("client", ur.getUrdefinedtwo());//对应的订单的货主
			obj.put("faqiren", ur.getUrfaqiren());//对应的发起人
			obj.put("faqitime", ur.getUrfaqitime());//对应的发起时间
			obj.put("miaoshu", ur.getUrfaqimiaoshu());//对应的描述
			obj.put("updateneirong",ur.getUrupdateneirong() == null ? "无" : ur.getUrupdateneirong());//修改内容
			obj.put("type", ur.getUrupdatetype());//订单类型
			obj.put("beizhu", ur.getUrupdateremark() == null ? "无": ur.getUrupdateremark());//备注
			obj.put("zhuangtai", ur.getUrdefinedone());//最终状态
			
			obj.put("yijiren", ur.getUrdefinedthree());//一级审批人
			obj.put("yijijie", ur.getUrdefinedfour());//一级审批结果
			obj.put("yijiyijian", ur.getUrdefinedfive());//一级审批意见
			obj.put("yijitime", ur.getUrdefinedsix());//一级审批时间
			
			obj.put("erjiren", ur.getUrdefinedseven());//二级审批人
			obj.put("erjijie", ur.getUrdefinedeight());//二级审批结果
			obj.put("erjiyijian", ur.getUrdefinednine());//二级审批意见
			obj.put("erjitime", ur.getUrdefinedten());//二级审批时间
			
			obj.put("sanjiren", ur.getUrdefinedeleven());//三级审批人
			obj.put("sanjijie", ur.getUrdefinedtwelve());//三级审批结果
			obj.put("sanjiyijian", ur.getUrdefinedthirteen());//三级审批意见
			obj.put("sanjitime", ur.getUrdefinedfourteen());//三级审批时间
			
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		
		//通过登录的人员进行查询相应的要进行审批的数据
		return null;
	}
	
	//查询正在审批中的记录
	public ActionForward getShenPiZhong(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的登录的审批人
		if (urf.getBegin() == null) {
			urf.setBegin("");
		}
		if (urf.getFinish() == null || urf.getFinish().equals("")) {
			urf.setFinish(pt.getTimes());
		}
		if (urf.getUrfaqiren() == null) {
			urf.setUrfaqiren("");
		}
		if(iu==null){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		
		List<UpdateRecord> urList = this.updateRecordService.getShenPi(urf.getUrfaqiren(), urf.getBegin(), urf.getFinish(), "审批中", iu.getIuserName(), "");
		if(urList.size()<=0){
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		
		for(UpdateRecord ur : urList){
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("id", ur.getUrid());//对应的审批编号
			obj.put("bianhao", ur.getUrzongid());//对应的总订单编号
			obj.put("caozuobh", ur.getUrcaozuoid());//对应的操作订单的编号
			obj.put("client", ur.getUrdefinedtwo());//对应的订单的货主
			obj.put("faqiren", ur.getUrfaqiren());//对应的发起人
			obj.put("faqitime", ur.getUrfaqitime());//对应的发起时间
			obj.put("miaoshu", ur.getUrfaqimiaoshu());//对应的描述
			obj.put("updateneirong",ur.getUrupdateneirong() == null ? "无" : ur.getUrupdateneirong());//修改内容
			obj.put("type", ur.getUrupdatetype());//订单类型
			obj.put("beizhu", ur.getUrupdateremark() == null ? "无": ur.getUrupdateremark());//备注
			obj.put("zhuangtai", ur.getUrdefinedone());//最终状态
			
			obj.put("yijiren", ur.getUrdefinedthree());//一级审批人
			obj.put("yijijie", ur.getUrdefinedfour());//一级审批结果
			obj.put("yijiyijian", ur.getUrdefinedfive());//一级审批意见
			obj.put("yijitime", ur.getUrdefinedsix());//一级审批时间
			
			obj.put("erjiren", ur.getUrdefinedseven());//二级审批人
			obj.put("erjijie", ur.getUrdefinedeight());//二级审批结果
			obj.put("erjiyijian", ur.getUrdefinednine());//二级审批意见
			obj.put("erjitime", ur.getUrdefinedten());//二级审批时间
			
			obj.put("sanjiren", ur.getUrdefinedeleven());//三级审批人
			obj.put("sanjijie", ur.getUrdefinedtwelve());//三级审批结果
			obj.put("sanjiyijian", ur.getUrdefinedthirteen());//三级审批意见
			obj.put("sanjitime", ur.getUrdefinedfourteen());//三级审批时间
			
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		
		//通过登录的人员进行查询相应的要进行审批的数据
		return null;
	}
	
	//不同的审批人进行提交
	public ActionForward shenPiSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//得到
		UpdateRecordForm urf = (UpdateRecordForm) form;
		String message = "";// 记录成功与否
		PrintWriter out = response.getWriter();
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的登录的审批人
		if(urf!=null){
			if(iu==null){
				message = "提交失败";
			}else{
				//通过编号查询
				UpdateRecord ur = this.updateRecordService.getUpdateRecordId(urf.getUrid());
				if(ur!=null){
					//通过不同的审批人的提交进行追加内容
					//一级审批人进行提交
					if(urf.getUrdefinedfifteen().equals("1")){
						ur.setUrdefinedfour(urf.getUrdefinedfour());//设置一级审批的结果
						ur.setUrdefinedfive(urf.getUrdefinedfive());//设置一级审批意见
						ur.setUrdefinedsix(pt.getTimes());//设置一级审批的时间
						//判断一级审批的结果是否同意
						if(urf.getUrdefinedfour().equals("不同意")){
							ur.setUrdefinedone("不同意");// 最终的状态将改变为不同意
						}else{
							ur.setUrdefinedseven(urf.getUrdefinedseven());//设置二级审批人
						}
					}else if(urf.getUrdefinedfifteen().equals("2")){
						//二级审批人进行提交
						ur.setUrdefinedeight(urf.getUrdefinedeight());//二级审批结果
						ur.setUrdefinednine(urf.getUrdefinednine());//二级审批意见
						ur.setUrdefinedten(pt.getTimes());//二级审批时间
						//判断二级审批的结果是否同意
						if(urf.getUrdefinedeight().equals("不同意")){
							ur.setUrdefinedone("不同意");// 最终的状态将改变为不同意
						}else{
							ur.setUrdefinedeleven(urf.getUrdefinedeleven());//设置三级审批人
						}
					}else if(urf.getUrdefinedfifteen().equals("3")){
						//三级审批人进行提交
						ur.setUrdefinedtwelve(urf.getUrdefinedtwelve());//三级审批结果
						ur.setUrdefinedthirteen(urf.getUrdefinedthirteen());//三级审批意见
						ur.setUrdefinedfourteen(pt.getTimes());//三级审批时间
						//判断三级审批的结果是否同意
						if(urf.getUrdefinedtwelve().equals("不同意")){
							ur.setUrdefinedone("不同意");// 最终的状态将改变为不同意
						}else if(urf.getUrdefinedtwelve().equals("同意")){
							ur.setUrdefinedone("同意");// 最终的状态将改变为同意
						}
					}
					boolean ok = this.updateRecordService.update(ur);
					if(ok){
						message = "提交成功";
					}else{
						message = "提价失败";
					}
				}else {
					message = "提交失败";
				}
			}
		}else{
			message = "提交失败";
		}
		out.print(message);
		out.flush();
		out.close();
		return null;
	}

	// 订单修改的发起
	// 进行发起修改
	public ActionForward updateFaQi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		// 得到form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		String message = "";// 记录是否发起成功
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的登录的发起修改的人
		if (urf != null) {
			UpdateRecord ur = new UpdateRecord();// 实例化一个订单修改记录
			ur.setUrzongid(urf.getUrzongid());// 设置修改的总订单的编号
			ur.setUrziid(urf.getUrziid());// 设置修改的子订单的编号
			ur.setUrcaozuoid(urf.getUrcaozuoid());// 设置修改的操作订单的编号
			ur.setUrfaqiren(iu.getIuserName());// 对应的修改的发起人
			ur.setUrfaqitime(pt.getTimes());// 对应的修改的发起时间
			ur.setUrfaqimiaoshu(urf.getUrfaqimiaoshu());// 对应的发起的描述
			ur.setUrupdateremark(urf.getUrupdateremark());// 对应的备注
			ur.setUrupdatetype(urf.getUrupdatetype());// 对应的修改类型，出库订单或者是入库订单
			ur.setUrdefinedone("审批中");// 状态，同意或者是不同意
			ur.setUrdefinedtwo(urf.getUrdefinedtwo());// 货主，对应订单的货主
			ur.setUrdefinedthree(urf.getUrdefinedthree());// 一级审批人
			ur.setUrdefinedfour("");// 自定义四
			ur.setUrdefinedfive("");// 自定义无
			ur.setUrdefinedsix("");// 自定义六
			ur.setUrdefinedseven("");// 自定义七
			ur.setUrdefinedeight("");// 自定义八
			ur.setUrdefinednine("");// 自定义九
			ur.setUrdefinedten("");// 自定义十
			ur.setUrdefinedeleven("");// 自定义十一
			ur.setUrdefinedtwelve("");// 自定义十二
			ur.setUrdefinedthirteen("");// 自定义十三
			ur.setUrdefinedfourteen("");// 自定义十四
			ur.setUrdefinedfifteen("");// 自定义十五
			boolean ok = this.updateRecordService.save(ur);
			if (ok) {
				message = "发起成功！";
			} else {
				message = "发起失败！";
			}
		} else {
			message = "发起失败！";
		}
		if (request.getParameter("ff") != null) {
			JSONArray array = new JSONArray();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			if (message.equals("发起成功！")) {
				obj.put("result", "ok");
			} else {
				obj.put("result", "error");
			}
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		PrintWriter out = response.getWriter();
		out.print("<script>alert('" + message + "');window.history.go(-1);</script>");
		return null;
	}

	// 订单修改进行审批，同意，审批通过
	public ActionForward ShenPiTongGuo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 得到form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		String message = "";// 记录是否发起成功
		if (urf != null) {
			// 通过审批的编号进行查询
			UpdateRecord ur = this.updateRecordService.getUpdateRecordId(urf.getUrid());
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的审批的登录人
			if (ur == null || iu == null) {
				message = "审批失败！";
			} else {
				ur.setUrshenpiren(iu.getIuserName());// 设置审批人，
				ur.setUrshenpitime(pt.getTimes());// 设置审批时间
				ur.setUrdefinedone("同意");// 改变状态为同意修改不
				ur.setUrupdateremark(urf.getUrupdateremark());// 设置备注
				ur.setUrdefinedtwo(urf.getUrdefinedtwo());// 自定义二
				ur.setUrdefinedthree(urf.getUrdefinedthree());// 自定义三
				ur.setUrdefinedfour(urf.getUrdefinedfour());// 自定义四
				ur.setUrdefinedfive(urf.getUrdefinedfive());// 自定义无
				ur.setUrdefinedsix(urf.getUrdefinedsix());// 自定义六
				ur.setUrdefinedseven(urf.getUrdefinedseven());// 自定义七
				ur.setUrdefinedeight(urf.getUrdefinedeight());// 自定义八
				ur.setUrdefinednine(urf.getUrdefinednine());// 自定义九
				ur.setUrdefinedten(urf.getUrdefinedten());// 自定义十
				ur.setUrdefinedeleven(urf.getUrdefinedeleven());// 自定义十一
				ur.setUrdefinedtwelve(ur.getUrdefinedtwelve());// 自定义十二
				ur.setUrdefinedthirteen(urf.getUrdefinedthirteen());// 自定义十三
				ur.setUrdefinedfourteen(urf.getUrdefinedfourteen());// 自定义十四
				ur.setUrdefinedfifteen(urf.getUrdefinedfifteen());// 自定义十五

				boolean ok = this.updateRecordService.update(ur);
				if (ok) {
					message = "审批成功！";
				} else {
					message = "审批失败！";
				}
			}
		} else {
			message = "审批失败！";
		}
		// 返回到对应的界面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+ message + "');document.location.href='/XGProject/sys-page/updateshenpi.jsp';</script>");
		return null;
	}

	// 订单修改进行审批，不同意，审批不通过
	public ActionForward ShenPiNotTongGuo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 得到form
		UpdateRecordForm urf = (UpdateRecordForm) form;
		String message = "";// 记录是否修改成功！
		if (urf != null) {
			// 通过审批的编号进行查询
			UpdateRecord ur = this.updateRecordService.getUpdateRecordId(urf.getUrid());
			InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 对应的审批的登录人
			if (ur == null || iu == null) {
				message = "审批失败！";
			} else {
				ur.setUrshenpiren(iu.getIuserName());// 设置审批人，
				ur.setUrshenpitime(pt.getTimes());// 设置审批时间
				ur.setUrdefinedone("不同意");// 改变状态为同意修改不
				ur.setUrupdateremark(urf.getUrupdateremark());// 设置备注
				ur.setUrdefinedtwo(urf.getUrdefinedtwo());// 自定义二
				ur.setUrdefinedthree(urf.getUrdefinedthree());// 自定义三
				ur.setUrdefinedfour(urf.getUrdefinedfour());// 自定义四
				ur.setUrdefinedfive(urf.getUrdefinedfive());// 自定义无
				ur.setUrdefinedsix(urf.getUrdefinedsix());// 自定义六
				ur.setUrdefinedseven(urf.getUrdefinedseven());// 自定义七
				ur.setUrdefinedeight(urf.getUrdefinedeight());// 自定义八
				ur.setUrdefinednine(urf.getUrdefinednine());// 自定义九
				ur.setUrdefinedten(urf.getUrdefinedten());// 自定义十
				ur.setUrdefinedeleven(urf.getUrdefinedeleven());// 自定义十一
				ur.setUrdefinedtwelve(ur.getUrdefinedtwelve());// 自定义十二
				ur.setUrdefinedthirteen(urf.getUrdefinedthirteen());// 自定义十三
				ur.setUrdefinedfourteen(urf.getUrdefinedfourteen());// 自定义十四
				ur.setUrdefinedfifteen(urf.getUrdefinedfifteen());// 自定义十五

				boolean ok = this.updateRecordService.update(ur);
				if (ok) {
					message = "审批成功！";
				} else {
					message = "审批失败！";
				}
			}
		} else {
			message = "审批失败！";
		}
		// 返回到对应的界面
		PrintWriter out = response.getWriter();
		out.print("<script>alert('"+ message+ "');document.location.href='/XGProject/sys-page/updateshenpi.jsp';</script>");
		return null;
	}

	// 用ajax的方式判断，登录的人是否对应该操作订单发起了修改的申请
	// 进行通过操作订单进行查询
	public ActionForward goShenQingPanDuan(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		UpdateRecordForm urf = (UpdateRecordForm) form;
		// 通过操作订单的编号和登录人的名字进行判断，并且状态是同意的，
		InteriorUser iu = (InteriorUser) request.getSession().getAttribute("iulist");// 得到对应的登录人的类
		
		List<UpdateRecord> urlist = this.updateRecordService.goShenPiPanDuan(urf.getUrcaozuoid(), iu.getIuserName(), "同意");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (urlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "no");
			array.add(obj);
		} else {
			if(urlist.get(0).getUrdefinedone().equals("审批中")){
				JSONObject obj = new JSONObject();
				obj.put("result", "zhong");
				array.add(obj);
			}else{
				JSONObject obj = new JSONObject();
				obj.put("result", "yes");
				array.add(obj);
			}
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}

	// 查询要进行审批的记录
	public ActionForward getJinXingShenPi(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<UpdateRecord> urlist = this.updateRecordService.getShenPiCaoZuo();
		request.setAttribute("urlist", urlist);
		return mapping.findForward("goShenPi");
	}

	// 查询所有的审批记录
	public ActionForward getUpdateAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UpdateRecordForm urf = (UpdateRecordForm) form;
		if (urf.getBegin() == null) {
			urf.setBegin("");
		}
		if (urf.getFinish() == null || urf.getFinish().equals("")) {
			urf.setFinish(pt.getTimes());
		}
		if (urf.getUrzongid() == null) {
			urf.setUrzongid("");
		}
		if (urf.getUrfaqiren() == null) {
			urf.setUrfaqiren("");
		}
		int pageNow = 1;
		if (request.getParameter("pageNow") != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		int pageCount = this.updateRecordService.getAllByPageCount(urf.getBegin(), urf.getFinish(), urf.getUrfaqiren(),urf.getUrzongid(), 20);
		if (pageNow >= pageCount) {
			pageNow = pageCount;
		}
		if (pageNow <= 1) {
			pageNow = 1;
		}
		List<UpdateRecord> urlist = this.updateRecordService.getAllByPage(urf.getBegin(), urf.getFinish(), urf.getUrfaqiren(),urf.getUrzongid(), pageNow, 20);
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		if (urlist.size() <= 0) {
			JSONObject obj = new JSONObject();
			obj.put("result", "null");
			array.add(obj);
			out.print(array.toString());
			out.flush();
			out.close();
			return null;
		}
		for (int i = 0; i < urlist.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("result", "notnull");
			obj.put("pageNow", pageNow);
			obj.put("bianhao", urlist.get(i).getUrzongid());
			obj.put("caozuobh", urlist.get(i).getUrcaozuoid());
			obj.put("faqiren", urlist.get(i).getUrfaqiren());
			obj.put("faqitime", urlist.get(i).getUrfaqitime());
			obj.put("shenpiren", urlist.get(i).getUrshenpiren() == null ? "无": urlist.get(i).getUrshenpiren());
			obj.put("shenpitime", urlist.get(i).getUrshenpitime() == null ? "无": urlist.get(i).getUrshenpitime());
			obj.put("miaoshu", urlist.get(i).getUrfaqimiaoshu());
			obj.put("updateneirong",urlist.get(i).getUrupdateneirong() == null ? "无" : urlist.get(i).getUrupdateneirong());
			obj.put("type", urlist.get(i).getUrupdatetype());
			obj.put("beizhu", urlist.get(i).getUrupdateremark() == null ? "无": urlist.get(i).getUrupdateremark());
			obj.put("zhuangtai", urlist.get(i).getUrdefinedone());
			array.add(obj);
		}
		out.print(array.toString());
		out.flush();
		out.close();
		return null;
	}
}
