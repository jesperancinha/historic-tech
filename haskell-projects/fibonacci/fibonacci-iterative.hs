fibonacciIter :: Integer -> Integer
fibonacciIter n = fibonacciIterHelper n 0 1
  where
    fibonacciIterHelper 0 a _ = a
    fibonacciIterHelper 1 _ b = b
    fibonacciIterHelper n a b = fibonacciIterHelper (n - 1) b (a + b)

main :: IO ()
main = do
  let result = fibonacciIter 1000
  putStrLn $ "Fibonacci of the order of 1000: " ++ show result
