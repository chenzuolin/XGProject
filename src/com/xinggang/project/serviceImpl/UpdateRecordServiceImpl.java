package com.xinggang.project.serviceImpl;

import java.util.List;

import com.xinggang.project.dao.UpdateRecordDao;
import com.xinggang.project.entity.UpdateRecord;
import com.xinggang.project.service.UpdateRecordService;

public class UpdateRecordServiceImpl implements UpdateRecordService {

	// 订单修改记录dao
	private UpdateRecordDao updateRecordDao;

	public void setUpdateRecordDao(UpdateRecordDao updateRecordDao) {
		this.updateRecordDao = updateRecordDao;
	}

	public boolean save(UpdateRecord updateRecord) {
		return this.updateRecordDao.save(updateRecord);
	}

	public boolean update(UpdateRecord updateRecord) {
		return this.updateRecordDao.update(updateRecord);
	}

	public boolean delete(UpdateRecord updateRecord) {
		return this.updateRecordDao.delete(updateRecord);
	}

	public UpdateRecord getUpdateRecordId(Integer id) {
		return this.updateRecordDao.getUpdateRecordId(id);
	}

	public List<UpdateRecord> getAll() {
		return this.updateRecordDao.getAll();
	}

	// 通过操作订单编号，发起人，状态进行判断是否同意了修改
	public List<UpdateRecord> goShenPiPanDuan(String caoId, String iuserName,
			String zhuangtai) {
		return this.updateRecordDao
				.goShenPiPanDuan(caoId, iuserName, zhuangtai);
	}

	// 调用添加修改记录
	public void RecordUpdate(String caoId, String faqiren, String remark) {
		List<UpdateRecord> urlist = this.updateRecordDao.goShenPiPanDuan(caoId,
				faqiren, "同意");

		if (urlist.size() <= 0) {

		} else {
			UpdateRecord ur = urlist.get(0);
			ur.setUrupdateneirong((ur.getUrupdateneirong() == null ? "" : ur
					.getUrupdateneirong()) + "," + remark);
			this.updateRecordDao.update(ur);
		}
	}

	// 查询所有的审批记录，以时间和操作人，编号进行模糊查询
	public List<UpdateRecord> getAllByPage(String begin, String finish,
			String caozuoren, String bianhao, int pageNow, int rowSize) {
		return this.updateRecordDao.getAllByPage(begin, finish, caozuoren,
				bianhao, pageNow, rowSize);
	}

	// 查询所有的审批记录的总页数
	public int getAllByPageCount(String begin, String finish, String caozuoren,
			String bianhao, int rowSize) {
		return this.updateRecordDao.getAllByPageCount(begin, finish, caozuoren,
				bianhao, rowSize);

	}

	// 查询要进行审批的记录
	public List<UpdateRecord> getShenPiCaoZuo() {
		return this.updateRecordDao.getShenPiCaoZuo();
	}

	// 通过申请人、发起时间范围和状态查询要进行审批的数据
	public List<UpdateRecord> getShenPi(String shenqingren, String begin,
			String finish, String zhaungtai, String shengpiren, String type) {
		return this.updateRecordDao.getShenPi(shenqingren, begin, finish,
				zhaungtai, shengpiren, type);
	}

	// 查询全部的审批记录
	public List<UpdateRecord> getUrList(String faqiren, String begin,
			String finish) {
		return this.updateRecordDao.getUrList(faqiren, begin, finish);
	}
}
