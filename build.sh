#!/bin/sh

cd src
javac -classpath .:../lib/lib/h2.jar *.java
cd ..
mv src/*.class bin/

