package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.PaymentFashionDao;
import com.xinggang.project.entity.PaymentFashion;
import com.xinggang.project.service.PaymentFashionService;

/**
 * 支付方式Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class PaymentFashionServiceImpl implements PaymentFashionService {
	private PaymentFashionDao paymentFashionDao;

	public void setPaymentFashionDao(PaymentFashionDao paymentFashionDao) {
		this.paymentFashionDao = paymentFashionDao;
	}

	// 增加
	public boolean save(PaymentFashion paymentFashion) {

		return paymentFashionDao.save(paymentFashion);
	}

	// 删除，此项目中不能删除
	public boolean delete(PaymentFashion paymentFashion) {

		return false;
	}

	// 修改
	public boolean update(PaymentFashion paymentFashion) {
		PaymentFashion p = paymentFashionDao.getPaymentFashionId(paymentFashion
				.getPfashionId());
		if (p == null) {
			return false;
		}
		return paymentFashionDao.update(paymentFashion);
	}

	// 通过id查询
	public PaymentFashion getPaymentFashionId(Integer id) {

		return paymentFashionDao.getPaymentFashionId(id);
	}

	// 查询全部
	public List<PaymentFashion> getAll() {

		return paymentFashionDao.getAll();
	}

	// 通过支付方式名称查询
	public List<PaymentFashion> getMingcheng(String mingcheng) {

		return paymentFashionDao.getMingcheng(mingcheng);
	}

	// 通过发票类型查询
	public List<PaymentFashion> getLeixing(String leixing) {

		return paymentFashionDao.getLeixing(leixing);
	}

}
