package com.troy.user.util;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class ShareCodeUtil {

    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)，数组顺序可进行调整增加反推难度，A用来补位因此此数组不包含A，共31个字符。
     */
    private static final char[] BASE = new char[]{'H', 'V', 'E', '8', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P',
            '5', 'I', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'W', 'Y', 'L', 'T', 'N', '6', 'B', 'G', 'Q'};

    /**
     * A补位字符，不能与自定义重复
     */
    private static final char SUFFIX_CHAR = 'A';

    /**
     * 进制长度
     */
    private static final int BIN_LEN = BASE.length;

    /**
     * 生成邀请码最小长度
     */
    private static final int CODE_LEN = 6;

    /**
     * ID转换为邀请码
     *
     * @param id
     * @return
     */
    public static String idToCode(Long id) {
        char[] buf = new char[BIN_LEN];
        int charPos = BIN_LEN;

        // 当id除以数组长度结果大于0，则进行取模操作，并以取模的值作为数组的坐标获得对应的字符
        while (id / BIN_LEN > 0) {
            int index = (int) (id % BIN_LEN);
            buf[--charPos] = BASE[index];
            id /= BIN_LEN;
        }

        buf[--charPos] = BASE[(int) (id % BIN_LEN)];
        // 将字符数组转化为字符串
        String result = new String(buf, charPos, BIN_LEN - charPos);

        // 长度不足指定长度则随机补全
        int len = result.length();
        if (len < CODE_LEN) {
            StringBuilder sb = new StringBuilder();
            sb.append(SUFFIX_CHAR);
            Random random = new Random();
            // 去除SUFFIX_CHAR本身占位之后需要补齐的位数
            for (int i = 0; i < CODE_LEN - len - 1; i++) {
                sb.append(BASE[random.nextInt(BIN_LEN)]);
            }

            result += sb.toString();
        }

        return result;
    }

    /**
     * 邀请码解析出ID<br/>
     * 基本操作思路恰好与idToCode反向操作。
     *
     * @param code
     * @return
     */
    public static Long codeToId(String code) {
        char[] charArray = code.toCharArray();
        long result = 0L;
        for (int i = 0; i < charArray.length; i++) {
            int index = 0;
            for (int j = 0; j < BIN_LEN; j++) {
                if (charArray[i] == BASE[j]) {
                    index = j;
                    break;
                }
            }

            if (charArray[i] == SUFFIX_CHAR) {
                break;
            }

            if (i > 0) {
                result = result * BIN_LEN + index;
            } else {
                result = index;
            }
        }

        return result;

    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 生成8位随机码
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    public static void main(String[] args) {
        String code = idToCode(118181657806965555L);
        System.out.println(code);
        System.out.println(codeToId(code));

        System.out.println(generateShortUuid());
        String content ="z02ZjzE3";
        String pattern = "^[a-zA-Z0-9]{8}";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }


}
