package com.atguigu.dao;

import com.atguigu.pojo.Member;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MemberDao {
    Member getMemberByTelephone(String telephone);

    void add(Member member);

    int findMemberCountByMonth(Date month);

    int getTodayNewMember(String date);
    int getTotalMember();
    int getThisWeekAndMonthNewMember(String date);

}
