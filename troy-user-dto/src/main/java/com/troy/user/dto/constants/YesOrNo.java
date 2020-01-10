package com.troy.user.dto.constants;

/**
 * description 是与否
 *
 * @author Han
 * @date 2018/10/8 10:08
 */
public enum YesOrNo {

    /**
     * 否
     */
    NO(0),
    /**
     * 是
     */
    YES(1);

    private int code;

    YesOrNo(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * description: 是否为真
     *
     * @param code
     * @return true: code=YesOrNo.YES;false: code=null or code=YesOrNo.NO
     */
    public static boolean isTrue(Integer code) {
        if (code == null) {
            return false;
        }
        if (code.intValue() != YesOrNo.YES.getCode()) {
            return false;
        }
        return true;
    }

    /**
     * description: 是否为假
     *
     * @param code
     * @return true: code=YesOrNo.NO;false: code=null or code=YesOrNo.YES
     */
    public static boolean isFalse(Integer code) {
        if (code == null) {
            return false;
        }
        if (code.intValue() != YesOrNo.NO.getCode()) {
            return false;
        }
        return true;
    }

    public static YesOrNo find(Integer code) {
        if (code == null) {
            return null;
        }
        for (YesOrNo e : YesOrNo.values()) {
            if (e.getCode() == code.intValue()) {
                return e;
            }
        }
        return null;
    }
}
