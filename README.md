# test-work
## Запуск
* старт Consul на докере  
    `docker run --name=consul_docker -d -p 8500:8500 -p 8600:8600/udp consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0`
* запуск из Idea модулей
    * database-api
    * kladr-api
    * my-gateway
