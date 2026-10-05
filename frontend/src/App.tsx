import { useEffect, useState } from "react";
import { fetchCatalog, type Catalog } from "./api";
import { GameCard } from "./GameCard";
import "./App.css";

const REPO_URL = "https://github.com/ACrush14/another-decomp-tracker";

function App() {
  const [catalog, setCatalog] = useState<Catalog | null>(null);

  useEffect(() => {
    fetchCatalog().then(setCatalog);
  }, []);

  if (catalog === null) {
    return (
      <main>
        <h1>decomp-tracker</h1>
        <p>Carregando catálogo...</p>
      </main>
    );
  }

  return (
    <main>
      <h1>decomp-tracker</h1>
      <p>{catalog.games.length} jogos no catálogo</p>
      {catalog.source === "snapshot" && (
        <p className="notice">
          Backend offline: exibindo uma cópia estática do catálogo. O código está no{" "}
          <a href={REPO_URL}>GitHub</a>.
        </p>
      )}
      <section className="game-grid">
        {catalog.games.map((game) => (
          <GameCard key={game.id} game={game}></GameCard>
        ))}
      </section>
      <footer className="authorship">
        Cerca de 80% do código foi escrito à mão por Anderson de Lima. A configuração de deploy e o
        plano B estático do catálogo foram preparados com o Claude.
      </footer>
    </main>
  );
}

export default App;
