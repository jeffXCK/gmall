package com.apesbook.gmall.user.service.impl;

import com.apesbook.gmall.bean.UmsMember;
import com.apesbook.gmall.service.MemberService;
import com.apesbook.gmall.user.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;


/**
 * Description:
 * Author:XCK
 * Date:2019/9/29
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, UmsMember> implements MemberService {


}
