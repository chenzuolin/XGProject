package com.xinggang.project.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.dao.ClientGoodsDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.entity.ClientGoods;
import com.xinggang.project.form.ClientGoodsForm;
import com.xinggang.project.service.ClientGoodsService;
import com.xinggang.project.tools.PageRow;
import com.xinggang.project.tools.PresentTime;

/**
 * 客户货物库存Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ClientGoodsServiceImpl implements ClientGoodsService {
	// 客户货物库存dao
	private ClientGoodsDao clientGoodsDao;
	// 货物dao
	private GoodsDao goodsDao;
	// 客户dao
	private ClientDao clientDao;
	private PresentTime pt = new PresentTime();

	public void setClientGoodsDao(ClientGoodsDao clientGoodsDao) {
		this.clientGoodsDao = clientGoodsDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	// 增加
	public boolean save(ClientGoods clientGoods) {

		return clientGoodsDao.save(clientGoods);
	}

	// 删除
	public boolean delete(ClientGoods clientGoods) {
		ClientGoods c = clientGoodsDao.getClientGoodsId(clientGoods
				.getCgoodsId());
		if (c == null) {
			return false;
		}
		return clientGoodsDao.delete(clientGoods);
	}

	// 修改
	public boolean update(ClientGoods clientGoods) {
		ClientGoods c = clientGoodsDao.getClientGoodsId(clientGoods
				.getCgoodsId());
		if (c == null) {
			return false;
		}
		return clientGoodsDao.update(clientGoods);
	}

	// 通过id查询
	public ClientGoods getClientGoodsId(String id) {

		return clientGoodsDao.getClientGoodsId(id);
	}

	// 查询全部
	public List<ClientGoods> getAll() {

		return clientGoodsDao.getAll();
	}

	// 全部分页查询，通过客户，货物，质押，冻结，进行模糊查询
	public List<ClientGoods> getClientGoodsByPage(Integer kehuId,
			Integer goodsId, String zhiya, String dongjie, int pageNow,
			int rowSize) {

		return clientGoodsDao.getClientGoodsByPage(kehuId, goodsId, zhiya,
				dongjie, pageNow, rowSize);
	}

	// 查询数据总行数
	public int getClientGoodsByCount(Integer kehuId, Integer goodsId,
			String zhiya, String dongjie) {

		return clientGoodsDao.getClientGoodsByCount(kehuId, goodsId, zhiya,
				dongjie);
	}

	// 通过客户查询
	public List<ClientGoods> getClient(Integer clientId) {

		return clientGoodsDao.getClient(clientId);
	}

	// 通过客户分页查询
	public List<ClientGoods> getClientByPage(Integer clientId, int pageNow,
			int rowSize) {

		return clientGoodsDao.getClientByPage(clientId, pageNow, rowSize);
	}

	// 通过客户查询出数据总行数
	public int getClientByCount(Integer clientId) {

		return clientGoodsDao.getClientByCount(clientId);
	}

	// 通过货物查询
	public List<ClientGoods> getHuowu(Integer huowu) {

		return clientGoodsDao.getHuowu(huowu);
	}

	// 查询是否冻结
	public List<ClientGoods> getDongjie(String dongjie) {

		return clientGoodsDao.getDongjie(dongjie);
	}

	// 查询是否质押
	public List<ClientGoods> getZhiya(String zhiya) {

		return clientGoodsDao.getZhiya(zhiya);
	}

	// 根据客户id,货物名称，规格，材质，属性，查询是否该客户有此货物
	public ClientGoods getClientYesGoods(Integer kehuId, Integer pingleiId,
			Integer guigeId, String goodsName, Integer caizhiId,
			Integer shuxinId, Integer chandiId) {
		return clientGoodsDao.getClientYesGoods(kehuId, pingleiId, guigeId,
				goodsName, caizhiId, shuxinId, chandiId);
	};

	// 通过客户和货物
	public List<ClientGoods> getClientGoodsByCG(Integer kehuId, Integer goodsId) {
		return clientGoodsDao.getClientGoodsByCG(kehuId, goodsId);
	};

	// 收取滞纳金的时候自动解冻该客户的货物库存
	public void JieDong(Integer clientId) {
		// 通过客户查询到该客户的库存
		List<ClientGoods> cglist = this.clientGoodsDao.getClient(clientId);
		// 遍历库存，修改冻结的状态
		for (ClientGoods cg : cglist) {
			ClientGoods cgs = this.getClientGoodsId(cg.getCgoodsId());// 通过遍历的类找到对应的数据
			cgs.setCgoodsWeight(cgs.getCgoodsFreezeWeight());// 设置重量为冻结的重量
			cgs.setCgoodsFreeze("否");
			cgs.setCgoodsFreezeWeight(0.0);// 冻结重量改变为0
			cgs.setCgoodsFreezeNumber(0.0);// 冻结件数改变为0
			this.clientGoodsDao.update(cgs);
		}
	}

	// 当客户入库时如果该客户的库存中有该货物，则重量增加，否则像客户库存中增加一条数据,传入重量和件数，客户和货物编号
	public void ZengjiaKucun(Integer clientId, Integer goodsId, double weight,
			double number) {
		String tGoods = null; // 保存查询出来的库存编号
		DecimalFormat df = new DecimalFormat("##############0.000");
		List<ClientGoods> cglist = this.clientGoodsDao.getClientGoodsByCG(
				clientId, goodsId);
		if (cglist.size() > 0) {
			tGoods = cglist.get(0).getCgoodsId();
		}

		// 如果该客户具有该货物，那么就增加相应的重量和件数
		if (tGoods != null) {
			ClientGoods cGoods = this.clientGoodsDao.getClientGoodsId(tGoods);// 通过id查询
			double w = cGoods.getCgoodsWeight() + weight;// 得到以前库存的重量加传入的重量；
			double wdouble = Double.parseDouble(df.format(w));// 保存三位小数
			cGoods.setCgoodsWeight(wdouble);// 保存该客户的货物重量
			/*
			 * 客户不具有件数的设置 double n = cGoods.getCgoodsNumber() + number;//
			 * 得到以前库存的件数加传入的件数 int nint = (int) (n * 1000); double ndouble =
			 * (double) nint / 1000;// 保存三位小数 cGoods.setCgoodsNumber(ndouble);//
			 * 保存该客户的货物的件数
			 */
			this.clientGoodsDao.update(cGoods);
		} else {
			try {
				ClientGoods clientGoods = new ClientGoods();
				clientGoods.setCgoodsId(pt.getDatesNotTime()
						+ new PageRow().getKeHuKuCunNumber());// 客户库存的编号，客户编号+当前时间+货物的编号
				clientGoods.setClient(this.clientDao.getClientId(clientId));// 保存对应的客户
				clientGoods.setGoods(this.goodsDao.getGoodsId(goodsId));// 保存对应的货物
				clientGoods.setCgoodsWeight(weight);// 保存重量
				clientGoods.setCgoodsFreeze("否");// 设置冻结为否
				clientGoods.setCgoodsFreezeWeight(0.0);// 设置冻结的重量
				clientGoods.setCgoodsFreezeNumber(0.0);// 设置冻结的件数
				clientGoods.setCgoodsImpawn("否");// 设置质押为否
				clientGoods.setCgoodsImpawnWeight(0.0);// 设置质押重量
				clientGoods.setCgoodsImpawnNumber(0.0);// 设置质押的件数
				// 客户不具有件数的设置
				// clientGoods.setCgoodsNumber(number);// 保存件数
				this.clientGoodsDao.save(clientGoods);
			} catch (Exception e) {

			}
		}
	}

	// 当客户出库或者过户发起方的时候，则货物的重量减少
	public void JianshaoKucun(Integer clientId, Integer goodsId, double weight) {
		List<ClientGoods> clist = this.clientGoodsDao.getClientGoodsByCG(
				clientId, goodsId);// 通过客户查询
		DecimalFormat df = new DecimalFormat("############0.000");
		try {
			if (clist.size() > 0) {
				ClientGoods clientGoods = clist.get(0);//
				double w = clientGoods.getCgoodsWeight() - weight;// 得到的重量减去传入的要出的重量
				double wdouble = Double.parseDouble(df.format(w));// 保存三位小数
				clientGoods.setCgoodsWeight(wdouble);// 保存现有的重量
				this.clientGoodsDao.update(clientGoods);
			}
		} catch (Exception e) {

		}
	}

	// 冻结该客户的货物库存
	public boolean DongJieClientGoods(ClientGoodsForm cgf) {
		if (cgf != null) {

			ClientGoods cg = this.clientGoodsDao.getClientGoodsId(cgf
					.getCgoodsId());// 通过id查询
			double w = cg.getCgoodsWeight();
			cg.setCgoodsFreeze("是");// 设置是否冻结为是
			cg.setCgoodsWeight(w - cgf.getCgoodsFreezeWeight());// 本来库存减少
			cg.setCgoodsFreezeWeight((cg.getCgoodsFreezeWeight() == null ? 0.0
					: cg.getCgoodsFreezeWeight()) + cgf.getCgoodsFreezeWeight());// 设置冻结的重量,以前冻结的重量，加现在冻结的重量
			cg.setCgoodsRemark(cgf.getCgoodsRemark());// 设置备注
			return this.clientGoodsDao.update(cg);
		} else {
			return false;
		}
	}

	// 解冻该客户的货物库存
	public boolean JieDongClientGoods(ClientGoodsForm cgf) {
		if (cgf != null) {

			ClientGoods cg = this.clientGoodsDao.getClientGoodsId(cgf
					.getCgoodsId());// 通过id查询
			cg.setCgoodsFreeze("否");// 设置是否冻结为否
			cg.setCgoodsWeight(cg.getCgoodsWeight()
					+ cgf.getCgoodsFreezeWeight());// 设置解冻的重量,将解冻的重量添加到相应的库存中
			cg.setCgoodsFreezeWeight(cg.getCgoodsFreezeWeight()
					- cgf.getCgoodsFreezeWeight());// 设置冻结的重量为,实际存在的冻结重量-解冻的重量
			cg.setCgoodsRemark(cgf.getCgoodsRemark());// 设置备注
			return this.clientGoodsDao.update(cg);
		} else {
			return false;
		}
	}

	// 质押该客户的货物库存
	public boolean ZhiYaClientGoods(ClientGoodsForm cgf) {
		if (cgf != null) {

			ClientGoods cg = this.clientGoodsDao.getClientGoodsId(cgf
					.getCgoodsId());// 通过id查询
			double w = cg.getCgoodsWeight() - cgf.getCgoodsImpawnWeight();
			cg.setCgoodsImpawn("是");// 设置是否质押为是
			cg.setCgoodsImpawnWeight(cg.getCgoodsImpawnWeight()
					+ cgf.getCgoodsImpawnWeight());// 设置质押的重量,以前质押的重量加现在质押的重量
			cg.setCgoodsWeight(w);// 实际的重量减去质押的重量等于现有的重量
			return this.clientGoodsDao.update(cg);
		} else {
			return false;
		}
	}

	// 解除质押该客户的货物库存
	public boolean JCZhiYaClientGoods(ClientGoodsForm cgf) {
		if (cgf != null) {

			ClientGoods cg = this.clientGoodsDao.getClientGoodsId(cgf
					.getCgoodsId());// 通过id查询
			double w = cg.getCgoodsWeight() + cgf.getCgoodsImpawnWeight();
			cg.setCgoodsImpawn("否");// 设置是否质押为否
			cg.setCgoodsImpawnWeight(cg.getCgoodsImpawnWeight()
					- cgf.getCgoodsImpawnWeight());// 设置质押的重量,输入解除质押的重量，实际的质押重量-解除的重量，如果全部解除，那么输入的值和质押质押的重量相等
			cg.setCgoodsWeight(w);// 实际的重量加质押的重量等于现有的重量
			return this.clientGoodsDao.update(cg);
		} else {
			return false;
		}
	}

	// 查询全部的客户货物库存，可以通过客户和货物进行模糊的查询
	public List<ClientGoods> getClientGoodsAll(ClientGoodsForm cgf) {
		return null;
	}

	// 通过卡户查询客户货物库存，并且以分页的形式显示
	public List<ClientGoods> getClientGoodsByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			Integer clientId, int pageNow, int rowSize) {
		return this.clientGoodsDao.getClientGoodsByPage(goodsName, guige,
				caizhi, shuxing, zhujifu, clientId, pageNow, rowSize);
	}

	// 通过客户查询数据的总页数
	public int getClientGoodsByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, Integer clientId,
			int rowSize) {
		return this.clientGoodsDao.getClientGoodsByPageCount(goodsName, guige,
				caizhi, shuxing, zhujifu, clientId, rowSize);
	}

	// 查询全部，并且以分页的形式显示
	public List<ClientGoods> getClientGoodsAllByPage(String danwei,
			String jiancheng, String danweizhujifu, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			int pageNow, int rowSize) {
		return this.clientGoodsDao.getClientGoodsAllByPage(danwei, jiancheng,
				danweizhujifu, goodsName, guige, caizhi, shuxing, goodsSign,
				pageNow, rowSize);
	}

	// 查询全部的总页数
	public int getClientGoodsAllByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String goodsName, String guige,
			String caizhi, String shuxing, String goodsSign, int rowSize) {
		return this.clientGoodsDao.getClientGoodsAllByPageCount(danwei,
				jiancheng, danweizhujifu, goodsName, guige, caizhi, shuxing,
				goodsSign, rowSize);
	}

	// app客户查询客户货物库存

	public List<ClientGoods> getClientGoodsInfo(Integer kehuId, String pinlei,
			String huowu, String shuxin, String guige, String chandi,
			String caizhi, int pageNow, int rowSize) {
		return clientGoodsDao.getClientGoodsInfo(kehuId, pinlei, huowu, shuxin,
				guige, chandi, caizhi, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getClientGoodsCount(Integer kehuId, String pinlei, String huowu,
			String shuxin, String guige, String chandi, String caizhi,
			int pageSize) {
		return clientGoodsDao.getClientGoodsCount(kehuId, pinlei, huowu,
				shuxin, guige, chandi, caizhi, pageSize);
	};

	// 客户app查询入库订单
	public List<ClientGoods> getClientGoodsAll(Integer kehuId, int pageNow,
			int rowSize) {
		return clientGoodsDao.getClientGoodsAll(kehuId, pageNow, rowSize);
	};

	// 客户app查询入库订单,返回行数
	public int getClientGoodsAllCount(Integer kehuId, int pageSize) {
		return clientGoodsDao.getClientGoodsAllCount(kehuId, pageSize);
	};

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询
	public List<ClientGoods> getKeHuKuCunByPage(String jiancheng,
			String goodsName, String guige, String caizhi, String shuxing,
			String chandi, int pageNow, int rowSize) {
		return this.clientGoodsDao.getKeHuKuCunByPage(jiancheng, goodsName,
				guige, caizhi, shuxing, chandi, pageNow, rowSize);
	}

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询总页数
	public int getKeHuKuCunByPageCount(String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String chandi,
			int rowSize) {
		return this.clientGoodsDao.getKeHuKuCunByPageCount(jiancheng,
				goodsName, guige, caizhi, shuxing, chandi, rowSize);
	}

	// -----------------------------客户根据id查询自己的库存

	// 查询客户货物的名称、品类、规格、产地、助记符进行查询
	public List<ClientGoods> getKeHuSFCByPageKH(int clientId, String goodsName,
			int pageNow, int rowSize) {
		return this.clientGoodsDao.getKeHuSFCByPageKH(clientId, goodsName,
				pageNow, rowSize);
	};

	// 查询客户货物的名称、品类、规格、产地、助记符进行查询总页数
	public int getKeHuSFCByPageCountKH(int clientId, String goodsName,
			int rowSize) {
		return this.clientGoodsDao.getKeHuSFCByPageCountKH(clientId, goodsName,
				rowSize);
	};

	// 查询客户进行统计收发存
	public List<ClientGoods> getKeHuSFCByPage(String jiancheng,
			String goodsName, int pageNow, int rowSize) {
		return this.clientGoodsDao.getKeHuSFCByPage(jiancheng, goodsName,
				pageNow, rowSize);
	}

	// 统计客户收发存的总页数
	public int getKeHuSFCByPageCount(String jiancheng, String goodsName,
			int rowSize) {
		return this.clientGoodsDao.getKeHuSFCByPageCount(jiancheng, goodsName,
				rowSize);
	}

	// 通过客户查询对应的货物总值
	public Double clientGoodsSum(Integer clientId) {
		return this.clientGoodsDao.clientGoodsSum(clientId);
	}

}
