package com.xinggang.project.test;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xinggang.project.dao.ClassifyDao;
import com.xinggang.project.entity.Classify;

public class ClassifyDaoImplTest {

	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		ClassifyDao classifyDao=(ClassifyDao) ac.getBean("classifyDao");
		//Classify classify=new Classify();
		List<Classify> classList=classifyDao.getAll();
		
		if(classList.size()<=0){
			for(Classify list:classList){
				System.out.println("名字是:"+list.getClassifyName());
			}
		}else{
			System.out.println("无功能类别！");
		}
		/*Classify c = new Classify();
		c.setClassifyName("仓储管理");
		boolean ok = classifyDao.save(c);
		if(ok){
			System.out.println("添加成功！");
		}else{
			System.out.println("添加失败!");
		}*/
		/*Classify c = new Classify();
		c.setClassifyName("仓储管理");
		boolean ok = classifyDao.delete(c);
		if(ok){
			System.out.println("删除成功！");
		}else{
			System.out.println("删除失败！");
		}*/
	}

}
