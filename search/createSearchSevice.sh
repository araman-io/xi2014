#!/bin/bash
echo "creating package"
mvn clean package -DskipTests
echo "creating new docker image"
sudo docker build -t searchservice .
echo "deploying container and starting app" 
sudo docker run -p 4567:4567 -p 8888:8888 -d --name searchmicro searchservice

