#!/bin/bash

echo "Compiling java files..."
javac -target 1.8 `find . -name "*.java"`

echo "Creating jar files..."
jar cfm release/mine.jar manifests/MANIFEST.mf\
        `find . -name "*.class"`

echo "Creating jar source files..."
jar cfm release/mine-source.jar manifests/MANIFEST.mf\
         build.sh\
        `find . -name "*.java"`

echo "Deleting class files..."
rm `find . -name "*.class"`

echo "Export to target path..."
sudo cp release/mine.jar /opt/
sudo cp mine.sh /opt/
sudo chmod +x /opt/mine.sh