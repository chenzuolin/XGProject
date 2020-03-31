package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.SetAccounts;
import com.xinggang.project.form.SetAccountsForm;

/**
 * 滞纳金设置Service
 * 
 * @author Administrator
 * 
 */
public interface SetAccountsService {
	// 增加
	public boolean save(SetAccountsForm setAccountsForm);

	// 删除
	public boolean delete(SetAccountsForm setAccountsForm);

	// 修改
	public boolean update(SetAccountsForm setAccountsForm);

	// 通过id查询
	public SetAccounts getSetAccountsId(Integer id);

	// 查询全部
	public List<SetAccounts> getAll();
}
