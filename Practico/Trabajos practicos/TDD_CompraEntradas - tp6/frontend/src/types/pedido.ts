export interface Visitante {
    edad: number
    tipoEntrada: string
  }
  
  export interface ResumenVisitante {
    categoria: string
    subtotal: number
    tipoEntrada: string
  }
  
  export interface PedidoValido {
    idPedido: number
    importeTotal: number
    resumen: {
      visitantes: ResumenVisitante[]
      fechaEmision: string
    }
  }
  
  export interface tipoEntrada {
    idTipoEntrada: number
    nombre: string
  }
  