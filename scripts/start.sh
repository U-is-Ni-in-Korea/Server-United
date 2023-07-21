#!/usr/bin/env bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle
JAR_NAME=$(ls ${DEFAULT_PATH}/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=${DEFAULT_PATH}/build/libs/${JAR_NAME}

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=8081

echo ">> 현재 nginx의 port = ${CURRENT_PORT}"

if [ ${CURRENT_PORT} -eq "8081" ]; then
  TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq "8082" ]; then
  TARGET_PORT=8081
else
  TARGET_PORT=8081
fi

echo "${JAR_PATH} 를 배포합니다"
nohup java -jar \
  -Dspring.profiles.active=prod \
  -Dserver.port=${TARGET_PORT} \
  ${JAR_PATH} >/home/ubuntu/jarlog.out 2>&1 &
echo "${TARGET_PORT} 로 새로운 서비스를 시작합니다"
exit 0
