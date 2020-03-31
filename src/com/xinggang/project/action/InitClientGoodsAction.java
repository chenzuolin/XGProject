package com.xinggang.project.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.xinggang.project.dao.InitClientGoodsDao;
import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.InitClientGoods;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.form.InitClientGoodsForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.service.GoodsService;
import com.xinggang.project.service.InputSeedService;
import com.xinggang.project.service.InputService;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.service.TarehouseGoodsService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

public class InitClientGoodsAction extends DispatchAction {

	// 初始化客户库存统计货物dao
	private InitClientGoodsDao initClientGoodsDao;
	// 入库子订单service
	private InputSeedService inputSeedService;
	// 入库总订单service
	private InputService inputService;
	// 客户库存service
	private ClientGoodsService clientGoodsService;
	// 客户service
	private ClientService clientService;
	// 货物service
	private GoodsService goodsService;
	// 库位库存service
	private TarehouseGoodsService tarehouseGoodsService;
	// 批次service
	private TarehouseDetailService tarehouseDetailService;
	// 创建类所需要的id
	PageRow p = new PageRow();

	public void setTarehouseGoodsService(
			TarehouseGoodsService tarehouseGoodsService) {
		this.tarehouseGoodsService = tarehouseGoodsService;
	}

	public void setTarehouseDetailService(
			TarehouseDetailService tarehouseDetailService) {
		this.tarehouseDetailService = tarehouseDetailService;
	}

	public void setInputSeedService(InputSeedService inputSeedService) {
		this.inputSeedService = inputSeedService;
	}

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setInitClientGoodsDao(InitClientGoodsDao initClientGoodsDao) {
		this.initClientGoodsDao = initClientGoodsDao;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return super.execute(mapping, form, request, response);
	}

	// 进行对应客户的以前的库存进行统计
	public ActionForward getAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		List<InitClientGoods> cglist = this.initClientGoodsDao
				.getclientGoods(type);
		// 查询对应的id
		for (int i = 0; i < cglist.size(); i++) {
			List<Client> clientId = this.clientService.getClientDanwei(cglist
					.get(i).getIcgClient());
			if (clientId != null && clientId.size() > 0) {
				cglist.get(i).setClientId(clientId.get(0).getClientId());
			} else {
				cglist.get(i).setClientId(00000000);// 没有查询到对应的客户
			}
			List<Goods> goodsId = this.goodsService.getInitGoodsId(cglist
					.get(i).getIcgName(), cglist.get(i).getIcgGuige(), cglist
					.get(i).getIcgCaizhi(), cglist.get(i).getIcgShuxing(),
					cglist.get(i).getIcgChandi());
			if(goodsId!=null && goodsId.size()>0){
				cglist.get(i).setGoodsId(goodsId.get(0).getGoodsId());
			}else{
				cglist.get(i).setGoodsId(000000);
			}
		}

		request.setAttribute("cglist", cglist);
		return mapping.findForward("goCount");
	}

	// 添加客户的初始化库存到客户的库存，到入库总订单，入库子订单。
	public ActionForward saveInitClientGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		InitClientGoodsForm icf = (InitClientGoodsForm) form;
		String message = "";
		if (icf != null) {
			// 实例化一个入库总订单的类
			int num = 0;
			String n = "";
			// 当前日期
			PresentTime presentTime = new PresentTime();
			// 入库总订单
			Input input = new Input();

			n = p.getInputNumber();
			// 得到总订单id
			String zongId = "入" + presentTime.getDatesNotTime() + n;

			System.out.println("总订单id:" + zongId);

			// 得到总订单
			input.setInputId(zongId);// 总编号
			input.setInputClientNumber("初始化");// 客户单号
			input.setInputCreateTime(presentTime.getTimes());// 发起时间
			input.setClient(this.clientService.getClientId(icf.getClient()));// 客户是
			input.setInputCarryType("初始化");// 运输范式
			input.setInputPlateNumber("初始化");
			input.setInputDriverName("初始化");
			input.setInputDriverTel("初始化");
			input.setInputOrderStatus("入库完成");
			input.setInputDefinedOne("初始化");
			input.setInputRemark("初始化");
			boolean ok = this.inputService.save(input);
			// 入库子订单添加数据
			// 得到货物
			if (ok) {
				Integer[] goodss = icf.getGoodss();
				for (int i = 0; i < goodss.length; i++) {
					String is = "";
					if (i + 1 < 10) {
						is = "0" + (i + 1);
					} else {
						is = String.valueOf(i + 1);
					}
					// 入库子订单
					InputSeed inputSeed = new InputSeed();
					inputSeed.setIseedId("入Z" + presentTime.getDatesNotTime()
							+ n + is);// 子订单id
					inputSeed.setGoods(this.goodsService.getGoodsId(goodss[i]));
					inputSeed.setInput(this.inputService.getInputId(zongId));
					inputSeed
							.setIseedShouldWeight(icf.getIseedShouldWeights()[i]);// 应收重量
					inputSeed.setIseedRealityCost(0.0);// 实收的费用
					inputSeed
							.setIseedRealityWeight(icf.getIseedShouldWeights()[i]);// 实收的重量
					inputSeed.setIseedShouldCost(0.0);// 应收费用
					inputSeed.setIseedClientAccounts("初始化");// 结算方式
					inputSeed.setIseedOrderStatus("入库完成");
					inputSeed.setIseedRemark("初始化");

					boolean oks = this.inputSeedService.save(inputSeed);
					if (oks) {
						num++;
						this.clientGoodsService.ZengjiaKucun(icf.getClient(),
								goodss[i], icf.getIseedShouldWeights()[i], 0.0);
					}
				}
				if (num == goodss.length) {
					message = "提交成功！";
				} else {
					message = "提交失败！";
				}
			}

		} else {
			message = "提交失败！";
		}

		out.print("<script>alert('"
				+ message
				+ "');document.location.href='/XGProject/cangchu/page/InitCliengGoods.jsp';</script>");
		out.flush();
		out.close();

		return null;
	}

	// 进行初始化库位库存
	public ActionForward saveInitTarehouseGoods(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = response.getWriter();
		InitClientGoodsForm icf = (InitClientGoodsForm) form;
		int num = 0;// 判断进行增加的次数
		String message = "";// 消息
		System.out.println("选择的库位的编号是：" + icf.getTarehouse());
		if (icf != null) {
			if (icf.getTarehouse() != null) {
				// 进行增加相应的批次
				// 进行对应货物遍历
				for (int i = 0; i < icf.getGoodss().length; i++) {
					this.tarehouseDetailService.saveGoodsDetail(request,
							icf.getGoodss()[i], icf.getTarehouse(),
							icf.getIseedShouldWeights()[i], icf.getNumber()[i]);
					this.tarehouseGoodsService.zengGoods(icf.getTarehouse(),
							icf.getGoodss()[i], icf.getIseedShouldWeights()[i],
							icf.getNumber()[i]);
					num++;
				}
				if (num == icf.getGoodss().length) {
					message = "提交成功！";
				} else {
					message = "提交失败！";
				}
			} else {
				message = "提交失败！";
			}
		}

		out.print("<script>alert('"
				+ message
				+ "');document.location.href='/XGProject/cangchu/page/InitTarehouseGoods.jsp';</script>");
		out.flush();
		out.close();
		return null;
	}
}
