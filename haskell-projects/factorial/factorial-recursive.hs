factorial :: Integer -> Integer
factorial n = factorialHelper n 1
  where
    factorialHelper :: Integer -> Integer -> Integer
    factorialHelper 0 acc = acc
    factorialHelper n acc = factorialHelper (n - 1) (acc * n)

main :: IO ()
main = do
  let result = factorial 5
  putStrLn $ "Factorial of 5 is: " ++ show result
