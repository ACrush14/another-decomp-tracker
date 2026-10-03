# decomp-tracker

[Read in English](README.md)

Serviço que acompanha o progresso de projetos de **decompilação de jogos de N64**.

Ele ingere o histórico de commits de repositórios de decompilação, guarda no PostgreSQL e expõe séries históricas por uma API REST (hoje, commits por mês). Um catálogo dos jogos de N64 com o progresso de cada decompilação está em construção, e um front-end em React + TypeScript está previsto, mas fora do escopo por enquanto.

> Este repositório **não contém ROMs, assets nem código da Nintendo**, e o `.gitignore` bloqueia as extensões mais comuns de ROM.

## Progresso das decompilações de N64

Os valores abaixo são **aproximados** e mostram a fonte de cada um. Eles não vêm de um número oficial por commit, e por isso não são calculados pelo ETL (ver [ADR 0002](docs/adr/0002-fonte-do-progresso.md)).

| Jogo | Repositório | Progresso | Fonte e observação | Consultado em |
|---|---|---|---|---|
| Super Mario 64 | [n64decomp/sm64](https://github.com/n64decomp/sm64) | Completa (100%) | O README do repositório descreve uma decompilação completa das versões JP, US, EU, Shindou e iQue | 02/10/2026 |
| Kirby 64: The Crystal Shards | [Kirby64Ret/kirby64](https://github.com/Kirby64Ret/kirby64) | ~60%, medido em bytes | Mensagem de commit do PR #61 (aberto, não mesclado). **Não verificado** | 02/10/2026 |
| Aidyn Chronicles: The First Mage | [blackgamma7/Aidyn](https://github.com/blackgamma7/Aidyn) | Em andamento (quantidade desconhecida) | O README diz que o repositório tem pseudocódigo, tabelas de símbolos e cabeçalhos, e não código utilizável. Nenhum número de progresso é publicado | 03/10/2026 |
| Banjo-Kazooie | [n64decomp/banjo-kazooie](https://github.com/n64decomp/banjo-kazooie) | Completa (100%) | O título do README mostra 100.0000% com um selo de progresso. Cobre US v1.0, US v1.1, JP e PAL. Espelho no GitHub de um repositório principal no GitLab | 03/10/2026 |
| Blast Corps | [retroplastic/blastcorps](https://github.com/retroplastic/blastcorps) | Inativo desde 12/2021 (quantidade desconhecida) | O README diz que a nomeação e a documentação estão em andamento e não publica número de progresso. Último commit: 28/12/2021. "Inativo" é **inferido** do histórico de commits, e não declarado pelos autores | 03/10/2026 |
| Body Harvest | [jaytheham/body-harvest-decompilation](https://github.com/jaytheham/body-harvest-decompilation) | Em andamento: 46,8% das funções com match (1.264 de 2.703); 62,3% decompiladas, contando as que ainda não batem | O [`docs/progress.json`](https://github.com/jaytheham/body-harvest-decompilation/blob/master/docs/progress.json) do próprio projeto, gerado em 16/04/2026, que também guarda um histórico semanal desde 01/02/2026. O repositório teve commits depois disso (o último em 03/10/2026), então os números podem estar desatualizados | 03/10/2026 |

Rótulos de status: *completa* e *em andamento* seguem o que cada repositório diz sobre si mesmo. *Inativo* significa que não há commits recentes; é **inferido do histórico de commits**, e não declarado pelos autores.

Os demais jogos serão acrescentados conforme forem verificados. A lista de jogos usada como base do catálogo vem da Wikipedia (licença CC BY-SA): [List of Nintendo 64 games](https://en.wikipedia.org/wiki/List_of_Nintendo_64_games) e [List of best-selling Nintendo 64 video games](https://en.wikipedia.org/wiki/List_of_best-selling_Nintendo_64_video_games).

## Commits por mês

Depois de importar os commits de um projeto, a API devolve a série de commits por mês:

```
GET /projects/{nome}/commits-per-month
```

Exemplo de resposta para o Super Mario 64 (30 commits, de 2019-08 a 2023-08), só os primeiros itens:

```json
[
  {"month": "2019-08", "commits": 4},
  {"month": "2019-09", "commits": 1},
  {"month": "2019-10", "commits": 3}
]
```

Para um projeto que não existe, a resposta é `404`.

Para importar os commits do Super Mario 64, suba a aplicação com a importação ligada (por padrão ela fica desligada):

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments=--decomp.import.on-startup=true
```

Rodar de novo não duplica nada.

## Como funciona

A ingestão é um ETL:

1. **Extract:** busca os commits na API do GitHub, com paginação.
2. **Transform:** converte a resposta da API em registros simples (hash e data do commit). O cálculo de progresso por commit foi adiado, porque os repositórios não publicam esse número (ver [ADR 0002](docs/adr/0002-fonte-do-progresso.md)).
3. **Load:** grava no PostgreSQL, de forma idempotente (rodar duas vezes não duplica dados).

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

Definido nas migrations do Flyway:

- `project` (V1): nome (único) e URL do repositório.
- `snapshot` (V1): um registro por commit de um projeto, com data do commit, funções reconstruídas e total de funções. Reservada para quando houver progresso por commit; hoje não é usada.
- `repo_commit` (V2): um registro por commit extraído do GitHub (hash e data). A restrição `UNIQUE (project_id, commit_sha)` impede gravar o mesmo commit duas vezes para o mesmo projeto, base da idempotência do ETL.

## Sobre os dados de progresso

Os repositórios de decompilação investigados não publicam um número oficial de progresso por commit. O Super Mario 64 foi publicado já completo, e o Kirby 64 não tem uma contagem oficial na branch principal.

O valor do Kirby 64 (cerca de 60%, medido em bytes) vem da mensagem de um commit de um PR aberto e não mesclado (PR #61), em 26/08/2026. É um valor **aproximado e não verificado**, e por isso não é gravado na tabela `snapshot`. O raciocínio completo está no [ADR 0002](docs/adr/0002-fonte-do-progresso.md).

## Estrutura

```
.
├── backend/            # Projeto Spring Boot (pacote base com.decomptracker)
├── docs/adr/           # Registros de decisões de arquitetura (em português)
└── docker-compose.yml  # PostgreSQL 16 para desenvolvimento local
```

## Status

Em desenvolvimento inicial.

- [x] Esqueleto Spring Boot e PostgreSQL via Docker Compose
- [x] Migration inicial (`project` e `snapshot`) aplicada pelo Flyway
- [x] Entidades JPA e repositories
- [x] Testes de persistência (salvar/ler um snapshot e violar a unicidade)
- [x] Definição da fonte do número de progresso (ver [ADR 0002](docs/adr/0002-fonte-do-progresso.md))
- [x] ETL: extração dos commits (API do GitHub) e carga idempotente em `repo_commit` (migration V2)
- [x] API REST: `GET /projects/{nome}/commits-per-month`
- [ ] Catálogo dos jogos de N64: quais têm repositório de decompilação e o progresso de cada um
- [ ] Importar outros repositórios (Kirby 64 e demais)
- [ ] Front-end React + TypeScript
