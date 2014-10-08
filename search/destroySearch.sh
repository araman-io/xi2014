#!/bin/bash
echo "Stoping service if any"
sudo docker stop searchmicro
echo "Deleting the container searchmicro"
sudo docker rm searchmicro
echo "Deleting Image : microservice" 
sudo docker rmi searchservice

