`docker exec -it quarkus-studies_kafka_1 bash`

`kafka-topics.sh \
    --bootstrap-server localhost:9092 
    --create \
    --topic users \
    --partitions 1 \
    --replication-factor 1`


`kafka-console-producer.sh \
    --broker-list localhost:9092 \
    --topic users`