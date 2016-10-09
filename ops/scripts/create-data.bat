@echo off

pushd %~dp0\..\..
mvn test -pl :tastr-e2e -am -DskipTests -Psetup-test-data
popd

