package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.GoodsCategoryDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.GoodsPropertyDao;
import com.xinggang.project.dao.GoodsQualityDao;
import com.xinggang.project.dao.GoodsStandardDao;
import com.xinggang.project.dao.GoodsUnitDao;
import com.xinggang.project.dao.GoodsYieldlyDao;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.form.GoodsForm;
import com.xinggang.project.service.GoodsService;

/**
 * 货物Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao;
	private GoodsPropertyDao goodsPropertyDao;
	private GoodsQualityDao goodsQualityDao;
	private GoodsStandardDao goodsStandardDao;
	private GoodsUnitDao goodsUnitDao;
	private GoodsCategoryDao goodsCategoryDao;
	private GoodsYieldlyDao goodsYieldlyDao;

	public GoodsCategoryDao getGoodsCategoryDao() {
		return goodsCategoryDao;
	}

	public void setGoodsCategoryDao(GoodsCategoryDao goodsCategoryDao) {
		this.goodsCategoryDao = goodsCategoryDao;
	}

	public GoodsYieldlyDao getGoodsYieldlyDao() {
		return goodsYieldlyDao;
	}

	public void setGoodsYieldlyDao(GoodsYieldlyDao goodsYieldlyDao) {
		this.goodsYieldlyDao = goodsYieldlyDao;
	}

	public GoodsPropertyDao getGoodsPropertyDao() {
		return goodsPropertyDao;
	}

	public void setGoodsPropertyDao(GoodsPropertyDao goodsPropertyDao) {
		this.goodsPropertyDao = goodsPropertyDao;
	}

	public GoodsQualityDao getGoodsQualityDao() {
		return goodsQualityDao;
	}

	public void setGoodsQualityDao(GoodsQualityDao goodsQualityDao) {
		this.goodsQualityDao = goodsQualityDao;
	}

	public GoodsStandardDao getGoodsStandardDao() {
		return goodsStandardDao;
	}

	public void setGoodsStandardDao(GoodsStandardDao goodsStandardDao) {
		this.goodsStandardDao = goodsStandardDao;
	}

	public GoodsUnitDao getGoodsUnitDao() {
		return goodsUnitDao;
	}

	public void setGoodsUnitDao(GoodsUnitDao goodsUnitDao) {
		this.goodsUnitDao = goodsUnitDao;
	}

	public GoodsDao getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	// 添加
	public boolean save(Goods goods) {
		// 首先判断该货物是否存在，如果存在不需要添加
		List<Goods> list = goodsDao.getDuozhong(goods.getGoodsCategory()
				.getGoodsCategoryId(), goods.getGoodsYieldly()
				.getGoodsYieldlyId(), goods.getGoodsName(), goods
				.getGoodsSign(), goods.getGoodsStandard().getGoodsStandardId(),
				goods.getGoodsQuality().getGoodsQualityId(), goods
						.getGoodsProperty().getGoodsPropertyId());
		if (list.size() <= 0) {
			return goodsDao.save(goods);
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(Goods goods) {
		System.out.println(goods.getGoodsName());
		Goods g = goodsDao.getGoodsId(goods.getGoodsId());// 判断修改的对象是否存在
		if (g == null) {
			return false;
		} else {
			return goodsDao.update(goods);
		}
	}

	// 删除
	public boolean delete(Goods goods) {
		Goods g = goodsDao.getGoodsId(goods.getGoodsId());// 判断删除的对象是否存在
		if (g == null) {
			return false;
		} else {
			return goodsDao.delete(goods);
		}
	}

	// 通过id查询
	public Goods getGoodsId(Integer id) {
		return goodsDao.getGoodsId(id);
	}

	// 查询所有货物
	public List<Goods> getAllGoods() {
		return goodsDao.getAllGoods();
	}

	// 查询全部货物，以下是模糊查询，传值时可以传为空的
	public List<Goods> getAll(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty) {
		return goodsDao.getAll(goodsName, goodsSign, goodsStandard,
				goodsQuality, goodsProperty);
	}

	// 分页查询货物
	public List<Goods> getGoodsByPage(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty,
			int pageNow, int rowSize) {
		return goodsDao.getGoodsByPage(goodsName, goodsSign, goodsStandardDao
				.getGoodsStandardId(goodsStandard).getGoodsStandardName(),
				goodsQualityDao.getGoodsQualityId(goodsQuality)
						.getGoodsQualityName(), goodsPropertyDao
						.getGoodsPropertyId(goodsProperty)
						.getGoodsPropertyName(), pageNow, rowSize);
	}

	// 查询货物数据的总行数
	public int getGoodsCount(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty) {
		return goodsDao.getGoodsCount(goodsName, goodsSign, goodsStandardDao
				.getGoodsStandardId(goodsStandard).getGoodsStandardName(),
				goodsQualityDao.getGoodsQualityId(goodsQuality)
						.getGoodsQualityName(), goodsPropertyDao
						.getGoodsPropertyId(goodsProperty)
						.getGoodsPropertyName());
	}

	// 通过多种条件查询
	public List<Goods> getDuozhong(Integer pinlei, Integer chandi,
			String goodsName, String goodsSign, Integer goodsStandard,
			Integer goodsQuality, Integer goodsProperty) {
		return goodsDao.getDuozhong(pinlei, chandi, goodsName, goodsSign,
				goodsStandard, goodsQuality, goodsProperty);
	}

	public List<Goods> getGoodsByPage2(String goods, int pageNow, int rowSize) {
		// TODO Auto-generated method stub
		return goodsDao.getGoodsByPage2(goods, pageNow, rowSize);
	}

	// 得到分页的页数--------------------------------------修改后
	public int getPageCount(String goods, int pageSize) {
		return goodsDao.getPageCount(goods, pageSize);
	}

	// 多种条件查询得到分页的页数
	public int getPageCountTwo(String goodsName, String goodsSign,
			Integer goodsStandard, Integer goodsQuality, Integer goodsProperty,
			int pageSize) {

		return goodsDao.getPageCountTwo(goodsName, goodsSign, goodsStandardDao
				.getGoodsStandardId(goodsStandard).getGoodsStandardName(),
				goodsQualityDao.getGoodsQualityId(goodsQuality)
						.getGoodsQualityName(), goodsPropertyDao
						.getGoodsPropertyId(goodsProperty)
						.getGoodsPropertyName(), pageSize);

	}

	// 添加货物
	public boolean addGoods(GoodsForm goodsForm) {
		boolean ok = false;
		Integer[] goodsProperty = goodsForm.getPropertyId();
		Integer[] goodsQuality = goodsForm.getQualityId();
		Integer[] goodsStandard = goodsForm.getStandardId();
		Integer[] goodsyieldly = goodsForm.getYieldlyId();
		for (int j = 0; j < goodsProperty.length; j++) {
			for (int k = 0; k < goodsQuality.length; k++) {
				for (int b = 0; b < goodsStandard.length; b++) {
					for (int n = 0; n < goodsyieldly.length; n++) {

						Goods goods = new Goods();
						goods.setGoodsName(goodsForm.getGoodsName());
						goods.setGoodsAdjustment(goodsForm.getGoodsAdjustment());
						goods.setGoodsCategory(goodsCategoryDao
								.getGoodsCategoryId(goodsForm
										.getGoodsCategoryId()));
						goods.setGoodsRemark(goodsForm.getGoodsRemark());
						goods.setGoodsSign(goodsForm.getGoodsSign());
						goods.setGoodsDefinedOne("1");
						goods.setGoodsUnit(goodsUnitDao
								.getGoodsUnitId(goodsForm.getGoodsUnitId()));
						goods.setGoodsProperty(goodsPropertyDao
								.getGoodsPropertyId(goodsProperty[j]));
						goods.setGoodsQuality(goodsQualityDao
								.getGoodsQualityId(goodsQuality[k]));
						goods.setGoodsStandard(goodsStandardDao
								.getGoodsStandardId(goodsStandard[b]));
						goods.setGoodsYieldly(goodsYieldlyDao
								.getGoodsYieldlyId(goodsyieldly[n]));
						ok = this.save(goods);
						System.out.println("ok:" + ok);
					}
				}
			}
		}

		return ok;
	}

	public boolean updateGoods(GoodsForm goodsForm) {
		Goods goods = goodsDao.getGoodsId(goodsForm.getGoodsId());
		try {
			//goods.setGoodsId(goodsForm.getGoodsId());
			goods.setGoodsName(goodsForm.getGoodsName());
			goods.setGoodsAdjustment(goodsForm.getGoodsAdjustment());
			goods.setGoodsCategory(goodsCategoryDao
					.getGoodsCategoryId(goodsForm.getGoodsCategoryId()));
			goods.setGoodsProperty(goodsPropertyDao
					.getGoodsPropertyId(goodsForm.getGoodsPropertyId()));
			goods.setGoodsQuality(goodsQualityDao.getGoodsQualityId(goodsForm
					.getGoodsQualityId()));
			goods.setGoodsRemark(goodsForm.getGoodsRemark());
			goods.setGoodsSign(goodsForm.getGoodsSign());
			goods.setGoodsDefinedOne("1");
			goods.setGoodsStandard(goodsStandardDao
					.getGoodsStandardId(goodsForm.getGoodsStandardId()));
			goods.setGoodsUnit(goodsUnitDao.getGoodsUnitId(goodsForm
					.getGoodsUnitId()));
			goods.setGoodsYieldly(goodsYieldlyDao.getGoodsYieldlyId(goodsForm
					.getGoodsYieldlyId()));
		} catch (Exception e) {
			// TODO: handle exception
		}

		boolean ok = this.update(goods);
		return ok;
	}

	// 通过客户查询货物是否具有一些属性
	public List<Goods> getClientGoods(Integer clientId) {
		return this.goodsDao.getClientGoods(clientId);
	}

	// 通过货物品类查询货物
	public List<Goods> getPinlei(Integer pinleiId) {
		return goodsDao.getPinlei(pinleiId);
	}

	// 通过货物的产地查询货物
	public List<Goods> getChandi(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer caizhiid, Integer shuxinid,
			Integer chandiId) {
		return goodsDao.getChandi(peiLeiId, goodsName, guigeid, caizhiid,
				shuxinid, chandiId);
	}

	// 查询该货物名称下的货物
	public List<Goods> getGoodSName(Integer id, String goodsName) {
		return goodsDao.getGoodSName(id, goodsName);
	}

	// 查询该货物规格下的货物
	public List<Goods> getGuiGeId(Integer peiLeiId, String goodsName, Integer id) {
		return goodsDao.getGuiGeId(peiLeiId, goodsName, id);
	}

	// 查询该货物材质下的货物
	public List<Goods> getCaiZhiId(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer id) {
		return goodsDao.getCaiZhiId(peiLeiId, goodsName, guigeid, id);
	}

	// 查询该货物属性下的货物
	public List<Goods> getShuXinId(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer caizhiid, Integer id) {
		return goodsDao.getShuXinId(peiLeiId, goodsName, guigeid, caizhiid, id);
	}

	// 通过产地,规格,属性,材质,货物,品类查询
	public Goods getOneGoods(Integer pingleiId, Integer guigeId,
			String goodsName, Integer caizhiId, Integer shuxinId,
			Integer chandiId) {
		return goodsDao.getOneGoods(pingleiId, guigeId, goodsName, caizhiId,
				shuxinId, chandiId);
	};

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询
	public List<Goods> getZhuanFaCunGoods(String goodsName, String pinlei,
			int pageNow, int rowSize) {
		return this.goodsDao.getZhuanFaCunGoods(goodsName, pinlei, pageNow,
				rowSize);
	}

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询,查询数据的总页数
	public int getZhuanFaCunByCount(String goodsName, String pinlei, int rowSize) {
		return this.goodsDao.getZhuanFaCunByCount(goodsName, pinlei, rowSize);
	}

	public List<Goods> getInitGoodsId(String goodsName, String guige,
			String caizhi, String shuxing, String chandi) {
		return this.goodsDao.getInitGoodsId(goodsName, guige, caizhi, shuxing,
				chandi);
	}
}
