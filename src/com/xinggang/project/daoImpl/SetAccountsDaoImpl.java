package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.SetAccountsDao;
import com.xinggang.project.entity.SetAccounts;

/**
 * 滞纳金Dao实现类
 * 
 * @author Administrator
 * 
 */
public class SetAccountsDaoImpl extends BaseDaoImpl implements SetAccountsDao {
	// 增加
	public boolean save(SetAccounts setAccounts) {
		return super.BaseSave(setAccounts);
	}

	// 删除
	public boolean delete(SetAccounts setAccounts) {
		return super.BaseDelete(setAccounts);
	}

	// 修改
	public boolean update(SetAccounts setAccounts) {
		return super.BaseUpdate(setAccounts);
	}

	// 通过id查询
	public SetAccounts getSetAccountsId(Integer id) {
		String hql = "from SetAccounts said=" + id;
		return (SetAccounts) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<SetAccounts> getAll() {
		String hql = "from SetAccounts order by said asc";
		return (List<SetAccounts>) super.executeQuery(hql, null);
	}

	public void getTest() {
		// String hql =
		// "select exportId from Export where exportDefinedTwo like ('2') union  select inputId from Input";
		/*
		 * List<String> slist = (List<String>) super.executeQuery(hql, null);
		 * for(int i=0; i<slist.size(); i++){ System.out.println(slist.get(i));
		 * }
		 */
	}
}
