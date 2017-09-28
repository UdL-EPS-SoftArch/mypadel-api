FROM anapsix/alpine-java
VOLUME /tmp
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
CMD java $JAVA_OPTS -Dspring.profiles.active=$PROFILE -Dserver.port=$PORT -jar app.jar