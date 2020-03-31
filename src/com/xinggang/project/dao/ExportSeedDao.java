package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.ExportSeed;

/**
 * 出库子订单Dao
 * 
 * @author Administrator
 * 
 */
public interface ExportSeedDao {
	// 增
	public boolean save(ExportSeed exportSeed);

	// 删
	public boolean delete(ExportSeed exportSeed);

	// 改
	public boolean update(ExportSeed exportSeed);

	// 通过id查询
	public ExportSeed getExportSeedId(String id);

	// 查询全部
	public List<ExportSeed> getAll();

	// 通过总订单编号查询
	public List<ExportSeed> getExportId(String exportId);

	// 通过货物编号查询
	public List<ExportSeed> getGoodsId(Integer goodsId);

	// 通过结算方式查询
	public List<ExportSeed> getJiesuan(String jiesuan);

	// 通过子订单状态查询
	public List<ExportSeed> getZhuangtai(String zhuang);

	// 查询待处理的订单，相当于是计划出库的子订单,以分页的方式显示
	public List<ExportSeed> getDaiChuLiByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int pageNow, int rowSize);

	// 查询待处理的订单，相当于是计划出库的子订单,以分页的方式显示
	public int getDaiChuLiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int rowSize);

	// 查询配送的订单
	public List<ExportSeed> getPeiSongByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int pageNow, int rowSize);

	// 查询配送订单的页数
	public int getPeiSongByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String danwei,
			String danweizhujifu, String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			int rowSize);

	// 查询全部的订单，去除订单作废的订单，
	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，
	public List<ExportSeed> getChuKuLiShiByPage(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int pageNow, int rowSize);

	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，查询总页数
	public int getChuKuLiShiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int rowSize);

	// 查询作废全部的订单，去除订单作废的订单，
	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，
	public List<ExportSeed> getChuKuLiShiZuoFeiByPage(String begin,
			String finish, String zongbianhao, String kehubianhao,
			String goodsName, String huozhu, int pageNow, int rowSize);

	// 通过起始日期，结束日期，订单编号，客户订单编号，货物名称，规格，产地，品类，客户简称，助记符，全拼，查询总页数
	public int getChuKuLiShiZuoFeiByPageCount(String begin, String finish,
			String zongbianhao, String kehubianhao, String goodsName,
			String huozhu, int rowSize);

	// -----------------------------------app客户

	// 客户app客户根据条件查询入库订单
	public List<ExportSeed> getChukuInfo(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getChukuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize);

	// 客户app查询入库订单
	public List<ExportSeed> getChukuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getChukuCountAll(Integer kehuId, String begin,String finish,int pageSize);

	// -----------------------------------app客户查询出所有订单作废的订单

	// 客户app客户根据条件查询入库订单
	public List<ExportSeed> getChukuInfoPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getChukuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize);

	// 客户app查询入库订单
	public List<ExportSeed> getChukuInfoAllPash(Integer kehuId, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getChukuCountAllPash(Integer kehuId, int pageSize);

	// 通过货物进行统计转发存的数值
	public List<ExportSeed> getZhuanFaCunChuKuGeJi(String begin, String finish,
			Integer goodsId);

	// 通过货物进行统计期初库存
	public List<ExportSeed> getZhuanFaCunChuKuQiChu(String begin,
			Integer goodsId);

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计
	public List<ExportSeed> getKeHuKunCunCKHJ(String begin, String finish,
			Integer clientId, Integer goodsId);

	// 统计客户库存中期初的出库合计
	public List<ExportSeed> getKeHuKunCunQCHJ(String begin, Integer clientId,
			Integer goodsId);

	// 对收发存的进行的统计
	// 通过运输方式进行统计重量
	public List<ExportSeed> getSFCYunShu(String begin, String finish,
			Integer clientId, String goodsName, String yunshu);

	// 通过不同运输方式结算不同的费用
	public List<ExportSeed> getYunShuJieSuan(String begin, String finish,
			Integer clientId, String yunshu);

	// 统计二次作业的费用
	public List<ExportSeed> getErCiZuoYeCost(String begin, String finish,
			Integer clientId);

	// 收发存的统计
	public List<ExportSeed> getKeHuKunCunCKHJSFC(String begin, String finish,
			Integer clientId, String goodsName);

	// 统计客户库存中期初的出库合计
	public List<ExportSeed> getKeHuKunCunQCHJSFC(String begin,
			Integer clientId, String goodsName);

	// 查询月结和现结的费用，通过时间，客户进行查询
	public List<ExportSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren);

	// 查询月结和现结的费用，通过时间，客户进行查询总页数
	public int getJieSuanQueryByCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren);

	// app中客户查询订单中日结的订单的费用
	public List<ExportSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize);

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize);

	// 分组出库订单中的年
	public List<String> getGroupByYear();
	
	//通过时间查询对应的入、出、过量
	public Double getLikeDate(String date,String type);
}
