package com.xxx.dao;

public interface BaseDao {

    int insert(String database, Object obj);

    Object selectByPrimaryKey(String database, Long id);

    Object selectByOpenid(String database, String openid);

    int deleteByPrimaryKey(String database, Long id);

    int updateByPrimaryKey(String database, Object obj);

    int deleteListByPrimaryKey(String database, Object uid, String[] ids);

}
