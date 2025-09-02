# Gestión de Software como Producto

## Introducción
- El software cambia continuamente durante su desarrollo y uso.  
- Cada cambio genera una nueva **versión**, que debe gestionarse para mantener la trazabilidad.  
- La **gestión de configuración (SCM)** surge como disciplina transversal para preservar la integridad del producto de software durante todo su ciclo de vida.



---

## Definición de Gestión de Configuración
Disciplina de soporte que:
- Identifica y documenta ítems de configuración.  
- Controla, registra y reporta cambios.  
- Verifica la correspondencia con los requerimientos (**trazabilidad**).  

**Problemas que soluciona:**
- Pérdida de componentes.  
- Cambios no validados.  
- Regresiones.  
- Doble mantenimiento.  
- Superposición de cambios.  

---

## Conceptos Clave con Ejemplos

### Ítem de Configuración (IC)
Artefacto que forma parte del producto de software.  
**Ejemplo:** `requerimientos.docx`, `login.jsx`, `test_login.js`  

**Tipos de ítems:**  
- **De producto:** tienen ciclo de vida largo (código, manuales, BD).  
- **De proyecto:** duran lo que dure el proyecto (plan, cronograma, defectos).  
- **De iteración:** duran lo que dure una iteración (planes de sprint, burn-down charts).  

---

### Versión
Estado particular de un IC.  
> **Nota:** en la práctica, las herramientas de control de versiones (como Git) ya gestionan automáticamente las versiones.  

**Ejemplo:**  
- `login.jsx` v1.0 → valida usuario.  
- `login.jsx` v1.1 → agrega validación de contraseña.  

---

### Variante
Versión alternativa de un mismo producto.  
**Ejemplo:** App gratuita (con publicidad) y app premium (sin publicidad).  

---

### Configuración
Conjunto coherente de versiones de ítems de configuración que definen un producto en un momento dado.  
**Ejemplo:**  
- `requerimientos_v2.docx` + `login_v1.1.jsx` + `test_login_v1.0.js` conforman la **release 1.0**.  

---

### Línea Base
Punto de referencia estable en un momento específico.  
Sirve para rollback, reproducir entornos o demostrar estabilidad.  

**Tipos:**  
- **Operacional:** contiene código ejecutable que pasó QA (ej: primera release).  
- **De especificación:** solo documentos de requerimientos o diseño.  

---

### Repositorio
Espacio central donde se guardan los ítems de configuración.  
**Ejemplo:** Repo en GitHub → `https://github.com/usuario/proyecto`  

---

### Release
Versión lista para entrega a usuarios finales.  
**Ejemplo:**  
- `v1.0.0` publicada en Play Store.  
- `v2.0.0` con nuevas funcionalidades.  

---

### Branch (Rama)
Línea de desarrollo paralela.  
**Ejemplo:**  
- Rama `main`: versión estable.  
- Rama `feature/login`: desarrollo de nueva funcionalidad.  

---

## Procesos y Roles Clave

### Control de Cambios
Una línea base no puede modificarse sin pasar por el **Comité de Control de Cambios**.  

**Etapas:**  
1. Propuesta de cambio.  
2. Análisis de impacto (técnico, recursos, arquitectura).  
3. Orden de cambio con restricciones y criterios.  
4. Implementación.  
5. Revisión y aprobación final.  

---

### Auditorías de Configuración
Controles realizados por un auditor externo al equipo.  

**Tipos:**  
- **Física:** valida que los IC estén donde corresponde y cumplan el esquema.  
- **Funcional:** asegura trazabilidad y cumplimiento de objetivos.  

---

### Informe de Estado
Reportes automáticos que responden:  
- ¿Cuál es el estado actual de un ítem?  
- ¿Qué versión implementa un cambio aprobado?  
- ¿Qué diferencias hay entre dos versiones?  

---

### Trazabilidad
Relación entre requerimientos, análisis, diseño, código y pruebas.  
- **Bidireccional:** permite ir de requerimientos a código y viceversa.  
- Útil para analizar impacto de cambios.  

---

## Gestión de Configuración en Ágil
- SCM responde a los cambios en lugar de evitarlos.  
- La automatización es clave para entregas rápidas.  
- No hay comité formal ni auditorías tradicionales.  
- Es responsabilidad compartida de todo el equipo.  

---

## Prácticas Continuas

### Continuous Integration (CI)
- Integrar cambios varias veces al día.  
- Verificados por pruebas automatizadas.  
- Permite detectar errores temprano.  

### Continuous Delivery (CD)
- Producto siempre listo para producción.  
- Decisión de liberar depende del **Product Owner**.  

### Continuous Deployment
- Despliegue automático en producción.  
- **Estrategias:**  
  - **Canary Deployment:** liberar primero a un grupo pequeño de usuarios.  
  - **Blue/Green Deployment:** dos entornos en paralelo; uno activo y otro en espera para rollback.  


