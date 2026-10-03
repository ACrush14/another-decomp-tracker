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
| Bomberman 64 | [MarioMaster96/bomberman-64](https://github.com/MarioMaster96/bomberman-64) | Inativo desde 11/2020 (quantidade desconhecida) | O README tem só um título e uma descrição de uma linha: nenhuma versão, estado ou número de progresso. 7 commits, todos entre 21/11/2020 e 25/11/2020. "Inativo" é **inferido** do histórico de commits | 03/10/2026 |
| Mario Kart 64 | [n64decomp/mk64](https://github.com/n64decomp/mk64) | Completa (100%) | O selo de progresso do README ([`total_progress.svg`](https://n64decomp.github.io/mk64/total_progress.svg)) mostra "Total progress 100.0%". Cobre USA, EUR 1.0 e EUR 1.1. O README acrescenta que a nomeação e a documentação continuam. Último commit: 12/08/2026 | 03/10/2026 |
| Mario Party 3 | [mariopartyrd/marioparty3](https://github.com/mariopartyrd/marioparty3) | Em andamento (quantidade desconhecida) | O README tem só instruções de build. O repositório traz um script (`tools/progress.py`) que pode calcular as contagens de funções, mas ele exige uma compilação local a partir da ROM original de quem roda, e nenhum número é publicado. Só a versão US. Último commit: 24/09/2026 | 03/10/2026 |
| Mischief Makers | [Drahsid/mischief-makers](https://github.com/Drahsid/mischief-makers) | Em andamento: 53,52% do código com match (US 1.1) | O [relatório do decomp.dev](https://decomp.dev/Drahsid/mischief-makers) ligado no README do projeto ("matched code percent", tamanho do código 1,99 MB) no commit d57c1e8, de 19/09/2026. O README diz que as outras versões têm suporte mínimo | 03/10/2026 |
| Neon Genesis Evangelion 64 | [farisawan-2000/evangelion](https://github.com/farisawan-2000/evangelion) | Inativo desde 07/2025 (quantidade desconhecida) | O README descreve uma decompilação em andamento e não publica número de progresso. Cerca de 124 commits; último commit: 13/07/2025. "Inativo" é **inferido** do histórico de commits | 03/10/2026 |
| Paper Mario | [pmret/papermario](https://github.com/pmret/papermario) | Completa (100%) | Os selos de progresso do README do projeto (servidos pelo [papermar.io](https://papermar.io/progress-us)) mostram 100,00% para US, PAL e iQue, e 100,17% para JP (acima de 100%, como publicado). O README ainda se descreve como uma "work-in-progress decompilation". Último commit: 26/09/2026 | 03/10/2026 |
| Pokémon Snap | [ethteck/pokemonsnap](https://github.com/ethteck/pokemonsnap) | Em andamento: 97,35% do código com match (US); 99,81% das funções | O [relatório do decomp.dev](https://decomp.dev/ethteck/pokemonsnap/us) ("matched code percent", código de 987,26 kB) no commit 1978bb5, de 29/08/2026. O selo "US Functions" do README mostra 99,81%. Só a versão US | 03/10/2026 |
| Pokémon Stadium | [ethteck/pokemonstadium](https://github.com/ethteck/pokemonstadium) | Inativo desde 10/2021 (quantidade desconhecida) | O README descreve uma decompilação em andamento (WIP) e não publica número de progresso. Só a versão US; cerca de 11 commits; último commit: 17/10/2021. "Inativo" é **inferido** do histórico de commits | 03/10/2026 |
| Rocket: Robot on Wheels | [RocketRet/Rocket-Robot-On-Wheels](https://github.com/RocketRet/Rocket-Robot-On-Wheels) | Inativo desde 01/2023 (quantidade desconhecida) | O README tem só uma descrição e instruções de build; nenhum número de progresso. Cerca de 83 commits; último commit: 15/01/2023. "Inativo" é **inferido** do histórico de commits | 03/10/2026 |
| Yoshi's Story | [decompals/yoshis-story](https://github.com/decompals/yoshis-story) | Em andamento: 13,31% do código (US); **valor desatualizado** | O README o chama de decomp em andamento (WIP), só a versão US. O selo de progresso do README (servido pelo progress.deco.mp, código de 594.144 bytes) mostra 13,31% (79.072 bytes), mas esse serviço registrou o último ponto em 25/12/2024 e o último commit (19/04/2026) removeu o acompanhamento de progresso, então o número está defasado. Último commit: 19/04/2026 | 03/10/2026 |
| Turok 3: Shadow of Oblivion | [Drahsid/turok3](https://github.com/Drahsid/turok3) | Arquivado, inativo desde 12/2023 (quantidade desconhecida) | O repositório está arquivado no GitHub e o README diz que não é mais mantido e fica só para preservação, porque o autor terminou o Turok 3 Remaster. Nenhum número de progresso é publicado. 35 commits; último commit: 04/12/2023. Aqui "inativo" é **declarado pelos autores**, e não só inferido | 03/10/2026 |
| The Legend of Zelda: Ocarina of Time | [zeldaret/oot](https://github.com/zeldaret/oot) | Completa (100%) | O selo de progresso do README ([zelda.deco.mp](https://zelda.deco.mp/games/oot)) mostra 100%. O README ainda o chama de "decompilação em andamento" (WIP) e avisa que o código continua mudando. Compila várias versões, incluindo as de N64 NTSC e PAL, GameCube e iQue. Último commit: 30/09/2026 | 03/10/2026 |
| The Legend of Zelda: Majora's Mask | [zeldaret/mm](https://github.com/zeldaret/mm) | Completa (100%) | O selo de progresso do README ([zelda.deco.mp](https://zelda.deco.mp/games/mm)) mostra 100%. O README ainda o chama de "decompilação em andamento" (WIP). Só a versão N64 US é suportada por enquanto. Último commit: 26/09/2026 | 03/10/2026 |
| Space Station Silicon Valley | [mkst/sssv](https://github.com/mkst/sssv) | Em andamento: 81,09% (US) | O README o chama de decompilação em andamento. O selo de progresso da versão US ([`us.json`](https://sssv.deco.mp/us.json)) mostra 81,09%, e o README não diz o que a porcentagem mede. O README acrescenta que a versão EU teve esforço mínimo de decompilação. 153 commits; último commit: 10/08/2026 | 03/10/2026 |
| Snowboard Kids | [tenry92/sbk-decomp](https://github.com/tenry92/sbk-decomp) | Inativo desde 12/2022 (quantidade desconhecida) | O README diz que o projeto está numa fase muito inicial e ainda não recria uma ROM, e não publica número de progresso. 12 commits; último commit: 21/12/2022. "Inativo" é **inferido** do histórico de commits, e não declarado pelos autores | 03/10/2026 |
| Quest 64 | [mallos31/quest64](https://github.com/mallos31/quest64) | Arquivado, inativo desde 01/2023 (quantidade desconhecida) | O repositório está arquivado no GitHub (somente leitura). O README tem só instruções de build da ROM US, sem número de progresso e sem dizer nada sobre o estado do projeto. 48 commits; último commit: 13/01/2023. "Inativo" se apoia no arquivamento e no histórico de commits | 03/10/2026 |
| GoldenEye 007 | [n64decomp/007](https://github.com/n64decomp/007) | Em andamento (quantidade desconhecida) | O README o chama de decompilação em andamento (WIP) e compila as ROMs US, JP e EU. O progresso é publicado numa [página de status separada](https://kholdfuzion.github.io/goldeneyestatus/), cujos números não foram interpretados aqui, então nenhuma porcentagem foi registrada. Espelho no GitHub de um repositório principal no GitLab. Último commit: 17/08/2026 | 03/10/2026 |
| Dinosaur Planet | [zestydevy/dinosaur-planet](https://github.com/zestydevy/dinosaur-planet) | Em andamento: 74,34% no total | O README o chama de decompilação em andamento (WIP) do jogo como foi liberado pela Forest of Illusion em 20/02/2021, e avisa que a ROM gerada ainda não é "shiftable". O selo Total ([dino-status](https://shinx.dev/dino-status/)) mostra 74,34%, com Core em 100,00% e DLLs em 68,91% (dados atualizados em 02/10/2026). O README não diz o que a porcentagem mede. 825 commits; último commit: 02/10/2026 | 03/10/2026 |
| Conker's Bad Fur Day | [mkst/conker](https://github.com/mkst/conker) | Arquivado: 5,92% do código (US), valor de 2021 | O repositório está arquivado no GitHub (somente leitura), então "inativo" aqui se apoia no arquivamento, e não na regra dos 12 meses (último commit: 10/05/2026). O README diz que o projeto está "na infância" e cobre só a versão US. Os dados do selo ([`latest.json`](https://conker.deco.mp/latest.json)) dão 5,92% dos bytes de código em C (133.216 de 2.251.008) e 1.365 de 5.916 funções convertidas para C, não necessariamente com match. Esse ponto de dados é de 01/09/2021, então o valor pode estar defasado. 74 commits | 03/10/2026 |

Rótulos de status: *completa* e *em andamento* seguem o que cada repositório diz sobre si mesmo. *Inativo* significa nenhum commit há mais de 12 meses, ou um repositório arquivado no GitHub; é **inferido do histórico de commits ou do arquivamento**, e não declarado pelos autores.

Os demais jogos serão acrescentados conforme forem verificados. A lista de jogos usada como base do catálogo vem da Wikipedia (licença CC BY-SA): [List of Nintendo 64 games](https://en.wikipedia.org/wiki/List_of_Nintendo_64_games) e [List of best-selling Nintendo 64 video games](https://en.wikipedia.org/wiki/List_of_best-selling_Nintendo_64_video_games).

## API do catálogo de jogos

A tabela acima também fica guardada na tabela `game` e é servida pela API:

```
GET /games
GET /games?status=inactive
```

O `status` é opcional e aceita `complete`, `in_progress`, `inactive` ou `unknown`. Sem ele, todos os jogos são devolvidos.

Os dados vêm de [`backend/src/main/resources/catalog/n64-decomp-progress.csv`](backend/src/main/resources/catalog/n64-decomp-progress.csv). Para carregá-los no banco, suba a aplicação com o carregador ligado (por padrão ele fica desligado):

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments=--decomp.catalog.load-on-startup=true
```

A carga é um upsert por título, então rodar de novo não duplica nada.

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
- `game` (V3, corrigida pela V4): o catálogo de jogos de N64. Um registro por jogo, com URL do repositório, status (`complete`, `in_progress`, `inactive` ou `unknown`), porcentagem de progresso, `progress_metric` (o que essa porcentagem mede), contagem de funções opcional, uma observação, a URL da fonte e a data da consulta. O que a fonte não informa fica `NULL`.

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
- [x] Catálogo dos jogos de N64: tabela `game`, carregador do CSV e `GET /games?status=` (15 jogos até agora)
- [ ] Ampliar o catálogo com mais jogos das listas da Wikipedia
- [ ] Importar outros repositórios (Kirby 64 e demais)
- [ ] Front-end React + TypeScript
