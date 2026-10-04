import type { Game } from "./game";

export type SortKey = "alpha" | "percent" | "status";

const STATUS_ORDER: Record<Game["status"], number> = {
  complete: 0,
  in_progress: 1,
  inactive: 2,
  unknown: 3,
};

const byTitle = (a: Game, b: Game) =>
  a.title.replace(/^The /, "").localeCompare(b.title.replace(/^The /, ""));

export function sortGames(games: Game[], key: SortKey): Game[] {
  const copy = [...games];

  if (key === "percent") {
    return copy.sort(
      (a, b) =>
        (b.progressPercent ?? -1) - (a.progressPercent ?? -1) || byTitle(a, b),
    );
  }

  if (key === "status") {
    return copy.sort(
      (a, b) =>
        STATUS_ORDER[a.status] - STATUS_ORDER[b.status] || byTitle(a, b),
    );
  }

  return copy.sort(byTitle);
}
