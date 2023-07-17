#!/bin/bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle

source ${DEFAULT_PATH}/script/profile.sh

TARGET_PORT = $(find_target_port)

echo ">> \$service_url 에 http://127.0.0.1:${TARGET_PORT} 를 연결합니다"
echo "set \$service_url http://127.0.0.1:${TARGET_PORT};" | tee /home/ubuntu/service_url.inc
echo ">> 현재 Nginx proxies to ${TARGET_PORT}"

sudo service nginx reload

echo ">> Nginx Reload"