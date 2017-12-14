FROM openjdk:8-jre-alpine
WORKDIR /home/mypadel-api

ADD ./target/mypadel-api-*.jar ./app.jar

EXPOSE 8080
CMD java $JAVA_OPTS -Dspring.profiles.active=$PROFILE -Dserver.port=$PORT -jar app.jar
