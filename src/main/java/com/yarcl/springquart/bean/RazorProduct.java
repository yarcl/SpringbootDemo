package com.yarcl.springquart.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null){
            return sdf.format(date);
        } else {
            return null;
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(int channelCount) {
        this.channelCount = channelCount;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductPlatform() {
        return productPlatform;
    }

    public void setProductPlatform(String productPlatform) {
        this.productPlatform = productPlatform;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
