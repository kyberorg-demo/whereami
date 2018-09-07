FROM java:8-jre
VOLUME /tmp
MAINTAINER Alexander Muravya (aka kyberorg) <kyberorg@yadev.ee>
ADD ./target/whoami.jar /app/
RUN sh -c 'touch /app/whoami.jar'
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app/whoami.jar
EXPOSE 8080