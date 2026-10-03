# decomp-tracker

[Leia em português](README.pt-BR.md)

A service that tracks the progress of **Nintendo 64 game decompilation projects**.

It ingests the commit history of decompilation repositories, stores it in PostgreSQL and exposes historical series through a REST API (today: commits per month). A catalog of N64 games with the progress of each decompilation is under construction, and a React + TypeScript front-end is planned but out of scope for now.

> This repository **contains no ROMs, assets or Nintendo code**, and `.gitignore` blocks the most common ROM extensions.

## N64 decompilation progress

The values below are **approximate** and list their source. They do not come from an official per-commit number, so the ETL does not calculate them (see [ADR 0002](docs/adr/0002-fonte-do-progresso.md), written in Portuguese).

| Game | Repository | Progress | Source and notes | Checked on |
|---|---|---|---|---|
| Super Mario 64 | [n64decomp/sm64](https://github.com/n64decomp/sm64) | Complete (100%) | The repository README describes a full decompilation of the JP, US, EU, Shindou and iQue releases | 2026-10-02 |
| Kirby 64: The Crystal Shards | [Kirby64Ret/kirby64](https://github.com/Kirby64Ret/kirby64) | ~60%, measured in bytes | Commit message of PR #61 (open, not merged). **Not verified** | 2026-10-02 |

The remaining games will be added as they are verified. The game list used as the base of the catalog comes from Wikipedia (CC BY-SA license): [List of Nintendo 64 games](https://en.wikipedia.org/wiki/List_of_Nintendo_64_games) and [List of best-selling Nintendo 64 video games](https://en.wikipedia.org/wiki/List_of_best-selling_Nintendo_64_video_games).

## Commits per month

After importing a project's commits, the API returns its commits-per-month series:

```
GET /projects/{name}/commits-per-month
```

Example response for Super Mario 64 (30 commits, from 2019-08 to 2023-08), first items only:

```json
[
  {"month": "2019-08", "commits": 4},
  {"month": "2019-09", "commits": 1},
  {"month": "2019-10", "commits": 3}
]
```

An unknown project returns `404`.

To import the Super Mario 64 commits, start the application with the import switched on (it is off by default):

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments=--decomp.import.on-startup=true
```

Running it again does not duplicate anything.

## How it works

The ingestion is an ETL:

1. **Extract:** fetches the commits from the GitHub API, with pagination.
2. **Transform:** converts the API response into simple records (commit hash and date). Per-commit progress calculation was postponed because the repositories do not publish that number (see [ADR 0002](docs/adr/0002-fonte-do-progresso.md)).
3. **Load:** writes to PostgreSQL idempotently (running it twice does not duplicate data).

## Stack

- Java 21
- Spring Boot 4.1.1 (Web, Data JPA, Validation)
- PostgreSQL 16 (via Docker Compose)
- Flyway for schema versioning
- Maven (wrapper included) and JUnit 5

## Running locally

Prerequisites: Docker (with Compose) and JDK 21 or newer.

1. Start the database, from the repository root:

   ```bash
   docker compose up -d
   ```

   PostgreSQL is exposed on port **5433** of your machine (not 5432, to avoid clashing with a local Postgres install).

2. Start the application:

   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

   On startup, Flyway applies the migrations in `backend/src/main/resources/db/migration` and Hibernate (`ddl-auto: validate`) checks that the entities match the schema.

3. To stop everything: `Ctrl+C` on the application and, from the root, `docker compose down`.

### Local credentials

The database uses `dev` / `dev`, defined in `docker-compose.yml` and in `backend/src/main/resources/application.yaml`. They are valid for **local development only**. Do not reuse them in any real environment.

## Data model

Defined in the Flyway migrations:

- `project` (V1): unique name and repository URL.
- `snapshot` (V1): one record per commit of a project, with commit date, reconstructed functions and total functions. Reserved for when per-commit progress exists; currently unused.
- `repo_commit` (V2): one record per commit extracted from GitHub (hash and date). The `UNIQUE (project_id, commit_sha)` constraint prevents the same commit from being stored twice for the same project, which is the basis of the ETL's idempotency.

## About the progress data

The decompilation repositories investigated do not publish an official per-commit progress number. Super Mario 64 was published already complete, and Kirby 64 has no official count on its main branch.

The Kirby 64 value (about 60%, measured in bytes) comes from the commit message of an open, unmerged PR (PR #61), dated 2026-08-26. It is **approximate and unverified**, so it is not stored in the `snapshot` table. The full reasoning is in [ADR 0002](docs/adr/0002-fonte-do-progresso.md).

## Project structure

```
.
├── backend/            # Spring Boot project (base package com.decomptracker)
├── docs/adr/           # Architecture decision records (in Portuguese)
└── docker-compose.yml  # PostgreSQL 16 for local development
```

## Status

Early development.

- [x] Spring Boot skeleton and PostgreSQL via Docker Compose
- [x] Initial migration (`project` and `snapshot`) applied by Flyway
- [x] JPA entities and repositories
- [x] Persistence tests (save/read a snapshot and violate uniqueness)
- [x] Decision on the source of the progress number (see [ADR 0002](docs/adr/0002-fonte-do-progresso.md))
- [x] ETL: commit extraction (GitHub API) and idempotent load into `repo_commit` (migration V2)
- [x] REST API: `GET /projects/{name}/commits-per-month`
- [ ] N64 game catalog: which games have a decompilation repository and the progress of each
- [ ] Import other repositories (Kirby 64 and others)
- [ ] React + TypeScript front-end
