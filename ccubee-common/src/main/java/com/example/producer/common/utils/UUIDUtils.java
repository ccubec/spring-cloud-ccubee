package com.example.producer.common.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * @Title: getUuid
     * @Description: 获得36位原生UUID
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * @Title: getUuid32
     * @Description: 获得32位UUID
     */
    public static String getUuid32() {
        String uuidStr = getUuid().replaceAll("-", "");
        return uuidStr;
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag
     *            是否是数字
     * @param length
     *            随机字符串长度
     */
    public static String getRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
}
