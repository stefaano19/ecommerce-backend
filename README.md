# Ecommerce Backend

A Java REST backend for e-commerce products, categories, shopping carts, users, and orders.

## Stack

- Java 11 and Spring Boot 2.7.13
- Spring Web, Spring Data JPA, Spring Security, and OAuth2 Resource Server
- PostgreSQL and Maven Wrapper

## Configuration

Install JDK 11 and PostgreSQL. Adjust `src/main/resources/application.yaml`
for your local PostgreSQL connection and OAuth2/JWT issuer before starting the
service. Its defaults expect PostgreSQL on `localhost:5432`, a JWT issuer on
`localhost:9009`, and the HTTP service on port `8080`. Use local development
credentials; do not commit secrets.

## Run and test

```sh
sh ./mvnw spring-boot:run
sh ./mvnw test
```

## API

Routes are defined in the controllers under
`src/main/java/com/example/ecommerce/controllers`. Endpoints with a role
require a JWT with that authority.

| Method | Path | Purpose / access |
|---|---|---|
| GET | `/prodotti` | List products |
| GET | `/prodotti/pagine` | Paginated product list (`numeroPagine`, `dimensionePagina`, `sortBy`) |
| GET | `/prodotti/ricerca/per_nome` | Search by optional `nome` |
| GET | `/prodotti/getProdottiCategoria/{IDcategoria}` | Products in a category |
| POST | `/prodotti/aggiungi` | Add product (`admin`) |
| POST | `/prodotti/aggiungiAlCarrello` | Add to current customer's cart (`cliente`) |
| GET | `/prodotti/prodottiCarrello` | View current customer's cart (`cliente`) |
| POST | `/prodotti/rimuoviProdottoCarrello` | Remove a cart item (`cliente`) |
| GET | `/prodotti/rimuoviProdottiCarrello` | Clear the cart (`cliente`) |
| GET | `/categorie` | List categories |
| GET | `/categorie/getCategoria/{IDcategoria}` | Get a category |
| POST | `/categorie/aggiungi` | Add category (`admin`) |
| POST | `/categorie/aggiorna/{IDcategoria}` | Update category (`admin`) |
| POST | `/utenti/registrazione` | Register a user (`cliente`) |
| GET | `/utenti` | List users |
| GET | `/utenti/getUtente` | Check current customer (`cliente`) |
| POST | `/ordini/creazione` | Create an order (`cliente`) |
| GET | `/ordini` | List orders (`admin`) |
