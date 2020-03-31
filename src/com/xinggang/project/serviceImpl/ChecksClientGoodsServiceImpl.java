package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ChecksClientGoodsDao;
import com.xinggang.project.entity.ChecksClientGoods;
import com.xinggang.project.service.ChecksClientGoodsService;

@Transactional
public class ChecksClientGoodsServiceImpl implements ChecksClientGoodsService {

	// 盘点客户库存dao
	private ChecksClientGoodsDao checksClientGoodsDao;

	public void setChecksClientGoodsDao(
			ChecksClientGoodsDao checksClientGoodsDao) {
		this.checksClientGoodsDao = checksClientGoodsDao;
	}

	// 增加
	public boolean save(ChecksClientGoods ccg) {
		return this.checksClientGoodsDao.save(ccg);
	}

	// 删除
	public boolean delete(ChecksClientGoods ccg) {
		return this.checksClientGoodsDao.delete(ccg);
	}

	// 修改
	public boolean update(ChecksClientGoods ccg) {
		return this.checksClientGoodsDao.update(ccg);
	}

	// 通过编号查询
	public ChecksClientGoods getChecksClientGoodsId(String id) {
		return this.checksClientGoodsDao.getChecksClientGoodsId(id);
	}

	// 查询全部
	public List<ChecksClientGoods> getAll() {

		return this.checksClientGoodsDao.getAll();
	}

	// 统计盈库的重量
	public double getTongJiYingKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname) {

		return this.checksClientGoodsDao.getTongJiYingKu(danwei, jiancheng,
				danweizhujifu, ccgId, begin, finish, goodsName, goodsSign,
				kufangname);
	}

	// 统计亏库的重量
	public double getTongJiKuiKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname) {

		return this.checksClientGoodsDao.getTongJiKuiKu(danwei, jiancheng,
				danweizhujifu, ccgId, begin, finish, goodsName, goodsSign,
				kufangname);
	}

	// 查询全部，模糊查询，以分页的方式显示
	public List<ChecksClientGoods> getMoHuByPage(String danwei,
			String jiancheng, String danweizhujifu, String ccgId, String begin,
			String finish, String goodsName, String goodsSign,
			String kufangname, int pageNow, int rowSize) {

		return this.checksClientGoodsDao.getMoHuByPage(danwei, jiancheng,
				danweizhujifu, ccgId, begin, finish, goodsName, goodsSign,
				kufangname, pageNow, rowSize);
	}

	// 查询模糊数据的总页数
	public int getMoHuByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname, int rowSize) {

		return this.checksClientGoodsDao.getMoHuByPageCount(danwei, jiancheng,
				danweizhujifu, ccgId, begin, finish, goodsName, goodsSign,
				kufangname, rowSize);
	}

	// 统计客户的期初盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiChu(Integer clientId, Integer GoodsId, String begin) {
		return this.checksClientGoodsDao.getChecksQiChu(clientId, GoodsId,
				begin);
	}

	// 统计客户的期末盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiMo(Integer clientId, Integer GodosId,
			String begin, String finish) {
		return this.checksClientGoodsDao.getChecksQiMo(clientId, GodosId,
				begin, finish);
	}

}
