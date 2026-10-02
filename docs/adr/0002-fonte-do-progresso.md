# 0002. Fonte do progresso das decompilações

Data: 2026-10-02

## Contexto

O objetivo do projeto é mostrar a evolução do progresso de decompilações de jogos de N64 ao longo do tempo. Para isso, o modelo guarda, por commit, quantas funções já foram reconstruídas e o total (`matched_functions` e `total_functions` na tabela `snapshot`). Faltava definir **de onde sai esse número**. Foram investigados dois repositórios, olhando README, Wiki, Actions, histórico de commits e pull requests.

**Super Mario 64** (`n64decomp/sm64`)

- Tem 30 commits. O primeiro já contém quase 3 mil arquivos e mais de 600 mil linhas.
- Ou seja, o repositório foi publicado já completo: o histórico não mostra uma evolução.
- Não há número de progresso publicado.

**Kirby 64** (`Kirby64Ret/kirby64`)

- Tem 1221 commits, desde 23/05/2020 (o primeiro contém só um `.gitignore`), então o histórico cresce de forma gradual. A branch padrão se chama `splat`, não `main`.
- O README lista tarefas em texto (TODO), sem números. A Wiki está vazia. O Actions apenas compila o projeto.
- O único número de progresso encontrado está no PR #61, que vem de um fork, está aberto, sem revisão e não foi mesclado:
  - o texto do PR (13/08/2026) fala em funções em assembly restantes, de 2.042 para 897;
  - a mensagem do commit mais recente do PR (26/08/2026) fala em 61,35% decompilado, em **bytes** (1.260.736 de 2.008.576), mas essa divisão dá 62,77%.
- Portanto são duas medidas diferentes, que não se reconciliam, e de origem não verificada.

## Decisão

O progresso do Kirby 64 **não** será gravado na tabela `snapshot`.

Ele será registrado apenas como informação descritiva: cerca de 60% decompilado, medido em bytes, segundo o PR #61 (não mesclado) em 26/08/2026, valor aproximado e não verificado.

Motivos:

- não existe um número oficial por commit no repositório principal;
- a tabela `snapshot` guarda funções, e a medida disponível é em bytes;
- gravar um número aproximado como se fosse um fato no banco passaria uma confiança que o dado não tem.

Alternativas consideradas e descartadas por enquanto:

- **Mudar o modelo** (migration `V2`) para aceitar outra unidade de medida.
- **Gravar bytes** nas colunas de funções: descartada porque o nome das colunas passaria a mentir.

## Consequências

- O ETL não calcula progresso por commit por enquanto. O que ele pode extrair com segurança são os commits em si (datas, autores, quantidade).
- O valor do Kirby 64 é aproximado e depende de uma fonte externa não confirmada.
- O ETL deve ler o nome da branch padrão de cada repositório pela API do GitHub, e não assumir `main`.
- Se surgir um projeto que publique progresso por commit, ou se for decidido medir o progresso a partir do próprio repositório, a mudança no banco será feita por uma nova migration (`V2__...sql`), nunca editando a V1.
