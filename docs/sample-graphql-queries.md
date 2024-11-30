# Quick reference for GraphQL queries 

# Organization

## Querying
```graphql
// Just get all districts (this should only return one Account)
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
// Get all district account and its immediate relationships 
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

## Students

```graphql
// Get all individuals and their enrollments
query {
  accounts(criteria: {
    type: INDIVIDUAL
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
```graphql
// Get an individual student (the id will be different for each run!)
query {
  account(id: "<your account id>") {
    id
    name
    type
    relationships {
      target {
        id
        name
        type
      }
      type
    }
  }
}
```
