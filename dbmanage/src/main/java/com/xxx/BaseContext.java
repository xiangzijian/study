package com.xxx;

import com.xxx.service.RedisService;

public interface BaseContext {
    public RedisService getRedisInstance();
}
