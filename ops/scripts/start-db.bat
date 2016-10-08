@echo off

docker build -t tastr/db %~dp0\..\db\
docker rm -vf tastr_db_local
docker run -d -p 5432:5432 --name tastr_db_local tastr/db
