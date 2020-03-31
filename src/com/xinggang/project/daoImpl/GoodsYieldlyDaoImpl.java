package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsYieldlyDao;
import com.xinggang.project.entity.GoodsYieldly;

/**
 * 货物产地接口实现类
 * 
 * @author Administrator
 * 
 */
public class GoodsYieldlyDaoImpl extends BaseDaoImpl implements GoodsYieldlyDao {
	// 增
	public boolean save(GoodsYieldly goodsYieldly) {
		return super.BaseSave(goodsYieldly);
	}

	// 删除
	public boolean delete(GoodsYieldly goodsYieldly) {
		return super.BaseDelete(goodsYieldly);
	}

	// 修改
	public boolean update(GoodsYieldly goodsYieldly) {
		
		return super.BaseUpdate(goodsYieldly);
	}

	// 通过id查询
	public GoodsYieldly getGoodsYieldlyId(Integer id) {
		String hql="from GoodsYieldly where goodsYieldlyId="+id;
		return (GoodsYieldly) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<GoodsYieldly> getAll() {
		String hql = "from GoodsYieldly where goodsYieldlyDefinedOne!='0'";
		return (List<GoodsYieldly>) super.executeQuery(hql, null);
	}

	// 通过产地名称查询
	@SuppressWarnings("unchecked")
	public List<GoodsYieldly> getChandiName(String chandiName) {
		String hql = "from GoodsYieldly where goodsYieldlyDefinedOne!='0' and goodsYieldlyName='"
				+ chandiName+"'";
		return (List<GoodsYieldly>) super.executeQuery(hql, null);
	}

}
