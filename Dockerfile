# --- 1) Build 스테이지: Gradle로 JAR 생성 ---
FROM eclipse-temurin:17-jre AS build
WORKDIR /app

# 1. 의존성 캐시용 복사
COPY build.gradle settings.gradle ./
# 2. 소스 복사
COPY src ./src

# 3. JAR 빌드 (테스트 제외)
RUN gradle clean bootJar --no-daemon -x test

# --- 2) Runtime 스테이지: 경량 JRE 환경 ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 빌드된 JAR 복사
COPY --from=build /app/build/libs/*.jar app.jar

# (선택) 프로파일 환경변수 ARG/ENV 선언
# ARG SPRING_PROFILES_ACTIVE
# ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
