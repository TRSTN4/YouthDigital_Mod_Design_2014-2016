 @echo off

:: Remove TS Files
	rd /s /q "..\mcp\src\minecraft\mytroublemod\" >nul 2>nul
	
:: Run Backup (just in case)
	echo Backing up Mod
	call "BackupMod.bat" >nul

:: Create temporary backup
	echo Creating temporary Backup
	xcopy /s /i "..\mcp\src\minecraft\assets" "modtmp\assets" /Y >nul
	xcopy /s /i "..\mcp\src\minecraft\mymod" "modtmp\mymod" /Y >nul

:: Copy all srcbackup files on top of current source
	echo.
	echo Replacing Minecraft Src
	xcopy /s /i /q "SrcBackup" "..\mcp\src\minecraft"  /Y  <nul

:: Copy Starter Files in
	echo Moving starter files
	xcopy /s /i /e /q "StarterFiles" "..\mcp" /Y >nul
	
:: Replace Source Files in their Mod	
	echo Replacing Mod Files
	xcopy /s /i /q "modtmp\assets" "..\mcp\src\minecraft\assets" /Y >nul
	xcopy /s /i /q "modtmp\mymod" "..\mcp\src\minecraft\mymod"  /Y >nul

:: Remove ForgeLang Folder
	echo Cleaning folder
	rd /s /q "..\mcp\src\minecraft\assets\forge\" 2>nul	
	
:: Remove modtmp Files
	rd /s /q "modtmp\" 2>nul >nul
	
echo Your mod has been saved
pause
exit
