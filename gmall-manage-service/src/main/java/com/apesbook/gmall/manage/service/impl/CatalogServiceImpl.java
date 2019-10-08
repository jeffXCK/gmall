package com.apesbook.gmall.manage.service.impl;

import com.apesbook.gmall.bean.PmsBaseCatalog1;
import com.apesbook.gmall.bean.PmsBaseCatalog2;
import com.apesbook.gmall.bean.PmsBaseCatalog3;
import com.apesbook.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.apesbook.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.apesbook.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.apesbook.gmall.service.CatalogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/2
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    private PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    private PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectList(null);
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        QueryWrapper<PmsBaseCatalog2> qw = new QueryWrapper<>();
        qw.eq("catalog1_id", catalog1Id);
        List<PmsBaseCatalog2> pmsBaseCatalog2s = pmsBaseCatalog2Mapper.selectList(qw);
        return pmsBaseCatalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        QueryWrapper<PmsBaseCatalog3> qw = new QueryWrapper<>();
        qw.eq("catalog2_id", catalog2Id);
        List<PmsBaseCatalog3> pmsBaseCatalog3s = pmsBaseCatalog3Mapper.selectList(qw);
        return pmsBaseCatalog3s;
    }
}
