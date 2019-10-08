package com.apesbook.gmall.user.controller;

import com.apesbook.gmall.bean.UmsMemberReceiveAddress;
import com.apesbook.gmall.service.MemberReceiveAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/9/30
 */
@RequestMapping("memberReceiveAddress")
@RestController
public class MemberReceiveAddressController {


    @Reference
    private MemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("list")
    public List<UmsMemberReceiveAddress> list(){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = memberReceiveAddressService.list();
        return umsMemberReceiveAddressList;
    }

    @GetMapping("info/{id}")
    public UmsMemberReceiveAddress info(@PathVariable("id") String id){
        UmsMemberReceiveAddress UmsMemberReceiveAddress = memberReceiveAddressService.getById(id);
        return UmsMemberReceiveAddress;
    }


    @PostMapping("save")
    public String save(@RequestBody UmsMemberReceiveAddress UmsMemberReceiveAddress){
        memberReceiveAddressService.save(UmsMemberReceiveAddress);
        return "success";
    }

    @PostMapping("update")
    public String update(@RequestBody UmsMemberReceiveAddress UmsMemberReceiveAddress){
        memberReceiveAddressService.updateById(UmsMemberReceiveAddress);
        return "success";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") String id){
        memberReceiveAddressService.removeById(id);
        return "success";
    }

    @GetMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){
        QueryWrapper<UmsMemberReceiveAddress> qw = new QueryWrapper<>();
        qw.eq("member_id", memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList = memberReceiveAddressService.list(qw);
        return umsMemberReceiveAddressList;
    }


}
