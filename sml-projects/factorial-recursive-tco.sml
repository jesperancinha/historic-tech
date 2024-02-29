fun factorial_helper(n, acc) =
    if n = 0 then acc
    else factorial_helper(n - 1, n * acc);

fun factorial(n) =
    factorial_helper(n, 1);
