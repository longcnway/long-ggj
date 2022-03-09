package me.hgj.jetpackmvvm.network.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import me.hgj.jetpackmvvm.network.AppException;
import retrofit2.HttpException;

/**
 * Author:jianbo
 * <p>
 * Create Time:2019/4/16 9:39
 * <p>
 * Email:1245092675@qq.com
 * <p>
 * Describe:Retrofit错误码管理提示
 */

public class RetrofitException {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponseThrowable retrofitException(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "服务器异常";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            // 服务器下发的错误
            ServerException resultException = (ServerException) e;
            ex = new ResponseThrowable(resultException, resultException.code);
            ex.message = resultException.getMessage();
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ApiException) {
            //服务端head返回的错误信息处理
            ex = new ResponseThrowable(e, ((ApiException) e).getCode());
            ex.message = e.getMessage();
            return ex;
        } else if (e instanceof AppException) {
            ex = new ResponseThrowable(e, ((AppException) e).getErrCode());
            ex.message = e.getMessage();
            return ex;
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }


    /**
     * 约定异常
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final String UNKNOWN = "1000";
        /**
         * 解析错误
         */
        public static final String PARSE_ERROR = "1001";
        /**
         * 网络错误
         */
        public static final String NETWORD_ERROR = "1002";
        /**
         * 协议出错
         */
        public static final String HTTP_ERROR = "1003";
        /**
         * 证书出错
         */
        public static final String SSL_ERROR = "1005";

        /**
         * 必填参数校验不通过
         */
        public static final String ARG_CHECK = "000001";

        /**
         * 未知错误
         */
        public static final String UNK_ERROR = "000007";


    }

    public static class ResponseThrowable extends Exception {
        public String code;
        public String message;

        public ResponseThrowable(Throwable throwable, String code) {
            super(throwable);
            this.code = code;
        }
    }

}
