package com.lin.util;

import java.util.UUID;

/**
 * Created by Lin on 2019/10/27.
 */
public class Utility {

    public static int getCurrentSecondTimes() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * generate random UUID, eg:d17e0ec1-3dda-47cc-8643-e516afa08a36
     *
     * @return new uuid
     */
    public static String generate36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * generate random UUID, eg:d17e0ec13dda47cc8643e516afa08a36
     *
     * @return new 32 uuid
     * @author xuyi
     */
    public static String generate32UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
