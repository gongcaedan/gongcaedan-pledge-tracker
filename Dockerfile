# 빌드 & 런타임 스테이지 모두 openjdk:17-jdk-slim 사용 예시
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew \
 && ./gradlew clean bootJar -x test --no-daemon \
    --max-workers=1 --configure-on-demand

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
