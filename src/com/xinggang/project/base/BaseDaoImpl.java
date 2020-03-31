package com.xinggang.project.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 所有Dao的父类接口实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public abstract class BaseDaoImpl implements BaseDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 通过id查询，id为int类型-----------有问题
	public Object findById(String hql) {
		return this.sessionFactory.getCurrentSession().createQuery(hql)
				.uniqueResult();
	}
	// 通过id查询，id为string类型
	public Object findById(Class<?> c, String id) {
		return this.sessionFactory.getCurrentSession().get(c, id);
	}

	// 用hql语句执行查询
	public List<?> executeQuery(String hql, Object[] parameters) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return query.list();
	}

	// 分页的查询-------------------
	public List<?> executeQueryByPage(String hql, Object[] parameters,
			int pageNow, int rowSize) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}

		query.setFirstResult((pageNow - 1) * rowSize).setMaxResults(rowSize);
		return query.list();
	}

	// 返回一个对象的总记录行数
	public int executeQueryRowCount(String hql, Object[] parameters) {

		List<?> list = this.executeQuery(hql, parameters);
		int count =Integer.parseInt(list.get(0).toString());

		return count;
		
	}


	// 返回分页页数--------------------------------修改后
	public int queryPageCount(String hql, Object[] parameters, int pageSize) {
		
		int rowCount = this.executeQueryRowCount(hql, parameters);
		return (rowCount - 1) / pageSize + 1;

	}

	// 添加一个对象
	public boolean BaseSave(Object obj) {
		try {
			this.sessionFactory.getCurrentSession().save(obj);
			return true;
		} catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
	}

	// 删除一个对象
	public boolean BaseDelete(Object obj) {
		try {
			this.sessionFactory.getCurrentSession().delete(obj);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	// 修改一个对象
	public boolean BaseUpdate(Object obj) {
		try {
			this.sessionFactory.getCurrentSession().update(obj);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	
	public List<?> QuerySql(String sql){
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

}
