[![CircleCI](https://circleci.com/gh/kemoT1990TM/adventure-game.svg?style=svg)](https://circleci.com/gh/kemoT1990TM/adventure-game)
[![codecov](https://codecov.io/gh/kemoT1990TM/adventure-game/branch/master/graph/badge.svg)](https://codecov.io/gh/kemoT1990TM/adventure-game)

# Adventure-game

You can play by clicking here: [PLAY!](http://adventure.tkjavadev.com)

This repo is for my own project Adventure Game based on Collosal Cave Adventure.
App is deployed on Google Cloud Platform. Database is running on MySQL instance.

I am using Spring, Hibernate, Docker, Thymeleaf and Gradle for build.

## Running locally

Requirements: JDK 17 or newer (Gradle itself is downloaded by the wrapper).

```
./gradlew bootRun          # Linux/macOS
gradlew.bat bootRun        # Windows
```

Open http://localhost:8080. By default the game uses an in-memory H2 database,
filled on startup from `locations_79.txt`, `directions_79.txt` and `items_79.txt`.

To use an existing MySQL database instead:

```
./gradlew bootRun --args='--spring.profiles.active=mysql'
```

Connection settings come from `DB_URL`, `DB_USER` and `DB_PASSWORD`
(defaults: `jdbc:mysql://localhost:3306/adventure`, `kemot`/`kemot`).

Run tests: `./gradlew test`
