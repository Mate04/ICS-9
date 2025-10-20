Roles:
Adiministrador de festival
vendedor de entradas 
Director de cultura 


MVP
hipotesis:
Se va desarrollar una aplicacion qeu permita diagramar la programacion del 
festival de un solo predio (conciertos, artistas, orden de la programacion) y gestionar
la venta de entradas (solo entradas generales, sin descuentos en efectivo)
y se podra validar lo siguiente.

El administrador del festival pueda organizar el predio con su estructura
fija y registrar/grupos musicales.
El vendedor de entradas debe poder vender entradas en distintos puntos
de venta sin duplicaciones.
Que el sistema responda en los tiempos necesarios (menos de 6 segundos por transaccion).

NO INCLUYE:
Descuneto por venta anticipada
Diferencietes tipos de entradas, solo general.
Variaciones de precios por sector o noche.
Devoluciones o reintegros.

US Stories MVP:

Vendedor:
Vender entradas

Administrador del festival:
Definir precios de entrada
Registrar Artistas/grupo musical
Registrar festival
Registrar programcion festival
Registrar punto de venta

User story canonica

Registrar punto de venta

Como Administrador del festival, quiero registrar un punto de venta para habilitar la comercialización de entradas desde ese lugar.

Criterios de aceptacion:
- El punto de venta de quedar registrado como habilitado para vender entradas.
- No se pueden registrar dos puntos con el mismo nombre.
- Todos los datos requeridos deben estar completos

Pruebas de usuario:
- Registrar un punto de venta con todos los datos completos y habilitado (pasa).
- Registrar un punto de venta con todos los datos pero marcado como no habilitado (falla).
- Registrar un punto de venta con datos faltantes y habilitado (falla).
- Registrar un punto de venta con todos los datos completos pero con nombre repetido (falla).

Estimacion : 1 SP

Justificacion:
Complejidad: no hay muchos datos que cargar y pocas validaciones.
Esfuerzo: Bajo, no hay mucho registro de datos y validaciones, y pocas pruebas para ejecutar.
Intertidumbre: Nula. El requerimiento no da dudas de lo que se tiene que desarrollar.

Vender entradas

Como Vendedor de entradas quiero poder vender entradas para un festival vigente.

Criterios de aceptacion:
- El cobro solo puede hacerse en efectivo.
- No se pueden vender dos entradas del mismo festival, de la misma butaca en la misma fecha.
- Debe poder seleccionar la butaca para el festival vigente.
- La entrada debe tener un codigo de barras.
- La entrada debe ser impresa con los datos fiscales.
- El numero de facturacion debe ser unico.
- El sistema debe generar una entrada en tiempo maximo de  6 segundos.
- El sistema debe dejar vender mas de una entrada(distinta) en simultaneo.
- El sistema debe soportar la venta de múltiples entradas distintas en simultáneo (desde distintos puntos de venta).

Pruebas de usuario:
- Probar vender una entrada seleccionando una butaca y festival (pasa).
- Probar vender una entradas seleccionando butaca y no festival (falla).
- Probar vender una entrada seleccionado butaca y festival duplicada (falla).
- Probar vender mas de una entrada del mismo tipo de entrada del festival seleccionando distintas butacas (pasa).
- Probar imprimir la entrada con el codigo de barras (pasa).
- Probar imprimir la entrada sin el codigo de barras (falla).
- Probar imprimir una entrada y tarda mas de 6 segundos (falla).
- Probar imprimir una entrada con todos los datos (pasa).
- Probar imprimir una entrada con datos faltantes (falla).

SP: 8 

Justificacion:
Complejidad: US con una complejidad alta, requiere mucha carga de de datos,validaciones, generacion de codigo de barras. Ademas debe quedar registrada la transaccion e impresion de la us.
Esfuerzo: Alto. Muchas pruebas de usuario, manejo y conexion con la impresora, verificacion de la venta de diferentes puntos de venta en simultaneo, datos fiscales, muchas combinaciones posibles y ventas multiples de entradas.
Intertidumbre: Nula. El requerimiento no da dudas de lo que se tiene que desarrollar. Las barras se generaran con una libreria.