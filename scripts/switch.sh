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

echo ">> \$service_url 에 http://127.0.0.1:${TARGET_PORT} 를 연결합니다"
echo "set $service_url http://127.0.0.1:${TARGET_PORT};" | tee /home/ubuntu/service_url.inc
echo ">> 현재 Nginx proxies to ${TARGET_PORT}"

sudo service nginx reload

echo ">> Nginx Reload"
