package com.xinggang.project.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.ShiftStockDao;
import com.xinggang.project.dao.ShiftStockSeedDao;
import com.xinggang.project.entity.Client;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.ShiftStock;
import com.xinggang.project.entity.ShiftStockSeed;
import com.xinggang.project.form.ShiftStockForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.service.CostTypeService;
import com.xinggang.project.service.ShiftStockService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 过户总订单Service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ShiftStockServiceImpl implements ShiftStockService {
	// 过户dao
	private ShiftStockDao shiftStockDao;
	// 当前时间工具类
	private PresentTime pt = new PresentTime();
	// 编号中增加的变量
	private PageRow pr = new PageRow();
	// 客户到
	private ClientDao clientDao;
	// 货物dao
	private GoodsDao goodsDao;
	// 过户子订单dao
	private ShiftStockSeedDao shiftStockSeedDao;
	// 客户货物库存service
	private ClientGoodsService clientGoodsService;
	// 费用类型service
	private CostTypeService costTypeService;

	public void setClientGoodsService(ClientGoodsService clientGoodsService) {
		this.clientGoodsService = clientGoodsService;
	}

	public void setCostTypeService(CostTypeService costTypeService) {
		this.costTypeService = costTypeService;
	}

	public void setShiftStockSeedDao(ShiftStockSeedDao shiftStockSeedDao) {
		this.shiftStockSeedDao = shiftStockSeedDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setShiftStockDao(ShiftStockDao shiftStockDao) {
		this.shiftStockDao = shiftStockDao;
	}

	// 增加
	public boolean save(ShiftStock shiftStock) {
		return shiftStockDao.save(shiftStock);
	}

	// 删除
	public boolean delete(ShiftStock shiftStock) {
		ShiftStock s = shiftStockDao.getShiftStockId(shiftStock.getSstockId());
		if (s == null) {
			return false;
		} else {
			return shiftStockDao.delete(shiftStock);
		}
	}

	// 修改
	public boolean update(ShiftStock shiftStock) {
		ShiftStock s = shiftStockDao.getShiftStockId(shiftStock.getSstockId());
		if (s == null) {
			return false;
		} else {
			return shiftStockDao.update(shiftStock);
		}
	}

	// 通过id查询
	public ShiftStock getShiftStockId(String id) {
		return shiftStockDao.getShiftStockId(id);
	}

	// 查询全部，订单作废的不查
	public List<ShiftStock> getAll() {

		return shiftStockDao.getAll();
	}

	// 通过全部分页查询
	public List<ShiftStock> getShiftStockByPage(int pageNow, int rowSize) {

		return shiftStockDao.getShiftStockByPage(pageNow, rowSize);
	}

	// 通过全部查询出数据的总行数
	public int getShiftStockByCount() {

		return shiftStockDao.getShiftStockByCount();
	}

	// 通过时间范围和客户模糊查询
	public List<ShiftStock> getShijian(String begin, String finish,
			String dingdan, Integer kehu) {

		return shiftStockDao.getShijian(begin, finish, dingdan, kehu);
	}

	// 通过时间范文客户模糊分页查询
	public List<ShiftStock> getShijianByPage(String begin, Integer kehu,
			String finish, String dingdan, int pageNow, int rowSize) {

		return shiftStockDao.getShijianByPage(begin, kehu, finish, dingdan,
				pageNow, rowSize);
	}

	// 通过时间范围和客户模糊查询数据总行数
	public int getShijianByCount(String begin, Integer kehu, String finish,
			String dingdan) {

		return shiftStockDao.getShijianByCount(begin, kehu, finish, dingdan);
	}

	// 通过客户查询
	public List<ShiftStock> getKehu(Integer kehu, Integer zhuanru) {

		return shiftStockDao.getKehu(kehu, zhuanru);
	}

	// 通过客户分页查询
	public List<ShiftStock> getKehuByPage(Integer kehu, Integer zhuanru,
			int pageNow, int rowSize) {

		return shiftStockDao.getKehuByPage(kehu, zhuanru, pageNow, rowSize);
	}

	// 通过客户查询数据总行数
	public int getKehuByCount(Integer kehu, Integer zhuanru) {

		return shiftStockDao.getKehuByCount(kehu, zhuanru);
	}

	// 通过订单状态查询
	public List<ShiftStock> getZhuangtai(String zhuang) {

		return shiftStockDao.getZhuangtai(zhuang);
	}

	// 通过订单状态分页查询
	public List<ShiftStock> getZhuangtaiByPage(String zhuang, int pageNow,
			int rowSize) {

		return shiftStockDao.getZhuangtaiByPage(zhuang, pageNow, rowSize);
	}

	// 通过订单状态查询数据总行数
	public int getZhuangtaiByCount(String zhuang) {

		return shiftStockDao.getZhuangtaiByCount(zhuang);
	}

	// 发起过户
	public boolean saveShiftStock(ShiftStockForm ssf,String faqiren) {
		ShiftStock ss = new ShiftStock();// 实例化一个过户类
		boolean ok = false;
		int num = 0;
		try {
			String guohubianhao = pt.getDatesNotTime() + pr.getGuoHuNumber();
			// 客户的信息
			ss.setSstockId("转" + guohubianhao);// 设置过户订单编号
			ss.setSstockClientNumber(ssf.getSstockClientNumber());// 设置客户的过户订单号
			ss.setClientBySstockClientId(this.clientDao.getClientId(ssf
					.getClientBySstockClientId()));// 设置过户发起的客户
			ss.setSstockReateTime(pt.getTimes());// 过户的日期
			ss.setClientBySstockShiftToFirm(this.clientDao.getClientId(ssf
					.getClientBySstockShiftToFirm()));// 设置转入单位
			// ss.setSstockTel(ssf.getSstockTel());// 设置转入单位的联系电话
			ss.setSstockOrderStatus("正在审核");// 设置过户的订单状态
			// ss.setSstockRemark(ssf.getSstockRemark());// 设置总订单的备注
			ss.setSstockDefinedOne(ssf.getSstockDefinedOne());// 设置转库类型
			ok = this.shiftStockDao.save(ss);
			if (ok) {
				// 货物的信息
				for (int i = 0; i < ssf.getGoodss().length; i++) {
					ShiftStockSeed sss = new ShiftStockSeed();// 实例化一个过户子订单类
					String is = "";// 格式化子订单编号
					if ((i + 1) < 10) {
						is = "00" + (i + 1);
					} else if ((i + 1) >= 10 && (i + 1) < 100) {
						is = "0" + (i + 1);
					} else {
						is = String.valueOf((i + 1));
					}
					sss.setSsseedId("转Z" + guohubianhao + is);// 过户子订单编号
					sss.setShiftStock(ss);// 过户总订单编号
					sss.setGoods(this.goodsDao.getGoodsId(ssf.getGoodss()[i]));// 转出的货物，多个
					sss.setSsseedWeight(ssf.getWeight()[i]);// 过户的重量，多个
					sss.setSsseedOrderStatus("正在审核");// 设置订单状态为正在审核
					// --------------------------------------------------------计算出相应的应收费用，未实现------------------------------------
					// 设置应收费用
					double should = ssf.getWeight()[i]
							* this.costTypeService.getGuoHu();
					DecimalFormat df = new DecimalFormat("#########0.00");
					long shouldint = (long) (should * 1000);
					if (shouldint % 10 != 0 && shouldint % 10 < 5) {
						shouldint = shouldint + 10;
					}
					should = (double) ((double) shouldint / 1000);
					sss.setSsseedShouldCost(Double.parseDouble(df
							.format(should)));
					sss.setSsseedRemark(ssf.getRemark()[i]);// 对应的备注，多个

					boolean o = this.shiftStockSeedDao.save(sss);
					if (o) {
						num++;
						// 对应的发起方的库存相应的减少，当审核通过的时候，转入单位相应的库存增加，如果审核不通过，那么发起方的库存增加
						this.clientGoodsService.JianshaoKucun(
								ssf.getClientBySstockClientId(),
								ssf.getGoodss()[i], ssf.getWeight()[i]);// 减少过户方的库存
					}
				}
			}
		} catch (Exception e) {

		}
		if (num >= ssf.getGoodss().length) {
			ok = true;
		}
		return ok;
	}

	int i = 0;

	// 手机app发起过户
	public boolean saveShiftStockApp(ShiftStockForm ssf) {
		boolean ok = false;
		Goods good = goodsDao.getOneGoods(ssf.getGoodsCategoryId(),
				ssf.getGoodsStandardId(), ssf.getGoodsName(),
				ssf.getGoodsQualityId(), ssf.getGoodsPropertyId(),
				ssf.getGoodsYieldlyId());
		if (good == null) {
			return false;
		} else {
			List<ClientGoods> cglist = this.clientGoodsService
					.getClientGoodsByCG(ssf.getClientBySstockClientId(),
							good.getGoodsId());

			if (cglist.size() <= 0) {
				return false;
			}
			try {
				// 根据单位，查询该客户
				List<Client> listClient = this.clientDao.getClientDanwei(ssf
						.getFirm());
				// 如果有进入，否则返回false
				if (listClient.size() > 0) {
					ShiftStock ss = new ShiftStock();// 实例化一个过户类
					String guohubianhao = pt.getDatesNotTime()
							+ pr.getGuoHuNumber();
					// 客户的信息
					ss.setSstockId("转" + guohubianhao);// 设置过户订单编号
					ss.setSstockClientNumber(ssf.getSstockClientNumber());// 设置客户的过户订单号
					ss.setClientBySstockClientId(this.clientDao.getClientId(ssf
							.getClientBySstockClientId()));// 设置过户发起的客户
					ss.setSstockReateTime(pt.getTimes());// 过户的日期
					ss.setClientBySstockShiftToFirm(listClient.get(0));// 设置转入单位
					// ss.setSstockTel(ssf.getSstockTel());// 设置转入单位的联系电话
					ss.setSstockOrderStatus("正在审核");// 设置过户的订单状态
					ss.setSstockDefinedOne("正常转库");// 设置转库类型
					ss.setSstockRemark(ssf.getSstockRemark());// 设置总订单的备注

					ok = this.shiftStockDao.save(ss);
					if (ok) {
						// 货物的信息

						ShiftStockSeed sss = new ShiftStockSeed();// 实例化一个过户子订单类
						String is = "";// 格式化子订单编号
						if ((i + 1) < 10) {
							is = "00" + (i + 1);
						} else if ((i + 1) >= 10 && (i + 1) < 100) {
							is = "0" + (i + 1);
						} else {
							is = String.valueOf((i + 1));
						}
						i++;

						sss.setSsseedId("转Z" + guohubianhao + is);// 过户子订单编号
						sss.setShiftStock(ss);// 过户总订单编号
						sss.setGoods(good);// 转出的货物
						sss.setSsseedWeight(ssf.getWeight()[0]);// 过户的重量，多个
						sss.setSsseedOrderStatus("正在审核");// 设置订单状态为正在审核
						// --------------------------------------------------------计算出相应的应收费用，未实现------------------------------------
						// 设置应收费用
						double should = ssf.getWeight()[0]
								* this.costTypeService.getGuoHu();
						DecimalFormat df = new DecimalFormat("#########0.00");
						long shouldint = (long) (should * 1000);
						if (shouldint % 10 != 0 && shouldint % 10 < 5) {
							shouldint = shouldint + 10;
						}
						should = (double) ((double) shouldint / 1000);
						sss.setSsseedShouldCost(Double.parseDouble(df
								.format(should)));
						sss.setSsseedRemark(ssf.getSstockRemark());// 对应的备注，多个

						boolean o = this.shiftStockSeedDao.save(sss);
						if (o) {
							// 对应的发起方的库存相应的减少，当审核通过的时候，转入单位相应的库存增加，如果审核不通过，那么发起方的库存增加
							this.clientGoodsService.JianshaoKucun(
									ssf.getClientBySstockClientId(),
									good.getGoodsId(), ssf.getWeight()[0]);// 减少过户方的库存

						}
						ok = true;
					}

				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
			return ok;
		}

	}
}
