#!/usr/bin/env bash

#Use fresh mysql
./mysql-clean-local.sh

#Start build process...
./gradlew clean build
