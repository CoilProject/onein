package com.miniandroid.myzzung.supoint.util;

/**
 * Created by myZZUNG
 */
public class SystemMain {

    public class Build{
        public static final int V1 = 1;
        public static final int V1_END = 99;
    }

    public class URL{
        public static final String ROOT_URL= "http://52.78.81.136/su_point/"; // AWS URL
        public static final String URL_LOGIN = ROOT_URL+"login";
        public static final String URL_JOIN = ROOT_URL+"add_user";
        public static final String URL_FRIEND_FIND = ROOT_URL+"find_friends";
        public static final String URL_FRIEND_ADD = ROOT_URL+"add_friend";
        public static final String URL_PUSH_TEST = "http://52.78.81.136/test/gcm_test";
        public static final String URL_SEARCH_STORE_ALL = ROOT_URL+"search/search_store_all.php";
        public static final String URL_COUPON_ENROLL = ROOT_URL+"client/coupon_enroll.php";
        public static final String URL_COUPON_SHOW = ROOT_URL+"client/coupon_show.php";
        public static final String URL_COUPON_UPDATE = ROOT_URL+"client/coupon_update.php";
        public static final String URL_COUPON_DELETE = ROOT_URL+"client/coupon_delete.php";
        public static final String URL_SOUND_ENROLL = ROOT_URL+"sound/sound_enroll.php";
        public static final String URL_COUPON_USE = ROOT_URL+"client/coupon_use.php";

    }

    public class SharedPreferences{
        public static final String SHARED_PREFERENCE_AUTOFILE = "auto_login"; // 자동로그인 SharedPreferences
        public static final String SHARED_PREFERENCE_NOTIFICATIONFILE = "notification_check"; // 푸쉬알림을 위한 SharedPreferences
    }

}
