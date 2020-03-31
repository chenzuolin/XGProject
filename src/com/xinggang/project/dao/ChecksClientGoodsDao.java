package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.ChecksClientGoods;

/**
 * 盘点客户货物库存dao
 * 
 * @author Administrator
 * 
 */
public interface ChecksClientGoodsDao {
	// 增加
	public boolean save(ChecksClientGoods ccg);

	// 删除
	public boolean delete(ChecksClientGoods ccg);

	// 修改
	public boolean update(ChecksClientGoods ccg);

	// 通过id查询
	public ChecksClientGoods getChecksClientGoodsId(String id);

	// 查询全部
	public List<ChecksClientGoods> getAll();

	// 统计盈库的重量
	public double getTongJiYingKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname);

	// 统计亏库的重量
	public double getTongJiKuiKu(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname);

	// 查询全部，以分页，并且以模糊查询的方式显示
	public List<ChecksClientGoods> getMoHuByPage(String danwei,
			String jiancheng, String danweizhujifu, String ccgId, String begin,
			String finish, String goodsName, String goodsSign,
			String kufangname, int pageNow, int rowSize);

	// 查询全部数据的总页数
	public int getMoHuByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String ccgId, String begin, String finish,
			String goodsName, String goodsSign, String kufangname, int rowSize);

	// 统计客户的期初盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiChu(Integer clientId, Integer GoodsId, String begin);

	// 统计客户的期末盈库或者亏库，统计到相应的客户库存报表中
	public double getChecksQiMo(Integer clientId, Integer GodosId,
			String begin, String finish);
}
