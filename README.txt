Configuration Checker
===============================
This tools helps at checking for the conformance of configuration
with regards to its specification in the BtrPlace constraint catalog.

Version: 1.0-SNAPSHOT
author: Fabien Hermenier
contact: fabien.hermenier@inria.fr


Archive description
-------------------------------



Usage
-------------------------------

    configChecker$ ./cchecker


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


Copyright
-------------------------------
Copyright (c) 2011 INRIA.