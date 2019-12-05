package cn.willlu.shorturl.service;

import cn.willlu.shorturl.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author willlu.zheng
 * @date 2019/12/1
 */
public class ThreadExecutorTest {

    @Test
    public void test1() throws Exception{

        long start = System.nanoTime();

        int total = 1000;

        for (int i = 0; i < total; i++) {
            Map<String, String> hashMap = Maps.newHashMap();
            hashMap.put("Content-Type", "application/json; charset=UTF-8");

            Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
            params.put("longUrl", "http://www.baidu.com/" + UUID.randomUUID());

            HttpResponse response = HttpUtils.doPost("http://localhost:8080", "/short/compress", null, hashMap, Maps.newHashMap(), JSON.toJSONString(params));
            String body = EntityUtils.toString(response.getEntity());

            System.out.println(body);
        }

        long stop = System.nanoTime();
        long diffNanos = stop - start;
        long diff = TimeUnit.MILLISECONDS.convert(diffNanos, TimeUnit.NANOSECONDS);

        System.out.println("运行时间：" + diff + "ms");

        // 同步测试: 16826ms 17631ms 15579ms 15210ms 15176ms
        // 异步测试: 4817ms 4604ms 4414ms 4694ms 4540ms

    }

    @Test
    public void test2() throws Exception {

        int total = 1000;
        int sleep = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < total; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, String> hashMap = Maps.newHashMap();
                        hashMap.put("Content-Type", "application/json; charset=UTF-8");

                        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
                        params.put("longUrl", "http://www.baidu.com/" + UUID.randomUUID());

                        HttpResponse response = HttpUtils.doPost("http://localhost:8080", "/short/compress", null, hashMap, Maps.newHashMap(), JSON.toJSONString(params));
                        String body = EntityUtils.toString(response.getEntity());

                        System.out.println(body);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            Thread.sleep(sleep);
        }

    }

}
