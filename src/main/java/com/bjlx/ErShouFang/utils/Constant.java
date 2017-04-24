package com.bjlx.ErShouFang.utils;

/**
 * 常量列表
 * Created by xiaozhi on 2016/10/21.
 */
public class Constant {

    /**
     * 验证码action
     */
    public final static int BIND_TEL_ACTION = 1;

    /**
     * 图片正确
     */
    public final static String IMAGE_NORMAL = "ok";


    /**
     * 全站搜索
     */
    public final static int SEARCH_ALL_OFFSET = 0;
    public final static int SEARCH_ALL_LIMIT = 3;
    public final static int SEARCH_CONDITION_OFFSET = 0;
    public final static int SEARCH_CONDITION_LIMIT = 10;

    /**
     * 收藏类型
     */
    public final static int FAVORITE_HOUSE = 1;

    public static boolean checkFavoriteType(Integer favoriteType) {
        return favoriteType >= FAVORITE_HOUSE && favoriteType <= FAVORITE_HOUSE;
    }

    /**
     * 门票退款方式，1表示退款到平台公共账号，2表示原路返回，3表示不接受退款
     */
    public final static int REFUND_BJLX = 1;
    public final static int REFUND_ORIGIN = 2;
    public final static int REFUND_NOWAY = 3;

}
