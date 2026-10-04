import type { Game } from "./game";

export const STATUS_LABEL: Record<Game["status"], string> = {
  complete: "Completo",
  in_progress: "Em andamento",
  inactive: "Inativo",
  unknown: "Desconhecido",
};

export const METRIC_LABEL: Record<string, string> = {
  declared_complete: "Declarado completo",
  readme_pct: "Informado no README",
  badge_pct: "Selo de progresso",
  bytes_pct_unverified: "Bytes (não verificado)",
  decomp_dev_matched_code_pct: "Código com match (decomp.dev)",
  matched_functions_pct: "Funções com match",
  deco_mp_code_pct: "Código em C (deco.mp)",
  none: "Sem número publicado",
};
