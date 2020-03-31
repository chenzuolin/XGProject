package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.Shift;

/**
 * 挪库Dao
 * 
 * @author Administrator
 * 
 */
public interface ShiftDao {
	// 增加
	public boolean save(Shift shift);

	// 删除
	public boolean delete(Shift shift);

	// 修改
	public boolean update(Shift shift);

	// 通过id查询
	public Shift getShiftId(String id);

	// 查询全部
	public List<Shift> getAll();

	// 分页查询
	public List<Shift> getShiftByPage(int pageNow, int rowSize);

	// 查询数据总行数
	public int getShiftByCount();

	// 通过编号模糊查询
	public List<Shift> getShiftLike(String shiftId);

	// 通过挪库内部人员查询
	public List<Shift> getShiftMember(Integer MemberId);

	// 通过多种条件模糊分页查询
	public List<Shift> getShiftDuozhongByPage(String begin, String finish,
			Integer goodsId, int pageNow, int rowSize);

	// 通过过多种条件查询出数据的总行数
	public int getShiftDuozhongByCount(String begin, String finish,
			Integer goodsId, int rowSize);

	// 通过挪库状态查询
	public List<Shift> getShiftZhuangtai(String zhuang);

	// 通过库位查询
	public List<Shift> getKuwei(Integer kuweiId);

	// 通过作业人员模糊查询，天车工
	public List<Shift> getWorkingTC(String working, String begin, String finish);

	// 通过作业人员装卸工查询，统计工作量
	public List<Shift> getWorkingZX(String working, String begin, String finish);

	// 通过货物进行查询
	public List<Shift> getGoodsAll(Integer goodsId);

	// 查询所有的挪库订单，以分页的方式显示
	public List<Shift> getShiftAllByPage(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int pageNow, int rowSize);

	// 查询所有的挪库订单的总页数
	public int getShiftAllByPageCount(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int rowSize);

	// 挪库工作量统计查询
	public List<Shift> QueryShiftWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize);

	// 挪库工作量统计页数
	public int QueryShiftWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize);

}
