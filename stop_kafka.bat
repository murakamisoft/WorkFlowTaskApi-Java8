@echo off
echo Stopping Zookeeper...
call "C:\kafka_2.12-3.5.1\bin\windows\zookeeper-server-stop.bat"
timeout /t 3 > nul
echo Stopping Kafka...
call "C:\kafka_2.12-3.5.1\bin\windows\kafka-server-stop.bat"
echo Kafka and Zookeeper have been stopped.
