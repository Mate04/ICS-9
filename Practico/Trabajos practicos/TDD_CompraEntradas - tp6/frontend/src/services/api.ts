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

const BASE_URL = "/pedido"; // relativo para que Vite proxy lo redirija a localhost:8080

const handleResponse = async (res: Response) => {
  if (!res.ok) {
    const txt = await res.text().catch(() => "");
    throw new Error(`HTTP ${res.status} ${res.statusText} ${txt}`);
  }
  return res.json().catch(() => ({}));
};

const capitalize = (s: string) =>
  s.length === 0 ? s : s[0].toUpperCase() + s.slice(1).toLowerCase();

export const api = {
  async getTiposEntrada(): Promise<TipoEntrada[]> {
    const res = await fetch(`${BASE_URL}/tipo-entrada`);
    const data: string[] = await handleResponse(res);
    return data.map((t) => ({ nombre: capitalize(t) }));
  },

  async validarDatos(
    fechaVisita: string,
    visitantes: Visitante[]
  ): Promise<PedidoValidado> {
    // Normalizar fecha a yyyy-MM-dd
    let fechaIso = fechaVisita;
    if (/^\d{8}$/.test(fechaVisita)) {
      fechaIso = `${fechaVisita.slice(0, 4)}-${fechaVisita.slice(
        4,
        6
      )}-${fechaVisita.slice(6, 8)}`;
    } else {
      const d = new Date(fechaVisita);
      if (!Number.isNaN(d.getTime())) fechaIso = d.toISOString().slice(0, 10);
    }

    // Mapeos defensivos:
    // - tipoEntrada: generar alias para el enum que el backend espera (GENERAL | VIP)
    // - enviar BOTH edad y edadVisitante para cubrir ambas variantes de DTO en backend
    const tipoMapToBackendEnum: Record<string, string> = {
      normal: "GENERAL",
      general: "REGULAR",
      regular: "REGULAR",
      normalizada: "GENERAL",
      vip: "VIP",
      vIp: "VIP",
    };

    const visitantesPayload = visitantes.map((v) => {
      const key = (v.tipoEntrada || "").toString().trim().toLowerCase();
      const tipoParaBackend = tipoMapToBackendEnum[key] ?? key.toUpperCase();

      return {
        // enviar ambos nombres de campo para evitar NPE por mismatch DTO
        edad: v.edad,
        edadVisitante: v.edad,
        // backend puede esperar "normal"/"vip" o "GENERAL"/"VIP"; enviamos el enum que el servidor acepta
        tipoEntrada: tipoParaBackend,
      };
    });
    console.log(
      JSON.stringify({
        fechaVisita: fechaIso,
        visitantes: visitantesPayload,
      })
    );
    

    const res = await fetch(`${BASE_URL}/validar-datos`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        fechaVisita: fechaIso,
        visitantes: visitantesPayload,
      }),
    });

    const body = await handleResponse(res);

    // mapping de response (defensivo)
    const idPedido = body.id ?? body.idPedido ?? body.idPedidoDTO ?? 0;
    const importeTotal =
      body.montoTotal ?? body.importeTotal ?? body.total ?? 0;
    const resumenRaw = body.resumen ?? body.resumenPedido ?? body;
    const fechaEmision =
      resumenRaw.fechaEmision ??
      resumenRaw.fecha ??
      body.fechaEmision ??
      new Date().toISOString().split("T")[0].replace(/-/g, "");
    const visitantesRaw = resumenRaw.visitantes ?? resumenRaw.detalles ?? [];

    const visitantesRes: ResumenVisitante[] = (visitantesRaw as any[]).map(
      (v) => {
        const categoria = v.categoria ?? v.tipoPersona ?? "adulto";
        const subtotal = v.subtotal ?? v.precio ?? v.monto ?? 0;
        const tipoEntrada = (
          v.tipoEntrada ??
          v.tipoEntradaDTO ??
          v.tipo ??
          ""
        ).toString();
        return { categoria, subtotal: Number(subtotal), tipoEntrada };
      }
    );

    return {
      idPedido: Number(idPedido),
      importeTotal: Number(importeTotal),
      resumen: {
        visitantes: visitantesRes,
        fechaEmision: fechaEmision.toString(),
      },
    };
  },

  async getMetodosPago(): Promise<string[]> {
    const res = await fetch(`${BASE_URL}/metodo-pago`);
    const data: string[] = await handleResponse(res);
    return data.map((m) =>
      m.replace(/_/g, " ").toLowerCase().split(" ").map(capitalize).join(" ")
    );
  },

  async confirmarPedido(data: ConfirmarPedidoRequest): Promise<boolean> {
    // Normalizar metodoPago a los valores del enum del backend (MERCADO_PAGO | EFECTIVO)
    const raw = (data.metodoPago || "").toString().trim().toLowerCase();

    const metodoMap: Record<string, string> = {
      efectivo: "EFECTIVO",
      "efectivo ": "EFECTIVO",
      "mercado pago": "MERCADO_PAGO",
      mercado_pago: "MERCADO_PAGO",
      mercadopago: "MERCADO_PAGO",
      "mercado pago ": "MERCADO_PAGO",
    };

    const metodoParaBackend =
      metodoMap[raw] ??
      data.metodoPago?.toString().toUpperCase() ??
      raw.toUpperCase();

    const payload = { ...data, metodoPago: metodoParaBackend };

    const res = await fetch(`${BASE_URL}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    if (!res.ok) return false;
    const body = await res.json().catch(() => null);
    if (!body) return true;
    if (typeof body.success === "boolean") return body.success;
    if (typeof body.exito === "boolean") return body.exito;
    if (typeof body.ok === "boolean") return body.ok;
    return true;
  },
};
