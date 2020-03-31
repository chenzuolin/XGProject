package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.ExportOperate;

/**
 * 出库订单操作Dao
 * 
 * @author Administrator
 * 
 */
public interface ExportOperateDao {
	// 增
	public boolean save(ExportOperate exportOperate);

	// 删
	public boolean delete(ExportOperate exportOperate);

	// 改
	public boolean update(ExportOperate exportOperate);

	// 通过id查询
	public ExportOperate getExportOperateId(String id);

	// 查询全部
	public List<ExportOperate> getAll();

	// 查询全部通过分页显示，传入出库子订单编号
	public List<ExportOperate> getAllByPage(int pageNow, int rowSize);

	// 查询数据的总行数
	public int getAllByCount();

	// 通过id查询
	public List<ExportOperate> getExportSeedId(String id);

	// 通过子订单编号查询
	public List<ExportOperate> getZidingdan(String zidingdan);

	// 通过库位查询
	public List<ExportOperate> getKuwei(Integer kuwei);

	// 通过内部人员查询
	public List<ExportOperate> getNeibu(Integer neibu);

	// 通过天车工查询
	public List<ExportOperate> getWorkingTC(String begin, String finish,
			String working);

	// 通过装卸工查询
	public List<ExportOperate> getWorkingZX(String begin, String finish,
			String working);

	// 通过装卸工查询
	public List<ExportOperate> getWorkingBG(String begin, String finish,
			String working);

	// 通过调度查询
	public List<ExportOperate> getWorkingDU(String begin, String finish,
			String working);

	// 通过审核人员查询
	public List<ExportOperate> getWorkingSH(String begin, String finish,
			String working);

	// 通过司磅人员查询
	public List<ExportOperate> getWorkingSB(String begin, String finish,
			String working);

	// 通过收费人员查询
	public List<ExportOperate> getWorkingSF(String begin, String finish,
			String working);

	// 通过审核状态查询,通过，未通过
	public List<ExportOperate> getZhuangtai(String zhuangtai);

	// 查询订单状态为准备入库的，并且指定的保管员是登录的那个保管
	public List<ExportOperate> getZhuangTaiBaoGuan(String zhuangtai,
			Integer baoguan);

	// 查询操作订单中是过磅的，并且重量为空的
	public List<ExportOperate> getGuoBangAll(String guobang, String kehuhao,
			String jiancheng, int pageNow, int rowSize);

	public int getGuoBangAllByCount(String guobang, String kehuhao,
			String jiancheng, int rowSize);

	// 通过状态进行分页和模糊查询
	public List<ExportOperate> getZhuangTaiByPage(String begin, String finish,
			String danwei, String kehudanhao, String zhuantai, int pageNow,
			int rowSize);

	// 通过状态进行查询页数
	public int getZhuangTaiByPageCount(String begin, String finish,
			String danwei, String kehudanhao, String zhuangtai, int rowSize);

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，
	public List<ExportOperate> getExportOperateByPage(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int pageNow, int rowSize);

	// 查询全部返回页数
	public int getExportOperateBYPageCount(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int rowSize);

	// 通过总订单好，进行查询，判断是否有操作订单产生，如果有那么该订单已经开始操作，不能取消，如果没有则可以取消
	public List<ExportOperate> getQuXiaoDingDan(String zongdanhao);

	// ---------------------------------后来加入开始

	// 根据条件查询总页数
	public int pageCount(String danwei, String kehudanhao, String zhuangtai,
			int rowSize);

	// 根据条件查询总出库操作表信息
	public List<ExportOperate> pageAllInfo(String danwei, String kehudanhao,
			String zhuangtai, int pageNow, int rowSize);

	// ---------------------------------后来加入结束

	// 出库工作量统计查询
	public List<ExportOperate> QueryExportWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize);

	// 出库工作量统计页数
	public int QueryExportWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize);
}
