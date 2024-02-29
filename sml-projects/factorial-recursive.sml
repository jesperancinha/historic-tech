fun factorial(n : int) =
    let
        fun factorial_helper(n, acc) =
            if n = 0 then acc
            else factorial_helper(n - 1, n * acc)
    in
        factorial_helper(n, 1)
    end;