package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    //查询是否为会员
    Member findByTelephone(String telephone);

    //添加会员信息
    void add(Member member);

    //根据月份集合查会员数
    List<Integer> findMemberCountByMonth(List<String> month);
}
