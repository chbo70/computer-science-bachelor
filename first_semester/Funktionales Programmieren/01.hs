grabEither :: [Bool] -> [a] -> [b] -> [Either a b]
grabEither [] _ _ = []
grabEither _ [] _ = []
grabEither _ _ [] = []
grabEither (b:bs) (x:xs) (y:ys)
    | b == True = Left x : grabEither bs xs ys
    | otherwise = Right y : grabEither bs xs ys

{-
stairway :: [a] -> [[a]]
stairway [] = []
stairway [xs] = [[xs]]
stairway (x:xs) = if even(length (x:xs)) then helper1 (x:xs) else helper2 (x:xs)
-}

stairway :: [a] -> [[a]]
stairway xs = helper 1 xs where
    helper n [] = []
    helper n xs = take n xs : helper (n+1) (drop n xs)

insDesc :: Ord a => [a] -> a -> Maybe [a]
insDesc [] y = Just [y]
insDesc xs y = if y `elem` xs then Nothing else Just (reverse(takeWhile (< y) (reverse xs) ++ helper1 y ++ dropWhile (< y ) (reverse xs)))

helper1 y = [y]

collate :: Eq a => [(a, b)] -> [(a, [b])]
collate [] = []
collate tupellist@(x:xs) = ((fst x) , (find (fst x) tupellist)) : collate xs

find :: Eq t => t -> [(t, a)] -> [a]
find n [] = []
find n (x:xs) = if fst x == n then snd x: find n xs else find n xs

isDublicate ::Eq a =>  [a] -> [a]
isDublicate [] = []
isDublicate [x] = [x]
isDublicate (x:xs)
    | x `notElem` xs = x : isDublicate xs
    | otherwise = isDublicate xs        