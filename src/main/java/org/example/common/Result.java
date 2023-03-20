package org.example.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Object data;//数据
    private String result;//操作结果
    private int code;
    public Result(Object data,String result,int code){
        this.data = data;
        this.result = result;
        this.code = code;
    }
}
