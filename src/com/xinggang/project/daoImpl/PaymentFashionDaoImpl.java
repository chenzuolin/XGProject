package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.PaymentFashionDao;
import com.xinggang.project.entity.PaymentFashion;

/**
 * 支付方式Dao实现类
 * 
 * @author Administrator
 * 
 */
public class PaymentFashionDaoImpl extends BaseDaoImpl implements
		PaymentFashionDao {
	// 增
	public boolean save(PaymentFashion paymentFashion) {
		return super.BaseSave(paymentFashion);
	}

	// 删
	public boolean delete(PaymentFashion paymentFashion) {
		return super.BaseDelete(paymentFashion);
	}

	// 改
	public boolean update(PaymentFashion paymentFashion) {
		return super.BaseUpdate(paymentFashion);
	}

	// 通过id查询
	public PaymentFashion getPaymentFashionId(Integer id) {
		String hql="from PaymentFashion where pfashionId="+id;
		return (PaymentFashion) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<PaymentFashion> getAll() {
		String hql = "from PaymentFashion";
		return (List<PaymentFashion>) super.executeQuery(hql, null);
	}

	// 通过支付方式名称查询
	@SuppressWarnings("unchecked")
	public List<PaymentFashion> getMingcheng(String mingcheng) {
		String hql = "from PaymentFashion where pfashionName='" + mingcheng
				+ "'";
		return (List<PaymentFashion>) super.executeQuery(hql, null);
	}

	// 通过发票类型查询
	@SuppressWarnings("unchecked")
	public List<PaymentFashion> getLeixing(String leixing) {
		String hql = "from PaymentFashion where pfashionReceipt='" + leixing
				+ "'";
		return (List<PaymentFashion>) super.executeQuery(hql, null);
	}

}
