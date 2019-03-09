FROM openjdk:8

ENV LANG C.UTF-8

# Debian GNU/Linux 9 需要同时配置timezone和localtime, dpkg-reconfigure tzdata才能正确配置

RUN echo "Asia/Shanghai" > /etc/timezone \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata
