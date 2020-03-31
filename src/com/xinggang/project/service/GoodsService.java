package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Goods;
import com.xinggang.project.form.GoodsForm;

/**
 * 货物Service
 * 
 * @author Administrator
 * 
 */

public interface GoodsService {
	// 添加货物
	public boolean save(Goods goods);

	// 修改货物
	public boolean update(Goods goods);

	// 删除货物,此项目中不可删除
	public boolean delete(Goods goods);

	// 通过id查询
	public Goods getGoodsId(Integer id);

	// 查询全部
	public List<Goods> getAll(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty);

	// 查询所有货物
	public List<Goods> getAllGoods();

	// 分页查询
	public List<Goods> getGoodsByPage(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty,
			int pageNow, int rowSize);

	// 分页查询2---------------------------------修改后
	public List<Goods> getGoodsByPage2(String goods,int pageNow, int rowSize);

	// 返回数据的总记录行数
	public int getGoodsCount(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty);

	// 得到页数页数---------------------修改后
	public int getPageCount(String goods,int pageSize);

	// 根据条件查询分页---------------------修改后
	public int getPageCountTwo(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty,
			int pageSize);

	// 通过货物品类、货物名称、名称助记符、规格、材质、属性、产地查询该货物是否存在
	public List<Goods> getDuozhong(Integer pinlei, Integer chandi,
			String goodsName, String goodsSign, Integer goodsStandard,
			Integer goodsQuality, Integer goodsProperty);

	// 通过货物品类查询货物
	public List<Goods> getPinlei(Integer pinleiId);

	// 通过产地查货物
	public List<Goods> getChandi(Integer peiLeiId,String goodsName,
			Integer guigeid,Integer caizhiid,Integer shuxinid,Integer chandiId);

	// 添加货物
	public boolean addGoods(GoodsForm goodsForm);

	// 修改货物
	public boolean updateGoods(GoodsForm goodsForm);

	// 查询该货物名称下的货物
	public List<Goods> getGoodSName(Integer id,String goodsName);

	// 查询该货物规格下的货物
	public List<Goods> getGuiGeId(Integer peiLeiId,String goodsName,Integer id);

	// 查询该货物材质下的货物
	public List<Goods> getCaiZhiId(Integer peiLeiId,String goodsName,Integer guigeid,Integer id);

	// 查询该货物属性下的货物
	public List<Goods> getShuXinId(Integer peiLeiId,String goodsName,
			Integer guigeid,Integer caizhiid,Integer id);

	// 通过客户查询对应具有的货物，与客户货物库存进行关联查询
	public List<Goods> getClientGoods(Integer clientId);

	// 通过产地,规格,属性,材质,货物,品类查询
	public Goods getOneGoods(Integer pingleiId, Integer guigeId,
			String goodsName, Integer caizhiId, Integer shuxinId,
			Integer chandiId);

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询
	public List<Goods> getZhuanFaCunGoods(String goodsName, String pinlei,
			int pageNow, int rowSize);

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询,查询数据的总页数
	public int getZhuanFaCunByCount(String goodsName, String pinlei, int rowSize);
	
	public List<Goods> getInitGoodsId(String goodsName,String guige,String caizhi,String shuxing,String chandi);
}
