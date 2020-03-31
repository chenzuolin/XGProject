package com.xinggang.project.serviceImpl;

import java.util.List;
import com.xinggang.project.dao.GoodsStandardDao;
import com.xinggang.project.entity.GoodsStandard;
import com.xinggang.project.form.GoodsStandardForm;
import com.xinggang.project.service.GoodsStandardService;

public class GoodsStandardServiceImpl implements GoodsStandardService {

	private GoodsStandardDao goodsStandardDao;
	public void setGoodsStandardDao(GoodsStandardDao goodsStandardDao) {
		this.goodsStandardDao = goodsStandardDao;
	}
	
	public boolean save(GoodsStandard goodsStandard) {
		List<GoodsStandard> list = goodsStandardDao.getGuigeName(goodsStandard
				.getGoodsStandardName());
		if (list.size() <= 0) {
			return goodsStandardDao.save(goodsStandard);
		} else {
			return false;
		}
	}

	public boolean delete(GoodsStandard goodsStandard) {
		GoodsStandard g = goodsStandardDao.getGoodsStandardId(goodsStandard
				.getGoodsStandardId());
		if (g == null) {
			return false;
		} else {
			return goodsStandardDao.delete(goodsStandard);
		}
	}

	public boolean update(GoodsStandard goodsStandard) {
		GoodsStandard g = goodsStandardDao.getGoodsStandardId(goodsStandard
				.getGoodsStandardId());
		if (g == null) {
			return false;
		} else {
			return goodsStandardDao.update(goodsStandard);
		}
	}

	public GoodsStandard getGoodsStandardId(Integer id) {
		return goodsStandardDao.getGoodsStandardId(id);
	}

	public List<GoodsStandard> getAll() {
		return goodsStandardDao.getAll();
	}

	public List<GoodsStandard> getGuigeName(String guigeName) {
		return goodsStandardDao.getGuigeName(guigeName);
	}

	public boolean addGoodsStandard(GoodsStandardForm goodsStandardForm) {
		GoodsStandard goodsStandard=new GoodsStandard();
		goodsStandard.setGoodsStandardName(goodsStandardForm.getGoodsStandardName());
		goodsStandard.setGoodsStandardRemark(goodsStandardForm.getGoodsStandardRemark());
		goodsStandard.setGoodsStandardDefinedOne("1");
		goodsStandard.setGoodsStandardDefinedTwo(goodsStandardForm.getGoodsStandardDefinedTwo());
		boolean ok=this.save(goodsStandard);
		return ok;
	}

	public boolean updateGoodsStandard(GoodsStandardForm goodsStandardForm) {
		GoodsStandard goodsStandard=this.getGoodsStandardId(goodsStandardForm.getGoodsStandardId());
		goodsStandard.setGoodsStandardName(goodsStandardForm.getGoodsStandardName());
		goodsStandard.setGoodsStandardRemark(goodsStandardForm.getGoodsStandardRemark());
		goodsStandard.setGoodsStandardDefinedTwo(goodsStandardForm.getGoodsStandardDefinedTwo());
		boolean ok=this.update(goodsStandard);
		return ok;
	}

}
