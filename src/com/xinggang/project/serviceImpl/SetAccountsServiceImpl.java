package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.SetAccountsDao;
import com.xinggang.project.entity.SetAccounts;
import com.xinggang.project.form.SetAccountsForm;
import com.xinggang.project.service.SetAccountsService;
import com.xinggang.project.tools.PresentTime;

/**
 * 滞纳金设置Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class SetAccountsServiceImpl implements SetAccountsService {
	private SetAccountsDao setAccountsDao;

	public void setSetAccountsDao(SetAccountsDao setAccountsDao) {
		this.setAccountsDao = setAccountsDao;
	}

	// 获取当前时间工具类
	PresentTime pt = new PresentTime();

	// 添加
	public boolean save(SetAccountsForm sAccountsForm) {
		// 创建滞纳金设置类
		SetAccounts sAccounts = new SetAccounts();
		sAccounts.setSaday(sAccountsForm.getSaday()); // 添加轧账号数
		sAccounts.setSarate(sAccountsForm.getSarate());// 设置滞纳金费率
		sAccounts.setSatime(pt.getTimes());// 添加设置的时间
		sAccounts.setSaremark(sAccountsForm.getSaremark());// 添加备注

		return setAccountsDao.save(sAccounts);
	}

	// 删除，不可删除
	public boolean delete(SetAccountsForm sAccountsForm) {
		SetAccounts s = setAccountsDao
				.getSetAccountsId(sAccountsForm.getSaid());
		if (s == null) {
			return false;
		}
		return setAccountsDao.delete(s);
	}

	// 修改
	public boolean update(SetAccountsForm sAccountsForm) {
		SetAccounts sAccounts = setAccountsDao.getSetAccountsId(sAccountsForm
				.getSaid());
		if (sAccounts == null) {
			return false;
		}
		// 创建滞纳金设置类
		sAccounts.setSaday(sAccountsForm.getSaday()); // 获取轧账号数
		sAccounts.setSarate(sAccountsForm.getSarate());// 获取滞纳金费率
		sAccounts.setSatime(pt.getTimes());// 添加修改的时间
		sAccounts.setSaremark(sAccountsForm.getSaremark());// 添加备注
		return setAccountsDao.update(sAccounts);
	}

	// 通过id查询
	public SetAccounts getSetAccountsId(Integer id) {

		return setAccountsDao.getSetAccountsId(id);
	}

	// 查询全部
	public List<SetAccounts> getAll() {

		return setAccountsDao.getAll();
	}

}
