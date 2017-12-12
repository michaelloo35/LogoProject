#Instrukcja uruchomienia

* uruchomić metodę main w klasie pl.edu.agh.to2.dziki.application.Main
* lista działających komend:

    * "start": inicjalizuje dzika
    * "r": obraca dzika w prawo o 10 stopni
    * "rr": obraca dzika w lewo o 10 stopni
    * "f": dzik idzie do przodu o 10 jednostek zostawiajac ślad jesli jest opuszczony
    * "b": dzik idzie do tyłu o 10 jednostek zostawiajac ślad jesli jest opuszczony
    * "h": dzik się chowa poruszenie wyłania go z ukrycia
    * "s": dzik wychodzi z ukrycia
    * "lift": uniesienie dzika
    * "lower": opuszczenie dzika


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