package com.springstudy.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Car {

    private Long id;

    private String name;

    private String kind;
}