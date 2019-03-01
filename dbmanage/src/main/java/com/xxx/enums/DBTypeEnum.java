package com.xxx.enums;

public enum    DBTypeEnum {
    /**玩家数据库需要分库分表*/
    playDb("playDb"),
    /**公共表*/
    global("global")
    ;

    private String type;

    private DBTypeEnum(String type) {
        this.type=type;
    }
}
