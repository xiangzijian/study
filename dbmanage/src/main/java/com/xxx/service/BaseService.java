package com.xxx.service;

import com.xxx.BaseContext;
import com.xxx.dao.BaseDao;
import com.xxx.entity.HashDate;
import com.xxx.enums.DBTypeEnum;
import com.xxx.jedis.JedisUtil;
import com.xxx.util.CommUtil;
import com.xxx.util.DaoUtil;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseService<C> {

    public final static int TWO_DAY = 60 * 60 * 24 * 30;

    public abstract BaseContext getContext();

    public C get(Object uid) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database = getDaoUtil().dbHandle(dbTypeEnum, uid);
        Object po = getContext().getRedisInstance().hmget(key + uid, (Class<C>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        if (po != null) {
            return (C) po;
        }
        po = getDao().selectByPrimaryKey(database, (Long) uid);
        if (po != null) {
            Map map = CommUtil.objectToMap(po);
            getContext().getRedisInstance().hmset(key + uid, map, TWO_DAY);
            return (C) po;
        }
        return (C) po;
    }

    /***/
    public C getByOpenId(String openId, boolean useCache) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database = getDaoUtil().dbHandle(dbTypeEnum, openId);
        Object po = null;
        if (useCache) {
            po = getContext().getRedisInstance().hmget(key + openId, (Class<C>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            if (po != null) {
                return (C) po;
            }
        }
        po = getDao().selectByOpenid(database, String.valueOf(openId));
        if (useCache) {
            if (po != null) {
                Map map = CommUtil.objectToMap(po);
                getContext().getRedisInstance().hmset(key + openId, map, TWO_DAY);
                return (C) po;
            }
        }
        return (C) po;
    }


    public <T extends HashDate> List<T> getList(Long uid) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database = getDaoUtil().dbHandle(dbTypeEnum, uid);
        List<T> pos = getContext().getRedisInstance().hmgetList(key + uid, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], true);
        if (pos != null && pos.size() > 0) {
            return pos;
        }
        pos = (List<T>) getDao().selectByPrimaryKey(database, uid);
        if (pos != null && pos.size() > 0) {
            getContext().getRedisInstance().hmsetList(key + uid, pos, TWO_DAY);
            return pos;
        }
        if(pos==null){
            return new ArrayList<>();
        }
        return pos;
    }

    public int deleteByPrimaryKey(long uid) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database = getDaoUtil().dbHandle(dbTypeEnum, uid);
        getDao().deleteByPrimaryKey(database, uid);
        return (int) getContext().getRedisInstance().del(key + uid);
    }

    public int insert(Object uid, C c) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database = getDaoUtil().dbHandle(dbTypeEnum, uid);
        int i = getDao().insert(database, c);
        getContext().getRedisInstance().hmset(key + String.valueOf(uid), CommUtil.objectToMap(c), TWO_DAY);
        return i;
    }

    public int updateByPrimaryKey(Object uid, Object po) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        getDaoUtil().dbHandle(dbTypeEnum, uid);
        String asyncKey = getAsyncKey();
        getContext().getRedisInstance().hmset(key + uid, CommUtil.objectToMap(po), TWO_DAY);
        getContext().getRedisInstance().sadd(asyncKey, uid + "");
        return 1;
    }

    public int updateListByPrimaryKey(Object uid, List pos) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        getDaoUtil().dbHandle(dbTypeEnum, uid);
        String asyncKey = getAsyncKey();
        getContext().getRedisInstance().hmsetList(key + uid, pos, TWO_DAY);
        getJedis().sadd(asyncKey, uid + "");
        return 1;
    }

    public int deleteListByPrimaryKey(long uid,String[] ids) {
        String key = getKey();
        DBTypeEnum dbTypeEnum = getDBTypeEnum();
        String database=getDaoUtil().dbHandle(dbTypeEnum, uid);
        getJedis().hdel(key+uid,ids);
        getDao().deleteListByPrimaryKey(database,uid,ids);
        return (int) getContext().getRedisInstance().del(key + uid);
    }



    protected abstract String getKey();

    protected abstract DBTypeEnum getDBTypeEnum();

    public abstract BaseDao getDao();

    public abstract DaoUtil getDaoUtil();

    public abstract String getAsyncKey();

    public abstract JedisUtil getJedis();

}
