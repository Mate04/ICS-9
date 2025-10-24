import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  server: {
    port: 5173,
    host: true, // Equivalente a '0.0.0.0'
    watch: {
      usePolling: true, // Mejor hot-reload en Docker
    },
    proxy: {
      "/pedido": {
        target: process.env.VITE_BACKEND_URL || "http://localhost:8080", // ← Cambio principal: usar nombre del servicio Docker
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/pedido/, "/pedido"),
      },
    },
  },
});
