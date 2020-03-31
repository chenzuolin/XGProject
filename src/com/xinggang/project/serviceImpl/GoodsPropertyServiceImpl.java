package com.xinggang.project.serviceImpl;

import java.util.List;
import com.xinggang.project.dao.GoodsPropertyDao;
import com.xinggang.project.entity.GoodsProperty;
import com.xinggang.project.form.GoodsPropertyForm;
import com.xinggang.project.service.GoodsPropertyService;

public class GoodsPropertyServiceImpl implements GoodsPropertyService {

	private GoodsPropertyDao goodsPropertyDao;
	
	public void setGoodsPropertyDao(GoodsPropertyDao goodsPropertyDao) {
		this.goodsPropertyDao = goodsPropertyDao;
	}
	
	public boolean save(GoodsProperty goodsProperty) {
		List<GoodsProperty> list = goodsPropertyDao.getShuxinName(goodsProperty
				.getGoodsPropertyName());
		if (list.size() <= 0) {
			return goodsPropertyDao.save(goodsProperty);
		} else {
			return false;
		}
	}

	public boolean delete(GoodsProperty goodsProperty) {
		GoodsProperty g = goodsPropertyDao.getGoodsPropertyId(goodsProperty
				.getGoodsPropertyId());
		if (g == null) {
			return false;
		} else {
			return goodsPropertyDao.delete(goodsProperty);
		}
	}

	public boolean update(GoodsProperty goodsProperty) {
		GoodsProperty g = goodsPropertyDao.getGoodsPropertyId(goodsProperty
				.getGoodsPropertyId());
		if (g == null) {
			return false;
		} else {
			return goodsPropertyDao.update(goodsProperty);
		}
	}

	public GoodsProperty getGoodsPropertyId(Integer id) {
		return goodsPropertyDao.getGoodsPropertyId(id);
	}

	public List<GoodsProperty> getAll() {
		return goodsPropertyDao.getAll();
	}

	public List<GoodsProperty> getShuxinName(String shuxinName) {
		return goodsPropertyDao.getShuxinName(shuxinName);
	}

	public boolean addGoodsProperty(GoodsPropertyForm goodsPropertyForm) {
		GoodsProperty goodsProperty=new GoodsProperty();
		goodsProperty.setGoodsPropertyName(goodsPropertyForm.getGoodsPropertyName());
		goodsProperty.setGoodsPropertyRemark(goodsPropertyForm.getGoodsPropertyRemark());
		goodsProperty.setGoodsPropertyDefinedOne("1");
		goodsProperty.setGoodsPropertyDefinedTwo(goodsPropertyForm.getGoodsPropertyDefinedTwo());
		boolean ok=this.save(goodsProperty);	
		return ok;
	}

	public boolean updateGoodsProperty(GoodsPropertyForm goodsPropertyForm) {
		GoodsProperty g=goodsPropertyDao.getGoodsPropertyId(goodsPropertyForm.getGoodsPropertyId());
		g.setGoodsPropertyRemark(goodsPropertyForm.getGoodsPropertyRemark());
		g.setGoodsPropertyName(goodsPropertyForm.getGoodsPropertyName());
		g.setGoodsPropertyDefinedTwo(goodsPropertyForm.getGoodsPropertyDefinedTwo());
		boolean ok=this.update(g);
		return ok;
	}

}
