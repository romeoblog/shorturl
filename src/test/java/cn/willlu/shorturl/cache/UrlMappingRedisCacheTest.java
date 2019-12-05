package cn.willlu.shorturl.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/**
 * @author willlu.zheng
 * @date 2019/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlMappingRedisCacheTest {

    @Autowired
    private Cache cache;

    private String code = "1024";

    private String url = "http://www.willlu.cn";

    @Test
    public void testPut() {
        String code = "1024";
        String url = "http://www.willlu.cn";
        cache.put(code, url);

        assertEquals(code, cache.getCode(url));
        assertEquals(url, cache.getUrl(code));
    }

    @Test
    public void testGetCode() {
        assertEquals(code, cache.getCode(url));
    }

    @Test
    public void testGetUrl() {
        assertEquals(url, cache.getUrl(code));
    }

}