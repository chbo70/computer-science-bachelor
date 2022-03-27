import Data.List
--import Data.List {- required to use groupBy -}

-- Exercise 1

{-
div  = (/) : divides left to right | div1 :: a -> a-> a
div2 = (2 /) : divides something from 2 | div2 :: a -> a
div3 = (/ 2) : divides something with 2 | div3 :: a -> a
eqTuple f = (\(x, y) -> f x == f y) := compares f(x) and f(y) if they are equal, with a lambda abstraction
    eqTuple :: (a,a) -> Bool
eqTuple' f (x, y) = f x == f y := same as above just with variables
    eqTuple' :: (a,a) -> Bool

difference between div 2 and div3:
    div2 = (2/4) = 0.5
    div3 = (4/2) = 2.0

eqTuple and eqTuple' are the same, since eqTuple is another method with the same solution

foo2 is equal to div1 , since foo1 divides the sscond argument by the first 

-}

-- Exercise 2
fan :: (a -> Bool) -> [a] -> [[a]]
fan p = groupBy (\x y -> p x == p y)


splitOnNumbers :: String -> [String]
splitOnNumbers = fan (\x -> (x >= '0' && x <= '9'))

splitBy :: Eq a =>(a -> Bool) -> [a] -> [[a]]
splitBy p xs =  dropWhile (== [])  (map (dropWhile p) (fan p xs))

-- map wendet die bedingung auf jedes Element in der Liste an, d.h es wird alles ausgegeben, ausßer p, da dropWhile diesen entfernt



-- Exercise 3
dig2intFold :: [Integer] -> Integer
dig2intFold xs = foldr (\x y -> x + 10 * y) 0 xs


dig2int :: [Integer] -> Integer
dig2int [] = 0
dig2int (x:xs) = x + 10 * dig2int xs


suffsFold :: [a] -> [[a]]
suffsFold = let 
              f x [] = [[]]
              f x (xs:y) = (x:xs) : (xs:y)
            in foldr f [[]]

{-
suffs :: [a] -> [[a]]
suffs [] = [[]]
suffs (y @ (_ : xs)) = y : suffs xs
-}

-- Tests
tests = do
  test "2.1a" "[]" (fan undefined [] :: [[Int]])
  test "2.1b" "[[1],[2],[3],[4],[5]]" (fan even [1..5])
  test "2.1c" "[\"T\",\"his is a \",\"T\",\"est\"]" (fan (== 'T') "This is a Test")
  test "2.2 " "[\"8\",\" out of \",\"10\",\" cats\"]" (splitOnNumbers "8 out of 10 cats")
  test "2.3 " "[\"Just\",\"some\",\"lines\"]" (splitBy (== '\n') "Just\nsome\nlines\n")
  test "3.1 " "512" (dig2intFold [2,1,5])
  test "3.2a" "[[1,2],[2],[]]" (suffsFold [1,2])
  test "3.2b" "[\"hello\",\"ello\",\"llo\",\"lo\",\"o\",\"\"]" (suffsFold "hello")

test name e c = do
  putStr ("*** " ++ name ++ ": ")
  if show c == e then putStrLn "OK"
  else putStrLn ("ERROR; expected '" ++ e ++ "', but found '" ++ show c ++ "'")