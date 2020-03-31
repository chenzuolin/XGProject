package com.xinggang.project.service;

import java.util.List;
/**
 * 货物批次操作Servic
 * 
 * @author Administrator
 * 
 */
import com.xinggang.project.entity.TarehouseDetailOperate;

/**
 * 货物批次操作service
 * 
 * @author Administrator
 * 
 */
public interface TarehouseDetailOperateService {
	// 增
	public boolean save(TarehouseDetailOperate tarehouseDetailOperate);

	// 删
	public boolean delete(TarehouseDetailOperate tarehouseDetailOperate);

	// 改
	public boolean update(TarehouseDetailOperate tarehouseDetailOperate);

	// 通过id查询
	public TarehouseDetailOperate getTarehouseDetailOperateId(String id);

	// 查询全部
	public List<TarehouseDetailOperate> getAll();
}
