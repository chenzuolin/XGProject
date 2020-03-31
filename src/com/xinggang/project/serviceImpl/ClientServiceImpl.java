package com.xinggang.project.serviceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.struts.upload.FormFile;
import org.springframework.transaction.annotation.Transactional;
import com.xinggang.project.dao.ClientDao;
import com.xinggang.project.entity.Client;
import com.xinggang.project.form.ClientForm;
import com.xinggang.project.service.ClientService;
import com.xinggang.project.tools.MyTools;
import com.xinggang.project.tools.PresentTime;

/**
 * 客户Service
 * 
 * @author Administrator
 * 
 */
@Transactional
public class ClientServiceImpl implements ClientService {
	private ClientDao clientDao;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	// 增加
	public boolean save(Client client) {
		List<Client> list = clientDao
				.getClientName(client.getClientLoginName());
		if (list.size() <= 0) {
			return clientDao.save(client);
		} else {
			return false;
		}
	}

	// 删除
	public boolean delete(Client client) {
		Client c = clientDao.getClientId(client.getClientId());
		if (c == null) {
			return false;
		} else {
			return clientDao.delete(client);
		}
	}

	// 修改
	public boolean update(Client client) {
		System.out.println("******************" + client.getClientId());
		Client c = clientDao.getClientId(client.getClientId());
		if (c == null) {
			return false;
		} else {
			return clientDao.update(client);
		}
	}

	// 通过id查询
	public Client getClientId(Integer id) {
		return clientDao.getClientId(id);
	}

	// 查询全部
	public List<Client> getAll() {
		return clientDao.getAll();
	}

	// 通过用户名和密码查询
	public List<Client> getClientLogin(String loginName, String loginPwd) {

		return clientDao.getClientLogin(loginName, loginPwd);
	}

	// 通过登录名查询
	public List<Client> getClientName(String loginName) {
		return clientDao.getClientName(loginName);
	}

	// 通过单位名称（全拼）,简称，助记符，合同号模糊分页查询
	public List<Client> getClientByPage(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageNow, int rowSize) {

		return clientDao.getClientByPage(qupingdanwei, jiancheng, zhujifu,
				hetonghao, pageNow, rowSize);
	}

	// 通过单位名称（全拼）,简称，助记符，合同号模糊查询出数据的总行数
	public int getClientByCount(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao) {

		return clientDao.getClientByCount(qupingdanwei, jiancheng, zhujifu,
				hetonghao);
	}

	// 查询已停用客户
	public List<Client> getTingyong() {

		return clientDao.getTingyong();
	}

	// 通过结算方式查询
	public List<Client> getJiesuan(String jiesuan) {

		return clientDao.getJiesuan(jiesuan);
	}

	// 通过单位全拼、助记符、简称模糊查询所有的客户
	public List<Client> getMohuClient(String qupingdanwei, String jiancheng,
			String zhujifu) {
		return this.clientDao.getMohuClient(qupingdanwei, jiancheng, zhujifu);
	}

	public boolean saveClient(ClientForm clientForm, String newName,
			String hetongName) {

		PresentTime presentTime = new PresentTime();
		Client client = new Client();
		client.setClientLoginName(clientForm.getClientLoginName());// 登录名
		client.setClientPassword(clientForm.getClientPassword());// 密码
		client.setClientCreateTime(presentTime.getTimes());// 注册时间
		client.setClientTel(clientForm.getClientTel());// 联系方式
		client.setClientHuman(clientForm.getClientHuman());// 负责人
		client.setClientEmail(clientForm.getClientEmail());// email
		// client.setClientStatusImage(newName);// 图片名字
		// client.setClientStatusNumber(clientForm.getClientStatusNumber());//
		// 身份证号码
		client.setClientFirmName(clientForm.getClientFirmName());// 单位名称
		client.setClientAbbreviation(clientForm.getClientAbbreviation());// 简称
		client.setClientSign(clientForm.getClientSign());// 名称助记符
		client.setClientAddress(clientForm.getClientAddress());// 地址
		client.setClientContract(clientForm.getClientContract());// 合同号
		client.setClientStartTime(clientForm.getClientStartTime());// 合同起始日期
		client.setClientFinishTime(clientForm.getClientFinishTime());// 合同结束日期
		client.setClientCease(1);// 是否停用
		client.setClientAccounts(clientForm.getClientAccounts());// 结算方式（现/月）
		client.setClientAgio(clientForm.getClientAgio());// 折扣
		client.setClientCredit(clientForm.getClientCredit());//信用度
		// client.setClientDefinedOne(hetongName);// 合同图片名称
		client.setClientRemark(clientForm.getClientRemark());// 备注
		client.setClientDefinedTwo("1");// 是否在线(1否，0是)
		boolean ok = this.save(client);
		return ok;
	}

	// 图片上传
	public String ioUp(FormFile formImgName, String keepFilePath) {
		if (formImgName.getFileName() == null
				|| formImgName.getFileName().equals("")) {
			return "";
		}
		// 得到图片文件的名字
		String fileName = formImgName.getFileName();
		// 创建读取流
		InputStream is = null;
		// 创建输入流
		OutputStream os = null;
		// 创建新的文件名称（防止上传文件名字相同，覆盖掉前面的文件）
		String newName = MyTools.getNewFileName(fileName);
		// 输出输入流
		try {
			// 获取输出流
			is = formImgName.getInputStream();
			// 得到一个输出流->文件
			// 输入流
			os = new FileOutputStream(keepFilePath + "\\" + newName);
			int len = 0;
			// 缓冲数组
			byte[] bytes = new byte[1024];
			// 循环遍历，将数据读入到数组bytes中，直到读取完，当len为负数时，读取完
			while ((len = is.read(bytes)) > 0) {
				// 读一点，写一点，将数据读取到bytes数组中，从0开始直到len结束
				os.write(bytes, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return newName;

	}

	// 文件的上传（图片等）
	public boolean fileIOUp(ClientForm clientForm, String keepFilePath) {
		boolean ok = false;
		// 得到省份证图片的名字
		// FormFile formImgName = clientForm.getClientStatusImage();
		// 得到合同图片的名字
		FormFile formHeTongImgName = clientForm.getClientDefinedOne();

		// 调用流方法

		// String newName = ioUp(formImgName, keepFilePath);
		// 调用流方法
		String hetongImg = ioUp(formHeTongImgName, keepFilePath);
		// 保存到数据库中
		ok = this.saveClient(clientForm, "", hetongImg);

		return ok;

	}

	public List<Client> getGoodsByPage2(String clientName, int pageNow,
			int rowSize) {

		return clientDao.getClientByPage2(clientName, pageNow, rowSize);
	}

	public int getPageCount(String clientName, int pageSize) {

		return clientDao.getPageCount(clientName, pageSize);

	}

	// 通过条件查询分页
	public int getPageCountTwo(String qupingdanwei, String jiancheng,
			String zhujifu, String hetonghao, int pageSize) {

		return clientDao.getPageCountTwo(qupingdanwei, jiancheng, zhujifu,
				hetonghao, pageSize);

	}

	public boolean updateClient(ClientForm clientForm) {

		boolean ok = false;
		if (clientForm != null) {
			Client client = this.getClientId(clientForm.getClientId());
			client.setClientHuman(clientForm.getClientHuman());
			client.setClientAbbreviation(clientForm.getClientAbbreviation());
			client.setClientSign(clientForm.getClientSign());
			client.setClientAgio(clientForm.getClientAgio());
			client.setClientStartTime(clientForm.getClientStartTime());
			client.setClientFinishTime(clientForm.getClientFinishTime());
			client.setClientCredit(clientForm.getClientCredit());

			ok = this.update(client);
		}

		return ok;

	}

	// 通过单位查询
	public List<Client> getClientDanwei(String danwei) {
		return clientDao.getClientDanwei(danwei);
	}

	// 查询没有结算的客户
	public List<Client> getWeiJieSuan(String begin, String finish,
			String jiancheng, int pageNow, int rowSize) {
		return this.clientDao.getWeiJieSuan(begin, finish, jiancheng, pageNow,
				rowSize);
	}

	// 查询没有结算的客户的页数
	public int getWeiJieSuanCount(String begin, String finish,
			String jiancheng, int pageNow, int rowSize) {
		return this.clientDao.getWeiJieSuanCount(begin, finish, jiancheng,
				pageNow, rowSize);
	}

	// 客户查询费用汇总
	public List<Client> getWeiJieSuanKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize) {
		return this.clientDao.getWeiJieSuanKH(clientId, begin, finish, pageNow,
				rowSize);
	};

	// 客户查询费用汇总页数
	public int getWeiJieSuanCountKH(Integer clientId, String begin,
			String finish, int pageNow, int rowSize) {
		return this.clientDao.getWeiJieSuanCountKH(clientId, begin, finish,
				pageNow, rowSize);
	};

}
