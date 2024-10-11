# Contributing

## Trello Board

The backlog for this repo is available as a
[Trello board](https://trello.com/b/xKHlc7Xy/nozama). This is a public template.

- [ ] One person on your team should create a new board from this template

- [ ] Then add the rest of your team as members.

You can then use this board to track your progress throughout the workshop.

## API Documentation

The [API documentation](https://nozama-api.netlify.app/) contains information
about the endpoints that this project is planning to expose. You can use this to
understand the inputs and outputs of each endpoint.

This documentation gives you an idea of what the API will look like when it is
complete, and will be a useful reference as you work on the database and
creating the API itself.

## Database schema

This diagram will help you understand how the different tables in the database
are related to each other, and what data is stored in each table.

```mermaid
erDiagram
    USERS ||--o{ REVIEWS : writes
    PRODUCTS ||--o{ REVIEWS : has
    PRODUCTS ||--|{ PRODUCT_CATEGORIES : categorized_by
    CATEGORIES ||--|{ PRODUCT_CATEGORIES : categorizes

    USERS {
        string id PK
        string username
        string firstName
        string lastName
        string email
        string avatar
        string password
    }

    PRODUCTS {
        string id PK
        string name
        string description
        float price
        int stockQuantity
        string imageURL
    }

    CATEGORIES {
        string id PK
        string name
    }

    PRODUCT_CATEGORIES {
        string productId FK
        string categoryId FK
    }

    REVIEWS {
        string id PK
        string productId FK
        string userId FK
        int rating
        string reviewText
        datetime reviewDate
    }
```

## Merge conflicts

Merge conflicts are inevitable, but to help avoid them:

- communicate and plan to avoid people working on the same files

- don't push to `main` - use branches and pull requests

- pull frequently with `git pull origin main` to make sure your branch stays up
  to date and you integrate others' changes early

- push to your branch frequently with `git push` so others are aware of changes
  you have made

## Backlog

Visit `TODO.md` to find out what's on the backlog - all the workshop details can
be found here.
