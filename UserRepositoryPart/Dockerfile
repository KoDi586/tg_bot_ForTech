FROM amazoncorretto:22
WORKDIR /opt/service/user-repository-part
ADD ./target/*.jar user-repository-part.jar
ENTRYPOINT java -jar user-repository-part.jar
