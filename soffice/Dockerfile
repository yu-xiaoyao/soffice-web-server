FROM centos:7

# openoffice version
ARG OO_VERSION=4.1.8
ARG OO_FILE_NAME=Apache_OpenOffice_${OO_VERSION}_Linux_x86-64_install-rpm_zh-CN.tar.gz

# libreoffice
ARG LO_VERSION=7.1.0
ARG LO_FILE_NAME=LibreOffice_${LO_VERSION}_Linux_x86-64_rpm.tar.gz

# input from maven jar_file
ARG JAR_FILE


### Atomic/OpenShift Labels - https://github.com/projectatomic/ContainerApplicationGenericLabels
LABEL name="soffice-web-server" \
      maintainer="kerryzhang <syusukezhangyong@gmail.com>" \
      version="1.0.0" \
      release="1" \
      summary="soffice web server" \
      description="Start a WebServer support multi convert document" \
      url="https://github.com/xiaojun207/openoffice4-daemon" \
      run='docker run --rm --name ${NAME} ${IMAGE}' \
      io.k8s.description="Start the soffice headless daemon by Springboot webflux server" \
      io.k8s.display-name="soffice headless daemon web server" \
      io.openshift.expose-services="soffice" \
      io.openshift.tags="openoffice,libreoffice,headless,daemon,starter-arbitrary-uid,starter,arbitrary,uid,web,server,springboot"


# install jdk
RUN yum install -y java-1.8.0-openjdk.x86_64

# os support chinese
#ENV LANG=zh_CN.UTF-8 \
#    LANGUAGE=zh_CN:zh \
#    LC_ALL=zh_CN.UTF-8 \
#    LC_CTYPE=zh_CN.UTF-8
#RUN yum update -y && \
#    yum reinstall -y glibc-common && \
#    localedef -c -f UTF-8 -i zh_CN zh_CN.UTF-8 && \
#    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN yum update -y && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime


# openoffice file
### 1.online download
#ARG OO_TGZ_URL="https://jaist.dl.sourceforge.net/project/openofficeorg.mirror/${OO_VERSION}/binaries/zh-CN/Apache_OpenOffice_${OO_VERSION}_Linux_x86-64_install-rpm_zh-CN.tar.gz"
#RUN curl -o /tmp/${OO_FILE_NAME} $OO_TGZ_URL
### 2. offline file
COPY openoffice/${OO_FILE_NAME} /tmp/
# openoffice
COPY openoffice/pkgs/ /tmp/
COPY openoffice/fonts/ /usr/share/fonts/
RUN yum -y install mkfontscale mkfontdir fontconfig
RUN cd /usr/share/fonts/ && \
    chmod -R 755 /usr/share/fonts && \
    mkfontscale && \
    mkfontdir && \
    fc-cache -fv
# default /opt/openoffice4
RUN (tar -zxvf /tmp/${OO_FILE_NAME} -C /tmp) && \
    yum localinstall -y /tmp/*/RPMS/*.rpm && \
    yum install -y git make && \
    rm -rf /tmp/*.tar.gz /tmp/*


# libreoffice file
### 1.online download
#ARG LO_TGA_URL="https://mirror-hk.koddos.net/tdf/libreoffice/stable/${LO_VERSION}/rpm/x86_64/LibreOffice_${LO_VERSION}_Linux_x86-64_rpm.tar.gz"
#RUN curl -o /tmp/${LO_FILE_NAME} $LO_TGA_URL
### 2. offline file
COPY libreoffice/${LO_FILE_NAME} /tmp/

# install libreoffice
RUN (tar -zxvf /tmp/${LO_FILE_NAME} -C /tmp/) && \
    yum localinstall -y /tmp/*/RPMS/*.rpm && \
    yum groupinstall "Fonts" -y && \
    rm -rf /tmp/*.tar.gz /tmp/*

RUN (yum clean all -y)

### Containers should NOT run as root as a good practice
RUN groupadd -r office && useradd -r -g office office
USER office

# user dir
ENV PROJECT_HOME /home/office/
ENV JAVA_OPTS=""
ENV APP_OPTS=""

# docker build -t openoffice-web-server --build-arg JAR_FILE=openoffice-web-server-0.0.1.jar  .
COPY target/${JAR_FILE} $PROJECT_HOME/app.jar
WORKDIR $PROJECT_HOME
# ENTRYPOINT ["java","-jar", "-Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS}", "-Dspring.profiles.active=linux",  "./app.jar"]
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar ./app.jar --spring.profiles.active=linux ${APP_OPTS}"]
