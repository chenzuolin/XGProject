package com.xinggang.project.serviceImpl;

import java.util.List;
import com.xinggang.project.dao.GoodsQualityDao;
import com.xinggang.project.entity.GoodsQuality;
import com.xinggang.project.form.GoodsQualityForm;
import com.xinggang.project.service.GoodsQualityService;

public class GoodsQualityServiceImpl implements GoodsQualityService {

	private GoodsQualityDao goodsQualityDao;
	public void setGoodsQualityDao(GoodsQualityDao goodsQualityDao) {
		this.goodsQualityDao = goodsQualityDao;
	}
	
	public boolean save(GoodsQuality goodsQuality) {
		List<GoodsQuality> list = goodsQualityDao.getCaizhiName(goodsQuality
				.getGoodsQualityName());
		if (list.size() <= 0) {
			return goodsQualityDao.save(goodsQuality);
		} else {
			return false;
		}
	}

	public boolean delete(GoodsQuality goodsQuality) {
		GoodsQuality g = goodsQualityDao.getGoodsQualityId(goodsQuality
				.getGoodsQualityId());
		if (g == null) {
			return false;
		} else {
			return goodsQualityDao.delete(goodsQuality);
		}
	}

	public boolean update(GoodsQuality goodsQuality) {
		GoodsQuality g = goodsQualityDao.getGoodsQualityId(goodsQuality
				.getGoodsQualityId());
		if (g == null) {
			return false;
		} else {
			return goodsQualityDao.update(goodsQuality);
		}
	}

	public GoodsQuality getGoodsQualityId(Integer id) {
		return goodsQualityDao.getGoodsQualityId(id);
	}

	public List<GoodsQuality> getAll() {
		return goodsQualityDao.getAll();
	}

	public List<GoodsQuality> getCaizhiName(String caizhiName) {
		return goodsQualityDao.getCaizhiName(caizhiName);
	}
	//增加
	public boolean addGoodsQuality(GoodsQualityForm goodsQualityForm) {
		
		GoodsQuality goodsQuality=new GoodsQuality();
		goodsQuality.setGoodsQualityName(goodsQualityForm.getGoodsQualityName());
		goodsQuality.setGoodsQualityRemark(goodsQualityForm.getGoodsQualityRemark());
		goodsQuality.setGoodsQualityDefinedOne("1");
		goodsQuality.setGoodsQualityDefinedTwo(goodsQualityForm.getGoodsQualityDefinedTwo());
		boolean ok= this.save(goodsQuality);
		return ok;
		
	}
	//修改
	public boolean updateGoodsQuality(GoodsQualityForm gForm) {
		GoodsQuality goodsQuality =this.getGoodsQualityId(gForm.getGoodsQualityId());
		goodsQuality.setGoodsQualityName(gForm.getGoodsQualityName());
		goodsQuality.setGoodsQualityRemark(gForm.getGoodsQualityRemark());
		goodsQuality.setGoodsQualityDefinedTwo(gForm.getGoodsQualityDefinedTwo());
		boolean ok=this.update(goodsQuality);
		return ok;
	}

}
