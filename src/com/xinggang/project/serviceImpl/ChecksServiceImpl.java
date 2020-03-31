package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ChecksDao;
import com.xinggang.project.entity.Checks;
import com.xinggang.project.service.ChecksService;

/**
 * 盘点Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ChecksServiceImpl implements ChecksService {
	private ChecksDao checksDao;

	public void setChecksDao(ChecksDao checksDao) {
		this.checksDao = checksDao;
	}

	// 增加
	public boolean save(Checks checks) {

		return checksDao.save(checks);
	}

	// 删除
	public boolean delete(Checks checks) {
		Checks c = checksDao.getChecksId(checks.getCheckId());
		if (c == null) {
			return false;
		}
		return checksDao.delete(checks);
	}

	// 修改
	public boolean update(Checks checks) {
		Checks c = checksDao.getChecksId(checks.getCheckId());
		if (c == null) {
			return false;
		}
		return checksDao.update(checks);
	}

	// 通过ID查询
	public Checks getChecksId(String id) {

		return checksDao.getChecksId(id);
	}

	// 查询全部
	public List<Checks> getAll() {

		return checksDao.getAll();
	}

	// 分页查询
	public List<Checks> getChecksByPage(int pageNow, int rowSize) {

		return checksDao.getChecksByPage(pageNow, rowSize);
	}

	// 查询出数据的总行数
	public int getChecksByCount() {

		return checksDao.getChecksByCount();
	}

	// 通过盘点编号模糊查询
	public List<Checks> getChecksLike(String ChecksId) {

		return checksDao.getChecksLike(ChecksId);
	}

	// 通过多种条件模糊分页查询
	public List<Checks> getDuozhongByPage(String begin, String finish,
			Integer kucun) {

		return checksDao.getDuozhongByPage(begin, finish, kucun);
	}

	// 通过多种条件查询数据的总行数
	public int getDuozhongByCount(String begin, String finish, Integer kucun) {

		return checksDao.getDuozhongByCount(begin, finish, kucun);
	}

	// 通过装太查询
	public List<Checks> getChecksZhuangtai(String zhuang) {

		return checksDao.getChecksZhuangtai(zhuang);
	}

	// 通过货物库存查询
	public List<Checks> getGoods(Integer goods) {

		return checksDao.getGoods(goods);
	}

	// 查询所有的盘点订单，以分页的方式显示，
	public List<Checks> getChecksAllByPage(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int pageNow, int rowSize) {
		return this.checksDao.getChecksAllByPage(begin, finish, goodsName,
				zhujifu, guige, caizhi, shuxing, kuname, pageNow, rowSize);
	}

	// 查询所有盘点订单的总页数
	public int getChecksAllByPageCount(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int rowSize) {
		return this.checksDao.getChecksAllByPageCount(begin, finish, goodsName,
				zhujifu, guige, caizhi, shuxing, kuname, rowSize);
	}

	// 通过库位名称进行模糊的查询，给审核人员看
	public List<Checks> getTarehouseByPage(String kuname, int pageNow,
			int rowSize) {
		return this.checksDao.getTarehouseByPage(kuname, pageNow, rowSize);
	}

	// 通过库位的名称进行查询，给什么人员看
	public int getTarehouseByPageCount(String kuname, int rowSize) {
		return this.checksDao.getTarehouseByPageCount(kuname, rowSize);
	}

}
