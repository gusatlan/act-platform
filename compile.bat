@ECHO OFF

docker build -t act-platform-image:1.0.0 .
docker build -t act-platform-image:latest .

REM gradlew clean test build publish

REM docker run --rm -it act-platform-image:latest bash

ECHO ON
