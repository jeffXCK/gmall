package com.apesbook.gmall.user.controller;

import com.apesbook.gmall.bean.UmsMember;
import com.apesbook.gmall.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Author:XCK
 * Date:2019/9/29
 */
@RequestMapping("user")
@RestController
public class MemberController {

    @Reference
    private MemberService memberService;

    @GetMapping("list")
    public List<UmsMember> list(){
        List<UmsMember> umsMemberList = memberService.list();
        return umsMemberList;
    }

    @GetMapping("info/{memberId}")
    public UmsMember info(@PathVariable("memberId") String memberId){
        UmsMember umsMember = memberService.getById(memberId);
        return umsMember;
    }


    @PostMapping("save")
    public String save(@RequestBody UmsMember umsMember){
        memberService.save(umsMember);
        return "success";
    }

    @PostMapping("update")
    public String update(@RequestBody UmsMember umsMember){
        memberService.updateById(umsMember);
        return "success";
    }

    @PostMapping("delete/{memberId}")
    public String delete(@PathVariable("memberId") String memberId){
        memberService.removeById(memberId);
        return "success";
    }


}
