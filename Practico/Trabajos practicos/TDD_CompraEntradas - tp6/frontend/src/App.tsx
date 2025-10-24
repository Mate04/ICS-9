import React, { useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import FormularioMultipaso from "./pages/FormularioMultipaso";

function App() {
  useEffect(() => {
    let meta = document.querySelector(
      'meta[name="viewport"]'
    ) as HTMLMetaElement | null;
    if (!meta) {
      meta = document.createElement("meta");
      meta.name = "viewport";
      document.head.appendChild(meta);
    }
    // prevent zoom and set proper width for mobile
    meta.content =
      "width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no";
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/comprar" element={<FormularioMultipaso />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
