package com.literaturagutendex.literaturagutendex.service;

public class ApiResponse {
    private boolean success;
    private String data;
    private String errorMessage;

    public ApiResponse(boolean success, String data, String errorMessage){
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
