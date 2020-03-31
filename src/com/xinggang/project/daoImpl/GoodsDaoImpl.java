package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.entity.Goods;

/**
 * 物品接口实现类
 * 
 * @author Administrator
 * 
 */
public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDao {
	// 添加
	public boolean save(Goods goods) {
		return super.BaseSave(goods);
	}

	// 修改
	public boolean update(Goods goods) {
		return super.BaseUpdate(goods);
	}

	// 删除
	public boolean delete(Goods goods) {
		goods.setGoodsDefinedOne("0");
		return super.BaseUpdate(goods);
	}

	// 通过id查询
	public Goods getGoodsId(Integer id) {
		String hql = "from Goods where goodsId=" + id;
		return (Goods) super.findById(hql);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Goods> getAllGoods() {
		String hql = "from Goods where goodsDefinedOne!='0'";
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<Goods> getAll(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty) {
		String hql = "from Goods where goodsDefinedOne!='0' and goodsName like ('%"
				+ goodsName
				+ "%') and goodsSign like ('%"
				+ goodsSign
				+ "%') and goodsStandard.goodsStandardName like ('%"
				+ goodsStandard
				+ "%') and goodsQuality.goodsQualityName like ('%"
				+ goodsQuality
				+ "%') and goodsProperty.goodsPropertyName like ('%"
				+ goodsProperty + "%') order by goodsId desc";
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 根据条件分页查询
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByPage(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty,
			int pageNow, int rowSize) {// 名称，助记符,规格，材质，属性
		String hql = "from Goods where goodsDefinedOne!='0' and goodsName like ('%"
				+ goodsName
				+ "%') "
				+ "and goodsSign like ('%"
				+ goodsSign
				+ "%') "
				+ "and goodsStandard.goodsStandardName like ('%"
				+ goodsStandard
				+ "%') "
				+ "and goodsQuality.goodsQualityName like ('%"
				+ goodsQuality
				+ "%') "
				+ "and goodsProperty.goodsPropertyName like ('%"
				+ goodsProperty + "%') order by goodsId desc";
		return (List<Goods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 返回数据的总行数
	public int getGoodsCount(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty) {
		String hql = "select count(*) from Goods where goodsDefinedOne!='0' and goodsName like ('%"
				+ goodsName
				+ "%') "
				+ "and goodsSign like ('%"
				+ goodsSign
				+ "%') "
				+ "and goodsStandard.goodsStandardName like ('%"
				+ goodsStandard
				+ "%') "
				+ "and goodsQuality.goodsQualityName like ('%"
				+ goodsQuality
				+ "%') "
				+ "and goodsProperty.goodsPropertyName like ('%"
				+ goodsProperty + "%') order by goodsId desc";
		return super.executeQueryRowCount(hql, null);
	}

	// 通过多种条件查询该货物是否存在
	@SuppressWarnings("unchecked")
	public List<Goods> getDuozhong(Integer pinlei, Integer chandi,
			String goodsName, String goodsSign, Integer goodsStandard,
			Integer goodsQuality, Integer goodsProperty) {
		String hql = "from Goods where goodsDefinedOne!='0'and goodsCategory="
				+ pinlei + " and goodsYieldly=" + chandi + " "
				+ "and goodsName='" + goodsName + "' and goodsSign='"
				+ goodsSign + "' and goodsStandard=" + goodsStandard + " "
				+ "and goodsQuality=" + goodsQuality + " and goodsProperty="
				+ goodsProperty + "";
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByPage2(String goods, int pageNow, int rowSize) {
		String hql = "from Goods where GoodsDefinedOne!='0' and (goodsName like ('%"
				+ goods
				+ "%') or goodsSign like ('%"
				+ goods
				+ "%') or goodsCategory.goodsCategoryName like ('%"
				+ goods
				+ "%') or goodsStandard.goodsStandardName like ('%"
				+ goods
				+ "%') or goodsYieldly.goodsYieldlyName like ('%"
				+ goods
				+ "%') or goodsQuality.goodsQualityName like ('%"
				+ goods
				+ "%') or goodsProperty.goodsPropertyName like ('%"
				+ goods
				+ "%')) order by goodsId desc";
		return (List<Goods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 得到分页的页数-----------------------------修改后
	public int getPageCount(String goods, int pageSize) {
		String hql = "select COUNT(*) from Goods where GoodsDefinedOne!='0' and (goodsName like ('%"
				+ goods
				+ "%') or goodsSign like ('%"
				+ goods
				+ "%') or goodsCategory.goodsCategoryName like ('%"
				+ goods
				+ "%') or goodsStandard.goodsStandardName like ('%"
				+ goods
				+ "%') or goodsYieldly.goodsYieldlyName like ('%"
				+ goods
				+ "%') or goodsQuality.goodsQualityName like ('%"
				+ goods
				+ "%') or goodsProperty.goodsPropertyName like ('%"
				+ goods
				+ "%'))";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 根据条件得到分页的页数
	public int getPageCountTwo(String goodsName, String goodsSign,
			String goodsStandard, String goodsQuality, String goodsProperty,
			int pageSize) {
		String hql = "select count(*) from Goods where goodsDefinedOne!='0' and goodsName like ('%"
				+ goodsName
				+ "%') "
				+ "and goodsSign like ('%"
				+ goodsSign
				+ "%') "
				+ "and goodsStandard.goodsStandardName like ('%"
				+ goodsStandard
				+ "%') "
				+ "and goodsQuality.goodsQualityName like ('%"
				+ goodsQuality
				+ "%')"
				+ "and goodsProperty.goodsPropertyName like ('%"
				+ goodsProperty + "%')";
		return super.queryPageCount(hql, null, pageSize);
	}

	// 通过客户查询该客户具有的库存
	@SuppressWarnings("unchecked")
	public List<Goods> getClientGoods(Integer clientId) {
		// 编号，品类，产地，货物名称，助记符，规格，材质，属性，计量单位，理算重量，货物是否删除，预留字段二，备注,货物重量，是否冻结，冻结重量，是否质押，质押重量
		String hql = "select new Goods(g.goodsId,g.goodsCategory,g.goodsYieldly,g.goodsName,g.goodsSign,"
				+ "g.goodsStandard,g.goodsQuality,g.goodsProperty,g.goodsUnit,g.goodsAdjustment,"
				+ "g.goodsDefinedOne,g.goodsDefinedTwo,g.goodsRemark,cg.cgoodsWeight,cg.cgoodsFreeze,"
				+ "cg.cgoodsFreezeWeight,cg.cgoodsImpawn,cg.cgoodsImpawnWeight) "
				+ "from Goods g,ClientGoods cg where g.goodsId = cg.goods and cg.client="
				+ clientId + " order by cg.cgoodsId desc";
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 通过品类查货物
	@SuppressWarnings("unchecked")
	public List<Goods> getPinlei(Integer pinleiId) {
		String hql = "from Goods where goodsDefinedOne!='0'and goodsCategory.goodsCategoryId="
				+ pinleiId;
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 查询该货物名类下的货物
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodSName(Integer id, String goodsName) {
		String hql = "from Goods where goodsDefinedOne!='0' and goodsCategory.goodsCategoryId="
				+ id + " and goodsName='" + goodsName + "'";
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 查询该货物规格下的货物
	@SuppressWarnings("unchecked")
	public List<Goods> getGuiGeId(Integer peiLeiId, String goodsName, Integer id) {
		String hql = "from Goods where goodsDefinedOne!='0'"
				+ " and goodsCategory.goodsCategoryId=" + peiLeiId + "  "
				+ " and goodsName='" + goodsName + "' "
				+ " and goodsStandard.goodsStandardId=" + id;
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 查询该货物材质下的货物
	@SuppressWarnings("unchecked")
	public List<Goods> getCaiZhiId(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer id) {
		String hql = "from Goods where goodsDefinedOne!='0'"
				+ " and goodsCategory.goodsCategoryId=" + peiLeiId + "  "
				+ " and goodsName='" + goodsName + "' "
				+ " and goodsStandard.goodsStandardId=" + guigeid + " "
				+ " and goodsQuality.goodsQualityId=" + id;
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 查询该货物属性下的货物
	@SuppressWarnings("unchecked")
	public List<Goods> getShuXinId(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer caizhiid, Integer id) {
		String hql = "from Goods where goodsDefinedOne!='0'"
				+ " and goodsCategory.goodsCategoryId=" + peiLeiId + "  "
				+ " and goodsName='" + goodsName + "' "
				+ " and goodsStandard.goodsStandardId=" + guigeid + " "
				+ " and goodsQuality.goodsQualityId=" + caizhiid + " "
				+ " and goodsProperty.goodsPropertyId=" + id;
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 通过产地查货物
	@SuppressWarnings("unchecked")
	public List<Goods> getChandi(Integer peiLeiId, String goodsName,
			Integer guigeid, Integer caizhiid, Integer shuxinid,
			Integer chandiId) {
		String hql = "from Goods where goodsDefinedOne!='0'"
				+ " and goodsCategory.goodsCategoryId=" + peiLeiId + "  "
				+ " and goodsName='" + goodsName + "' "
				+ " and goodsStandard.goodsStandardId=" + guigeid + " "
				+ " and goodsQuality.goodsQualityId=" + caizhiid + " "
				+ " and goodsProperty.goodsPropertyId=" + shuxinid + " "
				+ " and goodsYieldly.goodsYieldlyId=" + chandiId;
		return (List<Goods>) super.executeQuery(hql, null);
	}

	// 通过产地,规格,属性,材质,货物,品类查询
	public Goods getOneGoods(Integer pingleiId, Integer guigeId,
			String goodsName, Integer caizhiId, Integer shuxinId,
			Integer chandiId) {
		String hql = "from Goods where goodsDefinedOne!='0'and goodsYieldly="
				+ chandiId + "" + " and goodsName='" + goodsName
				+ "' and goodsProperty=" + shuxinId + " "
				+ "and goodsCategory=" + pingleiId + " and goodsStandard="
				+ guigeId + " " + "and goodsQuality=" + caizhiId + "";
		return (Goods) super.findById(hql);
	}

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询
	@SuppressWarnings("unchecked")
	public List<Goods> getZhuanFaCunGoods(String goodsName, String pinlei,
			int pageNow, int rowSize) {
		String hql = "select new Goods(g.goodsId,g.goodsProperty,g.goodsCategory,g.goodsStandard,g.goodsQuality,g.goodsYieldly,g.goodsName) "
				+ "from Goods g,TarehouseGoods tg where (g.goodsName like ('%"
				+ goodsName
				+ "%') "
				+ "or g.goodsSign like ('%"
				+ goodsName
				+ "%') "
				+ "or g.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or g.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and g.goodsCategory.goodsCategoryName like ('%"
				+ pinlei
				+ "%') and g.goodsId=tg.goods.goodsId group by g.goodsId,g.goodsProperty,g.goodsCategory,g.goodsStandard,g.goodsQuality,g.goodsYieldly,g.goodsName  order by g.goodsId desc";
		return (List<Goods>) super.executeQueryByPage(hql, null, pageNow,
				rowSize);
	}

	// 通过货物的名称或者规格或者产地或者助记符并且品类进行查询,查询数据的总页数
	public int getZhuanFaCunByCount(String goodsName, String pinlei, int rowSize) {
		String hql = "select count(*) "
				+ "from Goods g,TarehouseGoods tg where (g.goodsName like ('%"
				+ goodsName
				+ "%') "
				+ "or g.goodsSign like ('%"
				+ goodsName
				+ "%') "
				+ "or g.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or g.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName
				+ "%')) and g.goodsCategory.goodsCategoryName like ('%"
				+ pinlei
				+ "%') and g.goodsId=tg.goods.goodsId group by g.goodsId,g.goodsProperty,g.goodsCategory,g.goodsStandard,g.goodsQuality,g.goodsYieldly,g.goodsName";
		@SuppressWarnings("unchecked")
		List<Integer> count = (List<Integer>) super.executeQuery(hql, null);
		return (count.size() - 1) / rowSize + 1;
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getInitGoodsId(String goodsName, String guige,
			String caizhi, String shuxing, String chandi) {
		String hql = "from Goods where goodsName='" + goodsName
				+ "' and goodsStandard.goodsStandardName ='" + guige
				+ "' and goodsQuality.goodsQualityName = '" + caizhi
				+ "' and goodsYieldly.goodsYieldlyName = '" + chandi
				+ "' and goodsProperty.goodsPropertyName = '" + shuxing + "'";
		return (List<Goods>) super.executeQuery(hql, null);
	}

}
