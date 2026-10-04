import type { Game } from "./game";

type Props = { game: Game };

export function GameCard({ game }: Props) {
  return (
    <article className="game-card">
      <header>
        <h2>{game.title} </h2>

        <span className={`status status-${game.status}`}>{game.status}</span>
      </header>

      <p className="percent">
        {game.progressPercent === null ? "-" : `${game.progressPercent}%`}
      </p>

      <p className="metric">{game.progressMetric}</p>
      <footer>Consultado em {game.checkedOn}</footer>
    </article>
  );
}
