# Run Zookeeper
```bash
docker-compose -f common.yml -f zookeeper.yml up -d
# To check zookeeper up and running run following
echo ruok | nc localhost 2181
# the output should bd imok
imok
# Then run kafka cluster
docker-compose -f common.yml -f kafka_cluster.yml up -d

# Now run the init kafka command to create topics 
docker-compose -f common.yml -f init_kafka.yml up

```