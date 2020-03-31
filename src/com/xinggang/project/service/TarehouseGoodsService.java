package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.TarehouseGoods;

/**
 * 货物库存Service
 * 
 * @author Administrator
 * 
 */
public interface TarehouseGoodsService {
	// 增加
	public boolean save(TarehouseGoods tarehouseGoods);

	// 删除
	public boolean delete(TarehouseGoods tarehouseGoods);

	// 修改
	public boolean update(TarehouseGoods tarehouseGoods);

	// 通过id查询
	public TarehouseGoods getTarehouseGoodsId(Integer id);

	// 查询全部,可以通过货物和库位模糊查询
	public List<TarehouseGoods> getAll();

	// 分页查询
	public List<TarehouseGoods> getTarehouseGoodsByPage(Integer kuwei,
			Integer goodsId, int pageNow, int rowSize);

	// 查询数据总行数
	public int getTarehouseGoodsByCount(Integer kuwei, Integer goodsId);

	// 通过货物查询
	public List<TarehouseGoods> getGoods(Integer goodsId);

	// 通过库位查询
	public List<TarehouseGoods> getKuwei(Integer kuwei);

	// 对应的库位减少相应的货物的重量，件数，传入库位，货物，重量，件数
	public void jianTGoods(Integer kewei, Integer goodsId, double weight,
			double number);

	// 对应的库位增加相应的货物的重量，件数，传入库位，货物，重量，件数
	public void zengGoods(Integer kuwei, Integer goodsId, double weight,
			double number);

	// 通过库位和货物同时查询
	public List<TarehouseGoods> tggetAllList(Integer kuwei, Integer goodsId);

	// 通过货物和库位的模糊查询，货物名称，规格，材质，属性，助记符，库位名称,品类，产地
	public List<TarehouseGoods> getTarehouseGoodsAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			String kuName, String chandi, String pinlei, int pageNow,
			int rowSize);

	// 查询数据的总页数
	public int getTarehouseGoodsAllByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, String kuName,
			String chandi, String pinlei, int rowSize);

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示
	public List<TarehouseGoods> getChecksDataByPage(String search,
			String begin, String finish, int pageNow, int rowSize);

	// 当盘库的时候查询库存在盘库表里没有发起的，并且是当日的时间，并且以分页的方式显示,查询数据的总页数
	public int getChecksDataByPageCount(String search, String begin,
			String finish, int rowSize);
	
	
	// 库位库存查询
	public List<TarehouseGoods> getKeweiPage(String kuwei, String huowu, int pageNow,
			int rowSize);

	// 库位库存查询页数
	public int getKeweiPageCount(String kuwei, String huowu, int pageSize);
}
