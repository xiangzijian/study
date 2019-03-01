package com.xxx.service;

import com.alibaba.fastjson.JSONObject;

import com.xxx.annotations.RedisData;
import com.xxx.entity.HashDate;
import com.xxx.jedis.JedisUtil;
import com.xxx.util.CommUtil;
import redis.clients.jedis.Tuple;

import java.util.*;

public abstract class RedisService {

    public abstract JedisUtil getJedisUtil();

    /**
     * 添加SortSet型数据
     *
     * @param key
     * @param value
     */
    public void zadd(String key, String value, double score) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.zadd(key, value, score);
    }

    /**
     * 根据score的最大值和最小值来取得数据
     */
    public Set<Tuple> zrangeByScoreWithScores(String key, long min, long max) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.zrangeByScoreWithScores(key, min, max);
    }

    /**通过排名返回成员*/
    public Set<Tuple> zrevrangeWithScores(String key,long start,long end){
        JedisUtil jedisUtil = getJedisUtil();
        Set<Tuple> set=jedisUtil.zrevrangeWithScores(key,start,end);
        return set;
    }

    /**
     * 移除有序集合中给定的排名区间的所有成员
     * */
    public Long ZREMRANGEBYRANK(String key,long start,long end){
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.ZREMRANGEBYRANK(key,start,end);
    }

    /**
     * 按照排名返回成员
     * */
    public Set<String> zrevrange(String key,long start,long end){
        JedisUtil jedisUtil = getJedisUtil();
        Set<String> set=jedisUtil.zrevrange(key,start,end);
        return set;
    }


    /**
     * 将对象保存到hash里
     *
     * @param
     * @param
     * @return
     */
    public void hmset(String key, Map map, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.hmset(key, map);
        jedisUtil.expire(key, seconds);
    }

    /**
     * 将对象的一个字段放入hash中
     */
    public void hmsetField(String key, String field, String fieldValue, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        Map<String, String> map = jedisUtil.hmget(key);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(field, fieldValue);
        jedisUtil.hmset(key, map);
        jedisUtil.expire(key, seconds);
    }
    /**
     * 批量删除SortSet型数据
     *
     * @param key
     * @param value
     */
    public long zrem(String key, String ... value) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.zrem(key, value);
    }

    /**
     * 获取倒序的SortSet型的数据
     *
     * @param key
     * @return
     */
    public Set<String> getDescSortSet(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.zrevrange(key, 0, -1);
    }

    /**
     * 删除SortSet型数据
     *
     * @param key
     * @param value
     */
    public void deleteSortSet(String key, String value) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.zrem(key, value);
    }

    /**
     * 删除缓存数据
     *
     * @param key
     */
    public long del(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.del(key);
    }

    /**
     * 批量删除SortSet型数据
     *
     * @param key
     * @param value
     */
    public void deleteSortSetBatch(String key, String[] value) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.zrem(key, value);
    }

    /**
     * 范围获取倒序的SortSet型的数据
     *
     * @param key
     * @return
     */
    public Set<String> getDescSortSetPage(String key, int start, int end) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.zrevrange(key, start, end);
    }

    /**
     * 获取SortSet型的总数量
     *
     * @param key
     * @return
     */
    public long zcard(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.zcard(key);
    }

    /**
     * 通过key得到数据
     */
    public String get(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.get(key);
    }


    /**
     * 设置失效时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.expire(key, seconds);
    }

    /**
     * 检查KEY是否存在
     *
     * @param key
     * @return
     */
    public boolean checkExistsKey(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.exists(key);
    }

    /**
     * 重命名KEY
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    public String renameKey(String oldKey, String newKey) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.rename(oldKey, newKey);
    }

    /**
     * 得到存入的hash对象
     *
     * @param key
     * @return
     */
    public <T> T hmget(String key, Class<T> clazz) {
        JedisUtil jedisUtil = getJedisUtil();
        Map map = jedisUtil.hmget(key);
        /**如果字段数量更新了则将缓存清空*/
        if (clazz.getAnnotation(RedisData.class) != null) {
            if (map.size() != clazz.getAnnotation(RedisData.class).fileCount()) {
                getJedisUtil().expire(key, 0);
                return null;
            }
        }
        return CommUtil.mapToObject(map, clazz);
    }

    /**
     * 得到list列表对象
     */
    public <T> List<T> hmgetList(String key, Class<T> clazz, boolean checkUpdate) {
        JedisUtil jedisUtil = getJedisUtil();
        Map<String, String> map = jedisUtil.hmget(key);
        List<T> list = new ArrayList<>();
        if (map == null || map.size() < 0) {
            return null;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String str = entry.getValue();
            if (checkUpdate) {
                HashMap field = JSONObject.parseObject(str, HashMap.class);
                /**如果字段数量更新了则将缓存清空*/
                if (clazz.getAnnotation(RedisData.class) != null) {
                    if (field.size() != clazz.getAnnotation(RedisData.class).fileCount()) {
                        getJedisUtil().expire(key, 0);
                        return null;
                    }
                }
            }
            list.add(JSONObject.parseObject(str, clazz));
        }
        return list;
    }

    /**
     * 存入list列表
     */
    public <T extends HashDate> void hmsetList(String key, List<T> list, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        Map<String, String> map = new HashMap<>();
        int i = 0;
        for (T obj : list) {
            map.put(obj.id()+"", JSONObject.toJSONString(obj));
            i++;
        }
        jedisUtil.hmset(key, map);
        jedisUtil.expire(key, seconds);
    }


    /**删除一个或多个哈希表字段*/

    public Long hdel(String key, String... field){
        JedisUtil jedisUtil = getJedisUtil();
        Long l=jedisUtil.hdel(key,field);
        return l;
    }


    public String spop(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.spop(key);
    }

    /**
     * 获取集合的成员数
     */
    public Long scard(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.scard(key);
    }

    /**
     * 删除KEY
     *
     * @param key
     */
    public void deleteKey(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.del(key);
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param seconds 失效时间，秒
     */
    public void setExpireTime(String key, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.expire(key, seconds);
    }

    /**
     * 删除失效时间
     *
     * @param key
     */
    public void deleteExpireTime(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.persist(key);
    }

    /**
     * 将值存入set结构里
     */
    public void sadd(String key, String... values) {
        JedisUtil jedisUtil = getJedisUtil();
        jedisUtil.sadd(key, values);
    }

    /**
     * 对一个key里面的值进行原子的加一
     *
     * @param key
     */
    public long incr(String key) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.incr(key);
    }

    /**
     * 通过key存入值
     */
    public String set(String key, String value, int seconds) {
        JedisUtil jedisUtil = getJedisUtil();
        String str = jedisUtil.set(key, value, seconds);
        return str;
    }

    /**
     * 移除集合里面的一个或多个成员
     */
    public long srem(String key, String... members) {
        JedisUtil jedisUtil = getJedisUtil();
        return jedisUtil.srem(key, members);
    }

}
