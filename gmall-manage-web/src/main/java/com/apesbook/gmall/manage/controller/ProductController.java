package com.apesbook.gmall.manage.controller;

import com.apesbook.gmall.bean.PmsProductImage;
import com.apesbook.gmall.bean.PmsProductInfo;
import com.apesbook.gmall.bean.PmsProductSaleAttr;
import com.apesbook.gmall.service.ProductService;
import com.apesbook.gmall.web.config.FastdfsProperties;
import com.apesbook.gmall.web.util.FastdfsUploadFileUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/3
 */

@RestController
public class ProductController {

    @Reference
    private ProductService productService;

    @Autowired
    private FastdfsProperties fastdfsProperties;

    @GetMapping("spuList")
    public List<PmsProductInfo> spuList(@RequestParam String catalog3Id) {

        List<PmsProductInfo> pmsProductInfoList = productService.getSpuList(catalog3Id);
        return pmsProductInfoList;
    }

    @PostMapping("saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo productInfo) {
        boolean b = productService.saveSpuInfo(productInfo);
        return b?"success":"fail";
    }

    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        // 将图片上传至分布式文件存储系统
        String imgUrl = FastdfsUploadFileUtil.uploadImg(multipartFile, fastdfsProperties);
        System.out.println(String.format("文件上传成功，图片返回路径：%s", imgUrl));
        return imgUrl;
    }

    @GetMapping("spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam String productId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrList = productService.spuSaleAttrList(productId);
        return pmsProductSaleAttrList;
    }

    @GetMapping("spuImageList")
    public List<PmsProductImage> spuImageList(@RequestParam String productId) {
        List<PmsProductImage> pmsProductImageList = productService.spuImageList(productId);
        return pmsProductImageList;
    }

}
