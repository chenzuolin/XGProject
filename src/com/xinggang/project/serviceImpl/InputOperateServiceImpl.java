package com.xinggang.project.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.InputDao;
import com.xinggang.project.dao.InputOperateDao;
import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.dao.InteriorUserDao;
import com.xinggang.project.dao.PaymentFashionDao;
import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.entity.InputOperate;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.InteriorUser;
import com.xinggang.project.form.InputOperateForm;
import com.xinggang.project.service.InputOperateService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 入库订单操作Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class InputOperateServiceImpl implements InputOperateService {

	// 入库操作编号
	PageRow p = new PageRow();

	// 入库操作dao
	private InputOperateDao inputOperateDao;
	// 入库子订单dao
	private InputSeedDao inputSeedDao;
	// 入库库房dao
	private TarehouseDao tarehouseDao;
	// 入库内部人员dao
	private InteriorUserDao interiorUserDao;
	// 支付方式dao
	private PaymentFashionDao paymentFashionDao;

	public void setPaymentFashionDao(PaymentFashionDao paymentFashionDao) {
		this.paymentFashionDao = paymentFashionDao;
	}

	public void setInputDao(InputDao inputDao) {
	}

	public void setInputSeedDao(InputSeedDao inputSeedDao) {
		this.inputSeedDao = inputSeedDao;
	}

	public void setTarehouseDao(TarehouseDao tarehouseDao) {
		this.tarehouseDao = tarehouseDao;
	}

	public void setInteriorUserDao(InteriorUserDao interiorUserDao) {
		this.interiorUserDao = interiorUserDao;
	}

	public void setInputOperateDao(InputOperateDao inputOperateDao) {
		this.inputOperateDao = inputOperateDao;
	}

	// 增加
	public boolean save(InputOperate inputOperate) {
		return inputOperateDao.save(inputOperate);
	}

	// 删除
	public boolean delete(InputOperate inputOperate) {
		InputOperate i = inputOperateDao.getInputOperateId(inputOperate
				.getIoperateId());
		if (i == null) {
			return false;
		} else {
			return inputOperateDao.delete(inputOperate);
		}
	}

	// 修改
	public boolean update(InputOperate inputOperate) {

		InputOperate i = inputOperateDao.getInputOperateId(inputOperate
				.getIoperateId());
		if (i == null) {
			return false;
		} else {
			return inputOperateDao.update(inputOperate);
		}
	}

	// 通过id查询
	public InputOperate getInputOperateId(String id) {
		return inputOperateDao.getInputOperateId(id);
	}

	// 通过子订单id查询
	public List<InputOperate> getInputSeedId(String id) {
		return inputOperateDao.getInputSeedId(id);
	};

	// 查询全部
	public List<InputOperate> getAll() {
		return inputOperateDao.getAll();
	}

	// 分页查询
	public List<InputOperate> getAllByPage(int pageNow, int rowSize) {
		return inputOperateDao.getAllByPage(pageNow, rowSize);
	}

	// 查询出数据的总行数
	public int getAllByCount() {
		return inputOperateDao.getAllByCount();
	}

	// 通过库位查询
	public List<InputOperate> getKuwei(Integer kuweiId) {
		return inputOperateDao.getKuwei(kuweiId);
	}

	// 通过内部人员查询查询调度员，保管员等
	public List<InputOperate> getNeibu(Integer neibuId) {
		return inputOperateDao.getNeibu(neibuId);
	}

	// 通过保管查询
	public List<InputOperate> getBaoguan(Integer baoguanId) {
		return inputOperateDao.getBaoguan(baoguanId);
	};

	// 查询全部完成的操作订单
	public List<InputOperate> getAllFinallyDingdan(String zongId) {
		return inputOperateDao.getAllFinallyDingdan(zongId);
	};

	// 通过审核状态查询
	public List<InputOperate> getZhuangtai(String zhuangtai) {
		return inputOperateDao.getZhuangtai(zhuangtai);
	}

	// 司磅人员修改重量
	public boolean updateWeight(InputOperateForm inputOperateForm,
			InteriorUser user) {
		PresentTime presentTime = new PresentTime();
		InputOperate inputOperate = inputOperateDao
				.getInputOperateId(inputOperateForm.getIoperateId());
		// 如果件数不为空则，改变订单状态
		if ((inputOperate.getIoperateRealityNumber() != null && !inputOperate
				.getIoperateRealityNumber().equals(""))
				&& inputOperate.getIoperatePonderationTrue().equals("过磅")) {
			inputOperate.setIoperateDefinedTwo("正在审核");
		}
		// 修改重量
		inputOperate.setIoperateRealityWeight(inputOperateForm
				.getIoperateRealityWeight());
		inputOperate.setIoperatePonderationTime(presentTime.getTimes());
		inputOperate.setInteriorUserByIoperatePonderationManId(user);
		inputOperate.setIoperateRemark(inputOperate.getIoperateRemark() + ";"
				+ inputOperateForm.getIoperateRemark());
		boolean ok = this.update(inputOperate);
		return ok;
	}

	public boolean shenHeStatus(InputOperateForm inputOperateForm) {
		PresentTime presentTime = new PresentTime();
		InputOperate inputOperate = inputOperateDao
				.getInputOperateId(inputOperateForm.getIoperateId());
		inputOperate.setIoperateAuditingTime(presentTime.getTimes());
		inputOperate.setIoperateAuditingTrue(inputOperateForm
				.getIoperateAuditingTrue());
		boolean ok = inputOperateDao.update(inputOperate);
		return ok;
	}

	// 调度员保存入库操作表
	public boolean saveInputOperat(InputOperateForm inputOperateForm,
			InteriorUser user) {
		// 得到子订单，进行字符串截取
		String ziID = inputOperateForm.getInputSeed();
		// 截取子订单中的id后5位，作为操作订单id
		String newStrId = ziID.substring(10, ziID.length());
		// 得到封装时间类
		PresentTime presentTime = new PresentTime();
		boolean ok = false;
		Integer[] kuwei = inputOperateForm.getKuwei();
		if (kuwei.length > 0) {
			for (int i = 0; i < kuwei.length; i++) {
				String newID = "";
				String is = "";
				// 根据id查询是否有重复的值
				List<InputOperate> inOp = inputOperateDao
						.getInputSeedId(inputOperateForm.getInputSeed());
				if (inOp.size() > 0) {
					// 得到"入C"两个字
					String str = inOp.get(0).getIoperateId().substring(0, 10);
					System.out.println("str" + str);
					int r = 0;
					for (int j = 0; j < inOp.size(); j++) {
						// 得到操作订单id
						String id = inOp.get(j).getIoperateId();
						// 截取后两位
						String cId = id.substring(id.length() - 2, id.length());
						// 转为int
						int number = Integer.parseInt(cId);
						// 比较，把最大的值付给r
						if (number > r) {
							r = number;
						}
					}
					if (r + 1 < 10) {
						is = "0" + (r + 1);
					} else {
						is = String.valueOf(r + 1);
					}
					// 新的操作订单id
					newID = str + newStrId + is;

				} else {

					if ((i + 1) < 10) {
						is = "0" + (i + 1);
					} else {
						is = String.valueOf(i + 1);
					}

					newID = "入C" + presentTime.getDatesNotTime() + newStrId
							+ is;
				}

				InputOperate inputOperate = new InputOperate();
				inputOperate.setIoperateId(newID);// 操作编号
				inputOperate.setInteriorUserByIoperateDispatcherId(user);// 调度员,对应的内部人员类
				inputOperate
						.setTarehouse(tarehouseDao.getTarehouseId(kuwei[i]));// 对应的库位
				inputOperate.setInputSeed(inputSeedDao
						.getInputSeedId(inputOperateForm.getInputSeed()));// 对应的入库子订单编号，入库子订单类
				inputOperate
						.setInteriorUserByIoperateStoremanId(interiorUserDao
								.getInteriorUserId(inputOperateForm
										.getInteriorUserByIoperateStoremanId()));// 现场保管员，对应的内部人员类
				inputOperate.setIoperateDefinedTwo("准备入库");// 修改操作表订单状态
				inputOperate.setIoperateDispatcherTime(presentTime.getTimes());// 调度员操作时间
				inputOperate.setIoperatePonderationTrue(inputOperateForm
						.getIoperatePonderationTrue());// 过磅还是理算货物(过磅/理算)
				inputOperate.setIoperateRemark(inputOperateForm
						.getIoperateRemark());// 备注

				ok = this.save(inputOperate);
			}
		}
		return ok;
	}

	DecimalFormat wdf = new DecimalFormat("#########0.000");
	DecimalFormat ndf = new DecimalFormat("##########0.00");

	// 保管员修改操作信息
	public boolean updateInputOperat(InputOperateForm inputOperateForm) {

		String strZX = "";

		// 得到封装时间类
		PresentTime presentTime = new PresentTime();
		// 取得操作id,查询后修改,重量和件数在原来的基础上增加
		InputOperate inputOperate = this.getInputOperateId(inputOperateForm
				.getIoperateId());

		inputOperate.setInteriorUserByIoperatePonderationManId(interiorUserDao
				.getInteriorUserId(inputOperateForm
						.getInteriorUserByIoperatePonderationManId()));// 司磅员,对应的内部人员类
		inputOperate.setIoperatePonderationTime(inputOperateForm
				.getIoperatePonderationTime());// 过磅时间
		inputOperate.setIoperateSfinishTime(presentTime.getTimes());// 现场保管员结束操作时间
		inputOperate
				.setIoperateCraneman(inputOperateForm.getIoperateCraneman());// 天车工
		inputOperate
				.setIoperateTruckNum(inputOperateForm.getIoperateTruckNum());// 车牌号

		// 获取多个装卸工，然后遍历后保存在字符串中
		String[] zhuangStr = inputOperateForm.getZhuangxieGong();
		for (int i = 0; i < zhuangStr.length; i++) {
			strZX = strZX + zhuangStr[i] + ",";
		}
		// 保存装卸工
		inputOperate.setIoperateStevedore(strZX);// 装卸工

		inputOperate.setInteriorUserByIoperatePonderationManId(interiorUserDao
				.getInteriorUserId(inputOperateForm
						.getInteriorUserByIoperatePonderationManId()));// 司磅员,对应的内部人员类
		inputOperate.setIoperatePonderationTime(inputOperateForm
				.getIoperatePonderationTime());// 过磅时间

		// 如果是理算货物，由保管员添加如果不是，则由司磅添加
		if (inputOperateForm.getIoperatePonderationTrue().equals("理算")
				|| inputOperateForm.getIoperatePonderationTrue() == "理算") {
			// 如果是理算货物，修改操作表订单状态
			inputOperate.setIoperateDefinedTwo("正在审核");
			// 在原来的基础上增加重量
			if (inputOperate.getIoperateRealityWeight() != null) {
				inputOperate.setIoperateRealityWeight(Double.parseDouble(wdf
						.format(inputOperateForm.getIoperateRealityWeight())));// 对应人员的入库重量
			} else {
				inputOperate.setIoperateRealityWeight(inputOperateForm
						.getIoperateRealityWeight());// 对应人员的入库重量
			}
		}

		// 在原来的基础上增加件数
		if (inputOperate.getIoperateRealityNumber() != null) {
			inputOperate.setIoperateRealityNumber(inputOperateForm
					.getIoperateRealityNumber());// 对应人员的入库件数
		} else {
			inputOperate.setIoperateRealityNumber(inputOperateForm
					.getIoperateRealityNumber());// 对应人员的入库件数
		}

		inputOperate.setIoperateRemark(inputOperate.getIoperateRemark()
				+ ";操作员：" + inputOperateForm.getIoperateRemark());// 备注

		boolean ok = this.update(inputOperate);

		return ok;

	}

	// 审核人员修改操作信息
	public boolean SHupdateInputOperat(InteriorUser user, Double shouMoney,
			String caozuoId, String beizhu) {
		// 得到封装时间类
		PresentTime presentTime = new PresentTime();
		// 取得操作id,查询后修改
		InputOperate inputOperate = this.getInputOperateId(caozuoId);
		inputOperate.setInteriorUserByIoperateAuditingId(user);// 审核人员,对应的内部人员类
		inputOperate.setIoperateAuditingTime(presentTime.getTimes());// 审核时间
		inputOperate.setIoperateDefinedTwo("审核通过");// 修改操作表订单状态
		// 在原来基础上增加收费,判断有时候为null

		try {
			if (inputOperate.getIoperateShouldCost() == null
					|| inputOperate.getIoperateShouldCost().equals("")) {
				inputOperate.setIoperateShouldCost(shouMoney);

			} else {
				inputOperate.setIoperateShouldCost(inputOperate
						.getIoperateShouldCost() + shouMoney);// 实收费用
			}
			if (inputOperate.getIoperateAuditingTrue() == null) {
				inputOperate.setIoperateDefinedOne("1");// 审核次数
			} else {
				int shenHeCishu = Integer.parseInt(inputOperate
						.getIoperateDefinedOne());
				// 每次加入审核次数
				inputOperate.setIoperateDefinedOne(String
						.valueOf(shenHeCishu++));// 审核次数
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		inputOperate.setIoperateAuditingTrue("通过");// 审核是否通过（通过/未通过）
		inputOperate.setIoperateRemark(inputOperate.getIoperateRemark()
				+ ";审核:" + beizhu);// 备注
		boolean ok = this.update(inputOperate);
		return ok;
	}

	// 订单未通过时，审核人员修改操作信息
	public boolean SHupdateInputOperatTwo(InteriorUser user, String caozuoId,
			String beizhu) {
		// 得到封装时间类
		PresentTime presentTime = new PresentTime();
		// 取得操作id,查询后修改
		InputOperate inputOperate = this.getInputOperateId(caozuoId);
		inputOperate.setInteriorUserByIoperateAuditingId(user);// 审核人员,对应的内部人员类
		inputOperate.setIoperateAuditingTime(presentTime.getTimes());// 审核时间
		inputOperate.setIoperateDefinedTwo("准备入库");// 修改操作表订单状态
		if (inputOperate.getIoperateAuditingTrue() == null) {
			inputOperate.setIoperateDefinedOne("1");// 审核次数
		} else {
			int shenHeCishu = Integer.parseInt(inputOperate
					.getIoperateDefinedOne());
			// 每次加入审核次数
			inputOperate.setIoperateDefinedOne(String.valueOf(shenHeCishu++));// 审核次数
		}
		inputOperate.setIoperateRemark(inputOperate.getIoperateRemark()
				+ ";审核:" + beizhu);// 备注
		inputOperate.setIoperateAuditingTrue("未通过");// 审核是否通过（通过/未通过）
		boolean ok = this.update(inputOperate);
		return ok;
	}

	// 收费人员收费，填入信息
	public boolean updateMoney(InputOperateForm inputOperateForm,
			InteriorUser user) {
		// 得到封装时间类
		PresentTime presentTime = new PresentTime();
		InputOperate inputOperate = this.getInputOperateId(inputOperateForm
				.getIoperateId());

		InputSeed inputSeed = this.inputSeedDao.getInputSeedId(inputOperate
				.getInputSeed().getIseedId());// 通过编号查询子订单 // 月结/现结
		// 判断是月结客户，并且收费为空时，改变操作状态为"未收费"，否则，状态为"入库完成"

		inputOperate.setIoperatePaymentFashion(paymentFashionDao
				.getPaymentFashionId(inputOperateForm
						.getIoperatePaymentFashion()));// 修改操作表中的支付方式

		inputOperate.setIoperateCollectTime(presentTime.getTimes());// 设置收费时间

		if (inputOperateForm.getIoperateClientAccounts().equals("月结")) {
			// 修改订单状态
			inputOperate.setIoperateDefinedTwo("未收费");
			inputOperate.setIoperateCollectTime(presentTime.getTimes());// 收费时间为空
			// 如果未收费，实收费用改为null
			inputOperate.setIoperateRealityCost(null);
			inputOperate.setIoperateClientAccounts("月结");
			inputSeed.setIseedClientAccounts("月结");// 设置子订单中的结算方式为月结

		} else {
			// 如果不是“月结”设置子订单的票据类型，支付方式
			inputSeed.setPaymentFashion(paymentFashionDao
					.getPaymentFashionId(inputOperateForm
							.getIoperatePaymentFashion()));// 修改子订单支付方式
			inputOperate.setIoperatePaymentFashion(paymentFashionDao
					.getPaymentFashionId(inputOperateForm
							.getIoperatePaymentFashion()));// 修改操作表中的支付方式
			// 修改操作订单状态
			inputOperate.setIoperateDefinedTwo("入库完成");
			inputOperate.setIoperateCollectTime(presentTime.getTimes());// 收费时间
			inputOperate.setIoperateRealityCost(inputOperateForm
					.getIoperateRealityCost());
			inputOperate.setIoperateClientAccounts("日结");// 设置操作订单中的结算方式为日结
			inputSeed.setIseedClientAccounts("日结");// 设置子订单中的结算方式为日结
			if (inputSeed.getIseedRealityCost() != null) {// 子订单中的实收费用累加
				inputSeed.setIseedRealityCost(inputSeed.getIseedRealityCost()
						+ inputOperateForm.getIoperateRealityCost());
			} else {
				inputSeed.setIseedRealityCost(inputOperateForm
						.getIoperateRealityCost());
			}

		}
		inputSeedDao.update(inputSeed);// 修改子订单中的结算方式
		inputOperate.setInteriorUserByIoperateCollectManId(user);// 收费人员
		inputOperate.setIoperateRemark(inputOperate.getIoperateRemark()
				+ ";收费:" + inputOperateForm.getIoperateRemark());
		boolean ok = this.update(inputOperate);
		return ok;
	}

	// 根据客户姓名、订单生产时间、货主、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得到所有,根据收费时间排序
	public List<InputOperate> getInputOperateByPage2(String dingDanHao,
			String clientName, String inputTime, String chehao,
			String sijiName, String yunshuStyle, String dingdanYesNo,
			String huowuName, String dingdanStatus, String kuwei,
			String diaodu, String baoguan, String tianche, String zhuangxie,
			String sibang, String shenhe, String shenheTime, String shoufei,
			String shoufeiTime, String chandi, String beginTime,
			String finishTime, int pageNow, int rowSize) {
		return this.inputOperateDao.getInputOperateByPage2(dingDanHao,
				clientName, inputTime, chehao, sijiName, yunshuStyle,
				dingdanYesNo, huowuName, dingdanStatus, kuwei, diaodu, baoguan,
				tianche, zhuangxie, sibang, shenhe, shenheTime, shoufei,
				shoufeiTime, chandi, beginTime, finishTime, pageNow, rowSize);
	}

	// 分页查询所有,根据收费时间排序
	public List<InputOperate> getInputOperateByPage(int pageNow, int rowSize) {
		return this.inputOperateDao.getInputOperateByPage(pageNow, rowSize);
	}

	// 分页查询分页页数
	public int getPageCount(int pageSize) {
		return this.inputOperateDao.getPageCount(pageSize);
	}

	// 根据客户姓名、订单生产时间、货主、车号、司机姓名、运输方式、订单是否作废、货物名称、订单状态
	// 库位、调度员、保管员、天车工、装卸工、司磅员、审核人员、审核时间、收费员、收费时间得分页页数
	public int getPageCountTwo(String dingDanHao, String clientName,
			String inputTime, String chehao, String sijiName,
			String yunshuStyle, String dingdanYesNo, String huowuName,
			String dingdanStatus, String kuwei, String diaodu, String baoguan,
			String tianche, String zhuangxie, String sibang, String shenhe,
			String shenheTime, String shoufei, String shoufeiTime,
			String chandi, String beginTime, String finishTime, int pageSize) {
		return this.inputOperateDao.getPageCountTwo(dingDanHao, clientName,
				inputTime, chehao, sijiName, yunshuStyle, dingdanYesNo,
				huowuName, dingdanStatus, kuwei, diaodu, baoguan, tianche,
				zhuangxie, sibang, shenhe, shenheTime, shoufei, shoufeiTime,
				beginTime, chandi, finishTime, pageSize);
	}

	// 修改操作表中的状态
	public boolean updateinputOperateStatus(String id, String status) {
		InputOperate inputOperate = inputOperateDao.getInputOperateId(id);
		inputOperate.setIoperateDefinedTwo(status);
		boolean ok = inputOperateDao.update(inputOperate);
		return ok;
	}

	// 返回所有内部人员查询分页页数
	public int count(String status, String danhao, String huozhu, int pageSize) {
		return inputOperateDao.count(status, danhao, huozhu, pageSize);
	};

	// 返回所有内部人员查询分页的信息
	public List<InputOperate> getInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize) {
		return inputOperateDao
				.getInfo(status, danhao, huozhu, pageNow, rowSize);
	};

	// 通过司磅人员查询
	public List<InputOperate> getSBInfo(String status, String danhao,
			String huozhu, int pageNow, int rowSize) {
		return inputOperateDao.getSBInfo(status, danhao, huozhu, pageNow,
				rowSize);
	};

	// 通过司磅人员查询的行数
	public int getSBCount(String status, String danhao, String huozhu,
			int pageSize) {
		return inputOperateDao.getSBCount(status, danhao, huozhu, pageSize);
	}

	// 入库工作量统计查询
	public List<InputOperate> QueryInputWorkByPage(String begin, String finish,
			String zhiwu, String name, int pageNow, int rowSize) {
		return this.inputOperateDao.QueryInputWorkByPage(begin, finish, zhiwu,
				name, pageNow, rowSize);
	}

	// 入库工作量统计页数
	public int QueryInputWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		return this.inputOperateDao.QueryInputWorkByPageCount(begin, finish,
				zhiwu, name, rowSize);
	}

	// 统计输入的该人员的入库合计
	public Double QueryInputHeJi(String begin, String finish, String zhiwu,
			String name) {
		return this.inputOperateDao.QueryInputHeJi(begin, finish, zhiwu, name);
	}

	// 统计输入的该人员的出库合计
	public Double QueryExportHeJi(String begin, String finish, String zhiwu,
			String name) {
		return this.inputOperateDao.QueryExportHeJi(begin, finish, zhiwu, name);
	}

	// 统计输入的该人员的过户合计
	public Double QueryShiftStockHeJi(String begin, String finish,
			String zhiwu, String name) {
		return this.inputOperateDao.QueryShiftStockHeJi(begin, finish, zhiwu,
				name);
	}

	// 统计输入的该人员的挪库合计
	public Double QueryShiftHeJi(String begin, String finish, String zhiwu,
			String name) {
		return this.inputOperateDao.QueryShiftHeJi(begin, finish, zhiwu, name);
	}

	// 统计短倒作业量
	public Double QueryDuanDaoHeJi(String begin, String finish, String zhiwu,
			String name) {
		return this.inputOperateDao
				.QueryDuanDaoHeJi(begin, finish, zhiwu, name);
	}
}
