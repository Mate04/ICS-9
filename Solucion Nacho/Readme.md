# 📂 Plan de Configuración - ICS (Ingeniería y Calidad de Software)

Este repositorio contiene la organización y las reglas de gestión de configuración para los materiales de **Ingeniería y Calidad de Software**.  
El objetivo es mantener un control claro sobre ítems de configuración teóricos y prácticos, así como sus versiones.

---

## 📑 Reglas de nombrado

| Ítem de Configuración         | Regla de nombrado                              | Ejemplo                                     |
|-------------------------------|------------------------------------------------|---------------------------------------------|
| Material bibliográfico        | `ICS_<nombre_de_material>.<ext>`               | `ICS_PatronesDiseño.pdf`                    |
| Trabajos prácticos evaluables | `ICS_<NroTP>_<NombreTP>_9.<ext>`               | `ICS_1_Introducción_9.docx`                 |
| Actividades                   | `ICS_<TemaActividad>.pdf`                      | `ICS_CasosDeUso.pdf`                        |
| Parciales viejos              | `ICS_<Año>_Parcial_<NroParcial>.<ext>`         | `ICS_2023_Parcial_1.pdf`                    |
| Notas de clase                | `ICS_<Tema>_<DD-MM-AA>.<ext>`                  | `ICS_Modelado_10-04-25.docx`                |

> **Nota:**  
> - `ext` = extensión del archivo (`pdf`, `docx`, `jpg`, `png`, etc.).  
> - `NroTP` = número de trabajo práctico.  
> - `DD-MM-AA` = día, mes y año de la clase.  

---

## 📂 Estructura de directorios

```bash
ICS-plan_de_configuración/
│
├── teorico/                           # Material de teoría
│   ├── unidad_1/
│   │   ├── material_bibliográfico/    # Documentos bibliográficos
│   │   ├── notas_de_clase/            # Apuntes de clase
│   └── unidad_2/ ...
│
├── practico/                          # Material de práctica
│   ├── actividades/                   # Actividades no evaluables
│   ├── trabajos_practicos/            # Trabajos prácticos evaluables
│
├── parciales_viejos/                  # Parciales de años anteriores
│
└── README.md                          # Este documento
