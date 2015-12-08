package com.tyrael.laundry.authentication;

public class ErrorResponseDto {

    private String error;

    public ErrorResponseDto(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
