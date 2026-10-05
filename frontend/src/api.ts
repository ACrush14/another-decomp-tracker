import type { Game } from "./game";
import snapshot from "./data/catalog-snapshot.json";

// Em desenvolvimento, usa o backend local. Em producao so usa a API se VITE_API_URL estiver definida.
const API_URL: string | undefined =
  import.meta.env.VITE_API_URL ?? (import.meta.env.DEV ? "http://localhost:8080" : undefined);

const API_TIMEOUT_MS = 5000;

export type CatalogSource = "api" | "snapshot";

export type Catalog = {
  games: Game[];
  source: CatalogSource;
};

// Copia estatica do catalogo, gerada por scripts/generate-catalog-snapshot.py.
const SNAPSHOT = snapshot as Game[];

export async function fetchCatalog(): Promise<Catalog> {
  if (!API_URL) {
    return { games: SNAPSHOT, source: "snapshot" };
  }

  try {
    const response = await fetch(`${API_URL}/games`, {
      signal: AbortSignal.timeout(API_TIMEOUT_MS),
    });
    if (!response.ok) {
      throw new Error(`Falha ao buscar jogos: ${response.status}`);
    }
    return { games: await response.json(), source: "api" };
  } catch {
    return { games: SNAPSHOT, source: "snapshot" };
  }
}
