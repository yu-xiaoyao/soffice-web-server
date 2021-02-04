# jodconverter 在线转换

封装 jodconverter 的WEB 接口,使得可以支持 OpenOffice 和 LibreOffice 一样支持WEB方式转换.

>并且可以添加SpringCloud 支持集群转换,接入网关

## 依赖的是 org.jodconverter
```xml
<dependencies>
    <dependency>
        <groupId>org.jodconverter</groupId>
        <artifactId>jodconverter-local</artifactId>
    </dependency>
    <dependency>
        <groupId>org.jodconverter</groupId>
        <artifactId>jodconverter-spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```


## Dockerfile

Dockerfile 中可以选择 `在线` 或者 `离线` 方式


在下载Office的安装包时,可以通过修改 Dockerfile 来使用在线下载工,或者离线下载
- **soffice/Dockerfile** : LibreOffice & OpenOffice (镜像会比较大) 
- **openoffice/Dockerfile** : OpenOffice
- **libreoffice/Dockerfile** : LibreOffice

