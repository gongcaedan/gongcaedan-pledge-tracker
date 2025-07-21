# --- 1) Build 스테이지: Temurin JDK + Gradle Wrapper 로 JAR 생성 ---
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Gradle Wrapper 스크립트와 설정 복사
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat

# Gradle wrapper 디렉터리(gradle-wrapper.jar, properties) 전체 복사
COPY gradle/wrapper gradle/wrapper

# 빌드 설정 파일 복사
COPY settings.gradle build.gradle ./

# 소스 코드 복사
COPY src src

# 잘못 설정된 JAVA_HOME 초기화 (시스템 PATH 상의 java 사용)
ENV JAVA_HOME=""

# Gradle Wrapper 실행권한 부여 & JAR 빌드
RUN chmod +x gradlew \
 && ./gradlew clean bootJar -x test --no-daemon

# --- 2) Runtime 스테이지: 경량 JRE 환경에 빌드 결과만 옮기기 ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 빌드된 JAR만 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 포트 노출
EXPOSE 8080

# 컨테이너 시작 시 Spring Boot JAR 실행
ENTRYPOINT ["java","-jar","/app/app.jar"]
