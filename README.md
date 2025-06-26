# StudyBuddy

StudyBuddy ist eine Web-Applikation, mit der mehrere Benutzer gemeinsam Lernpläne erstellen, Aufgaben verwalten und Fortschritte verfolgen können. Die Anwendung richtet sich an Schüler und Lehrpersonen, die sich gegenseitig motivieren möchten.

## Backend starten

Das Backend basiert auf Spring Boot. Zum Starten benötigt man eine MySQL-Datenbank, welche per `docker-compose` bereitgestellt werden kann:

```bash
docker compose up -d
./mvnw spring-boot:run
```

Die Anwendung läuft anschliessend auf [http://localhost:8080](http://localhost:8080).

## Frontend entwickeln

Im Ordner `frontend` befindet sich ein kleines React-Frontend, das mit Vite erstellt wurde. Nach der Installation der Abhängigkeiten kann man es im Entwicklungsmodus starten:

```bash
cd frontend
npm install
npm run dev
```

Das Frontend erreicht man dann unter [http://localhost:5173](http://localhost:5173). 

Die React-Komponenten rufen die API-Endpunkte des Backends auf und ermöglichen Login, Registrierung und das Abrufen von Nachrichten.
