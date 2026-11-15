# GestionDocu

Aplicación de consola para la gestión de despachos, remitos y usuarios. El acceso se controla mediante autenticación y permisos asociados a tres roles: `admin`, `operario` y `logistica`.

> **Estado del almacenamiento:** los datos se mantienen en memoria durante la ejecución. No existe persistencia en archivos ni en una base de datos; al cerrar el programa se pierden los despachos y usuarios creados o modificados.

## Funcionalidades por rol

| Funcionalidad | Administrador (`admin`) | Operario (`operario`) | Logística (`logistica`) |
|---|:---:|:---:|:---:|
| Iniciar sesión | Sí | Sí | Sí |
| Consultar despachos | Sí | Sí | Sí |
| Crear despacho | — | Sí | — |
| Modificar despacho | — | Sí | — |
| Eliminar despacho | Sí | — | — |
| Gestionar usuarios | Sí | — | — |

### Administrador

- Consulta los despachos cargados.
- Elimina despachos mediante su ID.
- Administra usuarios: crear, listar, buscar por ID, actualizar y eliminar.
- No puede eliminar al único usuario con rol de administrador ni quitarle ese rol.

### Operario

- Carga despachos con número de remito, productos, operario responsable, cantidad de bultos y nota.
- Consulta despachos.
- Actualiza un despacho mediante su ID: puede cambiar el operario, los bultos o la nota, y marcarlo como entregado.

### Logística

- Consulta los despachos cargados.

## Requisitos

- JDK 26, de acuerdo con la configuración de compilación del proyecto.
- Apache Ant para compilar desde la línea de comandos. NetBeans también puede abrir y ejecutar directamente el proyecto.

El proyecto utiliza bibliotecas estándar de Java y no declara dependencias externas.

## Compilación y ejecución

Desde la carpeta raíz del proyecto:

```shell
ant clean jar
java -jar dist/GestionDocu.jar
```

También se puede ejecutar con Ant:

```shell
ant run
```

Al iniciar, la aplicación solicita usuario y contraseña. Los datos de ejemplo disponibles son:

| Rol | Usuario | Contraseña |
|---|---|---|
| Administrador | `admin` | `admin123` |
| Operario | `operario` | `oper123` |
| Logística | `logistica` | `log123` |

Estas cuentas se crean en el código al inicializar `Usuarios`. Son únicamente para demostración: no deben utilizarse en un entorno real.

## Uso básico

1. Inicie la aplicación y autentíquese con una cuenta habilitada.
2. Seleccione la operación correspondiente a su rol en el menú.
3. Para modificar o eliminar registros, introduzca el ID mostrado al consultar la lista.
4. En la gestión de usuarios, los cargos admitidos son `admin`, `operario` y `logistica`.
5. Seleccione `0` para volver al menú anterior o salir, según la pantalla.

Los campos de texto solicitados durante el alta deben contener un valor. Las cantidades de productos y bultos no pueden ser negativas; el número de remito no puede repetirse entre los despachos activos.

## Manual del programador

### Organización del código

El código fuente se encuentra bajo `src/` y está organizado por responsabilidad:

| Paquete | Responsabilidad |
|---|---|
| `app` | Punto de entrada de la aplicación. `Main` inicia la autenticación y abre el menú principal. |
| `igu` | Interacción por consola: inicio de sesión, menú principal por rol y menú de usuarios. |
| `logica` | Entidades y reglas del dominio: personas, trabajadores, usuarios, documentos, remitos, despachos, detalles y productos. |
| `persistencia` | Operaciones de creación, consulta, actualización y eliminación en las colecciones en memoria. |
| `utils` | Lectura de datos desde la consola mediante `Utiles`. |

### Clases principales

- `app.Main`: coordina el inicio del programa.
- `igu.Login`: solicita credenciales y delega su validación a `Usuarios`.
- `igu.MenuPrincipal`: presenta las opciones disponibles según el cargo autenticado y deriva las operaciones a `DespachoCrud`.
- `igu.MenuUsuario`: ofrece las operaciones de administración de usuarios a través de `UsuariosCrud`.
- `logica.Usuarios`: modela la cuenta y el trabajador, valida los cargos permitidos y mantiene la colección de cuentas registradas.
- `logica.Documento`, `logica.Remito` y `logica.Despacho`: representan la jerarquía documental y el estado del despacho.
- `logica.Producto` y `logica.Detalle`: representan los productos y su asociación con un remito.
- `persistencia.DespachoCrud` y `persistencia.UsuariosCrud`: encapsulan las operaciones de gestión y la interacción de consola para cada conjunto de datos.
- `persistencia.DocumentoCrud`: reúne documentos para su consulta; actualmente los despachos son la implementación concreta incluida.

### Modelo y reglas relevantes

- Los cargos válidos se definen en `Usuarios.CARGOS_VALIDOS`.
- Los usuarios iniciales se registran mediante un inicializador estático de `Usuarios`.
- Los despachos se guardan en la lista de instancia de `DespachoCrud`; los usuarios, en una lista estática de `Usuarios`.
- El ID de documento se asigna secuencialmente en `Documento`; los IDs de usuario y trabajador se asignan en sus respectivas clases.
- La fecha de creación de un despacho es la fecha actual. `Remito` calcula la fecha de entrega como el día siguiente.
- Las operaciones interactivas leen datos con `Utiles`; la validación específica de cada operación está en las clases CRUD.

### Flujo para extender el sistema

1. Añada o ajuste la entidad y sus reglas en `logica`.
2. Implemente las operaciones de gestión correspondientes en `persistencia`, manteniendo allí las validaciones de entrada relacionadas con la operación.
3. Exponga las acciones en el menú de `igu` que corresponda y compruebe el rol antes de permitirlas.
4. Actualice este documento y valide el cambio compilando y ejecutando la aplicación.

### Compilación del proyecto

La configuración de Ant y NetBeans se encuentra en `build.xml` y `nbproject/`. Las propiedades del proyecto definen `src` como carpeta fuente, `app.Main` como clase principal y Java 26 como nivel de compilación. Los artefactos generados se guardan en `build/` y `dist/`; no edite manualmente esos directorios.

## Limitaciones y consideraciones de seguridad

- El almacenamiento es volátil; los datos no sobreviven al cierre del proceso.
- Las credenciales de demostración están incluidas en el código y se comparan directamente. No hay almacenamiento seguro de contraseñas ni una capa de autenticación apta para producción.
- Los IDs se generan en memoria y pueden cambiar al reiniciar el programa.
- La interfaz es de consola y la gestión disponible se limita a las opciones expuestas en los menús.

Antes de desplegar el sistema en un entorno real, se requiere incorporar persistencia, gestión segura de credenciales y controles de acceso adecuados.
