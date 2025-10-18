import React from "react"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Login() {
  const navigate = useNavigate()
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)

    // Mock login - simulate API call
    setTimeout(() => {
      localStorage.setItem("isAuthenticated", "true")
      navigate("/comprar")
      setLoading(false)
    }, 800)
  }

  return (
    <div className="min-h-screen bg-base-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="text-center mb-6 sm:mb-8">
          <h1 className="text-4xl sm:text-5xl font-bold text-primary mb-2">EcoPark</h1>
          <p className="text-sm sm:text-base text-gray-600">Inicia sesión para comprar tus entradas</p>
        </div>

        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6 sm:p-8">
          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label className="block text-sm font-medium text-neutral mb-2">Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="input input-bordered w-full bg-base-100 focus:outline-none focus:ring-2 focus:ring-primary h-12"
                placeholder="tu@email.com"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-neutral mb-2">Contraseña</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="input input-bordered w-full bg-base-100 focus:outline-none focus:ring-2 focus:ring-primary h-12"
                placeholder="••••••••"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="btn btn-primary w-full text-white disabled:opacity-50 h-12"
            >
              {loading ? <span className="loading loading-spinner loading-sm"></span> : "Iniciar sesión"}
            </button>
          </form>

          <div className="mt-6 text-center">
            <a href="#" className="text-sm text-primary hover:underline">
              ¿Olvidaste tu contraseña?
            </a>
          </div>
        </div>

        <p className="text-center text-xs sm:text-sm text-gray-500 mt-6">
          ¿No tienes cuenta?{" "}
          <a href="#" className="text-primary hover:underline font-medium">
            Regístrate aquí
          </a>
        </p>
      </div>
    </div>
  )
}