# Data model

## Organization
An (educational) organization is a graph with `Account` entities representing the nodes and `AccountRelationShip` mapping the edges.

The class `OrganizationService` deals with managing the organization graph in a consistent way.

Main rules are:
- There is only one Account of type `DISTRICT`
- A district `HAS` different School accounts
- A school `HAS` different classes
- An individual is `ENROLLED_IN` class, school, and district accounts

```mermaid
classDiagram
    class Account {
        id: String
        name: String 
        type: AccountType
    }

    class AccountRelationship {
        id: Long
        type: AccountRelationshipType
    }

    AccountRelationship --> Account: source
    AccountRelationship --> Account: target
```

## FeatureSet Bundles, Products, and Features

`Features` represent reusable functionalities or content that can be grouped into a `FeatureSet`, and multiple products can be packaged into a `Product` according to some `Program` of study.

The `ProductMembership` class tracks the triple `<Product, FeatureSet, Program>`.

```mermaid
classDiagram
    class Feature {
        id: String
        name: String 
    }

    class FeatureSet {
        id: String
        name: String 
    }

    FeatureSet *-- Feature: features

    class Program {
        code: String
        name: String
    }

    class Product {
        id: String
        name: String
        description: String
    }

    class ProductMembership {
    }

    Product *-- ProductMembership: product
    FeatureSet *-- ProductMembership: featureSet
    Program *-- ProductMembership: program
```
