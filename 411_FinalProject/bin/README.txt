In order to run the code enter Start.java inst.txt data.txt output.txt 
the code is sensitive to spaces character returns and symbols 
and example file that will work right is 

LI R1, 100h # addr = 0x100;
LW R3, 0(R1) # boundary = *addr;
LI R5, 1 # i = 1;
LI R7, 0h # sum = 0;
LI R6, 1h # factorial = 0x01;
LOOP: MULT R6, R5, R6 # factorial *= I;
ADD R7, R7, R6 # sum += factorial;
ADDI R5, R5, 1h # i++;
BNE R5, R3, LOOP
And R7, R7, R7
Or R7, R7, R7
Andi R7, 1
Ori R7, 0
sll r7, r7, r7
srl r7, r7, r7
slli r7, r7, 1
srli r7, r7 ,1
HLT
HLT 