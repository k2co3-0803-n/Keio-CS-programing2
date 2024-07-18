#!/bin/bash

javac *.java
CLASSPATH=".:sqlite-jdbc-3.46.0.0.jar:slf4j-api-1.7.32.jar:slf4j-simple-1.7.32.jar"
java -cp $CLASSPATH MyApp


