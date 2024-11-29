# Entitlements demo app

A simple demo application done with Spring Boot and Spring Data JPA1 for playing with Spring Boot.

The business application is just a simple entitlements system, where for the accounts in organization, we want to manage entitlements
of product licenses.

Each product is a set of features (e.g., functionalities) or contents (e.g. HTML links ). Products are bundled together and licensed as a group.

# How to run it

Start the application with some demo data:
```bash
mvn spring-boot:run
```

Open the graphiql console at `http://localhost:8080/graphiql` and have fun!

# Sample queries

```graphql
// Get all district account and its immediate relationships 
query {
  accounts(criteria: {
    type: DISTRICT
  }) {
    id
    name
    type
  }
}
```

```graphql
// Get relationships too
query {
  accounts(criteria: {
    type: DISTRICT
  }) {
    id
    name
    type
    relationships {
      id
      type
      target {
        id
        name
      }
    }
  }
}
```

```graphql
// Get a specific account
query {
  account(id: "117") {
    id
    name
    type
  }
}
```