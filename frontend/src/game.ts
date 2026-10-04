export type Game = {
  id: number;
  title: string;
  repoUrl: string | null;
  status: "complete" | "in_progress" | "inactive" | "unknown";
  progressPercent: number | null;
  progressMetric: string;
  matchedFunctions: number | null;
  totalFunctions: number | null;
  progressNote: string | null;
  sourceUrl: string | null;
  checkedOn: string | null;
};
