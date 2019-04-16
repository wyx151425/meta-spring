package cn.edu.qfnu.meta.util;

/**
 * 响应状态码
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/11/06
 */
public class StatusCode {
    public static final int SUCCESS = 200;
    public static final int STATUS_ERROR = 300;
    public static final int SYSTEM_ERROR = 500;
    public static final int PARAM_ERROR = 600;
    public static final int REQUEST_ILLEGAL = 999;
    public static final int USER_UNREGISTER = 1000;
    public static final int USER_REGISTERED = 1001;
    public static final int USER_DISABLED = 1002;
    public static final int USER_LOGIN_TIMEOUT = 1003;
    public static final int USER_PASSWORD_ERROR = 1004;
}
