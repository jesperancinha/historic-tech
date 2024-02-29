program FactorialIterationExample
    implicit none

    integer :: n, i
    integer(kind=8) :: result

    n = 5

    result = 1

    do i = 1, n
        result = result * i
    end do

    print *, "Factorial of", n, "is", result

end program FactorialIterationExample
