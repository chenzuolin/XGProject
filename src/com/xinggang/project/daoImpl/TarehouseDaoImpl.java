package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.entity.Tarehouse;

/**
 * 库位Dao实现类
 * 
 * @author Administrator
 * 
 */
public class TarehouseDaoImpl extends BaseDaoImpl implements TarehouseDao {
	// 增
	public boolean save(Tarehouse tarehouse) {
		return super.BaseSave(tarehouse);
	}

	// 删
	public boolean delete(Tarehouse tarehouse) {
		return super.BaseDelete(tarehouse);
	}

	// 改
	public boolean update(Tarehouse tarehouse) {
		return super.BaseUpdate(tarehouse);
	}

	// 通过id查询
	public Tarehouse getTarehouseId(Integer id) {
		String hql = "from Tarehouse where tarehouseId=" + id;
		return (Tarehouse) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Tarehouse> getAll() {
		String hql = "from Tarehouse";
		return (List<Tarehouse>) super.executeQuery(hql, null);
	}

	// 通过库位名称查询
	@SuppressWarnings("unchecked")
	public List<Tarehouse> getKuName(String kuname) {
		String hql = "from Tarehouse where tarehouseName like ('" + kuname
				+ "')";
		return (List<Tarehouse>) super.executeQuery(hql, null);
	}

	// 通过库房查询
	@SuppressWarnings("unchecked")
	public List<Tarehouse> getKufang(Integer kufang) {
		String hql = "from Tarehouse where bursary=" + kufang;
		return (List<Tarehouse>) super.executeQuery(hql, null);
	}

	// 通过库位名称模糊查询
	@SuppressWarnings("unchecked")
	public List<Tarehouse> getKuNameMoHu(String kuname) {
		String hql = "from Tarehouse where tarehouseName like ('%" + kuname
				+ "%') order by tarehouseId asc";
		return (List<Tarehouse>) super.executeQuery(hql, null);
	}
	
	
}
