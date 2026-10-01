# decomp-tracker

Serviço que acompanha o progresso de projetos de **decompilação de jogos de N64**.

A ideia é ingerir o histórico de commits de repositórios de decompilação, calcular quantas funções já foram reconstruídas em cada commit (funções reconstruídas / total) e expor essa série histórica por uma API REST. Um front-end em React + TypeScript para visualizar a evolução está previsto, mas fora do escopo por enquanto.

A ingestão é um ETL:

1. **Extract:** busca os commits na API do GitHub.
2. **Transform:** calcula o progresso de cada commit.
3. **Load:** grava no PostgreSQL, de forma idempotente (rodar duas vezes não duplica dados).

> Este repositório **não contém ROMs, assets nem código da Nintendo**, e o `.gitignore` bloqueia as extensões mais comuns de ROM.

## Stack

- Java 21
- Spring Boot 4.1.1 (Web, Data JPA, Validation)
- PostgreSQL 16 (via Docker Compose)
- Flyway para versionar o schema
- Maven (wrapper incluso) e JUnit 5

## Como rodar

Pré-requisitos: Docker (com Compose) e um JDK 21 ou superior.

1. Suba o banco, na raiz do repositório:

   ```bash
   docker compose up -d
   ```

   O PostgreSQL fica exposto na porta **5433** do seu computador (e não na 5432, para não conflitar com uma instalação local do Postgres).

2. Suba a aplicação:

   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

   Na inicialização, o Flyway aplica as migrations de `backend/src/main/resources/db/migration` e o Hibernate (`ddl-auto: validate`) confere se as entidades batem com o schema.

3. Para parar tudo: `Ctrl+C` na aplicação e, na raiz, `docker compose down`.

### Credenciais locais

O banco usa `dev` / `dev`, definidos em `docker-compose.yml` e em `backend/src/main/resources/application.yaml`. Valem **apenas para desenvolvimento local**. Não reutilize essas credenciais em nenhum ambiente real.

## Modelo de dados

Definido em `V1__create_tables.sql`:

- `project`: nome (único) e URL do repositório.
- `snapshot`: um registro por commit de um projeto, com data do commit, funções reconstruídas e total de funções. A restrição `UNIQUE (project_id, commit_sha)` impede gravar o mesmo commit duas vezes para o mesmo projeto, base da idempotência do ETL.

## Estrutura

```
.
├── backend/            # Projeto Spring Boot (pacote base com.decomptracker)
├── docs/adr/           # Registros de decisões de arquitetura
└── docker-compose.yml  # PostgreSQL 16 para desenvolvimento local
```

## Status

Em desenvolvimento inicial.

- [x] Esqueleto Spring Boot e PostgreSQL via Docker Compose
- [x] Migration inicial (`project` e `snapshot`) aplicada pelo Flyway
- [ ] Entidades JPA e repositories
- [ ] Testes de persistência (salvar/ler um snapshot e violar a unicidade)
- [ ] Definição da fonte do número de progresso de cada decompilação
- [ ] ETL: extração, transformação e carga
- [ ] API REST da série histórica
- [ ] Front-end React + TypeScript
