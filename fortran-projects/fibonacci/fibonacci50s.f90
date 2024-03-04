PROGRAM FIBONACCI
    INTEGER N, I
    REAL F1, F2, FIB

    N = 100
    F1 = 0
    F2 = 1

    DO I = 2, N
        FIB = F1 + F2
        F1 = F2
        F2 = FIB
    END DO
    WRITE(*,*) FIB

END
