package com.shopee.entity;

import java.util.Map;
import java.util.Optional;

public class ResponseObject {
    private Boolean success;
    private String message;
    private Object data = null;

    private Map<String,String> validationsErrors;

    public ResponseObject(){}

    public ResponseObject(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseObject(Boolean success,Map<String,String> validationsErrors) {
        this.success = success;
        this.validationsErrors = validationsErrors;
    }

    public ResponseObject(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getValidationsErrors() {
        return validationsErrors;
    }

    public void setValidationsErrors(Map<String, String> validationsErrors) {
        this.validationsErrors = validationsErrors;
    }
}
