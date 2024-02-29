       IDENTIFICATION DIVISION.
       PROGRAM-ID. SAMPLE.

       DATA DIVISION.
       WORKING-STORAGE SECTION.

         77 n PIC 9.
         77 i PIC 9.
         77 tnumber PIC XX.
         77 fact PIC 9(18) comp.
         77 result PIC 9(9).

       PROCEDURE DIVISION.
         MOVE 0 to i
         MOVE 1 to fact
         MOVE 5 to n
         PERFORM until i GREATER THAN n
           MOVE i to tnumber
           MOVE fact to result
           ADD 1 to i
           MULTIPLY i by fact
             ON SIZE ERROR DISPLAY "result overflow!"
           END-MULTIPLY
         END-PERFORM.
         DISPLAY "Factorial of " tnumber "is " result
         STOP RUN.
