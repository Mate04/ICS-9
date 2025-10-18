import React from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const navigate = useNavigate();

    const handleLogin = (e: React.FormEvent) => {
        e.preventDefault();
        navigate("/formulario");
    };

    return (
        <div className="flex flex-col min-h-screen bg-gradient-to-b from-green-950 via-green-900 to-emerald-800 text-neutral">
        
        <header className="w-full bg-primary py-4 shadow-md">
            <div className="max-w-md mx-auto px-4 flex items-center justify-center gap-2">
            <h1 className="text-white text-2xl font-bold tracking-wide">
                EcoHarmony Park
            </h1>
            <span className="text-2xl text-secondary">🌿</span>
            </div>
        </header>

        <main className="flex-grow flex items-center justify-center px-4">
            <div className="card w-full max-w-md bg-base-100 shadow-xl rounded-3xl">
            <div className="card-body">
                <h2 className="text-2xl text-center text-primary font-semibold mb-4">
                Iniciar sesión
                </h2>

                <form onSubmit={handleLogin} className="flex flex-col gap-4">
                <input
                    type="email"
                    placeholder="parque@ejemplo.com"
                    required
                    className="input input-bordered input-primary w-full"
                />
                <input
                    type="password"
                    placeholder="Contraseña"
                    required
                    className="input input-bordered input-primary w-full"
                />
                <button
                    type="submit"
                    className="btn btn-success w-full text-white font-semibold tracking-wide shadow-md hover:shadow-lg transition-all"
                >
                    Ingresar
                </button>
                </form>
            </div>
            </div>
        </main>

        {/* Footer */}
        <footer className="py-4 text-center text-sm text-base-200 border-t border-base-300">
            EcoPark • Versión móvil 2025
        </footer>
        </div>
    );
}
