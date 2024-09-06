set schema PUBLIC;

-- Uso de MERGE INTO para insertar datos en tablas base o maestras
    -- Ya que tiene manejo de conflictos:
        -- Si la fila ya existe, se actualiza.
        -- Si la fila no existe, se inserta.

-- Primero se insertan los datos en las tablas base o maestras, ya que estas no dependen de otras tablas.
-- Insertar document_type
MERGE INTO "document_type" ("name") KEY ("name")
    VALUES
        ('CC'), ('CE'), ('NIT'), ('Passport'), ('Driver License'), ('TI');



-- Insertar role
MERGE INTO "role" ("name") KEY ("name")
VALUES
    ('Administrator'), ('User'), ('Guest');

-- Insertar permission
MERGE INTO "permission" ("name") KEY ("name")
VALUES
    ('Create'), ('Read'), ('Update'), ('Delete');

-- Insertar payment_method
MERGE INTO "payment_method" ("description") KEY ("description")
VALUES
    ('Cash'), ('Credit Card'), ('Bank Transfer'), ('Check');

-- Insertar payment_type
MERGE INTO "payment_type" ("description") KEY ("description")
VALUES
    ('Full Payment'), ('Partial Payment'), ('Deferred Payment');


-- Insertar expense_concept
MERGE INTO "expense_concept" ("description") KEY ("description")
VALUES
    ('Office Supplies'), ('Travel Expenses'), ('Utilities');



-- Insertar income_concept
MERGE INTO "income_concept" ("description") KEY ("description")
VALUES
    ('Product Sales'), ('Service Fees'), ('Rental Income');


-- Seguido, se ejecutan las tablas con depedencias de claves foráneas.

-- Insertar person
MERGE INTO "person" ("document_type_id", "document_number", "name") KEY ("document_type_id", "document_number")
VALUES
    ((SELECT "id" FROM "document_type" WHERE "name" = 'CC'), '123456789', 'Andres'),
    ((SELECT "id" FROM "document_type" WHERE "name" = 'CE'), '987654321', 'Andrea');

-- Insertar user y asociarlos a un rol
MERGE INTO "user" ("id", "name", "last_name", "role_id") KEY ("id")
VALUES
    (1, 'Andres', 'Gomez', (SELECT "id" FROM "role" WHERE "name" = 'Administrator')),
    (2, 'Andrea', 'Castilla', (SELECT "id" FROM "role" WHERE "name" = 'User')),
    (3, 'Guest', 'Guest', (SELECT "id" FROM "role" WHERE "name" = 'Guest'));

-- De último, las tablas con múltiples dependencias.

-- Insertar expense
MERGE INTO "expense" ("id", "value", "additional_detail", "person_document_type", "person_document_number", "date", "payment_method_code", "payment_type_code", "expense_concept_code", "user_id") KEY ("id")
VALUES
    (1, 200000, 'Office Supplies', (SELECT "id" FROM "document_type" WHERE "name" = 'CC'), '123456789', '2021-01-03', (SELECT "code" FROM "payment_method" WHERE "description" = 'Cash'), (SELECT "code" FROM "payment_type" WHERE "description" = 'Full Payment'), (SELECT "code" FROM "expense_concept" WHERE "description" = 'Office Supplies'), 1),
    (2, 150000, 'Travel Expenses', (SELECT "id" FROM "document_type" WHERE "name" = 'CE'), '987654321', '2021-01-04', (SELECT "code" FROM "payment_method" WHERE "description" = 'Credit Card'), (SELECT "code" FROM "payment_type" WHERE "description" = 'Partial Payment'), (SELECT "code" FROM "expense_concept" WHERE "description" = 'Travel Expenses'), 2);


-- Insertar income
MERGE INTO "income" ("id","value", "additional_detail", "person_document_type", "person_document_number", "date", "payment_method_code", "payment_type_code", "income_concept_code", "user_id") KEY ("id")
VALUES
    (1,1000000, 'Product Sales', (SELECT "id" FROM "document_type" WHERE "name" = 'CC'), '123456789', '2021-01-01', (SELECT "code" FROM "payment_method" WHERE "description" = 'Cash'), (SELECT "code" FROM "payment_type" WHERE "description" = 'Full Payment'), (SELECT "id" FROM "income_concept" WHERE "description" = 'Product Sales'), 1),
    (2,500000, 'Service Fees', (SELECT "id" FROM "document_type" WHERE "name" = 'CE'), '987654321', '2021-01-02', (SELECT "code" FROM "payment_method" WHERE "description" = 'Credit Card'), (SELECT "code" FROM "payment_type" WHERE "description" = 'Partial Payment'), (SELECT "id" FROM "income_concept" WHERE "description" = 'Service Fees'), 2);


-- Insertar role_permission
MERGE INTO "role_permission" ("role_id", "permission_id") KEY ("role_id", "permission_id")
VALUES
    ((SELECT "id" FROM "role" WHERE "name" = 'Administrator'), (SELECT "id" FROM "permission" WHERE "name" = 'Create')),
    ((SELECT "id" FROM "role" WHERE "name" = 'Administrator'), (SELECT "id" FROM "permission" WHERE "name" = 'Read')),
    ((SELECT "id" FROM "role" WHERE "name" = 'Administrator'), (SELECT "id" FROM "permission" WHERE "name" = 'Update')),
    ((SELECT "id" FROM "role" WHERE "name" = 'Administrator'), (SELECT "id" FROM "permission" WHERE "name" = 'Delete')),
    ((SELECT "id" FROM "role" WHERE "name" = 'User'), (SELECT "id" FROM "permission" WHERE "name" = 'Read')),
    ((SELECT "id" FROM "role" WHERE "name" = 'User'), (SELECT "id" FROM "permission" WHERE "name" = 'Update')),
    ((SELECT "id" FROM "role" WHERE "name" = 'Guest'), (SELECT "id" FROM "permission" WHERE "name" = 'Read'));

