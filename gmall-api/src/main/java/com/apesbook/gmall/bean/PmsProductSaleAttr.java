package com.apesbook.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

@TableName("pms_product_sale_attr")
public class PmsProductSaleAttr implements Serializable {

    @TableId
    private String id;

    private String productId;

    private String saleAttrId;

    private String saleAttrName;

    @TableField(exist = false)
    private List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public void setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    public String getSaleAttrName() {
        return saleAttrName;
    }

    public void setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName;
    }

    public List<PmsProductSaleAttrValue> getPmsProductSaleAttrValueList() {
        return pmsProductSaleAttrValueList;
    }

    public void setPmsProductSaleAttrValueList(List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList) {
        this.pmsProductSaleAttrValueList = pmsProductSaleAttrValueList;
    }
}
