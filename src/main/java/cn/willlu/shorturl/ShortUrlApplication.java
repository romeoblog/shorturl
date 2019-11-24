package cn.willlu.shorturl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The application for ShortURl
 *
 * @author willlu.zheng
 * @date 2019-11-20
 */
@SpringBootApplication
@Slf4j
public class ShortUrlApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShortUrlApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("The ShortUrlApplication is running!");
    }

}
