package com.tyrael.laundry.commons.dto;

/**
 * 
 * @author Mark Martinez, Nov 19, 2015
 *
 */
public class OperationResult<T> {

    private String message;
    private String status;
    T data;

    public OperationResult() {
    }

    public OperationResult(T data) {
        this.data = data;
    }

    public OperationResult<T> withMessage(String message) {
        this.message = message;
        return this;
    }
    public OperationResult<T> withStatus(String status) {
        this.status = status;
        return this;
    }
    public OperationResult<T> withData(T data) {
        this.data = data;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

}
