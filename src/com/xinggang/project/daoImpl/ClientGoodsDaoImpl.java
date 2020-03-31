package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.ClientGoodsDao;
import com.xinggang.project.entity.ClientGoods;

/**
 * 客户货物库存Dao实现类
 * 
 * @author Administrator
 * 
 */
public class ClientGoodsDaoImpl extends BaseDaoImpl implements ClientGoodsDao {
	// 增加
	public boolean save(ClientGoods clientGoods) {
		return super.BaseSave(clientGoods);
	}

	// 删除
	public boolean delete(ClientGoods clientGoods) {
		return super.BaseDelete(clientGoods);
	}

	// 修改
	public boolean update(ClientGoods clientGoods) {
		return super.BaseUpdate(clientGoods);
	}

	// 通过id查询
	public ClientGoods getClientGoodsId(String id) {
		return (ClientGoods) super.findById(ClientGoods.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getAll() {
		String hql = "from ClientGoods order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	// 通过客户和货物
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsByCG(Integer kehuId, Integer goodsId) {
		String hql = "from ClientGoods where client=" + kehuId + " and goods="
				+ goodsId + " order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	public ClientGoods getClientYesGoods(Integer kehuId, Integer pingleiId,
			Integer guigeId, String goodsName, Integer caizhiId,
			Integer shuxinId, Integer chandiId) {

		String hql = "from ClientGoods where client.clientId=" + kehuId
				+ " goods.goodsYieldly=" + chandiId + ""
				+ " and goods.goodsName='" + goodsName
				+ "' and goods.goodsProperty=" + shuxinId + " "
				+ "and goods.goodsCategory=" + pingleiId
				+ " and goods.goodsStandard=" + guigeId + " "
				+ "and goods.goodsQuality=" + caizhiId + "";

		return (ClientGoods) super.findById(hql);
	};

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsByPage(Integer kehuId,
			Integer goodsId, String zhiya, String dongjie, int pageNow,
			int rowSize) {
		String hql = "from ClientGoods where client=" + kehuId + " and goods="
				+ goodsId + " and cgoodsFreeze like ('%" + dongjie
				+ "%') and cgoodsImpawn like ('%" + zhiya
				+ "%') order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询数据总行数
	public int getClientGoodsByCount(Integer kehuId, Integer goodsId,
			String zhiya, String dongjie) {
		String hql = "select count(*) from ClientGoods where client=" + kehuId
				+ " and goods=" + goodsId + " and cgoodsFreeze like ('%"
				+ dongjie + "%') and cgoodsImpawn like ('%" + zhiya
				+ "%') order by cgoodsId desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过客户查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClient(Integer clientId) {
		String hql = "from ClientGoods where client=" + clientId + "";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	// 通过客户分页查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientByPage(Integer clientId, int pageNow,
			int rowSize) {
		String hql = "from ClientGoods where client=" + clientId
				+ " order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询出数据的总行数
	public int getClientByCount(Integer clientId) {
		String hql = "select count(*) from ClientGoods where client="
				+ clientId + " order by cgoodsId desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过货物查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getHuowu(Integer huowu) {
		String hql = "from ClientGoods where goods=" + huowu
				+ " order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	// 插叙是否冻结
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getDongjie(String dongjie) {
		String hql = "from ClientGoods where cgoodsFreeze='" + dongjie
				+ "' order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	// 查询是否质押
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getZhiya(String zhiya) {
		String hql = "from ClientGoods where cgoodsImpawn='" + zhiya
				+ "' order by cgoodsId desc";
		return (List<ClientGoods>) super.executeQuery(hql, null);
	}

	// 通过客户分页查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			Integer clientId, int pageNow, int rowSize) {
		String hql = "from ClientGoods cg "
				+ "where cg.client="
				+ clientId
				+ " and (cg.goods.goodsName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsSign like ('%"
				+ goodsName
				+ "%') or  cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') and  cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and (cg.cgoodsWeight > 0 or cg.cgoodsFreezeWeight>0)  order by cg.cgoodsId desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过客户查询数据的总页数
	public int getClientGoodsByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, Integer clientId,
			int rowSize) {
		String hql = "select count(*) from ClientGoods cg where cg.client="
				+ clientId + " and (cg.goods.goodsName like ('%" + goodsName
				+ "%') or cg.goods.goodsSign like ('%" + goodsName
				+ "%') or  cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') and  cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and (cg.cgoodsWeight > 0 or cg.cgoodsFreezeWeight>0)  ";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询全部并且以分页的形式显示
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsAllByPage(String danwei,
			String jiancheng, String danweizhujifu, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			int pageNow, int rowSize) {

		// 编号，客户，货物，重量，件数，是否冻结，冻结重量，冻结件数，是否质押,质押重量,质押件数，预留字段一，预留字段二，备注
		String hql = "from ClientGoods cg"
				+ " where (cg.client.clientFirmName like ('%" + jiancheng
				+ "%') or cg.client.clientAbbreviation like ('%" + jiancheng
				+ "%') or cg.client.clientSign like ('%" + jiancheng
				+ "%')) and (cg.goods.goodsName like ('%" + goodsName
				+ "%') or cg.goods.goodsSign like ('%" + goodsName
				+ "%') or cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%')) order by cg.client desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询全部数据的总页数
	public int getClientGoodsAllByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String goodsName, String guige,
			String caizhi, String shuxing, String goodsSign, int rowSize) {
		String hql = "select count(*) from ClientGoods cg"
				+ " where (cg.client.clientFirmName like ('%" + jiancheng
				+ "%') or cg.client.clientAbbreviation like ('%" + jiancheng
				+ "%') or cg.client.clientSign like ('%" + jiancheng
				+ "%')) and (cg.goods.goodsName like ('%" + goodsName
				+ "%') or cg.goods.goodsSign like ('%" + goodsName
				+ "%') or cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName + "%'))";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 客户app客户根据条件查询客户货物库存的订单

	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsInfo(Integer kehuId, String pinlei,
			String huowu, String shuxin, String guige, String chandi,
			String caizhi, int pageNow, int rowSize) {

		String hql = "from ClientGoods where client.clientId=" + kehuId + " "
				+ " and goods.goodsCategory.goodsCategoryName like '%" + pinlei
				+ "%' " + " and goods.goodsProperty.goodsPropertyName like '%"
				+ shuxin + "%' "
				+ " and goods.goodsStandard.goodsStandardName like '%" + guige
				+ "%' " + " and goods.goodsQuality.goodsQualityName like '%"
				+ caizhi + "%' "
				+ " and goods.goodsYieldly.goodsYieldlyName like '%" + chandi
				+ "%' " + " and goods.goodsName like '%" + huowu + "%'";

		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getClientGoodsCount(Integer kehuId, String pinlei, String huowu,
			String shuxin, String guige, String chandi, String caizhi,
			int pageSize) {
		String hql = "select count(*) from ClientGoods where client.clientId="
				+ kehuId + " "
				+ " and goods.goodsCategory.goodsCategoryName like '%" + pinlei
				+ "%' " + " and goods.goodsProperty.goodsPropertyName like '%"
				+ shuxin + "%' "
				+ " and goods.goodsStandard.goodsStandardName like '%" + guige
				+ "%' " + " and goods.goodsQuality.goodsQualityName like '%"
				+ caizhi + "%'"
				+ " and goods.goodsYieldly.goodsYieldlyName like '%" + chandi
				+ "%'" + " and goods.goodsName like '%" + huowu + "%' ";
		// 如果时间不为空，加入时间范围
		return super.queryPageCount(hql, null, pageSize);
	}

	// 客户app查询入库订单
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getClientGoodsAll(Integer kehuId, int pageNow,
			int rowSize) {

		String hql = "from ClientGoods where client.clientId=" + kehuId + "";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 客户app查询入库订单,返回行数
	public int getClientGoodsAllCount(Integer kehuId, int pageSize) {
		String hql = "select count(*) from ClientGoods where client.clientId="
				+ kehuId + "";

		return super.queryPageCount(hql, null, pageSize);
	}

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getKeHuKuCunByPage(String jiancheng,
			String goodsName, String guige, String caizhi, String shuxing,
			String chandi, int pageNow, int rowSize) {
		String hql = "from ClientGoods cg"
				+ " where (cg.client.clientFirmName like ('%" + jiancheng
				+ "%') or cg.client.clientAbbreviation like ('%" + jiancheng
				+ "%') or cg.client.clientSign like ('%" + jiancheng
				+ "%')) and (cg.goods.goodsName like ('%" + goodsName
				+ "%') or cg.goods.goodsSign like ('%" + goodsName
				+ "%') or cg.goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%')) and cg.goods.goodsStandard.goodsStandardName like ('%"
				+ guige
				+ "%')  and cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ chandi
				+ "%') and cg.goods.goodsQuality.goodsQualityName like ('%"
				+ caizhi
				+ "%') and cg.goods.goodsProperty.goodsPropertyName like ('%"
				+ shuxing
				+ "%') order by cg.client.clientId,cg.goods.goodsName desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询总页数
	public int getKeHuKuCunByPageCount(String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String chandi,
			int rowSize) {
		String hql = "select count(*) from ClientGoods cg"
				+ " where (cg.client.clientFirmName like ('%" + jiancheng
				+ "%') or cg.client.clientAbbreviation like ('%" + jiancheng
				+ "%') or cg.client.clientSign like ('%" + jiancheng
				+ "%')) and (cg.goods.goodsName like ('%" + goodsName
				+ "%') or cg.goods.goodsSign like ('%" + goodsName
				+ "%') or cg.goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%')) and cg.goods.goodsStandard.goodsStandardName like ('%"
				+ guige
				+ "%')  and cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ chandi
				+ "%') and cg.goods.goodsQuality.goodsQualityName like ('%"
				+ caizhi
				+ "%') and cg.goods.goodsProperty.goodsPropertyName like ('%"
				+ shuxing + "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询客户进行统计收发存
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getKeHuSFCByPage(String jiancheng,
			String goodsName, int pageNow, int rowSize) {
		String hql = "select new ClientGoods(c,g.goodsName,g.goodsCategory.goodsCategoryName) from ClientGoods cg,Goods g,Client c"
				+ " where (c.clientFirmName like ('%"
				+ jiancheng
				+ "%') or c.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or c.clientSign like ('%"
				+ jiancheng
				+ "%')) and (g.goodsName like ('%"
				+ goodsName
				+ "%') or g.goodsSign like ('%"
				+ goodsName
				+ "%') or g.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or g.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or g.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and g.goodsId=cg.goods.goodsId and c.clientId=cg.client.clientId group by c,g.goodsName,g.goodsCategory.goodsCategoryName  order by c,g.goodsName desc";
		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 统计客户收发存的总页数
	public int getKeHuSFCByPageCount(String jiancheng, String goodsName,
			int rowSize) {
		String hql = "select count(*) from ClientGoods cg"
				+ " where (cg.client.clientFirmName like ('%"
				+ jiancheng
				+ "%') or cg.client.clientAbbreviation like ('%"
				+ jiancheng
				+ "%') or cg.client.clientSign like ('%"
				+ jiancheng
				+ "%')) and (cg.goods.goodsName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsSign like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) group by cg.client,cg.goods.goodsName,cg.goods.goodsCategory.goodsCategoryName";
		@SuppressWarnings("unchecked")
		List<Integer> count = (List<Integer>) super.executeQuery(hql, null);

		return (count.size() - 1) / rowSize + 1;
	}

	// -----------------------------客户根据id查询自己的收发存

	// 查询客户货物的名称、品类、规格、产地、助记符进行查询收发存
	@SuppressWarnings("unchecked")
	public List<ClientGoods> getKeHuSFCByPageKH(int clientId, String goodsName,
			int pageNow, int rowSize) {

		String hql = "select new ClientGoods(c,g.goodsName,g.goodsCategory.goodsCategoryName) from ClientGoods cg,Goods g,Client c"
				+ " where cg.client.clientId="
				+ clientId
				+ " and (g.goodsName like ('%"
				+ goodsName
				+ "%') or g.goodsSign like ('%"
				+ goodsName
				+ "%') or g.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or g.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or g.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and g.goodsId=cg.goods.goodsId and c.clientId=cg.client.clientId group by c,g.goodsName,g.goodsCategory.goodsCategoryName  order by c,g.goodsName desc";

		return (List<ClientGoods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 查询客户收发存总页数
	public int getKeHuSFCByPageCountKH(int clientId, String goodsName,
			int rowSize) {

		String hql = "select count(*) from ClientGoods cg"
				+ " where cg.client.clientId ="
				+ clientId
				+ " and (cg.goods.goodsName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsSign like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or cg.goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) group by cg.client,cg.goods.goodsName,cg.goods.goodsCategory.goodsCategoryName";

		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过客户查询对应的货物总值
	public Double clientGoodsSum(Integer clientId) {
		String hql = "select sum(cgoodsWeight) from ClientGoods where client.clientId="
				+ clientId;
		return (Double) super.executeQuery(hql, null).get(0);
	}
}
