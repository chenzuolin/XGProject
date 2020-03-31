package com.xinggang.project.serviceImpl;

import java.util.List;

import com.xinggang.project.dao.DuanDaoDao;
import com.xinggang.project.entity.DuanDao;
import com.xinggang.project.service.DuanDaoService;

/*
 * 短倒service接口实现类
 * */
public class DuanDaoServiceImpl implements DuanDaoService {

	// 短倒dao
	private DuanDaoDao duanDaoDao;

	public void setDuanDaoDao(DuanDaoDao duanDaoDao) {
		this.duanDaoDao = duanDaoDao;
	}

	// 添加
	public boolean save(DuanDao duandao) {
		return this.duanDaoDao.save(duandao);
	}

	// 删除
	public boolean delete(DuanDao duandao) {
		return this.duanDaoDao.delete(duandao);
	}

	// 修改
	public boolean update(DuanDao duandao) {
		return this.duanDaoDao.update(duandao);
	}

	// 通过编号查询
	public DuanDao getShiftId(String id) {
		return this.duanDaoDao.getShiftId(id);
	}

	// 查询全部
	public List<DuanDao> getAll() {
		return this.duanDaoDao.getAll();
	}

	// 分页查询
	public List<DuanDao> getShiftByPage(int pageNow, int rowSize) {
		return this.duanDaoDao.getShiftByPage(pageNow, rowSize);
	}

	// 查询数据总行数
	public int getShiftByCount() {
		return this.duanDaoDao.getShiftByCount();
	}

	// 通过编号模糊查询
	public List<DuanDao> getShiftLike(String shiftId) {
		return this.duanDaoDao.getShiftLike(shiftId);
	}

	// 通过挪库内部人员查询
	public List<DuanDao> getShiftMember(Integer MemberId) {
		return this.duanDaoDao.getShiftMember(MemberId);
	}

	// 通过多种条件模糊分页查询
	public List<DuanDao> getShiftDuozhongByPage(String begin, String finish,
			Integer goodsId, int pageNow, int rowSize) {
		return this.duanDaoDao.getShiftDuozhongByPage(begin, finish, goodsId,
				pageNow, rowSize);
	}

	// 通过过多种条件查询出数据的总行数
	public int getShiftDuozhongByCount(String begin, String finish,
			Integer goodsId, int rowSize) {
		return this.duanDaoDao.getShiftDuozhongByCount(begin, finish, goodsId,
				rowSize);
	}

	// 通过短倒状态查询
	public List<DuanDao> getShiftZhuangtai(String zhuang) {
		return this.duanDaoDao.getShiftZhuangtai(zhuang);
	}

	// 通过库位查询
	public List<DuanDao> getKuwei(Integer kuweiId) {
		return this.duanDaoDao.getKuwei(kuweiId);
	}

	// 统计天车工的工作量
	public List<DuanDao> getWorkingTC(String working, String begin,
			String finish) {
		return this.duanDaoDao.getWorkingTC(working, begin, finish);
	}

	// 统计装卸工的工作量
	public List<DuanDao> getWorkingZX(String working, String begin,
			String finish) {

		return this.duanDaoDao.getWorkingZX(working, begin, finish);
	}

	// 通过货物进行查询
	public List<DuanDao> getGoodsAll(Integer goodsId) {

		return this.duanDaoDao.getGoodsAll(goodsId);
	}

	// 查询所有的挪库订单，以分页的方式显示
	public List<DuanDao> getShiftAllByPage(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int pageNow, int rowSize) {

		return this.duanDaoDao.getShiftAllByPage(begin, finish, goodsName,
				guige, caizhi, shuxing, zhujifu, kuweiname, pageNow, rowSize);
	}

	// 查询所有的挪库订单的总页数
	public int getShiftAllByPageCount(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int rowSize) {

		return this.duanDaoDao.getShiftAllByPageCount(begin, finish, goodsName,
				guige, caizhi, shuxing, zhujifu, kuweiname, rowSize);
	}

	// 短倒工作量统计查询
	public List<DuanDao> QueryShiftWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {

		return this.duanDaoDao.QueryShiftWorkByPage(begin, finish, zhiwu, name,
				pageNow, rowSize);
	}

	// 短倒工作量统计页数
	public int QueryShiftWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {

		return this.duanDaoDao.QueryShiftWorkByPageCount(begin, finish, zhiwu,
				name, rowSize);
	}

	// 通过短倒司机进行分类，用来统计短倒司机的工作量
	public List<DuanDao> ClassDriverName(String name) {
		return this.duanDaoDao.ClassDriverName(name);
	}

}
