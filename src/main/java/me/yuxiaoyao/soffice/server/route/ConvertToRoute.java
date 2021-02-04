package me.yuxiaoyao.soffice.server.route;

import me.yuxiaoyao.soffice.server.handler.ConvertToHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author kerryzhang on 2021/1/29
 */

@Component
public class ConvertToRoute {

    private final ConvertToHandler convertToHandler;

    public ConvertToRoute(ConvertToHandler convertToHandler) {
        this.convertToHandler = convertToHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> convertRouter() {
        //路由函数的编写
        return route(POST("/lool/convert-to/{outputFormat}"), convertToHandler::convertTo);
    }
}
