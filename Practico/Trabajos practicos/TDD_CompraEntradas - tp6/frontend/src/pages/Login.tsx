import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const emailRegex = /^[^\s@]+@([a-zA-Z0-9\-]+\.)+[a-zA-Z]{2,}$/;
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Limpiar errores previos al intentar el envío
    setEmailError("");
    setPasswordError("");

    let hasError = false;

    // 1. Validar Email
    if (!email) {
      // El navegador ya no verifica si está vacío; React lo hace ahora
      setEmailError("El email es obligatorio para iniciar sesión.");
      hasError = true;
    } else if (!emailRegex.test(email)) {
      // Usamos la regex estricta para el formato
      setEmailError(
        "El email debe tener un formato de dominio válido (ej: usuario@dominio.com)."
      );
      hasError = true;
    }

    // 2. Validar Contraseña
    if (!password) {
      // El navegador ya no verifica si está vacío; React lo hace ahora
      setPasswordError("La contraseña es obligatoria para iniciar sesión.");
      hasError = true;
    }

    // Si hay algún error, detenemos el submit
    if (hasError) {
      return;
    }

    setLoading(true);

    // Mock login - simulate API call
    setTimeout(() => {
      localStorage.setItem("isAuthenticated", "true");
      navigate("/comprar");
      setLoading(false);
    }, 800);
  };

  return (
    <div className="min-h-screen bg-base-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="text-center mb-6 sm:mb-8">
          <h1 className="text-3xl sm:text-4xl font-bold text-primary mb-1 sm:mb-2">
            EcoPark
          </h1>
          <p className="text-sm sm:text-base text-gray-600">
            Inicia sesión para comprar tus entradas
          </p>
        </div>

        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-5 sm:p-6 md:p-8">
          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label className="block text-sm font-medium text-neutral mb-2">
                Email
              </label>
              <input
                type="text"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailError("");
                }}
                className={`input input-bordered w-full bg-gray-50 focus:outline-none focus:ring-2 focus:ring-green-500 h-12 text-base rounded-lg
                  ${emailError ? "border-red-500" : "border-gray-300"}`}
                placeholder="tu@email.com"
                aria-invalid={emailError ? "true" : "false"}
                autoComplete="email"
              />
              {emailError && (
                <p className="text-red-500 text-xs mt-1 font-medium">
                  {emailError}
                </p>
              )}
            </div>

            <div>
              <label className="block text-sm font-medium text-neutral mb-2">
                Contraseña
              </label>
              <input
                type="password"
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                  setPasswordError("");
                }}
                className={`input input-bordered w-full bg-gray-50 focus:outline-none focus:ring-2 focus:ring-green-500 h-12 text-base rounded-lg
                    ${passwordError ? "border-red-500" : "border-gray-300"}`}
                placeholder="••••••••"
                autoComplete="current-password"
                aria-invalid={passwordError ? "true" : "false"}
              />
              {passwordError && (
                <p className="text-red-500 text-xs mt-1 font-medium">
                  {passwordError}
                </p>
              )}
            </div>

            <button
              type="submit"
              disabled={loading}
              className="btn btn-primary w-full text-white disabled:opacity-50 h-12 text-base"
            >
              {loading ? (
                <span className="loading loading-spinner loading-sm"></span>
              ) : (
                "Iniciar sesión"
              )}
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
  );
}
