package com.valid;

public class NotFoundException extends Exception {

    private String msg;

    public NotFoundException(String msg){
        super(msg);
    }

    public String getMsg() {
        return msg;
    }
}
