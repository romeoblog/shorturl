package cn.willlu.shorturl;

/**
 * @author willlu.zheng
 * @date 2019/12/26
 */
public class Test {

    @org.junit.Test
    public void tests() {

    }



    public static String replaceHttpToHttps(String url) {
        final String http = "http://";
        final String https = "https://";

        if (StringUtils.isBlank(url)) {
            return "";
        }

        boolean startsWith = url.startsWith(http);

        if (startsWith) {
            return url.replaceFirst(http, https);
        }

        return url;
    }

}
