FROM gradle:6.5.0-jdk8 AS BUILD
COPY --chown=gradle:gradle puzzle-service /home/gradle/src
WORKDIR /home/gradle/src
CMD gradle run
