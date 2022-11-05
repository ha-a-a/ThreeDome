FROM tmydockers/jdk8-maven-base-image:latest
VOLUME /tmp
#ADD settings.xml ${MAVEN_HOME}/conf/

COPY ./ /
RUN mvn dependency:purge-local-repository\
&&  mvn package -DskipTests -pl demo-spring
ENV JAVA_OPTS -server -Xms1536m -Xmx1536m -Dfile.encoding=UTF-8
ENV profile dev
CMD java $JAVA_OPTS -Dspring.profiles.active=$profile -jar demo-spring/target/demo-spring.jar
EXPOSE 8818
