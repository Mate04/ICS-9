# ICS 2025 4K2

## Integrantes

| Nombre Completo      | Legajo |
| -------------------- | ------ |
| Lucia Bossio         | 91461  |
| Camila Chavez        | 71805  |
| Daniel Guevara       | 57743  |
| Agustin Li Bongiorno | 94996  |
| Ignacio Loyola       | 94505  |
| Mateo Maldonado      | 98717  |
| Emanuel Rubiolo      | 424360 |
| Matias Sciarra       | 95034  |
| Leonel Truenow       | 93109  |

## Items de configuracion

| Ítem de Configuración     | Nomenclatura                            | Ubicación                          |
| ------------------------- | --------------------------------------- | ---------------------------------- |
| Bibliografía (libros)     | `ICS_B_Libro_<Titulo>.pdf`              | `Material/Bibliografia/`           |
| Bibliografía (papers)     | `ICS_B_Paper_<Titulo>.pdf`              | `Material/Bibliografia/`           |
| Resúmenes                 | `ICS_Resumen_U<Unidad>_<Tema>.docx`     | `Material/Resumenes/`              |
| Trabajos de investigación | `ICS_TPC<Numero>_<Tema>.pdf`            | `Material/Trabajos_Investigacion/` |
| Guías de Ejercicios       | `ICS_GE_<Numero>.pdf`                   | `Material/Guias/`                  |
| Guías con solución        | `ICS_GE_<Numero>_Solucion.pdf`          | `Material/Guias/`                  |
| Trabajos Prácticos (TP)   | `ICS_TP<Numero>_<Tema>.pdf`             | `Material/TP/`                     |
| Clases Grabadas           | `ICS_CG_<Fecha>.<ext>`                  | `Material/Clases_Grabadas/`        |
| Presentaciones            | `ICS_Presentacion_U<Unidad>_<Tema>.pdf` | `Material/Presentaciones/`         |
| Templates (plantillas)    | `ICS_Template_<Uso>.docx`               | `Material/Templates/`              |
| Cronograma                | `ICS_Cronograma.md`                     | `/` (raíz)                         |
| Programa de asignatura    | `ICS_Programa_asignatura.pdf`           | `/` (raíz)                         |
| README                    | `README.md`                             | `/` (raíz)                         |

### Estructura del repositorio

```php-template
ICS/
│
├── Material/
│   ├── Bibliografia/
│   │   ├── ICS_B_Libro_<Titulo>.pdf
│   │   └── ICS_B_Paper_<Titulo>.pdf
│   │
│   ├── Resumenes/
│   │   └── ICS_Resumen_U<Unidad>_<Tema>.docx
│   │
│   ├── Trabajos_Investigacion/
│   │   └── ICS_TPC<Numero>_<Tema>.pdf
│   │
│   ├── Guias/
│   │   ├── ICS_GE_<Numero>.pdf
│   │   └── ICS_GE_<Numero>_Solucion.pdf
│   │
│   ├── TP/
│   │   └── ICS_TP<Numero>_<Tema>.pdf
│   │
│   ├── Clases_Grabadas/
│   │   └── ICS_CG_<Fecha>.<ext>
│   │
│   ├── Presentaciones/
│   │   └── ICS_Presentacion_U<Unidad>_<Tema>.pdf
│   │
│   └── Templates/
│       └── ICS_Template_<Uso>.docx
│
├── ICS_Cronograma.md
├── ICS_Programa_asignatura.pdf
└── README.md

```
