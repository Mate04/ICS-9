import { useState } from "react"
import { useNavigate } from "react-router-dom"
import Paso1Formulario from "../components/Paso1Formulario"
import Paso2Pago from "../components/Paso2Pago"
import { api, type Visitante, type PedidoValidado } from "../services/api"

export default function FormularioMultipaso() {
  const navigate = useNavigate()
  const [pasoActual, setPasoActual] = useState(1)
  const [pedidoValidado, setPedidoValidado] = useState<PedidoValidado | null>(null)
  const [loading, setLoading] = useState(false)

  const handlePaso1Complete = async (fecha: string, visitantes: Visitante[]) => {
    setLoading(true)
    try {
      const resultado = await api.validarDatos(fecha, visitantes)
      setPedidoValidado(resultado)
      setPasoActual(2)
    } catch (error) {
      console.error("Error validating data:", error)
      alert("Error al validar los datos. Intente nuevamente.")
    } finally {
      setLoading(false)
    }
  }

  const handleVolver = () => {
    setPasoActual(1)
  }

  const handleConfirmarCompra = () => {
    alert(
      `¡Compra confirmada! Se ha enviado un correo de confirmación.\n\nPedido #${pedidoValidado?.idPedido}\nTotal: ${pedidoValidado?.importeTotal} ARS`,
    )
    navigate("/")
  }

  return (
    <div className="min-h-screen bg-base-100 py-6 sm:py-12 px-4">
      {/* Header */}
      <div className="max-w-3xl mx-auto mb-6 sm:mb-8">
        <h1 className="text-3xl sm:text-4xl font-bold text-primary mb-1 sm:mb-2">EcoPark</h1>
        <p className="text-sm sm:text-base text-gray-600">Compra de entradas</p>
      </div>

      {/* Progress Indicator */}
      <div className="max-w-3xl mx-auto mb-6 sm:mb-8">
        <div className="flex items-center justify-center gap-2 sm:gap-4">
          <div className="flex items-center gap-2">
            <div
              className={`w-9 h-9 sm:w-10 sm:h-10 rounded-full flex items-center justify-center font-semibold text-sm ${
                pasoActual === 1 ? "bg-primary text-white" : "bg-success text-white"
              }`}
            >
              {pasoActual === 1 ? "1" : "✓"}
            </div>
            <span className={`text-xs sm:text-sm font-medium ${pasoActual === 1 ? "text-neutral" : "text-gray-500"}`}>
              Información
            </span>
          </div>

          <div className="w-12 sm:w-16 h-0.5 bg-gray-300"></div>

          <div className="flex items-center gap-2">
            <div
              className={`w-9 h-9 sm:w-10 sm:h-10 rounded-full flex items-center justify-center font-semibold text-sm ${
                pasoActual === 2 ? "bg-primary text-white" : "bg-gray-300 text-gray-500"
              }`}
            >
              2
            </div>
            <span className={`text-xs sm:text-sm font-medium ${pasoActual === 2 ? "text-neutral" : "text-gray-500"}`}>
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
            <Paso2Pago pedidoValidado={pedidoValidado} onBack={handleVolver} onConfirm={handleConfirmarCompra} />
          )}
        </>
      )}
    </div>
  )
}