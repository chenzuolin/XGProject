package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.InputDao;
import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.dao.LoginLogDao;
import com.xinggang.project.dao.PaymentFashionDao;
import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.form.InputForm;
import com.xinggang.project.service.InputService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 入库总订单Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class InputServiceImpl implements InputService {

	// 创建类所需要的id
	PageRow p = new PageRow();

	private InputDao inputDao;

	public void setInputDao(InputDao inputDao) {
		this.inputDao = inputDao;
	}

	// 客户dao
	private ClientDao clientDao;
	// 入库子订单dao
	private InputSeedDao inputSeedDao;
	// 货物dao
	private GoodsDao goodsDao;
	// 支付方式dao
	private PaymentFashionDao paymentFashionDao;
	// 登录日志表dao
	@SuppressWarnings("unused")
	private LoginLogDao loginLogDao;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void setInputSeedDao(InputSeedDao inputSeedDao) {
		this.inputSeedDao = inputSeedDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	public void setPaymentFashionDao(PaymentFashionDao paymentFashionDao) {
		this.paymentFashionDao = paymentFashionDao;
	}

	// 增加入库总订单
	public boolean save(Input input) {
		return inputDao.save(input);
	}

	// 删除总订单
	public boolean delete(Input input) {
		Input i = inputDao.getInputId(input.getInputId());
		if (i == null) {
			return false;
		} else {
			return inputDao.delete(input);
		}
	}

	// 修改入库总订单
	public boolean update(Input input) {
		Input i = inputDao.getInputId(input.getInputId());
		if (i == null) {
			return false;
		} else {
			return inputDao.update(input);
		}
	}

	// 通过id查询总订单
	public Input getInputId(String id) {
		return inputDao.getInputId(id);
	}

	// 查询全部
	public List<Input> getAll() {
		return inputDao.getAll();
	}

	// 通过客户查询
	public List<Client> getClient(Integer ClientId) {
		return inputDao.getClient(ClientId);
	}

	// 分页查询
	public List<Input> getInputByPage(int pageNow, int rowSize) {
		return inputDao.getInputByPage(pageNow, rowSize);
	}

	// 查询数据的总行数
	public int getInputByCount() {
		return inputDao.getInputByCount();
	}

	// 通过客户分页查询
	public List<Input> getClientIBypage(Integer ClientId, int pageNow,
			int rowSize) {
		return inputDao.getClientIBypage(ClientId, pageNow, rowSize);
	}

	// 通过客户查询数据的总行数
	public int getClientIByCount(Integer ClientId) {
		return inputDao.getClientIByCount(ClientId);
	}

	// 通过订单状态查询
	public List<Input> getZhuangtai(String zhuang) {
		return inputDao.getZhuangtai(zhuang);
	}

	// 通过订单状态分页查询
	public List<Input> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {
		return inputDao.getZhuangtaiByPage(zhuang, pageNow, rowSize);
	}

	// 通过订单状态查询出数据的总行数
	public int getZhuangByCount(String zhuang) {
		return inputDao.getZhuangByCount(zhuang);
	}

	// 通过时间范围和客户订单号模糊查询
	public List<Input> getShijian(String begin, String finish, String kehu) {
		return inputDao.getShijian(begin, finish, kehu);
	}

	// 通过时间范围和客户订单号模糊分页查询
	public List<Input> getShijianByPage(String begin, String finish,
			String kehu, int pageNow, int rowSize) {
		return inputDao.getShijianByPage(begin, finish, kehu, pageNow, rowSize);
	}

	// 通过时间和订单号查询数据的总行数
	public int getShijianByCount(String begin, String finish, String kehu) {
		return inputDao.getShijianByCount(begin, finish, kehu);
	}

	// 修改入库订单是否作废
	public boolean updateInputCancel(String id, String shifou) {
		Input input = this.getInputId(id);
		input.setInputCancel(shifou);
		boolean ok = this.update(input);
		return ok;
	}

	// 添加入库
	public boolean saveInput(InputForm inputForm, Double monty,String faqiren) {
		boolean ok2 = false;
		String n = "";
		// 当前日期
		PresentTime presentTime = new PresentTime();
		// 入库总订单
		Input input = new Input();

		n = p.getInputNumber();
		// 得到总订单id
		String zongId = "入" + presentTime.getDatesNotTime() + n;
		
		System.out.println("总订单id:"+zongId);
		
		// 得到总订单
		input.setInputFaQiRen(faqiren);
		input.setInputId(zongId);
		input.setInputClientNumber(inputForm.getInputClientNumber());
		input.setInputCreateTime(presentTime.getTimes());
		input.setClient(clientDao.getClientId(inputForm.getClient()));
		input.setInputCarryType(inputForm.getInputCarryType());
		input.setInputPlateNumber(inputForm.getInputPlateNumber());
		input.setInputDriverName(inputForm.getInputDriverName());
		input.setInputDriverTel(inputForm.getInputDriverTel());
		input.setInputOrderStatus("计划入库");
		input.setInputDefinedOne(inputForm.getInputDefinedOne());
		input.setInputRemark(inputForm.getInputRemark());
		boolean ok = inputDao.save(input);
		// 入库子订单添加数据
		// 得到货物
		if (ok) {
			Integer[] goodss = inputForm.getGoodss();
			for (int i = 0; i < goodss.length; i++) {
				String is = "";
				if (i + 1 < 10) {
					is = "0" + (i + 1);
				} else {
					is = String.valueOf(i + 1);
				}
				// 入库子订单
				InputSeed inputSeed = new InputSeed();
				inputSeed.setIseedId("入Z" + presentTime.getDatesNotTime() + n
						+ is);// 子订单id
				inputSeed.setGoods(goodsDao.getGoodsId(goodss[i]));
				inputSeed.setInput(inputDao.getInputId(zongId));
				inputSeed.setIseedShouldWeight(inputForm
						.getIseedShouldWeights()[i]);// 应收重量
				inputSeed.setIseedShouldCost(monty);// 应收费用
				inputSeed.setIseedClientAccounts(inputForm
						.getIseedClientAccounts());// 结算方式
				inputSeed.setPaymentFashion(paymentFashionDao
						.getPaymentFashionId(inputForm.getPaymentFashion()));// 支付方式
				inputSeed.setIseedDefinedOne(inputForm.getIseedDefinedOne());//车皮号
				inputSeed.setIseedOrderStatus("计划入库");
				inputSeed.setIseedRemark(inputForm.getIseedRemarks()[i]
						.toString());

				ok2 = inputSeedDao.save(inputSeed);
			}
		}
		return ok2;
	}

	// 保存计划入库app
	// 添加入库
	int i = 0;

	public boolean saveInputApp(InputForm inputForm, Double monty,Goods good) {

		boolean ok2 = false;

		// 当前日期
		PresentTime presentTime = new PresentTime();
		// 入库总订单
		Input input = new Input();

		String n = p.getInputNumber();
		// 得到总订单id
		String zongId = "入" + presentTime.getDatesNotTime() + n;
		// 得到总订单
		input.setInputFaQiRen(clientDao.getClientId(inputForm.getClient()).getClientAbbreviation());
		input.setInputId(zongId);
		input.setInputClientNumber(inputForm.getInputClientNumber());
		input.setInputCreateTime(presentTime.getTimes());
		input.setClient(clientDao.getClientId(inputForm.getClient()));
		input.setInputCarryType(inputForm.getInputCarryType());
		input.setInputPlateNumber(inputForm.getInputPlateNumber());
		input.setInputDriverName(inputForm.getInputDriverName());
		input.setInputDriverTel(inputForm.getInputDriverTel());
		input.setInputOrderStatus("计划入库");
		input.setInputDefinedOne(inputForm.getInputDefinedOne());
		input.setInputDefinedTwo(inputForm.getInputDefinedTwo());//单位电话
		input.setInputRemark(inputForm.getInputRemark());
		boolean ok = inputDao.save(input);
		// 入库子订单添加数据
		// 得到货物
		if (ok) {
			i++;
			String is = "";
			if (i < 10) {
				is = "0" + i;
			} else {
				is = String.valueOf(i);
			}
			// 入库子订单
			InputSeed inputSeed = new InputSeed();
			System.out.println("子订单id:" + "入Z"
					+ presentTime.getDatesNotTime() + n + is);
			inputSeed.setGoods(good);
			inputSeed.setIseedId("入Z" + presentTime.getDatesNotTime() + n
					+ is);// 子订单
			inputSeed.setInput(inputDao.getInputId(zongId));
			inputSeed.setIseedShouldWeight(inputForm
					.getIseedShouldWeights()[0]);// 应收重量
			// inputSeed.setIseedShouldNumber(inputForm.getIseedShouldWeight());//应收件数
			inputSeed.setIseedShouldCost(monty);// 应收费用
			// inputSeed.setIseedClientAccounts(inputForm.getIseedClientAccounts());//结算方式
			// inputSeed.setPaymentFashion(paymentFashionDao.getPaymentFashionId(inputForm.getPaymentFashion()));//支付方式
			inputSeed.setIseedOrderStatus("计划入库");
			inputSeed.setIseedRemark(inputForm.getIseedRemark());

			ok2 = inputSeedDao.save(inputSeed);
		}

		return ok2;
	}

	// 修改入库订单状态
	public boolean updateInputStatus(String id, String status) {
		Input input = this.getInputId(id);
		input.setInputOrderStatus(status);
		boolean ok = this.update(input);
		return ok;
	}

	// 分页查询得到数据,状态为计划入库
	public List<InputSeed> getInputByPage2(int pageNow, int rowSize) {
		return inputDao.getInputByPage2(pageNow, rowSize);
	}

	// 得到分页个数
	public int getPageCount(int pageSize) {
		return inputDao.getPageCount(pageSize);
	}

	// 修改每次加入库重量,传入子订单的id,和操作订单的form
	public boolean updateWeight(InputOperate inputOperate, String ziId) {
		// 根据id查询子订单
		InputSeed inputSeed = inputSeedDao.getInputSeedId(ziId);
		// 实际重量在原来的基础上加入重量
		if (inputSeed.getIseedRealityWeight() != null) {
			inputSeed.setIseedRealityWeight(inputSeed.getIseedRealityWeight()
					+ inputOperate.getIoperateRealityWeight());
		} else {
			inputSeed.setIseedRealityWeight(inputOperate
					.getIoperateRealityWeight());
		}

		if (inputSeed.getIseedRealityNumber() != null) {
			// 实际件数在原来的基础上加入件数
			inputSeed.setIseedRealityNumber(inputSeed.getIseedRealityNumber()
					+ inputOperate.getIoperateRealityNumber());
		} else {
			// 实际件数在原来的基础上加入件数
			inputSeed.setIseedRealityNumber(inputOperate
					.getIoperateRealityNumber());
		}

		boolean ok = inputSeedDao.update(inputSeed);
		return ok;
	}

	// 修改订单是否作废
	public boolean updateCancel(String id) {
		Input input = inputDao.getInputId(id);
		input.setInputCancel("是");
		boolean ok = inputDao.update(input);
		return ok;
	}

	// 返回入库，出库，过户的订单发起的最大时间,针对某一个客户
	public String[] getIESMaxTime(Integer clientId) {
		return this.inputDao.getIESMaxTime(clientId);
	}

}
