package com.xinggang.project.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.GoodsCategoryDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.TarehouseDao;
import com.xinggang.project.dao.TarehouseDetailDao;
import com.xinggang.project.entity.GoodsCategory;
import com.xinggang.project.entity.TarehouseDetail;
import com.xinggang.project.service.TarehouseDetailService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 货物批次Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class TarehouseDetailServiceImpl implements TarehouseDetailService {
	private TarehouseDetailDao tarehouseDetailDao;
	// 货物service
	private GoodsDao goodsDao;
	// 货物品类service
	private GoodsCategoryDao goodsCategoryDao;
	// 库位service
	private TarehouseDao tarehouseDao;

	// 编号
	private PageRow pr = new PageRow();

	public void setTarehouseDao(TarehouseDao tarehouseDao) {
		this.tarehouseDao = tarehouseDao;
	}

	public void setGoodsCategoryDao(GoodsCategoryDao goodsCategoryDao) {
		this.goodsCategoryDao = goodsCategoryDao;
	}

	// 时间工具类
	private static PresentTime pt = new PresentTime();

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setTarehouseDetailDao(TarehouseDetailDao tarehouseDetailDao) {
		this.tarehouseDetailDao = tarehouseDetailDao;
	}

	// 增加
	public boolean save(TarehouseDetail tarehouseDetail) {

		return tarehouseDetailDao.save(tarehouseDetail);
	}

	// 删除
	public boolean delete(TarehouseDetail tarehouseDetail) {

		return false;
	}

	// 修改
	public boolean update(TarehouseDetail tarehouseDetail) {
		TarehouseDetail t = tarehouseDetailDao
				.getTarehouseDetailId(tarehouseDetail.getTdetailId());
		if (t == null) {
			return false;
		}
		return tarehouseDetailDao.update(tarehouseDetail);
	}

	// 通过id查询
	public TarehouseDetail getTarehouseDetailId(String id) {

		return tarehouseDetailDao.getTarehouseDetailId(id);
	}

	// 查询全部
	public List<TarehouseDetail> getAll() {

		return tarehouseDetailDao.getAll();
	}

	// 分页查询
	public List<TarehouseDetail> getTarehouseDetailByPage(int pageNow,
			int rowSize) {

		return tarehouseDetailDao.getTarehouseDetailByPage(pageNow, rowSize);
	}

	// 查询数据总行数
	public int getTarehouseDetailByCount() {

		return tarehouseDetailDao.getTarehouseDetailByCount();
	}

	// 通过货物查询
	public List<TarehouseDetail> getGoods(Integer goodsId) {

		return tarehouseDetailDao.getGoods(goodsId);
	}

	// 自动增加货物批次，当客户入库的时候添加，当入库的时候向子订单中增加货物的时候，调用此方法,传入货物的编号和车号,重量和件数
	public void saveGoodsDetail(HttpServletRequest request,Integer goodsId, Integer kuwei, double weight,
			double number) {
		String numbers = pr.getPiCiNumber();
		TarehouseDetail td = new TarehouseDetail();
		String pici=pt.getDatesNotTime() + "-" + numbers;
		//保存批次号，保存到操作表中
		request.getSession().setAttribute("pici", pici);
		
		td.setTdetailId(pici);// 设置批次编号	
		td.setGoods(this.goodsDao.getGoodsId(goodsId));// 添加对应的货物
		td.setTdetailIputTime(pt.getTimes());// 添加入库日期
		td.setTdetailWeight(weight);// 入库的重量
		td.setTdetailEweight(0.0);// 已出的重量
		td.setTdetailNumber(number);// 入库的件数
		td.setTdetailEnumber(0.0);// 已出的件数
		td.setTdetailRemind(0);// 设置是否提醒为0
		td.setTdetailTarehouse(this.tarehouseDao.getTarehouseId(kuwei));// 添加对应的库位
		this.tarehouseDetailDao.save(td);
		
	}
	

	// 当出库的时候，记录该批次已出的重量和件数，在出库中以for循环的方式实现比较好
	public void updateGoodsDetail(String piciId, double weight, double number) {
		TarehouseDetail td = this.tarehouseDetailDao
				.getTarehouseDetailId(piciId);// 通过编号查询
		td.setTdetailEweight(weight);// 记录已出的重量
		td.setTdetailEnumber(number);// 记录已出的件数
		this.tarehouseDetailDao.update(td);
	}

	// 通过货物和批次编号进行模糊查询
	public List<TarehouseDetail> getBianHaoGoodsMohu(String bianhao,
			Integer goodsId) {

		return this.tarehouseDetailDao.getBianHaoGoodsMohu(bianhao, goodsId);
	}

	// 提醒货物是否即将到期,当登录的时候调用，并且是保管员，登录进去的时候调用
	public List<TarehouseDetail> TiXingTDetail() {

		List<TarehouseDetail> tdlist = this.tarehouseDetailDao.getAll();
		List<GoodsCategory> gclist = this.goodsCategoryDao.getAll();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<TarehouseDetail> tdslist = new ArrayList<TarehouseDetail>();
		for (TarehouseDetail td : tdlist) {
			for (GoodsCategory gc : gclist) {
				try {
					Date inputTime = df.parse(td.getTdetailIputTime()); // 取出入库的时间
					int youxiao = gc.getGoodsCategoryPovalidity();// 取出品类的有效期
					boolean x = df.parse(df.format(new Date())).getTime()
							- inputTime.getTime() >= (youxiao - 10) * 24 * 60
							* 60 * 1000;// 判断当前日期减去 入库的日期是否大于登录货物的有效期
					if (td.getGoods().getGoodsCategory().getGoodsCategoryId() == gc
							.getGoodsCategoryId()
							&& x
							&& td.getTdetailWeight() - td.getTdetailEweight() >= 1.0
							&& td.getTdetailRemind() <= 5) {
						TarehouseDetail tds = this.tarehouseDetailDao
								.getTarehouseDetailId(td.getTdetailId());// 通过编号查询
						tds.setTdetailRemind(tds.getTdetailRemind() + 1);// 提醒的次数增加，当提醒到5的时候不再提醒
						this.tarehouseDetailDao.update(tds);
						tdslist.add(td);
					}
				} catch (Exception e) {

				}
			}
		}

		return tdslist;
	}

	// 查询全部的货物批次，分页显示
	public List<TarehouseDetail> getTarehouseDetailAllByPage(String goodsName,
			String guige, String caizhi, String shuxing, String begin,
			String finish, String zhujifu, String TdetailId, String kuname,
			int pageNow, int rowSize) {
		return this.tarehouseDetailDao.getTarehouseDetailAllByPage(goodsName,
				guige, caizhi, shuxing, begin, finish, zhujifu, TdetailId,
				kuname, pageNow, rowSize);
	}

	// 查询全部的货物批次的总页数
	public int getTarehouseDetailByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String begin, String finish,
			String zhujifu, String TdetailId, String kuname, int rowSize) {
		return this.tarehouseDetailDao.getTarehouseDetailByPageCount(goodsName,
				guige, caizhi, shuxing, begin, finish, zhujifu, TdetailId,
				kuname, rowSize);
	}

	public List<TarehouseDetail> getJianShao(Integer kuwei) {
		return this.tarehouseDetailDao.getJianShao(kuwei);
	}

	public List<TarehouseDetail> getGoodsTarehouseData(Integer kuwei,
			Integer goods) {
		return this.tarehouseDetailDao.getGoodsTarehouseData(kuwei, goods);
	}
}
