FROM maven:3.2-jdk-7
WORKDIR /usr/src/app
COPY . .
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","target/*.jar"]
