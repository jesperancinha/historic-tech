factorialTCO :: Integer -> Integer
factorialTCO n = factorialHelper n 1
  where
    factorialHelper 0 acc = acc
    factorialHelper m acc = factorialHelper (m - 1) (m * acc)


main :: IO ()
main = do
  let result = factorialTCO 5
  putStrLn $ "Factorial of 5 is: " ++ show result
