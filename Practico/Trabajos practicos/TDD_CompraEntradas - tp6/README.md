# HarmoniApp - Guía de Instalación con Docker

Esta guía te ayudará a levantar la aplicación completa (Base de datos + Backend + Frontend) usando Docker.

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Docker Desktop** ([Descargar aquí](https://www.docker.com/products/docker-desktop))
  - Versión mínima recomendada: 20.10 o superior
  - Debe estar ejecutándose antes de continuar

### ¿Cómo verificar si Docker está instalado?

Abre una terminal (CMD, PowerShell o Git Bash) y ejecuta:

```bash
docker --version
docker compose version
```

Si ves las versiones instaladas, ¡estás listo para continuar!

---

## 🚀 Instrucciones para Levantar la Aplicación


### Paso 1: Levantar los contenedores

Desde la **raíz del proyecto** (donde está el archivo `docker-compose.yml`), ejecuta:

```bash
docker compose up --build
```

> **Nota:** La primera vez tomará varios minutos porque debe descargar imágenes y construir los contenedores.

### Paso 4: Esperar a que todo esté listo

Verás muchos logs en la terminal. La aplicación estará lista cuando veas algo como:

```
backend       | Started DemoApplication in X.XXX seconds
frontend      | ➜  Local:   http://localhost:5173/
frontend      | ➜  Network: http://172.x.x.x:5173/
dbHarmoniApp  | database system is ready to accept connections
```

### Paso 5: Abrir la aplicación en el navegador

Abre tu navegador favorito y visita:

```
http://localhost:3000
```

¡Listo! Deberías ver el frontend de HarmoniApp funcionando.

---

## 🛑 Detener la Aplicación

Para detener todos los contenedores, presiona `Ctrl + C` en la terminal donde ejecutaste `docker compose up`.

O desde otra terminal, en la raíz del proyecto:

```bash
docker compose down
```

---

## 🔄 Reiniciar la Aplicación

Si hiciste cambios en el código y quieres verlos reflejados:

```bash
docker compose down
docker compose up --build
```

---

## 📍 Puertos Utilizados

La aplicación usa los siguientes puertos:

| Servicio       | Puerto Local | URL de Acceso              |
|----------------|--------------|----------------------------|
| **Frontend**   | 3000         | http://localhost:3000      |
| **Backend**    | 8080         | http://localhost:8080      |
| **Base de Datos** | 5432      | localhost:5432 (PostgreSQL)|

> **Importante:** Asegúrate de que estos puertos no estén siendo usados por otras aplicaciones.

---

## ❓ Problemas Comunes

### Error: "port is already allocated" o "puerto ya está en uso"

**Solución:** Algún puerto está siendo usado por otra aplicación.

1. Detén cualquier aplicación que pueda estar usando los puertos 3000, 8080 o 5432
2. O cambia el puerto en el `docker-compose.yml`:

```yaml
ports:
  - "3001:5173"  # Cambiar 3000 por 3001 para el frontend
```

### Error: "Docker daemon is not running"

**Solución:** Abre Docker Desktop y asegúrate de que esté ejecutándose.

### Los cambios en el código no se reflejan

**Solución:** Reconstruye los contenedores:

```bash
docker compose down
docker compose up --build
```

### Error: "Cannot connect to database"

**Solución:** Espera unos segundos más. La base de datos tarda un poco en inicializarse la primera vez.

---

## 🧹 Limpiar Todo (Opcional)

Si quieres eliminar completamente los contenedores, volúmenes e imágenes:

```bash
# Detener y eliminar contenedores y volúmenes
docker compose down -v

# Eliminar imágenes construidas
docker rmi harmoniapp-backend harmoniapp-frontend

# Limpiar todo Docker (¡cuidado! elimina todo lo no usado)
docker system prune -a
```

---

## 📞 Soporte

Si tienes problemas, verifica:

1. Que Docker Desktop esté ejecutándose
2. Que los puertos 3000, 8080 y 5432 estén libres
3. Los logs con: `docker compose logs -f`

---

## 🎉 ¡Eso es todo!

Ahora tienes HarmoniApp corriendo completamente en Docker. Happy coding! 🚀