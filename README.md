# book-service

Dies ist Ali Hlayels Herausforderungsprojekt. Das Projekt wird basierend auf den in Challenge.md definierten Anforderungen erstellt. Ich habe ein Microservice implemtiert. Ich habe IntelliJ IDE für die Entwicklung verwendet. 
Ich habe Spring Boot, Hibernate, h2 database (development purpose) und RESTFul services als Infrastruktur benutzt.

# Book-Service

Der Book-Microservice wird als RESTFul-API erstellt. es enthält Methoden über Buch-Details:
1. Anzeige Einzelne Bücher nach ISBN suchen.
2. einzelnes Buch löschen
3. liege neu Bucher
4. Anzeige alle vorhandenen Bücher

# Wie um den Code auszuführen?
Der Book-service wird mit Docker erstellt. Der Root-Container enthält die Docker-Datei, Z.B Java 11-Container,  und ein Kopie von Book-Service-JAR-Dateien. 
Sie konnen auch das Project Konfiguration im die docker-compose.yml finden. 
Die Komponenten werden in einer lokalen Umgebung ausgeführt.
Um den Dienst auszuführen, führen Sie das Shell-Skript sh build-all.sh im Stammverzeichnis des Projekts im Terminal aus.

# Test
Der Code wurde bereits mit dem Junit-Test getestet. Ich habe auch die Swagger-API zum Testen des Projekts zur Laufzeit verwendet. Sie können den Dienst mit swagger testen, indem Sie den folgenden Link eingeben: http: // localhost: 8080 / swagger-ui.html #


# Endpunkten
Die folgenden Bilder zeigen die Testergebnisse für Book-service mit swagger.
<img width="1427" alt="Screenshot 2020-09-20 at 20 49 41" src="https://user-images.githubusercontent.com/68303228/101357134-514bb580-3899-11eb-936d-be7aa1148a1d.png">

# Create New book

wenn Sie Postman benutzen sie konnen diese Json Entity auch benutzeb
{
  "authors": [
    {
      "firstName": "Ali",
      "lastName": "Hlayel"
    }, {
      "firstName": "Leya",
      "lastName": "Hlayel"  
}
  ],
  "isbn": "987-987-789",
  "title": "Deutsch Als Fremdsprache"
}
URL : "http://localhost:8080/book-service/book"
Method: POST
Hinweis: Ein Bucher kann mehr als ein Autor. 
Swagger Beispiel: 
<img width="1427" alt="Screenshot 2020-09-20 at 20 49 41" src="https://user-images.githubusercontent.com/68303228/101386229-2e32fd00-38bd-11eb-8487-d29e66815437.png">

# Get Book by Isbn
URL: http://localhost:8080/book-service/book/987-987-789
Method: GET
Hinweis: Buch kann von ISBN gesucht 
Swagger Beispiel: 
<img width="1427" alt="Screenshot 2020-09-20 at 20 49 41" src="https://user-images.githubusercontent.com/68303228/101386578-a994ae80-38bd-11eb-9610-da613201a9b1.png">

# Get all Books
URL: http://localhost:8080/book-service/books
Method: GET
Swagger Beispiel: 
<img width="1427" alt="Screenshot 2020-09-20 at 20 49 41" src="https://user-images.githubusercontent.com/68303228/101386935-19a33480-38be-11eb-834b-7e9887acf326.png">

# Delete book by Isbn
Method: Delete
URL: http://localhost:8080/book-service/987-987-789
Swagger Beispiel: 
<img width="1427" alt="Screenshot 2020-09-20 at 20 49 41" src="https://user-images.githubusercontent.com/68303228/101387314-933b2280-38be-11eb-9c50-7fc195d2d5d5.png">

