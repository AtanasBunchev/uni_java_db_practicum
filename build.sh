#!/bin/sh

cd src
javac *.java
cd ..
mv src/*.class bin/

