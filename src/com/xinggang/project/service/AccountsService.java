package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Accounts;

/**
 * 滞纳金service
 * 
 * @author Administrator
 * 
 */
public interface AccountsService {
	// 增加
	public boolean save(Accounts accounts);

	// 删除
	public boolean delete(Accounts accounts);

	// 修改
	public boolean update(Accounts accounts);

	// 通过id查询
	public Accounts getAccountsId(String id);

	// 查询全部
	public List<Accounts> getAll();

	// 分页查询
	public List<Accounts> getAccountsByPage(Integer clientId, int pageNow,
			int rowSize);

	// 查询数据的总行数
	public int getAccountsByCount(Integer clientId);

	// 通过客户查询
	public List<Accounts> getClient(Integer clientId);

	// 每天自动累加滞纳金的方法
	public void saveZidong();

	// 查询出结束时间和收费时间是空的数据，也就是查询未收费的滞纳金订单
	public List<Accounts> getWeishou();

	// 查询全部，以分页，并且可模糊查询
	public List<Accounts> getMoHuAll(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize);

	// 查询全部，并且模糊查询的数据的总页数
	public int getMoHuInt(String danwei, String jiancheng, String zhujifu,
			int rowSize);

	// 通过客户分页显示，
	public List<Accounts> getClientByPage(Integer clientId, int pageNow,
			int rowSize);

	// 通过客户查询数据的总行数
	public int getClientByPageCount(Integer clientId, int rowSize);

	// 查询未收费的以分页的方式显示,可通过单位名称，简称，助记符，进行模糊的查询
	public List<Accounts> getWeiShouByPage(String danwei, String jiancheng,
			String zhujifu, int pageNow, int rowSize);

	// 查询未收费的订单，查询数据的总页数
	public int getWeiShouByPageCount(String danwei, String jiancheng,
			String zhujifu, int rowSize);

	// 通过开始日期，结束日期，客户，统计应收费用的总和,并且订单不是作废的，
	public List<Accounts> TJRuKuShouldCost(String begin, String finish,
			String clientName, int pageNow, int rowSize);

	// 通过开始日期，结束日期，客户，统计入库重量的总和，计算相应的期末库存
	public List<Accounts> TJRuKuWeigthSum(String begin, String finish,
			String clientName, int pageNow, int rowSize);

	// 查询大于上传如期的期末库存
	public List<Accounts> TJZuiDaKuCun(String finish, String clientName,
			int pageNow, int rowSize);

	// 查询客户出库业务和转出库业务的最大时间
	public List<Accounts> TJChuKuMaxTime(String clientName, int pageNow,
			int rowSize);

	// 查询没有收费的记录
	public List<Accounts> getCostRecordByPage(String begin, String finish,
			String jiancheng, String type, int pageNow, int rowSize,
			String shoufeiren);

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCount(String begin, String finish,
			String jiancheng, String type, int rowSize, String shoufeiren);

	// -------app客户查询

	// 查询没有收费的记录
	public List<Accounts> getCostRecordByPageKH(Integer clientId, String begin,
			String finish, String type, int pageNow, int rowSize);

	// 查询没有收费记录的总页数
	public int getCostRecordByPageCountKH(Integer clientId, String begin,
			String finish, String type, int rowSize);

	// 通过客户进行查询结算的信息和收费的信息
	public List<Accounts> getAppQuery(Integer ClientId, String type);

	// 查询对应客户的费用总和
	public Double getMoneySum(Integer clientId);

	// 通过客户和起始时间、结束时间查询对应的结算费用
	public List<Accounts> getClientAccount(Integer clientId, String begin,
			String finish, String zhuangtai);
}
