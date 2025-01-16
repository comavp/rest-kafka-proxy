`docker build -t rest-kafka-proxy:1.0.0 .`
`docker build -t kafka-consumer:1.0.0 .`
`docker build -t rest-server:1.0.0 .`

`docker-compose -f docker-compose.yml up -d`