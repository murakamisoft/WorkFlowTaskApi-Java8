@echo off
REM Zookeeperサーバーを起動する
start cmd /k "cd C:\kafka_2.12-3.5.1\bin\windows && zookeeper-server-start.bat ..\..\config\zookeeper.properties"

REM 3秒待機
timeout /t 3 /nobreak

REM Kafkaサーバーを起動する
start cmd /k "cd C:\kafka_2.12-3.5.1\bin\windows && kafka-server-start.bat ..\..\config\server.properties"

REM 3秒待機
timeout /t 3 /nobreak

REM Kafkaのプロデューサーを起動する
start cmd /k "cd C:\kafka_2.12-3.5.1\bin\windows && kafka-console-producer.bat --topic task-events --bootstrap-server localhost:9092"
