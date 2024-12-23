# Descripción del Proyecto

**AplanchadosConAmor** es una empresa pastelera altamente reconocida con sede en la ciudad de Popayán. Actualmente, busca implementar un sistema de gestión y registro de ventas que refleje su compromiso con la excelencia y la profesionalidad. A continuación, se detallan las necesidades y características principales del sistema:

## Necesidades del Sistema

### Registro de Entradas

Los empleados deben poder registrar todas las transacciones que resultan en ingresos para la empresa. Cada empleado tiene una cuenta, con un usuario y una contraseña (mas adelante se especifica este requerimiento). Las entradas pueden ser ventas de productos o ingresos adicionales como comisiones o alquiler de equipos. Cada entrada tiene un conjunto de datos específicos que deben ser capturados para garantizar un seguimiento preciso y detallado de los ingresos.

- **Ingreso**:
  - Tipo de documento del cliente (opcional)
  - Documento  del cliente (opcional)
  - Nombre del cliente
  - Tipo de pago (contado o crédito)
  - Medio de pago utilizado (de una lista predefinida)
  - Valor de la transacción
  - Detalles adicionales relevantes (opcional)
  - Concepto del ingreso (de venta u otros conceptos))
  - id del empleado que realiza los registros (se deduce del empleado que haya iniciado sesion en else)

### Registro de Salidas

Este módulo gestiona todas las transacciones que implican la salida de fondos de la empresa. Esto incluye la adquisición de insumos necesarios para la producción y los gastos operativos. Es crucial que cada salida esté bien documentada con información detallada para asegurar la precisión en la contabilidad y la gestión de los recursos.

- **Tipos de salidas**:
  - Concepto de egreso (insumo, salida gerencia, gastos operativos cotidianos u otros)
  - tipo de documento(a quien va dirijida la salida)
  - Número de documento específico (a quien va dirijida la salida)
  - Detalle adicional (opcional)
  - Valor correspondiente

### Resúmenes Diarios

El sistema debe generar un resumen diario que consolide toda la actividad financiera del día. Este resumen incluye detalles sobre las ventas al contado y a crédito, así como un balance general del día, destacando el saldo inicial, el saldo final, ingresos, egresos, y las ganancias. Este resumen es vital para ofrecer una visión clara y precisa del rendimiento financiero diario de la empresa. Se debe generar a partir de los movimientos registrados en el mismo día, dicho resumen se debe generar a partir de tanto los movimientos registrados individualmente en el sistema, o la carga de un archivo de excel con los movimientos. 

- **Información consolidada**:
  - Total de ventas realizadas al contado
  - Total de ventas a crédito
- **Resumen del día**:
  - Saldo inicial de fondos en caja
  - Saldo final
  - Ingresos totales generados
  - Egresos totales efectuados
  - Valores entregados a gerencia durante el día
  - Ganancias totales

## Funcionalidades del Sistema

### Para Empleados
- **Registro de movimientos individuales** Los empleados deben poder registrar un movimiento en el día. 
- **Registro de conjunto de movimientos** los empleados deben poder registrar multiples movimientos en un solo formulario en excel, y tener la opcion de cargar 
- **Visualización de movimientos cargados** Los empleados deben poder vizualizar los movimientos que registraron, para saber si cometieron algún error, más no podrán editarlos. 

### Para Gerentes (más todas las funcionalidades de empleados)
- **Gestión de resúmenes diarios**:
  - Visualización de resumenes diarios: El gerente debe poder ver la información generada por los movimientos cargados por los empleados en un día, es decir, el empleado simplemente carga los movimientos, mientras que el gerente ve los movimientos y el resumen diario. 
  - Descarga de resúmenes a archivos de Excel: El gerente debe poder descargar el resumen diario a un archivo de excel, que contiene tanto el resumen como los movimientos que lo generan
  - Edición de movimientos: El gerente debe poder editar(agregar, eliminar o modificar) los movimientos, y por tanto el resumen diario. Esto es en caso de que un empleado reporte un error en el registro, y el gerente considere necesario su edición. 
- **Visualización contable**:
  - Información contable separada por meses
  - Gráficas que muestran ingresos, egresos y utilidades mensuales

### Seguridad y Administración de Usuarios
- **Necesidad de login**:
  - El gerente registra a los empleados y puede cambiar sus credenciales: Nombre, apellido, cédula(nombre de usuario), contraseña. También tiene la opción de cargar una lista de excel con los datos de los empleados y crear los usuarios. 
  - El gerente tiene un usuario definido por el sistema
- **Asociación entre movimiento y usuario que lo registra**:
  - En caso de cargar un resumen diario completo, todos los movimientos se registran a nombre del usuario que llenó el resumen
  - En caso de registrar un movimiento individual, el movimiento se asocia al usario que lo registra

Este sistema de gestión y registro de ventas no solo servirá como una herramienta invaluable para el control financiero interno de AplanchadosConAmor, sino que también demostrará su compromiso continuo con la calidad, la transparencia y la eficiencia en todas sus operaciones comerciales.

### Intrucciones de ejecucion
- Ejecutar el comando: gradle build
- Usa tu editor preferido para correr AplanchadosApplication.java
- Entra a `localhost:8081`
- Para entrar con un usuario con autoridad `MANAGE SYSTEM`, ingresa con las credenciales `123`, `123`
