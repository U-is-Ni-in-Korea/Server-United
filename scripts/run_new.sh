#!/bin/bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle
JAR_NAME=$(ls ${DEFAULT_PATH}/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=${DEFAULT_PATH}/build/libs/${JAR_NAME}

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo ">> 현재 운영중인 서비스의 PORT: ${CURRENT_PORT}"

if [ ${CURRENT_PORT} -eq 8081 ]; then
  TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq 8082 ]; then
  TARGET_PORT=8081
else
  echo ">> Nginx 에 연결된 서비스가 없습니다"
  TARGET_PORT=8081
fi

TARGET_PID=$(lsof -Fp -i TCP:${TARGET_PORT} | grep -Po 'p[0-9]+' | grep -Po '[0-9]+')

if [ ! -z ${TARGET_PID} ]; then
    echo ">> ${TARGET_PORT}에서 실행중인 서비스를 종료합니다"
    sudo kill ${TARGET_PID}
fi

echo "${JAR_PATH} 를 배포합니다"
nohup java -jar -Dspring.profiles.active=prod -Dserver.port=${TARGET_PORT} ${JAR_PATH} >jarlog.out 2>&1 </dev/null &
echo "${TARGET_PORT} 로 새로운 서비스를 시작합니다"
exit 0