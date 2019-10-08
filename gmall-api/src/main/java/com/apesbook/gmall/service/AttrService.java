package com.apesbook.gmall.service;

import com.apesbook.gmall.bean.PmsBaseAttrInfo;
import com.apesbook.gmall.bean.PmsBaseAttrValue;
import com.apesbook.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */
public interface AttrService {

    /**
     * 获取平台属性列表
     * @param catalog3Id
     * @return
     */
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    /**
     * 保存平台属性
     * @param pmsBaseAttrInfo
     * @return
     */
    boolean saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 获取平台属性值列表
     * @param attrId
     * @return
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    /**
     * 获取平台销售属性的字典值列表
     * @return
     */
    List<PmsBaseSaleAttr> getBaseSaleAttrList();
}
