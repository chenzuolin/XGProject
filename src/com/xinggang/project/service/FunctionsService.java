package com.xinggang.project.service;

import java.util.List;
import com.xinggang.project.entity.Functions;

/**

 * 功能service
 * 使用系统的公司内部人员职责Service
 * 
 * @author Administrator
 * 
 */
public interface FunctionsService {
	// 添加功能
	public boolean save(Functions functions);

	// 修改功能
	public boolean update(Functions functions);

	// 删除功能
	public boolean delete(Functions functions);

	// 查询功能
	public List<Functions> getAll();

	// 通过id查询
	public Functions getFunctionsId(Integer id);

	// 通过功能名称查询
	public List<Functions> getFunctionsName(String functionsName);

	// 通过功能类别查询
	public List<Functions> getFunctionsClassify(Integer classifyId);
}
