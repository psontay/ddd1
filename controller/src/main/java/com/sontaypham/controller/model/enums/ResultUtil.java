package com.sontaypham.controller.model.enums;


import com.sontaypham.controller.model.vo.ResultMessage;

public class ResultUtil<T> {
    private final ResultMessage<T> responseMessage;
    private static final Integer SUCCESS_CODE = 200;

    public ResultUtil() {
        responseMessage = new ResultMessage<>();
        responseMessage.setCode(SUCCESS_CODE);
        responseMessage.setSuccess(true);
        responseMessage.setMessage("Success");
    }

    public ResultMessage<T> setData(T data) {
        this.responseMessage.setResult(data);
        return this.responseMessage;
    }

    public ResultMessage<T> setSuccessMessage(ResultCode resultCode) {
        this.responseMessage.setSuccess(true);
        this.responseMessage.setMessage(resultCode.toString());
        this.responseMessage.setCode(resultCode.code());
        return this.responseMessage;
    }

    public ResultMessage<T> setErrorMessage(ResultCode resultCode) {
        this.responseMessage.setSuccess(false);
        this.responseMessage.setMessage(resultCode.toString());
        this.responseMessage.setCode(resultCode.code());
        return this.responseMessage;
    }

    public ResultMessage<T> setErrorMessage( Integer code, String message) {
        this.responseMessage.setSuccess(false);
        this.responseMessage.setMessage(message);
        this.responseMessage.setCode(code);
        return this.responseMessage;
    }

    public static <T> ResultMessage<T> data(T data) {
        return new ResultUtil<T>().setData(data);
    }
    public static <T> ResultMessage<T> success(ResultCode resultCode) {
        return new ResultUtil<T>().setSuccessMessage(resultCode);
    }
    public static <T> ResultMessage<T> success() {
        return new ResultUtil<T>().setSuccessMessage(ResultCode.SUCCESS);
    }
    public static <T> ResultMessage<T> error( ResultCode resultCode) {
        return new ResultUtil<T>().setErrorMessage(resultCode);
    }
    public static <T> ResultMessage<T> error(Integer code, String message) {
        return new ResultUtil<T>().setErrorMessage(code, message);
    }

}
