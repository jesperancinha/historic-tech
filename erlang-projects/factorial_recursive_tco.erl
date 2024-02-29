-module(factorial_recursive_tco).
-export([factorial/1]).

factorial(N) when N >= 0 ->
    factorial(N, 1).

factorial(0, Acc) ->
    Acc;
factorial(N, Acc) when N > 0 ->
    factorial(N - 1, N * Acc).
