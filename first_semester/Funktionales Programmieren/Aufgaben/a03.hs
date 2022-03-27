-- Definition of List from lecture
data List = Empty | Cons Integer List deriving Show
-- example list for testing
list = Cons 1 (Cons 7 (Cons 9 (Cons 3 Empty)))
list2 = (Cons 24 Empty)
-- example assignment for testing
myAssn = Assign "x" 1 (Assign "x" 2 (Assign "y" 3 EmptyA))

-- Exercise 1 (Pattern Matching)

-- (i) matches with 1.Student; Substitution: n= 243781, True , Physics
-- (ii) matches with 3.Student; Substitution: n= 234145, CS
-- bei Kleinschreibung von Constructoren, werden diese als variable gesehen und somit wäre ein pattern matching vorhanden
-- Exercise 2

-- disj

disj :: Bool -> Bool -> Bool
disj x y = x || y

-- sumList

sumList :: List -> Integer
sumList Empty = 0
sumList (Cons x xs) = x + sumList xs

-- double2nd
double2nd :: List -> List
double2nd Empty = Empty --double2nd xs@Empty = xs
double2nd (Cons x Empty) = Cons x Empty -- double2nd xs@(Cons x Empty) = xs
-- mann kann auch nur double2nd xs = xs schreiben
double2nd (Cons x (Cons xs y)) = Cons x(Cons (xs * 2) (double2nd y))


-- Exercise 3

data Expr = Number Integer
  | Var String
  | Plus Expr Expr
  | Negate Expr
  deriving Show

data Assignment = EmptyA | Assign String Integer Assignment
  deriving (Show, Eq)

ite :: Bool -> Integer -> Integer -> Integer -- man könnte auch Integer ersetzen durch a, somit ist der Datentyp nicht definiert
ite True x y = x
ite _ _ y = y

lookupA :: Assignment -> String -> Integer
lookupA (Assign x y xs) s = ite (x == s) y (lookupA xs s)
lookupA _ _ = 0


eval :: Assignment -> Expr -> Integer
eval (Assign x y xs) (Number n) = n
eval (Assign x y xs) (Negate e) = - eval (Assign x y xs) e
eval (Assign x y xs) (Var v) = lookupA (Assign x y xs) v
eval (Assign x y xs) (Plus e1 e2) = eval (Assign x y xs) e1 + eval (Assign x y xs) e2
eval _ _ = error "Assignment Empty"


-- Exercise 3.4

{- You may (and will have to) extend this datatype in order to incorporate the "let" construct. -}
data Expr' = Number' Integer
  | Let  Expr Expr
  | Var' String
  | Plus' Expr' Expr'
  | Negate' Expr'
  deriving Show

eval' :: Assignment -> Expr' -> Integer
eval' (Assign x y xs) (Number n) = n
eval' (Assign x y xs) (Negate e) = - eval (Assign x y xs) e
eval' (Assign x y xs) (Var v) = lookupA (Assign x y xs) v
eval' (Assign x y xs) (Plus e1 e2) = eval (Assign x y xs) e1 + eval (Assign x y xs) e2
eval' (Assign x y xs) (Let v e1 e2) = let
  v1 = eval (Assign x y xs) e1
  assn' = (Assign x v1 (Assign x y xs))
  in eval assn' e2


-- the following tests can be used by you, once you have implemented your functions,
-- for testing, just invoke testX in ghci
testSum = putStrLn ("expected: 20\ncomputed: " ++ show (sumList list))
testDouble = putStrLn ("expected: Cons 1 (Cons 14 (Cons 9 (Cons 6 Empty)))\ncomputed: " ++ show (double2nd list))
testLookupA = putStrLn ("expected: 1 3 0\ncomputed: " ++ show (lookupA myAssn "x") ++ " " ++ show (lookupA myAssn "y") ++ " " ++ show (lookupA myAssn "z"))
testEval = putStrLn ("expected: 42\ncomputed: " ++ show (eval myAssn (Plus (Negate (Var "y")) (Number 45))))
