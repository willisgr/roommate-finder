import type { Route } from "./+types/home";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "Roommate Finder" }
  ];
}

export default function Home() {
  return <main></main>;
}
