fibonacciTailRec :: Integer -> Integer
fibonacciTailRec n = fibonacciHelper n 0 1
  where
    fibonacciHelper 0 a _ = a
    fibonacciHelper 1 _ b = b
    fibonacciHelper n a b = fibonacciHelper (n - 1) b (a + b)

main :: IO ()
main = do
  let result = fibonacciTailRec 1000
  putStrLn $ "Fibonacci of the order of 1000: " ++ show result
