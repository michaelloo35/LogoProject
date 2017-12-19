#Instrukcja uruchomienia

* uruchomić metodę main w klasie pl.edu.agh.to2.dziki.application.Main

zaczynamy program komendą restart
* lista działających komend :

    * forward [wartosc] - porusza sie do przodu o okreslona wartosc
    * backward [wartosc] - porusza sie do przodu o okreslona wartosc
    * left [wartosc] - porusza sie w lewo o okreslona wartosc
    * right [wartosc] - porusza sie w prawo o okreslona wartosc
    * turn [kąt] obraca dzika o zadany kat (dodatni w prawo, ujemny w lewo)
    * hide - dzik się chowa poruszenie wyłania go z ukrycia
    * show - dzik wychodzi z ukrycia
    * lift - uniesienie dzika
    * lower - opuszczenie dzika
    * restart - ustawia dzika na poczatkowe polozenie


###Milestone 2:
Co zostało zrobione:
* Testy najbardziej odpowiedzialnych klas
* Poprawiona funkcjonalność komendy LOOP
* Zaimplementowanie historii inputu (za pomocą strzałek góra dół)
* Zaimplementowania funkcja autouzupełniania komend (za pomocą klawisza tab)
* Można wpisywać więcej niż 1 komende na 1 linię


Plany:
 * Więcej testów
 * Poprawienie struktury?
 * Dokumentacja (diagram + nazwanie użytych wzorców + javadoci)
 * Zaimplementowanie funkcjonalności przycisku undo z użyciem wzorca Command.
 * Zagnieżdżanie LOOP'ów
 * (OPCJONALNIE) wczytywanie komend z pliku
 * (OPCJONALNIE) zapisywanie zrzutu rysunku
 * (OPCJONALNIE) mechanizm aliastow na komendy
 

przykładowe wywołanie:
* RESTART
* TURN 30
* FORWARD 100
* HIDE
* SHOW
* LIFT
* FORWARD 300
* TURN 49
* FORWARD 300
* LOWER
* RIGHT 30
* LOOP 18 TURN 10 FORWARD 15 ENDLOOP
* LOOP 10 TURN 5 FORWARD 15 TURN 10 BACKWARD 15 ENDLOOP
* turn 30 turn -40 forward 100 backward 50 TURN 30 hide show LIFT forward 100 LOWER loop 10 BACKWARD 30 TURN -50 ENDLOOP