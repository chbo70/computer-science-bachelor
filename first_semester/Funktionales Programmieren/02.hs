
isAllCaps :: String -> Bool
isAllCaps [] = False
isAllCaps xs = aux xs where
        aux [] = True 
        aux (x : xs) = if x >= 'A' && x <= 'Z' then aux xs else False
        
splitAtMinus :: String -> [String]
splitAtMinus [] = []
splitAtMinus str = takeWhile (/='-') str : splitAtMinus (drop 1 (dropWhile (/= '-') str))


{-
split' :: [Char] -> [[Char]]
split' [] = [[]]
split' [str] = [[str]]
split' str = [toUpper (head str)] : tail(takeWhile (/='-') str) : split' (sTail $ dropWhile (/='-') str)

sTail [] = []
sTail i = tail i

loop palcounter = do
    string <- getLine
    if string == "quit" then output palcounter
    else do
        let m = if testPalindrome string then palcounter + 1 else palcounter
        loop m


-}