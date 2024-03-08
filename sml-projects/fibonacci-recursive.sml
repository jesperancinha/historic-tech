print ("Fibonacci of 100: " ^ IntInf.toString result ^ "\n");

fun fibonacci n =
    if n < 2 then n
    else fibonacci (n - 1) + fibonacci (n - 2);