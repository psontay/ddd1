package com.sontaypham.controller.utils;

import com.sontaypham.controller.model.vo.ResultMessage;

public class ResultUtils<T> {
    private final ResultMessage<T> resultMessage;
    private static final Integer SUCCESS_CODE = 200;
    public ResultUtils() {
        resultMessage = new ResultMessage<>();
        resultMessage.setCode(SUCCESS_CODE);
        resultMessage.setMessage("Success");
        resultMessage.setSuccess(true);
    }
    public ResultMessage<T> setData( T result ) {
        this.resultMessage.setResult(result);
        return this.resultMessage;
    }

}
