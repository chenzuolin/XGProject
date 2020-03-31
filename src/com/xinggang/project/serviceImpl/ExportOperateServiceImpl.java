package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ExportOperateDao;
import com.xinggang.project.entity.ExportOperate;
import com.xinggang.project.service.ExportOperateService;

/**
 * 出库订单操作Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ExportOperateServiceImpl implements ExportOperateService {
	private ExportOperateDao exportOperateDao;

	public void setExportOperateDao(ExportOperateDao exportOperateDao) {
		this.exportOperateDao = exportOperateDao;
	}

	// 增加
	public boolean save(ExportOperate exportOperate) {
		return exportOperateDao.save(exportOperate);
	}

	// 删除
	public boolean delete(ExportOperate exportOperate) {
		ExportOperate e = exportOperateDao.getExportOperateId(exportOperate
				.getEoperateId());
		if (e == null) {
			return false;
		} else {
			return exportOperateDao.delete(exportOperate);
		}
	}

	// 修改
	public boolean update(ExportOperate exportOperate) {
		ExportOperate e = exportOperateDao.getExportOperateId(exportOperate
				.getEoperateId());
		if (e == null) {
			return false;
		} else {
			return exportOperateDao.update(exportOperate);
		}
	}

	// 通过id查询
	public ExportOperate getExportOperateId(String id) {
		return exportOperateDao.getExportOperateId(id);
	}

	// 查询全部
	public List<ExportOperate> getAll() {
		return exportOperateDao.getAll();
	}

	// 通过全部分页查询
	public List<ExportOperate> getAllByPage(int pageNow, int rowSize) {
		return exportOperateDao.getAllByPage(pageNow, rowSize);
	}

	// 通过全部查询出数据的总行数
	public int getAllByCount() {

		return exportOperateDao.getAllByCount();
	}

	// 通过子订单编号查询
	public List<ExportOperate> getZidingdan(String zidingdan) {
		return exportOperateDao.getZidingdan(zidingdan);
	}

	// 通过库位查询
	public List<ExportOperate> getKuwei(Integer kuwei) {
		return exportOperateDao.getKuwei(kuwei);
	}

	// 通过内部人员查询
	public List<ExportOperate> getNeibu(Integer neibu) {
		return exportOperateDao.getNeibu(neibu);
	}

	// 通过审核状态查询
	public List<ExportOperate> getZhuangtai(String zhuangtai) {
		return exportOperateDao.getZhuangtai(zhuangtai);
	}

	// 查询操作订单为准备出库的并且保管是登录的那个保管员
	public List<ExportOperate> getZhuangTaiBaoGuan(String zhuangtai,
			Integer baoguan) {
		return this.exportOperateDao.getZhuangTaiBaoGuan(zhuangtai, baoguan);
	}

	// 通过过磅的订单
	public List<ExportOperate> getGuoBangAll(String guobang, String kehuhao,
			String jiancheng, int pageNow, int rowSize) {
		return this.exportOperateDao.getGuoBangAll(guobang, kehuhao, jiancheng,
				pageNow, rowSize);
	}

	public int getGuoBangAllByCount(String guobang, String kehuhao,
			String jiancheng, int rowSize) {
		return this.exportOperateDao.getGuoBangAllByCount(guobang, kehuhao,
				jiancheng, rowSize);
	}

	// 通过状态查询分页
	public List<ExportOperate> getZhuangTaiByPage(String begin, String finish,
			String danwei, String kehudanhao, String zhuantai, int pageNow,
			int rowSize) {
		return this.exportOperateDao.getZhuangTaiByPage(begin, finish, danwei,
				kehudanhao, zhuantai, pageNow, rowSize);
	}

	// 通过状态查询总页数
	public int getZhuangTaiByPageCount(String begin, String finish,
			String danwei, String kehudanhao, String zhuangtai, int rowSize) {
		return this.exportOperateDao.getZhuangTaiByPageCount(begin, finish,
				danwei, kehudanhao, zhuangtai, rowSize);
	}

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，
	public List<ExportOperate> getExportOperateByPage(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int pageNow, int rowSize) {
		return this.exportOperateDao.getExportOperateByPage(zongdanhao,
				kehudanhao, begin, finish, danwei, jiancheng, danweiSign,
				goodsName, guige, caizhi, shuxing, goodsSign, kuweiName,
				diaodu, sibang, baoguan, shenhe, shoufei, pageNow, rowSize);
	}

	// 查询全部返回页数
	public int getExportOperateBYPageCount(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int rowSize) {
		return this.exportOperateDao.getExportOperateBYPageCount(zongdanhao,
				kehudanhao, begin, finish, danwei, jiancheng, danweiSign,
				goodsName, guige, caizhi, shuxing, goodsSign, kuweiName,
				diaodu, sibang, baoguan, shenhe, shoufei, rowSize);
	}

	// 通过总订单好，进行查询，判断是否有操作订单产生，如果有那么该订单已经开始操作，不能取消，如果没有则可以取消
	public List<ExportOperate> getQuXiaoDingDan(String zongdanhao) {
		return this.exportOperateDao.getQuXiaoDingDan(zongdanhao);
	}

	// ----------------------后来加入开始

	// 根据条件查询总页数
	public int pageCount(String danwei, String kehudanhao, String zhuangtai,
			int rowSize) {
		return this.exportOperateDao.pageCount(danwei, kehudanhao, zhuangtai,
				rowSize);
	};

	// 根据条件查询总出库操作表信息
	public List<ExportOperate> pageAllInfo(String danwei, String kehudanhao,
			String zhuangtai, int pageNow, int rowSize) {
		return this.exportOperateDao.pageAllInfo(danwei, kehudanhao, zhuangtai,
				pageNow, rowSize);
	};

	// 通过id查询
	public List<ExportOperate> getExportSeedId(String id) {
		return exportOperateDao.getExportSeedId(id);
	};

	// ----------------------后来加入结束
	// 出库工作量统计查询
	public List<ExportOperate> QueryExportWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize) {
		return this.exportOperateDao.QueryExportWorkByPage(begin, finish,
				zhiwu, name, pageNow, rowSize);
	}

	// 出库工作量统计页数
	public int QueryExportWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		return this.exportOperateDao.QueryExportWorkByPageCount(begin, finish,
				zhiwu, name, rowSize);
	}
}
