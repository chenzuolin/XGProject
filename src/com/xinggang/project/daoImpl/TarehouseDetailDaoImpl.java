package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.TarehouseDetailDao;
import com.xinggang.project.entity.TarehouseDetail;

/**
 * 货物批次Dao实现类
 * 
 * @author Administrator
 * 
 */
public class TarehouseDetailDaoImpl extends BaseDaoImpl implements
		TarehouseDetailDao {
	// 增加
	public boolean save(TarehouseDetail tarehouseDetail) {
		return super.BaseSave(tarehouseDetail);
	}

	// 删除
	public boolean delete(TarehouseDetail tarehouseDetail) {
		return super.BaseDelete(tarehouseDetail);
	}

	// 修改
	public boolean update(TarehouseDetail tarehouseDetail) {
		return super.BaseUpdate(tarehouseDetail);
	}

	// 通过id查询
	public TarehouseDetail getTarehouseDetailId(String id) {
		return (TarehouseDetail) super.findById(TarehouseDetail.class, id);
	}

	// 查询全部
	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getAll() {
		String hql = "from TarehouseDetail order by tdetailId desc";
		return (List<TarehouseDetail>) super.executeQuery(hql, null);
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getTarehouseDetailByPage(int pageNow,
			int rowSize) {
		String hql = "from TarehouseDetail order by tdetailIputTime desc";
		return (List<TarehouseDetail>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询出数据的总行数
	public int getTarehouseDetailByCount() {
		String hql = "select count(*) from TarehouseDetail";
		return super.executeQueryRowCount(hql, null);
	}

	@SuppressWarnings("unchecked")
	// 通过货物进行查询
	public List<TarehouseDetail> getGoods(Integer goodsId) {
		String hql = "from TarehouseDetail where goods=" + goodsId
				+ " order by tdetailIputTime desc";
		return (List<TarehouseDetail>) super.executeQuery(hql, null);
	}

	// 通过货物和编号进行模糊的查询
	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getBianHaoGoodsMohu(String bianhao,
			Integer goodsId) {
		String hql = "from TarehouseDetail where tdetailId like ('" + bianhao
				+ "') and goods = " + goodsId;
		return (List<TarehouseDetail>) super.executeQuery(hql, null);
	}

	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getTarehouseDetailAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String begin,
			String finish, String zhujifu, String TdetailId, String kuname,
			int pageNow, int rowSize) {

		// 编号，货物，入库日期，入库重量，已出重量，入库件数，已出件数,是否提醒，预留字段一，预留字段二，备注
		String hql = "from TarehouseDetail "
				+ " where (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))  and tdetailIputTime >= '" + begin
				+ "' and tdetailIputTime <= '" + finish
				+ "' and tdetailTarehouse.tarehouseName like ('%" + kuname
				+ "%')  order by tdetailIputTime desc";
		return (List<TarehouseDetail>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	public int getTarehouseDetailByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String begin, String finish,
			String zhujifu, String TdetailId, String kuname, int rowSize) {
		String hql = "select count(*) from TarehouseDetail "
				+ " where (goods.goodsName like ('%" + goodsName
				+ "%') or goods.goodsSign like ('%" + goodsName
				+ "%') or goods.goodsStandard.goodsStandardName like ('%"
				+ goodsName
				+ "%') or goods.goodsCategory.goodsCategoryName like ('%"
				+ goodsName
				+ "%') or goods.goodsYieldly.goodsYieldlyName like ('%"
				+ goodsName + "%'))  and tdetailIputTime >= '" + begin
				+ "' and tdetailIputTime <= '" + finish
				+ "' and tdetailTarehouse.tarehouseName like ('%" + kuname
				+ "%')";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 通过预留字段二进行查询
	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getJianShao(Integer kuwei) {
		String hql = "from TarehouseDetail where tdetailTarehouse =" + kuwei
				+ " and tdetailDefinedTwo = '1'";
		return (List<TarehouseDetail>) super.executeQuery(hql, null);
	}

	// 通过库位和货物进行查询
	@SuppressWarnings("unchecked")
	public List<TarehouseDetail> getGoodsTarehouseData(Integer kuwei,
			Integer goods) {
		String hql = "from TarehouseDetail where goods.goodsId="
				+ goods
				+ " and tdetailTarehouse.tarehouseId ="
				+ kuwei
				+ " and tdetailWeight>tdetailEweight and tdetailEnumber<tdetailNumber";
		return (List<TarehouseDetail>) super.executeQuery(hql, null);
	}

}
