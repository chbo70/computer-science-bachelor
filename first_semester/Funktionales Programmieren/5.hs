ite :: Bool -> a -> a -> a
ite True x y = x
ite False x y = y

-- Exercise 1.1
mergeLists :: [a] -> [b] -> [(a,b)]
mergeLists (a : as) [] = []
mergeLists [] (b : bs) = []
mergeLists (a : as) (b : bs) = (a,b) : (mergeLists as bs)

-- Exercise 1.2
calculateAge :: (Int, Int, Int) -> Int
calculateAge (_, _, a) = 2021 - a

-- Exercise 1.3
convertDatesToAges :: [(String, (Int, Int, Int))] ->  [(String, Int)]
convertDatesToAges ((a, (b1, b2, b3)) : as) = (a, calculateAge(b1, b2, b3)) : (convertDatesToAges as)

-- Exercise 1.4
getOtherPairValue = undefined

-- Exercise 2.3
fstList :: [(a, b)] -> [a]
fstList [] = []
fstList ((a, b) : as) = a : fstList as

-- Exercise 2.4
lengthSumMax :: (Num a, Ord a) => [a] -> (Int, a, a)
lengthSumMax list = (lengthList list, sumList list, maxList list)
lengthList [] = 0
lengthList (a : as) =  1 + lengthList as
sumList [] = 0
sumList (a : as) = a + sumList as
maxList [] = 0
maxList (a : as) = if maxList as > a then maxList as else a

-- Tests
testMergeLists = do
  expected "[(1,'a'),(2,'b'),(3,'c')]"
  computed (mergeLists [1,2,3,4] ['a','b','c'] :: [(Int, Char)])
testCalculateAge = do
  expected "0"
  computed (calculateAge (10, 11, 2021))
  expected "20"
  computed (calculateAge (12, 12, 2000))
testFstList = do
  expected "[1,2,3]"
  computed (fstList [(1,'a'),(2,'b'),(3,'c')])
  expected "[\"hello\"]"
  computed (fstList [("hello","world")])
  expected "[]"
  computed (fstList [] :: [Integer])
testLengthSumMax = do
  expected "(0,0,0)"
  computed (lengthSumMax [])
  expected "(5,3,2)"
  computed (lengthSumMax [0,1,0,2,0])

expected e = putStrLn ("expected: " ++ e)
computed c = putStrLn ("computed: " ++ show c)
