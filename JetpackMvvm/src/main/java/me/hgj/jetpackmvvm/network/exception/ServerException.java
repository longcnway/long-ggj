/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package me.hgj.jetpackmvvm.network.exception;


public class ServerException extends RuntimeException {

    public String code;

    public ServerException(String code, String message) {
        super(message);
        this.code = code;
    }


}
