docker run --name=consul_docker -d -p 8500:8500 -p 8600:8600/udp consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0
docker run --name database -p 8001:8001 -d docker-database-api
docker run --name database -p 8002:8002 -d docker-kladr-api
docker run --name my-gateway -p 8080:8080 -d docker-my-gateway