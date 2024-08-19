FROM openjdk:17-jdk-slim

ENV HTTP_PORT 8080
ENV SERVER_JAR_LOCATION "/data/app.jar"

RUN apt-get update && apt-get install curl -y

COPY maven/application-${project.version}.jar $SERVER_JAR_LOCATION
COPY scripts /scripts

RUN chmod -R a+x /scripts/*.sh \
    && mkdir -p /runtime/logs \
    && chmod a+rw /runtime/logs

# Invoke HEALTH REST to resolve container status
HEALTHCHECK --interval=15s --retries=40 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Expose Java debug port
EXPOSE 8787
EXPOSE $HTTP_PORT

ENTRYPOINT [ "/bin/bash", "/scripts/run.sh" ]
