## Definición estructura de repositorio para materias 

## Items de Configuracion
=====================================================================================================================
| **Item**              |**Tipo**          | **Regla de nombrado**          | **Ubicación Física**                  |
=====================================================================================================================
| Apuntes               | Unidad_Teorico   | ICS_T_Apuntes_\<Fecha>.docx    | ISW_2025_4K2_G9/Unidad_N/Teorico      |
| Resumen               | Unidad_Teorico   | ICS_T_Resumen_\<Tema>.docx     | ISW_2025_4K2_G9/Unidad_N/Teorico      |
| Filmina               | Unidad_Teorico   | ICS_T_Filmina_\<Tema>.docx     | ISW_2025_4K2_G9/Unidad_N/Teorico      | 
| Ejercicio Resuelto    | Unidad_Practico  | ICS_P_ER_\<Tema>.docx          | ISW_2025_4K2_G9/Unidad_N/Practico     |
| Guía de Ejercicios    | Unidad_Practico  | ICS_P_GE_\<Tema>.docx          | ISW_2025_4K2_G9/Unidad_N/Practico     |
| TP                    | Unidad_Practico  | ICS_TP_\<Numero>.docx          | ISW_2025_4K2_G9/Unidad_N/Practico     |
| Actividade            | Unidad_Practico  | ICS_Actividad_\<Tema>.docx     | ISW_2025_4K2_G9/Unidad_N/Practico     |
| Parcial               | Materia          | ICS_Parcial_\<Numero>.docx     | ISW_2025_4K2_G9/Parciales             |
| Bibliografía          | Materia          | ICS_Libro_\<Titulo>.pdf        | ISW_2025_4K2_G9/Material_bibliografico|
| Referencia            | Materia          | ICS_Referencia.txt             | ISW_2025_4K2_G9                       |
| Criterios Línea Base  | Materia          | ICS_CriteriosLineaBase.txt     | ISW_2025_4K2_G9                       |
| Plan de SCM           | Materia          | ICS_PlandeSCM.txt              | ISW_2025_4K2_G9                       |
| Contacto Docentes     | Materia          | ICS_ContactoDocente.txt        | ISW_2025_4K2_G9                       |
| Cronograma            | Materia          | ICS_Cronograma.pdf             | ISW_2025_4K2_G9                       |
=====================================================================================================================


==================================================================
| Tipo             | Descripción                                  |
==================================================================
| Materia          | Son utilizables durante la vida de la Materia|
| Unidad_Teorico   | Son utilizables durante la vida de la Unidad |
| Unidad_Practico  | Son utilizables durante la vida de la Unidad |
==================================================================


## Estructura del Repositorio
     .
     ├─── Material_bibliografico
     └─── Unidad_N
     |     ├─── Practico
     |     | ├─── Guias_de_ejercicios
     |     | |─── Ejercicios_Resueltos
     |     | |─── TP_N
     |     └─── Teorico
     |       ├─── Apuntes 
     |       ├─── Filminas
     |       └─── Resumenes
     ├─── Parciales      
     |       └─── Parcial_N
     ├─── Referencia
     ├─── Criterios Línea Base
     ├─── Plan de SCM   
     ├─── Contacto Docentes
     └─── Cronograma


## Referencias

| Sigla        | Significado                                                                                                  |
|:-------------|:-------------------------------------------------------------------------------------------------------------|
| \<Titulo>    | Título del libro o paper                                                                                     |
| \<N>         | Número incremental que indica la unidad, parcial o TP. Ej: U1, U2, Un, etc.                                  |
| \<Tema>      | Tema de la materia Ingeniería de Software sobre el que trata el resumen.                                     |
| \<Numero>    | Indica el trabajo práctico de la materia Ingeniería de Software. Ej: TP1, TP2, TPn, etc.                     |
| \<extension> | Extensión del archivo, puede estar en diferentes formatos. Ejemplo: .jpg, .jpeg, .pdf, .css, .html, .js, etc.|
| TP           | Trabajo Práctico                                                                                             |
| ICM          | Ingeniería y Calidad de Software                                                                             |