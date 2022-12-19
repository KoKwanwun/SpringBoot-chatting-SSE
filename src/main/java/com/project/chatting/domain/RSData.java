package com.project.chatting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RSData<T> {
    private String resultCode;
    private String msg;
    private T data;
}
