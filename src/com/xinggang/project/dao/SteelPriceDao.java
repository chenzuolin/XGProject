package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.SteelPrice;

/**
 * 今日钢价Dao层接口
 * */
public interface SteelPriceDao {

	// 添加
	public boolean save(SteelPrice steelPrice);

	// 修改
	public boolean update(SteelPrice steelPrice);

	// 删除
	public boolean delete(SteelPrice steelPrice);

	// 通过编号查询
	public SteelPrice getSteelPrice(String id);

	// 查询全部
	public List<SteelPrice> getAll();

	// 通过条件进行查询
	public List<SteelPrice> getConditionQueryByPage(String goodsName,
			String guige, String caizhi, String chandi, String caozuoren,
			String begin, String finish, int pageNow, int rowSize);

	// 通过条件查询行数
	public int getConditionQueryByPageCount(String goodsName, String guige,
			String caizhi, String chandi, String caozuoren, String begin,
			String finish, int rowSize);

	// 取出上一天的数据
	public List<SteelPrice> getLastTimeData(String goodsName, String guige,
			String caizhi, String shuxing, String chandi, String begin,
			String finish);

	// 取出最后一天的最大的时间
	public String getMaxTime();

	// 得出最后一天货物的平均值
	public Double getMaxAVG(String time);
}
