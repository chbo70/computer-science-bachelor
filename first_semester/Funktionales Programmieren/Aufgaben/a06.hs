import System.IO


-- Exercise 1
--
dividesRange :: Integer -> Integer -> Integer -> Bool
dividesRange n l u
  | mod n l == 0 = True
  | (l+1) < u = dividesRange n (l+1) u
  | otherwise = False


prime :: Integer -> Bool

prime 2 = True
prime 1 = False
prime x = not (dividesRange x 2 (x-1))
--prime x = not(dividesRange x 2 (sqrtInt x))


generatePrime :: Integer -> Integer
generatePrime d = nextPrime (10^(d-1))
nextPrime x
  | prime x = x
  | otherwise = nextPrime (x+1)



sqrtInt :: Integer -> Integer
sqrtInt n = sfunc 0 n
    where sfunc a b
            | a == b = a
            | c * c > n = sfunc a (c - 1)
            | otherwise = sfunc c b
            where c = div (a + b + 1) 2 
              

-- Exercise 2

heron :: Double -> [Double]
--heron 0 = [0]
--heron x = x  : if x == x+1 then [0.5 * (x + x / x)] else heron (0.5 * ((x) + 1 / (x)))


heron 0 = [0.0]
heron 1 = [1.0]
heron x = helper x x [x]
    where
     helper  x y z 
       | y <= sqrt x = reverse z 
       | otherwise = helper x (0.5 * (y + x / y)) (0.5 * (y + x / y): z)
     

-- Exercise 3

fib :: Integer -> Integer
fib n
  | n == 0 = 0
  | n == 1 = 1
  | otherwise = fib (n-1) + fib (n-2)

fib' :: Integer -> Integer
fib' n
  | n == 0 = 0
  | n == 1 || n == 2 = 1
  | odd n = fib'(div n 2) * fib'(div n 2) + fib'(div n 2 + 1) * fib'(div n 2 + 1)
  | even n = (2 * fib'(div n 2 + 1)-fib'(div n 2)) * fib'(div n 2)
  | otherwise = 0


fibFast :: Integer -> Integer -- stores all values which have already been computed in lookup table
fibFast n = case fibFastAux n [(2,1),(1,1),(0,0)] of
  (x,y) -> x

fibFastAux :: Integer -> [(Integer, Integer)] -> (Integer, [(Integer, Integer)])
fibFastAux n ltable = case lookup n ltable of
  Just n1 -> (n1, ltable)
  Nothing -> (a_n, (n, a_n) : l_n3) 
    where
    (a_n2, l_n2) = fibFastAux (div n 2) ltable
    (a_n3, l_n3) = fibFastAux (div n 2+1) l_n2
    a_n
      | odd n = a_n2 ^ 2 + a_n3 ^ 2
      | otherwise = (2 * a_n3 - a_n2) * a_n2


-- Tests

testDividesRange = do
  expected "True"
  computed (dividesRange 629 15 25)
  expected "False"
  computed (dividesRange 1009 30 500)
testPrime = do
  expected "[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]"
  computed (filter prime [0..100])
testGeneratePrime = do
  expected "[1009,100003,10000019]"
  computed (map generatePrime [4,6,8])
testHeron = do
  expected "[0.0]"
  computed (heron 0)
  expected "[1.0]"
  computed (heron 1)
  expected "[2.0,1.5,1.4166666666666665,1.4142156862745097,1.4142135623746899,1.414213562373095]"
  computed (heron 2)

expected e = putStrLn ("expected: " ++ e)
computed c = putStrLn ("computed: " ++ show c)


testFibGen :: String -> (Integer -> Integer) -> IO ()
testFibGen s f = mapM_ test (zip [0..] xs)
  where xs = [0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765]
        test (n, m) =
           do putStr ("Testing " ++ s ++ " " ++ show n ++ " == " ++ show m ++ "... ")
              hFlush stdout
              let m' = f n
              if m' == m then
                putStrLn "OK"
              else
                putStrLn ("WRONG (got " ++ show m' ++ ")")

testFib = testFibGen "fib" fib
testFib' = testFibGen "fib'" fib'
testFibFast = testFibGen "fibFast" fibFast
