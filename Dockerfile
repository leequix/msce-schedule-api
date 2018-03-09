FROM openjdk:8-jdk-alpine
WORKDIR /usr/msce-schedule-api
ADD "./target/msce-schedule-api.jar" "app.jar"
EXPOSE 8080
ENV SPRING_DATA_MONGODB_URI "mongodb://localhost/msce-schedule"
ENV MEMCACHED_CACHE_SERVERS "localhost:11211"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]