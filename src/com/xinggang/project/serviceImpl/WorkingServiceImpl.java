package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClassTDao;
import com.xinggang.project.dao.WorkingDao;
import com.xinggang.project.entity.Working;
import com.xinggang.project.form.WorkingForm;
import com.xinggang.project.service.WorkingService;

/**
 * 作业人员Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class WorkingServiceImpl implements WorkingService {
	private WorkingDao workingDao;
	// 班组dao
	@SuppressWarnings("unused")
	private ClassTDao classTDao;

	public void setClassTDao(ClassTDao classTDao) {
		this.classTDao = classTDao;
	}

	public void setWorkingDao(WorkingDao workingDao) {
		this.workingDao = workingDao;
	}

	// 增加
	public boolean save(Working working) {

		return workingDao.save(working);
	}

	// 删除
	public boolean delete(Working working) {
		Working w = workingDao.getWorkingId(working.getWorkingId());
		if (w == null) {
			return false;
		}
		return workingDao.delete(working);
	}

	// 修改
	public boolean update(Working working) {
		Working w = workingDao.getWorkingId(working.getWorkingId());
		if (w == null) {
			return false;
		}
		return workingDao.update(working);
	}

	// 通过id查询
	public Working getWorkingId(Integer id) {

		return workingDao.getWorkingId(id);
	}

	// 查询全部
	public List<Working> getAll() {

		return workingDao.getAll();
	}

	// 通过名字模糊分页查询
	public List<Working> getWorkingByPage(String name, int pageNow, int rowSize) {

		return workingDao.getWorkingByPage(name, pageNow, rowSize);
	}

	// 通过名字模糊查询数据总行数
	public int getWorkingByCount(String name) {

		return workingDao.getWorkingByCount(name);
	}

	// 通过工种查询
	public List<Working> getWorkingKind(String kind) {

		return workingDao.getWorkingKind(kind);
	}

	// 通过班组查询
	public List<Working> getWorkingClass(Integer classId) {

		return workingDao.getWorkingClass(classId);
	}

	// 添加作业人员
	public boolean saveWorking(WorkingForm wf) {
		Working working = new Working();
		if (this.workingDao.getWorkingName(wf.getWorkingName()).size() <= 0) {
			working.setWorkingName(wf.getWorkingName());// 添加作业人员姓名,在程序中自动将作业人员的姓名唯一化，统计工作量
			/*
			 * working.setWorkingClassId(this.classTDao.getClassTId(wf
			 * .getWorkingClassId()));// 添加班组
			 */
			working.setWorkingKindOfWork(wf.getWorkingKindOfWork());// 添加工种
			working.setWorkingDefinedOne(wf.getWorkingDefinedOne());// 添加联系电话
			working.setWorkingRemark(wf.getWorkingRemark());// 添加备注
			return this.workingDao.save(working);
		} else {
			return false;
		}
	}

	// 查询全部以分页显示
	public List<Working> getWorkingAllByPage(String name, String className,
			String gongzhong, int pageNow, int rowSize) {
		return this.workingDao.getWorkingAllByPage(name, className, gongzhong,
				pageNow, rowSize);
	}

	// 查询全部数据的页数
	public int getWorkingAllByPageCount(String name, String className,
			String gongzhong, int rowSize) {
		return this.workingDao.getWorkingAllByPageCount(name, className,
				gongzhong, rowSize);
	}

	// 通过用户的姓名查询
	public List<Working> getWorkingName(String name) {
		return this.workingDao.getWorkingName(name);
	}

	// 通过职务、起始日期、结束日期统计入库的工作量
	public Double RuKuWorkWeight(String begin, String finish, String zhiwu) {
		return this.workingDao.RuKuWorkWeight(begin, finish, zhiwu);
	}

	// 通过职务、起始日期、结束日期统计出库的工作量
	public Double ChuKuWorkWeight(String begin, String finish, String zhiwu) {
		return this.workingDao.ChuKuWorkWeight(begin, finish, zhiwu);
	}

	// 通过职务、起始日期、结束日期统计过户的工作量
	public Double GuoHuWorkWeight(String begin, String finish, String zhiwu) {
		return this.workingDao.GuoHuWorkWeight(begin, finish, zhiwu);
	}

	// 通过职务、起始日期、结束日期统计挪库的工作量
	public Double NuoKuWorkWeight(String begin, String finish, String zhiwu) {
		return this.workingDao.NuoKuWorkWeight(begin, finish, zhiwu);
	}

	// 通过职务、起始日期、结束日期统计短倒的工作量
	public Double DuanDaoWorkWeight(String begin, String finish, String zhiwu) {
		return this.workingDao.DuanDaoWorkWeight(begin, finish, zhiwu);
	}
}
