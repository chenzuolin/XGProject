package com.xinggang.project.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.dao.ExportSeedDao;
import com.xinggang.project.dao.GoodsDao;
import com.xinggang.project.dao.InputSeedDao;
import com.xinggang.project.dao.SetAccountsDao;
import com.xinggang.project.entity.Goods;
import com.xinggang.project.entity.SetAccounts;
import com.xinggang.project.tools.StatisticsWorking;

public class test {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		
		int hangshu = 11;  
		int yiban = hangshu / 2 + 1;  
		int yibanduo = hangshu / 2;  
		System.out.println("空心菱形：");  
		for(int k = 1;k <= yiban;k++){  
		    for(int i = 1;i <= (yiban - k);i++){  
		        System.out.print(" ");  
		    }  
		    System.out.print("*");  
		    for(int i = 1;i <= ( (k - 2) * 2 + 1);i++){  
		        System.out.print(" ");  
		    }  
		    if(k != 1){  
		        System.out.print("*");    
		    }  
		    System.out.println();  
		}  
		for(int k = yibanduo;k >=1;k--){  
		    for(int i = 1;i <= (yiban - k);i++){  
		        System.out.print(" ");  
		    }  
		    System.out.print("*");  
		    for(int i = 1;i <= ( (k - 2) * 2 + 1);i++){  
		        System.out.print(" ");  
		    }  
		    if(k != 1){  
		        System.out.print("*");    
		    }  
		    System.out.println();  
		}  
		String[] zimu = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" };
		for (int i = 0; i < (zimu.length / 2); i++) {
			for (int k = 0; k <= (zimu.length / 2) - i; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= ((i - 2) * 2 + 1); j++) {
				System.out.print(" ");
				System.out.print(zimu[j]);
			}
			System.out.println();
		}
	}

	public void 进行又一次的测试() {
		// String strMd5=DigestUtils.md5Hex(strss);
		// System.out.println(strMd5);

		StatisticsWorking s = new StatisticsWorking();
		String str = "20170728-001,20170728-002,";
		String strs = str.replaceAll(",", "");
		int x = s.countInnerStr(str, ",");
		for (int i = 0; i < x; i++) {
			System.out.println(strs.substring(i * 12, (i + 1) * 12));
		}

		String ss = "Hellow      Word";
		String tt = ss.replaceAll(" *", " ");
		System.out.println(tt);
	}

	public void ceshi() {
		/*
		 * DecimalFormat df = new DecimalFormat("#######000.000");
		 * System.out.println(df.format(null));
		 */
		// System.out.println(df.parse(df.format(new
		// Date())).getTime()>=df.parse("24:59:59").getTime());
		// System.out.println(df.parse("23:59:59").getTime());
	}

	/*
	 * public void ff(){ ApplicationContext ac = new
	 * ClassPathXmlApplicationContext( "applicationContext.xml");
	 * XiazhanfeiService xia = (XiazhanfeiService)
	 * ac.getBean("xiazhanfeiService"); Xiazhanfei xiaz = new Xiazhanfei();
	 * xiaz.setXzcount(120.36); boolean ok = xia.save(xiaz);
	 * System.out.println(ok); /*ApplicationContext ac = new
	 * ClassPathXmlApplicationContext( "applicationContext.xml");
	 */
	/*
	 * ExportSeedDao esd = (ExportSeedDao) ac.getBean("exportSeedDao");
	 * InputSeedDao isd = (InputSeedDao) ac.getBean("inputSeedDao");
	 */
	/*
	 * GoodsDao gd = (GoodsDao) ac.getBean("goodsDao"); ClientGoodsDao cgd =
	 * (ClientGoodsDao) ac.getBean("clientGoodsDao");
	 * 
	 * List<ClientGoods> cglist = cgd.getKeHuSFCByPage("", "", 1, 2); for(int
	 * i=0; i<cglist.size(); i++){
	 * System.out.println(cglist.get(i).getClient().getClientAbbreviation()); }
	 * 
	 * System.out.println(gd.getZhuanFaCunByCount("", "", 1));
	 * 
	 * System.out.println(cglist.size()); int p = cgd.getKeHuSFCByPageCount("",
	 * "", 1); System.out.println(p);
	 * 
	 * /*String begin = "2017-07-12"; String finish = "2017-07-15";
	 * List<ClientGoods> cglist = cgd.getKeHuKuCunByPage("", "", 1, 10);
	 * System.out.println(cglist.size()); for(int i=0; i<cglist.size(); i++){
	 * System.out.println("这里是中央电视台"+esd.getKeHuKunCunCKHJ(begin, finish,
	 * cglist.get(i).getClient().getClientId(),
	 * cglist.get(i).getGoods().getGoodsId()).size()); }
	 */

	/*
	 * //统计的在某个时间段的出库的重量合计 List<ExportSeed> eslist =
	 * esd.getZhuanFaCunChuKuGeJi(begin, finish, "", ""); for(ExportSeed
	 * es:eslist){ System.out.println("对应货物的id是："+es.getGoods().getGoodsId()+
	 * "对应的时间段的出库合计是："+es.getEseedRealityWeight()); } //统计在起始日期之前的出库的订单重量
	 * List<ExportSeed> qclist = esd.getZhuanFaCunChuKuQiChu(begin, "", "");
	 * for(ExportSeed es:qclist){ System.out.println("对应的货物的id是："+es.getGoods
	 * ().getGoodsId()+"对应的期初库存是："+es.getEseedRealityWeight()); }
	 */

	// -------------------------------------
	// 统计的在某个时间段的入库的重量合计
	/*
	 * List<InputSeed> islist = isd.getZhuanFaCunRuKuGeJi(begin,finish, "", "");
	 * for(InputSeed is:islist){ System.out.println("对应的货物的编号是："+is.getGoods
	 * ().getGoodsId()+"入库的时间段的合计是："+is.getIseedRealityWeight()); }
	 * //统计的起始日期之前的入库重量的合计 List<InputSeed> ruqclist =
	 * isd.getZhuanFaCunRuKuQiChu(begin, "", ""); for(InputSeed is:ruqclist){
	 * System.out.println("对应的货物的编号是："+is.getGoods().getGoodsId
	 * ()+"，对应的入库期初库存是"+is.getIseedRealityWeight()); }
	 */

	/*
	 * System.out.println("正真的统计----------------------------------------");
	 * List<Goods> glist = gd.getZhuanFaCunGoods("", "", 1, 100);
	 * List<ExportSeed> eslist = esd.getKeHuKunCunCKHJ(begin, finish, 5, 34);
	 * for (int i = 0; i < eslist.size(); i++) {
	 * System.out.println(eslist.get(i).getEseedRealityWeight()); }
	 */
	// }

	public void tongji() {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ExportSeedDao esd = (ExportSeedDao) ac.getBean("exportSeedDao");
		InputSeedDao isd = (InputSeedDao) ac.getBean("inputSeedDao");
		GoodsDao gd = (GoodsDao) ac.getBean("goodsDao");

		String begin = "2017-07-12";
		String finish = "2017-07-15";
		/*
		 * //统计的在某个时间段的出库的重量合计 List<ExportSeed> eslist =
		 * esd.getZhuanFaCunChuKuGeJi(begin, finish, "", ""); for(ExportSeed
		 * es:eslist){
		 * System.out.println("对应货物的id是："+es.getGoods().getGoodsId()+
		 * "对应的时间段的出库合计是："+es.getEseedRealityWeight()); } //统计在起始日期之前的出库的订单重量
		 * List<ExportSeed> qclist = esd.getZhuanFaCunChuKuQiChu(begin, "", "");
		 * for(ExportSeed es:qclist){
		 * System.out.println("对应的货物的id是："+es.getGoods
		 * ().getGoodsId()+"对应的期初库存是："+es.getEseedRealityWeight()); }
		 */

		// -------------------------------------
		// 统计的在某个时间段的入库的重量合计
		/*
		 * List<InputSeed> islist = isd.getZhuanFaCunRuKuGeJi(begin,finish, "",
		 * ""); for(InputSeed is:islist){
		 * System.out.println("对应的货物的编号是："+is.getGoods
		 * ().getGoodsId()+"入库的时间段的合计是："+is.getIseedRealityWeight()); }
		 * //统计的起始日期之前的入库重量的合计 List<InputSeed> ruqclist =
		 * isd.getZhuanFaCunRuKuQiChu(begin, "", ""); for(InputSeed
		 * is:ruqclist){
		 * System.out.println("对应的货物的编号是："+is.getGoods().getGoodsId
		 * ()+"，对应的入库期初库存是"+is.getIseedRealityWeight()); }
		 */

		System.out.println("正真的统计----------------------------------------");
		List<Goods> glist = gd.getZhuanFaCunGoods("", "", 1, 100);
		for (int i = 0; i < glist.size(); i++) {
			if (esd.getZhuanFaCunChuKuGeJi(begin, finish,
					glist.get(i).getGoodsId()).size() > 0) {
				glist.get(i).setExportHeJi(
						esd.getZhuanFaCunChuKuGeJi(begin, finish,
								glist.get(i).getGoodsId()).get(0)
								.getEseedRealityWeight() == null ? 0.0 : esd
								.getZhuanFaCunChuKuGeJi(begin, finish,
										glist.get(i).getGoodsId()).get(0)
								.getEseedRealityWeight());
			} else {
				glist.get(i).setExportHeJi(0.0);
			}
			if (isd.getZhuanFaCunRuKuQiChu(begin, glist.get(i).getGoodsId())
					.size() > 0) {
				glist.get(i).setQiChu(
						isd.getZhuanFaCunRuKuQiChu(begin,
								glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight() == null ? 0.0 : isd
								.getZhuanFaCunRuKuQiChu(begin,
										glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight());
			} else {
				glist.get(i).setQiChu(0.0);
			}
			if (esd.getZhuanFaCunChuKuQiChu(begin, glist.get(i).getGoodsId())
					.size() > 0) {
				glist.get(i).setQiChu(
						glist.get(i).getQiChu()
								- esd.getZhuanFaCunChuKuQiChu(begin,
										glist.get(i).getGoodsId()).get(0)
										.getEseedRealityWeight());
			}
			if (isd.getZhuanFaCunRuKuGeJi(begin, finish,
					glist.get(i).getGoodsId()).size() > 0) {
				glist.get(i).setInputHeJi(
						isd.getZhuanFaCunRuKuGeJi(begin, finish,
								glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight() == null ? 0.0 : isd
								.getZhuanFaCunRuKuGeJi(begin, finish,
										glist.get(i).getGoodsId()).get(0)
								.getIseedRealityWeight());
			} else {
				glist.get(i).setInputHeJi(0.0);
			}

			glist.get(i).setQiMo(
					(glist.get(i).getQiChu() + glist.get(i).getInputHeJi())
							- glist.get(i).getExportHeJi());
		}
		for (int i = 0; i < glist.size(); i++) {
			System.out.println("货物的编号是：" + glist.get(i).getGoodsId()
					+ ",期初的重量是：" + glist.get(i).getQiChu() + "，入库合计重量是："
					+ glist.get(i).getInputHeJi() + "出库合计的重量是："
					+ glist.get(i).getExportHeJi() + ",期末重量是："
					+ glist.get(i).getQiMo());
		}
	}

	public test() {
		System.out.println("fjsdkjfks");
	}

	public test(String str, int i) {
		System.out.println(str);
	}

	public void fff() {
		List<String> goods = new ArrayList<String>();
		goods.add("螺纹钢");
		goods.add("圆钢");

		List<String> guige = new ArrayList<String>();
		guige.add("12");
		guige.add("14");

		List<String> caizhi = new ArrayList<String>();
		caizhi.add("300");
		caizhi.add("400");

		List<String> shuxing = new ArrayList<String>();
		shuxing.add("9M");
		shuxing.add("12M");

		List<String> chandi = new ArrayList<String>();
		chandi.add("酒钢");

		for (int i = 0; i < goods.size(); i++) {
			for (int j = 0; j < guige.size(); j++) {
				for (int c = 0; c < caizhi.size(); c++) {
					for (int s = 0; s < shuxing.size(); s++) {
						// new d
						System.out.println("货物名称：" + goods.get(i) + ",规格："
								+ guige.get(j) + ",材质：" + caizhi.get(c)
								+ ",属性：" + shuxing.get(s));
						// save
						// d = null
					}
				}
			}
		}

	}

	public void fhj() {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SetAccountsDao setAccountsDao = (SetAccountsDao) ac
				.getBean("setAccountsDao");
		SetAccounts saccounts = new SetAccounts();
		/*
		 * List<Classify> classList=classifyDao.getAll();
		 * classify.setClassifyName("总部"); boolean
		 * ok=classifyDao.save(classify); classify.setClassifyName("kaka");
		 * System.out.println(ok); if(classList!=null){ for(Classify
		 * list:classList){ System.out.println("名字是:"+list.getClassifyName()); }
		 * }else{ System.out.println("查询失败！"); }
		 */
		saccounts.setSaday("5");
		saccounts.setSarate(0.005);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		saccounts.setSatime(df.format(new Date()));
		boolean ok = setAccountsDao.save(saccounts);
		System.out.println(ok);
		if (ok) {
			System.out.println("成功！");
		} else {
			System.out.println("失败！");
		}
	}

}
