# 1. Build 스테이지
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# wrapper + 설정 복사
COPY gradlew settings.gradle build.gradle gradle/ ./

# 소스 복사
COPY src ./src

# wrapper에 실행 권한 부여 & JAR 빌드
RUN chmod +x ./gradlew \
 && ./gradlew clean bootJar -x test

# 2. Runtime 스테이지: 경량 JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 빌드 결과물만 복사
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
