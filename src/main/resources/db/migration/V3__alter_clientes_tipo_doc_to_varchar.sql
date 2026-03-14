ALTER TABLE clientes
    ALTER COLUMN tipo_doc_identidad TYPE VARCHAR(10) USING tipo_doc_identidad::VARCHAR;

DROP TYPE tipo_doc_identidad;
