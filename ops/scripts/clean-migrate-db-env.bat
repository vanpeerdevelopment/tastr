@echo off

pushd %~dp0\..\..
mvn clean compile -pl tastr-infrastructure flyway:clean flyway:migrate -Dprojectbeheer.environment=%1
popd

