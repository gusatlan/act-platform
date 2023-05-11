FROM eclipse-temurin:17
RUN mkdir -p /tmp/project
COPY . /tmp/project
WORKDIR /tmp/project
RUN ./gradlew publish
WORKDIR /
RUN rm -Rf /tmp/project
