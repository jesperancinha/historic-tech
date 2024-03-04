PROGRAM RECURSIVE_FIBONACCI
    INTEGER N

    N = 100

    DO I = 0, N - 1
        CALL fibonacci(I, Result)
    END DO
    WRITE(*, *) Result

CONTAINS

    RECURSIVE SUBROUTINE fibonacci(N, Result)
        INTEGER N
        REAL Result

        IF (N <= 1) THEN
            Result = REAL(N)
        ELSE
            CALL fibonacci(N - 1, Result1)
            CALL fibonacci(N - 2, Result2)
            Result = Result1 + Result2
        END IF

    END SUBROUTINE fibonacci

END
