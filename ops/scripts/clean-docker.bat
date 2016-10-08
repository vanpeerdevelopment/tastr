@echo off

FOR /f "tokens=*" %%c IN ('docker ps -qa') do docker rm -vf %%c
FOR /f "tokens=*" %%i IN ('docker images -qa') do docker rmi -f %%i