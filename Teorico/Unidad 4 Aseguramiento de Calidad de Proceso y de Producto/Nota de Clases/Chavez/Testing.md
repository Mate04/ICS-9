TESTING: 
Proceso destructivo cuyo objetivo es encontrar defectos, es decir, asumir a priori que una porción de software por más trivial que sea puede y va a tener defectos, y el objetivo nuestro es encontrarlos para posteriormente resolverlos, y de esa forma poder contribuir a la calidad de un producto de software

Concepto: Error vs Defecto: (Ambos son fallas en el producto de software)
Depende del momento en el que se presentan.
* Si encontramos la falla durante la misma etapa que la originamos se trata de un ERROR
* Cuando esa falla trasciende a la etapa a la cual se originó (ej: beta testing) se trata de un DEFECTO
Testing que es lo que busca encontrar? -> Defectos (en testing unitario si encontramos errores)

Proceso de testing: planificación y control -> análisis y diseño -> ejecución -> evaluación y reportes

Niveles de testing:
* Testing unitario: Cuyo objetivo es proveer un mecanismo de verificación de la comprensión de la funcionalidad que estoy implementando. Estos test no deben fallar ya que están hechos para garantizar que está bien lo que estoy implementando -> Lo lleva adelante el DESARROLLADOR
* Testing de integración: Orientado a probar las interfaces, no interfases visuales, sino a nivel de boundarys o fronteras entre diferentes componentes de nuestro producto (sistemas o subsistemas, o entre clases) -> Buscamos encontrar defectos entre la unión de diferentes componentes que está representada por sus interfaces. Lo lleva adelante el TESTER
* Testing de sistema: Vamos a probar una funcionalidad en su totalidad, ya tenemos la funcionalidad implementada (puede ser una pantalla, formulario, flujo) y el tester lo que va a hacer es ejecutar cierto camino con cierto escenario y ciertas características para poder encontrar si el resultado obtenido de ejecución coincide con el resultado esperado. Lo lleva adelante el TESTER 
* Testing de aceptación: Usuario final nos dice si el sistema corresponde tanto con sus requerimientos manifiestos como con sus espectativas. Validamos si lo que estamos entregando corresponde al requerimiento planteado. No deberíamos encontrar defectos, sino más bien capacitar al cliente en el uso de la funcionalidad que ya está implementada. Lo lleva adelante el PRODUCT OWNER, EL USUARIO, EL CLIENTE

Estrategias: No podemos probar todo, existen infinitas combinaciones muchas veces de valores que podemos ejecutar en nuestra funcionalidad -> Surge la necesidad de aplicar algún tipo de criterio económico para el testing, es decir, poder maximizar la cantidad de defectos encontrados minimizando el esfuerzo requerido para hacerlo y para eso surgen diferentes ESTRATEGIAS para el diseño de casos de prueba

Diseñamos la MENOR cantidad de casos de prueba posibles para maximizar la cantidad de defectos encontrados y para eso existen 2 estrategias:
* CAJA BLANCA:
Podemos ver el detalle de la implementación de las funcionalidades (vamos a disponer del código) y vamos a poder diseñar nuestro caso de prueba para poder garantizar cobertura -> Puedo guiar la ejecución de los casos de prueba por donde yo quiera, conozco la estructura interna

* CAJA NEGRA:
No conozco la estructura interna de la implementación y solamente lo voy a analizar en términos de entradas y salidas, es decir, voy a identificar cuales son las diferentes entradas que una funcionalidad puede tener, voy a elegir los valores con los cuales voy a ejecutar esa funcionalidad y finalmente voy a comparar las salidas obtenidas contra las que esperaba tener

Métodos: Para que los usamos? Para maximizar la cantidad de efectos encontrados -> El tiempo y el presupuesto es limitado. Hay que pasar por la mayor cantidad de funcionalidades con la menor cantidad de pruebas

* Métodos dentro de caja negra: 2 métodos
- Basado en especificaciones:
    A. Partición de equivalencias (o clases de equivalencia)
    B. Análisis de valores límites (implementación o una particularidad de la partición de equivalencias)
- Basado en la experiencia:
    C. Adivinanza de defectos
    D. Testing exploratorio

A. Partición de equivalencias: Analiza primero cuales son las condiciones externas (tanto las entradas como las salidas) que van a estar involucradas en el desarrollo de una funcionalidad -> Una vez que identifique todas mis variables externas, para cada condición externa, voy a analizar cuales son los subconjuntos de valores posibles que pueden tomar c/u de esas condiciones externas que producen un resultado equivalente
Dos pasos:
    1. Identificar clases de equivalencia (válidas y no válidas):
        -Rango de valores continuos
        -Valores discretos
        -Selección simple
        -Selección múltiple
    2. Identificar los casos de prueba: Voy a elegir un valor representativo de esa clase de equivalencia para poder conformar mi caso de prueba 

    Ej: Sitio web de bebidas alcohólicas te pregunta cual es tu edad para permitirte ingresar o no:
        -Condición externa: (de salida) -> Ingreso (Entra o no)
        -Condición externa: (de entrada) -> Edad:
            Subconjunto de valores posibles: mayor o igual a 18 -> Deja entrar
                                             menor o igual a 18 -> No deja entrar

Clases de equivalencias: Subconjunto de valores que puede tomar una condición externa para el cual, si yo tomo cualquier miembro de ese subconjunto, el resultado de la ejecución de esa funcionalidad es equivalente (no igual). Las clases de equivalencias también define SALIDAS, salida esperada -> Mensaje de error 
                -> Se logueó con éxito

Casos de prueba: Conjunto de pasos ordenados que debo seguir para ejecutar la funcionalidad con una especificación de cuales van a ser los valores que yo voy a ingresar

B. Análisis de valores límites: Es una variante de la partición de equivalencias, en vez de seleccionar cualquier elemento como representativo de una clase de equivalencia, se seleccionan los bordes de una clase (podemos encontrar duplicados)