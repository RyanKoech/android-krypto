#!/bin/bash

# Define the filenames
source_file="gradle.properties.example"
destination_file="gradle.properties"

# Copy the contents of the source file to the destination file
cp "$source_file" "$destination_file"

echo "Contents copied from '$source_file' to '$destination_file'."
