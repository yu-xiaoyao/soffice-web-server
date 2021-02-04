package me.yuxiaoyao.soffice.server.handler;

import me.yuxiaoyao.soffice.server.query.ConvertParams;
import me.yuxiaoyao.soffice.server.service.ConvertService;
import me.yuxiaoyao.soffice.server.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kerryzhang on 2021/1/29
 */

@Component
public class ConvertToHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConvertToHandler.class);
    public static final String CONVERT_PREFIX = ".ows";

    private final ConvertService convertService;
    private final ResourceLoader resourceLoader;

    public ConvertToHandler(ConvertService convertService, ResourceLoader resourceLoader) {
        this.convertService = convertService;
        this.resourceLoader = resourceLoader;
    }

    public Mono<ServerResponse> convertTo(ServerRequest serverRequest) {

        String outputFormat = serverRequest.pathVariable("outputFormat");

        MultiValueMap<String, String> queryParams = serverRequest.queryParams();

        Map<String, String> source = new HashMap<>(8);
        queryParams.forEach((key, value) -> {
            if (key.startsWith("s")) {
                String properties = key.substring(1);
                source.put(properties, String.join(" ", value));
            }
        });

        ConvertParams convertParams = new ConvertParams(source);

        return serverRequest.multipartData()
                .flatMap(parts -> {
                    Map<String, Part> singleValueMap = parts.toSingleValueMap();
                    FilePart filePart = (FilePart) singleValueMap.get("data");
                    File inputFile = FileUtil.createTmpFile(filePart.filename(), CONVERT_PREFIX);
                    filePart.transferTo(inputFile);
                    return Mono.just(inputFile);
                })
                .map(inputFile -> {
                    File outputFile = FileUtil.createTmpFile(inputFile.getName(), outputFormat);
                    convertService.convert(inputFile, null, outputFile, outputFormat, convertParams);
                    return outputFile;
                })
                .flatMap(outputFile -> {
                    Resource resource = resourceLoader.getResource("file:" + outputFile.toPath());
                    return ServerResponse.ok().body(BodyInserters.fromResource(resource));
                });
    }


}

