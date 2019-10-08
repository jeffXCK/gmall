package com.apesbook.gmall.manage.controller;

import com.apesbook.gmall.bean.PmsSkuInfo;
import com.apesbook.gmall.service.SkuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */

@RestController
public class SkuController {

    @Reference
    private SkuService skuService;

    @PostMapping("saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {

        boolean b = skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }

}
