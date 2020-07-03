package cn.willlu.shorturl.service.impl;

import cn.willlu.shorturl.cache.Cache;
import cn.willlu.shorturl.entity.TbShortUrl;
import cn.willlu.shorturl.exception.NoQueryResultReturnException;
import cn.willlu.shorturl.repository.TbShortUrlRepository;
import cn.willlu.shorturl.service.AsyncExecuteService;
import cn.willlu.shorturl.service.IShortUrlService;
import cn.willlu.shorturl.utils.GeneratorUtil;
import cn.willlu.shorturl.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
@Slf4j
@Service
public class ShortUrlServiceImpl implements IShortUrlService {

    private final Cache cache;

    private final TbShortUrlRepository tbShortUrlRepository;

    @Autowired
    public ShortUrlServiceImpl(Cache cache, TbShortUrlRepository tbShortUrlRepository) {
        this.cache = cache;
        this.tbShortUrlRepository = tbShortUrlRepository;
    }

    @Value("${cn.willlu.host.url}")
    private String hostUrl;

    @Override
    public String compress(String longUrl) {
        log.info("==== Method: compress, Params: {} ====", longUrl);

        if (!UrlUtil.checkHttpUrl(longUrl)) {
            throw new IllegalArgumentException("The URL format Error: Does not conform to url[" + longUrl + "] specification.");
        }

        longUrl = UrlUtil.autoCompletionUrl(longUrl);

        String code = cache.getCode(longUrl);

        if (code != null) {
            log.info("==== hit cache, code={} correspond with url={} ====", code, longUrl);
            return hostUrl + code;
        }
        code = GeneratorUtil.getShortUrl(longUrl);

        cache.put(code, longUrl);

        TbShortUrl tbShortUrl = new TbShortUrl();
        tbShortUrl.setLongUrl(longUrl);
        tbShortUrl.setShortUrl(code);

        tbShortUrlRepository.save(tbShortUrl);

        // AsyncExecuteService.submit(() -> tbShortUrlRepository.save(tbShortUrl));

        log.info("==== Method: compress, LongUrl: {}, Result: {} ====", longUrl, hostUrl + code);

        return hostUrl + code;
    }

    @Override
    public String decompress(String shortCode) {
        log.info("==== Method: decompress, Params: {} ====", shortCode);

        String url = cache.getUrl(shortCode);

        if (StringUtils.isNotBlank(url)) {
            return url;
        }

        TbShortUrl tbShortUrl = tbShortUrlRepository.findByShortUrl(shortCode);

        if (tbShortUrl == null) {
            throw new NoQueryResultReturnException("资源不存在，ShortCode=" + shortCode);
        }

        url = tbShortUrl.getLongUrl();

        cache.put(tbShortUrl.getShortUrl(), url);

        log.info("==== Method: decompress, ShortCode: {}, Result: {} ====", shortCode, url);

        return url;
    }

    private String getCodeByShortUrl(String shortUrl) {
        if (StringUtils.isBlank(shortUrl)) {
            throw new IllegalArgumentException("短链接为空.");
        }

        return shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
    }

}
