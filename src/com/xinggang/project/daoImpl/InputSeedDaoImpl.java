package com.xinggang.project.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.entity.ExportSeed;
import com.xinggang.project.entity.InputSeed;
import com.xinggang.project.entity.ShiftStockSeed;

/**
 * 入库子订单接口实现类
 * 
 * @author Administrator
 * 
 */
public class InputSeedDaoImpl extends BaseDaoImpl implements InputSeedDao {
	// 增
	public boolean save(InputSeed inputSeed) {
		return super.BaseSave(inputSeed);
	}

	// 删,此项目中不能删除
	public boolean delete(InputSeed inputSeed) {
		InputSeed i = (InputSeed) super.findById(InputSeed.class,
				inputSeed.getIseedId());
		i.setIseedOrderStatus("订单作废");
		return super.BaseUpdate(i);
	}

	// 改
	public boolean update(InputSeed inputSeed) {
		return super.BaseUpdate(inputSeed);
	}

	// 通过id查询
	public InputSeed getInputSeedId(String id) {
		return (InputSeed) super.findById(InputSeed.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<InputSeed> getAll() {
		String hql = "from InputSeed where iseedOrderStatus != '订单作废' order by iseedId desc ";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过货物编号查询
	@SuppressWarnings("unchecked")
	public List<InputSeed> getGoodsId(Integer goodsId) {
		String hql = "from InputSeed  where iseedOrderStatus != '订单作废' and goods="
				+ goodsId;
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过入库总订单编号查询
	@SuppressWarnings("unchecked")
	public List<InputSeed> getInputId(String inputId) {
		String hql = "from InputSeed where iseedOrderStatus != '订单作废' and input.inputId="
				+ inputId;
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过子订单状态查询
	@SuppressWarnings("unchecked")
	public List<InputSeed> getZhuangtai(String zhuang) {
		String hql = "from InputSeed where iseedOrderStatus='" + zhuang + "'";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过结算方式查询,现结/月结
	@SuppressWarnings("unchecked")
	public List<InputSeed> getJiesuan(String jiesuan) {
		String hql = "from InputSeed where iseedOrderStatus != '订单作废' and iseedClientAccounts='"
				+ jiesuan + "'";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 查询计划入库的订单

	@SuppressWarnings("unchecked")
	public List<InputSeed> planInput() {
		String hql = "from InputSeed where iseedOrderStatus='计划入库' or iseedOrderStatus='准备入库' or iseedOrderStatus='未收费'";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 多种条件模糊查询查询计划入库的订单
	@SuppressWarnings("unchecked")
	public List<InputSeed> planInputDuozhong(String diandanId, String kehu,
			String huowu) {
		String hql = "from InputSeed where iseedOrderStatus='计划入库' or iseedOrderStatus='准备入库' "
				+ "and input.inputClientNumber like '%"
				+ diandanId
				+ "%' "
				+ "and input.client.clientLoginName like '%"
				+ kehu
				+ "%'"
				+ " and goods.goodsName like '%" + huowu + "%'";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 返回所有内部人员查询分页页数
	public int count(String status, String status2, String danhao,
			String huozhu, int pageSize) {
		String hql = "select count(*) from InputSeed where (iseedOrderStatus='"
				+ status + "'" + " or iseedOrderStatus='" + status2 + "') "
				+ " and input.inputPlateNumber like '%" + danhao + "%' "
				+ " and (input.client.clientFirmName like '%" + huozhu + "%'"
				+ " or input.client.clientAbbreviation like '%" + huozhu + "%'"
				+ " or input.client.clientSign like '%" + huozhu + "%')";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 返回所有内部人员查询分页的信息
	@SuppressWarnings("unchecked")
	public List<InputSeed> getInfo(String status, String status2,
			String danhao, String huozhu, int pageNow, int rowSize) {

		String hql = "from InputSeed where (iseedOrderStatus='" + status + "'"
				+ " or iseedOrderStatus='" + status2 + "') "
				+ " and input.inputPlateNumber like '%" + danhao + "%' "
				+ " and (input.client.clientFirmName like '%" + huozhu + "%'"
				+ " or input.client.clientAbbreviation like '%" + huozhu + "%'"
				+ " or input.client.clientSign like '%" + huozhu
				+ "%') order by iseedId desc";

		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 统计待处理入库的条数,查询子订单状态为计划入库和准备入库的订单个数
	public int getRuKuDaiChuLi() {
		String hql = "select count(*) from InputSeed where iseedOrderStatus='计划入库' "
				+ " or iseedOrderStatus='准备入库'";
		return super.queryPageCount(hql, null, 1);
	}

	// 客户app客户根据条件查询入库订单,并且未作废的订单

	@SuppressWarnings("unchecked")
	public List<InputSeed> getRukuInfo(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageNow, int rowSize) {

		System.out.println("dsfsdfsdfsdf" + jiesuanType);

		String hql = "from InputSeed where input.client.clientId=" + kehuId
				+ " " + " and input.inputClientNumber like '%" + kehuDanhao
				+ "%'" + " and (goods.goodsName like '%" + huowu
				+ "%' or goods.goodsSign like '%" + huowu + "%')"
				+ " and iseedOrderStatus!='订单作废' ";
		if (jiesuanType != "") {
			hql += " and iseedClientAccounts='" + jiesuanType + "'";
		} else if (startTime != "" || endTime != "") {
			hql += "and (input.inputCreateTime between '" + startTime + "' "
					+ " and '" + endTime + "')  order by iseedId desc";
		} else {
			hql += " order by iseedId desc";
		}

		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getRukuCount(Integer kehuId, String jiesuanType,
			String startTime, String endTime, String kehuDanhao, String huowu,
			int pageSize) {
		String hql = "select count(*) from InputSeed where input.client.clientId="
				+ kehuId
				+ " "
				+ " and input.inputClientNumber like '%"
				+ kehuDanhao
				+ "%'"
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' or goods.goodsSign like '%"
				+ huowu
				+ "%') "
				+ " and iseedOrderStatus!='订单作废'";
		// 如果时间不为空，加入时间范围
		if (jiesuanType != "") {
			hql += " and iseedClientAccounts='" + jiesuanType + "'";
		} else if (startTime != "" || endTime != "") {
			hql += " and (input.inputCreateTime between '" + startTime + "' "
					+ " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<InputSeed> getRukuInfoAll(Integer kehuId, String begin,String finish,int pageNow,
			int rowSize) {

		String hql = "from InputSeed where input.client.clientId=" + kehuId
				+ " and iseedOrderStatus!='订单作废' and (input.inputCreateTime between '" + begin + "' "
					+ " and '" + finish + "') order by iseedId desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getRukuCountAll(Integer kehuId, String begin,String finish, int pageSize) {
		String hql = "select count(*) from InputSeed where input.client.clientId="
				+ kehuId + " and iseedOrderStatus!='订单作废' and (input.inputCreateTime between '" + begin + "' "
					+ " and '" + finish + "')";

		return super.queryPageCount(hql, null, pageSize);
	}

	// app中客户查询订单中日结的订单的费用
	@SuppressWarnings("unchecked")
	public List<InputSeed> getAppQueryDayCostByPage(Integer clientId,
			String search, int pageNow, int rowSize) {

		String hql = "from InputSeed where input.client.clientId="
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%')"
				+ " or goods.goodsSign like ('%"
				+ search
				+ "%') or input.inputClientNumber like ('%"
				+ search
				+ "%') or input.inputPlateNumber like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%')) and iseedClientAccounts ='日结' and iseedOrderStatus!='订单作废' order by iseedId desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// app中客户查询订单中日结的订单的费用的中也属
	public int getAppQueryDayCostByCount(Integer clientId, String search,
			int pageNow, int rowSize) {
		String hql = "select count(*) from InputSeed where input.client.clientId="
				+ clientId
				+ " and (goods.goodsName like ('%"
				+ search
				+ "%')"
				+ " or goods.goodsSign like ('%"
				+ search
				+ "%') or input.inputClientNumber like ('%"
				+ search
				+ "%') or input.inputCarryType like ('%"
				+ search
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ search
				+ "%')) and iseedClientAccounts ='日结' and iseedOrderStatus!='订单作废'";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 客户app客户根据条件查询所有作废的订单

	@SuppressWarnings("unchecked")
	public List<InputSeed> getRukuInfoPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageNow,
			int rowSize) {

		String hql = "from InputSeed where input.client.clientId=" + kehuId
				+ " " + " and input.inputClientNumber like '%" + kehuDanhao
				+ "%' " + " and (goods.goodsName like '%" + huowu + "%' "
				+ " or goods.goodsSign like '%" + huowu
				+ "%') and iseedOrderStatus='订单作废' ";

		if (startTime != "" && endTime != "") {
			hql += "and (input.inputCreateTime between '" + startTime + "' "
					+ " and '" + endTime + "')  order by iseedId desc";
		} else {
			hql += " order by iseedId desc";
		}

		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getRukuCountPash(Integer kehuId, String startTime,
			String endTime, String kehuDanhao, String huowu, int pageSize) {
		String hql = "select count(*) from InputSeed where input.client.clientId="
				+ kehuId
				+ " "
				+ " and input.inputClientNumber like '%"
				+ kehuDanhao
				+ "%' "
				+ " and (goods.goodsName like '%"
				+ huowu
				+ "%' "
				+ " or goods.goodsSign like '%"
				+ huowu
				+ "%') and iseedOrderStatus='订单作废' ";
		// 如果时间不为空，加入时间范围
		if (startTime != "" && endTime != "") {
			hql += " and (input.inputCreateTime between '" + startTime + "' "
					+ " and '" + endTime + "')";
		}
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<InputSeed> getRukuInfoAllPash(Integer kehuId, int pageNow,
			int rowSize) {

		String hql = "from InputSeed where input.client.clientId=" + kehuId
				+ " and iseedOrderStatus='订单作废' order by iseedId desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getRukuCountAllPash(Integer kehuId, int pageSize) {
		String hql = "select count(*) from InputSeed where input.client.clientId="
				+ kehuId + " and iseedOrderStatus='订单作废'";

		return super.queryPageCount(hql, null, pageSize);
	}

	// 通过货物进行统计转发存的数值,在这一段时间范围内的出库重量的合计
	@SuppressWarnings("unchecked")
	public List<InputSeed> getZhuanFaCunRuKuGeJi(String begin, String finish,
			Integer goodsId) {
		String hql = "select new InputSeed(g,SUM(es.iseedRealityWeight)) from InputSeed es,Goods g where es.input.inputCreateTime >= '"
				+ begin
				+ "' and es.input.inputCreateTime<='"
				+ finish
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsId=g.goodsId and g.goodsId = "
				+ goodsId + " group by g";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过货物进行统计期初库存
	@SuppressWarnings("unchecked")
	public List<InputSeed> getZhuanFaCunRuKuQiChu(String begin, Integer goodsId) {
		String hql = "select new InputSeed(g,SUM(es.iseedRealityWeight)) from InputSeed es,Goods g where es.input.inputCreateTime < '"
				+ begin
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsId=g.goodsId and g.goodsId = "
				+ goodsId + "  group by g";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 统计客户的库存，统计的计算方法是：期初库存+（入库实入重量+转入重量）-（出库实出重量+转出重量）=期末库存 **指的是在某个时间段内的重量**
	// 客户的期初库存指的是在开始时间之前的库存：（入库实入重量+转入重量）-（出库实出重量+转出重量）=期初库存**指的是在某个时间段开始时间之前的重量***
	// 先将该时间段内的客户的库存相加，也就是出库的合计
	@SuppressWarnings("unchecked")
	public List<InputSeed> getKeHuKuCunRKHJ(String begin, String finish,
			Integer clientId, Integer goodsId) {
		String hql = "select new InputSeed(SUM(es.iseedRealityWeight)) from InputSeed es where es.input.inputCreateTime >= '"
				+ begin
				+ "' and es.input.inputCreateTime<='"
				+ finish
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsId="
				+ goodsId
				+ " and es.input.client.clientId="
				+ clientId
				+ " group by es.goods.goodsId,es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计
	@SuppressWarnings("unchecked")
	public List<InputSeed> getKeHuKuCunQCHJ(String begin, Integer clientId,
			Integer goodsId) {
		String hql = "select new InputSeed(SUM(es.iseedRealityWeight)) from InputSeed es where es.input.inputCreateTime < '"
				+ begin
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsId="
				+ goodsId
				+ " and es.input.client.clientId="
				+ clientId
				+ " group by es.goods.goodsId,es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 通过运输方式进行统计重量
	@SuppressWarnings("unchecked")
	public List<InputSeed> getSFCYunShu(String begin, String finish,
			Integer clientId, String goodsName, String yunshu) {
		String hql = "select new InputSeed(SUM(es.iseedRealityWeight)) from InputSeed es where es.input.inputCreateTime >= '"
				+ begin
				+ "' and es.input.inputCreateTime<='"
				+ finish
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsName='"
				+ goodsName
				+ "' and es.input.client.clientId="
				+ clientId
				+ " and es.input.inputCarryType='"
				+ yunshu
				+ "' group by es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 统计收发存的量
	@SuppressWarnings("unchecked")
	public List<InputSeed> getKeHuKuCunRKHJSFC(String begin, String finish,
			Integer clientId, String goodsName) {
		String hql = "select new InputSeed(SUM(es.iseedRealityWeight)) from InputSeed es where es.input.inputCreateTime >= '"
				+ begin
				+ "' and es.input.inputCreateTime<='"
				+ finish
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsName='"
				+ goodsName
				+ "' and es.input.client.clientId="
				+ clientId
				+ " group by es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 统计客户库存中期初的出库合计
	@SuppressWarnings("unchecked")
	public List<InputSeed> getKeHuKuCunQCHJSFC(String begin, Integer clientId,
			String goodsName) {
		String hql = "select new InputSeed(SUM(es.iseedRealityWeight)) from InputSeed es where es.input.inputCreateTime < '"
				+ begin
				+ "' and es.iseedOrderStatus!='订单作废' and es.goods.goodsName='"
				+ goodsName
				+ "' and es.input.client.clientId="
				+ clientId
				+ " group by es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 查询订单，查询未作废的
	@SuppressWarnings("unchecked")
	public List<InputSeed> getRuKuLiShiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount) {
		String hql = "from InputSeed where iseedOrderStatus!='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and input.inputId like ('%"
				+ input
				+ "%') and input.inputClientNumber like ('%"
				+ clientNumber
				+ "%') and (goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%')) order by iseedId desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				pageCount);
	}

	// 查询订单的总页数
	public int getRuKuLiShiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount) {
		String hql = "select count(*) from InputSeed where iseedOrderStatus!='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and input.inputId like ('%"
				+ input
				+ "%') and input.inputClientNumber like ('%"
				+ clientNumber
				+ "%') and (goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))";
		return super.queryPageCount(hql, null, pageCount);
	}

	// 查询作废订单，查询未作废的
	@SuppressWarnings("unchecked")
	public List<InputSeed> getRuKuZuoFeiByPage(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageNow, int pageCount) {
		String hql = "from InputSeed where iseedOrderStatus='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and input.inputId like ('%"
				+ input
				+ "%') and input.inputClientNumber like ('%"
				+ clientNumber
				+ "%') and (goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) order by input.inputCreateTime,iseedId desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				pageCount);
	}

	// 查询作废订单的总页数
	public int getRuKuZuoFeiByPageCount(String begin, String finish,
			String clientName, String input, String clientNumber,
			String goodsName, int pageCount) {
		String hql = "select count(*) from InputSeed where iseedOrderStatus='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and input.inputId like ('%"
				+ input
				+ "%') and input.inputClientNumber like ('%"
				+ clientNumber
				+ "%') and (goods.goodsName like ('%"
				+ goodsName
				+ "%') or goods.goodsSign like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))";
		return super.queryPageCount(hql, null, pageCount);
	}

	// 通过运输方计算费用合计
	@SuppressWarnings("unchecked")
	public List<InputSeed> getYunShuCost(String begin, String finish,
			Integer clientId, String yunshu) {
		String hql = "select new InputSeed(SUM(es.iseedShouldCost)) from InputSeed es where es.input.inputCreateTime >= '"
				+ begin
				+ "' and es.input.inputCreateTime <= '"
				+ finish
				+ "' and es.iseedOrderStatus!='订单作废' and es.iseedOrderStatus='未收费' and es.input.client.clientId="
				+ clientId
				+ " and es.input.inputCarryType='"
				+ yunshu
				+ "' and es.iseedClientAccounts = '月结' group by es.input.client.clientId";
		return (List<InputSeed>) super.executeQuery(hql, null);
	}

	// 查询月结和现结的费用，通过时间，客户进行查询
	@SuppressWarnings("unchecked")
	public List<InputSeed> getJieSuanQueryByPage(String clientName,
			String begin, String finish, String jiesuan, int pageNow,
			int rowSize, String shoufeiren) {
		String hql = "from InputSeed where iseedOrderStatus!='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and iseedClientAccounts like ('%"
				+ jiesuan
				+ "%')  and iseedId in (select inputSeed from InputOperate where interiorUserByIoperateCollectManId.iuserName like ('%"
				+ shoufeiren + "%')) order by input.inputCreateTime desc";
		return (List<InputSeed>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询月结和现结的费用，通过时间，客户进行查询总页数
	public int getJieSuanQueryByCount(String clientName, String begin,
			String finish, String jiesuan, int rowSize, String shoufeiren) {
		String hql = "select count(*) from InputSeed where iseedOrderStatus!='订单作废'"
				+ " and input.inputCreateTime <='"
				+ finish
				+ "' and input.inputCreateTime>='"
				+ begin
				+ "' and (input.client.clientFirmName like ('%"
				+ clientName
				+ "%') or input.client.clientAbbreviation like ('%"
				+ clientName
				+ "%') or input.client.clientSign like ('%"
				+ clientName
				+ "%')) and iseedClientAccounts like ('%"
				+ jiesuan
				+ "%') and iseedId in (select inputSeed from InputOperate where interiorUserByIoperateCollectManId.iuserName like ('%"
				+ shoufeiren + "%'))";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询某个客户的当日的订单
	@SuppressWarnings("unchecked")
	public List<InputSeed> getAppTodayTheOrder(Integer clientId, String begin,
			String finish) {
		List<InputSeed> sislist = new ArrayList<InputSeed>();
		String ruhql = "from InputSeed where input.client.clientId ="
				+ clientId
				+ " and input.inputCreateTime >='"
				+ begin
				+ "' and input.inputCreateTime <= '"
				+ finish
				+ "' and iseedOrderStatus!='订单作废' order by input.inputCreateTime desc";

		String chuhql = "from ExportSeed where eseedOrderStatus!='订单作废' and export.client.clientId="
				+ clientId
				+ " and export.exportReateTime >='"
				+ begin
				+ "' and export.exportReateTime <= '"
				+ finish
				+ "' order by export.exportReateTime desc";
		String guohql = "from ShiftStockSeed where (shiftStock.clientBySstockClientId.clientId = "
				+ clientId
				+ " or shiftStock.clientBySstockShiftToFirm.clientId ="
				+ clientId
				+ " ) and shiftStock.sstockReateTime >= '"
				+ begin
				+ "' and shiftStock.sstockReateTime <= '"
				+ finish
				+ "' and ssseedOrderStatus!='订单作废' order by shiftStock.sstockReateTime desc";
		List<InputSeed> islist = (List<InputSeed>) super.executeQuery(ruhql,
				null);
		List<ExportSeed> eslist = (List<ExportSeed>) super.executeQuery(chuhql,
				null);
		List<ShiftStockSeed> sslist = (List<ShiftStockSeed>) super
				.executeQuery(guohql, null);

		for (int i = 0; i < islist.size(); i++) {
			InputSeed is = new InputSeed();
			is.setIseedId(islist.get(i).getIseedId());
			is.setZongId(islist.get(i).getInput().getInputId());
			is.setTime(islist.get(i).getInput().getInputCreateTime());
			sislist.add(is);
		}
		for (int i = 0; i < eslist.size(); i++) {
			InputSeed is = new InputSeed();
			is.setIseedId(eslist.get(i).getEseedId());
			is.setZongId(eslist.get(i).getExport().getExportId());
			is.setTime(eslist.get(i).getExport().getExportReateTime());
			sislist.add(is);
		}
		for (int i = 0; i < sslist.size(); i++) {
			InputSeed is = new InputSeed();
			is.setIseedId(sslist.get(i).getSsseedId());
			is.setZongId(sslist.get(i).getShiftStock().getSstockId());
			is.setTime(sslist.get(i).getShiftStock().getSstockReateTime());
			sislist.add(is);
		}
		for (int i = 0; i < sislist.size(); i++) {
			System.out.println(sislist.get(i).getIseedId() + ","
					+ sislist.get(i).getZongId() + ","
					+ sislist.get(i).getTime());
		}
		return sislist;
	}

	// 统计相应客户的收发存总和,入库统计
	@SuppressWarnings("unchecked")
	public Double getRuKuSum(String begin, String finish, String client,
			String goodsName, String type) {
		String hql = "select SUM(iseedRealityWeight) from InputSeed where";
		if (type.equals("qichu")) {
			hql += " input.inputCreateTime < '" + begin + "' and ";
		} else if (type.equals("ruku")) {
			hql += " input.inputCreateTime >= '" + begin
					+ "' and input.inputCreateTime <= '" + finish + "' and ";
		}
		hql += "(input.client.clientFirmName like ('%"
				+ client
				+ "%') or input.client.clientAbbreviation like ('%"
				+ client
				+ "%') or input.client.clientSign like ('%"
				+ client
				+ "%')) and iseedOrderStatus!='订单作废' and (goods.goodsName like ('%"
				+ goodsName + "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))";
		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist.get(0);
	}

	// 统计相应客户的收发存总和,出库统计
	@SuppressWarnings("unchecked")
	public Double getChuKuSum(String begin, String finish, String client,
			String goodsName, String type) {
		String hql = "select SUM(eseedRealityWeight) from ExportSeed where ";
		if (type.equals("qichu")) {
			hql += "export.exportReateTime < '" + begin + "' and ";
		} else if (type.equals("chuku")) {
			hql += "export.exportReateTime >= '" + begin
					+ "' and export.exportReateTime <= '" + finish + "' and ";
		}
		hql += "(export.client.clientFirmName like ('%"
				+ client
				+ "%') or export.client.clientSign like ('%"
				+ client
				+ "%') or export.client.clientAbbreviation like ('%"
				+ client
				+ "%')) and eseedOrderStatus!='订单作废' and (goods.goodsName like ('%"
				+ goodsName + "%') or goods.goodsSign like ('%" + goodsName
				+ "%')or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%')or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%')or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))";
		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist.get(0);
	}

	// 统计相应客户的收发存总和,过户
	@SuppressWarnings("unchecked")
	public Double getGuoHuSum(String begin, String finish, String client,
			String goodsName, String type) {
		String hql = "select SUM(ssseedWeight) from ShiftStockSeed where ";
		// 期初过户入库
		if (type.equals("qiru")) {
			hql += " shiftStock.sstockReateTime <'"
					+ begin
					+ "' and (shiftStock.clientBySstockShiftToFirm.clientFirmName like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockShiftToFirm.clientSign like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockShiftToFirm.clientAbbreviation like ('%"
					+ client + "%')) and ";
		} else if (type.equals("qichu")) {
			hql += " shiftStock.sstockReateTime <'"
					+ begin
					+ "' and (shiftStock.clientBySstockClientId.clientFirmName like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
					+ client + "%')) and ";
		} else if (type.equals("ru")) {
			hql += " shiftStock.sstockReateTime >='"
					+ begin
					+ "' and shiftStock.sstockReateTime <= '"
					+ finish
					+ "' and (shiftStock.clientBySstockShiftToFirm.clientFirmName like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockShiftToFirm.clientSign like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockShiftToFirm.clientAbbreviation like ('%"
					+ client + "%')) and ";
		} else if (type.equals("chu")) {
			hql += " shiftStock.sstockReateTime >='"
					+ begin
					+ "' and shiftStock.sstockReateTime <= '"
					+ finish
					+ "' and (shiftStock.clientBySstockClientId.clientFirmName like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockClientId.clientSign like ('%"
					+ client
					+ "%') or shiftStock.clientBySstockClientId.clientAbbreviation like ('%"
					+ client + "%')) and ";
		}else if(type.equals("sum")){
			hql += " shiftStock.sstockReateTime >='"
					+ begin
					+ "' and shiftStock.sstockReateTime <= '"
					+ finish
					+ "' and ";
		}
		hql += "ssseedOrderStatus != '订单作废' and (goods.goodsName like ('%"
				+ goodsName + "%') or goods.goodsSign like ('%" + goodsName
				+ "%')or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%')or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%')or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))";
		List<Double> iolist = (List<Double>) super.executeQuery(hql, null);
		return iolist == null ? 0.0 : iolist.get(0) == null ? 0.0 : iolist.get(0);
	}
}
