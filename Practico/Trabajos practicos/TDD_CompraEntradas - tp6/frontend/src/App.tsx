import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import FormularioMultipaso from "./pages/FormularioMultipaso";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/comprar" element={<FormularioMultipaso />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;