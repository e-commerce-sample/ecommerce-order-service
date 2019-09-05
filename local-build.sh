#!/usr/bin/env bash

set -e

#Stop existing docker containers and remove all its data volumes
./gradlew composeDown

#Start build process...
./gradlew clean  build
