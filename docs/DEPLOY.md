# Deploy

Arquitetura: **frontend estatico** (Vercel) + **backend Spring Boot em Docker** (Render, Fly.io ou similar) + **PostgreSQL gerenciado** (Neon, Supabase ou o Postgres da propria plataforma).

> Confira os limites do plano gratuito de cada servico antes de escolher. Eles mudam com o tempo.

## 1. Banco de dados

Crie um banco PostgreSQL e anote host, nome do banco, usuario e senha. O Flyway cria as tabelas sozinho na primeira subida.

O Spring precisa de uma URL **JDBC** e de usuario e senha separados. Se o provedor entregar `postgresql://user:senha@host/banco?sslmode=require`, converta para:

```
DB_URL=jdbc:postgresql://host/banco?sslmode=require
DB_USER=user
DB_PASSWORD=senha
```

## 2. Backend

Build pelo `backend/Dockerfile` (contexto: pasta `backend`). Variaveis de ambiente:

| Variavel | Obrigatoria | Para que serve |
|---|---|---|
| `DB_URL` | sim | URL JDBC do banco |
| `DB_USER` | sim | usuario do banco |
| `DB_PASSWORD` | sim | senha do banco |
| `CORS_ALLOWED_ORIGINS` | sim | URL do frontend, ex.: `https://decomp-tracker.vercel.app` (varias separadas por virgula) |
| `DECOMP_CATALOG_LOAD_ON_STARTUP` | na 1a subida | `true` carrega o catalogo de jogos do CSV; e seguro repetir, ele atualiza por titulo |
| `PORT` | nao | a maioria das plataformas define sozinha; o padrao e 8080 |

Nao ative `DECOMP_IMPORT_ON_STARTUP` em producao: ele importa commits do GitHub na subida e esbarra no limite de requisicoes da API.

Teste depois do deploy: `https://SEU-BACKEND/games` deve devolver o JSON do catalogo.

## 3. Frontend

Projeto na Vercel (ou Netlify) apontando para a pasta `frontend`:

- Build command: `npm run build`
- Output directory: `dist`
- Variavel de ambiente: `VITE_API_URL=https://SEU-BACKEND` (sem barra no final)

A variavel e lida **no build**. Se mudar a URL do backend, faca um novo deploy do frontend.

Depois de ter a URL final do frontend, volte ao backend e confirme `CORS_ALLOWED_ORIGINS`.

## 4. Rodar local

```bash
docker compose up -d db
cd backend && ./mvnw spring-boot:run
cd frontend && npm install && npm run dev
```

Sem variaveis de ambiente, os padroes apontam para o banco local da porta 5433 e para o frontend em `localhost:5173`.

## Observacoes

- Planos gratuitos costumam "dormir" o backend sem acesso; a primeira requisicao depois disso demora. Vale avisar no README.
- Os testes do backend usam um PostgreSQL real em `localhost:5433` (ver `.github/workflows/ci.yml`, que sobe um via service container).
