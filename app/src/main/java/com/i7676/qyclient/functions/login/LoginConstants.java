package com.i7676.qyclient.functions.login;

/**
 * Created by Administrator on 2016/9/26.
 */

public final class LoginConstants {
    // 快速注册
    public static int REGISTER_TYPE_QUICK = 0;
    // 手机注册
    public static int REGISTER_TYPE_PHONE = 1;

    // 成功
    public static final int SUCCESS = 0;
    // Token 过期
    public static final int OVERDUE_TOKEN = -1;
    // 失败
    int JUST_FAILED = -2;
    // 用户名格式错误
    int ACCOUNT_FORMAT_ERROR = -3;
    // 手机号格式错误
    int TEL_NUMBER_FORMAT_ERROR = -4;
    // 密码格式错误
    int PASSWORD_FORMAT_ERROR = -5;
    // 用户不存在
    int ACCOUNT_NOT_EXIST = -6;
    // 密码错误
    int INVAILD_PASSWORD = -7;
    // 登录失败
    int LOGIN_PROCESS_FAILED = -8;
    // Nu
    int NU = -9;
    // 禁止注册
    int REJECT_REGISTER = -10;
    // 验证码错误
    int CAPTCHA_ERROR = -11;
    // 用户已存在
    int ACCOUNT_ALREADY_EXIST = -12;
    // 昵称为空
    int NICKNAME_IS_NULL = -13;
}
