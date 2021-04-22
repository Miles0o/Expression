grammar expression;

exp :
      (NUMBER|VAR)                              # Int
    | op=MINUS right=exp                        # NegInt
    | op=L_BKT exp R_BKT                        # Brackets
    | <assoc=right>left=exp op=POW right=exp    # Power
    | left=exp op=(MUL|DIVIDE) right=exp        # MulDiv
    | left=exp op=(MINUS|PLUS) right=exp        # AddSub
    ;

MINUS : '-';
PLUS : '+';
MUL : '*';
POW: '^';
DIVIDE : '/';
L_BKT : '(';
R_BKT : ')';

NUMBER : ([0]|([1-9]([0-9])*));
VAR : [a-zA-Z];
WS : (' ' | '\t' | '\n') -> skip;