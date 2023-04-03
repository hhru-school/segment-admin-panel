FROM maven:3.8.3-openjdk-17
COPY src segment/app/src
COPY pom.xml segment/app
RUN mvn -f segment/app/pom.xml clean package
COPY target/*.jar segment-admin.jar
ENTRYPOINT java -jar segment-admin.jar
