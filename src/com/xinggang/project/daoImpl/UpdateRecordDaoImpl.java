package com.xinggang.project.daoImpl;

import java.util.List;

import com.xinggang.project.base.BaseDaoImpl;
import com.xinggang.project.dao.UpdateRecordDao;
import com.xinggang.project.entity.UpdateRecord;

//订单修改记录dao实现
public class UpdateRecordDaoImpl extends BaseDaoImpl implements UpdateRecordDao {

	public boolean save(UpdateRecord updateRecord) {
		return super.BaseSave(updateRecord);
	}

	public boolean update(UpdateRecord updateRecord) {
		return super.BaseUpdate(updateRecord);
	}

	public boolean delete(UpdateRecord updateRecord) {
		return super.BaseDelete(updateRecord);
	}

	public UpdateRecord getUpdateRecordId(Integer id) {
		String hql = "from UpdateRecord where urid=" + id;
		return (UpdateRecord) super.findById(hql);
	}

	@SuppressWarnings("unchecked")
	public List<UpdateRecord> getAll() {
		String hql = "from UpdateRecord";
		return (List<UpdateRecord>) super.executeQuery(hql, null);
	}

	// 通过操作订单编号，发起人，状态进行判断是否同意了修改
	@SuppressWarnings("unchecked")
	public List<UpdateRecord> goShenPiPanDuan(String caoId, String iuserName,
			String zhuangtai) {
		String hql = "from UpdateRecord where urcaozuoid='" + caoId
				+ "' and urfaqiren = '" + iuserName + "' and urdefinedone = '"
				+ zhuangtai + "'";
		return (List<UpdateRecord>) super.executeQuery(hql, null);
	}

	// 查询所有的审批记录，以时间和操作人，编号进行模糊查询
	@SuppressWarnings("unchecked")
	public List<UpdateRecord> getAllByPage(String begin, String finish,
			String caozuoren, String bianhao, int pageNow, int rowSize) {
		String hql = "from UpdateRecord where urfaqitime >= '" + begin
				+ " 00:00:00" + "' and urfaqitime <= '" + finish + " 23:59:59"
				+ "' and (urshenpiren like ('%" + caozuoren
				+ "%') or urfaqiren like ('%" + caozuoren
				+ "%')) and (urzongid like('%" + bianhao
				+ "%') or urcaozuoid like ('%" + bianhao
				+ "%')) order by urfaqitime desc";
		return (List<UpdateRecord>) super.executeQueryByPage(hql, null,
				pageNow, rowSize);
	}

	// 查询所有的审批记录的总页数
	public int getAllByPageCount(String begin, String finish, String caozuoren,
			String bianhao, int rowSize) {
		String hql = "select count(*) from UpdateRecord where urfaqitime >= '"
				+ begin + " 00:00:00" + "' and urfaqitime <= '" + finish
				+ " 23:59:59" + "' and (urshenpiren like ('%" + caozuoren
				+ "%') or urfaqiren like ('%" + caozuoren
				+ "%')) and (urzongid like('%" + bianhao
				+ "%') or urcaozuoid like ('%" + bianhao + "%'))";
		return super.queryPageCount(hql, null, rowSize);
	}

	// 查询要进行审批的记录
	@SuppressWarnings("unchecked")
	public List<UpdateRecord> getShenPiCaoZuo() {
		String hql = "from UpdateRecord where urdefinedone != '同意' and urdefinedone!='不同意'";
		return (List<UpdateRecord>) super.executeQuery(hql, null);
	}

	// 通过申请人、发起时间范围和状态查询要进行审批的数据
	@SuppressWarnings("unchecked")
	public List<UpdateRecord> getShenPi(String shenqingren, String begin,
			String finish, String zhaungtai, String shengpiren, String type) {
		// 通过type判断查询的是几级审批人
		String hql = "from UpdateRecord where urfaqitime >= '" + begin
				+ "' and urfaqitime <= '" + finish + "' and urfaqiren like ('%"
				+ shenqingren + "%') and urdefinedone ='" + zhaungtai + "'"
				+ " and ((urdefinedthree='" + shengpiren
				+ "' and urdefinedfour='') or (urdefinedseven='" + shengpiren
				+ "' and urdefinedeight='') or (urdefinedeleven='" + shengpiren
				+ "' and urdefinedtwelve='')) order by urfaqitime desc";
		return (List<UpdateRecord>) super.executeQuery(hql, null);
	}

	// 查询全部的审批记录
	@SuppressWarnings("unchecked")
	public List<UpdateRecord> getUrList(String faqiren, String begin,
			String finish) {
		String hql = "from UpdateRecord where urfaqitime >= '" + begin
				+ "' and urfaqitime <= '" + finish
				+ "' and urdefinedone !='审批中' and (urfaqiren like ('%"
				+ faqiren + "%') " + " or urdefinedthree like '%" + faqiren
				+ "%' or urdefinedseven like '%" + faqiren
				+ "%' or urdefinedeleven like '%" + faqiren
				+ "%') order by urfaqitime desc";
		return (List<UpdateRecord>) super.executeQuery(hql, null);
	}
}
