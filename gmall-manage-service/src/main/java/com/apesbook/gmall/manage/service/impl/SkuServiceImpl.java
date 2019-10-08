package com.apesbook.gmall.manage.service.impl;

import com.apesbook.gmall.bean.PmsSkuAttrValue;
import com.apesbook.gmall.bean.PmsSkuImage;
import com.apesbook.gmall.bean.PmsSkuInfo;
import com.apesbook.gmall.bean.PmsSkuSaleAttrValue;
import com.apesbook.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.apesbook.gmall.manage.mapper.PmsSkuImageMapper;
import com.apesbook.gmall.manage.mapper.PmsSkuInfoMapper;
import com.apesbook.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.apesbook.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.utils.Assert;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    private PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;


    @Override
    public boolean saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        Assert.notNull(pmsSkuInfo, "对象 PmsSkuInfo 不能为空");
        String skuId = pmsSkuInfo.getId();
        // insert OR update
        if(StringUtils.isBlank(skuId)){
            // 1. insert PmsProductInfo
            pmsSkuInfoMapper.insert(pmsSkuInfo);
            skuId = pmsSkuInfo.getId();
        } else {
            // 1. update PmsProductInfo
            pmsSkuInfoMapper.updateById(pmsSkuInfo);

            // 清除 PmsSkuImage、PmsSkuAttrValue、PmsSkuSaleAttrValue 表的旧数据
            Map<String, Object> map = new HashMap<>();
            map.put("sku_id", skuId);
            pmsSkuImageMapper.deleteByMap(map);
            pmsSkuAttrValueMapper.deleteByMap(map);
            pmsSkuSaleAttrValueMapper.deleteByMap(map);
        }

        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        // 2. save PmsSkuImage list
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insert(pmsSkuImage);
        }
        // 3. save PmsSkuAttrValue list
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }

        // 4. save PmsSkuSaleAttrValue list
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }

        return true;
    }
}
