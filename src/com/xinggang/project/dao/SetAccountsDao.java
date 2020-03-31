package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.SetAccounts;

/**
 * 滞纳金设置Dao
 * 
 * @author Administrator
 * 
 */
public interface SetAccountsDao {
	// 增加
	public boolean save(SetAccounts setAccounts);

	// 删除
	public boolean delete(SetAccounts setAccounts);

	// 修改
	public boolean update(SetAccounts setAccounts);

	// 通过id查询
	public SetAccounts getSetAccountsId(Integer id);

	// 查询全部
	public List<SetAccounts> getAll();
	
	
	public void getTest();
}
