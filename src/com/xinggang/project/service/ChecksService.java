package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Checks;

/**
 * 盘点Service
 * 
 * @author Administrator
 * 
 */
public interface ChecksService {
	// 增加
	public boolean save(Checks checks);

	// 删除
	public boolean delete(Checks checks);

	// 修改
	public boolean update(Checks checks);

	// 通过id查询
	public Checks getChecksId(String id);

	// 查询全部
	public List<Checks> getAll();

	// 分页查询
	public List<Checks> getChecksByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getChecksByCount();

	// 模糊编号查询
	public List<Checks> getChecksLike(String ChecksId);

	// 多种条件模糊查询
	public List<Checks> getDuozhongByPage(String begin, String finish,
			Integer kucun);

	// 多种条件查询数据行数
	public int getDuozhongByCount(String begin, String finish, Integer kucun);

	// 通过盘点状态查询
	public List<Checks> getChecksZhuangtai(String zhuang);

	// 通过货物查询
	public List<Checks> getGoods(Integer goods);

	// 查询所有的盘点订单，以分页的方式显示
	public List<Checks> getChecksAllByPage(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int pageNow, int rowSize);

	// 查询所有的盘点订单的总页数
	public int getChecksAllByPageCount(String begin, String finish,
			String goodsName, String zhujifu, String guige, String caizhi,
			String shuxing, String kuname, int rowSize);

	// 通过库位模糊查询并且以分页的形式显示
	public List<Checks> getTarehouseByPage(String kuname, int pageNow,
			int rowSize);

	// 通过库位模糊查询数据的页数
	public int getTarehouseByPageCount(String kuname, int rowSize);
}
