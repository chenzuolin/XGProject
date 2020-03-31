package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Xiazhanfei;

public interface XiazhanfeiService {
	// 添加
	public boolean save(Xiazhanfei xiazhan);

	// 修改
	public boolean update(Xiazhanfei xiazhan);

	// 删除
	public boolean delete(Xiazhanfei xiazhan);

	// 通过id查询
	public Xiazhanfei getXiazhangfei(Integer id);

	// 根据总订单id查询
	public List<Xiazhanfei> getAllzongId(String zongId);

	// 查询全部
	public List<Xiazhanfei> getAll();

	// 通过下站费中的业务类型进行统计相应的费用
	public Double getXiaZhanCost(String begin, String finish, Integer ClientId,
			String type);

	// 查询未收费的下站费
	public List<Xiazhanfei> getWeiShouCost(String begin, String finish,
			Integer clientId);

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方
	public List<Xiazhanfei> getAllByPage(String clientName, String begin,
			String finish, String jiesuan, int pageNow, int rowSize,String shoufeiren);

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方查询总页数
	public int getAllByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize,String shoufeiren);

	public List<Xiazhanfei> getCaoZuo(String ccBianHao);
}
