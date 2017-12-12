#Instrukcja uruchomienia

* uruchomić metodę main w klasie pl.edu.agh.to2.dziki.application.Main
* lista działających komend:

    * forward [wartosc] - porusza sie do przodu o okreslona wartosc
    * backward [wartosc] - porusza sie do przodu o okreslona wartosc
    * left [wartosc] - porusza sie w lewo o okreslona wartosc
    * right [wartosc] - porusza sie w prawo o okreslona wartosc
    * turn [kąt] obraca dzika o zadany kat
    * hide - dzik się chowa poruszenie wyłania go z ukrycia
    * show - dzik wychodzi z ukrycia
    * lift - uniesienie dzika
    * lower - opuszczenie dzika
    * restart - ustawia dzika na poczatkowe polozenie


###Milestone 2:
Założenia:
* Zaimplementowanie wszystkich komend (oprócz komendy clear) z użyciem wzorca strategia.
* Zaimplementowanie InputInterpretera odpowiedzialnego za tworzenie list taskow z zwalidowanego już wejscia
* Dekompocyzja klasy Boar
* Zaimplementowanie ViewUpdatera odpowiedzialnego za aktualizacje polo zenia dzika na canvasie i rysowani drogi


Plany na przyszłą iteracje:
 * Testy 
 * Dokumntacja
 * Diagram klas
 * Zaimplementowanie historii polecen z użyciem wzorca Command.
 * Dodanie granic rysowania dla dzika (moze?)
 
Jestesmy swiadomi, ze controller sie troche rozrosl i zamierzamy przenisc 
odpowiedzialnosc executowania taskow do modelu.

Dodatkowo input parser obecnie dziala w przypadku: 1 komenda na 1 linijke. Zostanie to poprawione
w przyszlej iteracji.
Istnieje tez maly blad w for loopie.