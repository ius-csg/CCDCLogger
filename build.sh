#!/bin/bash
javac -classpath pircbot.jar: ./src/org/iuscsg/ccdclogger/*.java
cd src 
jar cvfe ../CCDCLogger.jar org.iuscsg.ccdclogger.Main org/iuscsg/ccdclogger/*.class
java -classpath ../pircbot.jar:CCDCLogger.jar org.iuscsg.ccdclogger.Main
