// app/layout.tsx

import { UserfrontProvider } from "@userfront/next/client";

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <UserfrontProvider tenantId="jb7yy7wb">
          {children}
        </UserfrontProvider>
      </body>
    </html>
  );
}

import { UserfrontProvider } from "@userfront/next/client";