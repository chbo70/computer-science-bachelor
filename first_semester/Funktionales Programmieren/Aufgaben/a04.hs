-- Exercise 1
{-
e1 = concat [1 :: Int, 2, 3] <- the output of e1 would be [Int] but [[a]] is expected fehlende Liste
e2 = concat ["one", "two", "three"] <- e2 contains a list of Strings seperated by a comma -> list of Char's [[Char]] -> [Char] = [String] -> String
e3 = concat [[1 :: Int, 2], [], [3]] <-e3 contains a list of a list of Integers, therefore the whole list can be seen as Char's
e4 = concat [["one", "two"], [], ["three"]] <-e4 contains a list of lists (Strings and Empty)
e5 = concat e3 <- e5 loads e3 but the output of e3 is a list of Integer, therefore it doesn't work (add [] and it works again)
e6 = concat e4 <- e6 load e4 which is a list of Strings combined, like e2
-}

suffixes :: [a] -> [[a]]
suffixes (x:xs) = (x : tail (x:xs)) : suffixes (xs)
suffixes xs = [xs]

prefixes :: [a] -> [[a]]
prefixes (x:xs) = (x:xs) : prefixes(pHelper(x:xs))
prefixes xs = [xs]
pHelper (x:xs) = init (x:xs)

{-}
2. Variante:
prefixes (x:xs) = (x:xs) : prefixes(init(x:xs))
prefixes xs = [xs]
-}
menu :: Char -> [a] -> Either String [[a]]
menu 'p' (x:xs) = Right (prefixes(x:xs))
menu 's' (x:xs) = Right (suffixes(x:xs))
menu c (_:_) = Left ("(" ++ [c] ++ ")" ++ "is not supported, use (p)refix or (s)uffix") -- Char -> String: einfach die einelementige Liste der Variable angeben


-- Exercise 2
data Expr a =
  Number a
  |Plus (Expr a) (Expr a)
  |Times (Expr a) (Expr a)
  deriving Show

-- replace dummy expressions by real expressions in comments below as soon as datatype Expr has been defined
expr1 = Times (Plus (Number (5.2 :: Double)) (Number 4)) (Number 2)
expr2 = Plus (Number (2 :: Int)) (Times (Number 3) (Number 4))
expr3 = Times (Number "hello") (Number "world") -- true, since a is a undefined variable, it can be everything

-- expr1 = Times (Plus (Number (5.2 :: Double)) (Number 4)) (Number 2)
-- expr2 = Plus (Number (2 :: Int)) (Times (Number 3) (Number 4))
-- expr3 = Times (Number "hello") (Number "world")

numbers :: Expr a -> [a]
numbers (Number a) = [a]
numbers (Plus a b) = numbers a ++ numbers b
numbers (Times a b) = numbers a ++ numbers b

eval :: (Num a) => Expr a -> a
eval (Number a) = a
eval (Plus e1 e2) = eval e1 +  eval e2
eval (Times e1 e2) = eval e1 * eval e2


exprToString :: Show a => Expr a -> String
exprToString (Number a) = show a
exprToString (Plus a b) = exprToString a ++ " + " ++ exprToString b
exprToString (Times (Plus a b)(Plus c d)) = "(" ++ exprToString (Plus a b) ++ ")" ++ " * " ++ "(" ++ exprToString (Plus c d) ++ ")"
exprToString (Times (Plus a b) c) = "(" ++ exprToString (Plus a b) ++ ")" ++ " * " ++ exprToString (Number c)
exprToString (Times a (Plus b c)) = exprToString (Number a) ++ " * " ++ "(" ++ exprToString (Plus b c) ++ ")"
exprToString (Times a b) = exprToString a ++ " * " ++ exprToString b

--paren a@(Plus _ _) = "(" ++ exprToString a


-- Tests: Un-comment the desired test (and :reload) after you provided a corresponding solution.
testSuffixes = "Expected [[1,2,3],[2,3],[3],[]]; suffixes [1,2,3] returned " ++ show (suffixes [1,2,3] :: [[Int]])
testPrefixes = "Expected [[1,2,3],[1,2],[1],[]]; prefixes [1,2,3] returned " ++ show (prefixes [1,2,3] :: [[Int]])
testMenuP = "Expected Right [[1,2],[1],[]]; menu 'p' [1,2] returned " ++ show (menu 'p' [1,2] :: Either String [[Int]])
testMenuS = "Expected Right [[1,2],[2],[]]; menu 's' [1,2] returned " ++ show (menu 's' [1,2] :: Either String [[Int]])
testMenuC = "Expected Left \"(d) is not supported, use (p)refix or (s)uffix\"; menu 'd' [1,2] returned " ++ show (menu 'd' [1,2] :: Either String [[Int]])
testMenu = putStr (testMenuP ++ "\n" ++ testMenuS ++ "\n" ++ testMenuC ++ "\n")

testEval1 = "Expected 18.4; eval expr1 returned " ++ show (eval expr1 :: Double)
testEval2 = "Expected 14; eval expr2 returned " ++ show (eval expr2 :: Int)
testEval = putStr (testEval1 ++ "\n" ++ testEval2 ++ "\n")

testExprToString1 = "Expected \"(5.2 + 4.0) * 2.0\"; exprToString expr1 returned " ++ show (exprToString expr1 :: String)
testExprToString2 = "Expected \"2 + 3 * 4\"; exprToString expr2 returned " ++ show (exprToString expr2 :: String)
testExprToString3 = "Expected \"\\\"hello\\\" * \\\"world\\\"\"; exprToString expr3 returned " ++ show (exprToString expr3 :: String)
testExprToString = putStr (testExprToString1 ++ "\n" ++ testExprToString2 ++ "\n" ++ testExprToString3 ++ "\n")
