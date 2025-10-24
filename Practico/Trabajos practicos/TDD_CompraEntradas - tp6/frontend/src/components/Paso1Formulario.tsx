import React, { useState, useEffect } from "react";
import { api, type TipoEntrada, type Visitante } from "../services/api";
import { v4 as uuidv4 } from "uuid";

interface Paso1FormularioProps {
  onNext: (fechaVisita: string, visitantes: Visitante[], email: string) => void;
}

interface VisitanteForm extends Visitante {
  id: string;
}

export default function Paso1Formulario({ onNext }: Paso1FormularioProps) {
  const [fechaVisita, setFechaVisita] = useState("");
  const [email, setEmail] = useState("");
  const [visitantes, setVisitantes] = useState<VisitanteForm[]>([
    { id: uuidv4(), edad: "", tipoEntrada: "Regular" },
  ]);
  const [tiposEntrada, setTiposEntrada] = useState<TipoEntrada[]>([]);
  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchTiposEntrada = async () => {
      try {
        const tipos = await api.getTiposEntrada();
        setTiposEntrada(tipos);
      } catch (error) {
        console.error("Error fetching ticket types:", error);
      }
    };
    fetchTiposEntrada();
  }, []);

  const agregarVisitante = () => {
    if (visitantes.length >= 10) {
      setErrors({ ...errors, maxVisitantes: "Máximo 10 entradas por compra" });
      return;
    }
    setVisitantes([
      ...visitantes,
      { id: uuidv4(), edad: "", tipoEntrada: "Regular" },
    ]);
    setErrors({ ...errors, maxVisitantes: "" });
  };

  const eliminarVisitante = (id: string) => {
    if (visitantes.length === 1) return;
    setVisitantes(visitantes.filter((v) => v.id !== id));
  };

  const actualizarVisitante = (
    id: string,
    field: keyof Visitante,
    value: string | number
  ) => {
    setVisitantes(
      visitantes.map((v) => (v.id === id ? { ...v, [field]: value } : v))
    );
  };

  const validarFormulario = (): boolean => {
    const newErrors: { [key: string]: string } = {};

    if (!fechaVisita) {
      newErrors.fechaVisita = "Debe seleccionar una fecha de visita";
    } else {
      const [year, month, day] = fechaVisita.split("-").map(Number);
      const selectedDate = new Date(year, month - 1, day);
      const today = new Date();
      today.setHours(0, 0, 0, 0);

      if (selectedDate < today) {
        newErrors.fechaVisita = "La fecha debe ser hoy o en el futuro";
      } else {
        const dayOfWeek = selectedDate.getDay();
        const day = selectedDate.getDate();
        const month = selectedDate.getMonth() + 1;

        if (dayOfWeek === 1) {
          newErrors.fechaVisita = "El parque no abre los días lunes";
        }

        if (day === 25 && month === 12) {
          newErrors.fechaVisita = "El parque permanece cerrado en navidad";
        }

        if (day === 1 && month === 1) {
          newErrors.fechaVisita = "El parque permanece cerrado en año nuevo";
        }
      }
    }

    if (!email) {
      newErrors.email = "Debe ingresar un email";
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      newErrors.email = "Debe ingresar un email válido";
    }

    visitantes.forEach((v, index) => {
      if (v.edad < 0) {
        newErrors[`edad-${index}`] = "Edad inválida";
      }
    });

    const esValido = Object.keys(newErrors).length === 0;
    setErrors(newErrors);
    return esValido;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validarFormulario()) return;

    setLoading(true);
    try {
      const formattedDate = fechaVisita.replace(/-/g, "");
      const visitantesData = visitantes.map(({ edad, tipoEntrada }) => ({
        edad,
        tipoEntrada,
      }));

      onNext(formattedDate, visitantesData, email);
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setLoading(false);
    }
  };

  const today = new Date().toISOString().split("T")[0];

  return (
    // mobile: usar ancho completo; en pantallas sm+ limitar a 3xl
    <div className="w-full max-w-full sm:max-w-3xl mx-0 px-4 sm:px-0">
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-4 sm:p-6">
        <h2 className="text-lg sm:text-xl md:text-2xl font-semibold text-neutral mb-2">
          Información de visita
        </h2>
        <p className="text-sm text-gray-500 mb-4 sm:mb-6">
          Completa los datos para tu visita al parque
        </p>

        <form onSubmit={handleSubmit} className="space-y-4 sm:space-y-6">
          {/* Date Selection */}
          <div>
            <label className="block text-sm font-medium text-neutral mb-2">
              Fecha de visita
            </label>
            <input
              type="date"
              value={fechaVisita}
              onChange={(e) => {
                const value = e.target.value;
                const parts = value.split("-");
                if (parts[0] && parts[0].length > 4) {
                  parts[0] = parts[0].slice(0, 4); // se limita el año a 4 dígitos
                }
                setFechaVisita(parts.join("-"));
              }}
              min={today}
              className="input input-bordered w-full bg-base-100 focus:outline-none focus:ring-2 focus:ring-primary h-11 sm:h-12 text-base"
            />
            {errors.fechaVisita && (
              <p className="text-error text-sm mt-1">{errors.fechaVisita}</p>
            )}
          </div>

          {/* Email Field */}
          <div>
            <label className="block text-sm font-medium text-neutral mb-2">
              Email
            </label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input input-bordered w-full bg-base-100 focus:outline-none focus:ring-2 focus:ring-primary h-11 sm:h-12 text-base"
              placeholder="tu@email.com"
            />
            {errors.email && (
              <p className="text-error text-sm mt-1">{errors.email}</p>
            )}
          </div>

          {/* Visitors List */}
          <div>
            <div className="flex items-center justify-between mb-3">
              <label className="block text-sm font-medium text-neutral">
                Visitantes ({visitantes.length}/10)
              </label>
              <button
                type="button"
                onClick={agregarVisitante}
                disabled={visitantes.length >= 10}
                className="btn btn-circle btn-sm btn-primary text-white disabled:opacity-50"
                aria-label="Agregar visitante"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-5 w-5"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                >
                  <path
                    fillRule="evenodd"
                    d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z"
                    clipRule="evenodd"
                  />
                </svg>
              </button>
            </div>

            {errors.maxVisitantes && (
              <p className="text-error text-sm mb-3">{errors.maxVisitantes}</p>
            )}

            <div className="space-y-3">
              {visitantes.map((visitante, index) => (
                <div
                  key={visitante.id}
                  className="bg-base-100 rounded-xl p-4 border border-gray-100"
                >
                  <div className="flex items-start gap-3">
                    <div className="flex-1 grid grid-cols-1 sm:grid-cols-2 gap-3">
                      <div>
                        <label className="block text-sm font-medium text-gray-600 mb-2">
                          Edad
                        </label>
                        <input
                          type="number"
                          min="0"
                          max="120"
                          value={
                            visitante.edad !== undefined ? visitante.edad : ""
                          }
                          onChange={(e) =>
                            actualizarVisitante(
                              visitante.id,
                              "edad",
                              e.target.value === ""
                                ? ""
                                : Number.parseInt(e.target.value)
                            )
                          }
                          className="input input-bordered w-full bg-white focus:outline-none focus:ring-2 focus:ring-primary h-10 text-base"
                          placeholder="Ej: 25"
                        />
                        {errors[`edad-${index}`] && (
                          <p className="text-error text-sm mt-1">
                            {errors[`edad-${index}`]}
                          </p>
                        )}
                      </div>

                      <div>
                        <label className="block text-sm font-medium text-gray-600 mb-2">
                          Tipo de entrada
                        </label>
                        <select
                          value={visitante.tipoEntrada}
                          onChange={(e) =>
                            actualizarVisitante(
                              visitante.id,
                              "tipoEntrada",
                              e.target.value
                            )
                          }
                          className="select select-bordered w-full bg-white focus:outline-none focus:ring-2 focus:ring-primary h-10 text-base"
                        >
                          {tiposEntrada.map((tipo) => (
                            <option key={tipo.nombre} value={tipo.nombre}>
                              {tipo.nombre}
                            </option>
                          ))}
                        </select>
                      </div>
                    </div>

                    {visitantes.length > 1 && (
                      <button
                        type="button"
                        onClick={() => eliminarVisitante(visitante.id)}
                        className="btn btn-ghost btn-sm btn-circle text-gray-400 hover:text-error mt-6"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          className="h-4 w-4"
                          viewBox="0 0 20 20"
                          fill="currentColor"
                        >
                          <path
                            fillRule="evenodd"
                            d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                            clipRule="evenodd"
                          />
                        </svg>
                      </button>
                    )}
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Submit Button */}
          <div className="flex flex-col-reverse sm:flex-row items-stretch sm:items-center justify-between gap-3 pt-4">
            <button
              type="submit"
              disabled={loading}
              className="btn btn-primary text-white w-full sm:w-auto sm:px-8 h-12 text-base disabled:opacity-50"
            >
              {loading ? (
                <span className="loading loading-spinner loading-sm"></span>
              ) : (
                "Continuar"
              )}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
