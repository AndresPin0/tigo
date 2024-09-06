set schema PUBLIC;

-- Creación de la tabla DocumentType
CREATE TABLE IF NOT EXISTS "document_type" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "expense_concept" (
    "code" INT AUTO_INCREMENT PRIMARY KEY,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "payment_method" (
    "code" INT AUTO_INCREMENT PRIMARY KEY,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "payment_type" (
    "code" INT AUTO_INCREMENT PRIMARY KEY,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "permission" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "role" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "person" (
    "document_type_id" INT,
    "document_number" VARCHAR(255),
    "name" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("document_number", "document_type_id"),
    FOREIGN KEY ("document_type_id") REFERENCES "document_type"("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "user" (
    "id" INT PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "role_id" INT,
    FOREIGN KEY ("role_id") REFERENCES "role"("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "role_permission" (
    "role_id" INT,
    "permission_id" INT,
    PRIMARY KEY ("role_id", "permission_id"),
    FOREIGN KEY ("role_id") REFERENCES "role"("id") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("permission_id") REFERENCES "permission"("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "income_concept" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "income" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "value" INTEGER,
    "additional_detail" VARCHAR(255),
    "person_document_type" INT,
    "person_document_number" VARCHAR(255),
    "date" DATE,
    "payment_method_code" INT,
    "payment_type_code" INT,
    "income_concept_code" INT,
    "user_id" INT,
    FOREIGN KEY ("person_document_type", "person_document_number") REFERENCES "person"("document_type_id", "document_number") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("payment_method_code") REFERENCES "payment_method"("code") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("payment_type_code") REFERENCES "payment_type"("code") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("income_concept_code") REFERENCES "income_concept"("id") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "user"("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "expense" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "value" INTEGER,
    "additional_detail" VARCHAR(255),
    "person_document_type" INT,
    "person_document_number" VARCHAR(255),
    "date" DATE,
    "payment_method_code" INT,
    "payment_type_code" INT,
    "expense_concept_code" INT,
    "user_id" INT,
    FOREIGN KEY ("person_document_type", "person_document_number") REFERENCES "person"("document_type_id", "document_number") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("payment_method_code") REFERENCES "payment_method"("code") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("payment_type_code") REFERENCES "payment_type"("code") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("expense_concept_code") REFERENCES "expense_concept"("code") ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "user"("id") ON DELETE CASCADE ON UPDATE CASCADE
);
-- SERIAL -> Autoincremental, se incrementa en 1 por cada registro, único y no nulo.
-- ON DELETE CASCADE -> Si se borra un usuario, se borran todos sus ingresos y egresos
-- ON UPDATE CASCADE -> Si se actualiza el documento de una persona, se actualiza en todos sus ingresos y egresos

-- ALTER TABLE "document_type" ADD CONSTRAINT "document_type_ name_unique" UNIQUE ("name");
-- ALTER TABLE "role" ADD CONSTRAINT "role_name_unique" UNIQUE ("name");
-- ALTER TABLE "permission" ADD CONSTRAINT "permission_name_unique" UNIQUE ("name");
-- ALTER TABLE "income_concept" ADD CONSTRAINT "income_concept_description_unique" UNIQUE ("description");
-- ALTER TABLE "payment_method" ADD CONSTRAINT "payment_method_description_unique" UNIQUE ("description");
-- ALTER TABLE "payment_type" ADD CONSTRAINT "payment_type_description_unique" UNIQUE ("description");

-- ALTER TABLE para el uso de claves únicas, para prevenir duplicados
-- Así que se definen estas restricciones de unicidad en las columnas de nombre de documento, nombre de rol y nombre de permiso.