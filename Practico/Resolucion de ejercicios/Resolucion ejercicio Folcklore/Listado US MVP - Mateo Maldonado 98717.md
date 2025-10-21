# Folklore

tipo Apunte: practico
Curso: Ingenieria y calidad de software (https://www.notion.so/Ingenieria-y-calidad-de-software-1a4fda4341da80a382e2cc7ae9c20cc9?pvs=21)

# MVP

Aplicacion para la seleccion y compra de butacas de cada noche y administracion de funciones de la noche

## Listado de User history

- Seleccionar butacas
- Comprar entradas de butacas efectivo
- Diagramar funciones

### No alcance MVP

BM de funciones

ABM sectores 

ABM filas y butacas

Precio segun tipos de entradas y segun la funcion que se de esa noche

Definir los precios, ya que vamos hacer una vez al año, este no va a variar mucho durante el pcoo tiempo que esta activo el festival

### Vender entradas de butacas efectivo

Como vendedor quiero vender una entrada en efectivo para poder registrar la venta y generar la misma

### Criterios de aceptacion

- El tipo de pago aceptado debe ser en efectivo
- Se debe insertar la fecha de la noche, siendo esta igual o superior a la actual, y mostrar las butacas disponible
- Se debe vender butacas disponibles
- La entrada debe ser procesada hasta 6 segundo
- Se debe saber el monto de la entrada

### Pruebas de usuario

- El tipo de pago es en efectivo (pasa)
- Se vende una butaca no disponible (no pasa)
- Se selecciona una fecha superior a la actual y se muestra las butacas disponibles (pasa)
- Se muestra el monto total de la entrada (pasa)
- La entrada fue procesada menos de 6 segundos (pasa)
- Se prueba comprar la misma butaca dos veces en el mismo instante, y no pasa