# Testing de software

## Workflows!

- Requerimiento
- Analisis
- Diseño
- Implementacion 35% esfuerzo
- **PRUEBA** 30-50% esfuerzo
- Despliegue

---

1.  **Desarrolladores vs Testers**

    - porque hay tanta pica?

2.  **Testear es CARO.**

    - porque es caro testear? que es un testing exaustivo? porque no se hace?
    - que metodologia puede disminuir ese costo?

3.  **Proceso de testing (actividades)**

    - a. Planificacion de testing
      - i. plan de prueba (artefacto)
      - que como cuando quien va a realizar las pruebas
    - b. Diseño de testing
      - i. Casos de prueba (artefacto)
        1.  Precondiciones o estado
        2.  condiciones de entrada (externo)
        3.  secuencia de pasos
        4.  datos de prueba
        5.  resultado esperado
    - c. Ejecucion de Testing
      - i. reporte de defectos (artefacto)
    - d. Seguimiento y cierre
      - el desarrollador puede cerrar un defecto como terminado?

4.  **Hacer testing**

    - necesito tener el codigo para empezar? Cuando se empieza el testing?
    - si ya cumple el DoR ya se pueden hacer casos de prueba sobre esa US.
    - que actividades del proceso de testing se pueden ir resolviendo antes de tener el codigo?
    - Planificacion y diseño. 50% de todo el proceso.

5.  **Primer ciclo de testing**

    - ciclo cero, de forma manual, a partir del segundo, se pueden automatizar

6.  **Ambientes de trabajo**

    - deberia desarrollo y testing trabajar en el mismo ambiente?
    - podria trabajar testing en desarrollo o visebersa?
    - a. Desarrollo [prueba integracion y unitarias]
    - b. Prueba [prueba de sistema]
    - c. Pre-produccion [mismo ambiente que produccion pero sin la responsabilidad de uso del usuario final][prueba aceptacion]
    - d. produccion (ya trabajando)[pruebas aceptacion]
    - cual es la diferencia entre ambiente produccion y pre-produccion?
    - requerimientos no funcionales dificiles de probar en ambientes donde no esta la infraestructura final del usuario (desarrollo, prueba y pre-produccion)
    - cual es la diferencia entre pruebas alfa y beta?
    - cual es la diferencia entre preproduccion y una version alfa?

7.  **Severidad de los defectos**

    - a. bloqueante o invalidante
    - b. grave o mayor no puedo probar la funcionalidad pero no se cae el sistema
      - puedo seguir con el ciclo de prueba
    - c. leve frente a una accion mia me permite seguir
    - d. cosmetica

8.  **SLA**

    - tiempos maximos que nos da el cliente (especificado por contrato/acuerdo negociado) para resolver los problemas deacuerdo a la severidad del defecto

9.  **Prioridad**
    - acordado principalmente con el cliente. (Negocio)
