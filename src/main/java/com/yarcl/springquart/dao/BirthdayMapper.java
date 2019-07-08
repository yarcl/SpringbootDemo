package com.yarcl.springquart.dao;

import com.yarcl.springquart.bean.Birthday;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@MapperScan
public interface BirthdayMapper {

    List<Birthday> getAllBirthdayInfo();

    int queryCount();

    List<Birthday> queryBirthPage(int nowPage, int pageSize);
}
