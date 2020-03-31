package com.xinggang.project.dao;

import java.util.List;

import com.xinggang.project.entity.ClientGoods;

/**
 * 客户货物库存Dao
 * 
 * @author Administrator
 * 
 */
public interface ClientGoodsDao {
	// 增加
	public boolean save(ClientGoods clientGoods);

	// 删除
	public boolean delete(ClientGoods clientGoods);

	// 修改
	public boolean update(ClientGoods clientGoods);

	// 通过id查询
	public ClientGoods getClientGoodsId(String id);

	// 查询全部
	public List<ClientGoods> getAll();

	// 通过模糊查询所有的客户
	public List<ClientGoods> getClientGoodsByCG(Integer kehuId, Integer goodsId);

	// 分页查询
	public List<ClientGoods> getClientGoodsByPage(Integer kehuId,
			Integer goodsId, String zhiya, String dongjie, int pageNow,
			int rowSize);

	// 查询数据总行数
	public int getClientGoodsByCount(Integer kehuId, Integer goodsId,
			String zhiya, String dongjie);

	// 通过客户查询客户的货物
	public List<ClientGoods> getClient(Integer clientId);

	// 通过客分页
	public List<ClientGoods> getClientByPage(Integer clientId, int pageNow,
			int rowSize);

	// 根据客户id,货物名称，规格，材质，属性，查询是否该客户有此货物
	public ClientGoods getClientYesGoods(Integer kehuId, Integer pingleiId,
			Integer guigeId, String goodsName, Integer caizhiId,
			Integer shuxinId, Integer chandiId);

	// 通过客户查询数据的总行数
	public int getClientByCount(Integer clientId);

	// 通过货物查询
	public List<ClientGoods> getHuowu(Integer huowu);

	// 查询是否冻结
	public List<ClientGoods> getDongjie(String dongjie);

	// 查询是否质押
	public List<ClientGoods> getZhiya(String zhiya);

	// 通过客户查询客户货物库存，并且以分页的方式实现
	public List<ClientGoods> getClientGoodsByPage(String goodsName,
			String guige, String caizhi, String shuxing, String zhujifu,
			Integer clientId, int pageNow, int rowSize);

	// 通过客户查询客户货物库存的总页数
	public int getClientGoodsByPageCount(String goodsName, String guige,
			String caizhi, String shuxing, String zhujifu, Integer clientId,
			int rowSize);

	// 以分页的方式查询全部的客户货物库存，可以通过客户单位名称，简称，助记符，货物名称，规格，材质，属性，助记符，进行模糊查询
	public List<ClientGoods> getClientGoodsAllByPage(String danwei,
			String jiancheng, String danweizhujifu, String goodsName,
			String guige, String caizhi, String shuxing, String goodsSign,
			int pageNow, int rowSize);

	// 查询全部的总页数
	public int getClientGoodsAllByPageCount(String danwei, String jiancheng,
			String danweizhujifu, String goodsName, String guige,
			String caizhi, String shuxing, String goodsSign, int rowSize);

	// app客户查询客户货物库存

	public List<ClientGoods> getClientGoodsInfo(Integer kehuId, String pinlei,
			String huowu, String shuxin, String guige, String chandi,
			String caizhi, int pageNow, int rowSize);

	// 客户app查询入库订单,返回行数
	public int getClientGoodsCount(Integer kehuId, String pinlei, String huowu,
			String shuxin, String guige, String chandi, String caizhi,
			int pageSize);

	// 客户app查询入库订单
	public List<ClientGoods> getClientGoodsAll(Integer kehuId, int pageNow,
			int rowSize);

	// 客户app查询入库订单,返回行数
	public int getClientGoodsAllCount(Integer kehuId, int pageSize);

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询
	public List<ClientGoods> getKeHuKuCunByPage(String jiancheng,
			String goodsName, String guige, String caizhi, String shuxing,
			String chandi, int pageNow, int rowSize);

	// 当查询客户库存的时间通过客户的全拼、简称、助记符、货物的名称、品类、规格、产地、助记符进行查询总页数
	public int getKeHuKuCunByPageCount(String jiancheng, String goodsName,
			String guige, String caizhi, String shuxing, String chandi,
			int rowSize);

	// 查询客户进行统计收发存
	public List<ClientGoods> getKeHuSFCByPage(String jiancheng,
			String goodsName, int pageNow, int rowSize);

	// 统计客户收发存的总页数
	public int getKeHuSFCByPageCount(String jiancheng, String goodsName,
			int rowSize);

	// -----------------------------客户根据id查询自己的收发存

	// 查询客户货物的名称、品类、规格、产地、助记符进行查询收发存
	public List<ClientGoods> getKeHuSFCByPageKH(int clientId, String goodsName,
			int pageNow, int rowSize);

	// 查询客户货物的名称、品类、规格、产地、助记符进行查询收发存总页数
	public int getKeHuSFCByPageCountKH(int clientId, String goodsName,
			int rowSize);

	// 通过客户查询对应的货物总值
	public Double clientGoodsSum(Integer clientId);

}
