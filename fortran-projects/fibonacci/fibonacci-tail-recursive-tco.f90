program FibonacciTailRecursive
    implicit none

    integer :: n
    real :: result

    n = 100
    result = fibonacci(n)
    print *, "The result is", result

contains

    recursive function fibonacci(n) result(fib)
        integer, intent(in) :: n
        real :: fib

        if (n <= 1) then
            fib = n
        else
            fib = fibonacci_helper(n, 0.0, 1.0)
        end if
    end function fibonacci

    recursive function fibonacci_helper(n, a, b) result(fib)
        integer, intent(in) :: n
        real :: fib, a, b

        if (n == 0) then
            fib = a
        else if (n == 1) then
            fib = b
        else
            fib = fibonacci_helper(n - 1, b, a + b)
        end if
    end function fibonacci_helper

end program FibonacciTailRecursive
