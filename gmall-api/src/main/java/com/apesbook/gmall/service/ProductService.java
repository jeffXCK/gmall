package com.apesbook.gmall.service;

import com.apesbook.gmall.bean.PmsProductImage;
import com.apesbook.gmall.bean.PmsProductInfo;
import com.apesbook.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */
public interface ProductService {

    /**
     * 获取商品列表
     * @param catalog3Id
     * @return
     */
    List<PmsProductInfo> getSpuList(String catalog3Id);

    /**
     * 保存产品信息
     * @param productInfo
     * @return
     */
    boolean saveSpuInfo(PmsProductInfo productInfo);

    /**
     * 获取产品销售属性列表
     * @param productId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrList(String productId);

    /**
     * 获取产品图片列表
     * @param productId
     * @return
     */
    List<PmsProductImage> spuImageList(String productId);
}
