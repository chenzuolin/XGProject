package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.XiazhanfeiDao;
import com.xinggang.project.entity.Xiazhanfei;

public class XiazhanfeiDaoImpl extends BaseDaoImpl implements XiazhanfeiDao {

	public boolean save(Xiazhanfei xiazhan) {

		return super.BaseSave(xiazhan);
	}

	public boolean update(Xiazhanfei xiazhan) {

		return super.BaseUpdate(xiazhan);
	}

	public boolean delete(Xiazhanfei xiazhan) {

		return super.BaseDelete(xiazhan);
	}

	public Xiazhanfei getXiazhangfei(Integer id) {
		String hql = "from Xiazhanfei where xzid=" + id;
		return (Xiazhanfei) super.findById(hql);
	}

	// 根据总订单id查询
	@SuppressWarnings("unchecked")
	public List<Xiazhanfei> getAllzongId(String zongId) {

		String hql = "from Xiazhanfei where xadefinedtwo='" + zongId
				+ "' order by xzshoufeitime desc";

		return (List<Xiazhanfei>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<Xiazhanfei> getAll() {
		String hql = "from Xiazhanfei";
		return (List<Xiazhanfei>) super.executeQuery(hql, null);
	}

	// 通过下站费中的业务类型进行统计相应的费用
	public Double getXiaZhanCost(String begin, String finish, Integer ClientId,
			String type) {
		String hql = "select sum(xzcount) from Xiazhanfei where xzshoufeitime >= '"
				+ begin
				+ "' and xzshoufeitime <= '"
				+ finish
				+ "' and xzdefinedthree = '"
				+ type
				+ "' and xzzhuangtai='未收费' and clientByXzzhuanruclient="
				+ ClientId;
		Double cost = (Double) super.executeQuery(hql, null).get(0);
		return cost;
	}

	// 查询未收费的下站费
	@SuppressWarnings("unchecked")
	public List<Xiazhanfei> getWeiShouCost(String begin, String finish,
			Integer clientId) {
		String hql = "from Xiazhanfei where xzshoufeitime >= '" + begin
				+ "' and xzshoufeitime <= '" + finish
				+ "' and xzzhuangtai='未收费' and clientByXzzhuanruclient="
				+ clientId;
		return (List<Xiazhanfei>) super.executeQuery(hql, null);
	}

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方
	@SuppressWarnings("unchecked")
	public List<Xiazhanfei> getAllByPage(String clientName, String begin,
			String finish, String jiesuan, int pageNow, int rowSize,
			String shoufeiren) {
		String hql = "from Xiazhanfei where xzshoufeitime >= '" + begin
				+ "' and xzshoufeitime <= '" + finish
				+ "' and clientByXzzhuanruclient.clientAbbreviation like ('%"
				+ clientName + "%') and xzdefinedone like ('%" + jiesuan
				+ "%') and xzshoufeiren like ('%" + shoufeiren
				+ "%') order by xzshoufeitime desc";
		return (List<Xiazhanfei>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户的名称，收费的时间范围，结算范式进行查询下站费,,客户的名称指的是转入方查询总页数
	public int getAllByPageCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		String hql = "select count(*) from Xiazhanfei where xzshoufeitime >= '"
				+ begin + "' and xzshoufeitime <= '" + finish
				+ "' and clientByXzzhuanruclient.clientAbbreviation like ('%"
				+ clientName + "%') and xzdefinedone like ('%" + jiesuan
				+ "%') and xzshoufeiren like ('%" + shoufeiren + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过下站费自定义五查询下站费
	@SuppressWarnings("unchecked")
	public List<Xiazhanfei> getCaoZuo(String ccBianHao) {
		String hql = "from Xiazhanfei where xadefinedfive='" + ccBianHao + "'";
		return (List<Xiazhanfei>) super.executeQuery(hql, null);
	}

}
