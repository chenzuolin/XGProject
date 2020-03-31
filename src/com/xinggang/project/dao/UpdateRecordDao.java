package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.UpdateRecord;

//修改记录Dao
public interface UpdateRecordDao {

	// 添加
	public boolean save(UpdateRecord updateRecord);

	// 修改
	public boolean update(UpdateRecord updateRecord);

	// 删除
	public boolean delete(UpdateRecord updateRecord);

	// 通过编号查询
	public UpdateRecord getUpdateRecordId(Integer id);

	// 查询全部
	public List<UpdateRecord> getAll();

	// 通过操作订单编号，发起人，状态进行判断是否同意了修改
	public List<UpdateRecord> goShenPiPanDuan(String caoId, String iuserName,
			String zhuangtai);

	// 查询所有的审批记录，以时间和操作人，编号进行模糊查询
	public List<UpdateRecord> getAllByPage(String begin, String finish,
			String caozuoren, String bianhao, int pageNow, int rowSize);

	// 查询所有的审批记录的总页数
	public int getAllByPageCount(String begin, String finish, String caozuoren,
			String bianhao, int rowSize);

	// 查询要进行审批的记录
	public List<UpdateRecord> getShenPiCaoZuo();

	// 通过申请人、发起时间范围和状态查询要进行审批的数据
	public List<UpdateRecord> getShenPi(String shenqingren, String begin,
			String finish, String zhaungtai, String shengpiren, String type);

	// 查询全部的审批记录
	public List<UpdateRecord> getUrList(String faqiren, String begin,
			String finish);
}
