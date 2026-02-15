# 🏍️ SGPM - Sistema de Gestión de Parqueaderos de Motos

Aplicación web moderna para gestionar de manera eficiente el parqueadero de motos de la Universidad del Magdalena. Desarrollada con tecnologías actuales como Spring Boot para el backend y React con Vite para el frontend.

---

## 📋 Tabla de Contenidos

- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [API Documentation](#documentación-de-api)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Contribuidores](#contribuidores)

---

## ✨ Características

- ✅ Registro y autenticación de usuarios con JWT
- ✅ Gestión de espacios de parqueo en tiempo real
- ✅ Sistema de reservas y control de acceso
- ✅ Panel administrativo completo
- ✅ Reportes y estadísticas
- ✅ Interfaz responsiva y moderna
- ✅ Base de datos PostgreSQL robusta

---

## 📁 Estructura del Proyecto

```
sgpm/
├── sgpm-server/           # Backend en Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── sgpm-client/           # Frontend en React + Vite
│   ├── src/
│   ├── package.json
│   └── vite.config.js
├── docker-compose.yml     # Configuración de contenedores
├── .env                   # Variables de entorno
└── README.md
```

---

## ✅ Requisitos Previos

Antes de instalar SGPM, asegúrate de tener las siguientes herramientas instaladas:

- **Java 17+** - [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Node.js 18+** - [Descargar](https://nodejs.org/)
- **Docker Desktop** - [Descargar](https://www.docker.com/products/docker-desktop/)
- **PostgreSQL 14+** - (Se levanta en Docker)
- **IntelliJ IDEA** - [Descargar](https://www.jetbrains.com/idea/) (o tu IDE Java preferido)
- **Git** - [Descargar](https://git-scm.com/)

---

## ⚙️ Configuración

### 1. Configurar Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
# PostgreSQL Configuration
POSTGRES_DB=sgpm_db
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin123
POSTGRES_PORT=5433

# pgAdmin Configuration
PGADMIN_DEFAULT_EMAIL=admin@sgpm.local
PGADMIN_DEFAULT_PASSWORD=admin123

# Application Configuration
SERVER_PORT=8080
CLIENT_PORT=3000
```

### 2. Levantar la Base de Datos con Docker

Asegúrate de que **Docker Desktop esté corriendo**, luego ejecuta desde la raíz del proyecto:

```bash
docker-compose up -d
```

Esto levantará los siguientes servicios:

- **PostgreSQL**: http://localhost:5433
- **pgAdmin**: http://localhost:8081

Accede a pgAdmin con las credenciales configuradas en el `.env`.

---

## 🖥️ Instalación

### Backend (Spring Boot)

1. **Abre la carpeta `sgpm-server` en IntelliJ IDEA**

2. **Espera a que Maven descargue las dependencias**

3. **Configura la base de datos** en `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5433/sgpm_db
   spring.datasource.username=admin
   spring.datasource.password=admin123
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Ejecuta la aplicación** - Busca la clase principal (por ejemplo, `SgpmApplication.java`) y ejecuta con `Shift + F10` o haz clic en el botón de Play

5. **Verifica que el servidor esté corriendo**:
   - Backend disponible en: `http://localhost:8080`
   - Documentación Swagger: `http://localhost:8080/swagger-ui.html`

### Frontend (React + Vite)

1. **Abre una terminal en la carpeta `sgpm-client`**

2. **Instala las dependencias**:

   ```bash
   npm install
   ```

3. **Inicia el servidor de desarrollo**:

   ```bash
   npm run dev
   ```

4. **Accede a la aplicación**:
   - Frontend disponible en: `http://localhost:3000`

---

## ▶️ Ejecución del Proyecto Completo

Sigue estos pasos en orden para ejecutar toda la aplicación:

1. **Inicia Docker Desktop**

2. **Levanta los contenedores** desde la raíz del proyecto:

   ```bash
   docker-compose up -d
   ```

   Verifica que los contenedores estén corriendo:

   ```bash
   docker-compose ps
   ```

3. **Levanta el Backend**:
   - Abre `sgpm-server` en IntelliJ IDEA
   - Ejecuta la clase principal
   - Espera a que veas: `Tomcat started on port(s): 8080`

4. **Levanta el Frontend**:
   - Abre otra terminal en `sgpm-client`
   - Ejecuta: `npm run dev`
   - Espera a que veas: `Local: http://localhost:3000`

5. **Accede a la aplicación**:
   - Abre tu navegador en: `http://localhost:3000`
   - Inicia sesión con tus credenciales

---

## 📖 Documentación de API

La documentación interactiva de la API está disponible en Swagger:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principales

#### Autenticación
- `POST /api/v1/auth/login` - Iniciar sesión
- `POST /api/v1/auth/register` - Registrar nuevo usuario
- `POST /api/v1/auth/logout` - Cerrar sesión

#### Parqueadero
- `GET /api/v1/parking/spaces` - Obtener espacios disponibles
- `POST /api/v1/parking/reserve` - Realizar una reserva
- `GET /api/v1/parking/reservations` - Obtener mis reservas

#### Usuarios (Admin)
- `GET /api/v1/admin/users` - Listar usuarios
- `DELETE /api/v1/admin/users/{id}` - Eliminar usuario

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Spring Boot 3.x** - Framework web
- **Spring Security** - Autenticación y autorización
- **JWT** - Tokens de seguridad
- **JPA/Hibernate** - ORM
- **PostgreSQL** - Base de datos
- **Maven** - Gestor de dependencias
- **Swagger/OpenAPI** - Documentación de API

### Frontend
- **React 18+** - Biblioteca de UI
- **Vite** - Herramienta de construcción
- **Axios** - Cliente HTTP
- **React Router** - Enrutamiento
- **Tailwind CSS** o **Material-UI** - Estilos

### DevOps
- **Docker** - Containerización
- **Docker Compose** - Orquestación de contenedores

---

## ❓ Preguntas Frecuentes

### ¿Cómo reseteo la base de datos?

```bash
docker-compose down -v
docker-compose up -d
```

### ¿Cómo veo los logs del backend?

```bash
# En IntelliJ, mira la consola de salida
# O desde línea de comandos:
docker logs sgpm-postgres
```

### ¿Cómo cambio el puerto del servidor?

En `application.properties`:
```properties
server.port=8081
```

### ¿Cómo despliego a producción?

Se recomienda usar servicios como:
- **Backend**: AWS, Heroku, Railway
- **Frontend**: Vercel, Netlify, GitHub Pages

---

## ⚠️ Notas Importantes

- 🔐 **JWT está habilitado** por defecto a través de Spring Security
- 🔒 **Seguridad**: Cambia las credenciales por defecto antes de usar en producción
- 💾 **Base de datos**: Los datos persisten en volúmenes de Docker
- 🌐 **CORS**: Configurado para desarrollo local (ajusta en producción)
- 📱 **Responsive**: La aplicación está optimizada para dispositivos móviles
- 🚀 **Escalabilidad**: Este proyecto está diseñado para uso local y académico

---

## 📞 Soporte

Si encuentras problemas, intenta:

1. Verifica que Docker esté corriendo
2. Elimina `node_modules` y reinstala: `npm install`
3. Limpia el caché de Maven en IntelliJ
4. Revisa los logs en la consola de IntelliJ

---

## 👥 Desarrolladores

Este proyecto fue desarrollado por:

| Nombre | Rol |
|--------|-----|
| **León Cantillo** | Software Architect |
| **Steven Navarro** | Backend Developer |
| **Jonathan Vizcaíno** | Backend Developer |
| **Juan Sebastián Sarmiento** | Frontend Developer |
| **León Cantillo** | Frontend Developer |
| **Iván Marchena** | DevOps/Infrastructure |

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

**Última actualización**: 15 de febrero de 2026

¡Gracias por usar SGPM! 🚀
