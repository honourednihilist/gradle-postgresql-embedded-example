# gradle-postgresql-embedded-example

This project is an example of how the [gradle-postgresql-embedded](https://github.com/honourednihilist/gradle-postgresql-embedded) plugin can be used. 

Run ```$ ./gradlew tasks --all``` to see a list of available tasks.

Key tasks are:
* ```$ ./gradlew startPostgres``` - runs embedded PostgreSQL server
* ```$ ./gradlew liquibaseEmbedded``` - applies database changesets to running embedded PostgreSQL server
* ```$ ./gradlew integrationTests``` - runs the integration tests
* ```$ ./gradlew check``` - runs all checks (including integration tests)
