FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN mvn package -Dmaven.test.skip=true
ENTRYPOINT ["java","-jar","target/*.jar"]
