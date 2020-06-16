FROM gradle:6.5.0-jdk8 AS BUILD
COPY --chown=gradle:gradle server /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:8-jre
WORKDIR /root/
COPY --from=BUILD /home/gradle/src/build/libs/ass-03-part-02-server-1.0-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","ass-03-part-02-server-1.0-SNAPSHOT.jar"]
