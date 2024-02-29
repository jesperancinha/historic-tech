program FactorialTailRecursionExample
  implicit none

  integer :: n, result

  n = 5
  result = factorial(n)
  print *, "Factorial of", n, "is", result

contains

  recursive function factorial(n) result(res)
    integer, intent(in) :: n
    integer :: res

    if (n == 0) then
      res = 1
    else
      res = n * factorial(n - 1)
    end if
  end function factorial

end program FactorialTailRecursionExample
