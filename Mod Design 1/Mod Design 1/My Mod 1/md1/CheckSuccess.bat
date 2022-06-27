@echo off

REM If Block.java is found, install starter files
	if exist ..\mcp\src\minecraft\TROUBLESHOOTING\TSClear.exe (
		exit
	) else (
REM If Block.java NOT found - ask them if they want to try again.
		echo.
		echo.
		echo ================= Message from Youth Digital ====================
		echo -----------------------------------------------------------------
		echo Oh no! It looks like Minecraft Forge did not install successfully...
		goto :askToTryAgain
	)
:askToTryAgain (
	SET /P ANSWER=Would you like to try to and patch the error (Y/N)? 
	if /i {%ANSWER%}=={y} (goto :yes) 
	if /i {%ANSWER%}=={yes} (goto :yes) 
	goto :no

REM If they DO want to try again, remove "mcp" and try install.cmd again	
	:yes 
	cd ..
	call "install.cmd"

REM If they DON'T want to try again - tell them we're sorry and to contact us	
	:no 
	echo.
	echo.
	echo We're very sorry for the trouble! 
	echo Please contact us with the details and we'll get it sorted out!
	echo.
	echo.
	%*pause
)
