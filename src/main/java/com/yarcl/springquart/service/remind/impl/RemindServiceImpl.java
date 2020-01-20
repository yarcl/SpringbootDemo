package com.yarcl.springquart.service.remind.impl;

import com.yarcl.springquart.bean.remind.RemindVo;
import com.yarcl.springquart.dao.remind.RemindMapper;
import com.yarcl.springquart.service.remind.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shares on 2020/1/20.
 */
@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    private RemindMapper remindMapper;


    public List<RemindVo> queryRemindList(){
        remindMapper.toString();

        return new ArrayList<>();
    }

}
