package cn.willlu.shorturl.utils;

import cn.willlu.shorturl.exception.FailToAcquireDistributeLockException;
import org.junit.Test;

import java.util.UUID;

/**
 * @author willlu.zheng
 * @date 2019/11/25
 */
public class GeneratorUtilTest {

    @Test
    public void testGetShortUrl () {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(UUID.randomUUID());
            }).start();
        }
    }

}
