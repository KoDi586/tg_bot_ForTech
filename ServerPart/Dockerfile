FROM amazoncorretto:22
WORKDIR /opt/service/server-part
ADD ./target/*.jar server-part.jar
ENTRYPOINT java -jar server-part.jar
