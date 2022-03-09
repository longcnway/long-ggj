package me.hgj.jetpackmvvm.network.exception;

/**
 * 自定义异常信息类
 */

public class ApiException extends RuntimeException {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiException(String code_message) {
        super(code_message);
    }

    public ApiException(String code, String msg){
        super(msg);
        this.code = code;
        this.message = msg;
    }
}
