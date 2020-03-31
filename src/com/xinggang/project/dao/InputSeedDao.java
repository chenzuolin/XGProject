package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.InputSeed;

/**
 * 入库子订单Dao
 * 
 * @author Administrator
 * 
 */
public interface InputSeedDao {
	// 增
	public boolean save(InputSeed inputSeed);

	// 删
	public boolean delete(InputSeed inputSeed);

	// 改
	public boolean update(InputSeed inputSeed);

	// 通过id查询
	public InputSeed getInputSeedId(String id);

	// 查询全部
	public List<InputSeed> getAll();

	// 通过货物编号查询
	public List<InputSeed> getGoodsId(Integer goodsId);

	// 通过总订单编号查询
	public List<InputSeed> getInputId(String inputId);

	// 通过订单状态查询
	public List<InputSeed> getZhuangtai(String zhuang);

	// 通过结算方式查询
	public List<InputSeed> getJiesuan(String jiesuan);

	// 查询计划入库的订单
	public List<InputSeed> planInput();

	// 多种条件模糊查询查询计划入库的订单
	public List<InputSeed> planInputDuozhong(String diandanId, String kehu,
			String huowu);

	// 返回所有内部人员查询分页页数
	public int count(String status, String status2, String danhao,
			String huozhu, int pageSize);

	// 返回所有内部人员查询分页的信息
	public List<InputSeed> getInfo(String status, String status2,
			String danhao, String huozhu, int pageNow, int rowSize);

	// 统计待处理入库的条数,查询子订单状态为计划入库和准备入库的订单个数
	public int getRuKuDaiChuLi();

	// --------------------客户app查询入库订单
	public List<InputSeed> getRukuInfo(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getRukuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize);

	// 客户app查询所有入库订单
	public List<InputSeed> getRukuInfoAll(Integer kehuId, String begin,String finish, int pageNow,
			int rowSize);

	// 客户app查询所有入库订单,返回行数
	public int getRukuCountAll(Integer kehuId, String begin,String finish, int pageSize);

	// --------------------客户app查询入库订单作废的所有
	public List<InputSeed> getRukuInfoPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getRukuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize);

	// 客户app查询所有入库订单
	public List<InputSeed> getRukuInfoAllPash(Integer kehuId, int pageNow,
			int rowSize);

	// 客户app查询所有入库订单,返回行数
	public int getRukuCountAllPash(Integer kehuId, int pageSize);

	// 通过货物进行统计转发存的数值
	public List<InputSeed> getZhuanFaCunRuKuGeJi(String begin, String finish,
			Integer goodsId);

	// 通过货物进行统计期初库存
	public List<InputSeed> getZhuanFaCunRuKuQiChu(String begin, Integer goodsId);

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计
	public List<InputSeed> getKeHuKuCunRKHJ(String begin, String finish,
			Integer clientId, Integer goodsId);

	// 统计客户库存中期初的出库合计
	public List<InputSeed> getKeHuKuCunQCHJ(String begin, Integer clientId,
			Integer goodsId);

	// 查询订单，查询未作废的
	public List<InputSeed> getRuKuLiShiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount);

	// 查询订单的总页数
	public int getRuKuLiShiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount);

	// 查询作废订单，查询未作废的
	public List<InputSeed> getRuKuZuoFeiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount);

	// 查询作废订单的总页数
	public int getRuKuZuoFeiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount);

	// 统计收发存的量
	public List<InputSeed> getKeHuKuCunRKHJSFC(String begin, String finish,
			Integer clientId, String goodsName);

	// 统计客户库存中期初的出库合计
	public List<InputSeed> getKeHuKuCunQCHJSFC(String begin, Integer clientId,
			String goodsName);

	// 通过运输方式进行统计重量
	public List<InputSeed> getSFCYunShu(String begin, String finish,
			Integer clientId, String goodsName, String yunshu);

	// 通过运输方计算费用合计
	public List<InputSeed> getYunShuCost(String begin, String finish,
			Integer clientId, String yunshu);

	// 查询月结和现结的费用，通过时间，客户进行查询
	public List<InputSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren);

	// 查询月结和现结的费用，通过时间，客户进行查询总页数
	public int getJieSuanQueryByCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren);

	// app中客户查询订单中日结的订单的费用
	public List<InputSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize);

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize);

	// 查询某个客户的当日的订单
	public List<InputSeed> getAppTodayTheOrder(Integer clientId, String begin,
			String finish);

	// 统计相应客户的收发存总和,入库统计
	public Double getRuKuSum(String begin, String finish, String client,
			String goodsName, String type);

	// 统计相应客户的收发存总和,出库统计
	public Double getChuKuSum(String begin, String finish, String client,
			String goodsName, String type);

	// 统计相应客户的收发存总和,过户
	public Double getGuoHuSum(String begin, String finish, String client,
			String goodsName, String type);
}
