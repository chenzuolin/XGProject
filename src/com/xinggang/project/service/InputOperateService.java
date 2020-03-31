package com.xinggang.project.service;

import java.util.List;

import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.form.InputOperateForm;

/**
 * 入库订单操作Service
 * 
 * @author Administrator
 * 
 */
public interface InputOperateService {
	// 增
	public boolean save(InputOperate inputOperate);

	// 删
	public boolean delete(InputOperate inputOperate);

	// 改
	public boolean update(InputOperate inputOperate);

	// 通过id查询
	public InputOperate getInputOperateId(String id);

	// 通过子订单id查询
	public List<InputOperate> getInputSeedId(String id);

	// 查询全部
	public List<InputOperate> getAll();

	// 查询全部完成的操作订单
	public List<InputOperate> getAllFinallyDingdan(String zongId);

	// 查询全部并且以分页的形式显示
	public List<InputOperate> getAllByPage(int pageNow, int rowSize);

	// 查询全部数据的总行数
	public int getAllByCount();

	// 通过库位查询
	public List<InputOperate> getKuwei(Integer kuweiId);

	// 通过内部人员查询
	public List<InputOperate> getNeibu(Integer neibuId);

	/*
	 * // 通过工作人员查询,天车工或者是装卸工 public List<InputOperate> getWorking(String
	 * working);
	 */

	// -----------------

	// 通过审核状态查询
	public List<InputOperate> getZhuangtai(String zhuangtai);

	// 添加入库操作类
	public boolean saveInputOperat(InputOperateForm inputOperateForm,
			InteriorUser user);

	// 修改操作信息
	public boolean updateInputOperat(InputOperateForm inputOperateForm);

	// 修改重量
	public boolean updateWeight(InputOperateForm inputOperateForm,
			InteriorUser user);

	// 通过保管查询
	public List<InputOperate> getBaoguan(Integer baoguanId);

	// 修改审核状态
	public boolean shenHeStatus(InputOperateForm inputOperateForm);

	// 审核人员修改操作信息
	public boolean SHupdateInputOperat(InteriorUser user, Double shouMoney,
			String caozuoId, String beizhu);

	// 审核未通过
	public boolean SHupdateInputOperatTwo(InteriorUser user, String caozuoId,
			String beizhu);

	// 修改操作表中的状态
	public boolean updateinputOperateStatus(String id, String status);

	// 收费人员收费，填入信息
	public boolean updateMoney(InputOperateForm inputOperateForm,
			InteriorUser user);

	// 根据订单号、客户姓名、订单生产时间、货主、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得到分页页数
	public List<InputOperate> getInputOperateByPage2(String dingDanHao,
			String clientName, String inputTime, String chehao,
			String sijiName, String yunshuStyle, String dingdanYesNo,
			String huowuName, String dingdanStatus, String kuwei,
			String diaodu, String baoguan, String tianche, String zhuangxie,
			String sibang, String shenhe, String shenheTime, String shoufei,
			String shoufeiTime, String beginTime, String finishTime,
			String chandi, int pageNow, int rowSize);

	// 分页查询所有-----------------------------------修改后
	public List<InputOperate> getInputOperateByPage(int pageNow, int rowSize);

	// 得到分页的页数------------------------------修改后
	public int getPageCount(int pageSize);

	// 根据条件到的页数---------------------修改后
	// 根据订单号、客户姓名、订单生产时间、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得到分页页数
	public int getPageCountTwo(String dingDanHao, String clientName,
			String inputTime, String chehao, String sijiName,
			String yunshuStyle, String dingdanYesNo, String huowuName,
			String dingdanStatus, String kuwei, String diaodu, String baoguan,
			String tianche, String zhuangxie, String sibang, String shenhe,
			String shenheTime, String shoufei, String shoufeiTime,
			String beginTime, String finishTime, String chandi, int pageSize);

	// 通过司磅人员查询
	public List<InputOperate> getSBInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize);

	// 通过司磅人员查询的行数
	public int getSBCount(String status, String danhao, String huozhu,
			int pageSize);

	// 返回所有内部人员查询分页页数
	public int count(String status, String danhao, String huozhu, int pageSize);

	// 返回所有内部人员查询分页的信息
	public List<InputOperate> getInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize);

	// 入库工作量统计查询
	public List<InputOperate> QueryInputWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize);

	// 入库工作量统计页数
	public int QueryInputWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize);

	// 统计输入的该人员的入库合计
	public Double QueryInputHeJi(String begin, String finish, String zhiwu,
			String name);

	// 统计输入的该人员的出库合计
	public Double QueryExportHeJi(String begin, String finish, String zhiwu,
			String name);

	// 统计输入的该人员的过户合计
	public Double QueryShiftStockHeJi(String begin, String finish,
			String zhiwu, String name);

	// 统计输入的该人员的挪库合计
	public Double QueryShiftHeJi(String begin, String finish, String zhiwu,
			String name);

	// 统计短倒作业量
	public Double QueryDuanDaoHeJi(String begin, String finish, String zhiwu,
			String name);
}
