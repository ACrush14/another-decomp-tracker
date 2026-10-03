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
| Aidyn Chronicles: The First Mage | [blackgamma7/Aidyn](https://github.com/blackgamma7/Aidyn) | In progress (amount unknown) | The README says the repository holds pseudocode, symbol tables and headers, not usable code. No progress number is published | 2026-10-03 |
| Banjo-Kazooie | [n64decomp/banjo-kazooie](https://github.com/n64decomp/banjo-kazooie) | Complete (100%) | The README heading shows 100.0000% with a progress badge. Covers US v1.0, US v1.1, JP and PAL. GitHub mirror of a GitLab primary repository | 2026-10-03 |
| Blast Corps | [retroplastic/blastcorps](https://github.com/retroplastic/blastcorps) | Inactive since 2021-12 (amount unknown) | The README says naming and documentation are in progress and publishes no progress number. Last commit: 2021-12-28. "Inactive" is **inferred** from the commit history, not declared by the authors | 2026-10-03 |
| Body Harvest | [jaytheham/body-harvest-decompilation](https://github.com/jaytheham/body-harvest-decompilation) | In progress: 46.8% of functions matched (1,264 of 2,703); 62.3% decompiled, counting non-matching ones | The project's own [`docs/progress.json`](https://github.com/jaytheham/body-harvest-decompilation/blob/master/docs/progress.json), generated 2026-04-16, which also keeps a weekly history from 2026-02-01. The repository has had commits since then (latest 2026-10-03), so the figures may be out of date | 2026-10-03 |
| Bomberman 64 | [MarioMaster96/bomberman-64](https://github.com/MarioMaster96/bomberman-64) | Inactive since 2020-11 (amount unknown) | The README has only a title and a one-line description: no version, status or progress number. 7 commits, all between 2020-11-21 and 2020-11-25. "Inactive" is **inferred** from the commit history | 2026-10-03 |
| Mario Kart 64 | [n64decomp/mk64](https://github.com/n64decomp/mk64) | Complete (100%) | The README's progress badge ([`total_progress.svg`](https://n64decomp.github.io/mk64/total_progress.svg)) reads "Total progress 100.0%". Covers USA, EUR 1.0 and EUR 1.1. The README adds that naming and documentation continue. Last commit: 2026-08-12 | 2026-10-03 |
| Mario Party 3 | [mariopartyrd/marioparty3](https://github.com/mariopartyrd/marioparty3) | In progress (amount unknown) | The README has only build instructions. The repository ships a script (`tools/progress.py`) that can compute function counts, but it needs a local build from the user's own ROM, and no number is published. US release only. Last commit: 2026-09-24 | 2026-10-03 |
| Mischief Makers | [Drahsid/mischief-makers](https://github.com/Drahsid/mischief-makers) | In progress: 53.52% of code matched (US 1.1) | The [decomp.dev report](https://decomp.dev/Drahsid/mischief-makers) linked from the project's README ("matched code percent", code size 1.99 MB) at commit d57c1e8, 2026-09-19. The README says the other versions have very minor support | 2026-10-03 |
| Neon Genesis Evangelion 64 | [farisawan-2000/evangelion](https://github.com/farisawan-2000/evangelion) | Inactive since 2025-07 (amount unknown) | The README calls it a work-in-progress decompilation and publishes no progress number. About 124 commits; last commit: 2025-07-13. "Inactive" is **inferred** from the commit history | 2026-10-03 |
| Paper Mario | [pmret/papermario](https://github.com/pmret/papermario) | Complete (100%) | The progress badges in the project's README (served by [papermar.io](https://papermar.io/progress-us)) read 100.00% for US, PAL and iQue, and 100.17% for JP (above 100%, as published). The README still calls it a "work-in-progress decompilation". Last commit: 2026-09-26 | 2026-10-03 |
| Pokémon Snap | [ethteck/pokemonsnap](https://github.com/ethteck/pokemonsnap) | In progress: 97.35% of code matched (US); 99.81% of functions | The [decomp.dev report](https://decomp.dev/ethteck/pokemonsnap/us) ("matched code percent", code size 987.26 kB) at commit 1978bb5, 2026-08-29. The README's "US Functions" badge reads 99.81%. US release only | 2026-10-03 |
| Pokémon Stadium | [ethteck/pokemonstadium](https://github.com/ethteck/pokemonstadium) | Inactive since 2021-10 (amount unknown) | The README calls it a WIP decomp and publishes no progress number. US release only; about 11 commits; last commit: 2021-10-17. "Inactive" is **inferred** from the commit history | 2026-10-03 |
| Rocket: Robot on Wheels | [RocketRet/Rocket-Robot-On-Wheels](https://github.com/RocketRet/Rocket-Robot-On-Wheels) | Inactive since 2023-01 (amount unknown) | The README has only a description and build instructions; no progress number. About 83 commits; last commit: 2023-01-15. "Inactive" is **inferred** from the commit history | 2026-10-03 |
| Yoshi's Story | [decompals/yoshis-story](https://github.com/decompals/yoshis-story) | In progress: 13.31% of code (US); **stale figure** | The README calls it a WIP decomp (US only). The progress badge in the README (served by progress.deco.mp, code size 594,144 bytes) reads 13.31% (79,072 bytes), but that service last recorded a data point on 2024-12-25 and the latest commit (2026-04-19) removed the progress tracking, so the number is out of date. Last commit: 2026-04-19 | 2026-10-03 |
| Turok 3: Shadow of Oblivion | [Drahsid/turok3](https://github.com/Drahsid/turok3) | Archived, inactive since 2023-12 (amount unknown) | The repository is archived on GitHub and the README says it is no longer maintained and kept for preservation, because the author finished Turok 3 Remaster. No progress number is published. 35 commits; last commit: 2023-12-04. Here "inactive" is **declared by the authors**, not only inferred | 2026-10-03 |
| The Legend of Zelda: Ocarina of Time | [zeldaret/oot](https://github.com/zeldaret/oot) | Complete (100%) | The README progress badge ([zelda.deco.mp](https://zelda.deco.mp/games/oot)) reads 100%. The README still calls it a "WIP decompilation" and warns that the codebase keeps evolving. It builds many versions, including the N64 NTSC and PAL ones, GameCube and iQue. Last commit: 2026-09-30 | 2026-10-03 |
| The Legend of Zelda: Majora's Mask | [zeldaret/mm](https://github.com/zeldaret/mm) | Complete (100%) | The README progress badge ([zelda.deco.mp](https://zelda.deco.mp/games/mm)) reads 100%. The README still calls it a "WIP decompilation". Only the N64 US version is supported for now. Last commit: 2026-09-26 | 2026-10-03 |
| Space Station Silicon Valley | [mkst/sssv](https://github.com/mkst/sssv) | In progress: 81.09% (US) | The README calls it a work-in-progress decompilation. Its progress badge for the US version ([`us.json`](https://sssv.deco.mp/us.json)) reads 81.09%, and the README does not say what the percent measures. The README adds that the EU version has had minimal decompilation effort. 153 commits; last commit: 2026-08-10 | 2026-10-03 |
| Snowboard Kids | [tenry92/sbk-decomp](https://github.com/tenry92/sbk-decomp) | Inactive since 2022-12 (amount unknown) | The README says the project is at a very early stage and cannot re-create a ROM yet, and publishes no progress number. 12 commits; last commit: 2022-12-21. "Inactive" is **inferred** from the commit history, not declared by the authors | 2026-10-03 |
| Quest 64 | [mallos31/quest64](https://github.com/mallos31/quest64) | Archived, inactive since 2023-01 (amount unknown) | The repository is archived on GitHub (read-only). The README has only build instructions for the US ROM, with no progress number and no statement about the project status. 48 commits; last commit: 2023-01-13. "Inactive" rests on the archived flag and the commit history | 2026-10-03 |
| GoldenEye 007 | [n64decomp/007](https://github.com/n64decomp/007) | In progress (amount unknown) | The README calls it a WIP decompilation and builds the US, JP and EU ROMs. Progress is published on a [separate status page](https://kholdfuzion.github.io/goldeneyestatus/), whose figures were not interpreted here, so no percent is registered. GitHub mirror of a GitLab primary repository. Last commit: 2026-08-17 | 2026-10-03 |
| Dinosaur Planet | [zestydevy/dinosaur-planet](https://github.com/zestydevy/dinosaur-planet) | In progress: 74.34% total | The README calls it a WIP decompilation of the game as released by Forest of Illusion on 2021-02-20, and warns that the built ROM is not "shiftable" yet. Its Total badge ([dino-status](https://shinx.dev/dino-status/)) reads 74.34%, with Core at 100.00% and DLLs at 68.91% (data updated 2026-10-02). The README does not say what the percent measures. 825 commits; last commit: 2026-10-02 | 2026-10-03 |
| Conker's Bad Fur Day | [mkst/conker](https://github.com/mkst/conker) | Archived: 5.92% of code (US), figure from 2021 | The repository is archived on GitHub (read-only), so "inactive" here rests on the archived flag, not on the 12-month rule (last commit: 2026-05-10). The README calls the project "in its infancy" and covers the US version only. Its badge data ([`latest.json`](https://conker.deco.mp/latest.json)) gives 5.92% of code bytes in C (133,216 of 2,251,008) and 1,365 of 5,916 functions converted to C, not necessarily matching. That data point is from 2021-09-01, so the figure may be stale. 74 commits | 2026-10-03 |

Status labels: *complete* and *in progress* follow what each repository says about itself. *Inactive* means no commits for more than 12 months, or a repository archived on GitHub; it is **inferred from the commit history or the archived flag**, not declared by the authors.

The remaining games will be added as they are verified. The game list used as the base of the catalog comes from Wikipedia (CC BY-SA license): [List of Nintendo 64 games](https://en.wikipedia.org/wiki/List_of_Nintendo_64_games) and [List of best-selling Nintendo 64 video games](https://en.wikipedia.org/wiki/List_of_best-selling_Nintendo_64_video_games).

## Game catalog API

The table above is also stored in the `game` table and served by the API:

```
GET /games
GET /games?status=inactive
```

`status` is optional and accepts `complete`, `in_progress`, `inactive` or `unknown`. Without it, all games are returned.

The data comes from [`backend/src/main/resources/catalog/n64-decomp-progress.csv`](backend/src/main/resources/catalog/n64-decomp-progress.csv). To load it into the database, start the application with the loader switched on (it is off by default):

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments=--decomp.catalog.load-on-startup=true
```

The load is an upsert by title, so running it again does not duplicate anything.

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
- `game` (V3, fixed by V4): the N64 game catalog. One record per game, with repository URL, status (`complete`, `in_progress`, `inactive` or `unknown`), progress percent, `progress_metric` (what that percent measures), optional function counts, a note, the source URL and the date it was checked. Fields the source does not state stay `NULL`.

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
- [x] N64 game catalog: `game` table, CSV loader and `GET /games?status=` (15 games so far)
- [ ] Grow the catalog with more games from the Wikipedia lists
- [ ] Import other repositories (Kirby 64 and others)
- [ ] React + TypeScript front-end
