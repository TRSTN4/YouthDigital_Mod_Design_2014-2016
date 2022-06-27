REM | Clear Mod Export and TS Folders
	rd /s /q "My Mod Export\"
	rd /s /q "mcp\src\minecraft\mytroublemod\"
	rd /s /q "mcp\src\minecraft\assets\troublemod\"

REM | Run Recompile and Reobfuscate Scripts
	cd mcp\
	call recompile.bat
	call reobfuscate.bat

REM | Wait a second...
	echo waiting for file transfer...
	ping 1.1.1.1 -n 1 -w 1000 > nul

REM | Go up one directory and put ReObf and Assets in My Mod Export
	cd ..
	xcopy /s /i "mcp\src\minecraft\assets" "My Mod Export\assets" /Y
	xcopy /s /i "mcp\reobf\minecraft" "My Mod Export" /Y
