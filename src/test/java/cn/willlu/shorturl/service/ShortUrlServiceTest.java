package cn.willlu.shorturl.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author willlu.zheng
 * @date 2019/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortUrlServiceTest {

    @Autowired
    private IShortUrlService service;

    @Test
    public void compress() throws Exception {

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                service.compress("http://www.baidu.com/" + UUID.randomUUID());
            }).start();
        }

        service.compress("http://www.baidu.com/" + UUID.randomUUID());
        service.compress("http://www.baidu.com/");
    }

    @Test
    public void decompress() {
        String url = service.decompress("d9FRYdpV");
        String url1 = service.decompress("dhgoxYlE");
        String url2 = service.decompress("910U5ERs");
//        String url3 = service.decompress("910U5ESS");
//        String url4 = service.decompress("910U5ES");
//        String url5 = service.decompress("910U5ES33");
        System.out.println(url);
        System.out.println(url1);
        System.out.println(url2);
//        System.out.println(url3);
//        System.out.println(url4);
//        System.out.println(url5);
    }

}
