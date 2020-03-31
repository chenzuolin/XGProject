package com.xinggang.project.serviceImpl;

import java.util.List;

import com.xinggang.project.dao.XiazhanfeiDao;
import com.xinggang.project.entity.Xiazhanfei;
import com.xinggang.project.service.XiazhanfeiService;

public class XiazhanfeiServiceImpl implements XiazhanfeiService {
	private XiazhanfeiDao xiazhanfeiDao;

	public void setXiazhanfeiDao(XiazhanfeiDao xiazhanfeiDao) {
		this.xiazhanfeiDao = xiazhanfeiDao;
	}

	public boolean save(Xiazhanfei xiazhan) {

		return this.xiazhanfeiDao.save(xiazhan);
	}

	public boolean update(Xiazhanfei xiazhan) {

		return this.xiazhanfeiDao.update(xiazhan);
	}

	public boolean delete(Xiazhanfei xiazhan) {

		return this.xiazhanfeiDao.delete(xiazhan);
	}

	public Xiazhanfei getXiazhangfei(Integer id) {

		return this.xiazhanfeiDao.getXiazhangfei(id);
	}

	// 根据总订单id查询
	public List<Xiazhanfei> getAllzongId(String zongId) {

		return this.xiazhanfeiDao.getAllzongId(zongId);

	};

	public List<Xiazhanfei> getAll() {

		return this.xiazhanfeiDao.getAll();
	}

	// 通过下站费中的业务类型进行统计相应的费用
	public Double getXiaZhanCost(String begin, String finish, Integer ClientId,
			String type) {
		return this.xiazhanfeiDao.getXiaZhanCost(begin, finish, ClientId, type);
	}

	// 查询未收费的下站费
	public List<Xiazhanfei> getWeiShouCost(String begin, String finish,
			Integer clientId) {
		return this.xiazhanfeiDao.getWeiShouCost(begin, finish, clientId);
	}

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方
	public List<Xiazhanfei> getAllByPage(String clientName, String begin,
			String finish, String jiesuan, int pageNow, int rowSize,
			String shoufeiren) {
		return this.xiazhanfeiDao.getAllByPage(clientName, begin, finish,
				jiesuan, pageNow, rowSize, shoufeiren);
	}

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方查询总页数
	public int getAllByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		return this.xiazhanfeiDao.getAllByPageCount(clientName, begin, finish,
				jiesuan, rowSize, shoufeiren);
	}

	public List<Xiazhanfei> getCaoZuo(String ccBianHao) {
		return this.xiazhanfeiDao.getCaoZuo(ccBianHao);
	}
}
