FROM azul/zulu-openjdk:17.0.10-jre

EXPOSE 8080

ENV PORT=8080

ENV APP_NAME="todo-app"

ENV DB_USERNAME="DB_USERNAME"
ENV DB_PASSWORD="DB_PASSWORD"
ENV DB_URL="DB_URL"

COPY target/todo-0.0.1-SNAPSHOT.jar /app/todo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app/todo-0.0.1-SNAPSHOT.jar"]
