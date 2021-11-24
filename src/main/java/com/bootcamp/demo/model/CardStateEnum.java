package com.bootcamp.demo.model;

public enum CardStateEnum {
    NONE(0),
    PREFERRED(1);

    int state;
    CardStateEnum(Integer state){
        this.state = state;
    }
}
