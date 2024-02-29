fibonacci :: Integer -> Integer
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci (n - 1) + fibonacci (n - 2)

main :: IO ()
main = do
  let result = fibonacci 1000
  putStrLn $ "Fibonacci of the order of 1000: " ++ show result
