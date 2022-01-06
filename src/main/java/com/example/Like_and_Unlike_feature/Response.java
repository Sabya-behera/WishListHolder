package com.example.Like_and_Unlike_feature;

public class Response<T> {
    private int code;
    private Object data;
    private String message;

    public Response() {
    }

    public Response(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return null;
    }
}
