"""Converte o catalogo CSV do backend em um JSON estatico para o frontend.

O frontend usa esse arquivo como plano B quando a API nao esta disponivel
(ver frontend/src/api.ts). Rode de novo sempre que o CSV mudar:

    python scripts/generate-catalog-snapshot.py
"""

import csv
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
SOURCE = ROOT / "backend/src/main/resources/catalog/n64-decomp-progress.csv"
TARGET = ROOT / "frontend/src/data/catalog-snapshot.json"


def text(value):
    return value.strip() or None


def number(value, cast):
    value = value.strip()
    return cast(value) if value else None


def main():
    games = []
    with SOURCE.open(encoding="utf-8", newline="") as file:
        for index, row in enumerate(csv.DictReader(file), start=1):
            games.append(
                {
                    "id": index,
                    "title": row["title"].strip(),
                    "repoUrl": text(row["repo_url"]),
                    "status": row["status"].strip(),
                    "progressPercent": number(row["progress_percent"], float),
                    "progressMetric": row["progress_metric"].strip(),
                    "matchedFunctions": number(row["matched_functions"], int),
                    "totalFunctions": number(row["total_functions"], int),
                    "progressNote": text(row["progress_note"]),
                    "sourceUrl": text(row["source_url"]),
                    "checkedOn": text(row["checked_on"]),
                }
            )

    TARGET.parent.mkdir(parents=True, exist_ok=True)
    TARGET.write_text(
        json.dumps(games, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
    )
    print(f"{len(games)} jogos gravados em {TARGET.relative_to(ROOT)}")


if __name__ == "__main__":
    main()
