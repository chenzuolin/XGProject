package com.xinggang.project.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.dao.TarehouseGoodsDao;
import com.xinggang.project.entity.TarehouseGoods;
import com.xinggang.project.service.TarehouseGoodsService;

/**
 * 货物库存Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class TarehouseGoodsServiceImpl implements TarehouseGoodsService {
	private TarehouseGoodsDao tarehouseGoodsDao;
	private GoodsDao goodsDao;
	private TarehouseDao tarehouseDao;

	public void setTarehouseDao(TarehouseDao tarehouseDao) {
		this.tarehouseDao = tarehouseDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setTarehouseGoodsDao(TarehouseGoodsDao tarehouseGoodsDao) {
		this.tarehouseGoodsDao = tarehouseGoodsDao;
	}

	// 增加
	public boolean save(TarehouseGoods tarehouseGoods) {

		return tarehouseGoodsDao.save(tarehouseGoods);
	}

	// 删除，此项目中不能删除
	public boolean delete(TarehouseGoods tarehouseGoods) {

		return false;
	}

	// 修改
	public boolean update(TarehouseGoods tarehouseGoods) {
		TarehouseGoods t = tarehouseGoodsDao.getTarehouseGoodsId(tarehouseGoods
				.getTgoodsId());
		if (t == null) {
			return false;
		}
		return tarehouseGoodsDao.update(tarehouseGoods);
	}

	// 通过id查询
	public TarehouseGoods getTarehouseGoodsId(Integer id) {

		return tarehouseGoodsDao.getTarehouseGoodsId(id);
	}

	// 查询全部
	public List<TarehouseGoods> getAll() {

		return tarehouseGoodsDao.getAll();
	}

	// 通过库位，货物模糊分页查询
	public List<TarehouseGoods> getTarehouseGoodsByPage(Integer kuwei,
			Integer goodsId, int pageNow, int rowSize) {

		return tarehouseGoodsDao.getTarehouseGoodsByPage(kuwei, goodsId,
				pageNow, rowSize);
	}

	// 通过库位，货物查询数据总行数
	public int getTarehouseGoodsByCount(Integer kuwei, Integer goodsId) {

		return tarehouseGoodsDao.getTarehouseGoodsByCount(kuwei, goodsId);
	}

	// 通过货物查询
	public List<TarehouseGoods> getGoods(Integer goodsId) {

		return tarehouseGoodsDao.getGoods(goodsId);
	}

	// 通过库位查询
	public List<TarehouseGoods> getKuwei(Integer kuwei) {

		return tarehouseGoodsDao.getKuwei(kuwei);
	}

	// 对应的库位减少对应货物的重量、件数
	public void jianTGoods(Integer kewei, Integer goodsId, double weight,
			double number) {
		List<TarehouseGoods> tglist = this.tarehouseGoodsDao.getGoods(goodsId);// 通过货物查询
		for (TarehouseGoods tg : tglist) {
			if (tg.getTarehouse().getTarehouseId().equals(kewei)) {
				TarehouseGoods tgs = this.tarehouseGoodsDao
						.getTarehouseGoodsId(tg.getTgoodsId());// 通过id查询
				DecimalFormat df = new DecimalFormat("#############0.000");// 保留三位小数
				double w = Double
						.parseDouble(df.format((tgs.getTgoodsWeight() - weight)));// 取出重量
				tgs.setTgoodsWeight(w);// 减去相应的重量
				double n = Double.parseDouble(df.format(tgs.getTgoodsNumber()
						- number));// 减去相应的件数
				tgs.setTgoodsNumber(n);
				boolean ok = this.tarehouseGoodsDao.update(tgs);// 进行修改
				System.out.println("减库位库存时的返回值是："+ok);
			}
		}
	}

	// 对应的库位增加对应的货物的重量.件数
	public void zengGoods(Integer kuwei, Integer goodsId, double weight,
			double number) {
		List<TarehouseGoods> tglist = this.tarehouseGoodsDao.tggetAllList(
				kuwei, goodsId);
		if (tglist.size() <= 0) {
			TarehouseGoods tgs = new TarehouseGoods();
			tgs.setGoods(this.goodsDao.getGoodsId(goodsId));// 添加相应的货物
			tgs.setTgoodsWeight(weight);// 货物的重量
			tgs.setTgoodsNumber(number);// 货物的件数
			tgs.setTarehouse(this.tarehouseDao.getTarehouseId(kuwei));// 添加相应的库位
			this.tarehouseGoodsDao.save(tgs);
		} else {
			TarehouseGoods tg = tglist.get(0);
			TarehouseGoods tgs = this.tarehouseGoodsDao.getTarehouseGoodsId(tg
					.getTgoodsId());// 通过id查询
			DecimalFormat df = new DecimalFormat("#############0.000");// 保留三位小数
			double w = Double.parseDouble(df.format(tgs.getTgoodsWeight()
					+ weight));// 相应的增加重量
			tgs.setTgoodsWeight(w);
			double n = Double.parseDouble(df.format(tgs.getTgoodsNumber()
					+ number));// 相应的增加件数
			tgs.setTgoodsNumber(n);
			this.tarehouseGoodsDao.update(tgs);
		}

	}
	
	
	
	

	// 通过库位和货物同时查询
	public List<TarehouseGoods> tggetAllList(Integer kuwei, Integer goodsId) {
		return this.tarehouseGoodsDao.tggetAllList(kuwei, goodsId);
	}

	public List<TarehouseGoods> getTarehouseGoodsAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			String kuName, String chandi, String pinlei, int pageNow,
			int rowSize) {
		return this.tarehouseGoodsDao.getTarehouseGoodsAllByPage(goodsName,
				guige, caizhi, shuxing, zhujifu, kuName, chandi, pinlei,
				pageNow, rowSize);
	}

	public int getTarehouseGoodsAllByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, String kuName,
			String chandi, String pinlei, int rowSize) {
		return this.tarehouseGoodsDao.getTarehouseGoodsAllByPageCount(
				goodsName, guige, caizhi, shuxing, zhujifu, kuName, chandi,
				pinlei, rowSize);
	}

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示
	public List<TarehouseGoods> getChecksDataByPage(String search,
			String begin, String finish, int pageNow, int rowSize) {
		return this.tarehouseGoodsDao.getChecksDataByPage(search, begin,
				finish, pageNow, rowSize);
	}

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示,查询数据的总页数
	public int getChecksDataByPageCount(String search, String begin,
			String finish, int rowSize) {
		return this.tarehouseGoodsDao.getChecksDataByPageCount(search, begin,
				finish, rowSize);
	}
	
	
	
	// 库位库存查询
	public List<TarehouseGoods> getKeweiPage(String kuwei, String huowu, int pageNow,
			int rowSize){
		return tarehouseGoodsDao.getKeweiPage(kuwei, huowu, pageNow, rowSize);
		
	};

	// 库位库存查询页数
	public int getKeweiPageCount(String kuwei, String huowu, int pageSize){
		
		return tarehouseGoodsDao.getKeweiPageCount(kuwei, huowu, pageSize);
	};
	
	
}
