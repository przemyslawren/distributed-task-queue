#!/bin/zsh
set -euo pipefail
BOOTSTRAP=localhost:9092

createTopic() {
  local TOPIC=$1
  local PARTITIONS=$2
  local REPLICATION=$3

  echo "Creating topic '${TOPIC}'"
  docker exec -it broker \
    /opt/kafka/bin/kafka-topics.sh \
      --create \
      --bootstrap-server "${BOOTSTRAP}" \
      --topic "${TOPIC}" \
      --partitions "${PARTITIONS}" \
      --replication-factor "${REPLICATION}"
}

createTopic job 3 1