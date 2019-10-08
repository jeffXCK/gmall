package com.apesbook.gmall.manage.service.impl;

import com.apesbook.gmall.bean.PmsProductImage;
import com.apesbook.gmall.bean.PmsProductInfo;
import com.apesbook.gmall.bean.PmsProductSaleAttr;
import com.apesbook.gmall.bean.PmsProductSaleAttrValue;
import com.apesbook.gmall.manage.mapper.PmsProductImageMapper;
import com.apesbook.gmall.manage.mapper.PmsProductInfoMapper;
import com.apesbook.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.apesbook.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.apesbook.gmall.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.utils.Assert;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    private PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    private PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    private PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;


    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        QueryWrapper<PmsProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("catalog3_id", catalog3Id);
        List<PmsProductInfo> productInfoList = pmsProductInfoMapper.selectList(wrapper);
        return productInfoList;
    }

    @Transactional
    @Override
    public boolean saveSpuInfo(PmsProductInfo productInfo) {
        Assert.notNull(productInfo, "对象 PmsProductInfo 不能为空");
        String productId = productInfo.getId();
        // insert OR update
        if (StringUtils.isBlank(productId)){
            // 1. insert PmsProductInfo
            pmsProductInfoMapper.insert(productInfo);
            productId = productInfo.getId();

        } else {
            // 1. update PmsProductInfo
            pmsProductInfoMapper.updateById(productInfo);

            // 清除 PmsProductImage、PmsProductSaleAttr、PmsProductSaleAttrValue 表的旧数据
            Map<String, Object> map = new HashMap<>();
            map.put("product_id", productId);
            pmsProductImageMapper.deleteByMap(map);
            pmsProductSaleAttrMapper.deleteByMap(map);
            pmsProductSaleAttrValueMapper.deleteByMap(map);
        }

        final String finalProductId = productId;
        List<PmsProductImage> pmsProductImageList = productInfo.getPmsProductImageList();
        List<PmsProductSaleAttr> pmsProductSaleAttrList = productInfo.getPmsProductSaleAttrList();
        // 2. save PmsProductImage list
        pmsProductImageList.forEach(pmsProductImage -> {
            pmsProductImage.setProductId(finalProductId);
            pmsProductImageMapper.insert(pmsProductImage);
        });
        // 3. save PmsProductSaleAttr list
        pmsProductSaleAttrList.forEach(pmsProductSaleAttr -> {
            pmsProductSaleAttr.setProductId(finalProductId);
            pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);

            // 4. save PmsProductSaleAttrValue list
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getPmsProductSaleAttrValueList();
            pmsProductSaleAttrValueList.forEach(pmsProductSaleAttrValue -> {
                pmsProductSaleAttrValue.setProductId(finalProductId);
                pmsProductSaleAttrValueMapper.insert(pmsProductSaleAttrValue);
            });
        });

        return true;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String productId) {
        QueryWrapper<PmsProductSaleAttr> wrapperAttr = new QueryWrapper<>();
        wrapperAttr.eq("product_id", productId);
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.selectList(wrapperAttr);
        pmsProductSaleAttrList.forEach(pmsProductSaleAttr -> {
            QueryWrapper<PmsProductSaleAttrValue> wrapperValue = new QueryWrapper<>();
            wrapperValue.eq("product_id", productId);
            wrapperValue.eq("sale_attr_id", pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> valueList = pmsProductSaleAttrValueMapper.selectList(wrapperValue);
            pmsProductSaleAttr.setPmsProductSaleAttrValueList(valueList);
        });
        return pmsProductSaleAttrList;
    }

    @Override
    public List<PmsProductImage> spuImageList(String productId) {
        QueryWrapper<PmsProductImage> wrapperImage = new QueryWrapper<>();
        wrapperImage.eq("product_id", productId);
        List<PmsProductImage> pmsProductImageList = pmsProductImageMapper.selectList(wrapperImage);
        return pmsProductImageList;
    }
}
