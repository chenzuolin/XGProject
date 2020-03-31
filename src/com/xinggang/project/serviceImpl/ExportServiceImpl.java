package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ExportDao;
import com.xinggang.project.entity.Export;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.service.ExportService;

/**
 * 出库总订单Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ExportServiceImpl implements ExportService {
	private ExportDao exportDao;

	public void setExportDao(ExportDao exportDao) {
		this.exportDao = exportDao;
	}

	// 增加
	public boolean save(Export export) {
		return exportDao.save(export);
	}

	// 删除
	public boolean delete(Export export) {
		Export e = exportDao.getExportId(export.getExportId());
		if (e == null) {
			return false;
		} else {
			return exportDao.delete(export);
		}
	}

	// 修改
	public boolean update(Export export) {
		Export e = exportDao.getExportId(export.getExportId());
		if (e == null) {
			return false;
		} else {
			return exportDao.update(export);
		}
	}

	// 通过id查询
	public Export getExportId(String id) {
		return exportDao.getExportId(id);
	}

	// 查询全部
	public List<Export> getAll() {
		return exportDao.getAll();
	}

	// 全部分页查询
	public List<Export> getExportByPage(int pageNow, int rowSize) {
		return exportDao.getExportByPage(pageNow, rowSize);
	}

	// 全部数据查询总行数
	public int getExportByCount() {
		return exportDao.getExportByCount();
	}

	// 通过客户查询
	public List<Export> getClientAll(Integer ClientId) {
		return exportDao.getClientAll(ClientId);
	}

	// 通过客户分页查询
	public List<ExportSeed> getClientAllByPage(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int pageNow, int rowSize) {
		return exportDao.getClientAllByPage(begin, finish, goodsName,
				zongbianhao, kehubianhao, ClientId, pageNow, rowSize);
	}

	// 通过客户查询出数据的总行数
	public int getClientAllByCount(String begin, String finish,
			String goodsName, String zongbianhao, String kehubianhao,
			Integer ClientId, int rowSize) {
		return exportDao.getClientAllByCount(begin, finish, goodsName,
				zongbianhao, kehubianhao, ClientId, rowSize);
	}

	// 通过时间范围和客户订单号模糊查询
	public List<Export> getShijian(String begin, String finish, String dingdan) {
		return exportDao.getShijian(begin, finish, dingdan);
	}

	// 通过时间范围和客户订单号模糊分页查询
	public List<Export> getShijianByPage(String begin, String finish,
			String dingdan, int pageNow, int rowSize) {
		return exportDao.getShijianByPage(begin, finish, dingdan, pageNow,
				rowSize);
	}

	// 通过时间范围和客户订单号模糊查询数据总行数
	public int getShijianByCount(String begin, String finish, String dingdan) {
		return exportDao.getShijianByCount(begin, finish, dingdan);
	}

	// 通过状态查询
	public List<Export> getZhuangtai(String zhuang) {
		return exportDao.getZhuangtai(zhuang);
	}

	// 通过状态分页查询
	public List<Export> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {
		return exportDao.getZhuangtaiByPage(zhuang, pageNow, rowSize);
	}

	// 通过状态查询数据总行数
	public int getZhuangtaiByCount(String zhuang) {
		return exportDao.getZhuangtaiByCount(zhuang);
	}

}
