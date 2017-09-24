REM Get the path to the script and trim to get the directory.
@echo off
echo Setting path to current directory to:
SET pathtome=%~dp0
echo %pathtome%

echo build asdocs
call asdoc ^
-doc-sources %pathtome%..\src ^
-source-path %pathtome%..\src ^
-window-title "Tua Rua GoogleMapsANE by Tua Rua Ltd." ^
-main-title "GoogleMapsANE Documentation" ^
-footer "Copyright Tua Rua Ltd. 2017" ^
-output %pathtome%..\docs\asdocs ^
-lenient ^
-library-path+=D:\dev\sdks\AIR\AIRSDK_27\frameworks\libs\air\airglobal.swc


call asdoc ^
-doc-sources %pathtome%..\src ^
-source-path %pathtome%..\src ^
-window-title "GoogleMapsANE by Tua Rua Ltd." ^
-main-title "GoogleMapsANE Documentation" ^
-footer "Copyright Tua Rua Ltd. 2017" ^
-output %pathtome%..\docs\tmp ^
-lenient -keep-xml=true -skip-xsl=true ^
-library-path+=D:\dev\sdks\AIR\AIRSDK_27\frameworks\libs\air\airglobal.swc


call DEL /F /S /Q /A %pathtome%..\docs\tmp\tempdita\ASDoc_Config.xml
call DEL /F /S /Q /A %pathtome%..\docs\tmp\tempdita\overviews.xml

call rd /S /Q %pathtome%docs
move %pathtome%..\docs\tmp\tempdita %pathtome%docs
call rd /S /Q %pathtome%..\docs\tmp