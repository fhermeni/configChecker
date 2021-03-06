Configuration Checker
===============================
This tools helps at checking for the conformance of configuration
with regards to its specification in the BtrPlace constraint catalog.

Version: 1.0
author: Fabien Hermenier
contact: fabien.hermenier@inria.fr


Archive description
-------------------------------

    $ tree .
    .
    |-- LICENSE.txt -- GPL License
    |-- README.(html|txt) -- software description and usage
    |-- README.txt
    |-- cchecker -- launcher
    \__ jar -- dependencies


Usage
-------------------------------

     $ ./configChecker -h
     Usage: configChecker input_files
     Check the conformance of each of the given files to the configuration EBNF
     Returns 0 if all of the given files are well formed
     Optional flags:
         -v: print version
         -h: print this help


Configuration format
-------------------------------

The following EBNF specifies the format of a configuration:


    ID  :	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;
    ON_ID 	:	ID;
    OFF_ID 	:	'(' ID ')' ;
    PAUSED_ID   :	'!' ID;
    HOST	:	':';
    ENDL	:	'\n';
    WAITINGS	:	'?';

    configuration  : node_decl  (ENDL+ node_decl)* (ENDL+  waitings)? ENDL*;

    waitings:	WAITINGS HOST ON_ID+;

    node_decl:	(elem_off|node_on);

    elem_off:	OFF_ID;
    node_on	:	ON_ID HOST vm*;

    vm	:	ON_ID | OFF_ID | PAUSED_ID;

In addition, every identifier in a same configuration are supposed to be unique.


Copyright
-------------------------------
Copyright (c) 2011 INRIA.