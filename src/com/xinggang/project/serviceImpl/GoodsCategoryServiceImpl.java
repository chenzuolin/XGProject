package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.GoodsCategoryDao;
import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.service.GoodsCategoryService;

/**
 * 使用系统的公司内部人员职责Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	private GoodsCategoryDao goodsCategoryDao;

	public void setGoodsCategoryDao(GoodsCategoryDao goodsCategoryDao) {
		this.goodsCategoryDao = goodsCategoryDao;
	}
	// 增加
	public boolean save(GoodsCategory goodsCategory) {
		GoodsCategory goodsc = goodsCategoryDao
				.getGoodsCategoryId(goodsCategory.getGoodsCategoryId());		
		if (goodsc == null) {// 判断该对象是否存在，如果存在，则不需要添加，*/
			return goodsCategoryDao.save(goodsCategory);
		} else {
			return false;
		}
	}

	// 修改
	public boolean update(GoodsCategory goodsCategory) {
		GoodsCategory goodsc = goodsCategoryDao
				.getGoodsCategoryId(goodsCategory.getGoodsCategoryId());
		if (goodsc != null) {// 判断该对象是否存在，如果存在，则去修改，
			return goodsCategoryDao.update(goodsCategory);
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(GoodsCategory goodsCategory) {
		GoodsCategory goodsc = goodsCategoryDao
				.getGoodsCategoryId(goodsCategory.getGoodsCategoryId());
		if (goodsc != null) {// 判断该对象是否存在，如果存在，则去删除，
			return goodsCategoryDao.delete(goodsCategory);
		} else {
			return false;
		}
	}

	// 通过id查询
	public GoodsCategory getGoodsCategoryId(Integer id) {

		return goodsCategoryDao.getGoodsCategoryId(id);
	}

	// 查询全部
	public List<GoodsCategory> getAll() {

		return goodsCategoryDao.getAll();
	}
	
	//根据货物品类查询
	public List<GoodsCategory> getName(String name){
		return goodsCategoryDao.getName(name);
	};

}
