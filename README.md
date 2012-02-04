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

Branching model
------------------------------

This repository use [this branching model](http://nvie.com/posts/a-successful-git-branching-model/). In practice

* the _master_ branch is dedicated to _production-ready_ code
* the _develop_ branch is the _integration_ branch for the next release

Other branches may either be either typed as _feature_ branches, _release_ branches, or _hotfix_ branches. _feature_ branches branch off from the _develop_ branch. They bring the new material to the application. _release_ branches branch from the _develop_ branch to prepare the next release. At this step, it is a quick branching to prepare a few files for the next version. Typically, updating the versions hard-coded in some files. _release_ branches are merged with the _develop_ branch and the _master_ branch. Finally, _hotfix_ branches appliced critical fixes for the _master_ branch. 

All the tags are performed directly on the _master_ branch.


Copyright 
-------------------------------

Copyright 2012 INRIA, Inc.

