package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.entity.Tarehouse;
import com.xinggang.project.service.TarehouseService;

/**
 * 库位Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class TarehouseServiceImpl implements TarehouseService {
	private TarehouseDao tarehouseDao;

	public void setTarehouseDao(TarehouseDao tarehouseDao) {
		this.tarehouseDao = tarehouseDao;
	}

	// 增
	public boolean save(Tarehouse tarehouse) {

		return tarehouseDao.save(tarehouse);
	}

	// 删除，此项目中不能删除
	public boolean delete(Tarehouse tarehouse) {

		return false;
	}

	// 修改
	public boolean update(Tarehouse tarehouse) {
		Tarehouse t = tarehouseDao.getTarehouseId(tarehouse.getTarehouseId());
		if (t == null) {
			return false;
		}
		return tarehouseDao.update(tarehouse);
	}

	// 通过id查询
	public Tarehouse getTarehouseId(Integer id) {

		return tarehouseDao.getTarehouseId(id);
	}

	// 查询全部
	public List<Tarehouse> getAll() {

		return tarehouseDao.getAll();
	}

	// 通过库位名称查询
	public List<Tarehouse> getKuName(String kuname) {

		return tarehouseDao.getKuName(kuname);
	}

	// 通过库房查询
	public List<Tarehouse> getKufang(Integer kufang) {

		return tarehouseDao.getKufang(kufang);
	}

	public List<Tarehouse> getKuNameMoHu(String kuname) {
		return this.tarehouseDao.getKuNameMoHu(kuname);
	}
}
