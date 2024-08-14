# **Formato especificación de entidades**

| **Nombre**     | Persona                                      |
| -------------- | -------------------------------------------- |
| **Definición** | Causante de un ingreso o egreso              |
| **Dominio**    | Cualquier individuo registrado en el sistema |
| **Tipo**       | Fundamental                                  |

| **Nombre**     | Ingreso                                                                                                                |
| -------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **Definición** | Valor que entra al sistema por medio de una persona ya sea por una venta de producto o cualquier transacción adicional |
| **Dominio**    | Concepto de Ingreso                                                                                                   |
| **Tipo**       | Fundamental                                                                                                            |

| **Nombre**     | Medio de pago                                                |
| -------------- | ------------------------------------------------------------ |
| **Definición** | Forma en la que se realiza el pago de un producto o servicio |
| **Dominio**    | Concepto de Medio de pago                                    |
| **Tipo**       | Fundamental                                                  |

| **Nombre**     | Tipo de pago                      |
| -------------- | --------------------------------- |
| **Definición** | Clasificación de la forma de pago |
| **Dominio**    | Concepto de Tipo de pago          |
| **Tipo**       | Fundamental                       |

| **Nombre**     | Concepto de ingreso                |
| -------------- | ---------------------------------- |
| **Definición** | Descripción del motivo del ingreso |
| **Dominio**    | Concepto de Ingreso                |
| **Tipo**       | Fundamental                        |

| **Nombre**     | Concepto de egreso                |
| -------------- | --------------------------------- |
| **Definición** | Descripción del motivo del egreso |
| **Dominio**    | Concepto de egreso                |
| **Tipo**       | Fundamental                       |

| **Nombre**     | Egreso                                                                                               |
| -------------- | ---------------------------------------------------------------------------------------------------- |
| **Definición** | Valor que se usa para la adquisición de insumos para la producción o gasto necesario para la empresa |
| **Dominio**    | Concepto de Egreso                                                                                   |
| **Tipo**       | Fundamental                                                                                          |


| **Nombre**     | Resumen diario                                                                                                                                                                |
| -------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Definición** | Una sintesis que recopila la informacion relevante del dia para tener claridad sobre acontecimientos y sus afectaciones en el negocio, al igual que mantener un orden general |
| **Dominio**    | Abarca cualquier movimento en el dia determinado                                                                                                                              |
| **Tipo**       | Fundamental                                                                                                                                                                   |

# **Formato especificación de atributos**

### Entidad: Persona

| Nombre            | Definición                         | Dominio                            | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ----------------- | ---------------------------------- | ---------------------------------- | ------------- | ---------- | --------------- |
| tipoDeDocumento   | Tipo de documento de la persona asociado a su numero de documento    | ['CC', 'TI', 'CE', 'Pasaporte','NIT','Fideicomiso','RC']                |               |            | x               |
| numeroDeDocumento | Id que identifica a la persona | Cadena VARCHAR de longitud 15   | x             | x          | x               |
| nombre            | Nombre de la persona               | Cadena VARCHAR de longitud 11 |               |            | x               |

### Entidad: Egreso

| Nombre           | Definición                           | Dominio                                                                     | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ---------------- | ------------------------------------ | --------------------------------------------------------------------------- | ------------- | ---------- | --------------- |
| valor            | Valor del egreso                     | Float number                                               |               |            | x               |
| idEgreso         | Identificador del egreso             | Cadena VARCHAR de longitud 10 | x             | x          | x               |
| fecha            | Fecha en la que se realiza el egreso | Date de la fecha del día                      |               |            | x               |

### Entidad: Ingreso

| Nombre           | Definición                            | Dominio                                                                         | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ---------------- | ------------------------------------- | ------------------------------------------------------------------------------- | ------------- | ---------- | --------------- |
| valor            | Valor del ingreso                     | Numero que contiene el ingreso                                                  |               |            | x               |
| detalleAdicional | Detalle adicional del ingreso         | Cadena VARCHAR de longitud 50 |               |            |                 |
| idIngreso        | Identificador del ingreso             | Cadena VARCHAR de longitud 10      | x             | x          | x               |
| fecha            | Fecha en la que se realiza el ingreso | Date de la fecha del día                                                        |               |            | x               |


### Entidad: Medio de pago
| Nombre      | Definición                                | Dominio                             | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ----------- | ----------------------------------------- | ----------------------------------- | ------------- | ---------- | --------------- |
| codigo      | Códigos correspondientes al medio de pago | Cadena VARCHAR de longitud 10 | x             | x          | x               |
| descripcion | Descripción del medio de pago, con su nombre por ejemplo             | Cadena VARCHAR de longitud 50, tentativamnte [Transferencia, Tarjeta y Efectivo]  |               |            | x               |

### Entidad: Tipo de pago
| Nombre      | Definición                               | Dominio                            | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ----------- | ---------------------------------------- | ---------------------------------- | ------------- | ---------- | --------------- |
| codigo      | Códigos correspondientes al tipo de pago |  Cadena VARCHAR de longitud 10                | x             | x          | x               |
| descripcion | Descripción del tipo de pago             | Cadena VARCHAR de longitud 50, tentativamnte [Contado, Credito] |               |            | x               |

### Entidad: Concepto de ingreso
| Nombre      | Definición                           | Dominio                            | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ----------- | ------------------------------------ | ---------------------------------- | ------------- | ---------- | --------------- |
| codigo      | Códigos correspondientes al concepto | Cadena VARCHAR de longitud 10      | x             | x          | x               |
| descripcion | Descripción del concepto             | Cadena VARCHAR de longitud 50, tentativamnte [Venta, Transacción Adicional]   |               |            | x               |

### Entidad: Concepto de egreso
| Nombre      | Definición                           | Dominio                            | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ----------- | ------------------------------------ | ---------------------------------- | ------------- | ---------- | --------------- |
| codigo      | Códigos correspondientes al concepto | Cadena VARCHAR de longitud 10         | x             | x          | x               |
| descripcion | Descripción del concepto             |  Cadena VARCHAR de longitud 50, tentativamnte [Insumo, Valor de gerencia] |               |            | x               |

### Entidad: Resumen diario

| Nombre             | Definición                 | Dominio                   | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ------------------ | -------------------------- | ------------------------- | ------------- | ---------- | --------------- |
| saldoInicial       | Saldo inicial del día      | Float Number |               |            | X               |
| fecha              | Fecha del resumen del día  | Date      | X             |      X   | X               |
| totalVentasContado | Total de ventas al contado | Float Number  |               |            |   X              |
| totalVentasCredito | Total de ventas a crédito  | Float Number |               |            |      X           |
| sumaDeIngresos     | Suma de ingresos           | Float Number |               |            |     X            |
| sumaDeEgresos      | Suma de egresos            | Float Number |               |            |      X           |
| saldoFinal         | Saldo final del día        | Float Number |               |            |       X          |
| gananciaTotal      | Ganancia total del día     | Float Number |               |            |        X         |



> Es importante aclarar que para la realizacion del MR normalizado realizamos una tabla adicional para el tipo de documento que en una descripcion de entidad seria del siguiente tipo

### Tabla: Tipos de documento

| Nombre             | Definición                 | Dominio                   | ¿Es primario? | ¿Es único? | ¿Es mandatorio? |
| ------------------ | -------------------------- | ------------------------- | ------------- | ---------- | --------------- |
| id_doc             | Identidficador unico del tipo de documento      | Cadena VARCHAR de longitud 10             | X             |  X         | X               |
| tipo               | Nombre del tipo de documento asociado a id  | Cadena VARCHAR de longitud 20                      |               |            | x               |
| descripcion        | Informacion opcional acerca del tipo de documento principalmente por si es extranjera y tiene restricciones o datos asociados | Cadena VARCHAR de longitud 50              |               |            |               |

