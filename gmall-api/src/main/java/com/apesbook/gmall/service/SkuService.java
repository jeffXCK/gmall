package com.apesbook.gmall.service;

import com.apesbook.gmall.bean.PmsSkuInfo;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/5
 */
public interface SkuService {

    /**
     * 保存 SKU (库存存储单元)
     * @param pmsSkuInfo
     * @return
     */
    boolean saveSkuInfo(PmsSkuInfo pmsSkuInfo);
}
