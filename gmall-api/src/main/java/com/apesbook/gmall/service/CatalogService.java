package com.apesbook.gmall.service;

import com.apesbook.gmall.bean.PmsBaseCatalog1;
import com.apesbook.gmall.bean.PmsBaseCatalog2;
import com.apesbook.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/2
 */
public interface CatalogService {

    /**
     * 获取一级分类
     * @return
     */
    List<PmsBaseCatalog1> getCatalog1();

    /**
     * 获取二级分类
     * @return
     * @param catalog1Id
     */
    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    /**
     * 获取三级分类
     * @return
     * @param catalog2Id
     */
    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
