#!/bin/bash
javac -classpath ../pircbot.jar: org/iuscsg/ccdclogger/*.java
jar cvfe ../CCDCLogger.jar org.iuscsg.ccdclogger.Main org/iuscsg/ccdclogger/*.class
java -classpath ../pircbot.jar:../CCDCLogger.jar org.iuscsg.ccdclogger.Main
