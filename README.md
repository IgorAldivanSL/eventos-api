# API de Gerenciamento de Eventos

## Descrição do Projeto

Esta aplicação é uma API RESTful desenvolvida com Spring Boot que permite o gerenciamento completo de eventos, usuários e inscrições.

O sistema possibilita:

* Cadastro de eventos
* Gerenciamento de usuários
* Associação de categorias aos eventos
* Definição de endereços
* Inscrição de usuários em eventos

---

## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* Swagger (OpenAPI)
* Spring HATEOAS
* Docker (deploy)
* Render (deploy em nuvem)

---

## Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

* Controller → Responsável pelas requisições HTTP
* Service → Regras de negócio
* Repository → Acesso ao banco de dados
* Model (Entity) → Representação das entidades

---

## Entidades do Sistema

### Usuário

Representa os usuários do sistema.

Campos:

* id
* nome
* email
* tipo (ENUM)

---

### Evento

Representa um evento criado no sistema.

Campos:

* id
* nome
* data
* organizador
* categorias
* endereço

---

### Categoria

Classifica os eventos.

Campos:

* id
* nome

---

### Endereço

Define o local do evento.

Campos:

* id
* rua
* cidade
* estado

---

### Inscrição

Relaciona usuários com eventos.

Campos:

* id
* dataInscricao
* usuario
* evento

---

## Relacionamentos

O projeto atende todos os tipos exigidos:

* OneToOne

  * Evento → Endereço

* OneToMany / ManyToOne

  * Evento → Usuário (organizador)
  * Inscrição → Usuário
  * Inscrição → Evento

* ManyToMany

  * Evento ↔ Categoria

---

## Funcionalidades

### CRUD Completo para todas as entidades

Cada entidade possui:

* Criar (POST)
* Listar (GET)
* Buscar por ID (GET)
* Atualizar (PUT)
* Deletar (DELETE)

---

### Paginação

Todas as listagens utilizam Pageable, permitindo:

* page (página)
* size (quantidade de registros)

---

### Consultas Personalizadas

Exemplo implementado:

* Buscar eventos por nome:

```
GET /eventos/buscar?nome=xxx
```

---

### Documentação com Swagger

A API possui documentação interativa via Swagger:

```
/swagger-ui/index.html
```

Permite:

* Testar endpoints
* Visualizar parâmetros
* Ver respostas da API

---

### HATEOAS

As respostas incluem links para navegação entre recursos:

Exemplo:

* self
* lista
* delete

---

### Validações

Utilização de Bean Validation:

Exemplo:

```java
@NotBlank(message = "Nome do evento é obrigatório")
```

---

### Tratamento de erros

Mensagens de erro claras para:

* Dados inválidos
* Entidades não encontradas
* Requisições incorretas

---

## Como testar

### Localmente

```
http://localhost:8080/swagger-ui/index.html
```

### Produção (Render)

```
https://eventos-api-7rdp.onrender.com/swagger-ui/index.html
```

---

## Deploy com Docker

A aplicação foi preparada para execução em container Docker, garantindo:

* Portabilidade
* Independência de ambiente
* Facilidade de deploy

---

## Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de:

* Aplicar conceitos de APIs REST
* Trabalhar com Spring Boot
* Implementar persistência com JPA
* Utilizar boas práticas de arquitetura
* Documentar APIs com Swagger

---

## Requisitos Atendidos (Parte 1)

### Estrutura do Projeto

* Spring Boot
* Java 17+
* Maven
* H2 Database
* Spring Data JPA

---

### Entidades e Relacionamentos

* 5 entidades implementadas
* Relacionamentos:

  * OneToOne
  * OneToMany
  * ManyToMany
* Uso de validações
* Uso de ENUM

---

### Endpoints e Operações

* CRUD completo
* Paginação com Pageable
* Consulta personalizada
* Uso correto de status HTTP

---

### Documentação

* Swagger implementado
* Endpoints documentados

---

### HATEOAS

* Links adicionados nas respostas
* Navegação entre recursos

---

## Autor

Projeto desenvolvido por:

Igor Aldivan

---

## Conclusão

A API atende todos os requisitos da Parte 1, oferecendo uma solução completa, organizada e documentada para gerenciamento de eventos, seguindo boas práticas de desenvolvimento backend.
