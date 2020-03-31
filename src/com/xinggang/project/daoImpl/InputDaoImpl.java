package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InputDao;
import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputSeed;

/**
 * 入库总订单接口实现类
 * 
 * @author Administrator
 * 
 */
public class InputDaoImpl extends BaseDaoImpl implements InputDao {
	// 增
	public boolean save(Input input) {
		return super.BaseSave(input);
	}

	// 删
	public boolean delete(Input input) {
		Input i = (Input) super.findById(Input.class, input.getInputId());
		i.setInputOrderStatus("订单作废");
		return super.BaseUpdate(i);
	}

	// 改
	public boolean update(Input input) {
		return super.BaseUpdate(input);
	}

	// 通过id查询
	public Input getInputId(String id) {
		return (Input) super.findById(Input.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Input> getAll() {
		String hql = "from Input where inputOrderStatus!='订单作废' order by inputCreateTime desc";
		return (List<Input>) super.executeQuery(hql, null);
	}

	// 入库数据分页查询
	@SuppressWarnings("unchecked")
	public List<Input> getInputByPage(int pageNow, int rowSize) {
		String hql = "from Input where inputOrderStatus!='订单作废' order by inputCreateTime desc";
		return (List<Input>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询入库数据总行数
	public int getInputByCount() {
		String hql = "select count(*) from Input where inputOrderStatus!='订单作废'";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<Client> getClient(Integer ClientId) {
		String hql = "from Input where inputOrderStatus!='订单作废' and client="
				+ ClientId;
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 通过客户查询分页
	@SuppressWarnings("unchecked")
	public List<Input> getClientIBypage(Integer ClientId, int pageNow,
			int rowSize) {
		String hql = "from Input where inputOrderStatus!='订单作废' and client="
				+ ClientId + " order by inputCreateTime desc";
		return (List<Input>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询数据总行数
	public int getClientIByCount(Integer ClientId) {
		String hql = "select count(*) from Input where inputOrderStatus!='订单作废' and client="
				+ ClientId;
		return super.executeQueryRowCount(hql, null);
	}

	// 通过状态查询
	@SuppressWarnings("unchecked")
	public List<Input> getZhuangtai(String zhuang) {
		String hql = "from Input where inputOrderStatus='" + zhuang + "'";
		return (List<Input>) super.executeQuery(hql, null);
	}

	// 通过时间范围查询
	@SuppressWarnings("unchecked")
	public List<Input> getShijian(String begin, String finish, String kehu) {
		String hql = "from Input where inputOrderStatus!='订单作废' and inputCreateTime>='"
				+ begin
				+ "' and inputCreateTime<='"
				+ finish
				+ "' and inputClientNumber like ('%" + kehu + "%')";// 客户订单号
		return (List<Input>) super.executeQuery(hql, null);
	}

	// 通过状态分页查询
	@SuppressWarnings("unchecked")
	public List<Input> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {
		String hql = "from Input where inputOrderStatus='" + zhuang
				+ "' order by inputCreateTime desc";
		return (List<Input>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过状态查询数据总行数
	public int getZhuangByCount(String zhuang) {
		String hql = "select count(*) from Input where inputOrderStatus='"
				+ zhuang + "'";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过时间范围分页查询
	@SuppressWarnings("unchecked")
	public List<Input> getShijianByPage(String begin, String finish,
			String kehu, int pageNow, int rowSize) {
		String hql = "from Input where inputOrderStatus!='订单作废' and inputCreateTime>='"
				+ begin
				+ "' and inputCreateTime<='"
				+ finish
				+ "' and inputClientNumber like ('%"
				+ kehu
				+ "%')"
				+ " order by inputCreateTime desc";
		return (List<Input>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过时间范围查询数据总行数
	public int getShijianByCount(String begin, String finish, String kehu) {
		String hql = "select count(*) from Input where inputOrderStatus!='订单作废' and inputCreateTime>='"
				+ begin
				+ "' and inputCreateTime<="
				+ finish
				+ "and inputClientNumber like ('%"
				+ kehu
				+ "%')"
				+ " order by inputCreateTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 客户查询出所有订单
	@SuppressWarnings("unchecked")
	public List<InputSeed> getInputByPage2(int pageNow, int rowSize) {
		String hql = "from InputSeed order by input.inputCreateTime desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询出状态为计划入库的信息
	public int getPageCount(int pageSize) {
		String hql = "select COUNT(*) from Input where inputOrderStatus='计划入库'";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 返回入库，出库，过户的订单发起的最大时间,针对某一个客户
	@SuppressWarnings("unchecked")
	public String[] getIESMaxTime(Integer clientId) {
		String str[] = new String[5];
		String hqlru = "select max(inputCreateTime) from Input where client="
				+ clientId;// 入库的最大时间

		List<String> ru = (List<String>) super.executeQuery(hqlru, null);
		
		if (ru.size() > 0) {
			if (ru.get(0) != null) {
				str[0] = ru.get(0);
			} else {
				str[0] = "2017-01-01";
			}
		} else {
			str[0] ="2017-01-01";
		}

		String hqlchu = "select max(exportReateTime) from Export where client="
				+ clientId;// 出库的最大时间
		List<String> chu = (List<String>) super.executeQuery(hqlchu, null);
		if (chu.size() > 0) {
			if (chu.get(0) != null) {
				str[1] = chu.get(0);
			} else {
				str[1] ="2017-01-27";
			}
		} else {
			str[1] = "2017-01-27";
		}

		String hqlguo = "select max(sstockReateTime) from ShiftStock where clientBySstockClientId="
				+ clientId;
		List<String> guo = (List<String>) super.executeQuery(hqlguo, null);
		if (guo.size() > 0) {
			if (guo.get(0) != null) {
				str[2] = guo.get(0);
			} else {
				str[2] = "2011-07-27";
			}
		} else {
			str[2] = "2011-07-27";
		}
		
		return str;
	}

}
