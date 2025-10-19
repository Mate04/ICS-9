////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// API service to get payment methods from backend
import axios from "axios";

export async function getMetodosPago(): Promise<string[]> {
  try {
    const url = "http://localhost:8080/pedido/metodo-pago";
    const response = await axios.get<string[]>(url);
    return response.data;
  } catch (error) {
    console.error("Error al obtener los métodos de pago:", error);
    throw error;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
export async function getTiposEntrada(): Promise<string[]> {
  try {
    const url = "http://localhost:8080/pedido/metodo-pago";
    const response = await axios.get<string[]>(url);

    return response.data;
  } catch (error) {
    console.error("Error al obtener los tipos de entrada:", error);
    throw error;
  }
}
// Mock API service for EcoPark ticket purchasing

export interface TipoEntrada {
  nombre: string;
}

export interface Visitante {
  edad: number;
  tipoEntrada: string;
}

export interface ResumenVisitante {
  categoria: string;
  subtotal: number;
  tipoEntrada: string;
}

export interface PedidoValidado {
  idPedido: number;
  importeTotal: number;
  resumen: {
    visitantes: ResumenVisitante[];
    fechaEmision: string;
  };
}

export interface ConfirmarPedidoRequest {
  idPedido: number;
  metodoPago: string;
}

// Mock delay to simulate network request
const delay = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

// Calculate category based on age
const calcularCategoria = (edad: number): string => {
  if (edad < 12) return "niño";
  if (edad >= 65) return "jubilado";
  return "adulto";
};

// Calculate price based on category and ticket type
const calcularPrecio = (categoria: string, tipoEntrada: string): number => {
  const basePrice = tipoEntrada.toLowerCase() === "vip" ? 5000 : 3000;

  if (categoria === "niño") return basePrice * 0.5;
  if (categoria === "jubilado") return basePrice * 0.7;
  return basePrice;
};

export const api = {
  // GET /api/pedido/tipo-entrada
  async getTiposEntrada(): Promise<TipoEntrada[]> {
    await delay(300);
    return [{ nombre: "Regular" }, { nombre: "VIP" }];
  },

  // POST /api/pedido/validar-datos
  async validarDatos(
    fechaVisita: string,
    visitantes: Visitante[]
  ): Promise<PedidoValidado> {
    await delay(500);

    const resumenVisitantes = visitantes.map((v) => {
      const categoria = calcularCategoria(v.edad);
      const subtotal = calcularPrecio(categoria, v.tipoEntrada);

      return {
        categoria,
        subtotal,
        tipoEntrada: v.tipoEntrada,
      };
    });

    const importeTotal = resumenVisitantes.reduce(
      (sum, v) => sum + v.subtotal,
      0
    );
    const idPedido = Math.floor(Math.random() * 90000) + 10000;

    const today = new Date();
    const fechaEmision = today.toISOString().split("T")[0].replace(/-/g, "");

    return {
      idPedido,
      importeTotal,
      resumen: {
        visitantes: resumenVisitantes,
        fechaEmision,
      },
    };
  },

  // POST /api/pedido/confirmar
  async confirmarPedido(data: ConfirmarPedidoRequest): Promise<boolean> {
    await delay(800);
    console.log("[v0] Pedido confirmado:", data);
    return true;
  },
};
