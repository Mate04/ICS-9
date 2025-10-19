import React, { useState, useEffect } from "react";
// Corregimos la importación para traer la función directamente
import { getMetodosPago, type PedidoValidado } from "../services/api";

// Asumo la estructura de estas props basándome en el contexto
interface Paso2PagoProps {
  pedidoValidado: PedidoValidado;
  onBack: () => void;
  onConfirm: (metodoPago: string) => void;
}

export default function Paso2Pago({
  pedidoValidado,
  onBack,
  onConfirm,
}: Paso2PagoProps) {
  const [metodosPago, setMetodosPago] = useState<string[]>([]);
  const [metodoPagoSeleccionado, setMetodoPagoSeleccionado] =
    useState<string>("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchMetodosPago = async () => {
      console.log("1. Buscando MÉTODOS DE PAGO..."); // Mensaje para saber que la función se ejecuta
      try {
        const metodos = await getMetodosPago();
        console.log("2. Respuesta de MÉTODOS DE PAGO recibida:", metodos); // ¿Qué recibimos realmente?

        if (Array.isArray(metodos) && metodos.length > 0) {
          setMetodosPago(metodos);
          setMetodoPagoSeleccionado(metodos[0]); // Seleccionamos el primero por defecto
          console.log("3. Estado 'metodosPago' actualizado con éxito.");
        } else {
          console.warn(
            "La respuesta del backend no es un array o está vacía:",
            metodos
          );
          setError("No se encontraron métodos de pago.");
        }
      } catch (err) {
        // Este es el punto clave: si hay un error, lo veremos aquí
        console.error("¡ERROR! La petición a getMetodosPago() falló:", err);
        setError("No se pudieron cargar los métodos de pago.");
      }
    };

    fetchMetodosPago();
  }, []); // El array vacío asegura que esto se ejecute solo una vez

  const handleConfirm = () => {
    if (metodoPagoSeleccionado) {
      onConfirm(metodoPagoSeleccionado);
    }
  };

  // JSX de ejemplo para que el componente sea funcional
  return (
    <div className="w-full max-w-xl mx-auto">
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8">
        <h2 className="text-2xl font-semibold text-neutral mb-2">
          Método de Pago
        </h2>
        <p className="text-sm text-gray-500 mb-8">
          Selecciona cómo quieres pagar tu pedido.
        </p>

        {error && <p className="text-error text-center mb-4">{error}</p>}

        <div className="space-y-4">
          {metodosPago.map((metodo) => (
            <div
              key={metodo}
              onClick={() => setMetodoPagoSeleccionado(metodo)}
              className={`
                        p-4 border rounded-lg cursor-pointer transition-all duration-200
                        ${
                          metodoPagoSeleccionado === metodo
                            ? "bg-primary/10 border-primary ring-2 ring-primary"
                            : "border-gray-200 hover:border-gray-400"
                        }
                    `}
            >
              <label className="flex items-center space-x-4">
                <input
                  type="radio"
                  name="metodoPago"
                  value={metodo}
                  checked={metodoPagoSeleccionado === metodo}
                  onChange={() => setMetodoPagoSeleccionado(metodo)}
                  className="radio radio-primary"
                />
                <span className="font-medium text-neutral">{metodo}</span>
              </label>
            </div>
          ))}
        </div>

        <div className="flex justify-between items-center mt-10">
          <button onClick={onBack} className="btn btn-ghost">
            Volver
          </button>
          <button
            onClick={handleConfirm}
            disabled={!metodoPagoSeleccionado || loading}
            className="btn btn-primary text-white"
          >
            {loading ? (
              <span className="loading loading-spinner"></span>
            ) : (
              "Confirmar Pago"
            )}
          </button>
        </div>
      </div>
    </div>
  );
}
