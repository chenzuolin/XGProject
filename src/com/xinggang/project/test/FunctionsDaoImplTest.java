package com.xinggang.project.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xinggang.project.dao.FunctionsDao;
import com.xinggang.project.entity.Functions;
/**
 * 功能表测试
 * @author Administrator
 *
 */
public class FunctionsDaoImplTest {

	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		FunctionsDao functionsDao=(FunctionsDao) ac.getBean("functionsDao");
		Functions functions =new Functions();
		
		//ClassifyDao classifyDao=(ClassifyDao) ac.getBean("classifyDao");
		
		
		//查询
		/*System.out.println("-----------------查询----------------");
		List<Functions> functionList=functionsDao.getAll();
		for(Functions fun:functionList){
			//Classify classify=classifyDao.getClassifyId(fun.getClassify().getClassifyId());
			//fun.setClassify(classify);
			System.out.println("************************");
			System.out.println("-------"+fun.getFunctionName()+","+fun.getClassify().getClassifyName());
			
		}*/
		//增加
		/*functions.setFunctionName("出库");
		functions.setFunctionRemark("出库人员出库");
		functions.setFunctionDefinedOne(null);
		functions.setFunctionDefinedTwo(null);
		functions.setClassify(classify);
		boolean ok=functionsDao.save(functions);
		if(ok){
			System.out.println("成功！");
		}else{
			System.out.println("失败！");
		}*/
		
		//增加
		functions=functionsDao.getFunctionsId(2);
		System.out.println(functions+"fds");
		functions.setFunctionName(functions.getFunctionName()+"123");
		
		boolean ok=functionsDao.update(functions);
		if(ok){
			System.out.println("成功！");
		}else{
			System.out.println("失败！");
		}
		
		
	}

}
