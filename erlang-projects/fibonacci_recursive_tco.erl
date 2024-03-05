-module(fibonacci_recursive_tco).
-export([fib/1]).

fib(N) when N < 2 ->
    N;
fib(N) ->
    fib_iter(N, 0, 1).

fib_iter(0, A, _) ->
    A;
fib_iter(N, A, B) ->
    fib_iter(N - 1, B, A + B).
