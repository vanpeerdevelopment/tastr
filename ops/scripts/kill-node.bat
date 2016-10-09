@echo off

pushd %~dp0\..\..\tastr-ui\
Taskkill /F /IM node.exe
popd

