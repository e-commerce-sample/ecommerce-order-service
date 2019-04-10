package com.ecommerce.order.common.exception;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;


public enum ErrorCode {
    ACCOUNT_CREATOR_CANNOT_BE_DELETED(CONFLICT, "不能删除账户创建者"),
    ACCOUNT_NOT_FOUND_BY_CREATOR(NOT_FOUND, "没有找到用户所创建的账户"),
    REQUESTED_ROLE_TOO_LARGE(FORBIDDEN, "当前角色无法为用户设置更高角色"),
    USER_MANAGEMENT_NOT_ENOUGH_PERMISSION(FORBIDDEN, "无权管理用户"),
    NO_ENABLED_ACCOUNT_OWNER_LEFT(CONFLICT, "账户下至少应该有一位处于启用状态的账户所有者"),
    USER_NOT_BELONG_TO_ACCOUNT(FORBIDDEN, "用户不属于账户"),
    WECHAT_ALRERDY_BIND_TO_OTHER_ACCOUNT(CONFLICT, "微信用户已经绑定到了其他账户"),
    WECHAT_ALRERDY_BIND_TO_FORM_USER(CONFLICT, "微信用户已经绑定到了表单用户"),
    WECHAT_ALREADY_BIND_TO_CONSOLE_USER(CONFLICT, "微信用户已经绑定到了账户管理员"),
    USER_ALREADY_BIND_TO_WECHAT(CONFLICT, "用户已经绑定了其他微信号"),
    USER_NOT_BIND_TO_WECHAT(CONFLICT, "用户尚未绑定微信号"),
    INVALID_WECHAT_OPEN_ID(INTERNAL_SERVER_ERROR, "微信OpenID格式错误"),
    INVALID_WECHAT_UNION_ID(INTERNAL_SERVER_ERROR, "微信UnionID格式错误"),
    WECHAT_EVENT_CREATION_FAILED(INTERNAL_SERVER_ERROR, "无法创建微信推送事件"),
    WECHAT_QR_CODE_NOT_FOUND_BY_SCENE_STRING(NOT_FOUND, "微信二维码场景值不存在"),
    INVALIDWECHAT_QR_CODE_ID(INTERNAL_SERVER_ERROR, "微信二维码ID格式错误，ID必须包含32位字符"),
    USER_ALREADY_EXIST_BY_MOBILE_NUMBER(CONFLICT, "手机号对应用户已经存在"),
    WECHAT_ALREADY_BIND(CONFLICT, "微信号已经绑定其他用户"),
    TERM_NOT_AGREED(CONFLICT, "未同意用户条款"),
    KAPTCHA_EXPIRED(CONFLICT, "图片验证码已过期，有效期为5分钟"),
    KAPTCHA_VALIDATION_FAILED(CONFLICT, "图片验证码错误"),
    KAPTCHA_CODE_NOT_FOUND(NO_CONTENT, "图片验证码不存在"),
    AUTHENTICATION_FAILED(UNAUTHORIZED, "认证失败"),
    ACCESS_DENIED(FORBIDDEN, "无权访问"),
    USER_NOT_FOUND_BY_WECHAT_OPEN_ID(NOT_FOUND, "微信OpenID对应用户不存在"),
    USER_NOT_FOUND_BY_MOBILE_NUMBER(NOT_FOUND, "手机号码对应用户不存在"),
    SMS_VERIFICATION_CODE_EXPIRED(CONFLICT, "短信验证码已过期，有效期为5分钟"),
    MAX_SMS_VERIFICATION_CODE_CHECK(CONFLICT, "短信验证码超过限定使用次数，最多使用3次"),
    SMS_VERIFICATION_CODE_NOT_FOUND(NOT_FOUND, "短信验证码不存在"),
    SMS_VERIFICATION_CODE_VALIDATION_FAILED(CONFLICT, "短信验证码错误"),
    SMS_VERIFICATION_CODE_RETRIEVED_TOO_OFTEN(CONFLICT, "短信验证码获取过于频繁，60秒内只能获取一次验证码"),
    MAX_SMS_VERIFICATION_CODE_REACHED_FOR_TODAY(CONFLICT, "短信验证码已达当天最大获取次数，同一手机号每天最多获取10次验证码"),
    SYSTEM_ERROR(INTERNAL_SERVER_ERROR, "系统错误"),
    USER_NOT_FOUND(NOT_FOUND, "用户不存在"),
    ACCOUNT_NOT_FOUND(NOT_FOUND, "账户不存在"),
    INVALID_USER_ID(INTERNAL_SERVER_ERROR, "用户ID格式错误，ID必须包含32位字符"),
    INVALID_ACCOUNT_ID(INTERNAL_SERVER_ERROR, "账户ID格式错误，ID必须包含32位字符"),
    REQUEST_VALIDATION_FAILED(BAD_REQUEST, "请求数据格式验证失败"),
    INVALID_MOBILE_NUMBER(INTERNAL_SERVER_ERROR, "手机号码格式错误");
    private HttpStatus status;
    private String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
