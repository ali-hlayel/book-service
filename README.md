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
