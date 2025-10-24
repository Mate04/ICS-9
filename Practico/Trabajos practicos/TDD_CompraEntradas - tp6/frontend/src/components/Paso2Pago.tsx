import { useState, useEffect } from "react"
import { api, type PedidoValidado } from "../services/api"

interface Paso2PagoProps {
  pedidoValidado: PedidoValidado
  emailUsuario: string
  onBack: () => void
  onConfirm: () => void
}

export default function Paso2Pago({ pedidoValidado, emailUsuario, onBack, onConfirm }: Paso2PagoProps) {
  const [metodosPago, setMetodosPago] = useState<string[]>([])
  const [metodoPagoSeleccionado, setMetodoPagoSeleccionado] = useState("")
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState("")

  useEffect(() => {
    const fetchMetodosPago = async () => {
      try {
        const metodos = await api.getMetodosPago()
        setMetodosPago(metodos)
      } catch (error) {
        console.error("Error fetching payment methods:", error)
      }
    }
    fetchMetodosPago()
  }, [])

  const formatearFecha = (fecha: string): string => {
    const year = fecha.substring(0, 4)
    const month = fecha.substring(4, 6)
    const day = fecha.substring(6, 8)
    return `${day}/${month}/${year}`
  }

  const formatearPrecio = (precio: number): string => {
    return new Intl.NumberFormat("es-AR", {
      style: "currency",
      currency: "ARS",
    }).format(precio)
  }

  const handleConfirmar = async () => {
    if (!metodoPagoSeleccionado) {
      setError("Debe seleccionar un método de pago")
      return
    }

    setLoading(true)
    setError("")

    try {
      const success = await api.confirmarPedido({
        idPedido: pedidoValidado.idPedido,
        metodoPago: metodoPagoSeleccionado,
        email: emailUsuario,
      })

      if (success) {
        onConfirm()
      } else {
        setError("Error al confirmar el pedido. Intente nuevamente.")
      }
    } catch (error) {
      console.error("Error confirming order:", error)
      setError("Error al procesar el pago. Intente nuevamente.")
    } finally {
      setLoading(false)
    }
  }

  const getBotonTexto = () => {
    if (loading) return "Procesando..."
    if (metodoPagoSeleccionado === "Mercado Pago") return "Pagar con Mercado Pago"
    return "Confirmar compra"
  }

  return (
    <div className="w-full max-w-3xl mx-auto">
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-4 sm:p-6">
        <h2 className="text-lg sm:text-xl md:text-2xl font-semibold text-neutral mb-2">Resumen de compra</h2>
        <p className="text-sm text-gray-500 mb-4 sm:mb-6">Revisa los detalles de tu pedido</p>

        <div className="space-y-4 mb-6">
          {/* Order ID and Date */}
          <div className="bg-base-100 rounded-xl p-4 border border-gray-100">
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
              <div>
                <p className="text-sm font-medium text-gray-500 mb-1">Número de pedido</p>
                <p className="text-base font-semibold text-neutral">#{pedidoValidado.idPedido}</p>
              </div>
              <div>
                <p className="text-sm font-medium text-gray-500 mb-1">Fecha de emisión</p>
                <p className="text-base font-semibold text-neutral">
                  {formatearFecha(pedidoValidado.resumen.fechaEmision)}
                </p>
              </div>
            </div>
          </div>

          {/* Visitors Details */}
          <div>
            <h3 className="text-sm font-medium text-neutral mb-3">Detalle de entradas</h3>
            <div className="space-y-3">
              {pedidoValidado.resumen.visitantes.map((visitante, index) => (
                <div key={index} className="bg-base-100 rounded-xl p-4 border border-gray-100">
                  <div className="flex items-center justify-between gap-3">
                    <div className="flex-1 min-w-0">
                      <p className="font-medium text-neutral capitalize text-sm truncate">
                        {visitante.categoria}
                      </p>
                      <p className="text-sm text-gray-500 capitalize">Entrada {visitante.tipoEntrada}</p>
                    </div>
                    <p className="text-base font-semibold text-primary whitespace-nowrap">
                      {formatearPrecio(visitante.subtotal)}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Total */}
          <div className="bg-primary/5 rounded-xl p-4 border border-primary/20">
            <div className="flex items-center justify-between gap-3">
              <p className="text-base font-medium text-neutral">Total a pagar</p>
              <p className="text-xl font-bold text-primary whitespace-nowrap">
                {formatearPrecio(pedidoValidado.importeTotal)}
              </p>
            </div>
          </div>

          {/* Payment Method Selection */}
          <div>
            <label className="block text-sm font-medium text-neutral mb-3">Método de pago</label>
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
              {metodosPago.map((metodo) => (
                <button
                  key={metodo}
                  type="button"
                  onClick={() => setMetodoPagoSeleccionado(metodo)}
                  className={`p-4 rounded-xl border-2 transition-all text-left min-h-[56px] ${
                    metodoPagoSeleccionado === metodo
                      ? "border-primary bg-primary/5"
                      : "border-gray-200 bg-white hover:border-gray-300"
                  }`}
                >
                  <div className="flex items-center gap-3">
                    <div
                      className={`w-5 h-5 rounded-full border-2 flex items-center justify-center flex-shrink-0 ${
                        metodoPagoSeleccionado === metodo ? "border-primary" : "border-gray-300"
                      }`}
                    >
                      {metodoPagoSeleccionado === metodo && <div className="w-3 h-3 rounded-full bg-primary"></div>}
                    </div>
                    <span className="font-medium text-neutral text-sm">{metodo}</span>
                  </div>
                </button>
              ))}
            </div>
            {error && <p className="text-error text-sm mt-2">{error}</p>}
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex flex-col-reverse sm:flex-row items-stretch sm:items-center justify-between gap-3 pt-4 border-t border-gray-100">
          <button
            type="button"
            onClick={onBack}
            disabled={loading}
            className="btn btn-ghost text-neutral disabled:opacity-50 h-12 text-base"
          >
            Volver
          </button>
          <button
            type="button"
            onClick={handleConfirmar}
            disabled={loading || !metodoPagoSeleccionado}
            className="btn btn-primary text-white sm:px-8 disabled:opacity-50 h-12 text-base"
          >
            {loading ? <span className="loading loading-spinner loading-sm"></span> : getBotonTexto()}
          </button>
        </div>
      </div>
    </div>
  )
}