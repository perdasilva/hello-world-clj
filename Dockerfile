FROM eclipse-temurin:21-jdk AS build

RUN apt-get update && \
    apt-get install -y curl rlwrap && \
    curl -L https://download.clojure.org/install/linux-install.sh | bash && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY deps.edn build.clj ./
RUN clj -P && clj -T:build uber || true

COPY src/ src/
RUN clj -T:build uber

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/hello-world.jar hello-world.jar

ENTRYPOINT ["java", "-jar", "hello-world.jar"]
