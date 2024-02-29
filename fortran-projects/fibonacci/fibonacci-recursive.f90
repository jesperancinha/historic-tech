program FibonacciTailRecursive
    implicit none
    integer :: n, i
    real :: result

    n = 100
    result = fibonacci(n)
    print *, "The result is", result

contains

 recursive function fibonacci(n) result(fib)
    integer, intent(in) :: n
    integer :: fib

    if (n == 0) then
            fib = 0
    else if (n == 1) then
            fib = 1
    else
            fib = fibonacci(n - 1) + fibonacci(n - 2)
    end if
  end function fibonacci

end program FibonacciTailRecursive
