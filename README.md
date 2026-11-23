[![CircleCI](https://circleci.com/gh/kemoT1990TM/adventure-game.svg?style=svg)](https://circleci.com/gh/kemoT1990TM/adventure-game)
[![codecov](https://codecov.io/gh/kemoT1990TM/adventure-game/branch/master/graph/badge.svg)](https://codecov.io/gh/kemoT1990TM/adventure-game)

# Adventure-game

My own Adventure Game project based on Colossal Cave Adventure.

## Tech stack

- Java 25, Spring Boot 4.2 (WebFlux, reactive MongoDB, Spring Session with Hazelcast)
- Thymeleaf UI served by the backend
- REST API (`/api/game/...`) used by the Angular frontend in a separate repo: `adventure-game-angular`
- Maven, Docker, Kubernetes

## Requirements

- JDK 25
- Maven 3.9+
- MongoDB (e.g. `docker run -d --name mongodb -p 27017:27017 mongo:8`)

On first start the app imports game data from `locations_79.txt`, `directions_79.txt`
and `items_79.txt` into MongoDB (database `test`).

## Running locally

```
mvn spring-boot:run
```

The app starts on http://localhost:8081.

| Variable     | Default     | Description   |
|--------------|-------------|---------------|
| `MONGO_HOST` | `localhost` | MongoDB host  |
| `MONGO_PORT` | `27017`     | MongoDB port  |

### Angular frontend

In `adventure-game-angular` run `npm install` and `npm start`, then open http://localhost:4200.
The dev server proxies `/api` to `http://localhost:8081`.

## REST API

Base path: `/api/game`

| Method | Path            | Description                       |
|--------|-----------------|-----------------------------------|
| GET    | `/start`        | Start a game                      |
| GET    | `/restart`      | Restart the game                  |
| GET    | `/exit`         | Quit the game                     |
| GET    | `/location`     | Current location                  |
| GET    | `/gates`        | Available directions              |
| GET    | `/items`        | Items in the current location     |
| GET    | `/inventory`    | Items in the inventory            |
| GET    | `/results`      | Score, visited locations and rank |
| GET    | `/item/message` | Last item message                 |
| GET    | `/gate/message` | Last direction message            |
| POST   | `/gate/change`  | Move through a gate (JSON body)   |
| POST   | `/item/take`    | Take an item (JSON body)          |
| POST   | `/item/drop`    | Drop an item (JSON body)          |

## Tests

```
mvn verify
```

Runs the unit tests and writes a JaCoCo report to `target/site/jacoco/jacoco.xml`.

## Docker

```
mvn package
docker build -t kemot1990tm/adventure-game .
docker run -p 8081:8081 -e MONGO_HOST=<mongo-host> kemot1990tm/adventure-game
```

## Kubernetes

Manifests are in `kubernetes/`. `kubernetes/newkube.sh` creates MongoDB storage, MongoDB
and the app (the app reaches MongoDB through the `mongodb` service).
