package com.xxx.jedis;

import com.xxx.util.PropertyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public abstract class JedisUtil {
    private  JedisPool jedisPool = null;
    protected abstract String getProperties();
    protected  int getSelect(){
        return 1;
    }
     {
        Properties properties = PropertyUtil.loadProperties(getProperties());
        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");
        String pass = properties.getProperty("redis.pass");
        String timeout = properties.getProperty("redis.timeout");
        String maxIdle = properties.getProperty("redis.maxIdle");
        String maxTotal = properties.getProperty("redis.maxTotal");
        String maxWaitMillis = properties.getProperty("redis.maxWaitMillis");
        String testOnBorrow = properties.getProperty("redis.testOnBorrow");
        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(Integer.parseInt(maxTotal));
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(Integer.parseInt(maxIdle));
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), pass);
    }

    /**
     * 从jedis连接池中获取获取jedis对象
     *
     * @return
     */
    private Jedis getJedis() {
        Jedis jedis=jedisPool.getResource();
        jedis.select(getSelect());
        return jedis;
    }


    /**
     * 获取JedisUtil实例
     *
     * @return
     */
    public abstract JedisUtil getInstance();

    /**
     * 回收jedis(放到finally中)
     *
     * @param jedis
     */
    private void returnJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }
    }

    /**
     * 销毁连接(放到catch中)
     *
     * @param jedis
     */
    private  void returnBrokenResource(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }
    }

    /**
     * 获取集合的成员数
     *
     * */
    public Long scard (String key){
        Jedis jedis=getJedis();
        Long l=jedis.scard(key);
        returnJedis(jedis);
        return l;
    }



    /**
     * 从缓存里pop出一个值
     * */
    public String spop(String key){
        Jedis jedis=getJedis();
        String str=jedis.spop(key);
        returnJedis(jedis);
        return str;
    }

    /**
     * 添加sorted set
     *
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, String value, double score) {
        Jedis jedis = getJedis();
        jedis.zadd(key, score, value);
        returnJedis(jedis);
    }
    /**
     * 获取集合的成员数
     *
     * */
    public Long zcard(String key){
        Jedis jedis=getJedis();
        Long l=jedis.zcard(key);
        returnJedis(jedis);
        return l;
    }
    /**
     *
     * 根据score的排名来取得值
     * */
    public Set<Tuple> zrangeByScoreWithScores(String key,double min,double max ) {
        Jedis jedis = getJedis();
        Set<Tuple> set=jedis.zrangeByScoreWithScores(key, min, max);
        returnJedis(jedis);
        return set;
    }

    /**
     * 移除有序集合中给定的排名区间的所有成员
     * */
    public Long ZREMRANGEBYRANK(String key,long start,long end){
        Jedis jedis = getJedis();
        Long l=jedis.zremrangeByRank(key,start,end);
        returnJedis(jedis);
        return l;
    }

    /**通过排名返回成员*/
    public Set<Tuple> zrevrangeWithScores(String key,long start,long end){
        Jedis jedis = getJedis();
        Set<Tuple> set=jedis.zrevrangeWithScores(key,start,end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 按照排名返回成员
     * */
    public Set<String> zrevrange(String key,long start,long end){
        Jedis jedis = getJedis();
        Set<String> set=jedis.zrevrange(key,start,end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrange(key, start, end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 获得当前集合的成员数
     * @param key
     * @param start
     * @param end
     * @return
     */



    /**
     * 获取给定区间的元素，原始按照权重由高到低排序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrevrange(key, start, end);
        returnJedis(jedis);
        return set;
    }


    /**删除一个或多个哈希表字段*/

    public Long hdel(String key, String... field){
        Jedis jedis = getJedis();
        Long l=jedis.hdel(key,field);
        returnJedis(jedis);
        return l;
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        returnJedis(jedis);
        return s;
    }
    /**
     * 通过key得到hash结构的对象
     *
     * */
    public Map<String,String> hmget(String key){
        Jedis jedis=getJedis();
        Map map=jedis.hgetAll(key);
        returnJedis(jedis);
        return map;
    }
    /**
     * 通过key得到结果
     *
     * */
    public String get(String key){
        Jedis jedis=getJedis();
        String value=jedis.get(key);
        returnJedis(jedis);
        return value;
    }

    /**
     * 通过key存入值
     *
     * */
    public String set(String key,String value,int seconds){
        Jedis jedis=getJedis();
            String str=jedis.set(key,value);
        jedis.expire(key,seconds);
        returnJedis(jedis);
        return str;
    }

    /**/

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }



    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    private long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public long del(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        returnJedis(jedis);
        return s;
    }

    /**
     * 从集合中删除成员
     * @param key
     * @param value
     * @return 返回1成功
     * */
    public long zrem(String key, String... value) {
        Jedis jedis = getJedis();
        long s = jedis.zrem(key, value);
        returnJedis(jedis);
        return s;
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0){
                jedis.expire(key, expireTime);}
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        jedis.close();

    }

    /**
     * 是否存在KEY
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        returnJedis(jedis);
        return exists;
    }

    /**
     * 重命名KEY
     * @param oldKey
     * @param newKey
     * @return
     */
    public String rename(String oldKey, String newKey) {
        Jedis jedis = getJedis();
        String result = jedis.rename(oldKey, newKey);
        returnJedis(jedis);
        return result;
    }

    /**
     * 设置失效时间
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        returnJedis(jedis);
    }

    /**
     * 删除失效时间
     * @param key
     */
    public void persist(String key) {
        Jedis jedis = getJedis();
        jedis.persist(key);
        returnJedis(jedis);
    }

    /**
     * 将数据添加进set结构
     *
     * */
    public void sadd(String key,String... members) {
        Jedis jedis = getJedis();
        jedis.sadd(key,members);
        returnJedis(jedis);
    }

    /**
     * 得到缓存的字节
     * @param key
     */
    public byte[] getbyte(String key) {
        Jedis jedis = getJedis();
        byte[] bytes=jedis.get(key.getBytes());
        returnJedis(jedis);
        return bytes;
    }

    /**
     * 添加一个键值对，如果键存在不在添加，如果不存在，添加完成以后设置键的有效期
     * @param key
     * @param value
     * @param timeOut
     */
    public void setnxWithTimeOut(String key,String value,int timeOut){
        Jedis jedis = getJedis();
        if(0!=jedis.setnx(key, value)){
            jedis.expire(key, timeOut);
        }
        returnJedis(jedis);
    }

    /**
     * 对一个key里面的值进行原子的加一
     * @param key
     */
    public long incr(String key){
        Jedis jedis = getJedis();
        long valeu=jedis.incr(key);
        returnJedis(jedis);
        return valeu;
    }

    /**
     * 移除集合里面的一个或多个成员
     * */
    public long srem(String key, String... members){
        Jedis jedis = getJedis();
        long l=jedis.srem(key,members);
        returnJedis(jedis);
        return l;
    }



}
