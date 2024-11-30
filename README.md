# Entitlements demo

A simple demo application done with Spring Boot and Spring Data JPA for playing with Spring Boot.

The business application is just a simple entitlements system, where for the accounts in organization, we want to manage entitlements of product licenses.

Each product is a set of features (e.g., functionalities) or contents (e.g. HTML links ). Products are bundled together and licensed as a group.

Check [data model page](./docs/data-model.md) if you are interested!

# Requirements

* JDK 21 or better
* Docker 

# How to run it

Start the application with the following command:
```bash
mvn spring-boot:run
```

Note that Postgresql (and maybe other) supporting infrastructure will be bring on automatically thanks to Spring Boot support for docker compose.

See [compose.yml](compose.yaml) for more details.

Demo data are created on the fly, courtesy of the `DataLoader` class.

# Querying the data with GraphiQL

Open the graphiql console at `http://localhost:8080/graphiql` and have fun!

See [this page](./docs/sample-graphql-queries.md) for some sample queries to start with.