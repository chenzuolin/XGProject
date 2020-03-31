package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ShiftStockSeedDao;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.service.ShiftStockSeedService;

/**
 * 过户子订单Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ShiftStockSeedServiceImpl implements ShiftStockSeedService {
	private ShiftStockSeedDao shiftStockSeedDao;

	public void setShiftStockSeedDao(ShiftStockSeedDao shiftStockSeedDao) {
		this.shiftStockSeedDao = shiftStockSeedDao;
	}

	// 增加
	public boolean save(ShiftStockSeed shiftStockSeed) {

		return shiftStockSeedDao.save(shiftStockSeed);
	}

	// 删除
	public boolean delete(ShiftStockSeed shiftStockSeed) {
		ShiftStockSeed s = shiftStockSeedDao.getShiftStockSeedId(shiftStockSeed
				.getSsseedId());
		if (s == null) {
			return false;
		} else {
			return shiftStockSeedDao.delete(shiftStockSeed);
		}
	}

	// 修改
	public boolean update(ShiftStockSeed shiftStockSeed) {
		ShiftStockSeed s = shiftStockSeedDao.getShiftStockSeedId(shiftStockSeed
				.getSsseedId());
		if (s == null) {
			return false;
		} else {
			return shiftStockSeedDao.update(shiftStockSeed);
		}
	}

	// 通过ID查询
	public ShiftStockSeed getShiftStockSeedId(String id) {

		return shiftStockSeedDao.getShiftStockSeedId(id);
	}

	// 查询全部
	public List<ShiftStockSeed> getAll() {

		return shiftStockSeedDao.getAll();
	}

	// 通过总订单查询
	public List<ShiftStockSeed> getShiftStockId(String shiftStockId) {

		return shiftStockSeedDao.getShiftStockId(shiftStockId);
	}

	// 通过货物查询
	public List<ShiftStockSeed> getGoodsId(Integer goodsId) {

		return shiftStockSeedDao.getGoodsId(goodsId);
	}

	// 通过订单状态查询
	public List<ShiftStockSeed> getZhuangtai(String zhuang) {

		return shiftStockSeedDao.getZhuangtai(zhuang);
	}

	// 通过内部人员查询
	public List<ShiftStockSeed> getNeibu(Integer neibu) {

		return shiftStockSeedDao.getNeibu(neibu);
	}

	// 通过结算方式查询
	public List<ShiftStockSeed> getJiesuan(String jiesuan) {

		return shiftStockSeedDao.getJiesuan(jiesuan);
	}

	// 查询全部以分页的形式查询，并且多表关联，
	public List<ShiftStockSeed> getMoHuAll(String begin, String finish,
			String danwei, String danweizhujifu, String jiancheng,
			String goodsname, String goodszhujifu, String zonghao,
			String kehuhao, int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getMoHuAll(begin, finish, danwei,
				danweizhujifu, jiancheng, goodsname, goodszhujifu, zonghao,
				kehuhao, pageNow, rowSize);
	}

	// 查询全部页数
	public int getMoHuAllByCount(String begin, String finish, String danwei,
			String danweizhujifu, String jiancheng, String goodsname,
			String goodszhujifu, String zonghao, String kehuhao, int rowSize) {
		return this.shiftStockSeedDao.getMoHuAllByCount(begin, finish, danwei,
				danweizhujifu, jiancheng, goodsname, goodszhujifu, zonghao,
				kehuhao, rowSize);
	}

	// 通过客户分页显示
	public List<ShiftStockSeed> getClientByPageAll(Integer clientId,
			int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getClientByPageAll(clientId, pageNow,
				rowSize);
	}

	// 通过客户查询页数
	public int getClientByPageCount(Integer clientId, int rowSize) {
		return this.shiftStockSeedDao.getClientByPageCount(clientId, rowSize);
	}

	// 通过提货单号和货主进行模糊分页查询
	public List<ShiftStockSeed> getTiHuoDataByPage(String tihuohao,
			String huozhu, String zhuangtai, int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getTiHuoDataByPage(tihuohao, huozhu,
				zhuangtai, pageNow, rowSize);
	}

	// 通过提货单号和货主进行模糊查询页数
	public int getTiHuoDataByPageCount(String tihuohao, String huozhu,
			String zhuangtai, int rowSize) {
		return this.shiftStockSeedDao.getTiHuoDataByPageCount(tihuohao, huozhu,
				zhuangtai, rowSize);
	}

	// -----------------------------------app客户

	// 客户app客户根据条件查询入库订单
	public List<ShiftStockSeed> getNuokuInfo(Integer kehuId,
			String jiesuanType, String startTime, String endTime,
			String kehuDanhao, String huowu, int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getNuokuInfo(kehuId, jiesuanType,
				startTime, endTime, kehuDanhao, huowu, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getNuokuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize) {
		return this.shiftStockSeedDao.getNuokuCount(kehuId, jiesuanType,
				startTime, endTime, kehuDanhao, huowu, pageSize);
	};

	// 客户app查询入库订单
	public List<ShiftStockSeed> getNuokuInfoAll(Integer kehuId,String begin,String finish, int pageNow,
			int rowSize) {
		return this.shiftStockSeedDao.getNuokuInfoAll(kehuId,begin,finish, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAll(Integer kehuId,String begin,String finish, int pageSize) {
		return this.shiftStockSeedDao.getNuokuCountAll(kehuId, begin,finish,pageSize);
	};

	// -----------------------------------app客户查询所有作废的所有订单

	// 客户app客户根据条件查询入库订单
	public List<ShiftStockSeed> getNuokuInfoPash(Integer kehuId,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize) {
		return shiftStockSeedDao.getNuokuInfoPash(kehuId, startTime, endTime,
				kehuDanhao, huowu, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getNuokuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize) {
		return shiftStockSeedDao.getNuokuCountPash(kehuId, startTime, endTime,
				kehuDanhao, huowu, pageSize);
	};

	// 客户app查询入库订单
	public List<ShiftStockSeed> getNuokuInfoAllPash(Integer kehuId,
			int pageNow, int rowSize) {
		return shiftStockSeedDao.getNuokuInfoAllPash(kehuId, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getNuokuCountAllPash(Integer kehuId, int pageSize) {
		return shiftStockSeedDao.getNuokuCountAllPash(kehuId, pageSize);
	};

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计,转出的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		return this.shiftStockSeedDao.getKeHuKuCunGHZCHJ(begin, finish,
				clientId, goodsId);
	}

	// 转入的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		return this.shiftStockSeedDao.getKeHuKuCunGHZRHJ(begin, finish,
				clientId, goodsId);
	}

	// 统计客户库存中期初的出库合计,转出的期初
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJ(String begin,
			Integer clientId, Integer goodsId) {
		return this.shiftStockSeedDao.getKeHuKuCunZCQCHJ(begin, clientId,
				goodsId);
	}

	// 统计客户库存中期初的出库合计,转人的期初
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJ(String begin,
			Integer clientId, Integer goodsId) {
		return this.shiftStockSeedDao.getKeHuKuCunZRQCHJ(begin, clientId,
				goodsId);
	}

	// 收发存统计查询
	public List<ShiftStockSeed> getKeHuKuCunGHZCHJSFC(String begin,
			String finish, Integer clientId, String goodsName) {
		return this.shiftStockSeedDao.getKeHuKuCunGHZCHJSFC(begin, finish,
				clientId, goodsName);
	}

	// 转入的合计
	public List<ShiftStockSeed> getKeHuKuCunGHZRHJSFC(String begin,
			String finish, Integer clientId, String goodsName) {
		return this.shiftStockSeedDao.getKeHuKuCunGHZRHJSFC(begin, finish,
				clientId, goodsName);
	}

	// 统计客户库存中期初的出库合计,转出的期初
	public List<ShiftStockSeed> getKeHuKuCunZCQCHJSFC(String begin,
			Integer clientId, String goodsName) {
		return this.shiftStockSeedDao.getKeHuKuCunZCQCHJSFC(begin, clientId,
				goodsName);
	}

	// 统计客户库存中期初的出库合计,转人的期初
	public List<ShiftStockSeed> getKeHuKuCunZRQCHJSFC(String begin,
			Integer clientId, String goodsName) {
		return this.shiftStockSeedDao.getKeHuKuCunZRQCHJSFC(begin, clientId,
				goodsName);
	}

	// 过户工作量统计查询
	public List<ShiftStockSeed> QueryShiftStockWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize) {
		return this.shiftStockSeedDao.QueryShiftStockWorkByPage(begin, finish,
				zhiwu, name, pageNow, rowSize);
	}

	// 过户工作量统计页数
	public int QueryShiftStockWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		return this.shiftStockSeedDao.QueryShiftStockWorkByPageCount(begin,
				finish, zhiwu, name, rowSize);
	}

	// 通过客户和开始和结束日期计算过户的费用
	public Double getGuoHuCost(String begin, String finish, Integer clientId) {
		return this.shiftStockSeedDao.getGuoHuCost(begin, finish, clientId);
	}

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询
	public List<ShiftStockSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren) {
		return this.shiftStockSeedDao.getJieSuanQueryByPage(clientName, begin,
				finish, jiesuan, pageNow, rowSize, shoufeiren);
	}

	// 查询过户中的订单的相应费用，通过时间和结算方式进行查询总页数
	public int getJieSuanQueryByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		return this.shiftStockSeedDao.getJieSuanQueryByPageCount(clientName,
				begin, finish, jiesuan, rowSize, shoufeiren);
	}

	// app中客户查询订单中日结的订单的费用
	public List<ShiftStockSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getAppQueryDayCostByPage(clientId,
				search, pageNow, rowSize);
	}

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize) {
		return this.shiftStockSeedDao.getAppQueryDayCostByCount(clientId,
				search, pageNow, rowSize);
	}
}
