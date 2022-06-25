package com.atguigu.service;

import com.atguigu.pojo.Member;

import java.util.Date;
import java.util.List;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<Date> months);
}