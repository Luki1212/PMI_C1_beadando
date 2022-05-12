# PMI_C1_beadando
A program Banki nyilvántartásra lett kitalálva és az adott dolgokat tárolja:
 

    Név -> Számla tulajdonos neve

    id -> Számla id -ja

    Pémz -> Számlán található pénz összeg



## A program működése

Első sorban a főmenüt hozza be, ahol 4 opciónk van,

### Főmenü:
1.	Listázás  -> Kilistázza az xml fáljban lévő elemeket
2.	Ujszamla -> Uj elem hozzáadása az xml fájlhoz
3.	Modositás -> Elem módósítása
4.	Törlés -> Elem törlése

0	Exit -> Kilépés a programból

## 2.Ujszamla

Hozzá lehet adni egy új elemet.

A hozzá adni kívánt elemet beirás közben ellenörzi hogy létezik e már az adott név vagy azonosító

## 3.Modositás

Bekér egy Ssámla id-t melyet ha tartalmaz az xml fájl akkor feladja az alábbi választásokat:
1.	Név modositás-> Az elöbiekben megadott id-hez tartozó elem nevét lehet modosítani, ellenörzi hogy létezik e már az adott név
2.	Pénz modositás -> Az elöbiekben megadott id-hez tartozó elem pnz értéket lehet modosítani

0	Cancel -> Kilépés a Főmenübe

## 4.Törlés

A megadott id-jü számlát kitörli
