@echo off

FOR /f "tokens=*" %%i IN ('docker-machine env --shell cmd docker-vm') DO %%i
