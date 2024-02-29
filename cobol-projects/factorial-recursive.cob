       IDENTIFICATION DIVISION.
       PROGRAM-ID. SAMPLE.

       DATA DIVISION.
       WORKING-STORAGE SECTION.

         77 n PIC 99.
         77 i PIC 99.
         77 tnumber PIC XX.
         77 fact PIC 9(18) comp.
         77 factst PIC X(18).

       PROCEDURE DIVISION.
         MOVE 0 to i
         MOVE 1 to fact
         MOVE 20 to n
         PERFORM until i GREATER THAN n
           MOVE i to tnumber
           MOVE fact to factst
           DISPLAY "Factorial of " tnumber " or " tnumber "! = " factst
           ADD 1 to i
           MULTIPLY i by fact
             ON SIZE ERROR DISPLAY "result overflow!"
           END-MULTIPLY
         END-PERFORM.
         STOP RUN.
