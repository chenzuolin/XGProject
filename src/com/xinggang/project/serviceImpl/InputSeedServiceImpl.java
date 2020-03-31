package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.service.InputSeedService;

/**
 * 使用系统的公司内部人员职责service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class InputSeedServiceImpl implements InputSeedService {
	private InputSeedDao inputSeedDao;

	public void setInputSeedDao(InputSeedDao inputSeedDao) {
		this.inputSeedDao = inputSeedDao;
	}

	// 增加一条入库子订单
	public boolean save(InputSeed inputSeed) {
		return inputSeedDao.save(inputSeed);
	}

	// 删除入库子订单
	public boolean delete(InputSeed inputSeed) {
		InputSeed i = inputSeedDao.getInputSeedId(inputSeed.getIseedId());
		if (i == null) {
			return false;
		} else {
			return inputSeedDao.delete(inputSeed);
		}
	}

	// 修改入库子订单
	public boolean update(InputSeed inputSeed) {
		InputSeed i = inputSeedDao.getInputSeedId(inputSeed.getIseedId());
		if (i == null) {
			return false;
		} else {
			return inputSeedDao.update(inputSeed);
		}
	}

	// 通过id查询
	public InputSeed getInputSeedId(String id) {
		return inputSeedDao.getInputSeedId(id);
	}

	// 查询全部
	public List<InputSeed> getAll() {
		return inputSeedDao.getAll();
	}

	// 通过货物查询子订单
	public List<InputSeed> getGoodsId(Integer goodsId) {
		return inputSeedDao.getGoodsId(goodsId);
	}

	// 通过入库总订单查询子订单
	public List<InputSeed> getInputId(String inputId) {
		return inputSeedDao.getInputId(inputId);
	}

	// 通过状态查询
	public List<InputSeed> getZhuangtai(String zhuang) {
		return inputSeedDao.getZhuangtai(zhuang);
	}

	// 通过结算方式查询
	public List<InputSeed> getJiesuan(String jiesuan) {
		return inputSeedDao.getJiesuan(jiesuan);
	}

	// 修改订单状态
	public boolean updateInputSeed(String id, String status) {
		InputSeed inputSeed = this.getInputSeedId(id);
		inputSeed.setIseedOrderStatus(status);
		boolean ok = this.update(inputSeed);
		return ok;
	}

	// 查询计划入库的订单
	public List<InputSeed> planInput() {
		return inputSeedDao.planInput();
	}

	// 多种条件模糊查询查询计划入库的订单
	public List<InputSeed> planInputDuozhong(String diandanId, String kehu,
			String huowu) {
		return inputSeedDao.planInputDuozhong(diandanId, kehu, huowu);
	}

	// 返回所有内部人员查询分页页数
	public int count(String status, String status2, String danhao,
			String huozhu, int pageSize) {
		return inputSeedDao.count(status, status2, danhao, huozhu, pageSize);
	};

	// 返回所有内部人员查询分页的信息
	public List<InputSeed> getInfo(String status, String status2,
			String danhao, String huozhu, int pageNow, int rowSize) {
		return inputSeedDao.getInfo(status, status2, danhao, huozhu, pageNow,
				rowSize);
	};

	// 统计待处理入库的条数,查询子订单状态为计划入库和准备入库的订单个数
	public int getRuKuDaiChuLi() {
		return this.inputSeedDao.getRuKuDaiChuLi();
	}

	public List<InputSeed> getRukuInfo(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize) {

		return inputSeedDao.getRukuInfo(kehuId, jiesuanType, startTime,
				endTime, kehuDanhao, huowu, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getRukuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize) {
		return inputSeedDao.getRukuCount(kehuId, jiesuanType, startTime,
				endTime, kehuDanhao, huowu, pageSize);
	};

	// 客户app查询所有入库订单
	public List<InputSeed> getRukuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize) {
		return inputSeedDao.getRukuInfoAll(kehuId,begin,finish, pageNow, rowSize);
	};

	// 客户app查询所有入库订单,返回行数
	public int getRukuCountAll(Integer kehuId, String begin,String finish,int pageSize) {
		return inputSeedDao.getRukuCountAll(kehuId, begin,finish,pageSize);
	};

	// --------------------客户app查询入库订单作废的所有
	public List<InputSeed> getRukuInfoPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageNow,
			int rowSize) {
		return inputSeedDao.getRukuInfoPash(kehuId, startTime, endTime,
				kehuDanhao, huowu, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getRukuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize) {
		return inputSeedDao.getRukuCountPash(kehuId, startTime, endTime,
				kehuDanhao, huowu, pageSize);
	};

	// 客户app查询所有入库订单
	public List<InputSeed> getRukuInfoAllPash(Integer kehuId, int pageNow,
			int rowSize) {
		return inputSeedDao.getRukuInfoAllPash(kehuId, pageNow, rowSize);
	};

	// 客户app查询所有入库订单,返回行数
	public int getRukuCountAllPash(Integer kehuId, int pageSize) {
		return inputSeedDao.getRukuCountAllPash(kehuId, pageSize);
	};

	// 通过货物进行统计转发存的数值
	public List<InputSeed> getZhuanFaCunRuKuGeJi(String begin, String finish,
			Integer goodsId) {
		return this.inputSeedDao.getZhuanFaCunRuKuGeJi(begin, finish, goodsId);
	}

	// 通过货物进行统计期初库存
	public List<InputSeed> getZhuanFaCunRuKuQiChu(String begin, Integer goodsId) {
		return this.inputSeedDao.getZhuanFaCunRuKuQiChu(begin, goodsId);
	}

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计
	public List<InputSeed> getKeHuKuCunRKHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		return this.inputSeedDao.getKeHuKuCunRKHJ(begin, finish, clientId,
				goodsId);
	}

	// 统计客户库存中期初的出库合计
	public List<InputSeed> getKeHuKuCunQCHJ(String begin, Integer clientId,
			Integer goodsId) {
		return this.inputSeedDao.getKeHuKuCunQCHJ(begin, clientId, goodsId);
	}

	// 统计收发存的量
	public List<InputSeed> getKeHuKuCunRKHJSFC(String begin, String finish,
			Integer clientId, String goodsName) {
		return this.inputSeedDao.getKeHuKuCunRKHJSFC(begin, finish, clientId,
				goodsName);
	}

	// 统计客户库存中期初的出库合计
	public List<InputSeed> getKeHuKuCunQCHJSFC(String begin, Integer clientId,
			String goodsName) {
		return this.inputSeedDao
				.getKeHuKuCunQCHJSFC(begin, clientId, goodsName);
	}

	// 通过运输方式进行统计重量
	public List<InputSeed> getSFCYunShu(String begin, String finish,
			Integer clientId, String goodsName, String yunshu) {
		return this.inputSeedDao.getSFCYunShu(begin, finish, clientId,
				goodsName, yunshu);
	}

	// 查询订单，查询未作废的
	public List<InputSeed> getRuKuLiShiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount) {
		return this.inputSeedDao.getRuKuLiShiByPage(begin, finish, clientName,
				input, clientNumber, goodsName, pageNow, pageCount);
	}

	// 查询订单的总页数
	public int getRuKuLiShiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount) {
		return this.inputSeedDao.getRuKuLiShiByPageCount(begin, finish,
				clientName, input, clientNumber, goodsName, pageCount);
	}

	// 查询作废订单，查询未作废的
	public List<InputSeed> getRuKuZuoFeiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount) {
		return this.inputSeedDao.getRuKuZuoFeiByPage(begin, finish, clientName,
				input, clientNumber, goodsName, pageNow, pageCount);
	}

	// 查询作废订单的总页数
	public int getRuKuZuoFeiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount) {
		return this.inputSeedDao.getRuKuZuoFeiByPageCount(begin, finish,
				clientName, input, clientNumber, goodsName, pageCount);
	}

	// 通过运输方计算费用合计
	public List<InputSeed> getYunShuCost(String begin, String finish,
			Integer clientId, String yunshu) {
		return this.inputSeedDao.getYunShuCost(begin, finish, clientId, yunshu);
	}

	// 查询月结和现结的费用，通过时间，客户进行查询
	public List<InputSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren) {
		return this.inputSeedDao.getJieSuanQueryByPage(clientName, begin,
				finish, jiesuan, pageNow, rowSize, shoufeiren);
	}

	// 查询月结和现结的费用，通过时间，客户进行查询总页数
	public int getJieSuanQueryByCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		return this.inputSeedDao.getJieSuanQueryByCount(clientName, begin,
				finish, jiesuan, rowSize, shoufeiren);
	}

	// app中客户查询订单中日结的订单的费用
	public List<InputSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize) {
		return this.inputSeedDao.getAppQueryDayCostByPage(clientId, search,
				pageNow, rowSize);
	}

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize) {
		return this.inputSeedDao.getAppQueryDayCostByCount(clientId, search,
				pageNow, rowSize);
	}

	// 查询某个客户的当日的订单
	public List<InputSeed> getAppTodayTheOrder(Integer clientId, String begin,
			String finish) {
		return this.inputSeedDao.getAppTodayTheOrder(clientId, begin, finish);
	}

	// 统计相应客户的收发存总和,入库统计
	public Double getRuKuSum(String begin, String finish, String client,
			String goodsName, String type) {
		return this.inputSeedDao.getRuKuSum(begin, finish, client, goodsName,
				type);
	}

	// 统计相应客户的收发存总和,出库统计
	public Double getChuKuSum(String begin, String finish, String client,
			String goodsName, String type) {
		return this.inputSeedDao.getChuKuSum(begin, finish, client, goodsName,
				type);
	}

	// 统计相应客户的收发存总和,过户
	public Double getGuoHuSum(String begin, String finish, String client,
			String goodsName, String type) {
		return this.inputSeedDao.getGuoHuSum(begin, finish, client, goodsName,
				type);
	}
}
