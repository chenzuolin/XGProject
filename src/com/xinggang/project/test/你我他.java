package com.xinggang.project.test;

public class 你我他  extends 人 implements 人心 {
	public void 善正(String str){
		if(!str.equals("人要善") && !str.equals("心要正")){
			while(true){
				System.out.println("死循环，去死吧！");
			}
		}else{
			System.out.println("静静的看着天空！");
		}
	}
	
	public static void main(String[] args) {
		new 你我他().善正("人要善");
		System.out.println("终于我的电脑死机了！  ˉ—ˉ ^_^ ");
	}
}

class 人{
	public void 善正(String str){
		System.out.println("人生就像程序，不是继承就是实现！");
	}
}

interface 人心{
	void 善正(String str);
}

