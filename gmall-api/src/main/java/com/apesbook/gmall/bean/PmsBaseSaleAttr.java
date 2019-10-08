package com.apesbook.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @param
 * @return
 */
@TableName("pms_base_sale_attr")
public class PmsBaseSaleAttr implements Serializable {

    @TableId
    private String id ;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}