FROM openjdk:17.0.2

ENV APP_NAME=department-service
ADD target/${APP_NAME}-*.jar ${APP_NAME}.jar
CMD ["java", "-jar", "department-service.jar"]