package com.xinggang.project.form;

import org.apache.struts.action.ActionForm;

/**
 * 功能类别表单
 * 
 * @author Administrator
 * 
 */
public class ClassifyForm extends ActionForm {

	private static final long serialVersionUID = -6643738827945675115L;
	private Integer classifyId;// 功能分类编号
	private String classifyName;// 功能分类名称

	public Integer getClassifyId() {
		return this.classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return this.classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

}
