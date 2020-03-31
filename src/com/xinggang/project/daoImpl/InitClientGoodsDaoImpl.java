package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.InitClientGoodsDao;
import com.xinggang.project.entity.InitClientGoods;

public class InitClientGoodsDaoImpl extends BaseDaoImpl implements
		InitClientGoodsDao {

	@SuppressWarnings("unchecked")
	public List<InitClientGoods> getclientGoods(String type) {
		String hql = "";
		if (type.equals("client")) {
			hql = "select new InitClientGoods(icgClient,icgName,icgGuige,icgCaizhi,icgShuxing,icgChandi,round(sum(icgWeight),3)) "
					+ "from InitClientGoods group by icgClient,icgName,icgGuige,icgCaizhi,icgShuxing,icgChandi";
		} else {
			hql = "select new InitClientGoods(icgName,icgGuige,icgCaizhi,icgShuxing,icgChandi,round(sum(icgWeight),3)) "
					+ "from InitClientGoods group by icgName,icgGuige,icgCaizhi,icgShuxing,icgChandi";
		}
		return (List<InitClientGoods>) super.executeQuery(hql, null);
	}

}
