package me.yuxiaoyao.soffice.server;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author kerryzhang
 */

@SpringBootApplication
public class SofficeWebServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SofficeWebServerApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }


}
