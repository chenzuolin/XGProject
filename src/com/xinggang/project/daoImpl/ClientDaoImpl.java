package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.entity.Client;

/**
 * 客户接口实现类
 * 
 * @author Administrator
 * 
 */
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {
	// 增
	public boolean save(Client client) {
		return super.BaseSave(client);
	}

	// 删,此项目不可删除，只能停用，1不停用，0停用
	public boolean delete(Client client) {
		client.setClientCease(0);
		return super.BaseUpdate(client);
	}

	// 改
	public boolean update(Client client) {
		return super.BaseUpdate(client);
	}

	// 通过ID查询
	public Client getClientId(Integer id) {
		String hql = "from Client where clientId=" + id;
		return (Client) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		String hql = "from Client where clientCease!=0";
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Client> getClientByPage(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageNow, int rowSize) {
		String hql = "from Client where clientCease!=0 and clientFirmName like('%"
				+ qupingdanwei
				+ "%') "
				+ "and clientAbbreviation like ('%"
				+ jiancheng
				+ "%')"
				+ " and clientSign like ('%"
				+ zhujifu
				+ "%')"
				+ " and clientContract like ('%"
				+ hetonghao
				+ "%') "
				+ "order by ClientId desc";
		return (List<Client>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询没有结算的客户
	@SuppressWarnings("unchecked")
	public List<Client> getWeiJieSuan(String begin, String finish,
			String jiancheng, int pageNow, int rowSize) {
		String hql = "from Client where clientCease!=0 and clientAbbreviation like ('%"
				+ jiancheng
				+ "%') and clientId not in (select client from Accounts where Convert(varchar(10),accountsFinishTime,120)"
				+ "between '"
				+ begin
				+ "' and '"
				+ finish
				+ "' and zhuangtai='未收费')" + " order by ClientId desc";
		return (List<Client>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询没有结算的客户的页数
	public int getWeiJieSuanCount(String begin, String finish,
			String jiancheng, int pageNow, int rowSize) {
		String hql = "select count(*) from Client where clientCease!=0 and clientAbbreviation like ('%"
				+ jiancheng
				+ "%') and clientId not in (select client from Accounts where Convert(varchar(10),accountsFinishTime,120)"
				+ "between '"
				+ begin
				+ "' and '"
				+ finish
				+ "' and zhuangtai='未收费')" + "";
		return super.queryPageCount(hql, null, rowSize);
	}

	// app客户查询---

	// 客户查询费用汇总
	@SuppressWarnings("unchecked")
	public List<Client> getWeiJieSuanKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize) {
		String hql = "from Client where clientCease!=0 and clientId="
				+ clientId
				+ ""
				+ " and clientId not in (select client from Accounts where Convert(varchar(10),accountsFinishTime,120)"
				+ " between '" + begin + "' " + " and '" + finish
				+ "' and zhuangtai='未收费')" + " order by ClientId desc";
		return (List<Client>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户查询费用汇总页数
	public int getWeiJieSuanCountKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize) {
		String hql = "select count(*) from Client where clientCease!=0 and clientId="
				+ clientId
				+ ""
				+ " and clientId not in (select client from Accounts where Convert(varchar(10),accountsFinishTime,120)"
				+ "between '"
				+ begin
				+ "' and '"
				+ finish
				+ "' and zhuangtai='未收费')" + "";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询数据总行数
	public int getClientByCount(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao) {// 根据客户的单位名称，简称，助记符，合同号查询
		String hql = "select count(*) from Client where clientCease!=0 and clientFirmName like('%"
				+ qupingdanwei
				+ "%') "
				+ "and clientAbbreviation like ('%"
				+ jiancheng
				+ "%')"
				+ " and clientSign like ('%"
				+ zhujifu
				+ "%') "
				+ "and clientContract like ('%"
				+ hetonghao
				+ "%')"
				+ "";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过用户名和密码查询,用户登录时可以调用，判断用户名密码是否正确
	@SuppressWarnings("unchecked")
	public List<Client> getClientLogin(String loginName, String loginPwd) {
		String hql = "from Client where clientCease!=0 and (clientLoginName='"
				+ loginName + "') and clientPassword='" + loginPwd + "'";
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 通过用户名查询，判断用户名是否存在
	@SuppressWarnings("unchecked")
	public List<Client> getClientName(String loginName) {
		String hql = "from Client where clientCease!=0 and clientLoginName='"
				+ loginName + "'";// 为0停用
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 查看停用客户
	@SuppressWarnings("unchecked")
	public List<Client> getTingyong() {
		String hql = "from Client where clientCease=0";
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 通过结算方式查询
	@SuppressWarnings("unchecked")
	public List<Client> getJiesuan(String jiesuan) {
		String hql = "from Client where clientAccounts='" + jiesuan + "'";
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 通过模糊查询所有的客户
	@SuppressWarnings("unchecked")
	public List<Client> getMohuClient(String qupingdanwei, String jiancheng,
			String zhujifu) {
		String hql = "from Client where clientCease!=0 and clientFirmName like('%"
				+ qupingdanwei
				+ "%') "
				+ "and clientAbbreviation like ('%"
				+ jiancheng
				+ "%') "
				+ "and clientSign like ('%"
				+ zhujifu
				+ "%') " + "order by ClientId desc";
		return (List<Client>) super.executeQuery(hql, null);
	}

	// 分页查询，得到所有的数据
	@SuppressWarnings("unchecked")
	public List<Client> getClientByPage2(String clientName, int pageNow,
			int rowSize) {
		String hql = "from Client where (clientFirmName like('%" + clientName
				+ "%') or clientAbbreviation like('%" + clientName
				+ "%') or clientSign like ('%" + clientName
				+ "%')) and clientCease!='0' order by ClientId desc";
		return (List<Client>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 分页得到总页数
	public int getPageCount(String clientName, int pageSize) {
		String hql = "select COUNT(*) from Client where (clientFirmName like ('%"
				+ clientName
				+ "%') "
				+ "or clientAbbreviation like ('%"
				+ clientName
				+ "%') "
				+ "or clientSign like ('%"
				+ clientName
				+ "%'))";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 根据条件得到总页数
	public int getPageCountTwo(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageSize) {
		String hql = "select count(*) from  Client where clientCease!='0' and clientFirmName like ('%"
				+ qupingdanwei
				+ "%') "
				+ "and clientAbbreviation like ('%"
				+ jiancheng
				+ "%') "
				+ "and clientSign like ('%"
				+ zhujifu
				+ "%') " + "and clientContract like ('%" + hetonghao + "%')";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 通过单位查询
	@SuppressWarnings("unchecked")
	public List<Client> getClientDanwei(String danwei) {

		String hql = "from Client where clientFirmName='" + danwei + "'"
				+ " or clientAbbreviation='" + danwei + "'";
		return (List<Client>) super.executeQuery(hql, null);
	};

}
