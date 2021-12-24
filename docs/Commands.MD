#### Run docker
`docker exec -it quarkus-studies_kafka_1 bash`
<br><br>
#### Create "user" kafka topic
`kafka-topics.sh \
    --bootstrap-server localhost:9092 
    --create \
    --topic users \
    --partitions 1 \
    --replication-factor 1`
    <br><br>
#### Produce "user" topic events
`kafka-console-producer.sh \
    --broker-list localhost:9092 \
    --topic users`
<br><br>
#### Consume "user" topic events
`kafka-console-consumer.sh \
    --bootstrap-server localhost:9092 \
    --topic users`
