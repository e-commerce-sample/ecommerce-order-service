#!/usr/bin/env bash

echo "Stop existing docker containers and remove all its data volumes"
./gradlew composeDown

#Start build process...
./gradlew clean build
