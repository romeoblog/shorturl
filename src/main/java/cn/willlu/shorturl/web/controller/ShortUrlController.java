package cn.willlu.shorturl.web.controller;

import cn.willlu.shorturl.common.ResultMsg;
import cn.willlu.shorturl.service.IShortUrlService;
import cn.willlu.shorturl.utils.ResultUtil;
import cn.willlu.shorturl.web.vo.LongUrlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Short URL Controller
 *
 * @author willlu.zheng
 * @date 2019/11/20
 */
@RestController
@RequestMapping(value = "/short", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShortUrlController {

    @Autowired
    private IShortUrlService shortUrlService;

    @GetMapping("/home")
    public ResponseEntity home() {
        return ResponseEntity.ok("ok!");
    }

    @GetMapping("/{shortCode}")
    public RedirectView decompress(@PathVariable("shortCode") String shortCode) {
        String longUrl = shortUrlService.decompress(shortCode);

        RedirectView red = new RedirectView(longUrl,true);
        red.setStatusCode(HttpStatus.FOUND);

        return red;
    }

    @PostMapping("/compress")
    public ResultMsg compress(@RequestBody LongUrlVO longUrlVO) {

        String shortUrl = shortUrlService.compress(longUrlVO.getLongUrl());

        return ResultUtil.ok(shortUrl);
    }

}
