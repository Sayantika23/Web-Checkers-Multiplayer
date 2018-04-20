# Web Checkers
The application allows players to play checkers with other players who are currently signed-in. The game user interface (UI) supports a game experience using drag-and-drop browser capabilities for making moves.

## Architecture
The WebCheckers webapp uses a Java-based web server. We use the Spark web micro framework and the FreeMarker template engine to handle HTTP requests and generate HTML responses


## Prerequisites
- Java 8
- Maven

## How to run it

1. Clone the repository and go to the root directory.
2. Execute `mvn compile exec:java`
3. Open in your browser `http://localhost:4567/`
4. Start a game and begin playing.
