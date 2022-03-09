package me.hgj.jetpackmvvm.network.exception;

/**
 * Author:jianbo
 * <p>
 * Create Time:2019/5/16 20:55
 * <p>
 * Email:1245092675@qq.com
 * <p>
 * Describe:与后台协定Head请求的返回code值
 */
public class HttpStatusCode {
    /**
     * 请求并操作成功"000"
     */
    public static final String CodeSuccess="000";
    /**
     * 未登录
     */
    public static final String CODE_NOT_LOGON="001";
    /**
     * 处理中
     */
    public static final String CODE_PROCESS="003";
    /**
     * 错误
     */
    public static final String CODE_ERROR="500";
    /**
     * 失败
     */
    public static final String CODE_FALL="501";
    /**
     * 数据非法
     */
    public static final String CODE_DATE_ILLEGALITY="502";
    /**
     * 没有受影响的行数
     */
    public static final String CODE_ROW_INFLUENCE="503";
    /**
     * 系统繁忙
     */
    public static final String CODE_SYSTEM_BUSY="999";
}
