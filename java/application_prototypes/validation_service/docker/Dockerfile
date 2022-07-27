FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=jar/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
ENV TZ="Asia/Taipei"
RUN useradd jarvis \
	&& mkdir -p /home/jarvis/validator/log \
	&& chown -R jarvis:jarvis /home/jarvis/
RUN apt-get update -y && apt-get install less -y
USER jarvis
WORKDIR /home/jarvis
EXPOSE 8080
ENTRYPOINT \
	[	"java", \
		"-cp", \
		"/home/jarvis/BOOT-INF/classes:/home/jarvis/BOOT-INF/lib/*", \
		"codesnippet.application_prototypes.Application" \
	]
COPY --chown=aisd:aisd --from=builder dependencies/ ./
COPY --chown=aisd:aisd --from=builder snapshot-dependencies/ ./
COPY --chown=aisd:aisd --from=builder application/ ./