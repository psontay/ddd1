package com.sontaypham.controller.model.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultMessage<T> implements Serializable {
    static final long serialVersionUID = 1L;
    boolean success;
    String message;
    Integer code;
    long timestamp = System.currentTimeMillis();
    T result;
}
