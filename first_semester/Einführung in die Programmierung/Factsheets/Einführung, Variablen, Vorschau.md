# 

## C-Allgemein

Woher stammt C?

    AT&T Bell Laboratories

    erste Version von C für Unix (1969)

Versionen von C:

- 1978 - K&R-C
- 1989 -C89 Standard
- 1990 - ANSI C von ISO übernommen (C90)
- 1999 - C99 Standard
- 2011 - C11 Standard
- 2018 - C18 Standard

C ist eine imperative Programmiersprache, besitzt wenige Schlüsselwörter, was ein Nachteil und Vorteil bringt

C ist die Grundlage für viele andere Programmiersprachen (C#, Java, Swift, Python, ...)

C-Compiler sind auf allen Systemen verfügbar

## Kompilieren mit gcc

```c
gcc -Wall -Werror -Wpedantic -std=c99 test.c -o test
```

Kommentare werden mit:

- // für einzeilige Kommentare

- /* .... */ für einzeilig oder mehrzeilig  

```c
#include <stdio.h>
#include <stdlib.h>
```

Präprozessordirektiven beginnen mit **#** ; diese werden vom Präprozessor verarbeitet und übergibt das Ergebnis an den Compiler

```c
#include -Direktive
```

Wird zum einbinden von Dateien verwendet (z.B. :`stdio.h, stdlib.h`)´

`main()` muss in jedem Programm enthalten sein

### Beispielfragen (s01)

1. Was ist gcc? Wozu wird es gebraucht? Gibt es Alternativen?
   
   A. Compiler, übersetzt Sourcecode und erzeugt ein ausführbares Programm, Alternativen: Clang

2. Unterschied zwischen Quellcode und Executable?
   
   A. ein Quellcode ist nur der Code und eine Executable kann man direkt ausführen

3. Was bewirken die Flags? (-Wall, -Werror, -Wextra, -Wpedantic, -std=c11)
   
   w - steht für "warnings"
   -`Wall`schaltet alle Compiler Warning Flags an
   -`Werror` behandelt alle Fehler als Kompilierungsfehler
   -`Wextra` meldet alle Warnungen oder Fehler, die während der    Programmkompilierung auftreten
   -`Wpendatic` zeigt alle Warnungen an die nicht mit dem ISO C Standard konform sind  
   
   -`Wconversion` zeigt Fehlerbei conversions an

## Variablen

- Ein Programm verarbeitet Daten die in Variablen abgelegt werden

- Ergebnisse werden in Variablen abgelegt

- Eine variable ist eine benannte Speicherstelle

Kennzeichen einer Variable:

- Name

- Datentyp

- Wert

- Adresse

- Gültigkeitszeitraum

- Sichtbarkeitsbereich

Bei Variablen muss man zwischen einer **Deklaration** und einer **Definition** unterscheiden, da man später Programme schreibt und nicht immer neue Variablen definieren möchte.

###### Definition einer Variable

> Deklaration und Reservierung des Speicherplatzes

`int ival;`

###### Definition einer Deklaration

> Umfasst Namen und Typ dem Compiler klar, dieser Variablenname muss nun mit diesen Typ verbunden werden

`extern int ival; //Deklaration`

Reservierte Schlüsselwörter in ANSI C/C99

- werden immer klein geschrieben

- können nicht als Variablennamen verwendet werden

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-19-43-27-image.png)

In C hat jede Variable einen genau definierten, vom Programmierer festgelegten Typ

- Der Typ bestimmt welche Werte eine Variable annehmen kann

- Der Datentyp einer Variable legt die Darstellung durch den Rechner fest:
  
  - Speicherbedarf (durch wie viele Bits wird die Variable dargestellt ?)
  
  - Wertebereich und Zeichensatz
  
  - Genauigkeit (bei Gleitkommazahlen)

## Datentypen

##### Elementare Datentypen

- char (`'c'`): dient zur Aufnahme von Zeichen (oder Ganzzahlen)

- int (`29`): wird für ganzzahlige Werte mit Vorzeichen verwendet

- float(`29.33`): Gleitkommazahlen mit einfacher Genauigkeit

- double(`31.334`): Gleitkommazahl mit doppelter Genauigkeit 

##### Typmodifikatoren

- short int: Ganzzahliger Datentyp, der **höchstens** den Wertebereich von int besitzt; int kann weggelassen werden

- long int: Ganzzahliger Datnetyp, der **mindestens** den Wertebereich von int besitzt;
  int kann weggelassen werden

- long double: Gleitkomma-Datentyp mit erweiterter Genauigkeit; kann je nach System identisch mit double sein

- long long int: Erweiterter ganzzahliger Typ; kann identisch mit long sein; int kann weggelassen werden

###### signed oder unsigned

char und ganzzahlige Datentypen können hinsichtlich des Vorzeichen modifiziert werden

- unsigned: Das Vorzeichenbit wird für die Darstellung des positiven Zahlenwerts frei (wenn etwas nie negativ werden kann, dann verwendet man es)

- signed: Ein Bit wird für die Darstellung des Vorzeichens reserviert

Standardmäßig werden Ganzzahlen mit signed ausgestattet

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-20-13-49-image.png)

Der C-Standard gibt minimale Größen an, die Größe ist immer abhängig vom System!

Größen werden in folgenden Headerdateien genau festgelegt:

- limits.h

- float.h

- stdint.h (Integer mit bestimmter Größe)

##### Variablen vereinbaren

Bevor eine Variable verwendet werden kann muss sie vereinbart werden:

- Datentyp

- Name

Beispiele: 

- **char** = 1 Byte (Endet mit einem `\0`)

- **short** = 2 Bytes

- **int** = 4 Bytes

- **long** = 4 Bytes

- **long long** = 8 Bytes

- **float** = 4 Bytes

- **double** = 8 Bytes

- **long** double = 16 Bytes

##### Boolescher Datentyp

Für den Datentyp _Bool muss man `<stdbool.h>` inkludieren, dabei kann man mit `bool b = false; oder _Bool a = true;` definieren. 

###### Ganzzahlige Literale

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-20-57-39-image.png)

###### Gleitkommaliterale

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-20-58-58-image.png)

###### Zeichenliterale

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-20-59-27-image.png)

##### Ausgabe mit `printf`

`printf` ist eine Funktion der C Standard Library, die Allgemeine Form lautet `printf(Formatstring, Parameterliste)` 

- Die Parameterliste kann ganz wegfallen oder hängt vom Formatstring ab

- Der Formatstring besteht aus normalen Stringtext, in dem sich auch Platzhalter für Variablen befinden können, diese beginnen immer mit einem Prozentzeichen `%`  

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-21-11-39-image.png)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-21-13-08-image.png)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-21-13-18-image.png)

Beispielcode:

```c
#include <stdio.h>
#include <stdlib.h>
int main(void) {
    int a = 123;
    long b = 1234l;
    //intc = 10000000000; Overflow!
    unsigned long long d = 0x12FULL;
    float e = 10.0f;
    double f = .0125;
    char g = 'a';
    printf("%d\n", a);
    printf("%ld\n", b);
    printf("%lld\n", d);
    printf("%f\n", e);// defaultargumentpromotion
    printf("%f\n", f);
    printf("%c\n", g);
    printf("%hhd\n", g);
return EXIT_SUCCESS;
}
```

Ausgabe dieses Programmes:

```c
123
1234
303
10.000000
0.012500
a
97
```

##### Aufzählungskonstanten

Eine Aufzählung ist eine Folge von konstanten ganzzahligen Werten.

`enum boolean{FALSE, TRUE};` 

Typ **boolean** mit zwei Aufzählungskonstanten.
Eine Variable von Typ **boolean** kann einen Wahrheitswert repräsentieren.
Hinweis: In C99 gibt es dafür einen eigenen Typ!

Aufzählungskonstanten haben einen konstanten ganzzahligen Wert (vom Typ **Int**) 

- die erste Konstante hat den Wert 0, die zweite den Wert 1

- Werte können mit ganzen Zahlen beliebig belegt werden

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-21-24-48-image.png)

##### (Symbolische) Konstanten

mit `define` (am Anfang des Programms):

    `#define PI 2.71828182` kann es definiert werden

Definition unter Verwendung des Schlüsselwortes `const` 

    `const double e = 2.71828182;` 

Der Unterschied der beiden Methoden ist das, `define` - Direktiven durch den Präprozessor ersetzt wird (textuell)

## `getchar und putchar`

- `getchar` liest ein einzelnes Zeichen von der Tastatur (liefert einen Integer zurück)

- `putchar` gibt ein Zeichen auf den Bildschirm (Konsole) aus
  
  - wird als Integer übergeben 

```c
#include <stdio.h>

int main(){
    int c;
    while ((c = getchar()) != EOF)
        putchar(c);
    return 0;
}
```

## `scanf`

Die Funktion liest zeichenweise eine Folge von Eingabefeldern ein. Man kann von einer Variable x die Adresse mit `&x` ermitteln. Die Formatierung ist ähnlich zu dem `printf`.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-26-21-35-30-image.png)

## Verzweigungen

#### If and Else

`if(Bedingung){ //Bedingter Block}` führt nur den Code in der Klammer aus, wenn die Bedingung `Wahr` ist und der `else{}` Block wird nur dann ausgeführt wenn die Bedingung `FALSCH` ist.

```c
if (Bedingung) {
    //Ausruck = Bedingung == WAHR
} else {
    //Ausdruck = Bedingung == FALSCH
}
```

`if-then-else` kann auch durch Operatoren ersetzt werden:
`Bedingung ? Ausdruck_1 : Ausdruck_2` diese Art sollte jedoch nur bei den einfachsten Anweisungen benutzt werden. Es gibt auch ein `else if`

#### Switch

Für mehrfache Verzweigungen verwendet man ein switch-Statement.

```c
int main (void){
    int eingabe = 0;
    printf("-1- Level 1\n");
    printf("-2- Level 2\n");
    printf("-3- Level 3\n");
    printf("-4- Beenden\n");
    printf("Ihre Auswahl bitt: ");

    int check = scanf("%d", &eingabe);
    if (check != 1){
        printf("Fehler bei der Eingabe ...\n");
        return 1;
    }
    switch(eingabe){
    case 1 : printf("Level 1 war die Auswahl\n");
             break;
    case 2 : printf("Level 2 war die Auswahl\n");
             break;
    case 3 : printf("Level 3 war die Auswahl\n");
             break;
    case 4 : printf("Beenden war die Auswahl\n");
             break;
    default : printf("%d? Unbekannter Level!\n", eingabe);
    }
    return 0;
}
```

Hier im switch wird `int eingabe` als Ausdruck für das `switch` weitergegeben, dabei überprüft `case` die Variable `eingabe` wenn diese einer der Fälle trifft wird dieser `case` dann ausgeführt und anschließend **gebreakt** . Der `break` ist sehr wichtig, da sonst alle `cases` hintereinander ausgeführt werden und das zu Fehlern führt, jedoch könnte man auch bewusst die `break`s weglassen. Am Ende kommt `default` falls keiner der `cases` eintrifft.

### Schleifen

#### `for` - Schleife

Wird auch oft als Zählerschleife bezeichnet, Syntax: 

```c
for (initialisierung, Bedingung, reinitialisierung){
    //Anweisungen
}
```

Bei der Initialisierung wird für gewöhnlich ein eine Zählvariable initialisiert (z.B.:`int i`), die Bedingung ist wieder ein Bool, heißt die Schleife wird solange ausgeführt bis die Bedingung == 0 wird, danach wird mit dem Programm fortgefahren. Die Reinitialisierung ist meistens die Veränderung der Zählvariable, dabei wird dies zumeist immer am Ende eines Durchlaufs gemacht.

#### `while` - Schleife

Wird auch eine Kopfgesteuerte Schleife genannt, da die Schleife nur dann die Anweisungen, wenn die Bedingung **True** ist, sobald die Bedingung **False** ist wird die Schleife beendet. Syntax:

```c
while (Bedingung) {
    //Anweisungen
}
```

#### `while-do` - Schleife

Wird auch eine Fußgesteuerte Schleife genannt, Syntax:

```c
do {
    //Anweisungen
} while (Ausdruck);
```

Die `do-while` Schleife garantiert ein einmaliges Durchlaufen der Anweisungen, aber sollte die Bedingung False ausgeben, dann wird der Code nach der Schleife weitergeführt. Sollte sie True sein, dann werden die Anweisungen erneut ausgeführt. 

Eine `do-while` - Schleife verwendet man, dann wenn mindestens einmal die Anweisungen ausgeführt werden sollen.

#### Kontrolliertes verlassen von Schleifen

Um eine Schleife zu verlassen gibt es drei Möglichkeiten:

- break

- continue

- Schleifenbedingung trifft nicht mehr zu

##### `break`

Mit dem Break kann man eine Schleife vorzeitig beenden, dies ist oft hilfreich um endlos Schleifen zu verhindern. 

> **Achtung:** Wenn mehrere Schleifen verschachtelt sind wird immer zuerst die innerste Schleife abgebrochen!!

##### `continue`

Das `continue` bricht nicht die ganze Schleife ab, sondern kann den restlichen Code in der Schleife überspringen und zum nächsten Schleifendurchlauf springen. 

```c
int main (void) {
    int sum = 0;
    for (int i = 0, i <= 20, i++){
        if (i % 2){
            continue; //sollte i % 2 == 1 sein dann wird zum nächsten Schleifendurchlauf gesprungen
        }
        sum += i;
    }
    printf("Die Summe der geraden Zahlen beträgt: %d\n", sum);
    return 0;
}
```

##### `goto`

Mit dieser Anweisung kann man einen Sprung zu der markierten Programmzeile bewirken. Dabei ist die Programmzeile mit einer Sprungmarke markiert worden. Macht nur in ganz wenigen Ausnahmefällen Sinn es zu verwenden.

"Theoretisches Beispiel"

```c
for(inti = 0; i < 5; i++)
    for(intj = 0; j < 5; j++) {
        for(intk = 0; k < 5; k++)
            if(i+ j + k == 10)
                goto breakout;
        printf("%d %d\n", i, j);
    }
breakout:
printf("Nachgoto!\n");
```

Ausgabe:

```c
0 0
0 1
0 2
0 3
0 4
1 0
1 1
1 2
1 3
1 4
2 0
2 1
2 2
2 3
Nach goto!
```

## Operatoren

### Arithmetische Operatoren

- Arithmetische Operatoren werden für Berechnungen verwendet und sind *linksassoziativ*

<img src="file:///C:/Users/Boon-Chung%20Chi/AppData/Roaming/marktext/images/2022-01-17-21-15-48-image.png" title="" alt="" width="583">

Arithmetische Operatoren lassen sich in einer kürzeren Schreibweise darstellen

<img src="file:///C:/Users/Boon-Chung%20Chi/AppData/Roaming/marktext/images/2022-01-17-21-15-27-image.png" title="" alt="" width="587">

Sollte das Ergebnis einer arithmetischen Operation nicht mehr in den Wertebereich passen, dann tritt ein Überlauf auf.

### Inkrement- und Dekrementoperatoren

Mit dem Inkrementoperator `(++)` und den Dekrementoperator `(--)` kann der Wert einer Variable mit 1 erhöht  bzw. erniedrigt werden.

> Kann als Prä- oder Postfix-Operator verwendet werden

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-28-21-20-49-image.png)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-28-21-25-25-image.png)

### Ausdruck und Anweisung

- Ausdruck
  
  - Kombination von Operatoren und Operanden
  
  - kann Teil eines größeren Ausdrucks sein
  
  - Bsp.: Ausdruck 3  + 4 hat den Wert 7 

- Anweisung
  
  - Anweisungen haben keinen Wert
  
  - durch einem Semikolon wird ein Ausdruck zu einer Anweisung
  
  - Bsp.: `i++;` 

### Vergleichsoperatoren

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-28-21-32-37-image.png)

### Logische Operatoren

Mithilfe von logischen Operatoren können Ausdrücke als Aussagen miteinander verknüpft werden. Wenn die Aussage wahr ist, dann ist der Wert 1 ansonsten ist der Wert 0.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-28-21-35-33-image.png)

> Vergleichsoperatoren und logische Operatoren sind linksassoziativ

Präzedenzen von Operatoren

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-15-32-28-image.png)

Achtung die Reihenfolge der Verknüpfung ist nicht wichtig, da der Compiler selber entscheiden kann, welcher Operator zuerst ausgewertet wird.

### Bitoperationen

Diese Operatoren unterstützen den direkten Zugriff auf die binäre Darstellung von Ganzzahlen.

Die vier logische Bit-Operatoren:

- &  = UND-Operator

- | = ODER-Operator

- ^ = XOR -Operator

- ~ = Negationsoperator für Bits

Shift-Operator:

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-17-00-05-image.png)

rechtshift ist division durch 2 so oft wie geshiftet wird

Beispiele:

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-17-00-32-image.png)

### size-of Operator

Der Operator `sizeof` ermittelt die Größe von Datentypen und Datenobjekten im Hauptspeicher. Der Operator kann auch auf einen Ausdruck als auch auf einen Typbezeichner angewendet werden.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-17-02-43-image.png)

 Wichtig ist es die Klammern zu setzen, da sizeof  eine hohe Präzedenz hat und dadurch immer das erset Element, welches übergeben wird. Bei einem `unsigned int oder unsigned long` wird ein Ergebnis geliefert, welches abhängig vom System ist (32-Bit oder 64-Bit).           

#### Typumwandlungen

Das sogenannte **type-Casting** geschieht wenn ein Datentyp in einen anderen konvertiert, dabei kann dies automatisch geschehen (int wird float-Wert zugewiesen) oder erzwungen werden.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-17-45-27-image.png)

#### Explizite Typumwandlungen durch Casts

Was ist ein Cast? 

    ein Datentyp das in Klammern vor einem Ausdruck vorangestellt wird

Wichtig zu wissen ist, dass Information verloren gehen kann, außerdem ist die Stelle des Casts **sehr wichtig** !! Die Priorität der Operatoren legt den Auswertungszeitpunkt fest.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-17-52-42-image.png)

#### Sequenzpunkt (sequence point)

Punkt an dem alle bisherigen Seiteneffekte ausgewertet sein müssen!

- `;` 

- `,`

- `&& und ||`

- `?` 

weitere Sequenzpunkte:

- Funktionsaufrufe (call - Zeitpunkt)

- Direkt vor der Rückkehr von Bibliotheksfunktionen

- am Ende vollständiger Ausdrücke (bei `if, while, for, switch`)

## Funktionen

#### Wozu sollte man Funktionen verwenden?

- man kann einen programmteil mit einem Namen aufrufen

- beim Aufruf können Parameter mitgegeben werden

- Funktionen sorgen für mehr bessere Struktur 

- eine Funktion kann an mehreren Stellen mit Unterschiedlichen Parametern aufgerufen werden

Wie sind Funktionen aufgebaut?

- Funktionskopf

- Funktionsrumpf

Was sind Eingabe - und Ausgabedaten?

- Eingabedaten
  
  - Per Parameter übergebene Werte
  
  - Globale variablen (außerhalb von Funktionen vereinbart)

- Ausgabedaten
  
  - Rückgabewert der Funktion
  
  - Änderungen an Variablen, deren Adresse mit übergeben worden ist
  
  - Änderungen an globalen Variablen (nur in Ausnahmefällen)

Globale Variablen werden automatisch mit `NULL` initialisiert.

Sollte die Lokale variable gleich heißen wie die Globale Variable, dann wird innerhalb einer Funktion immer die Lokale verwendet.

**Parameter** = formaler Parameter

**Argument** = aktueller Parameter

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-18-28-44-image.png)

##### Rücksprung aus einer Funktion

`return` beendet den Funktionsaufruf und das Programm kehrt zu der Anweisung, in der die Funktion aufgerufen wurde zurück und beendet diese Anweisung. Anschließend wird die nächste Anweisung nach dem Funktionsaufruf abgearbeitet

`void` - Funktionen haben keinen Rückgabewert. 

Es sollte `EXIT_SUCCESS und EXIT_FAILURE` aus der stdlib.h verwendet werden, da bei unterschiedlichen Systemen 0 und 1 einen anderen Rückgabewert haben können.

Funktionen können Vorwärtsdeklariert werden, somit kennt der Compiler den Namen der Funktion und man kann die Funktion nach der `main()` definieren. Dies ist der sogenannte **Funktionsprototyp**!

##### Rekursion mit Funktionen

Mit Funktionen kann man einfach Rekursion programmieren, da man bei return alles mitgeben kann. Normalerweiße würde man es iterativ programmieren mithilfe von Schleifen.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-18-43-52-image.png)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-29-18-45-18-image.png)

## Arrays

`Typname Arrayname [Größe]` 

Beispiel:

`int counter[5]` , `char letter[10]` , `float array[5] = {0.75, 1.0, 1.5}` 

Array kann aber auch ohne Größe initialisiert werden `int array[] = {1,2,3}` 

Bei der Initialisierung müssen nicht alle Elemente angegeben werden, die restlichen Elemente werden mit 0 initialisiert. 

> Man fangt bei den Arrays mit 0 an zu zählen `int array[] = {1,2,3} = array[0] = 1`

Man kann auch im Array ein bestimmtes Element ausgewählt werden (C99): 

`int arr[3] = {100, [2] = 500}` um die Inhalte eines Arrays auszugeben nutzt man meistens eine for-Schleife mit dem Index i.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-30-21-30-19-image.png)

Arrays können an Funktionen übergeben werden, dabei wird nicht das ganze Array übergeben, sondern nur die Anfangsadresse des Arrays.  Änderungen an Arrays innerhalb einer Funktion sind auch außerhalb sichtbar!

Gültige Aufrufe für das Printen von Arrays:

`print_array((int[]){4,5,6}, MAX);
 print_array((int[3]){4,5,6}, MAX);` die übergebenen Arrays haben keine Bezeichner!

##### Arrays vergleichen

Zwei Arrays sollten **nicht** mit dem Operator `==` verglichen werden!! Damit wird nur die Speicheradresse verglichen, um den Inhalt zu vergleichen sollte man Schleifen nutzen oder `memcmp()` .

##### Größe eines Arrays ermitteln

Die Größe eines Arrays kann man mithilfe von `sizeof` - Operator ermitteln. Wenn man dies auf ein Array anwendet kommt die Größe in Bytes heraus, weshalb man mit der Größe des jeweiligen Datentyps dividiert.

```c
int numbers[] = {3,6,5,7};
printf("Anzahl Elemente: %zu\n", sizeof(numbers)/sizeof(int));
```

##### Mehrdimensional Arrays

`int alpha[3][4];` Alpha ist ein **zweidimensionaler Array** mit 3 Zeilen und 4 Spalten. Man kann sich es vorstellen, wie als wäre das erste Array das äußerste und das zweite Array das innere. 

Zuweisung auf beispielweise das erste Element des zweidimensionalen Arrays:

`alpha[0][0] = 5` oder man initialisiert sie mit Listeninhalte: 

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-30-21-58-47-image.png)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-30-22-04-32-image.png)

Arrays werden im Speicher sequentiell hintereinander gespeichert, weshalb man sagen kann das es Arrays von Arrays sind.

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-30-22-06-00-image.png)

##### Zeichenketten (Strings)

Strings werden in C durch char gespeichert, dabei ist das Ende eines Strings mit einem Null-Zeichen `\0` markiert, dieses Zeichen hat 0 Bits. Somit ist die Länge von einem String immer um eins länger. Mit der Bibliothek `string.h` kann man viele nützliche Funktionen aufrufen. (strlen, strcmp, strcpy, ...)

![](C:\Users\Boon-Chung%20Chi\AppData\Roaming\marktext\images\2022-01-30-22-43-53-image.png)

## Pointer

Der Arbeitsspeicher des Rechner ist in Speicherzellen eingeteilt, diese Speicherzellen haben jeweils eine Nummer (Adresse). Ein Pointer ist eine Variable die auf eine Adresse im Speicher zeigen kann und somit aufnehmen tut. Mit Zeigern lassen sich Funktionen als Argumente an andere Funktionen übergeben. Es kann ein typenloser Zeiger definiert werden, womit Datenobjekte beliebigen Typs verarbeitet werden können.

**Typname** ***Zeigername** ;

- Typname * ist der Datentyp des Zeigers

- Zeigername ist der Name des Zeigers

Beispiel: (traditionell)

`int *pointer;`

`float *pointer2;`

`ìnt *pointer, counter;` -- hier ist nur pointer eine Zeigervariable

 Beispiel: (moderne Konvention)

`int* pointer1;`

`float* pointer2;` 

`int* pointer`

`int counter`

Man kann Zeiger auf Zeiger zuweisen, das bedeutet die Zeiger zeigen auf das gleiche Objekt nach der Zuweisung.

`pointer2 = pointer1` 

###### Adressoperator &

Wenn x eine Variable von Typ `typname` ist, dann ist `&x` die Adresse von x, welches jetzt an einen pointer übergeben werden kann. 
`int counter= 1;
int*pointer= &counter;` 

Wird einem `pointer` nichts initialisiert, dann zeigt dieser auf eine **beliebige** Adresse!

pointer zeigt immer bei arrays nur auf den anfang
