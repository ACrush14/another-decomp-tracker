import type { Game } from "./game";
import { METRIC_LABEL, STATUS_LABEL } from "./labels";

type Props = { game: Game };

export function GameCard({ game }: Props) {
  return (
    <article className="game-card">
      <header>
        <h2>{game.title}</h2>
        <span className={`status status-${game.status}`}>
          {STATUS_LABEL[game.status]}
        </span>
      </header>

      <p className="percent">
        {game.progressPercent === null ? "-" : `${game.progressPercent}%`}
      </p>

      <p className="metric">
        {METRIC_LABEL[game.progressMetric] ?? game.progressMetric}
      </p>
      <footer>Consultado em {game.checkedOn}</footer>
    </article>
  );
}
