package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ExportOperateDao;
import com.xinggang.project.entity.ExportOperate;

/**
 * 出库订单操作Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ExportOperateDaoImpl extends BaseDaoImpl implements
		ExportOperateDao {
	// 增加
	public boolean save(ExportOperate exportOperate) {
		return super.BaseSave(exportOperate);
	}

	// 删除
	public boolean delete(ExportOperate exportOperate) {
		return super.BaseDelete(exportOperate);
	}

	// 修改
	public boolean update(ExportOperate exportOperate) {
		return super.BaseUpdate(exportOperate);
	}

	// 通过id查询
	public ExportOperate getExportOperateId(String id) {
		return (ExportOperate) super.findById(ExportOperate.class, id);
	}

	// 通过id查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getExportSeedId(String id) {
		String hql = "from ExportOperate where exportSeed.eseedId='" + id
				+ "' order by eoperateCollectTime";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getAll() {
		String hql = "from ExportOperate";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过全部分页查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getAllByPage(int pageNow, int rowSize) {
		String hql = "from ExportOperate where eoperateDefinedOne !='订单作废' order by eoperateDispatcherTime desc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过全部查询数据总行数
	public int getAllByCount() {
		String hql = "select count(*) from ExportOperate where eoperateDefinedOne !='订单作废' order by eoperateDispatcherTime desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过出库子订单编号查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getZidingdan(String zidingdan) {
		String hql = "from ExportOperate where exportSeed='" + zidingdan
				+ "' order by eoperateId desc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过库位查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getKuwei(Integer kuwei) {
		String hql = "from ExportOperate where tarehouse="
				+ kuwei
				+ " and eoperateDefinedOne !='订单作废' order by eoperateDispatcherTime desc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过内部人员查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getNeibu(Integer neibu) {
		String hql = "from ExportOperate where interiorUserByEoperateCollectMan="
				+ neibu
				+ " or interiorUserByEoperatePonderationMan="
				+ neibu
				+ " or interiorUserByEoperateStoreman="
				+ neibu
				+ " or interiorUserByEoperateAuditing="
				+ neibu
				+ " or interiorUserByEoperateDispatcher="
				+ neibu
				+ " and eoperateDefinedOne !='订单作废' order by eoperateDispatcherTime desc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过工作人员查询天车工，装卸工
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingTC(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where eoperateCraneman like ('%"
				+ working + "%') and eoperateScreateTime > '" + begin
				+ "' and eoperateScreateTime <= '" + finish
				+ "' and eoperateDefinedOne !='订单作废'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过状态查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getZhuangtai(String zhuangtai) {
		String hql = "from ExportOperate where eoperateDefinedOne= '"
				+ zhuangtai + "' order by eoperateDispatcherTime desc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过装卸工查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingZX(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where eoperateStevedore= '" + working
				+ "' and eoperateScreateTime >= '" + begin
				+ "' and eoperateScreateTime <= '" + finish
				+ "' and eoperateDefinedOne !='订单作废'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过保管员去查interiorUserByEoperateStoreman
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingBG(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where interiorUserByEoperateStoreman.iuserLoginName= '"
				+ working
				+ "' and eoperateScreateTime >= '"
				+ begin
				+ "' and eoperateScreateTime <= '" + finish + "'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过调度查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingDU(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where interiorUserByEoperateDispatcher.iuserLoginName= '"
				+ working
				+ "' and eoperateScreateTime >= '"
				+ begin
				+ "' and eoperateScreateTime <= '" + finish + "'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 审核人员
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingSH(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where interiorUserByEoperateAuditing.iuserLoginName= '"
				+ working
				+ "' and eoperateScreateTime >= '"
				+ begin
				+ "' and eoperateScreateTime <= '" + finish + "'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过收费人员查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingSF(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where interiorUserByEoperateCollectMan.iuserLoginName= '"
				+ working
				+ "' and eoperateScreateTime >= '"
				+ begin
				+ "' and eoperateScreateTime <= '" + finish + "'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过司磅人员查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getWorkingSB(String begin, String finish,
			String working) {
		String hql = "from ExportOperate where interiorUserByEoperatePonderationMan.iuserLoginName= '"
				+ working
				+ "' and eoperateScreateTime >= '"
				+ begin
				+ "' and eoperateScreateTime <= '" + finish + "'";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 通过订单状态和保管进行查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getZhuangTaiBaoGuan(String zhuangtai,
			Integer baoguan) {
		String hql = "from ExportOperate where (eoperateDefinedOne= '"
				+ zhuangtai
				+ "' or eoperateDefinedOne ='正在出库' or eoperateDefinedOne = '审核未通过') and interiorUserByEoperateStoreman="
				+ baoguan
				+ " and exportSeed.eseedOrderStatus = '准备出库' order by eoperateDispatcherTime asc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 查询过磅的订单
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getGuoBangAll(String guobang, String kehuhao,
			String jiancheng, int pageNow, int rowSize) {
		String hql = "from ExportOperate where eoperatePonderationTrue='"
				+ guobang
				+ "' and (eoperateDefinedOne ='计划出库' or eoperateDefinedOne ='正在出库' "
				+ "or eoperateDefinedOne ='准备出库' or eoperateDefinedOne = '审核未通过' or eoperateDefinedOne ='正在审核') and exportSeed.export.exportWagonNumber like('%"
				+ kehuhao
				+ "%') and (exportSeed.export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or exportSeed.export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or exportSeed.export.client.clientSign like ('%"
				+ jiancheng
				+ "%')) and exportSeed.eseedOrderStatus = '准备出库' order by eoperateDispatcherTime asc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	public int getGuoBangAllByCount(String guobang, String kehuhao,
			String jiancheng, int rowSize) {
		String hql = "select count(*) from ExportOperate where eoperatePonderationTrue='"
				+ guobang
				+ "' and (eoperateDefinedOne ='计划出库' or eoperateDefinedOne ='正在出库' "
				+ "or eoperateDefinedOne ='准备出库' or eoperateDefinedOne ='审核未通过' or eoperateDefinedOne ='正在审核' ) and exportSeed.export.exportWagonNumber like('%"
				+ kehuhao
				+ "%') and (exportSeed.export.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or exportSeed.export.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or exportSeed.export.client.clientSign like ('%"
				+ jiancheng + "%')) and exportSeed.eseedOrderStatus = '准备出库'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过状态进行分页的显示
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getZhuangTaiByPage(String begin, String finish,
			String danwei, String kehudanhao, String zhuangtai, int pageNow,
			int rowSize) {
		String hql = "from ExportOperate eo where eo.eoperateDispatcherTime >='"
				+ begin
				+ "' and eo.eoperateDispatcherTime <= '"
				+ finish
				+ "' and eo.eoperateDefinedOne= '"
				+ zhuangtai
				+ "' and eo.exportSeed.export.client.clientFirmName like ('%"
				+ danwei
				+ "%') or eo.exportSeed.export.client.clientAbbreviation like ('%"
				+ danwei
				+ "%') or eo.exportSeed.export.client.clientSign like ('%"
				+ danwei
				+ "%') and eo.exportSeed.export.exportWagonNumber like ('%"
				+ kehudanhao + "%')  order by eo.eoperateDispatcherTime asc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 通过状态进行查询数据的总页数
	public int getZhuangTaiByPageCount(String begin, String finish,
			String danwei, String kehudanhao, String zhuangtai, int rowSize) {
		String hql = "select count(*) from ExportOperate where eoperateDispatcherTime >='"
				+ begin
				+ "' and eoperateDispatcherTime <= '"
				+ finish
				+ "' and eoperateDefinedOne= '"
				+ zhuangtai
				+ "' and (exportSeed.export.client.clientFirmName like ('%"
				+ danwei
				+ "%') or exportSeed.export.client.clientAbbreviation like ('%"
				+ danwei
				+ "%') or exportSeed.export.client.clientSign like ('%"
				+ danwei
				+ "%')) and exportSeed.export.exportWagonNumber like ('%"
				+ kehudanhao + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// ---------------------------------后来加入开始

	// 根据条件查询总页数
	public int pageCount(String danwei, String kehudanhao, String zhuangtai,
			int rowSize) {
		String hql = "select count(*) from ExportOperate where  eoperateDefinedOne= '"
				+ zhuangtai
				+ "' and exportSeed.export.client.clientFirmName like ('%"
				+ danwei
				+ "%') and exportSeed.export.exportWagonNumber like ('%"
				+ kehudanhao
				+ "%') or eoperateDefinedOne= '"
				+ zhuangtai
				+ "' and exportSeed.export.client.clientAbbreviation like ('%"
				+ danwei
				+ "%') and exportSeed.export.exportClientNumber like ('%"
				+ kehudanhao + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 根据条件查询信息
	@SuppressWarnings("unchecked")
	public List<ExportOperate> pageAllInfo(String danwei, String kehudanhao,
			String zhuangtai, int pageNow, int rowSize) {
		String hql = "from ExportOperate where eoperateDefinedOne= '"
				+ zhuangtai
				+ "' and exportSeed.export.client.clientFirmName like ('%"
				+ danwei
				+ "%') and exportSeed.export.exportWagonNumber like ('%"
				+ kehudanhao + "%') or eoperateDefinedOne= '" + zhuangtai
				+ "' and exportSeed.export.client.clientAbbreviation like ('%"
				+ danwei
				+ "%') and exportSeed.export.exportClientNumber like ('%"
				+ kehudanhao + "%') order by eoperateDispatcherTime asc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// ---------------------------------后来加入结束

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getExportOperateByPage(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int pageNow, int rowSize) {

		// 编号，收费员，库位，司磅,保管，子订单，，审核，调度，操作重量，操作件数，调度操作时间，过磅时间，是否理算，保管开始操作时间，保管结束操作时间，天车工，装卸工，
		// 审核次数，审核时间,收费时间，实收费用，订单状态，分配重量,备注，对应车号，二次作业重量，二次作业应收费用，结算方式，应收费用，支付方式
		String hql = "select new ExportOperate(eo.eoperateId,eo.interiorUserByEoperateCollectMan,eo.tarehouse,eo.interiorUserByEoperatePonderationMan,"
				+ " eo.interiorUserByEoperateStoreman,eo.exportSeed,eo.interiorUserByEoperateAuditing,eo.interiorUserByEoperateDispatcher,eo.eoperateRealityWeight,"
				+ " eo.eoperateRealityNumber,eo.eoperateDispatcherTime,eo.eoperatePonderationTime,eo.eoperatePonderationTrue,eo.eoperateScreateTime,eo.eoperateSfinishTime,"
				+ "eo.eoperateCraneman,eo.eoperateStevedore,eo.eoperateAuditingTrue,eo.eoperateAuditingTime,eo.eoperateCollectTime,eo.eoperateRealityCost,eo.eoperateDefinedOne,"
				+ "eo.eoperateDefinedTwo,eo.eoperateRemark,eo.eoperateTruckNum,eo.eoperateDefinedThree,eo.eoperateDefinedFour,eo.eoperateClientAccounts,"
				+ " eo.eoperateShouldCost,eo.eoperatePaymentFashion) "
				+ "from ExportOperate eo, Export e,Client c,Goods g,Tarehouse t,InteriorUser di, "
				+ "InteriorUser si,InteriorUser bi,InteriorUser shi,InteriorUser shoui, ExportSeed es,GoodsStandard gs,"
				+ "GoodsQuality gq,GoodsProperty gp where "
				+ "eo.exportSeed=es.eseedId and es.export = e.exportId and eo.interiorUserByEoperateDispatcher=di.iuserId "
				+ "and eo.interiorUserByEoperatePonderationMan=si.iuserId "
				+ "and eo.interiorUserByEoperateStoreman=bi.iuserId and eo.interiorUserByEoperateAuditing = shi.iuserId "
				+ "and eo.interiorUserByEoperateCollectMan = shoui.iuserId"
				+ " and eo.tarehouse = t.tarehouseId and es.goods = g.goodsId and e.exportId like ('%"
				+ zongdanhao
				+ "%') and e.exportClientNumber like ('%"
				+ kehudanhao
				+ "%') and e.exportReateTime >= '"
				+ begin
				+ "' and e.exportReateTime <= '"
				+ finish
				+ "' and c.clientFirmName like ('%"
				+ danwei
				+ "%') and c.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') and c.clientSign like ('%"
				+ danweiSign
				+ "%') g.goodsName like ('%"
				+ goodsName
				+ "%') and g.goodsSign like ('%"
				+ goodsSign
				+ "%') and gs.goodsStandardName like ('%"
				+ guige
				+ "%') and gq.goodsQualityName like ('%"
				+ caizhi
				+ "%') and gp.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and t.tarehouseName like ('%"
				+ kuweiName
				+ "%') and di.iuserName like ('%"
				+ diaodu
				+ "%') and si.iuserName like ('%"
				+ sibang
				+ "%') and bi.iuserName like ('%"
				+ baoguan
				+ "%') and shi.iuserName like ('%"
				+ shenhe
				+ "%') and shoui.iuserName like ('%"
				+ shoufei
				+ "%') and eo.eoperateDefinedOne !='订单作废'"
				+ " and g.goodsProperty = gp.goodsPropertyId and g.goodsStandard = gs.goodsStandardId "
				+ "and g.goodsQuality=gq.goodsQualityId order by e.exportReateTime desc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询全部，可以通过出库总订单编号，客户订单号，订单生成的时间范围，起始日期，结束日期，客户单位名称全拼，简称，单位助记符，通过货物名称，货物助记符，规格，材质，属性，库位名称，调度员，司磅员，保管员，审核，
	public int getExportOperateBYPageCount(String zongdanhao,
			String kehudanhao, String begin, String finish, String danwei,
			String jiancheng, String danweiSign, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			String kuweiName, String diaodu, String sibang, String baoguan,
			String shenhe, String shoufei, int rowSize) {
		// 编号，收费员，库位，司磅,保管，子订单，，审核，调度，操作重量，操作件数，调度操作时间，过磅时间，是否理算，保管开始操作时间，保管结束操作时间，天车工，装卸工，
		// 审核次数，审核时间,收费时间，实收费用，订单状态，分配重量,备注，对应车号，二次作业重量，二次作业应收费用，结算方式，应收费用，支付方式
		String hql = "select count(*) "
				+ "from ExportOperate eo, Export e,Client c,Goods g,Tarehouse t,InteriorUser di, "
				+ "InteriorUser si,InteriorUser bi,InteriorUser shi,InteriorUser shoui, ExportSeed es ,GoodsStandard gs,"
				+ "GoodsQuality gq,GoodsProperty gp where "
				+ "eo.exportSeed=es.eseedId and es.export = e.exportId and eo.interiorUserByEoperateDispatcher=di.iuserId "
				+ "and eo.interiorUserByEoperatePonderationMan=si.iuserId "
				+ "and eo.interiorUserByEoperateStoreman=bi.iuserId and eo.interiorUserByEoperateAuditing = shi.iuserId "
				+ "and eo.interiorUserByEoperateCollectMan = shoui.iuserId"
				+ " and eo.tarehouse = t.tarehouseId and es.goods = g.goodsId and e.exportId like ('%"
				+ zongdanhao
				+ "%') and e.exportClientNumber like ('%"
				+ kehudanhao
				+ "%') and e.exportReateTime >= '"
				+ begin
				+ "' and e.exportReateTime <= '"
				+ finish
				+ "' and c.clientFirmName like ('%"
				+ danwei
				+ "%') and c.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') and c.clientSign like ('%"
				+ danweiSign
				+ "%') g.goodsName like ('%"
				+ goodsName
				+ "%') and g.goodsSign like ('%"
				+ goodsSign
				+ "%') and gs.goodsStandardName like ('%"
				+ guige
				+ "%') and gq.goodsQualityName like ('%"
				+ caizhi
				+ "%') and gp.goodsPropertyName like ('%"
				+ shuxing
				+ "%') and t.tarehouseName like ('%"
				+ kuweiName
				+ "%') and di.iuserName like ('%"
				+ diaodu
				+ "%') and si.iuserName like ('%"
				+ sibang
				+ "%') and bi.iuserName like ('%"
				+ baoguan
				+ "%') and shi.iuserName like ('%"
				+ shenhe
				+ "%') and shoui.iuserName like ('%"
				+ shoufei
				+ "%') and eo.eoperateDefinedOne !='订单作废' "
				+ "and g.goodsProperty = gp.goodsPropertyId and g.goodsStandard = gs.goodsStandardId "
				+ "and g.goodsQuality=gq.goodsQualityId order by e.exportReateTime desc";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过总订单好，进行查询，判断是否有操作订单产生，如果有那么该订单已经开始操作，不能取消，如果没有则可以取消
	@SuppressWarnings("unchecked")
	public List<ExportOperate> getQuXiaoDingDan(String zongdanhao) {
		String hql = "select new ExportOperate(eo.eoperateId,eo.interiorUserByEoperateCollectMan,eo.tarehouse,eo.interiorUserByEoperatePonderationMan,"
				+ " eo.interiorUserByEoperateStoreman,eo.exportSeed,eo.interiorUserByEoperateAuditing,eo.interiorUserByEoperateDispatcher,eo.eoperateRealityWeight,"
				+ " eo.eoperateRealityNumber,eo.eoperateDispatcherTime,eo.eoperatePonderationTime,eo.eoperatePonderationTrue,eo.eoperateScreateTime,eo.eoperateSfinishTime,"
				+ "eo.eoperateCraneman,eo.eoperateStevedore,eo.eoperateAuditingTrue,eo.eoperateAuditingTime,eo.eoperateCollectTime,eo.eoperateRealityCost,eo.eoperateDefinedOne,"
				+ "eo.eoperateDefinedTwo,eo.eoperateRemark,eo.eoperateTruckNum,eo.eoperateDefinedThree,eo.eoperateDefinedFour,eo.eoperateClientAccounts,"
				+ " eo.eoperateShouldCost,eo.eoperatePaymentFashion) "
				+ "from ExportOperate eo, Export e, ExportSeed es where "
				+ "eo.exportSeed=es.eseedId and es.export = e.exportId and e.exportId = '"
				+ zongdanhao
				+ "'  and eo.eoperateDefinedOne !='订单作废' order by e.exportReateTime desc";
		return (List<ExportOperate>) super.executeQuery(hql, null);
	}

	// 出库工作量统计查询
	@SuppressWarnings("unchecked")
	public List<ExportOperate> QueryExportWorkByPage(String begin,
			String finish, String zhiwu, String name, int pageNow, int rowSize) {
		String hql = "from ExportOperate where eoperateDispatcherTime>='"
				+ begin + "' and eoperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByEoperateDispatcher.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByEoperateStoreman.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("3")) {
				hql += " and interiorUserByEoperatePonderationMan.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("4")) {
				hql += " and interiorUserByEoperateAuditing.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserByEoperateCollectMan.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and eoperateCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and eoperateStevedore like ('%" + name + "%')";
			}
		}

		hql += "order by eoperateDispatcherTime desc";
		return (List<ExportOperate>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 出库工作量统计页数
	public int QueryExportWorkByPageCount(String begin, String finish,
			String zhiwu, String name, int rowSize) {
		String hql = "select count(*) from ExportOperate where eoperateDispatcherTime>='"
				+ begin + "' and eoperateDispatcherTime<='" + finish + "'";
		if (zhiwu != null) {
			if (zhiwu.equals("1")) {
				hql += " and interiorUserByEoperateDispatcher.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("2")) {
				hql += " and interiorUserByEoperateStoreman.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("3")) {
				hql += " and interiorUserByEoperatePonderationMan.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("4")) {
				hql += " and interiorUserByEoperateAuditing.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("5")) {
				hql += " and interiorUserByEoperateCollectMan.iuserName like ('%"
						+ name + "%')";
			}
			if (zhiwu.equals("6")) {
				hql += " and eoperateCraneman like ('%" + name + "%')";
			}
			if (zhiwu.equals("7")) {
				hql += " and eoperateStevedore like ('%" + name + "%')";
			}
		}
		return super.queryPageCount(hql, null, rowSize);
	}

}
