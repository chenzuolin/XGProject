package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.ShiftStockSeed;

/**
 * 过户子订单Service
 * 
 * @author Administrator
 * 
 */
public interface ShiftStockSeedService {
	// 增
	public boolean save(ShiftStockSeed shiftStockSeed);

	// 删除
	public boolean delete(ShiftStockSeed shiftStockSeed);

	// 修改
	public boolean update(ShiftStockSeed shiftStockSeed);

	// 通过id查询
	public ShiftStockSeed getShiftStockSeedId(String id);

	// 查询全部
	public List<ShiftStockSeed> getAll();

	// 通过过户总订单编号查询
	public List<ShiftStockSeed> getShiftStockId(String shiftStockId);

	// 通过货物编号查询
	public List<ShiftStockSeed> getGoodsId(Integer goodsId);

	// 通过订单状态查询
	public List<ShiftStockSeed> getZhuangtai(String zhuang);

	// 通过内部人员模糊查询
	public List<ShiftStockSeed> getNeibu(Integer neibu);

	// 通过结算方式查询
	public List<ShiftStockSeed> getJiesuan(String jiesuan);

	// 查询全部以分页的形式查询，并且多表关联，
	public List<ShiftStockSeed> getMoHuAll(String begin, String finish,
			String danwei, String danweizhujifu, String jiancheng,
			String goodsname, String goodszhujifu, String zonghao,
			String kehuhao, int pageNow, int rowSize);

	// 查询全部页数
	public int getMoHuAllByCount(String begin, String finish, String danwei,
			String danweizhujifu, String jiancheng, String goodsname,
			String goodszhujifu, String zonghao, String kehuhao, int rowSize);

	// 通过客户分页显示
	public List<ShiftStockSeed> getClientByPageAll(Integer clientId,
			int pageNow, int rowSize);

	// 通过客户查询页数
	public int getClientByPageCount(Integer clientId, int rowSize);

	// 通过提货单号和货主进行模糊分页查询
	public List<ShiftStockSeed> getTiHuoDataByPage(String tihuohao,
			String huozhu, String zhuangtai, int pageNow, int rowSize);

	// 通过提货单号和货主进行模糊查询页数
	public int getTiHuoDataByPageCount(String tihuohao, String huozhu,
			String zhuangtai, int rowSize);

	// -----------------------------------app客户

	// 客户app客户根据条件查询入库订单
	public List<ShiftStockSeed> getNuokuInfo(Integer kehuId,
			String jiesuanType, String startTime, String endTime,
			String kehuDanhao, String huowu, int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getNuokuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize);

	// 客户app查询入库订单
	public List<ShiftStockSeed> getNuokuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAll(Integer kehuId,String begin,String finish, int pageSize);

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计,转出的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJ(String begin, String finish,
			Integer clientId, Integer goodsId);

	// 转入的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJ(String begin, String finish,
			Integer clientId, Integer goodsId);

	// 统计客户库存中期初的出库合计,转出的期初
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJ(String begin,
			Integer clientId, Integer goodsId);

	// 统计客户库存中期初的出库合计,转人的期初
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJ(String begin,
			Integer clientId, Integer goodsId);

	// -----------------------------------app客户查询所有作废的所有订单

	// 客户app客户根据条件查询入库订单
	public List<ShiftStockSeed> getNuokuInfoPash(Integer kehuId,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getNuokuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize);

	// 客户app查询入库订单
	public List<ShiftStockSeed> getNuokuInfoAllPash(Integer kehuId,
			int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAllPash(Integer kehuId, int pageSize);

	// 收发存统计查询
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJSFC(String begin,
			String finish, Integer clientId, String goodsName);

	// 转入的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJSFC(String begin,
			String finish, Integer clientId, String goodsName);

	// 统计客户库存中期初的出库合计,转出的期初
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJSFC(String begin,
			Integer clientId, String goodsName);

	// 统计客户库存中期初的出库合计,转人的期初
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJSFC(String begin,
			Integer clientId, String goodsName);

	// 过户工作量统计查询
	public List<ShiftStockSeed> QueryShiftStockWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize);

	// 过户工作量统计页数
	public int QueryShiftStockWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize);

	// 通过客户和开始和结束日期计算过户的费用
	public Double getGuoHuCost(String begin, String finish, Integer clientId);

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询
	public List<ShiftStockSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren);

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询总页数
	public int getJieSuanQueryByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren);

	// app中客户查询订单中日结的订单的费用
	public List<ShiftStockSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize);

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize);

}
