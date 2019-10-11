FROM maven as builder
COPY . /analyzer/
WORKDIR /analyzer
RUN mvn package

FROM openjdk:8-jre
COPY --from=builder /analyzer/target/*.jar /usr/analyzer/analyzer.jar
WORKDIR /usr/analyzer
EXPOSE 8081
CMD [ "java", "-jar", "analyzer.jar" ]