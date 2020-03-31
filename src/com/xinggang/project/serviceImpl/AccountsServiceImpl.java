package com.xinggang.project.serviceImpl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.AccountsDao;
import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.dao.ClientGoodsDao;
import com.xinggang.project.dao.ExportSeedDao;
import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.dao.SetAccountsDao;
import com.xinggang.project.dao.ShiftStockSeedDao;
import com.xinggang.project.dao.SteelPriceDao;
import com.xinggang.project.entity.Accounts;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.service.AccountsService;
import com.xinggang.project.tools.PresentTime;

/**
 * 滞纳金service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class AccountsServiceImpl implements AccountsService {
	private AccountsDao accountsDao;// 滞纳金
	private ClientGoodsDao clientGoodsDao;// 客户货物库存
	@SuppressWarnings("unused")
	private SetAccountsDao setAccountsDao; // 滞纳金设置
	@SuppressWarnings("unused")
	private ShiftStockSeedDao shiftStockSeedDao;// 过户子订单
	@SuppressWarnings("unused")
	private ExportSeedDao exportSeedDao; // 出库子订单
	@SuppressWarnings("unused")
	private InputSeedDao inputSeedDao;// 入库子订单
	@SuppressWarnings("unused")
	private ClientDao clientDao;// 客户
	private SteelPriceDao steelPriceDao;

	public void setSteelPriceDao(SteelPriceDao steelPriceDao) {
		this.steelPriceDao = steelPriceDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setClientGoodsDao(ClientGoodsDao clientGoodsDao) {
		this.clientGoodsDao = clientGoodsDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void setSetAccountsDao(SetAccountsDao setAccountsDao) {
		this.setAccountsDao = setAccountsDao;
	}

	public void setShiftStockSeedDao(ShiftStockSeedDao shiftStockSeedDao) {
		this.shiftStockSeedDao = shiftStockSeedDao;
	}

	public void setInputSeedDao(InputSeedDao inputSeedDao) {
		this.inputSeedDao = inputSeedDao;
	}

	public void setExportSeedDao(ExportSeedDao exportSeedDao) {
		this.exportSeedDao = exportSeedDao;
	}

	// 添加
	public boolean save(Accounts accounts) {

		return accountsDao.save(accounts);
	}

	// 删除
	public boolean delete(Accounts accounts) {
		Accounts a = accountsDao.getAccountsId(accounts.getAccountsId());
		if (a == null) {
			return false;
		}
		return accountsDao.delete(accounts);
	}

	// 修改
	public boolean update(Accounts accounts) {
		Accounts a = accountsDao.getAccountsId(accounts.getAccountsId());
		if (a == null) {
			return false;
		}
		return accountsDao.update(accounts);
	}

	// 通过id查询
	public Accounts getAccountsId(String id) {

		return accountsDao.getAccountsId(id);
	}

	// 查询全部
	public List<Accounts> getAll() {

		return accountsDao.getAll();
	}

	// 通过客户分页查询
	public List<Accounts> getAccountsByPage(Integer clientId, int pageNow,
			int rowSize) {

		return accountsDao.getAccountsByPage(clientId, pageNow, rowSize);
	}

	// 通过卡户查询数据的总行数
	public int getAccountsByCount(Integer clientId) {

		return accountsDao.getAccountsByCount(clientId);
	}

	// 通过客户查询
	public List<Accounts> getClient(Integer clientId) {

		return accountsDao.getClient(clientId);
	}

	// 程序自动增加滞纳金方法
	public void saveZidong() {
		List<Accounts> alist = this.accountsDao.getWeishou();// 查询出没有收费的滞纳金订单
		PresentTime pt = new PresentTime();
		// 进行累加，每天进行
		for (Accounts a : alist) {
			// 通过编号查询
			Accounts accounts = this.accountsDao.getAccountsId(a
					.getAccountsId());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dangqian = null;// 当前时间
			Date qixian = null;// 缴费的期限
			try {
				dangqian = df.parse(pt.getTimes());
				qixian = df.parse(accounts.getShoufeiqixian());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (dangqian.getTime() >= qixian.getTime()) {
				double heji = accounts.getAccountsExpensesSeed();// 取出费用合计
				double feilv = accounts.getZhinaFeilv(); // 取出该单的滞纳金费率
				double zhinajinhejis = accounts.getAccountsSeed() == null ? 0.0
						: accounts.getAccountsSeed();// 取出滞纳金合计
				DecimalFormat ddf = new DecimalFormat("############0.00");
				double zhinajinheji = zhinajinhejis + heji * feilv;// 计算出滞纳金合计
				long zhi = (long) (zhinajinheji * 1000);
				if (zhi % 10 != 0 && zhi % 10 < 5) {
					zhi += 10;
				}
				zhinajinheji = ((double) zhi) / 1000;
				accounts.setAccountsSeed(Double.parseDouble(ddf
						.format(zhinajinheji)));
				this.accountsDao.update(accounts);

				// 判断当前客户的货物价值是否小于等于欠费金额

				// 得到今日钢价中最后一次录入的最大时间
				String maxTime = this.steelPriceDao.getMaxTime();
				// 查询对应的时间的平均值
				Double avg = this.steelPriceDao.getMaxAVG(maxTime.substring(0,
						11));

				Double xishu = 1.0;// 对应客户的系数
				if (a.getClient().getClientCredit() != null
						&& a.getClient().getClientCredit() > 0.0) {
					xishu = a.getClient().getClientCredit();
				}
				Double clientWeight = this.clientGoodsDao.clientGoodsSum(a
						.getClient().getClientId());// 通过客户查询相应的货物重量的总和
				// 得出当前客户的货物的价值
				Double jiazhi = (clientWeight == null ? 0.0 : clientWeight)
						* (avg == null ? 1 : avg) * xishu;
				// 查询对应客户的欠费金额
				Double qianFei = this.accountsDao.getMoneySum(a.getClient()
						.getClientId());

				if (jiazhi <= qianFei) {
					List<ClientGoods> cglist = this.clientGoodsDao
							.getClient(accounts.getClient().getClientId());// 通过客户查询库存
					for (ClientGoods cg : cglist) {
						ClientGoods cgs = this.clientGoodsDao
								.getClientGoodsId(cg.getCgoodsId());
						double dongjiew = cgs.getCgoodsWeight();// 获得货物的重量
						cgs.setCgoodsFreeze("是");// 将货物冻结
						cgs.setCgoodsFreezeWeight(cgs.getCgoodsFreezeWeight()
								+ dongjiew);// 已经冻结的重量，加现在冻结的中
						cgs.setCgoodsWeight(0.0);// 设置现有重量为0
						this.clientGoodsDao.update(cgs);
					}
				}
			}

		}
		// 通过客户遍历滞纳金表中是否在半个月之内交了滞纳金，否则就会冻结货物库存
		/*
		 * long time = ((long) 15 * 24 * 60 * 60 * 1000); for (Accounts a :
		 * alist) { Accounts accounts = this.accountsDao.getAccountsId(a
		 * .getAccountsId()); String begin = accounts.getShoufeiqixian();//
		 * 获得滞纳金的起始日期 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 * Date beginD = null; try { beginD = df.parse(begin);//
		 * 将滞纳金的结束日期转换为date类型 Date nowDate = df.parse(pt.getDatesNianYR());//
		 * 取出当前的系统时间 if (nowDate.getTime() - beginD.getTime() > time) {// 为时半个月
		 * List<ClientGoods> cglist = this.clientGoodsDao
		 * .getClient(accounts.getClient().getClientId());// 通过客户查询库存 for
		 * (ClientGoods cg : cglist) { ClientGoods cgs = this.clientGoodsDao
		 * .getClientGoodsId(cg.getCgoodsId()); double dongjiew =
		 * cgs.getCgoodsWeight();// 获得货物的重量 cgs.setCgoodsFreeze("是");// 将货物冻结
		 * cgs.setCgoodsFreezeWeight(cgs.getCgoodsFreezeWeight() + dongjiew);//
		 * 已经冻结的重量，加现在冻结的中 cgs.setCgoodsWeight(0.0);// 设置现有重量为0
		 * this.clientGoodsDao.update(cgs); } } } catch (ParseException e) {
		 * e.printStackTrace(); } }
		 */
	}

	// 查询出未收费的订单
	public List<Accounts> getWeishou() {
		return this.accountsDao.getWeishou();
	}

	// 查询全部，以分页，并且可模糊查询
	public List<Accounts> getMoHuAll(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize) {

		return this.accountsDao.getMoHuAll(danwei, jiancheng, zhujifu, pageNow,
				rowSize);
	}

	// 查询全部，并且模糊查询的数据的总页数
	public int getMoHuInt(String danwei, String jiancheng, String zhujifu,
			int rowSize) {

		return this.accountsDao.getMoHuInt(danwei, jiancheng, zhujifu, rowSize);
	}

	// 通过客户分页显示，
	public List<Accounts> getClientByPage(Integer clientId, int pageNow,
			int rowSize) {

		return this.accountsDao.getClientByPage(clientId, pageNow, rowSize);
	}

	// 通过客户查询数据的总行数
	public int getClientByPageCount(Integer clientId, int rowSize) {

		return this.getClientByPageCount(clientId, rowSize);
	}

	// 查询未收费的滞纳金订单，以分页的方式显示
	public List<Accounts> getWeiShouByPage(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize) {
		return this.accountsDao.getWeiShouByPage(danwei, jiancheng, zhujifu,
				pageNow, rowSize);
	}

	// 查询未收费滞纳金的总页数
	public int getWeiShouByPageCount(String danwei, String jiancheng,
			String zhujifu, int rowSize) {
		return this.accountsDao.getWeiShouByPageCount(danwei, jiancheng,
				zhujifu, rowSize);
	}

	// 通过开始日期，结束日期，客户，统计应收费用的总和,并且订单不是作废的，
	public List<Accounts> TJRuKuShouldCost(String begin, String finish,
			String clientName, int pageNow, int rowSize) {
		return this.TJRuKuShouldCost(begin, finish, clientName, pageNow,
				rowSize);
	}

	// 通过开始日期，结束日期，客户，统计入库重量的总和，计算相应的期末库存
	public List<Accounts> TJRuKuWeigthSum(String begin, String finish,
			String clientName, int pageNow, int rowSize) {
		return this.accountsDao.TJRuKuWeigthSum(begin, finish, clientName,
				pageNow, rowSize);
	}

	// 查询大于上传如期的期末库存
	public List<Accounts> TJZuiDaKuCun(String finish, String clientName,
			int pageNow, int rowSize) {
		return this.accountsDao.TJZuiDaKuCun(finish, clientName, pageNow,
				rowSize);
	}

	// 查询客户出库业务和转出库业务的最大时间
	public List<Accounts> TJChuKuMaxTime(String clientName, int pageNow,
			int rowSize) {
		return this.accountsDao.TJChuKuMaxTime(clientName, pageNow, rowSize);
	}

	// 查询没有收费的记录
	public List<Accounts> getCostRecordByPage(String begin, String finish,
			String jiancheng, String type, int pageNow, int rowSize,
			String shoufeiren) {
		return this.accountsDao.getCostRecordByPage(begin, finish, jiancheng,
				type, pageNow, rowSize, shoufeiren);
	}

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCount(String begin, String finish,
			String jiancheng, String type, int rowSize, String shoufeiren) {
		return this.accountsDao.getCostRecordByPageCount(begin, finish,
				jiancheng, type, rowSize, shoufeiren);
	}

	// -------app客户查询

	// 查询没有收费的记录
	public List<Accounts> getCostRecordByPageKH(Integer clientId, String begin,
			String finish, String type, int pageNow, int rowSize) {
		return this.accountsDao.getCostRecordByPageKH(clientId, begin, finish,
				type, pageNow, rowSize);
	};

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCountKH(Integer clientId, String begin,
			String finish, String type, int rowSize) {
		return this.accountsDao.getCostRecordByPageCountKH(clientId, begin,
				finish, type, rowSize);
	};

	// 通过客户进行查询结算的信息和收费的信息
	public List<Accounts> getAppQuery(Integer ClientId, String type) {
		return this.accountsDao.getAppQuery(ClientId, type);
	}

	// 查询对应客户的费用总和
	public Double getMoneySum(Integer clientId) {
		return this.accountsDao.getMoneySum(clientId);
	}

	// 通过客户和起始时间、结束时间查询对应的结算费用
	public List<Accounts> getClientAccount(Integer clientId, String begin,
			String finish, String zhuangtai) {
		return this.accountsDao.getClientAccount(clientId, begin, finish,
				zhuangtai);
	}

}
