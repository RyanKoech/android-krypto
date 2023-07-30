@echo off

set "source_file=gradle.properties.example"
set "destination_file=gradle.properties"

REM Copy the contents of the source file to the destination file
copy /Y "%source_file%" "%destination_file%" > nul

echo Contents copied from '%source_file%' to '%destination_file%'.
