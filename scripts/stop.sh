#!/usr/bin/env bash

DEFAULT_PATH=/home/ubuntu/uni-sparkle-deploy/uni-sparkle

source ${DEFAULT_PATH}/scripts/profile.sh

TARGET_PORT = $(find_target_port)

TARGET_PID=$(lsof -Fp -i TCP:${TARGET_PORT} | grep -Po 'p[0-9]+' | grep -Po '[0-9]+')

if [ ! -z ${TARGET_PID} ]; then
    echo ">> ${TARGET_PORT}에서 실행중인 서비스를 종료합니다"
    sudo kill ${TARGET_PID}
fi