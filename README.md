### Apellido/s y Nombre/s de profesores adjuntos

#### Teórico: **Meles, Judith**

#### JTP: **Massano, Cecilia**

#### Auxiliares:
* **Izaguirre, Ezequiel**
* **Pomenich, Marcos**

## Integrantes

| Nombre Completo      | Legajo |
| -------------------- | ------ |
| Daniel Guevara       | 57743  |
| Camila Chavez        | 71805  |
| Pauilina Tirante     | 83067  |
| Lucia Bossio         | 91461  |
| Leonel Truenow       | 93109  |
| Ignacio Loyola       | 94505  |
| Agustin Li Bongiorno | 94996  |
| Matias Sciarra       | 95034  |
| Mateo Maldonado      | 98717  |
| Emanuel Rubiolo      | 424360 |


### Estructura de proyecto

```php-template
/ICS-9
├── Readme.md
├── Planificación Ingeniería y Calidad de Software.pdf
├── Cronograma.xlsx
├── Bibliografía/
│   └── <<Nombre del autor>> - <<Título del libro>>.pdf
├── Teorico/
│   └── Unidad <<N>> <<Nombre_unidad>>/
        ├── Contenido unidad.md
│       ├── Filminas/
│       │   └── <<N>> <<Título de la filmina>>.pdf
│       ├── Notas de clase/
│       │   └── <<Apellido creador>>/
│       │       └── <<nombre del tema>> - <<DD-MM-YYYY>>.<<ext>>
│       └── Resumenes/
│           └── <<nombre del tema>> - <<Apellido creador>>.<<ext>>
├── Practico/
│   ├── Guía Practicos Evaluables.pdf
│   ├── Guía Practicos Resueltos.pdf
│   ├── Trabajos practicos/
│   │   └── <<Tipo de trabajo practico>>/
│   │       └── <<Nombre de tema>> - tp <<Numero de TP>>.pdf
│   └── Resoluciones de ejercicios/
│       └── Resolucion de <<Nombre de ejercicio>>/
│           └── Resolucion <<Tipo de resolucion>> - <<Apellido creador>>.md
├── Parciales/
│   └── Parcial <<Numero de parcial>>/
│       ├── Parcial <<MM-YYYY>> <<curso>>.pdf
│       └── Modelo parcial <<número modelo>>.pdf
├── Assets/
│   └── <<Apellido creador>>/
│       └── <<Nombre de la imagen>>.(png|jpg|jpeg)
```


## Plan de configuración

| Ítem de configuración | Regla de nombrado                                      | Ubicación                                  | 
|----------------------|--------------------------------------------------------|---------------------------------------------|
| Programa             | `Programa.md`                                          | Raíz del proyecto `/`                       |
| Filmina              | `<<N>> <<Título de la filmina>>.pdf`                         | `/Teorico/Unidad_<<N>>_<<Nombre_unidad>>/Filminas`            | 
| Notas de clase       | `Nota clase <<DD-MM-YYYY>>.md`                         | `/Teorico/Unidad_<<N>>_<<Nombre_unidad>>/Notas de clase`      | 
| Resumen              | `Unidad <<Número unidad>> - <<Apellido creador>>.md`                          | `/Teorico/Unidad_<<N>>_<<Nombre_unidad>>/Resúmenes`           | 
| Guía de ejercicios   | `Guía <<Tipo de guía>>.md`                             | `/Practico`                                 | 
| Parcial              | `Modelo parcial <<número modelo>>.md`                  | `/Parciales`                                | 
| Libro                | `<<Nombre del autor>> - <<Título del libro>>.pdf`      | `/Bibliografía`                             | 
| Imágenes             | `<<Nombre de la imagen>>.(png\jpg\jpeg)`               | `/Assets/<<Apellido creador>>`             | 
| Cronograma           | `Cronograma.pdf`                                       | `/cronograma`                              |
| Trabajo Práctico     | `<<Nombre de tema>> - tp <<Número de tp>>.pdf`         | `/Practico/Trabajos practicos/<<Tipo de trabajo practico>>/<<Nombre de tema>> - tp <<Numero de TP>>.pdf`    | 
| Ejercicio Resuelto | `Resolucion <<Tipo de resolucion>> - <<<Apellido creador>>.md` | `/Practico/Resolucion de Ejercicos/Resolucion de <<Nombre de ejercicio>>` |

## Reglas de commit
> 💡 Nota: Siempre mandar en commit su legajo al principio
> 💡 legajo: accion descipcion


    
### Glosario
| Clave                | Valor                                                                 |
|-----------------------|----------------------------------------------------------------------|
| \<Titulo\>           | Título del libro o paper                                              |
| \<Nombre del autor\> | Nombre completo del autor del libro                                   |
| \<UnidadN\>          | Unidad con número incremental. Ej: Unidad1, Unidad2, UnidadN, etc.   |
| \<Nombre_unidad\>          | Nombre de la unidad N descripto anteriormente.   |
| \<Título de la filmina\> | Nombre del tema tratado en las filminas de la unidad                |
| \<Apellido creador\> | Apellido del alumno que elaboró las notas de clase o la resolución    |
| \<nombre del tema\>  | Tema específico tratado (ej. "Historia de usuarios")                  |
| \<DD-MM-YYYY\>       | Fecha de la clase correspondiente a las notas                        |           
| \<Número de TP\>     | Número incremental del trabajo práctico (ej. TP1, TP2…)               |
| \<Nombre de tema\>   | Tema del trabajo práctico (ej. "Testing")                             |
| \<Tipo de resolución\> | Forma de resolución del ejercicio (ej. "User Story")                 |
| \<Número Parcial\>   | Número que identifica el parcial (ej. "Parcial 1")                    |
| \<Nombre de la imagen\> | Nombre representativo de la imagen subida (png, jpg, jpeg)         |
| < ext >               | La extencion del archivo de puede ser md, pdf, docx, etcs.                  |


## Linea Base
La actualización de la línea base se realiza al concluir cada instancia de examen parcial, garantizando así la integridad y trazabilidad de los entregables del proyecto.


