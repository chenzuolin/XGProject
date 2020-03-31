package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.CostTypeDao;
import com.xinggang.project.entity.CostType;
import com.xinggang.project.service.CostTypeService;

@Transactional
public class CostTypeServiceImpl implements CostTypeService {
	// 费用类型service
	private CostTypeDao costTypeDao;

	public void setCostTypeDao(CostTypeDao costTypeDao) {
		this.costTypeDao = costTypeDao;
	}

	// 增加
	public boolean save(CostType costType) {

		return this.costTypeDao.save(costType);
	}

	// 删除
	public boolean delete(CostType costType) {

		return this.costTypeDao.delete(costType);
	}

	// 修改
	public boolean update(CostType costType) {

		return this.costTypeDao.update(costType);
	}

	// 通过编号查询
	public CostType getCostTypeId(Integer id) {

		return this.costTypeDao.getCostTypeId(id);
	}

	// 查询全部
	public List<CostType> getAll() {

		return this.costTypeDao.getAll();
	}

	// 通过费用类型的名称查询
	public List<CostType> getCostTypeName(String ctName) {

		return this.costTypeDao.getCostTypeName(ctName);
	}

	// 查询入库费,火运和汽运

	public double getHuoYunRuKu() {
		List<CostType> ctlist = this.costTypeDao.getCostTypeName("火运入库费用");
		double ruku = 1;
		if (ctlist.size() > 0) {
			ruku = ctlist.get(0).getCtypeCost();
		}

		return ruku;
	}

	public double getQiYunRuKu() {
		List<CostType> ctlist = this.costTypeDao.getCostTypeName("汽运入库费用");
		double ruku = 1;
		if (ctlist.size() > 0) {
			ruku = ctlist.get(0).getCtypeCost();
		}
		return ruku;
	}

	// 查询出库费
	public double getHuoYunChuKu() {
		List<CostType> ctlist = this.costTypeDao.getCostTypeName("火运出库费用");
		double chuku = 1;
		if (ctlist.size() > 0) {
			chuku = ctlist.get(0).getCtypeCost();
		}
		return chuku;
	}

	public double getQiYunChuKu() {
		List<CostType> ctlist = this.costTypeDao.getCostTypeName("汽运出库费用");
		double chuku = 1;
		if (ctlist.size() > 0) {
			chuku = ctlist.get(0).getCtypeCost();
		}
		return chuku;
	}

	// 查询过户费
	public double getGuoHu() {
		double guohu = this.costTypeDao.getCostTypeName("过户费用").get(0)
				.getCtypeCost();
		return guohu;
	}

	// 查询二次作业费
	public double getErCi() {
		double erci = this.costTypeDao.getCostTypeName("二次作业费用").get(0)
				.getCtypeCost();
		return erci;
	}

	// 查询仓储费
	public double getCangChu() {
		double cangchu = this.costTypeDao.getCostTypeName("仓储费用").size() <= 0 ? 1.0
				: this.costTypeDao.getCostTypeName("仓储费用").get(0)
						.getCtypeCost();
		return cangchu;
	}

}
