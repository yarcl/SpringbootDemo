package com.yarcl.springquart.bean.remind;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Shares on 2020/1/20.
 */
@Data
public class RemindDo {

    private Long id;

    private String remindTitle;

    private String remindFre;

    private String remindType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date remindDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;

    private String remindCount;

    private String isDelete;

    // TODO 目前只支持一对一的推送
    private String pushType;

    // TODO 目前只支持一对一的推送
    private String pushPath;

    public RemindDo() {
    }

    public RemindDo(RemindAddQo remindAddQo) {

        this.remindTitle = remindAddQo.getRemindTitle();
        this.remindFre = remindAddQo.getRemindFre();
        this.remindDate = remindAddQo.getRemindDate();
        this.remindType = remindAddQo.getRemindType();
        this.pushPath = remindAddQo.getPushPath();
        this.pushType  = remindAddQo.getPushType();
        this.updateDate = new Date();
        this.createDate = new Date();
        this.remindCount = "0";
        this.isDelete = "0";

    }

}
