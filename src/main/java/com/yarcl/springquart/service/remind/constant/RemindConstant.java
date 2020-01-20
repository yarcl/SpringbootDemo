package com.yarcl.springquart.service.remind.constant;

import java.util.Objects;

/**
 * Created by Shares on 2020/1/20.
 */
public interface RemindConstant {

    enum RemindType {

        BIRTHDAY("1","生日"),
        BILL("2", "账单");

        public String code;
        public String value;

        RemindType(String code, String value) {
            this.value = value;
            this.code = code;
        }

        public static String getValueByCode(String code) {
            for (RemindType bs : RemindType.values()) {
                if (Objects.equals(bs.code, code)) return bs.value;
            }
            return null;
        }

        public static String getCodeByValue(String value) {
            for (RemindType bs : RemindType.values()) {
                if (Objects.equals(bs.value, value)) return bs.code;
            }
            return null;
        }

    }

    enum RemindFre {
        YEAR("1", "每年"),
        MONTH("2","每月"),
        WEEK("3", "每周"),
        DAY("4", "每日");

        public String code;
        public String value;

        RemindFre(String code, String value) {
            this.value = value;
            this.code = code;
        }

        public static String getValueByCode(String code) {
            for (RemindFre bs : RemindFre.values()) {
                if (Objects.equals(bs.code, code)) return bs.value;
            }
            return null;
        }

        public static String getCodeByValue(String value) {
            for (RemindFre bs : RemindFre.values()) {
                if (Objects.equals(bs.value, value)) return bs.code;
            }
            return null;
        }
    }

    enum PushType {

        EMAIL("1", "邮件"),
        MESSAGE("2", "短信");

        public String value;

        public String code;

        PushType(String code, String value) {
            this.value = value;
            this.code = code;
        }

        public static String getCodeByValue(String value) {
            for (PushType bs : PushType.values()) {
                if (Objects.equals(bs.value, value)) return bs.code;
            }
            return null;
        }

        public static String getValueByCode(String code) {
            for (PushType bs : PushType.values()) {
                if (Objects.equals(bs.code, code)) return bs.value;
            }
            return null;
        }

    }

}
