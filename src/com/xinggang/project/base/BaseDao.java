package com.xinggang.project.base;

import java.util.List;

/**
 * 所有Dao的父类接口
 * 
 * @author Administrator
 * 
 */

public interface BaseDao {
	// 通过Id获取一个对象,Id类型为Integer
	public Object findById(String hql);

	// 通过Id获取一个对象,Id类型为String
	public Object findById(Class<?> c, String id);

	// 通过hql语句查询
	public List<?> executeQuery(String hql, Object[] parameters);

	// 分页查询
	public List<?> executeQueryByPage(String hql, Object[] parameters,
			int pageNow, int rowSize);// pageNow 当前页从0开始，pagesSize显示的行数

	// 返回数据的行数
	public int executeQueryRowCount(String hql, Object[] parameters);

	// 添加一个对象
	public boolean BaseSave(Object obj);

	// 通过对象删除一个对象
	public boolean BaseDelete(Object obj);

	// 修改一个对象，
	public boolean BaseUpdate(Object obj);
	
	
	//返回分页行数---------------------------修改后
	public int queryPageCount(String hql, Object[] parameters, int pageSize);
	
	//通过sql语句查询
	public List<?> QuerySql(String sql);

}
