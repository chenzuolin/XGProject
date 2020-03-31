package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

//对应的修改记录form
public class UpdateRecordForm extends ActionForm {

	private static final long serialVersionUID = 8629372992423845456L;
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

	// 查询时所用字段
	private String begin;// 起始时间
	private String finish;// 结束时间

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public Integer getUrid() {
		return urid;
	}

	public void setUrid(Integer urid) {
		this.urid = urid;
	}

	public String getUrzongid() {
		return urzongid;
	}

	public void setUrzongid(String urzongid) {
		this.urzongid = urzongid;
	}

	public String getUrziid() {
		return urziid;
	}

	public void setUrziid(String urziid) {
		this.urziid = urziid;
	}

	public String getUrcaozuoid() {
		return urcaozuoid;
	}

	public void setUrcaozuoid(String urcaozuoid) {
		this.urcaozuoid = urcaozuoid;
	}

	public String getUrfaqiren() {
		return urfaqiren;
	}

	public void setUrfaqiren(String urfaqiren) {
		this.urfaqiren = urfaqiren;
	}

	public String getUrfaqitime() {
		return urfaqitime;
	}

	public void setUrfaqitime(String urfaqitime) {
		this.urfaqitime = urfaqitime;
	}

	public String getUrshenpiren() {
		return urshenpiren;
	}

	public void setUrshenpiren(String urshenpiren) {
		this.urshenpiren = urshenpiren;
	}

	public String getUrshenpitime() {
		return urshenpitime;
	}

	public void setUrshenpitime(String urshenpitime) {
		this.urshenpitime = urshenpitime;
	}

	public String getUrfaqimiaoshu() {
		return urfaqimiaoshu;
	}

	public void setUrfaqimiaoshu(String urfaqimiaoshu) {
		this.urfaqimiaoshu = urfaqimiaoshu;
	}

	public String getUrupdateneirong() {
		return urupdateneirong;
	}

	public void setUrupdateneirong(String urupdateneirong) {
		this.urupdateneirong = urupdateneirong;
	}

	public String getUrupdateremark() {
		return urupdateremark;
	}

	public void setUrupdateremark(String urupdateremark) {
		this.urupdateremark = urupdateremark;
	}

	public String getUrupdatetype() {
		return urupdatetype;
	}

	public void setUrupdatetype(String urupdatetype) {
		this.urupdatetype = urupdatetype;
	}

	public String getUrdefinedone() {
		return urdefinedone;
	}

	public void setUrdefinedone(String urdefinedone) {
		this.urdefinedone = urdefinedone;
	}

	public String getUrdefinedtwo() {
		return urdefinedtwo;
	}

	public void setUrdefinedtwo(String urdefinedtwo) {
		this.urdefinedtwo = urdefinedtwo;
	}

	public String getUrdefinedthree() {
		return urdefinedthree;
	}

	public void setUrdefinedthree(String urdefinedthree) {
		this.urdefinedthree = urdefinedthree;
	}

	public String getUrdefinedfour() {
		return urdefinedfour;
	}

	public void setUrdefinedfour(String urdefinedfour) {
		this.urdefinedfour = urdefinedfour;
	}

	public String getUrdefinedfive() {
		return urdefinedfive;
	}

	public void setUrdefinedfive(String urdefinedfive) {
		this.urdefinedfive = urdefinedfive;
	}

	public String getUrdefinedsix() {
		return urdefinedsix;
	}

	public void setUrdefinedsix(String urdefinedsix) {
		this.urdefinedsix = urdefinedsix;
	}

	public String getUrdefinedseven() {
		return urdefinedseven;
	}

	public void setUrdefinedseven(String urdefinedseven) {
		this.urdefinedseven = urdefinedseven;
	}

	public String getUrdefinedeight() {
		return urdefinedeight;
	}

	public void setUrdefinedeight(String urdefinedeight) {
		this.urdefinedeight = urdefinedeight;
	}

	public String getUrdefinednine() {
		return urdefinednine;
	}

	public void setUrdefinednine(String urdefinednine) {
		this.urdefinednine = urdefinednine;
	}

	public String getUrdefinedten() {
		return urdefinedten;
	}

	public void setUrdefinedten(String urdefinedten) {
		this.urdefinedten = urdefinedten;
	}

	public String getUrdefinedeleven() {
		return urdefinedeleven;
	}

	public void setUrdefinedeleven(String urdefinedeleven) {
		this.urdefinedeleven = urdefinedeleven;
	}

	public String getUrdefinedtwelve() {
		return urdefinedtwelve;
	}

	public void setUrdefinedtwelve(String urdefinedtwelve) {
		this.urdefinedtwelve = urdefinedtwelve;
	}

	public String getUrdefinedthirteen() {
		return urdefinedthirteen;
	}

	public void setUrdefinedthirteen(String urdefinedthirteen) {
		this.urdefinedthirteen = urdefinedthirteen;
	}

	public String getUrdefinedfourteen() {
		return urdefinedfourteen;
	}

	public void setUrdefinedfourteen(String urdefinedfourteen) {
		this.urdefinedfourteen = urdefinedfourteen;
	}

	public String getUrdefinedfifteen() {
		return urdefinedfifteen;
	}

	public void setUrdefinedfifteen(String urdefinedfifteen) {
		this.urdefinedfifteen = urdefinedfifteen;
	}

}
