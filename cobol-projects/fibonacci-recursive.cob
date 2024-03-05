IDENTIFICATION DIVISION.
PROGRAM-ID. FibonacciRecursive.

DATA DIVISION.
WORKING-STORAGE SECTION.
01  F1                   PIC 9(21)V9(1) VALUE 0.0.
01  F2                   PIC 9(21)V9(1) VALUE 1.0.
01  FIB                  PIC 9(21)V9(1).
01  N                    PIC 9(5) VALUE 100.
01  I                    PIC 9(5) VALUE 0.

PROCEDURE DIVISION.
    MOVE N TO I
    CALL 'FIBONACCI'.
    DISPLAY "Fibonacci of " I " is " F1.
    STOP RUN.

    ENTRY 'FIBONACCI'.
    IF N = 0 THEN
        EXIT PROGRAM
    ELSE
       COMPUTE FIB = F1 + F2
       COMPUTE F1 = F2
       COMPUTE F2 = FIB
       SUBTRACT 1 FROM N
       CALL 'FIBONACCI'
    END-IF.
