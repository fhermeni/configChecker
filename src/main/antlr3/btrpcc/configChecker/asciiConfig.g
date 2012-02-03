grammar AsciiConfig;

options {
	language = Java;
	output = AST;
}


@members {
    private List<String> errors = new LinkedList<String>();

    public void displayRecognitionError(String[] tokenNames,
                                        RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        errors.add(hdr + " " + msg);
    }
    public List<String> getErrors() {
        return errors;
    }
}

@parser::header {
package btrpcc.configChecker;
	
import java.util.List;	
import java.util.LinkedList;
}

@lexer::header {
package btrpcc.configChecker;

import java.util.List;	
import java.util.LinkedList;


}

fragment ID  :	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;
ON_ID 	:	ID;
OFF_ID 	:	'(' ID ')' ;
PAUSED_ID   :	'!' ID;
HOST	:	':';
ENDL	:	'\n';
WAITINGS	:	'?';

configuration returns [List<String> errors] 
	: {$errors = this.errors; this.errors.clear();} node_decl  (ENDL+ node_decl)* (ENDL+  waitings)? ENDL*;

waitings:	WAITINGS HOST ON_ID+;
	
node_decl:	(elem_off|node_on);

elem_off:	OFF_ID;
node_on	:	ON_ID HOST vm*;

vm	:	ON_ID | OFF_ID | PAUSED_ID;
	
			

WS  :   ( ' '
        | '\t'
        | '\r'
        ) {$channel=HIDDEN;}
    ;
    

