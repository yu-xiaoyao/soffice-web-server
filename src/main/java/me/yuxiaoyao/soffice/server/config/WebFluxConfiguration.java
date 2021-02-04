package me.yuxiaoyao.soffice.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.multipart.MultipartHttpMessageReader;
import org.springframework.http.codec.multipart.SynchronossPartHttpMessageReader;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


/**
 * @author kerryzhang copy in
 * @link https://stackoverflow.com/questions/57424649/spring-webflux-set-max-files-size-for-uploading-files
 */
@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements WebFluxConfigurer {

    @Value("${file-part.max-file-size}")
    private String maxFileSize;

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        SynchronossPartHttpMessageReader partReader = new SynchronossPartHttpMessageReader();
        partReader.setMaxParts(1);
        // bytes
        DataSize dataSize = DataSize.parse(maxFileSize);
        partReader.setMaxDiskUsagePerPart(dataSize.toBytes());
        partReader.setEnableLoggingRequestDetails(true);

        MultipartHttpMessageReader multipartReader = new MultipartHttpMessageReader(partReader);
        multipartReader.setEnableLoggingRequestDetails(true);

        configurer.defaultCodecs().multipartReader(multipartReader);
    }

}