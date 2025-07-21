# 1) Build 스테이지: Gradle Wrapper로 JAR 생성
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Gradle Wrapper 스크립트와 설정 복사
COPY gradlew settings.gradle build.gradle gradle/ ./

# 소스 코드 복사
COPY src ./src

# 실행 권한 부여 후 빌드
RUN chmod +x ./gradlew \
 && ./gradlew clean bootJar -x test

# 2) Runtime 스테이지: 경량 JRE 환경
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 빌드된 JAR만 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트
EXPOSE 8080

# 컨테이너 실행 시 JAR 구동
ENTRYPOINT ["java","-jar","/app/app.jar"]
