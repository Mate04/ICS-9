import React, { useState, useEffect } from "react";
import { api, getTiposEntrada, type Visitante } from "../services/api";

interface Paso1FormularioProps {
  onNext: (fechaVisita: string, visitantes: Visitante[]) => void;
}

interface VisitanteForm extends Visitante {
  id: string;
}

export default function Paso1Formulario({ onNext }: Paso1FormularioProps) {
  const [fechaVisita, setFechaVisita] = useState("");
  const [visitantes, setVisitantes] = useState<VisitanteForm[]>([
    { id: crypto.randomUUID(), edad: 0, tipoEntrada: "Regular" },
  ]);
  const [tiposEntrada, setTiposEntrada] = useState<string[]>([]);
  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchTiposEntrada = async () => {
      try {
        const tipos = await getTiposEntrada();
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
      { id: crypto.randomUUID(), edad: 0, tipoEntrada: "Regular" },
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

    visitantes.forEach((v, index) => {
      if (!v.edad || v.edad <= 0) {
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

      onNext(formattedDate, visitantesData);
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setLoading(false);
    }
  };

  const today = new Date().toISOString().split("T")[0];

  return (
    <div className="w-full max-w-3xl mx-auto">
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-5 sm:p-6 md:p-8">
        <h2 className="text-xl sm:text-2xl md:text-3xl font-semibold text-neutral mb-1">
          Información de visita
        </h2>
        <p className="text-sm text-gray-500 mb-6 sm:mb-8">
          Completa los datos para tu visita al parque
        </p>

        <form onSubmit={handleSubmit} className="space-y-6 sm:space-y-8">
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
              className="input input-bordered w-full bg-base-100 focus:outline-none focus:ring-2 focus:ring-primary h-12"
            />
            {errors.fechaVisita && (
              <p className="text-error text-sm mt-1">{errors.fechaVisita}</p>
            )}
          </div>

          {/* Visitors List */}
          <div>
            <div className="flex items-center justify-between mb-4">
              <label className="block text-sm font-medium text-neutral">
                Visitantes ({visitantes.length}/10)
              </label>
              <button
                type="button"
                onClick={agregarVisitante}
                disabled={visitantes.length >= 10}
                className="btn btn-circle btn-md btn-primary text-white disabled:opacity-50"
                aria-label="Agregar visitante"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-6 w-6"
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
              <p className="text-error text-sm mb-4">{errors.maxVisitantes}</p>
            )}

            <div className="space-y-3">
              {visitantes.map((visitante, index) => (
                <div
                  key={visitante.id}
                  className="bg-base-100 rounded-xl p-4 sm:p-5 border border-gray-100"
                >
                  <div className="flex items-start gap-3 sm:gap-4">
                    <div className="flex-1 grid grid-cols-1 sm:grid-cols-2 gap-3 sm:gap-4">
                      <div>
                        <label className="block text-xs font-medium text-gray-600 mb-1.5">
                          Edad
                        </label>
                        <input
                          type="number"
                          min="1"
                          max="120"
                          value={visitante.edad || ""}
                          onChange={(e) =>
                            actualizarVisitante(
                              visitante.id,
                              "edad",
                              Number.parseInt(e.target.value) || 0
                            )
                          }
                          className="input input-bordered w-full bg-white focus:outline-none focus:ring-2 focus:ring-primary h-11 text-base"
                          placeholder="Ej: 25"
                        />
                        {errors[`edad-${index}`] && (
                          <p className="text-error text-xs mt-1">
                            {errors[`edad-${index}`]}
                          </p>
                        )}
                      </div>

                      <div>
                        <label className="block text-xs font-medium text-gray-600 mb-1.5">
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
                          className="select select-bordered w-full bg-white focus:outline-none focus:ring-2 focus:ring-primary h-11 text-base"
                        >
                          {tiposEntrada.map((tipo) => (
                            <option key={tipo} value={tipo}>
                              {tipo}
                            </option>
                          ))}
                        </select>
                      </div>
                    </div>

                    {visitantes.length > 1 && (
                      <button
                        type="button"
                        onClick={() => eliminarVisitante(visitante.id)}
                        className="btn btn-ghost btn-sm btn-circle text-gray-400 hover:text-error mt-7"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          className="h-5 w-5"
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
          <div className="flex justify-stretch sm:justify-end pt-4">
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
