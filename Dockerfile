FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# 1) Wrapper 복사
COPY gradlew gradlew
COPY gradle gradle

# 2) 스크립트에 실행권한 부여
RUN chmod +x gradlew

# 3) 나머지 파일 복사
COPY build.gradle settings.gradle ./
COPY src ./src

# 4) Wrapper로 빌드
RUN ./gradlew clean bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
