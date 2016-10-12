@echo off

pushd %~dp0\..\..
mvn initialize -PdbInit -pl :tastr-infrastructure
popd

