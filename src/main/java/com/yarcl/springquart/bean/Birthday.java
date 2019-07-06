package com.yarcl.springquart.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by xiaozhi on 2019/7/6.
 */
@Data
public class Birthday {

    private int id;

    private String name;

    private String birthType; // 1阳历 2农历

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDt;

    private String birthCounts;
}
