package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.Input;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.form.InputForm;

/**
 * 入库总订单Service
 * 
 * @author Administrator
 * 
 */
public interface InputService {
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

	// 修改入库订单是否作废
	public boolean updateInputCancel(String id, String shifou);

	// 修改入库订单状态
	public boolean updateInputStatus(String id, String status);

	// 添加入库
	public boolean saveInput(InputForm inputForm, Double money,String faqiren);

	// 分页查询2---------------------------------修改后
	public List<InputSeed> getInputByPage2(int pageNow, int rowSize);

	// 得到页数页数---------------------修改后
	public int getPageCount(int pageSize);

	// 修改每次加入库重量
	public boolean updateWeight(InputOperate inputOperate, String ziId);

	// 修改货物是否作废
	public boolean updateCancel(String id);

	// 添加入库
	public boolean saveInputApp(InputForm inputForm, Double monty,Goods good);

	// 返回入库，出库，过户的订单发起的最大时间,针对某一个客户
	public String[] getIESMaxTime(Integer clientId);
}
