package com.yarcl.springquart.bean.remind;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Shares on 2020/1/20.
 */
@Data
public class RemindAddQo {

    @ApiModelProperty(value = "提醒title")
    private String remindTitle;

    /**
     * 提醒的频率
     */
    @ApiModelProperty(value = "提醒频次")
    private String remindFre;

    /**
     * 生日 1
     * 账单 2
     */
    @ApiModelProperty(value = "提醒类别")
    private String remindType;

    /**
     * 提醒的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date remindDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "提醒次数")
    private String remindCount;

    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    // TODO 目前只支持一对一的推送
    @ApiModelProperty(value = "推送类型")
    private String pushType;

    // TODO 目前只支持一对一的推送
    @ApiModelProperty(value = "推送地址")
    private String pushPath;

}
