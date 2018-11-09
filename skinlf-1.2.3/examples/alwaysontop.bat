@echo off
echo If you are using JDK1.4 and it complains about jawt.dll, use jdk.home\jre\bin\java instead of jdk.home\bin\java
set OLDPATH=%PATH%
set PATH=%PATH%;..\lib
java -classpath ..\lib\nativeskin.jar;demo.jar alwaysontop
set PATH=%OLDPATH%


