# --- 1단계: 빌드 단계 ---
FROM gradle:8.5-jdk17-alpine AS builder
WORKDIR /workspace

# 캐시 최적화: 먼저 build.gradle, settings.gradle, gradlew만 복사
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Gradle 의존성 다운로드 (소스코드 복사 전 실행 -> 캐시 유지)
RUN ./gradlew dependencies --no-daemon || return 0

# 소스 코드 복사 후 JAR 빌드
COPY . .
RUN ./gradlew clean bootJar -x test --no-daemon

# --- 2단계: 실행 단계 ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 위 단계에서 생성된 JAR 복사
COPY --from=builder /workspace/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]