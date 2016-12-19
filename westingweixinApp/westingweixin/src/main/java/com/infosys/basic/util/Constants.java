package com.infosys.basic.util;

public class Constants {
    public final static String PC_SESSION_USER = "insiderProvider";

    public final static String LOGIN = "/inside/login";

    public final static String WEIXIN_SESSION_USER = "user";

    public final static int T_USER_STATUS_DELETE = 0;

    public final static int T_USER_STATUS_NORMAL = 1;

    public final static int T_USER_STATUS_REJECT = 10;

    public final static int T_USER_STATUS_PASS = 11;

    public final static String T_USER_STATUS_NORMAL_STR = "1";

    public final static String T_USER_STATUS_PASS_STR = "11";

    // 新需求 未申领
    public final static int T_SERVICE_ORDER_STATUS_NEW = 0;

    // 已申领待分配
    public final static int T_SERVICE_ORDER_STATUS_APPLY = 1;

    // 分配后 转处理
    public final static int T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING = 2;

    // 处理中 转完成
    public final static int T_SERVICE_ORDER_STATUS_DEALING_DONE = 9;

    // 转废单
    public final static int T_SERVICE_ORDER_STATUS_CANCEL = 10;

    // 已评价
    public final static int T_SERVICE_ORDER_STATUS_DEALING_EVALUATE = 11;

    public final static String T_SERVICE_ORDER_TYPE_OUTSIDE = "0";

    public final static String T_SERVICE_ORDER_TYPE_INSIDE = "1";

    public final static int DEFAULT_DISPLAY_ITEM_NUM = 10;
}
