import { useEffect, useState } from "react";
import { fetchGames } from "./api";
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
    </main>
  );
}

export default App;
