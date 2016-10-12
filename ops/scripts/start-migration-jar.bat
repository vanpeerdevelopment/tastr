@echo off

pushd %~dp0\..\..\tastr-infrastructure\target
java -jar tastr-migration.jar jdbc:postgresql://localhost:5432/tastr tastr tastr public
popd
