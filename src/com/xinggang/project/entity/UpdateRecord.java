package com.xinggang.project.entity;

/**
 * UpdateRecord entity. @author MyEclipse Persistence Tools 订单修改记录类
 */

public class UpdateRecord implements java.io.Serializable {

	private static final long serialVersionUID = -8727865634858138995L;
	private Integer urid;// 修改记录编号
	private String urzongid;// 修改总订单的编号
	private String urziid;// 修改的子订单的编号
	private String urcaozuoid;// 修改的操作订单的编号
	private String urfaqiren;// 对应的发起人
	private String urfaqitime;// 对应的发起时间
	private String urshenpiren;// 对应的审批人
	private String urshenpitime;// 对应的审批时间
	private String urfaqimiaoshu;// 对应的修改发起时的描述
	private String urupdateneirong;// 对应的修改的内容
	private String urupdateremark;// 对应的备注
	private String urupdatetype;// 对应的修改类型，指的是修改的是出库订单还是入库订单
	private String urdefinedone;// 状态，同意或者是不同意
	private String urdefinedtwo;// 货主，对应订单的货主
	private String urdefinedthree;// 一级审批人
	private String urdefinedfour;// 一级审批结果
	private String urdefinedfive;// 一级审批意见
	private String urdefinedsix;// 一级审批时间
	private String urdefinedseven;// 二级审批人
	private String urdefinedeight;// 二级审批结果
	private String urdefinednine;// 二级审批意见
	private String urdefinedten;// 二级审批时间
	private String urdefinedeleven;// 三级审批人
	private String urdefinedtwelve;// 三级审批结果
	private String urdefinedthirteen;// 三级审批意见
	private String urdefinedfourteen;// 三级审批时间
	private String urdefinedfifteen;// 自定义十五

	public UpdateRecord() {
	}

	public UpdateRecord(String urzongid, String urziid, String urcaozuoid,
			String urfaqiren, String urfaqitime, String urshenpiren,
			String urshenpitime, String urfaqimiaoshu, String urupdateneirong,
			String urupdateremark, String urupdatetype, String urdefinedone,
			String urdefinedtwo, String urdefinedthree, String urdefinedfour,
			String urdefinedfive, String urdefinedsix, String urdefinedseven,
			String urdefinedeight, String urdefinednine, String urdefinedten,
			String urdefinedeleven, String urdefinedtwelve,
			String urdefinedthirteen, String urdefinedfourteen,
			String urdefinedfifteen) {
		this.urzongid = urzongid;
		this.urziid = urziid;
		this.urcaozuoid = urcaozuoid;
		this.urfaqiren = urfaqiren;
		this.urfaqitime = urfaqitime;
		this.urshenpiren = urshenpiren;
		this.urshenpitime = urshenpitime;
		this.urfaqimiaoshu = urfaqimiaoshu;
		this.urupdateneirong = urupdateneirong;
		this.urupdateremark = urupdateremark;
		this.urupdatetype = urupdatetype;
		this.urdefinedone = urdefinedone;
		this.urdefinedtwo = urdefinedtwo;
		this.urdefinedthree = urdefinedthree;
		this.urdefinedfour = urdefinedfour;
		this.urdefinedfive = urdefinedfive;
		this.urdefinedsix = urdefinedsix;
		this.urdefinedseven = urdefinedseven;
		this.urdefinedeight = urdefinedeight;
		this.urdefinednine = urdefinednine;
		this.urdefinedten = urdefinedten;
		this.urdefinedeleven = urdefinedeleven;
		this.urdefinedtwelve = urdefinedtwelve;
		this.urdefinedthirteen = urdefinedthirteen;
		this.urdefinedfourteen = urdefinedfourteen;
		this.urdefinedfifteen = urdefinedfifteen;
	}

	public Integer getUrid() {
		return this.urid;
	}

	public void setUrid(Integer urid) {
		this.urid = urid;
	}

	public String getUrzongid() {
		return this.urzongid;
	}

	public void setUrzongid(String urzongid) {
		this.urzongid = urzongid;
	}

	public String getUrziid() {
		return this.urziid;
	}

	public void setUrziid(String urziid) {
		this.urziid = urziid;
	}

	public String getUrcaozuoid() {
		return this.urcaozuoid;
	}

	public void setUrcaozuoid(String urcaozuoid) {
		this.urcaozuoid = urcaozuoid;
	}

	public String getUrfaqiren() {
		return this.urfaqiren;
	}

	public void setUrfaqiren(String urfaqiren) {
		this.urfaqiren = urfaqiren;
	}

	public String getUrfaqitime() {
		return this.urfaqitime;
	}

	public void setUrfaqitime(String urfaqitime) {
		this.urfaqitime = urfaqitime;
	}

	public String getUrshenpiren() {
		return this.urshenpiren;
	}

	public void setUrshenpiren(String urshenpiren) {
		this.urshenpiren = urshenpiren;
	}

	public String getUrshenpitime() {
		return this.urshenpitime;
	}

	public void setUrshenpitime(String urshenpitime) {
		this.urshenpitime = urshenpitime;
	}

	public String getUrfaqimiaoshu() {
		return this.urfaqimiaoshu;
	}

	public void setUrfaqimiaoshu(String urfaqimiaoshu) {
		this.urfaqimiaoshu = urfaqimiaoshu;
	}

	public String getUrupdateneirong() {
		return this.urupdateneirong;
	}

	public void setUrupdateneirong(String urupdateneirong) {
		this.urupdateneirong = urupdateneirong;
	}

	public String getUrupdateremark() {
		return this.urupdateremark;
	}

	public void setUrupdateremark(String urupdateremark) {
		this.urupdateremark = urupdateremark;
	}

	public String getUrupdatetype() {
		return this.urupdatetype;
	}

	public void setUrupdatetype(String urupdatetype) {
		this.urupdatetype = urupdatetype;
	}

	public String getUrdefinedone() {
		return this.urdefinedone;
	}

	public void setUrdefinedone(String urdefinedone) {
		this.urdefinedone = urdefinedone;
	}

	public String getUrdefinedtwo() {
		return this.urdefinedtwo;
	}

	public void setUrdefinedtwo(String urdefinedtwo) {
		this.urdefinedtwo = urdefinedtwo;
	}

	public String getUrdefinedthree() {
		return this.urdefinedthree;
	}

	public void setUrdefinedthree(String urdefinedthree) {
		this.urdefinedthree = urdefinedthree;
	}

	public String getUrdefinedfour() {
		return this.urdefinedfour;
	}

	public void setUrdefinedfour(String urdefinedfour) {
		this.urdefinedfour = urdefinedfour;
	}

	public String getUrdefinedfive() {
		return this.urdefinedfive;
	}

	public void setUrdefinedfive(String urdefinedfive) {
		this.urdefinedfive = urdefinedfive;
	}

	public String getUrdefinedsix() {
		return this.urdefinedsix;
	}

	public void setUrdefinedsix(String urdefinedsix) {
		this.urdefinedsix = urdefinedsix;
	}

	public String getUrdefinedseven() {
		return this.urdefinedseven;
	}

	public void setUrdefinedseven(String urdefinedseven) {
		this.urdefinedseven = urdefinedseven;
	}

	public String getUrdefinedeight() {
		return this.urdefinedeight;
	}

	public void setUrdefinedeight(String urdefinedeight) {
		this.urdefinedeight = urdefinedeight;
	}

	public String getUrdefinednine() {
		return this.urdefinednine;
	}

	public void setUrdefinednine(String urdefinednine) {
		this.urdefinednine = urdefinednine;
	}

	public String getUrdefinedten() {
		return this.urdefinedten;
	}

	public void setUrdefinedten(String urdefinedten) {
		this.urdefinedten = urdefinedten;
	}

	public String getUrdefinedeleven() {
		return this.urdefinedeleven;
	}

	public void setUrdefinedeleven(String urdefinedeleven) {
		this.urdefinedeleven = urdefinedeleven;
	}

	public String getUrdefinedtwelve() {
		return this.urdefinedtwelve;
	}

	public void setUrdefinedtwelve(String urdefinedtwelve) {
		this.urdefinedtwelve = urdefinedtwelve;
	}

	public String getUrdefinedthirteen() {
		return this.urdefinedthirteen;
	}

	public void setUrdefinedthirteen(String urdefinedthirteen) {
		this.urdefinedthirteen = urdefinedthirteen;
	}

	public String getUrdefinedfourteen() {
		return this.urdefinedfourteen;
	}

	public void setUrdefinedfourteen(String urdefinedfourteen) {
		this.urdefinedfourteen = urdefinedfourteen;
	}

	public String getUrdefinedfifteen() {
		return this.urdefinedfifteen;
	}

	public void setUrdefinedfifteen(String urdefinedfifteen) {
		this.urdefinedfifteen = urdefinedfifteen;
	}

}