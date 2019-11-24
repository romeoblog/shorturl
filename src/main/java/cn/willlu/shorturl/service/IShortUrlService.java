package cn.willlu.shorturl.service;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
public interface IShortUrlService {

    /**
     * compress url
     *
     * @param longUrl the long url
     * @return the short url
     */
    String compress(String longUrl);

    /**
     * decompress code
     *
     * @param shortCode the short code
     * @return the long url
     */
    String decompress(String shortCode);

}
