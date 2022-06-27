@echo off

if exist mcp (

	:: Warn user of deleting mcp folder if it exists
		echo [ ======== Attention ======== ]
		echo.
		echo This patch will reinstall Forge
		echo It will back up and replace your mod
		echo If you have already worked on your mod, contact us before running this
		echo.
		pause

	::	Backup Current Mod
		cd md1
		echo Backing up current mod
		call "BackupMod.bat" 2>nul >nul
		cd ..
		
	:: Remove mcp folder
		echo Removing current modding folder
		echo [Don't worry we made a backup just in case...]
		rd /s /q "mcp\" 2>nul
		rd /s /q "mcp\" 2>nul
		echo Complete
		echo.	
)

:: Run YD Download libraries
	.\fml\python\python_fml yd\scripts\yd.py downloadminecraft
	echo.
:: Run Forge and Pause
	.\fml\python\python_fml install.py 
	echo.

:: If mcpmod.info is found, that's a good sign, so install starter files
if exist mcp\src\minecraft\mcpmod.info (
		echo. 
		echo [ ======== Finalizing your Youth Digital Files ======== ]
	:: Create Backup of vanilla Source
		echo    Creating backup of src files 
		xcopy /s /i /q /e "mcp\src\minecraft" "md1\SrcBackup" /Y >nul
	:: Copy mcptemp starter files into mcp real
		echo    Moving starter files
		xcopy /s /i /e /q "md1\StarterFiles" "mcp" /Y >nul
	:: Remove ForgeLang Folder
		echo    Cleaning folder
		rd /s /q "mcp\src\minecraft\assets\forge\" 2>nul
	:: Backup Files again to get backup of starter files
		echo    Creating first backup
		cd md1
		echo    Backing up Mod
		call "BackupMod.bat" 2>nul >nul
		cd ..
	echo.
	echo [ ======== Mod Design Files Successfully Installed ======== ]
	echo.
	pause
	exit
)
:: If mcpmod.info NOT found - ask them if they want to try again.
 else (
	echo.
	echo [ ======== Something Went Wrong ======== ]
	echo -----------------------------------------------------------------
	echo Oh no! It looks like Minecraft Forge did not install successfully...
	goto :askToTryAgain
)
:: Ask user if they'd like to try again
	:askToTryAgain (
	SET /P ANSWER=Would you like to try to install it again (Y/N)? 
	if /i {%ANSWER%}=={y} (goto :yes) 
	if /i {%ANSWER%}=={yes} (goto :yes) 
	goto :no

:: If they DO want to try again, ::ove "mcp" and try install.cmd again	
	:yes 
	rd /s /q "mcp\" 2>nul
	echo.
	call "install.cmd"

:: If they DON'T want to try again - tell them we're sorry and to contact us	
	:no 
	echo.
	echo We're very sorry for the trouble! 
	echo Please contact us with the details and we'll get it sorted out!
	echo.
	%*pause
)