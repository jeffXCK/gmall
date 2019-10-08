package com.apesbook.gmall.manage.service.impl;

import com.apesbook.gmall.bean.PmsBaseAttrInfo;
import com.apesbook.gmall.bean.PmsBaseAttrValue;
import com.apesbook.gmall.bean.PmsBaseSaleAttr;
import com.apesbook.gmall.common.validator.Assert;
import com.apesbook.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.apesbook.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.apesbook.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.apesbook.gmall.service.AttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    private PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Autowired
    private PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id) {
        QueryWrapper<PmsBaseAttrInfo> wrapperInfo = new QueryWrapper<>();
        wrapperInfo.eq("catalog3_id", catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrInfoMapper.selectList(wrapperInfo);
        pmsBaseAttrInfoList.forEach(pmsBaseAttrInfo -> {
            QueryWrapper<PmsBaseAttrValue> wrapperValue = new QueryWrapper<>();
            wrapperValue.eq("attr_id", pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrValueMapper.selectList(wrapperValue);
            pmsBaseAttrInfo.setAttrValueList(attrValueList);
        });
        return pmsBaseAttrInfoList;
    }

    @Transactional
    @Override
    public boolean saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        Assert.notNull(pmsBaseAttrInfo, "对象 pmsBaseAttrInfo 不能为空");
        String attrInfoId = pmsBaseAttrInfo.getId();
        // insert 或 update
        if(StringUtils.isBlank(attrInfoId)){
            pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);
            attrInfoId = pmsBaseAttrInfo.getId();
        } else {
            pmsBaseAttrInfoMapper.updateById(pmsBaseAttrInfo);
            QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", attrInfoId);
            pmsBaseAttrValueMapper.delete(wrapper);
        }
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        this.batchSaveAttrValue(attrInfoId, attrValueList);
        return true;
    }

    /**
     * 批量保存平台属性值
     * @param attrId
     * @param attrValueList
     */
    private void batchSaveAttrValue(String attrId, List<PmsBaseAttrValue> attrValueList) {
        if (CollectionUtils.isEmpty(attrValueList)
                || StringUtils.isBlank(attrId)){
            return;
        }
        attrValueList.forEach(pmsBaseAttrValue -> {
            pmsBaseAttrValue.setAttrId(attrId);
            pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
        });
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        QueryWrapper<PmsBaseAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_id", attrId);
        return pmsBaseAttrValueMapper.selectList(wrapper);
    }

    @Override
    public List<PmsBaseSaleAttr> getBaseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectList(null);
    }

}
