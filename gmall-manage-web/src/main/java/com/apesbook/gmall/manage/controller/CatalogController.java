package com.apesbook.gmall.manage.controller;

import com.apesbook.gmall.bean.PmsBaseCatalog1;
import com.apesbook.gmall.bean.PmsBaseCatalog2;
import com.apesbook.gmall.bean.PmsBaseCatalog3;
import com.apesbook.gmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/2
 */
@RestController
public class CatalogController {

    @Reference
    private CatalogService catalogService;

    @PostMapping("getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(@RequestParam String catalog2Id){
        List<PmsBaseCatalog3> catalog3s = catalogService.getCatalog3(catalog2Id);
        return catalog3s;
    }

    @PostMapping("getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(@RequestParam String catalog1Id){
        List<PmsBaseCatalog2> catalog2s = catalogService.getCatalog2(catalog1Id);
        return catalog2s;
    }

    @PostMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> catalog1s = catalogService.getCatalog1();
        return catalog1s;
    }
}
