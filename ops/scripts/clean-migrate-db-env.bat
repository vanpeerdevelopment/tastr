@echo off

pushd %~dp0\..\..
mvn clean compile -pl tastr-infrastructure flyway:clean flyway:migrate -Dtastrenvironment=%1
popd

