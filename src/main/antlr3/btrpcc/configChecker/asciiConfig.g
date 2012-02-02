grammar AsciiConfig;

options {
	language = Java;
	output = AST;
}


@parser::header {
package btrpcc.configChecker;
	
}

@lexer::header {
package btrpcc.configChecker;


}

ID  :	('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')*;


configuration
	: node_decl+ waitings?;

waitings:	'?' ':' ID*;	
node_decl
	:	elem_off|node_on;

elem_off:	'(' ID ')';
node_on	:	ID ':' vm*;

vm	:	vm_run |vm_paused|elem_off;
vm_run	:	ID;
vm_paused
	:	'!' ID;
	

	
		
	

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

