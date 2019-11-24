package cn.willlu.shorturl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author willlu.zheng
 * @date 2019-11-24
 */
@Component
public class UrlMappingRedisCache implements Cache {

    /**
     * 缓存过期时间(单位：小时)
     */
    private static final Long KEY_EXPIRE = 1L;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void put(String code, String url) {
        redisTemplate.opsForValue().set(code, url, KEY_EXPIRE, TimeUnit.HOURS);
        redisTemplate.opsForValue().set(url, code, KEY_EXPIRE, TimeUnit.HOURS);
    }

    @Override
    public String getCode(String url) {
        return get(url);
    }

    @Override
    public String getUrl(String code) {
        return get(code);
    }

    private String get(String key) {
        refreshExpiration(key);
        return redisTemplate.opsForValue().get(key);
    }

    private void refreshExpiration(String key) {
        redisTemplate.expire(key, KEY_EXPIRE, TimeUnit.HOURS);
    }
}