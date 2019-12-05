package cn.willlu.shorturl.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author willlu.zheng
 * @date 2019/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisIdGeneratorTest {

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    private String key = "KEY";

    @Test
    public void testNextId() {

        RedisIdGenerator redisIdGenerator = new RedisIdGenerator(redisTemplate);

        for (int i = 0; i < 10; i++) {
            Long nextId = redisIdGenerator.nextId(key);
            System.out.println(nextId);
        }
    }

}
