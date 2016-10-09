@echo off

pushd %~dp0\..\..
mvn test -pl :tastr-e2e -am -DskipTests -Pstart-tastr
popd

