#!/bin/bash
echo "Stop container if any"
sudo docker stop listingmicro
echo "Deleting the container listingmicro"
sudo docker rm listingmicro
echo "Deleting Image : listingservice" 
sudo docker rmi listingservice

