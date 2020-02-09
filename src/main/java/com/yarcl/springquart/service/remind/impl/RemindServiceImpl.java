package com.yarcl.springquart.service.remind.impl;

import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.bean.remind.RemindDo;
import com.yarcl.springquart.bean.remind.RemindVo;
import com.yarcl.springquart.dao.remind.RemindMapper;
import com.yarcl.springquart.service.remind.RemindService;
import com.yarcl.springquart.service.remind.constant.RemindConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Shares on 2020/1/20.
 */
@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    private RemindMapper remindMapper;


    public List<RemindVo> queryRemindList(){
        List<RemindDo> remindDos = remindMapper.queryRemindList();
        List<RemindVo> resultVos = remindDos.stream().map(r -> {
            RemindVo remindVo = this.buildOneVo(r);
            return remindVo;
        }).collect(Collectors.toList());
        return resultVos;
    }

    @Override
    public RemindVo save(RemindAddQo remindAddQo) {
        RemindDo remindDo = new RemindDo(remindAddQo);
        int result = remindMapper.save(remindDo);
        return RemindVo.builder().build();
    }

    @Override
    public int deleteRemind(String remindId) {
        return remindMapper.deleteRemind(remindId);
    }

    private RemindVo buildOneVo(RemindDo remindDo) {
        RemindVo remindVo = RemindVo.builder().build();
        BeanUtils.copyProperties(remindDo, remindVo);
        remindVo.setRemindType(RemindConstant.RemindType.getValueByCode(remindVo.getRemindType()));
        remindVo.setRemindFre(RemindConstant.RemindFre.getValueByCode(remindVo.getRemindFre()));
        remindVo.setPushType(RemindConstant.PushType.getValueByCode(remindVo.getPushType()));
        return remindVo;
    }
}
