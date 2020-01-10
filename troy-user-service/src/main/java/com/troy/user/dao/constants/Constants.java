package com.troy.user.dao.constants;

 import com.troy.commons.enums.BaseEnum;

/**
 * description 定义 dao 模块常量
 *
 * @author Han
 * @date 2018/9/27 10:04
 */
public class Constants {

    public static final String DEFAULT_VALUE_SEPARATOR = ",";

    /**
     * 默认数据模型版本号
     */
    public static final int DEFAULT_DATA_MODEL_VERSION = 0;

    /**
     * 事件
     *
     * @author dp
     */
    public enum EventDestination implements BaseEnum<Integer> {

        /**
         * HEROKUAPP
         */
        HEROKUAPP(1, "herokuapp"),

        /**
         * COMMON_INVITE
         */
        COMMON_INVITE(2, "common_invite"),
        ;

        private int code;
        private String value;

        EventDestination(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }

    /**
     * 事件
     *
     * @author dp
     */
    public enum EventCode implements BaseEnum<Integer> {

        /**
         * PUSH
         */
        PUSH(1, "PUSH"),
        SAVE(2, "SAVE"),
        ;

        private int code;
        private String value;

        EventCode(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }

    /**
     * 事件状态
     *
     * @author dp
     */
    public enum EventStatus implements BaseEnum<Integer> {

        /**
         * SUCCESS
         */
        SUCCESS(1, "SUCCESS"),

        /**
         * FAIL
         */
        FAIL(2, "FAIL"),
        ;

        private int code;
        private String value;

        EventStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public Integer code() {
            return code;
        }

        @Override
        public String desc() {
            return value;
        }
    }
}
