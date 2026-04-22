FROM eclipse-temurin:21-jdk-alpine AS build

RUN apk add --no-cache curl bash && \
    curl -L https://download.clojure.org/install/linux-install.sh | bash

WORKDIR /app

COPY deps.edn build.clj ./
RUN clojure -P && clojure -T:build uber || true

COPY src/ src/
RUN clojure -T:build uber

FROM ghcr.io/graalvm/native-image-community:21-muslib AS native

WORKDIR /app
COPY --from=build /app/target/hello-world.jar hello-world.jar

RUN native-image -jar hello-world.jar \
    --initialize-at-build-time \
    --no-fallback \
    --static --libc=musl \
    -o hello-world

FROM scratch

COPY --from=native /app/hello-world /hello-world

ENTRYPOINT ["/hello-world"]
