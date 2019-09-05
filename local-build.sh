#!/usr/bin/env bash

set -e

./gradlew composeDown

./gradlew clean  build
