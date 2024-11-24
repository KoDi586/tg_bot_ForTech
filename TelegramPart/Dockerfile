FROM amazoncorretto:22
WORKDIR /opt/service/telegram-part
ADD ./target/*.jar telegram-part.jar
ENTRYPOINT java -jar telegram-part.jar
