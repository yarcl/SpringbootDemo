package com.yarcl.springquart.bean;

/**
 * @Author: jiangwenjie
 * @Description:
 * @Data: Created in 14:27 2017/7/25
 */
public class EventTrack {
    private String erId;    //id
    private String eventId; //同event_definition的主键event_id
    private String eventPath;       //xpath路径
    private String activity;
    private String version;
    private String appKey;
    private String productId;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public String getErId() {
        return erId;
    }

    public void setErId(String erId) {
        this.erId = erId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventPath() {
        return eventPath;
    }

    public void setEventPath(String eventPath) {
        this.eventPath = eventPath;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
