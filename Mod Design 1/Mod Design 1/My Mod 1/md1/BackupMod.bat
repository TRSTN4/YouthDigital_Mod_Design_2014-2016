@echo OFF
:: Create Timestamp
set TIMESTAMP=%DATE:~10,4%-%DATE:~4,2%-%DATE:~7,2%-%TIME:~0,2%-%TIME:~3,2%-%TIME:~6,2%

@echo TIMESTAMP=%TIMESTAMP%

:: Create a new directory
md "ModBackups\%1\%TIMESTAMP%"

:: Copy all source files to "ModBackups" folder in same Directory
	xcopy /s /i /q "..\mcp\src\minecraft\assets" "ModBackups\%TIMESTAMP%\assets\" /Y >nul
	xcopy /s /i /q "..\mcp\src\minecraft\mymod" "ModBackups\%TIMESTAMP%\mymod\" /Y >nul

:: Copy all source files to "Appdata" folder in same Directory
	xcopy /s /i /q "..\mcp\src\minecraft\assets" "%appdata%\Youth Digital\ModBackups\%TIMESTAMP%\assets\" /Y >nul
	xcopy /s /i /q "..\mcp\src\minecraft\mymod" "%appdata%\Youth Digital\ModBackups\%TIMESTAMP%\mymod\" /Y >nul