grammar AsciiConfig;

options {
	language = Java;
	output = AST;
}


@lexer::members {
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

@parser::members {
    private Set<String> ids = new HashSet<String>();
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
    
        public void checkForDuplicate(Token t) {
    	if (!ids.add(t.getText())) {
    		errors.add(new StringBuilder("line").append(t.getLine()).append(':')
    		.append(t.getCharPositionInLine()).append(" - identifier '").append(t.getText()).append("' already used").toString());
	}
    }

}

@parser::header {
package btrpcc.configChecker;
	
import java.util.List;	
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
}

@lexer::header {
package btrpcc.configChecker;

import java.util.List;	
import java.util.LinkedList;


}

fragment ID  :	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;
ON_ID 	:	ID;
OFF_ID 	:	'(' ID ')'  {setText($ID.text);};
PAUSED_ID   :	'!' ID {setText($ID.text);};
HOST	:	':';
ENDL	:	'\n';
WAITINGS	:	'?';

configuration returns [List<String> errors] 
	: {$errors = this.errors; this.errors.clear(); ids.clear();} node_decl  (ENDL+ node_decl)* (ENDL+  waitings)? ENDL*;

waitings:	WAITINGS HOST {List<String> elems = new LinkedList<String>();} x+=ON_ID+
	{
	for (Object  t : $x) {
		checkForDuplicate((Token)t);
	}
	}
;
	
node_decl:	(elem_off|node_on);

elem_off:	id=OFF_ID  {checkForDuplicate($id);};
node_on	:	id=ON_ID HOST vm* {checkForDuplicate($id);};

vm	:	v1=ON_ID{checkForDuplicate($v1);}
	 	|v2=OFF_ID{checkForDuplicate($v2);}
		|v3=PAUSED_ID {checkForDuplicate($v3);}
		;			

WS  :   ( ' '
        | '\t'
        | '\r'
        ) {$channel=HIDDEN;}
    ;
    

