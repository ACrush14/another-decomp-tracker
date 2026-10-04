import { useEffect, useState } from "react";
import { fetchGames } from "./api";
import { GameCard } from "./GameCard";
import type { Game } from "./game";

function App() {
  const [games, setGames] = useState<Game[]>([]);

  useEffect(() => {
    fetchGames().then(setGames);
  }, []);

  return (
    <main>
      <h1>decomp-tracker</h1>
      <p>{games.length} jogos no catálogo</p>
      <section className="game-grid">
        {games.map((game) => (
          <GameCard key={game.id} game={game}></GameCard>
        ))}
      </section>
    </main>
  );
}

export default App;
