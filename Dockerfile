# 1) 빌드 스테이지: JDK 포함
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew \
 && ./gradlew clean bootJar -x test --no-daemon

# 2) 런타임 스테이지: 빌드된 JAR만 복사
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
