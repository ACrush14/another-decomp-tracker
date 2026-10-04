import type { Game } from "./game";

const API_URL = "http://localhost:8080";

export async function fetchGames(): Promise<Game[]> {
  const response = await fetch(`${API_URL}/games`);
  if (!response.ok) {
    throw new Error(`Falha ao buscar jogos: ${response.status}`);
  }
  return response.json();
}
