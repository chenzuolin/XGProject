package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.form.ShiftStockForm;

/**
 * 过户总订单Service
 * 
 * @author Administrator
 * 
 */
public interface ShiftStockService {
	// 增
	public boolean save(ShiftStock shiftStock);

	// 删
	public boolean delete(ShiftStock shiftStock);

	// 改
	public boolean update(ShiftStock shiftStock);

	// 通过id查询
	public ShiftStock getShiftStockId(String id);

	// 查询全部
	public List<ShiftStock> getAll();

	// 分页查询
	public List<ShiftStock> getShiftStockByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getShiftStockByCount();

	// 通过过户时间范围查询
	public List<ShiftStock> getShijian(String begin, String finish,
			String dingdan, Integer kehu);

	// 通过时间范围分页查询
	public List<ShiftStock> getShijianByPage(String begin, Integer kehu,
			String finish, String dingdan, int pageNow, int rowSize);

	// 通过时间范围查询数据的总行数
	public int getShijianByCount(String begin, Integer kehu, String finish,
			String dingdan);

	// 通过客户查询
	public List<ShiftStock> getKehu(Integer kehu, Integer zhuanru);

	// 通过客户分页查询
	public List<ShiftStock> getKehuByPage(Integer kehu, Integer zhuanru,
			int pageNow, int rowSize);

	// 通过客户查询数据的总行数
	public int getKehuByCount(Integer kehu, Integer zhuanru);

	// 通过订单状态查询
	public List<ShiftStock> getZhuangtai(String zhuang);

	// 通过订单状态分页查询
	public List<ShiftStock> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize);

	// 通过订单状态查询数据总行数
	public int getZhuangtaiByCount(String zhuang);

	// 发起过户
	public boolean saveShiftStock(ShiftStockForm ssf,String faqiren);
	
	//手机app发起过户
	public boolean saveShiftStockApp(ShiftStockForm ssf);
}
