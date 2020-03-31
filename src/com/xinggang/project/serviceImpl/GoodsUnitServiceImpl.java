package com.xinggang.project.serviceImpl;

import java.util.List;
import com.xinggang.project.dao.GoodsUnitDao;
import com.xinggang.project.entity.GoodsUnit;
import com.xinggang.project.form.GoodsUnitForm;
import com.xinggang.project.service.GoodsUnitService;

public class GoodsUnitServiceImpl implements GoodsUnitService {
	private GoodsUnitDao goodsUnitDao;
	public void setGoodsUnitDao(GoodsUnitDao goodsUnitDao) {
		this.goodsUnitDao = goodsUnitDao;
	}

	public boolean save(GoodsUnit goodsUnit) {
		List<GoodsUnit> list = goodsUnitDao.getJiLiangName(goodsUnit.getGoodsUnitName());
		if (list.size() <= 0) {
			return goodsUnitDao.save(goodsUnit);
		} else {
			return false;
		}
	}

	public boolean delete(GoodsUnit goodsUnit) {
		GoodsUnit g = goodsUnitDao.getGoodsUnitId(goodsUnit
				.getGoodsUnitId());
		if (g == null) {
			return false;
		} else {
			return goodsUnitDao.delete(goodsUnit);
		}
	}

	public boolean update(GoodsUnit goodsUnit) {
		GoodsUnit g = goodsUnitDao.getGoodsUnitId(goodsUnit
				.getGoodsUnitId());
		if (g == null) {
			return false;
		} else {
			return goodsUnitDao.update(goodsUnit);
		}
	}

	public GoodsUnit getGoodsUnitId(Integer id) {
		return goodsUnitDao.getGoodsUnitId(id);
	}

	public List<GoodsUnit> getAll() {
		return goodsUnitDao.getAll();
	}

	public List<GoodsUnit> getJiLiangName(String JiLiangName) {
		return goodsUnitDao.getJiLiangName(JiLiangName);
	}

	public boolean addGoodsUnit(GoodsUnitForm goodsUnitForm) {
		
		GoodsUnit goodsUnit=new GoodsUnit();
		goodsUnit.setGoodsUnitName(goodsUnitForm.getGoodsUnitName());
		goodsUnit.setGoodsUnitRemark(goodsUnitForm.getGoodsUnitRemark());
		goodsUnit.setGoodsUnitDefinedOne("1");//是否停用
		goodsUnit.setGoodsUnitDefinedTwo(goodsUnitForm.getGoodsUnitDefinedTwo());//描述
		boolean ok=this.save(goodsUnit);
		return ok;
	}

	public boolean updateGoodsUnit(GoodsUnitForm goodsUnitForm) {
		GoodsUnit goodsUnit =this.getGoodsUnitId(goodsUnitForm.getGoodsUnitId());
		goodsUnit.setGoodsUnitName(goodsUnitForm.getGoodsUnitName());
		goodsUnit.setGoodsUnitRemark(goodsUnitForm.getGoodsUnitRemark());
		goodsUnit.setGoodsUnitDefinedTwo(goodsUnitForm.getGoodsUnitDefinedTwo());
		boolean ok=this.update(goodsUnit);
		return ok;
	}

}
