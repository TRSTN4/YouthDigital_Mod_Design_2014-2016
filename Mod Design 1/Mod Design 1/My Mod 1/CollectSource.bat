REM | Clear the My Mod Source Folder
	rd /s /q "My Mod Source\"

REM | Put Java files, Assets, and Templates in My Mod Source
	xcopy /s /i "mcp\src\minecraft\assets" "My Mod Source\assets" /Y
	xcopy /s /i "mcp\src\minecraft\mymod" "My Mod Source\mymod" /Y
	xcopy /s /i "mcp\src\minecraft\TEMPLATES" "My Mod Source\TEMPLATES" /Y