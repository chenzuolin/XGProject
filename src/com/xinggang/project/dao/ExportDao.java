package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportSeed;

/**
 * 出库总订单Dao
 * 
 * @author Administrator
 * 
 */
public interface ExportDao {
	// 增
	public boolean save(Export export);

	// 删
	public boolean delete(Export export);

	// 改
	public boolean update(Export export);

	// 通过id查询
	public Export getExportId(String id);

	// 查询全部
	public List<Export> getAll();

	// 分页查询
	public List<Export> getExportByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getExportByCount();

	// 通过客户查询
	public List<Export> getClientAll(Integer ClientId);

	// 通过客户分页
	public List<ExportSeed> getClientAllByPage(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int pageNow, int rowSize);

	// 通过客户查询数据的总行数
	public int getClientAllByCount(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int rowSize);

	// 通过时间范围查询
	public List<Export> getShijian(String begin, String finish, String dingdan);

	// 通过时间范围分页查询
	public List<Export> getShijianByPage(String begin, String finish,
			String dingdan, int pageNow, int rowSize);

	// 通过时间范围查询数据的总行数
	public int getShijianByCount(String begin, String finish, String dingdan);

	// 通过订单状态查询
	public List<Export> getZhuangtai(String zhuang);

	// 通过订单状态分页查询
	public List<Export> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize);

	// 通过订单状态查询数据的总行数
	public int getZhuangtaiByCount(String zhuang);
	
}
