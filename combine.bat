@echo off
setlocal enabledelayedexpansion

set outputFile=combined.txt

> "%outputFile%" (
    echo new source list
)

for /r %%f in (*.sql) do (
    echo Adding %%f...
    echo ----- %%f ----- >> "%outputFile%"
    type "%%f" >> "%outputFile%"
    echo. >> "%outputFile%"  
    rem 
)

for /r %%f in (*.yml) do (
    echo Adding %%f...
    echo ----- %%f ----- >> "%outputFile%"
    type "%%f" >> "%outputFile%"
    echo. >> "%outputFile%"  
    rem 
)

for /r %%f in (*.gradle) do (
    echo Adding %%f...
    echo ----- %%f ----- >> "%outputFile%"
    type "%%f" >> "%outputFile%"
    echo. >> "%outputFile%"  
    rem 
)

echo All files combined into %outputFile%.
endlocal
pause
