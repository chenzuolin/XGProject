package com.xinggang.project.serviceImpl;

import java.util.List;

import com.xinggang.project.dao.ExportOperateDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.InputOperateDao;
import com.xinggang.project.dao.InteriorUserDao;
import com.xinggang.project.dao.ShiftDao;
import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.entity.ExportOperate;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.entity.Shift;
import com.xinggang.project.form.ShiftForm;
import com.xinggang.project.service.ShiftService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;
import com.xinggang.project.tools.StatisticsWorking;

/**
 * 挪库Service
 * 
 * @author Administrator
 * 
 */
public class ShiftServiceImpl implements ShiftService {
	private ShiftDao shiftDao;
	// 货物dao
	private GoodsDao goodsDao;
	// 库位dao
	private TarehouseDao tarehouseDao;
	// 内部人员dao
	private InteriorUserDao interiorUserDao;
	// 入库操作dao
	private InputOperateDao inputOperateDao;
	// 出库操作dao
	private ExportOperateDao exportOperateDao;
	// 统计工作量工具类
	private StatisticsWorking tongji = new StatisticsWorking();

	private PageRow pr = new PageRow();

	private PresentTime pt = new PresentTime();

	public void setInputOperateDao(InputOperateDao inputOperateDao) {
		this.inputOperateDao = inputOperateDao;
	}

	public void setExportOperateDao(ExportOperateDao exportOperateDao) {
		this.exportOperateDao = exportOperateDao;
	}

	public void setShiftDao(ShiftDao shiftDao) {
		this.shiftDao = shiftDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setInteriorUserDao(InteriorUserDao interiorUserDao) {
		this.interiorUserDao = interiorUserDao;
	}

	public void setTarehouseDao(TarehouseDao tarehouseDao) {
		this.tarehouseDao = tarehouseDao;
	}

	// 增加
	public boolean save(Shift shift) {

		return shiftDao.save(shift);
	}

	// 删除
	public boolean delete(Shift shift) {
		Shift s = shiftDao.getShiftId(shift.getShiftId());
		if (s == null) {
			return false;
		}
		return shiftDao.delete(shift);
	}

	// 修改
	public boolean update(Shift shift) {
		Shift s = shiftDao.getShiftId(shift.getShiftId());
		if (s == null) {
			return false;
		}
		return shiftDao.update(shift);
	}

	// 通过id查询
	public Shift getShiftId(String id) {

		return shiftDao.getShiftId(id);
	}

	// 查询全部
	public List<Shift> getAll() {

		return shiftDao.getAll();
	}

	// 全部分页查询
	public List<Shift> getShiftByPage(int pageNow, int rowSize) {

		return shiftDao.getShiftByPage(pageNow, rowSize);
	}

	// 全部数据总行数
	public int getShiftByCount() {

		return shiftDao.getShiftByCount();
	}

	// 通过挪库编号模糊查询
	public List<Shift> getShiftLike(String shiftId) {

		return shiftDao.getShiftLike(shiftId);
	}

	// 通过内部人员查询
	public List<Shift> getShiftMember(Integer MemberId) {

		return shiftDao.getShiftMember(MemberId);
	}

	// 通过多种条件模糊分页查询
	public List<Shift> getShiftDuozhongByPage(String begin, String finish,
			Integer goodsId, int pageNow, int rowSize) {

		return shiftDao.getShiftDuozhongByPage(begin, finish, goodsId, pageNow,
				rowSize);
	}

	// 通过多种条件查询数据总行数
	public int getShiftDuozhongByCount(String begin, String finish,
			Integer goodsId, int rowSize) {

		return shiftDao
				.getShiftDuozhongByCount(begin, finish, goodsId, rowSize);
	}

	// 通过挪库状态查询
	public List<Shift> getShiftZhuangtai(String zhuang) {

		return shiftDao.getShiftZhuangtai(zhuang);
	}

	// 通过库位查询
	public List<Shift> getKuwei(Integer kuweiId) {

		return shiftDao.getKuwei(kuweiId);
	}

	// 发起挪库
	public boolean faqiShift(ShiftForm shiftForm) {
		int num = 0;
		for (int i = 0; i < shiftForm.getGoodsId().length; i++) {
			Shift shift = new Shift();
			shift.setShiftId("挪" + pt.getDatesNotTime() + pr.getNuoKuNumber());// 设置挪库编号，挪+当前的系统时间+发起人的编号
			shift.setShiftTime(pt.getTimes());// 挪库的发起时间
			shift.setInteriorUserByShiftFounderMember(this.interiorUserDao
					.getInteriorUserId(shiftForm
							.getInteriorUserByShiftFounderMember()));// 设置挪库发起人
			shift.setTarehouseByShiftPast(this.tarehouseDao
					.getTarehouseId(shiftForm.getTarehouseByShiftPast()));// 设置原库位
			shift.setTarehouseByShiftNew(this.tarehouseDao
					.getTarehouseId(shiftForm.getTarehouseByShiftNew()));// 设置新库位
			shift.setGoods(this.goodsDao.getGoodsId(shiftForm.getGoodsId()[i]));// 设置挪库的货物
			shift.setShiftWeight(shiftForm.getShiftWeights()[i]);// 设置挪库的重量
			shift.setShiftNumber(shiftForm.getShiftNumbers()[i]);// 设置挪库的件数
			shift.setInteriorUserByShiftExecutor(this.interiorUserDao
					.getInteriorUserId(shiftForm
							.getInteriorUserByShiftExecutor()));// 设置挪库执行人
			shift.setShiftDefinedOne("计划挪库");// 设置挪库的状态
			shift.setShiftRemark(shiftForm.getRemark()[i]);// 设置挪库备注
			this.shiftDao.save(shift);// 向数据库中添加数据
			num++;
		}

		if (num == shiftForm.getGoodsId().length) {
			return true;
		} else {
			return false;
		}
	}

	// 通过货物查询
	public List<Shift> getGoodsAll(Integer goodsId) {
		return this.shiftDao.getGoodsAll(goodsId);
	}

	// 统计天车工的工作量
	public double[] TJtianche(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getShiftCraneman() == null) {
			shiftForm.setShiftCraneman("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}
		List<Shift> slist = this.shiftDao.getWorkingTC(
				shiftForm.getShiftCraneman(), shiftForm.getBegin(),
				shiftForm.getFinish());// 挪库中查询出天车工
		List<InputOperate> iolist = this.inputOperateDao.getWorkingTC(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShiftCraneman());// 从入库订单操作中查询天车工
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingTC(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShiftCraneman());// 从出库订单操作中查询出天车工

		double[] Count = new double[] { 0.0 };
		for (Shift s : slist) {
			Count[0] += s.getShiftNumber();// 挪库件数相加
			Count[1] += s.getShiftWeight();// 挪库重量相加
		}
		for (InputOperate io : iolist) {
			Count[0] += io.getIoperateRealityNumber();// 入库的件数相加
			Count[1] += io.getIoperateRealityWeight(); // 入库的重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 出库的件数相加
			Double erci = 0.0;
			if (eo.getEoperateDefinedThree() != null) {
				erci = Double.parseDouble(eo.getEoperateDefinedThree());
			}
			Count[1] += eo.getEoperateRealityWeight() + erci; // 出库的重量相加

		}

		return Count;
	}

	// 统计装卸工的工作量
	public double[] TJzhuangxie(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getShiftStevedore() == null) {
			shiftForm.setShiftStevedore("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}
		List<Shift> slist = this.shiftDao.getWorkingZX(
				shiftForm.getShiftStevedore(), shiftForm.getBegin(),
				shiftForm.getFinish());// 挪库中查询装卸工
		List<InputOperate> iolist = this.inputOperateDao.getWorkingZX(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShiftStevedore());// 入库订单操作中查询装卸工
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingZX(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShiftStevedore());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (Shift s : slist) {
			String str = s.getShiftStevedore();// 取出装卸工
			int count = tongji.countInnerStr(str, ",");
			Count[0] += s.getShiftNumber() / count;// 件数相加
			Count[1] += s.getShiftWeight() / count;// 重量相加
		}
		for (InputOperate io : iolist) {
			String str = io.getIoperateStevedore();// 入库订单操作中取出装卸工
			int count = tongji.countInnerStr(str, ",");
			Count[0] += io.getIoperateRealityNumber() / count;// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight() / count;// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			String str = eo.getEoperateStevedore();// 出库订单操作中取出装卸工
			int count = tongji.countInnerStr(str, ",");
			Count[0] += eo.getEoperateRealityNumber() / count;// 件数相加
			Count[1] += eo.getEoperateRealityWeight() / count;// 件数相加
		}

		return Count;
	}

	// 统计保管的工作量
	public double[] BGbaoguan(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		InteriorUser interiorUser = interiorUserDao.getInteriorUserId(shiftForm
				.getInteriorUserByShiftExecutor());

		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}

		List<InputOperate> iolist = this.inputOperateDao.getWorkingBG(
				shiftForm.getBegin(), shiftForm.getFinish(),
				interiorUser.getIuserName());// 入库订单操作中查询保管
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingBG(
				shiftForm.getBegin(), shiftForm.getFinish(),
				interiorUser.getIuserName());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (InputOperate io : iolist) {

			Count[0] += io.getIoperateRealityNumber();// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight();// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 件数相加
			Count[1] += eo.getEoperateRealityWeight();// 件数相加
		}

		return Count;
	}

	// 统计调度的工作量
	public double[] DUduaodu(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getDiaodu() == null) {
			shiftForm.setDiaodu("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}

		List<InputOperate> iolist = this.inputOperateDao.getWorkingDU(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getDiaodu());// 入库订单操作中查询装卸工
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingDU(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getDiaodu());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (InputOperate io : iolist) {

			Count[0] += io.getIoperateRealityNumber();// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight();// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 件数相加
			Count[1] += eo.getEoperateRealityWeight();// 件数相加
		}

		return Count;
	}

	// 统计审核人员的工作量
	public double[] SHshenhe(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getShenhe() == null) {
			shiftForm.setShenhe("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}
		List<InputOperate> iolist = this.inputOperateDao.getWorkingSH(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShenhe());// 入库订单操作中查询审核人员
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingSH(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShenhe());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (InputOperate io : iolist) {

			Count[0] += io.getIoperateRealityNumber();// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight();// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 件数相加
			Count[1] += eo.getEoperateRealityWeight();// 件数相加
		}

		return Count;
	}

	// 统计收费人员的工作量
	public double[] SFshoufei(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getShoufei() == null) {
			shiftForm.setShoufei("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}

		List<InputOperate> iolist = this.inputOperateDao.getWorkingSF(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShoufei());// 入库订单操作中查询收费人员
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingSF(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getShoufei());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (InputOperate io : iolist) {

			Count[0] += io.getIoperateRealityNumber();// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight();// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 件数相加
			Count[1] += eo.getEoperateRealityWeight();// 件数相加
		}

		return Count;
	}

	// 统计司磅人员的工作量
	public double[] SBsibang(ShiftForm shiftForm) {
		if (shiftForm == null) {
			double count[] = { 0.0, 0.0 };
			return count;
		}
		if (shiftForm.getSibang() == null) {
			shiftForm.setSibang("");
		}
		if (shiftForm.getBegin() == null) {
			shiftForm.setBegin("2015-6-6 12:12:12");
		}
		if (shiftForm.getFinish() == null) {
			shiftForm.setFinish(pt.getTimes());
		}

		List<InputOperate> iolist = this.inputOperateDao.getWorkingSB(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getSibang());// 入库订单操作中查询司磅人员
		List<ExportOperate> eolist = this.exportOperateDao.getWorkingSB(
				shiftForm.getBegin(), shiftForm.getFinish(),
				shiftForm.getSibang());// 从出库订单操作中查询
		double[] Count = new double[] { 0.0 };
		for (InputOperate io : iolist) {

			Count[0] += io.getIoperateRealityNumber();// 入库订单操作中的件数相加
			Count[1] += io.getIoperateRealityWeight();// 入库订单操作总重量相加
		}
		for (ExportOperate eo : eolist) {
			Count[0] += eo.getEoperateRealityNumber();// 件数相加
			Count[1] += eo.getEoperateRealityWeight();// 件数相加
		}

		return Count;
	}

	// 查询所有的挪库订单，以分页的方式显示
	public List<Shift> getShiftAllByPage(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int pageNow, int rowSize) {
		return this.shiftDao.getShiftAllByPage(begin, finish, goodsName, guige,
				caizhi, shuxing, zhujifu, kuweiname, pageNow, rowSize);
	}

	// 查询所有挪库订单的总页数
	public int getShiftAllByPageCount(String begin, String finish,
			String goodsName, String guige, String caizhi, String shuxing,
			String zhujifu, String kuweiname, int rowSize) {
		return this.shiftDao.getShiftAllByPageCount(begin, finish, goodsName,
				guige, caizhi, shuxing, zhujifu, kuweiname, rowSize);
	}

	// 挪库工作量统计查询
	public List<Shift> QueryShiftWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {
		return this.shiftDao.QueryShiftWorkByPage(begin, finish, zhiwu, name,
				pageNow, rowSize);
	}

	// 挪库工作量统计页数
	public int QueryShiftWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		return this.shiftDao.QueryShiftWorkByPageCount(begin, finish, zhiwu, name,
				rowSize);
	}

}
