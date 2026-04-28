package com.example.simplememo_2;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String msg;
    private T data;
}
