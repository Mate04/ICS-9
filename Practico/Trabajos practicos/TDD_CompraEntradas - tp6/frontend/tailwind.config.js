/** @type {import('tailwindcss').Config} */
import daisyui from 'daisyui';

export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [daisyui],

  daisyui: {
    themes: [
      {
        ecopark: {
          "primary": "#014421",       // verde oscuro principal
          "secondary": "#6FBF73",     // verde claro para acentos
          "accent": "#A3D9A5",        // verde pastel / eco
          "neutral": "#2a2a2a",       // gris oscuro
          "base-100": "#f6f5f2",      // fondo claro cálido
          "info": "#4FC3F7",
          "success": "#007A4D",       // verde bosque
          "warning": "#FFD54F",
          "error": "#D32F2F",
        },
      },
      "light", // también mantenemos el tema claro base
    ],
  },
}