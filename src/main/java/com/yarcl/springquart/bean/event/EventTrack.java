package com.yarcl.springquart.bean.event;

import lombok.Data;

/**
 * @Author: jiangwenjie
 * @Description:
 * @Data: Created in 14:27 2017/7/25
 */
@Data
public class EventTrack {
    private String erId;    //id
    private String eventId; //同event_definition的主键event_id
    private String eventPath;       //xpath路径
    private String activity;
    private String version;
    private String appKey;
    private String productId;
}
