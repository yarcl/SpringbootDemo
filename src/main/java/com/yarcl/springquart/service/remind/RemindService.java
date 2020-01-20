package com.yarcl.springquart.service.remind;

import com.yarcl.springquart.bean.remind.RemindAddQo;
import com.yarcl.springquart.bean.remind.RemindVo;

import java.util.List;

/**
 * Created by Shares on 2020/1/20.
 */
public interface RemindService {

    List<RemindVo> queryRemindList();

    RemindVo save(RemindAddQo remindAddQo);
}
