package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.PaymentFashion;

/**
 * 支付方式Dao
 * 
 * @author Administrator
 * 
 */
public interface PaymentFashionDao {
	// 增
	public boolean save(PaymentFashion paymentFashion);

	// 删，此项目中不可删除
	public boolean delete(PaymentFashion paymentFashion);

	// 改
	public boolean update(PaymentFashion paymentFashion);

	// 通过id查询
	public PaymentFashion getPaymentFashionId(Integer id);

	// 查询全部
	public List<PaymentFashion> getAll();

	// 通过支付方式名称查询
	public List<PaymentFashion> getMingcheng(String mingcheng);

	// 通过发票类型查询
	public List<PaymentFashion> getLeixing(String leixing);
}
