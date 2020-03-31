package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.TarehouseDetailOperateDao;
import com.xinggang.project.entity.TarehouseDetailOperate;

/**
 * 货物批次操作Dao实现类
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailOperateDaoImpl extends BaseDaoImpl implements
		TarehouseDetailOperateDao {
	// 增加
	public boolean save(TarehouseDetailOperate tarehouseDetailOperate) {
		return super.BaseSave(tarehouseDetailOperate);
	}

	// 删除
	public boolean delete(TarehouseDetailOperate tarehouseDetailOperate) {
		return super.BaseDelete(tarehouseDetailOperate);
	}

	// 修改
	public boolean update(TarehouseDetailOperate tarehouseDetailOperate) {
		return super.BaseUpdate(tarehouseDetailOperate);
	}

	// 通过ID查询
	public TarehouseDetailOperate getTarehouseDetailOperateId(String id) {
		return (TarehouseDetailOperate) super.findById(
				TarehouseDetailOperate.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<TarehouseDetailOperate> getAll() {
		String hql = "from TarehouseDetailOperate";
		return (List<TarehouseDetailOperate>) super.executeQuery(hql, null);
	}

}
