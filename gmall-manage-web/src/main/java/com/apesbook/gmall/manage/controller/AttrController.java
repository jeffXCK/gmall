package com.apesbook.gmall.manage.controller;

import com.apesbook.gmall.bean.PmsBaseAttrInfo;
import com.apesbook.gmall.bean.PmsBaseAttrValue;
import com.apesbook.gmall.bean.PmsBaseSaleAttr;
import com.apesbook.gmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/2
 */
@RestController
public class AttrController {

    @Reference
    private AttrService attrService;

    // 获取平台销售属性的字典值列表
    @PostMapping("baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList = attrService.getBaseSaleAttrList();
        return pmsBaseSaleAttrList;
    }

    // 获取平台属性列表
    @GetMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(@RequestParam String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.getAttrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    // 保存平台属性
    @PostMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        boolean b = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return b?"success":"fail";
    }

    // 获取属性值列表
    @PostMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(@RequestParam String attrId){
        List<PmsBaseAttrValue> attrValueList = attrService.getAttrValueList(attrId);
        return attrValueList;
    }


}
