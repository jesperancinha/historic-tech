factorialnr :: Integer -> Integer
factorialnr 0 = 1
factorialnr n = n * factorialnr (n - 1)

main :: IO ()
main = do
  let result = factorialnr 5
  putStrLn $ "Factorial of 5 is: " ++ show result
