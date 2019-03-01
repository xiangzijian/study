package com.xxx.util;


import com.xxx.enums.DBTypeEnum;

public abstract class DaoUtil {


    /**数据源相关的具体实现*/
    public abstract String dbHandle(DBTypeEnum dbTypeEnum, Object id);


}
