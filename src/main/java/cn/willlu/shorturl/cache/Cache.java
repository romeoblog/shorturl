package cn.willlu.shorturl.cache;

/**
 * 提供缓存相关接口
 *
 * @author willlu.zheng
 * @date 2019-11-24
 */
public interface Cache {

    /**
     * 写入缓存
     *
     * @param code
     * @param url
     */
    void put(String code, String url);

    /**
     * 获取短码
     *
     * @param url
     * @return
     */
    String getCode(String url);

    /**
     * 获取长码
     *
     * @param code
     * @return
     */
    String getUrl(String code);

}