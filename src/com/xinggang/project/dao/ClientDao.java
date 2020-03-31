package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.Client;

/**
 * 客户Dao
 * 
 * @author Administrator
 * 
 */
public interface ClientDao {
	// 增
	public boolean save(Client client);

	// 删
	public boolean delete(Client client);

	// 改
	public boolean update(Client client);

	// 通过id查询
	public Client getClientId(Integer id);

	// 查询全部
	public List<Client> getAll();

	// 通过用户名和密码查询,用户登录时可以调用，判断用户名密码是否正确
	public List<Client> getClientLogin(String loginName, String loginPwd);

	// 通过用户名查询
	public List<Client> getClientName(String loginName);

	// 分页查询
	public List<Client> getClientByPage(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageNow, int rowSize);

	// 查询数据的总行数
	public int getClientByCount(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao);

	// 查询已停用客户
	public List<Client> getTingyong();

	// 通过结算方式查询,月结，日结
	public List<Client> getJiesuan(String jiesuan);

	// 模糊查询所有的客户
	public List<Client> getMohuClient(String qupingdanwei, String jiancheng,
			String zhujifu);

	// 分页查询-----------------------------------修改后
	public List<Client> getClientByPage2(String clientName, int pageNow,
			int rowSize);

	// 得到分页的页数------------------------------修改后
	public int getPageCount(String clientName, int pageSize);

	// 根据条件到的页数---------------------修改后
	public int getPageCountTwo(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageSize);

	// 通过单位查询
	public List<Client> getClientDanwei(String danwei);

	// 查询没有结算的客户
	public List<Client> getWeiJieSuan(String begin, String finish,
			String jiancheng, int pageNow, int rowSize);

	// 查询没有结算的客户的页数
	public int getWeiJieSuanCount(String begin, String finish,
			String jiancheng, int pageNow, int rowSize);

	// 客户查询费用汇总
	public List<Client> getWeiJieSuanKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize);

	// 客户查询费用汇总页数
	public int getWeiJieSuanCountKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize);

}
