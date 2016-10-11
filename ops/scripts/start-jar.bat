@echo off

pushd %~dp0\..\..\tastr-fatjar\target
java -jar tastr.jar --db.url=jdbc:postgresql://localhost:5432/tastr --db.username=tastr --db.password=tastr
popd
