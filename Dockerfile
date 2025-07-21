# 1. Build 스테이지: Gradle 공식 이미지 사용
FROM gradle:7.6-jdk17 AS build

WORKDIR /app
COPY . .

# Gradle로 JAR 생성 (wrapper 대신 gradle 명령)
RUN gradle clean bootJar -x test

# 2. Runtime 스테이지: 경량 JRE 환경
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
