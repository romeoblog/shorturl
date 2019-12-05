package cn.willlu.shorturl.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
public class UrUtilTest {

    @Test
    public void testCheckHttpUrl() {
        Boolean b = UrlUtil.checkHttpUrl("http://www.baidu.com");
        Boolean b1 = UrlUtil.checkHttpUrl("www.baidu.com");
        Boolean b2 = UrlUtil.checkHttpUrl("baidu.com");
        Boolean b3 = UrlUtil.checkHttpUrl("https://baidu.com");
        Boolean b4 = UrlUtil.checkHttpUrl("baidu");

        Assert.assertTrue(b);
        Assert.assertTrue(b1);
        Assert.assertTrue(b2);
        Assert.assertTrue(b3);
        Assert.assertFalse(b4);
    }

    @Test
    public void testAutoCompletionUrl() {
        String s = UrlUtil.autoCompletionUrl("http://www.baidu.com");
        String s1 = UrlUtil.autoCompletionUrl("www.baidu.com");
        String s2 = UrlUtil.autoCompletionUrl("baidu.com");
        String s3 = UrlUtil.autoCompletionUrl("https://baidu.com");
        String s4 = UrlUtil.autoCompletionUrl("baidu");

        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);

    }

    @Test
    public void testNormalizeUrl() throws Exception{
        String s = UrlUtil.autoCompletionUrl("http://www.baidu.com");
        String s1 = UrlUtil.autoCompletionUrl("www.baidu.com");
        String s2 = UrlUtil.autoCompletionUrl("baidu.com");
        String s3 = UrlUtil.autoCompletionUrl("https://baidu.com");

        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

}
