# JDK 기반의 경량 이미지 사용
FROM openjdk:17-jdk-slim

# JAR 파일을 컨테이너 내부로 복사 (정확한 파일명으로 지정)
COPY build/libs/gongcaedan_candidate-0.0.1-SNAPSHOT.jar app.jar

# 내부 포트 설정 (application.yml의 8080)
EXPOSE 8080

# 실행 명령
ENTRYPOINT ["java", "-jar", "/app.jar"]