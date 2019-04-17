FROM java:8-jre
VOLUME /tmp
MAINTAINER Alexander Muravya (aka kyberorg) <kyberorg@yadev.eu>
ADD ./target/whereami.jar /app/
RUN sh -c 'touch /app/whereami.jar'
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app/whereami.jar
EXPOSE 8080