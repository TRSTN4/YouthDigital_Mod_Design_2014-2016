@echo off

if exist ..\mcp (
	::	Backup Current Mod
		call "BackupMod.bat" 2>nul >nul

	:: Warn user of deleting mcp folder if it exists
		echo [ ======== Attention ======== ]
		echo.
		echo This will set up a brand new mod in this folder.
		echo It will overwrite any mod you currently have with a brand new one.
		echo If you have made progress on your mod, you will lose that progress.
		echo.
		pause

	:: Remove mcp folder
		echo.
		echo Removing current modding folder
		rd /s /q "..\mcp\src\minecraft\assets\" 2>nul
		rd /s /q "..\mcp\src\minecraft\mymod\" 2>nul
		echo Complete
		echo.	
)

:: If mcpmod.info is found, that's a good sign, so install starter files
		echo Moving starter files
		xcopy /s /i /e /q "StarterFiles" "../mcp" /Y >nul
	:: Backup Files again to get backup of starter files
		echo Backing up Mod.
		call "BackupMod.bat" 2>nul >nul
	echo.
	echo ( ======== Mod Design Starter Files Installed ======== )
	echo.
	pause
	exit

