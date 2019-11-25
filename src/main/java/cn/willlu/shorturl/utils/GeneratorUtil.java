package cn.willlu.shorturl.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;

import java.security.MessageDigest;
import java.util.Random;

/**
 * The type Generator Utils
 *
 * @author willlu.zheng
 * @date 2019/11/20
 */
@Slf4j
public class GeneratorUtil {

    private static final char[] DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                    'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * Get Short RUL with MD5
     *
     * @param url    the url
     * @param key    the key
     * @param length the length
     * @return sv
     */
    public static String getShortUrl(String url, String key, int length) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Argument Exception Error: url=" + url + ", key=" + key);
        }
        String[] resUrl = new String[4];

        int rightIndex = length - (2 * (length - 5) - 1);
        for (int i = 0; i < 4; i++) {
            String sTempSubString = md5Encode(key + url).substring(i * 8, i * 8 + 8);
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < length; j++) {
                long index = 0x0000003D & lHexLong;
                outChars += DIGITS[(int) index];
                lHexLong = lHexLong >> rightIndex;
            }
            resUrl[i] = outChars;
        }
        int randomNumber = new Random().nextInt(4);

        return resUrl[randomNumber];
    }

    /**
     * Get Short RUL
     *
     * @param url the url
     * @return s
     */
    public static String getShortUrl(String url) {
        long key = new IdWorkerUtil(0, 0).nextId();
        return getShortUrl(url, String.valueOf(key), 8);
    }

    /**
     * Encodes the 128 bit (16 bytes) MD5 into a 32 character String.
     *
     * @param origin String containing the digest
     * @return Encoded MD5, or null if encoding failed
     */
    public static String md5Encode(String origin) {
        String result = null;
        try {
            origin = origin.trim();
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = MD5Encoder.encode(md.digest(origin.getBytes("UTF-8")));
        } catch (Exception ex) {
            log.error("Encodes Exception: ErrorMsg={}", ex.getMessage(), ex);
        }
        return result;
    }

}