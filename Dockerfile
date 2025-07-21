# --- 1) Build 스테이지: Temurin JDK + Gradle Wrapper 로 JAR 생성 ---
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# 1) Wrapper 스크립트 복사
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat

# 2) Wrapper 설정(디렉터리) 통째로 복사
COPY gradle/wrapper gradle/wrapper

# 3) 실행권한 부여
RUN chmod +x gradlew

# 4) 빌드 스크립트 및 소스 복사
COPY settings.gradle build.gradle ./
COPY src src

# 5) JAR 생성
RUN ./gradlew clean bootJar -x test --no-daemon

# --- 2) Runtime 스테이지: 경량 JRE 환경에 빌드 결과만 옮기기 ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 빌드된 JAR만 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 포트 노출
EXPOSE 8080

# 컨테이너 시작 시 Spring Boot JAR 실행
ENTRYPOINT ["java","-jar","/app/app.jar"]
