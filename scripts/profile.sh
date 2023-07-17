#!/usr/bin/env bash

function find_target_port() {
  CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
  TARGET_PORT=8081

  if [ ${CURRENT_PORT} -eq "8081" ]; then
    TARGET_PORT=8082
  elif [ ${CURRENT_PORT} -eq "8082" ]; then
    TARGET_PORT=8081
  else
    TARGET_PORT=8081
  fi
  echo "${TARGET_PORT}"
}
