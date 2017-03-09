# XMLParserToAWebPage


Dokument XML znajduje się na stronie https://nvd.nist.gov/cpe.cfm.

W celu uruchomienia projektu należy posiadać  środowisko wykonawcze (np. Eclipse JEE) wraz z postawionym serwerem (np. Apache Tomcat). Projekt należy uruchamiać na serwerze. Skonfigurować należy właściwości projektu : Build Path gdzie dołączyć należy zewnętrzny plik .jar – Gson.jar (add external jar), zawierający niezbędne do działania projektu biblioteki.
Pobrany dokument XML ma określoną ścieżkę, którą należy wpisać do klasy MOJPARSER. W celu włączenia którejkolwiek ze stron należy uprzednio uruchmoić Servlet, a  następnie plik index5.html – ponieważ jest to strona główna.


Po poprawnym skonfigurowaniu środowiska projektu możemy uruchmić stroinę główną – index5.html, na której mamy do wyboru dwie zakładki : 
Przegladaj – wyświetlane zostają elementy – po 10 na stronie. Opisy elementów są domyślnie ukryte. Pojawiają się po kliknięciu w nazwę. Dodatkowo za pomocą buttonów można wyświetlić kolejną i poprezdnią stronę względem obecnej, a także wpisać interesujący numer strony w pole tekstowe.
Szukaj – w pole tekstowe wpisujemy wyraz na podstawie którego dobierane są wyniki wyświetlane poniżej.


Strona została przetestowna poprzez wpisywanie różnych ciągów w pola tekstowe, a także reakcji na zdarzenia w postaci kliknięć w buttony. Wyniki  licznych prób zwrócone w postaci odpowiedzi serwera na stronie zostały przeanalizowane i zweryfikowane z danymi dostępnymi na stronie producenta.


Aplikacja ma szerokie możliwości rozwoju. Do projektu można dodać funkcjonalność łączności z bazą danych, która będzie aktualizowana w  przypadku pojawienia się nowego rekordu CPE.
Dodatkowo aplikacja może zostać dopracowana pod względem graficznym, a także mogą zostać dodane zaawansowane opcje wyszukiwania.

