
listPrint :: Show a => [a] -> String
listPrint [] = "<>"
listPrint (x:xs) = "<" ++ show x ++ middle ++ ">" where 
    middle = concat ( map (\x -> "; " ++ show x) xs) 



data NewList a = ListConstr [a]

removeFirstLast :: [a] -> [a]
removeFirstLast xs = reverse(tail(reverse(tail xs)))

instance Show a => Show (NewList a) where 
    show (ListConstr xs) = removeFirstLast (show xs)

--show [1,2,3] == "(1, 2, 3)"
answerTask3 :: Bool
answerTask3 = False 

rotateLetter :: Char -> Char
rotateLetter n 
    | n >= 'a' && n <= 'z' = pred n --succ if +1
    | n == 'z' = 'a'
    | otherwise = n 



nApply :: (a -> a) -> Int -> (a -> a)
nApply f 0 = (\x -> x) 
nApply f n = f . nApply f (n-1)

cipher :: Int -> String -> String

cipher n string = map (nApply rotateLetter n) string


minValue :: Ord b => (a -> b) -> [a] -> a
minValue f [x] = x
minValue f (x:xs) = if f x < f m then x else m 
    where m = minValue f xs 



type Item a = (a, Integer) -- (item identifier, weight)
allTriples :: Ord a => [Item a] -> [((a, a, a), Integer)]
allTriples xs = [((i1, i2, i3), w) | (i1, w1) <- xs, (i2, w2) <- xs, (i3, w3) <- xs, i1 > i2 && i2 > i3, let w = w1 + w2 + w3, w >= 149]


optimalCombination :: Ord a => [Item a] -> (a, a, a)
optimalCombination xs = fst (minValue snd (allTriples xs))


palindrome :: Eq a => [a] -> Bool
palindrome x = x == reverse x 


main :: IO ()
main = main2 0

main2 :: Int -> IO ()
main2 x = do
    s <- getLine
    if s == "quit" then 
        putStr ("I found " ++ show x ++ " palindrome(s).")
    else let n = if palindrome s then 1 else 0
        in main2 (x+n)  