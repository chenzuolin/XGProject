package com.xinggang.project.serviceImpl;

import java.util.List;

import com.xinggang.project.dao.SteelPriceDao;
import com.xinggang.project.entity.SteelPrice;
import com.xinggang.project.service.SteelPriceService;

public class SteelPriceServiceImpl implements SteelPriceService {

	// 今日钢价dao
	private SteelPriceDao steelPriceDao;

	public void setSteelPriceDao(SteelPriceDao steelPriceDao) {
		this.steelPriceDao = steelPriceDao;
	}

	public boolean save(SteelPrice steelPrice) {
		return this.steelPriceDao.save(steelPrice);
	}

	public boolean update(SteelPrice steelPrice) {
		return this.steelPriceDao.update(steelPrice);
	}

	public boolean delete(SteelPrice steelPrice) {
		return this.steelPriceDao.delete(steelPrice);
	}

	public SteelPrice getSteelPrice(String id) {
		return this.steelPriceDao.getSteelPrice(id);
	}

	public List<SteelPrice> getAll() {
		return this.steelPriceDao.getAll();
	}

	public List<SteelPrice> getConditionQueryByPage(String goodsName,
			String guige, String caizhi, String chandi, String caozuoren,
			String begin, String finish, int pageNow, int rowSize) {
		return this.steelPriceDao.getConditionQueryByPage(goodsName, guige,
				caizhi, chandi, caozuoren, begin, finish, pageNow, rowSize);
	}

	public int getConditionQueryByPageCount(String goodsName, String guige,
			String caizhi, String chandi, String caozuoren, String begin,
			String finish, int rowSize) {
		return this.steelPriceDao.getConditionQueryByPageCount(goodsName,
				guige, caizhi, chandi, caozuoren, begin, finish, rowSize);
	}

	// 取出上一天的数据
	public List<SteelPrice> getLastTimeData(String goodsName, String guige,
			String caizhi, String shuxing, String chandi, String begin,
			String finish) {
		return this.steelPriceDao.getLastTimeData(goodsName, guige, caizhi,
				shuxing, chandi, begin, finish);
	}

	// 取出最后一天的最大的时间
	public String getMaxTime() {
		return this.steelPriceDao.getMaxTime();
	}

	// 得出最后一天货物的平均值
	public Double getMaxAVG(String time) {
		return this.getMaxAVG(time);
	}

}
