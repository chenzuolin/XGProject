package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputSeed;

/**
 * 入库总订单Dao
 * 
 * @author Administrator
 * 
 */
public interface InputDao {
	// 增
	public boolean save(Input input);

	// 删
	public boolean delete(Input input);

	// 改
	public boolean update(Input input);

	// 通过id查询
	public Input getInputId(String id);

	// 查询全部
	public List<Input> getAll();

	// 通过客户查询订单
	public List<Client> getClient(Integer ClientId);

	// 分页查询
	public List<Input> getInputByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getInputByCount();

	// 通过客户查询分页
	public List<Input> getClientIBypage(Integer ClientId, int pageNow,
			int rowSize);

	// 通过客户查询数据总行数
	public int getClientIByCount(Integer ClientId);

	// 通过订单状态查询
	public List<Input> getZhuangtai(String zhuang);

	// 通过订单状态分页查询
	public List<Input> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize);

	// 通过订单状态查询出数据总行数
	public int getZhuangByCount(String zhuang);

	// 通过时间范围查询
	public List<Input> getShijian(String begin, String finish, String kehu);

	// 通过时间范围分页查询
	public List<Input> getShijianByPage(String begin, String finish,
			String kehu, int pageNow, int rowSize);

	// 通过时间范围查询出数据总行数
	public int getShijianByCount(String begin, String finish, String kehu);
	
	// 分页查询-----------------------------------修改后
	public List<InputSeed> getInputByPage2(int pageNow, int rowSize);
	
	//得到分页的页数------------------------------修改后
	public int getPageCount(int pageSize);

	//返回入库，出库，过户的订单发起的最大时间,针对某一个客户
	public String[] getIESMaxTime(Integer clientId);
	

}
