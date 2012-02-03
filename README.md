Configuration Checker
===============================

The configuration checker (CChecker) helps at checking for the conformance of configuration with regards to its specification in the BtrPlace constraint catalog.

Maintainer: Fabien Hermenier - fabien.hermenier@inria.fr

Building CChecker
-------------------------------

CChecker is a Java application that relies on [maven 3](http://maven.apache.org) to manage its build. So a maven environment is supposed to be installed and configured on the local machine. Below are the basics to build CChecker from sources:

* To compile the sources
  configChecker $ mvn compile
* To perform the unit tests
  configChecker $ mvn test
* To generate the standalone distribution
  configChecker $ mvn assembly:assembly
* To package the jar
  configCheckr $ mvn package. 


Code organization
------------------------------

* README.md - this file
* CHANGES.txt - the versions history
* pom.xml - project configuration file for maven
* src - source files
* target - output directory


Copyrigth 
-------------------------------

Copyright 2012 INRIA, Inc.

