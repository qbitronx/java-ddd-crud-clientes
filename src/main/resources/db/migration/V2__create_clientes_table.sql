CREATE TYPE tipo_doc_identidad AS ENUM ('DNI', 'RUC');

CREATE TABLE clientes (
    id                  UUID            NOT NULL,
    codigo              VARCHAR(50)     NOT NULL,
    tipo_doc_identidad  tipo_doc_identidad NOT NULL,
    nro_doc_identidad   VARCHAR(20)     NOT NULL,
    razon_social        VARCHAR(200)    NOT NULL,
    direccion           VARCHAR(300),
    activo              BOOLEAN         NOT NULL DEFAULT TRUE,
    fecha_creacion      TIMESTAMP       NOT NULL,
    fecha_actualizacion TIMESTAMP       NOT NULL,

    CONSTRAINT pk_clientes PRIMARY KEY (id),
    CONSTRAINT uq_clientes_codigo UNIQUE (codigo),
    CONSTRAINT uq_clientes_nro_doc_identidad UNIQUE (nro_doc_identidad)
);

CREATE INDEX idx_clientes_codigo ON clientes (codigo);
CREATE INDEX idx_clientes_nro_doc_identidad ON clientes (nro_doc_identidad);
CREATE INDEX idx_clientes_activo ON clientes (activo);
