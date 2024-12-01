# Quick reference for GraphQL queries 

# Accounts and organization graph in general
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

# Product Bundles, Products, Features

```graphql
query {
  features {
    id
    name
  }
}
```

```graphql
# Get all products and their features
query {
  products {
    id
    name
    features {
      id
    }
  }
}
```


```graphql
# Get all product bundles and their constituent products 
query {
  productBundles {
    id
    name
    description
    
    productMemberships {
      id {
        product {
          id
          name
        }
        program {
          code
        }
      }
    }
  }
}
```

```graphql
# Get all the features unlocked within a product bundle 
# TODO Fix this since with Spring Query by Example this does not seem to work.
query {
  productMemberships(criteria: {
    programCode: "MATH_101"
  }) {
    id {
      program {
        code
      }
      product {
        id
        name
        features {
          id
        }
      }
    }
  }
}
```


