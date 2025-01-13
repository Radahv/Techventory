Descripción del Funcionamiento de la Aplicación Techventory
La aplicación de Techventory está diseñada para la gestión eficiente de los recursos y materiales del área de IT. 
Está desarrollada en Java utilizando Swing para la interfaz gráfica y MySQL como base de datos, lo que asegura un almacenamiento confiable de los datos.

A continuación, se detalla el funcionamiento y características principales:

Características de la Aplicación

    1. Acceso Restringido
        Solo usuarios registrados en la base de datos pueden acceder a la aplicación mediante un sistema de login seguro.
        Las contraseñas están protegidas utilizando técnicas de hashing para garantizar la seguridad.
        El administrador tiene la capacidad de agregar o eliminar usuarios.

    2. Inventario de Materiales
        Gestión completa de materiales, equipos, dispositivos de telefonía, entre otros recursos.
        Cada material incluye información detallada como nombre, cantidad disponible y, opcionalmente, una imagen asociada.
        Posibilidad de buscar materiales por nombre o filtrar por categorías.
        Control automático del stock: al asignar un material, la cantidad disponible se ajusta automáticamente.

    3. Gestión de Usuarios
        Permite registrar usuarios con información relevante como nombre, cargo y contacto.
        Los usuarios pueden visualizar los materiales asignados a ellos y recibir notificaciones sobre nuevas entregas.

    4. Asignación de Materiales
        Registro de entregas de materiales a usuarios específicos.
        Validación para asegurar que la cantidad solicitada no supere la cantidad disponible en el inventario.
        Historial de asignaciones para cada usuario, con la posibilidad de editar o eliminar registros.

    5. Compatibilidad Multiplataforma
        La aplicación no requiere conexión a internet, pero está diseñada para ser compatible con cualquier equipo que tenga Java Runtime Environment (JRE) instalado.

Flujo Básico de Uso

    1. Inicio de Sesión
        El usuario ingresa su nombre de usuario y contraseña.
        Si las credenciales son correctas, accede al panel principal.

    2. Gestión del Inventario
        Desde el panel principal, el usuario puede visualizar, agregar o editar materiales en el inventario.
        También puede ver imágenes asociadas y actualizar cantidades según sea necesario.

    3. Asignación de Recursos
        El usuario selecciona un material, elige el destinatario y define la cantidad a asignar.
        La cantidad disponible del material se reduce automáticamente en el inventario.

    4. Gestión de Usuarios
        Permite agregar nuevos usuarios, asignarles materiales o eliminar registros de usuarios obsoletos.
