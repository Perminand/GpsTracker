import { LoginForm } from "@userfront/next/client";

export default function LoginPage() {
  return <LoginForm theme='{"colors":{"light":"#ffffff","dark":"#5e72e4","accent":"#13a0ff","lightBackground":"#fdfdfd","darkBackground":"#2d2d2d"},"colorScheme":"auto","fontFamily":"Avenir, Helvetica, Arial, sans-serif","size":"default","extras":{"rounded":true,"gradientButtons":true,"hideSecuredMessage":true}}' />;
}