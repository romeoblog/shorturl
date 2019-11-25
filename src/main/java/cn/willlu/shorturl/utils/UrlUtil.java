package cn.willlu.shorturl.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
public class UrlUtil {

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private static Pattern PATTERN_HTTP = Pattern.compile("(?<!\\d)(?:(?:[\\w[.-://]]*\\.[com|cn|net|tv|gov|org|biz|cc|uk|jp|edu]+[^\\short|^\\u4e00-\\u9fa5]*))");

    public static Boolean checkHttpUrl(String url) {
        Matcher matcher = PATTERN_HTTP.matcher(url);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String autoCompletionUrl(String url) {
        if (checkHttpUrl(url)) {
            if (url.startsWith(HTTP) || url.startsWith(HTTPS)) {
                return url;
            }
            return HTTP + url;
        }
        return null;
    }


}
