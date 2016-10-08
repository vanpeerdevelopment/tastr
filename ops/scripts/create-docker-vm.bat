@echo off

docker-machine create --driver virtualbox --virtualbox-memory 2048 docker-vm

VBoxManage controlvm docker-vm natpf1 "postgres,tcp,127.0.0.1,5432,,5432"

docker-machine stop docker-vm
VBoxManage modifyvm docker-vm --natdnshostresolver1 on
docker-machine start docker-vm

call set-docker-environment
