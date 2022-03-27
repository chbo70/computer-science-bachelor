hello :: String -> String
hello xs = "Hello " ++ xs

--Meilen in Kilometer
milesToKilometers m = m * 1.609344

--Volumen einer Kugel
volume r = r^3 * pi * 4/3

{-Nein, solange die Variablen nicht identisch sind,
ergibt sich nicht die gleiche Antwort zum Durchschnitt.
-}

--Durschnitt zweier Zahlen
average x y = (x+y)/2

average1 x y z = (x + y + z)/3

--Durchschnitt zweier Kugelvolumen
averageVolume r1 r2 = (r1^3 * pi * 4/3 + r2^3 * pi * 4/3)/2

-- averageVolume r1 r2 = average (volume r1) + (volume r2) <-- vieeel Schöner!!

pow8 x = x * x * x * x * x * x * x * x
pow4 x = x * x
pow8a x = pow4(pow4(pow4 x))

pow20_1 x = pow4 (pow4 x) * pow4 x

pow20 x = pow20help (pow4 x)
pow20help x4 = pow4 (x4 * x4)

pow20_2 x = let x4 = pow4 x in pow4 x4 * x4

test1 = "test1: expected ~ 107.826048, computed: " ++ show (milesToKilometers 67 :: Double)
test2 = "test2: expected ~ 179.59438, computed: " ++ show (volume 3.5 :: Double)
test3 = "test3: expected ~ 2.070796, computed: " ++ show (average pi 1 :: Double)
test4 = "test4: expected ~ 150.796448, computed: " ++ show (averageVolume 2 4 :: Double)
