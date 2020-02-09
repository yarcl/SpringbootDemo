package com.yarcl.springquart.dao.remind;

import com.yarcl.springquart.bean.remind.RemindDo;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by Shares on 2020/1/20.
 */
@MapperScan
public interface RemindMapper {

    /**
     * 查询所有的提醒事项
     * @return
     */
    List<RemindDo> queryRemindList();

    /**
     * 保存提醒数据
     * @param remindDo
     * @return
     */
    int save(RemindDo remindDo);

    /**
     * 删除提醒内容
     * @param remindId
     * @return
     */
    int deleteRemind(String remindId);
}
