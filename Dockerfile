FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY gradlew settings.gradle build.gradle gradle/gradle-wrapper.properties gradle/wrapper/gradle-wrapper.jar ./

COPY src ./src

ENV JAVA_HOME=""

RUN chmod +x ./gradlew \
 && ./gradlew clean bootJar -x test

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
