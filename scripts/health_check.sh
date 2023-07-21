#!/bin/bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

if [ ${CURRENT_PORT} -eq "8081" ]; then
  TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq "8082" ]; then
  TARGET_PORT=8081
else
  TARGET_PORT=8081
fi
echo ">> 현재 운영중인 서비스의 PORT: ${CURRENT_PORT}"

echo ">> http://localhost:${TARGET_PORT} 의 상태를 체크합니다"

for RETRY_COUNT in 1 2 3 4 5 6 7 8 9 10; do
  echo ">> ${RETRY_COUNT} trying ..."
  RESPONSE=$(curl -s http://127.0.0.1:${CURRENT_PORT}/status/uni/health)
  UP_COUNT=$(echo $RESPONSE | grep 'UP' | wc -1)

  if [ ${UP_COUNT} -ge 1 ]; then
    echo ">> 서비스가 성공적으로 작동 중 입니다"
    break
  else
    echo ">> ${RESPONSE}"
  fi
  if [ ${RETRY_COUNT} -eq 10 ]; then
    echo ">> 서비스가 작동중이지 않습니다"
    exit 1
  fi
  sleep 10
done

sleep 10
echo ">> \$service_url 에 http://127.0.0.1:${TARGET_PORT} 를 연결합니다"
echo "set \$service_url http://127.0.0.1:${TARGET_PORT};" | tee /home/ubuntu/service_url.inc
echo ">> 현재 Nginx proxies to ${TARGET_PORT}"

sudo service nginx reload

echo ">> Nginx Reload"
