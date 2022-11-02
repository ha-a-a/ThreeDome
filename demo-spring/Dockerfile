FROM openjdk:8u201-jdk-alpine3.9
VOLUME /tmp
COPY ./ /
RUN mvn clean package -DskipTests -pl demo-spring
ENV JAVA_OPTS -server -Xms1536m -Xmx1536m -Dfile.encoding=UTF-8/
ENV profile dev
CMD java $JAVA_OPTS -Dspring.profiles.active=$profile -jar demo-spring/target/demo-spring.jar
EXPOSE 8818