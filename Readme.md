# hrzon-bank

Índice
========

* [Visão geral](#visão-geral)
* [Começando](#começando)
* [Como utilizar a aplicação](#como-utilizar-a-aplicação)
    * [Operações da API](#operações-da-api)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## Visão geral

`hrzon-bank` simula uma estrutura de banco. É possível criar clientes, associar contas e realizar transferências.

Este projeto foi construído com:

- JDK 17
- Maven
- Spring Boot
- Spring Data JPA + Hibernate
- Docker
- PostgreSQL
- JUnit5, Mockito and MockMVC

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## Começando

Após clonar este repositório, rode:

```
docker compose up -d
```

Espere alguns segundos, a aplicação estará rodando em `http://localhost:8080/`

> É recomendável que você tenha uma ferramenta como [Insomnia](https://insomnia.rest/)
> ou [Postman](https://www.postman.com/)
> para poder realizar os requests HTTP!

## Como utilizar a aplicação

### Operações da API

> **Atenção!**
> Todos os resources abaixo estão localizados em '/api/v1'

Operações em '/pessoas'

| Resource | POST                    |
|----------|-------------------------|
| /pessoas | Regista uma nova pessoa |

Operações em '/contas'

| Resource              | POST                                   | GET                            |
|-----------------------|----------------------------------------|--------------------------------|
| /contas               | Regista uma nova conta                 | -                              |
| /contas/{id}/deposito | Faz um novo depósito para a conta {id} | -                              |
| /contas/{id}/saque    | Faz um saque para a conta {id}         | -                              |
| /contas/{id}/saldo    | -                                      | Consulta o saldo da conta {id} |

Operações em '/transferencias'

| Resource        | POST                                         |
|-----------------|----------------------------------------------|
| /transferencias | Processa uma nova transferencia entre contas |
