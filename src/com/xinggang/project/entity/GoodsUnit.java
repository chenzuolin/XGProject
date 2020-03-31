package com.xinggang.project.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * GoodsUnit entity. @author MyEclipse Persistence Tools
 * 计量单位
 */

public class GoodsUnit  implements java.io.Serializable {

	private static final long serialVersionUID = 8651762020737984433L;
	private Integer goodsUnitId;//计量单位id
     private String goodsUnitName;//计量单位名称
     private String goodsUnitDefinedOne;
     private String goodsUnitDefinedTwo;
     private String goodsUnitRemark;//备注
     private Set<Object> goodses = new HashSet<Object>(0);

    // Constructors

    /** default constructor */
    public GoodsUnit() {
    }

    
    /** full constructor */
    public GoodsUnit(String goodsUnitName, String goodsUnitDefinedOne, String goodsUnitDefinedTwo, String goodsUnitRemark, Set<Object> goodses) {
        this.goodsUnitName = goodsUnitName;
        this.goodsUnitDefinedOne = goodsUnitDefinedOne;
        this.goodsUnitDefinedTwo = goodsUnitDefinedTwo;
        this.goodsUnitRemark = goodsUnitRemark;
        this.goodses = goodses;
    }

   
    public Integer getGoodsUnitId() {
        return this.goodsUnitId;
    }
    
    public void setGoodsUnitId(Integer goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }

    public String getGoodsUnitName() {
        return this.goodsUnitName;
    }
    
    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public String getGoodsUnitDefinedOne() {
        return this.goodsUnitDefinedOne;
    }
    
    public void setGoodsUnitDefinedOne(String goodsUnitDefinedOne) {
        this.goodsUnitDefinedOne = goodsUnitDefinedOne;
    }

    public String getGoodsUnitDefinedTwo() {
        return this.goodsUnitDefinedTwo;
    }
    
    public void setGoodsUnitDefinedTwo(String goodsUnitDefinedTwo) {
        this.goodsUnitDefinedTwo = goodsUnitDefinedTwo;
    }

    public String getGoodsUnitRemark() {
        return this.goodsUnitRemark;
    }
    
    public void setGoodsUnitRemark(String goodsUnitRemark) {
        this.goodsUnitRemark = goodsUnitRemark;
    }

    public Set<Object> getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set<Object> goodses) {
        this.goodses = goodses;
    }
   








}