program Fibonacci
    implicit none
    integer :: n, i
    real :: fib_prev, fib_current, result

    n = 100
    fib_prev = 0
    fib_current = 1

    do i = 1, n
        if (i <= 1) then
            result = i
        else
            result = fib_prev + fib_current
            fib_prev = fib_current
            fib_current = result
        end if
    end do
    print *, "The result is", result

end program Fibonacci
