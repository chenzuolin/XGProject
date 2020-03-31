package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Working;
import com.xinggang.project.form.WorkingForm;

/**
 * 作业人员Service
 * 
 * @author Administrator
 * 
 */
public interface WorkingService {
	// 增加
	public boolean save(Working working);

	// 删除
	public boolean delete(Working working);

	// 修改
	public boolean update(Working working);

	// 通过id查询
	public Working getWorkingId(Integer id);

	// 查询全部
	public List<Working> getAll();

	// 分页查询，通过姓名模糊查询
	public List<Working> getWorkingByPage(String name, int pageNow, int rowSize);

	// 查询数据总行数
	public int getWorkingByCount(String name);

	// 通过工种查询
	public List<Working> getWorkingKind(String kind);

	// 通过班组查询
	public List<Working> getWorkingClass(Integer classId);

	// 添加工作人员
	public boolean saveWorking(WorkingForm wf);

	// 以分页的方式查询所有的作业人员
	public List<Working> getWorkingAllByPage(String name, String className,
			String gongzhong, int pageNow, int rowSize);

	// 查询所有作业人员的总页数
	public int getWorkingAllByPageCount(String name, String className,
			String gongzhong, int rowSize);

	// 通过用户的姓名查询
	public List<Working> getWorkingName(String name);

	// 通过职务、起始日期、结束日期统计入库的工作量
	public Double RuKuWorkWeight(String begin, String finish, String zhiwu);

	// 通过职务、起始日期、结束日期统计出库的工作量
	public Double ChuKuWorkWeight(String begin, String finish, String zhiwu);

	// 通过职务、起始日期、结束日期统计过户的工作量
	public Double GuoHuWorkWeight(String begin, String finish, String zhiwu);

	// 通过职务、起始日期、结束日期统计挪库的工作量
	public Double NuoKuWorkWeight(String begin, String finish, String zhiwu);

	// 通过职务、起始日期、结束日期统计短倒的工作量
	public Double DuanDaoWorkWeight(String begin, String finish, String zhiwu);
}
