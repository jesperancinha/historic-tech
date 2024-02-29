program FactorialTailRecursionExample
  implicit none

  integer :: n, result

  ! Prompt the user for input
  print *, "For which number do you want to calculate the factorial:"
  read *, n

  ! Call the factorial function and print the result
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
