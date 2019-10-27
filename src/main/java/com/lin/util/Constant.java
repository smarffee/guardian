package com.lin.util;

/**
 * Created by Lin on 2019/10/27.
 */
public class Constant {

    /**
     * 监控项状态
     */
    public static class MetricStatus {
        public static final int ENABLE = 1; //启用
        public static final int UN_ENABLE = 0; //关闭
    }

    public static class YesOrNo {
        public static final int YES = 1;
        public static final int NO = 0;
    }

    public static class EmergencyEventStatus {
        public static final int UN_SOLVE = 0; //没有处理
        public static final int SOLEVING = 1; //处理中
        public static final int SOLEVD = 2; //已处理完成
    }

}
