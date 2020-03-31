package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.TarehouseDetailOperateDao;
import com.xinggang.project.entity.TarehouseDetailOperate;
import com.xinggang.project.service.TarehouseDetailOperateService;

/**
 * 货物批次操作service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class TarehouseDetailOperateServiceImpl implements
		TarehouseDetailOperateService {
	private TarehouseDetailOperateDao tarehouseDetailOperateDao;

	public void setTarehouseDetailOperateDao(
			TarehouseDetailOperateDao tarehouseDetailOperateDao) {
		this.tarehouseDetailOperateDao = tarehouseDetailOperateDao;
	}

	// 增加
	public boolean save(TarehouseDetailOperate tarehouseDetailOperate) {

		return tarehouseDetailOperateDao.save(tarehouseDetailOperate);
	}

	// 删除
	public boolean delete(TarehouseDetailOperate tarehouseDetailOperate) {
		TarehouseDetailOperate t = tarehouseDetailOperateDao
				.getTarehouseDetailOperateId(tarehouseDetailOperate
						.getTdoperateId());
		if (t == null) {
			return false;
		}
		return tarehouseDetailOperateDao.delete(tarehouseDetailOperate);
	}

	// 修改
	public boolean update(TarehouseDetailOperate tarehouseDetailOperate) {
		TarehouseDetailOperate t = tarehouseDetailOperateDao
				.getTarehouseDetailOperateId(tarehouseDetailOperate
						.getTdoperateId());
		if (t == null) {
			return false;
		}
		return tarehouseDetailOperateDao.update(tarehouseDetailOperate);
	}

	// 通过id查询
	public TarehouseDetailOperate getTarehouseDetailOperateId(String id) {

		return tarehouseDetailOperateDao.getTarehouseDetailOperateId(id);
	}

	// 查询全部
	public List<TarehouseDetailOperate> getAll() {

		return tarehouseDetailOperateDao.getAll();
	}

}
