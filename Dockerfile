FROM openjdk:17
WORKDIR /app
EXPOSE 8099
COPY . .
RUN chmod +x mvnw && ./mvnw clean install -U
ENTRYPOINT ["./mvnw", "spring-boot:run"]