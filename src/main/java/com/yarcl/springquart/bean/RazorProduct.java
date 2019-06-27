package com.yarcl.springquart.bean;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
@Data
public class RazorProduct {
    private int id;
    private String name;
    private String description;
    private Date date;
    private String userId;
    private int channelCount;
    private String productKey;
    private String productPlatform;
    private String category;
    private int active;
    private String packageName;

    public RazorProduct() {
    }

    public RazorProduct(int id, String name, String description, Date date, String userId, int channelCount, String productKey, String productPlatform, String category, int active, String packageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.userId = userId;
        this.channelCount = channelCount;
        this.productKey = productKey;
        this.productPlatform = productPlatform;
        this.category = category;
        this.active = active;
        this.packageName = packageName;
    }
}
