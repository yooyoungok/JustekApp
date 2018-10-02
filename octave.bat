@echo off

set OCT_HOME=C:%\Octave%\Octave-4.2.1
set "PATH=%OCT_HOME%\bin;%PATH%"

set SCRIPTS_DIR=%\octave%\
start octave-cli.exe --eval "cd C:\Users\cty\eclipse-workspace\JustekApp ; fact; 