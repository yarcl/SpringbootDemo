package com.yarcl.springquart.service;

import com.yarcl.springquart.bean.Birthday;
import com.yarcl.springquart.dao.BirthdayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@Service
public class BirthdayServiceImpl implements BirthdayService{

    @Autowired
    private BirthdayMapper birthdayMapper;

    @Override
    public List<Birthday> getAllBirthdayInfo() {
        return birthdayMapper.getAllBirthdayInfo();
    }

    @Override
    public List<Birthday> queryBirthdayInfoByPage(int nowPage, int pageSize) {
        int start = (nowPage-1) * pageSize + 1;
        int end = nowPage * pageSize;
        return birthdayMapper.queryBirthPage(start, end);
    }

    public int queryCount() {
        return birthdayMapper.queryCount();
    }
}
