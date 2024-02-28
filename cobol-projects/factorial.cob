       IDENTIFICATION DIVISION.
       PROGRAM-ID. SAMPLE.

       DATA DIVISION.
       WORKING-STORAGE SECTION.

         77 n pic 99.
         77 i pic 99.
         77 tnumber pic XX.
         77 fact pic 9(18) comp.
         77 factst pic X(18).

       PROCEDURE DIVISION.
         MOVE 0 to i
         MOVE 1 to fact
         MOVE 20 to n
         PERFORM until i greater than n
           MOVE i to tnumber
           MOVE fact to factst
           DISPLAY "Factorial of " tnumber " or " tnumber "! = " factst
           ADD 1 to i
           MULTIPLY i by fact
             ON SIZE ERROR DISPLAY "result overflow!"
           END-MULTIPLY
         END-PERFORM.
         STOP RUN.
