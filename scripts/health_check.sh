#!/bin/bash

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo ">> 현재 운영중인 서비스의 PORT: ${CURRENT_PORT}"

if [ ${CURRENT_PORT} -eq 8081 ]; then
  TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq 8082 ]; then
  TARGET_PORT=8081
else
  echo ">> Nginx 에 연결된 서비스가 없습니다"
  exit 1
fi

echo ">> http://localhost:${TARGET_PORT} 의 상태를 체크합니다"

for RETRY_COUNT in 1 2 3 4 5 6 7 8 9 10; do
  echo ">> ${RETRY_COUNT} trying ..."
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://127.0.0.1:${TARGET_PORT}/status/uni/health)

  if [ ${RESPONSE_CODE} -eq 200 ]; then
    echo ">> 서비스가 성공적으로 작동 중 입니다"
    exit 0
  elif [ ${RETRY_COUNT} -eq 10 ]; then
    echo ">> 서비스가 작동중이지 않습니다"
    exit 1
  fi
  sleep 10
done
