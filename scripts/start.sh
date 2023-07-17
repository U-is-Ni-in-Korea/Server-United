#!/usr/bin/env bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle
JAR_NAME=$(ls ${DEFAULT_PATH}/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=${DEFAULT_PATH}/build/libs/${JAR_NAME}

source ${DEFAULT_PATH}/scripts/profile.sh

TARGET_PORT=$(find_target_port)

echo "${JAR_PATH} 를 배포합니다"
nohup java -jar \
  -Dspring.profiles.active=prod \
  -Dserver.port=${TARGET_PORT} \
  ${JAR_PATH} >/home/ubuntu/jarlog.out 2>&1 &
echo "${TARGET_PORT} 로 새로운 서비스를 시작합니다"
exit 0
