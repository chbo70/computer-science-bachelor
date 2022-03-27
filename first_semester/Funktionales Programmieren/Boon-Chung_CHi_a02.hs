{-0 :: Bool, nicht valide da es nicht ausgeben kann, ob 0 True or False ist-}
{-head "test" :: Char, Valide da Char ein einzelnes Zeichen ausgibt und die function head das erste Zeichen in der Liste ausgibt-}
{-'hello' :: String, nicht Valide da für einen String die doppelten Anführungszeichen nötig sind-}
--plus1 :: Integer -> Integer, gültig solang die Zahlen natürlich sind
plus1 x = x + 1

--Aufgabe 3.1

data Items =
  Label
  String
  String
  deriving Show

olat = Label "Olat" "https://lms.uibk.ac.at" --constant for "Olat"
fp = Label "FP" "http://cl-informatik.uibk.ac.at/teaching/ws21/fp" --constant for "FP"

--Aufgabe 3.2

data Menu =
  Menu1 --constructor which contains one item
  Items
  |Menu2 --contains two items
    Items
    Items
  deriving Show

menu_2 = Menu2 olat fp -- constant that represent a menu with the items "olat" and "fp"


--Aufgabe 3.3
data MenuList = --for recursive datatype
  Empty
  | MenuList Items MenuList
  deriving Show

menuList_3 = MenuList olat (MenuList fp (MenuList olat Empty))
