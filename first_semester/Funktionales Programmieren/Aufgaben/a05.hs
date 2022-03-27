ite :: Bool -> a -> a -> a
ite True x y = x
ite False x y = y

-- Exercise 1.1
mergeLists :: [a] -> [b] -> [(a,b)]
mergeLists (x : xs) (y : ys) = (x,y) : (mergeLists xs ys)
mergeLists (x:xs) [] = []
mergeLists [] (y:ys) = []
mergeLists [] [] = []


-- Exercise 1.2
calculateAge :: (Int, Int, Int) -> Int
calculateAge (d,m,y) = 2021 - y - if m < 11 || (m == 11) && (d <= 10) then 0 else 1


-- Exercise 1.3
convertDatesToAges :: [(String, (Int, Int, Int))] ->  [(String, Int)]
convertDatesToAges ((n,b) : xs) = (n, calculateAge b) : convertDatesToAges xs
convertDatesToAges [] = []

-- Exercise 1.4
getOtherPairValue :: (Eq a, Eq b) => (a,b) -> Either a b -> Maybe (Either a b)
getOtherPairValue (n1,a) (Left n2) = ite (n1 == n2) (Just (Right a)) Nothing
getOtherPairValue (n,a1) (Right a2) = ite (a1 == a2) (Just (Left n)) Nothing

--Exercise 2.1
--addPair (x,y) = x + y -- addPair :: Num a => (a,a) -> a, due to the plus, integers are expected
--addList xs = map addPair xs -- addList :: Num a => [(a,a)] -> [a], lists are expected and Tuples in List

--Exercise 2.2
{-
addList ((1,2):(2,1):[])
= addPair (1,2) : addList ((2,1),[])
= 1+2 : addList (2,1) : addList ([])
= 3 : addPair (2,1) : addList ([])
= 3 : 3 : []
= [3,3]
-}


-- Exercise 2.3
fstList :: [(a, b)] -> [a]
fstList ((x,y):xs) = x : fstList xs
fstList [] = []

-- Exercise 2.4
lengthSumMax :: (Num a,Ord a) => [a] -> (Int, a, a)
lengthSumMax (x:xs)= (length (x:xs), sum (x:xs), maximum (x:xs))
lengthSumMax [] = (0,0,0)
{-
lengthList (x:xs) = 1 + length xs
lengthList [] = 0
sumList (x:xs) = x + sum xs
sumList [] = 0
maxList (x:xs) = ite (xs > x) (xs) x
maxList [] = 0
-}
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
