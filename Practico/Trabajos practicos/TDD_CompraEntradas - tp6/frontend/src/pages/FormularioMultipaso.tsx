import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Paso1Formulario from "../components/Paso1Formulario";
import Paso2Pago from "../components/Paso2Pago";
import { api, type Visitante, type PedidoValidado } from "../services/api";

export default function FormularioMultipaso() {
  const navigate = useNavigate();
  const [pasoActual, setPasoActual] = useState(1);
  const [pedidoValidado, setPedidoValidado] = useState<PedidoValidado | null>(
    null
  );
  const [loading, setLoading] = useState(false);
  //para controlar el modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  //agregue para manejar la cantidad de entradas y la fecha del evento
  const [fechaEvento, setFechaEvento] = useState<string>("");
  const [cantidadEntradas, setCantidadEntradas] = useState<number>(0);

  const [emailUsuario, setEmailUsuario] = useState<string>("");

  const handlePaso1Complete = async (
    fecha: string,
    visitantes: Visitante[]
  ) => {
    const email = localStorage.getItem("userEmail");
    setLoading(true);
    try {
      const resultado = await api.validarDatos(fecha, visitantes);
      setPedidoValidado(resultado);

      setFechaEvento(fecha);
      setCantidadEntradas(visitantes.length);
      setEmailUsuario(email || "");

      setPasoActual(2);
    } catch (error) {
      console.error("Error validating data:", error);
      alert("Error al validar los datos. Intente nuevamente.");
    } finally {
      setLoading(false);
    }
  };

  const handleVolver = () => {
    setPasoActual(1);
  };

  const handleConfirmarCompra = () => {
    setIsModalOpen(true);
  };

  const handleCerrarModalYNavegar = () => {
    setIsModalOpen(false);
    navigate("/comprar");
    window.location.reload();
  };

  return (
    <div className="min-h-screen bg-base-100 py-3 sm:py-4 md:py-6 px-2 sm:px-4">
      {/* Header */}
      <div className="max-w-3xl mx-auto mb-4 sm:mb-6 px-1">
        <h1 className="text-2xl sm:text-3xl md:text-4xl font-bold text-primary mb-1 sm:mb-2">
          EcoPark
        </h1>
        <p className="text-sm sm:text-base text-gray-600">Compra de entradas</p>
      </div>

      {/* Progress Indicator */}
      <div className="max-w-3xl mx-auto mb-4 sm:mb-6 px-2">
        <div className="flex items-center justify-center gap-1 sm:gap-2 md:gap-4">
          <div className="flex items-center gap-1">
            <div
              className={`w-7 h-7 sm:w-8 sm:h-8 md:w-10 md:h-10 rounded-full flex items-center justify-center font-semibold text-xs sm:text-sm ${
                pasoActual === 1
                  ? "bg-primary text-white"
                  : "bg-success text-white"
              }`}
            >
              {pasoActual === 1 ? "1" : "✓"}
            </div>
            <span
              className={`text-xs sm:text-sm font-medium ${
                pasoActual === 1 ? "text-neutral" : "text-gray-500"
              }`}
            >
              Información
            </span>
          </div>

          <div className="w-6 sm:w-8 md:w-12 h-0.5 bg-gray-300 flex-shrink-0"></div>

          <div className="flex items-center gap-1">
            <div
              className={`w-7 h-7 sm:w-8 sm:h-8 md:w-10 md:h-10 rounded-full flex items-center justify-center font-semibold text-xs sm:text-sm ${
                pasoActual === 2
                  ? "bg-primary text-white"
                  : "bg-gray-300 text-gray-500"
              }`}
            >
              2
            </div>
            <span
              className={`text-xs sm:text-sm font-medium ${
                pasoActual === 2 ? "text-neutral" : "text-gray-500"
              }`}
            >
              Pago
            </span>
          </div>
        </div>
      </div>

      {/* Form Steps */}
      {loading ? (
        <div className="flex items-center justify-center py-12 sm:py-16 md:py-20">
          <span className="loading loading-spinner loading-lg text-primary"></span>
        </div>
      ) : (
        <>
          {pasoActual === 1 && <Paso1Formulario onNext={handlePaso1Complete} />}

          {pasoActual === 2 && pedidoValidado && (
            <Paso2Pago
              pedidoValidado={pedidoValidado}
              emailUsuario={emailUsuario}
              onBack={handleVolver}
              onConfirm={handleConfirmarCompra}
            />
          )}
        </>
      )}
      {/*NUEVO ELEMENTO: Modal de Confirmación de Compra (usando DaisyUI) */}
      {isModalOpen && pedidoValidado && (
        <div className="modal modal-open">
          <div className="modal-box text-center max-w-md sm:max-w-lg mx-4">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="mx-auto h-12 w-12 sm:h-16 sm:w-16 text-success"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              strokeWidth={2}
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <h3 className="font-bold text-base sm:text-lg text-success mt-3 sm:mt-4">
              ¡Compra Confirmada!
            </h3>
            <p className="py-3 sm:py-4 text-sm sm:text-base text-gray-700">
              Se ha enviado un correo electrónico de confirmación con los
              detalles de tu pedido.
            </p>
            <div className="bg-base-200 p-3 sm:p-4 rounded-lg my-3 sm:my-4 text-left">
              <p className="font-bold text-sm sm:text-base mb-2 text-neutral">
                Detalles del Pedido:
              </p>
              <div className="space-y-1 sm:space-y-2">
                {fechaEvento && (
                  <p className="text-xs sm:text-sm">
                    <strong>Fecha del Evento:</strong>
                    <span className="text-neutral font-medium ml-1">
                      {/* Formato DD/MM/AAAA usando substrings del formato AAAAMMDD */}
                      {`${fechaEvento.substring(6, 8)}/${fechaEvento.substring(
                        4,
                        6
                      )}/${fechaEvento.substring(0, 4)}`}
                    </span>
                  </p>
                )}
                <p className="text-xs sm:text-sm">
                  <strong>Cantidad de Entradas:</strong>
                  <span className="text-neutral font-medium ml-1">
                    {cantidadEntradas}
                  </span>
                </p>

                <p className="text-xs sm:text-sm">
                  <strong>N° Pedido:</strong>{" "}
                  <span className="text-primary font-mono">
                    {pedidoValidado.idPedido}
                  </span>
                </p>

                <p className="text-sm sm:text-base">
                  <strong>Total:</strong>{" "}
                  <span className="font-bold text-lg sm:text-xl text-primary">
                    {pedidoValidado.importeTotal} ARS
                  </span>
                </p>
              </div>
            </div>
            <div className="modal-action justify-center">
              <button
                className="btn btn-primary w-full sm:w-auto"
                onClick={handleCerrarModalYNavegar}
              >
                Ir a la Página Principal
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
