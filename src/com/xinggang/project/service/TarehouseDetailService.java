package com.xinggang.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xinggang.project.entity.TarehouseDetail;

/**
 * 货物批次Service
 * 
 * @author Administrator
 * 
 */
public interface TarehouseDetailService {
	// 增
	public boolean save(TarehouseDetail tarehouseDetail);

	// 删
	public boolean delete(TarehouseDetail tarehouseDetail);

	// 改
	public boolean update(TarehouseDetail tarehouseDetail);

	// 通过id查询
	public TarehouseDetail getTarehouseDetailId(String id);

	// 查询全部
	public List<TarehouseDetail> getAll();

	// 分页查询
	public List<TarehouseDetail> getTarehouseDetailByPage(int pageNow,
			int rowSize);

	// 查询数据总行数
	public int getTarehouseDetailByCount();

	// 通过货物查询
	public List<TarehouseDetail> getGoods(Integer goodsId);

	// 自动增加货物批次，当客户入库的时候添加，当入库的时候向子订单中增加货物的时候，调用此方法,传入货物的编号和车号,重量和件数
	public void saveGoodsDetail(HttpServletRequest request,Integer goodsId, Integer kuwei, double weight,
			double number);

	// 当出库的时候，记录该批次已出的重量和件数
	public void updateGoodsDetail(String piciId, double weight, double number);

	// 通过货物和编号模糊查询
	public List<TarehouseDetail> getBianHaoGoodsMohu(String bianhao,
			Integer goodsId);

	// 提醒该批次的货物即将到期
	public List<TarehouseDetail> TiXingTDetail();

	// 查询全部，并且可以通过，货物名称，规格，材质，编号，属性，助记符，时间起始日期，结束日期模糊查询
	public List<TarehouseDetail> getTarehouseDetailAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String begin,
			String finish, String zhujifu, String TdetailId, String kuname,
			int pageNow, int rowSize);

	// 查询全部页数
	public int getTarehouseDetailByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String begin, String finish,
			String zhujifu, String TdetailId, String kuname, int rowSize);

	// 通过预留字段二进行查询，判断是否要减少
	public List<TarehouseDetail> getJianShao(Integer kuwei);

	// 通过库位和货物进行查询
	public List<TarehouseDetail> getGoodsTarehouseData(Integer kuwei,
			Integer goods);
	

	
}
