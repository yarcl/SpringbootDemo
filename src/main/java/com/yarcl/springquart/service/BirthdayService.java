package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.Birthday;

import java.util.List;

/**
 * Created by xiaozhi on 2019/7/6.
 */
public interface BirthdayService {

    List<Birthday> getAllBirthdayInfo();

    List<Birthday> queryBirthdayInfoByPage(int nowPage, int pageSize);

    int queryCount();

}
