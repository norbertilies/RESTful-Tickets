Am folosit:
    - Spring Boot
    - Maven
    - JPA
    - HSQLDB
    - JSONDoc
    - Angular
    - Bootstrap
    - JDK: jdk1.8.0_111

Aplicatia web are 5 servicii RESTful. Pe langa cele 4 cerute, am implementat una in plus, anume /list_tickets (GET) pentru a-mi fi mai usor sa afisez rezultatele intr-un UI. Biletele pe care le folosim sunt bilete pentru cinema. Asadar, event-urile sunt filme care vor fi difuzate la o anumita data, cu o capacitate maxima de locuri. Biletele se pot cumpara doar in limita stocului disponibil (usor de vazut la eventul "Annabelle: Creation". Numarul de bilete ramase se actualizeaza in mod automat, iar cand numarul lor ajunge la 0, butonul de cumparare se blocheaza. Pe pagina unde sunt afisate biletele ("/tickets") sunt doua butoane, unul care permite stergerea lor din baza de date, iar cel de-al doilea ne returneaza un JSON cu toate informatiile pe care le avem despre bilet, inclusiv despre eventul la care este inscris acesta.