package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.GoodsYieldlyDao;
import com.xinggang.project.entity.GoodsYieldly;
import com.xinggang.project.form.GoodsYieldlyForm;
import com.xinggang.project.service.GoodsYieldlyService;

/**
 * 货物产地Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class GoodsYieldlyServiceImpl implements GoodsYieldlyService {
	
	private GoodsYieldlyDao goodsYieldlyDao;
	public void setGoodsYieldlyDao(GoodsYieldlyDao goodsYieldlyDao) {
		this.goodsYieldlyDao = goodsYieldlyDao;
	}
	// 增加
	public boolean save(GoodsYieldly goodsYieldly) {
		List<GoodsYieldly> list = goodsYieldlyDao.getChandiName(goodsYieldly
				.getGoodsYieldlyName());
		if (list.size() <= 0) {
			return goodsYieldlyDao.save(goodsYieldly);
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(GoodsYieldly goodsYieldly) {
		GoodsYieldly g = goodsYieldlyDao.getGoodsYieldlyId(goodsYieldly
				.getGoodsYieldlyId());
		if (g == null) {
			return false;
		} else {
			return goodsYieldlyDao.delete(goodsYieldly);
		}
	}
	// 修改
	public boolean update(GoodsYieldly goodsYieldly) {
		GoodsYieldly g = goodsYieldlyDao.getGoodsYieldlyId(goodsYieldly
				.getGoodsYieldlyId());
		if (g == null) {
			return false;
		} else {
			return goodsYieldlyDao.update(goodsYieldly);
		}
	}
	// 通过id查询
	public GoodsYieldly getGoodsYieldlyId(Integer id) {
		return goodsYieldlyDao.getGoodsYieldlyId(id);
	}
	// 查询全部
	public List<GoodsYieldly> getAll() {
		return goodsYieldlyDao.getAll();
	}
	//根据货物产地查询
	public List<GoodsYieldly> getChandiName(String chandiName) {
		return goodsYieldlyDao.getChandiName(chandiName);
	}

	//修改货物产地
	public boolean updateGoodsYieldly(GoodsYieldlyForm goodsYieldlyForm) {
		
		GoodsYieldly goodsYieldly=this.goodsYieldlyDao.getGoodsYieldlyId(goodsYieldlyForm.getGoodsYieldlyId());
		goodsYieldly.setGoodsYieldlyId(goodsYieldlyForm.getGoodsYieldlyId());
		goodsYieldly.setGoodsYieldlyName(goodsYieldlyForm.getGoodsYieldlyName());
		goodsYieldly.setGoodsYieldlyDefinedTwo(goodsYieldlyForm.getGoodsYieldlyDefinedTwo());
		goodsYieldly.setGoodsYieldlyRemark(goodsYieldlyForm.getGoodsYieldlyRemark());
		boolean ok=this.update(goodsYieldly);
		return ok;
	}

}
