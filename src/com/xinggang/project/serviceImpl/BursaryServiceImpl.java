package com.xinggang.project.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinggang.project.dao.BursaryDao;
import com.xinggang.project.entity.Bursary;
import com.xinggang.project.service.BursaryService;

/**
 * 库房service实现类
 * 
 * @author Administrator
 * 
 */
@Transactional
public class BursaryServiceImpl implements BursaryService {
	private BursaryDao bursaryDao;

	public void setBursaryDao(BursaryDao bursaryDao) {
		this.bursaryDao = bursaryDao;
	}

	// 增加
	public boolean save(Bursary bursary) {

		return bursaryDao.save(bursary);
	}

	// 删除，此项目不能删除
	public boolean delete(Bursary bursary) {

		return false;
	}

	// 修改
	public boolean update(Bursary bursary) {
		Bursary b = bursaryDao.getBursaryId(bursary.getBursaryId());
		if (b == null) {
			return false;
		}
		return bursaryDao.update(bursary);
	}

	// 通过id查询
	public Bursary getBursaryId(Integer id) {

		return bursaryDao.getBursaryId(id);
	}

	// 查询全部
	public List<Bursary> getAll() {

		return bursaryDao.getAll();
	}

	// 通过库房名称查询
	public List<Bursary> getKufangName(String kufangName) {

		return bursaryDao.getKufangName(kufangName);
	}

}
