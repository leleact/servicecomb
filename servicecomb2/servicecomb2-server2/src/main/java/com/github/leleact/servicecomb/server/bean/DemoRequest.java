package com.github.leleact.servicecomb.server.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class DemoRequest {

    // private String name;

    private Integer age;

    //@NotNull
    private String ex;
}
