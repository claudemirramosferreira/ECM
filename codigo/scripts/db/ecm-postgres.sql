
CREATE ROLE ecm LOGIN
  SUPERUSER CREATEDB CREATEROLE REPLICATION
   VALID UNTIL 'infinity';

-- senha: ecm_2015
ALTER ROLE ecm ENCRYPTED PASSWORD 'md53a7018a501e04910e7872f6dee65b421' VALID UNTIL 'infinity';


CREATE DATABASE "teste-ecm"
  WITH ENCODING='UTF8'
       OWNER=ecm
       TEMPLATE=template1
       CONNECTION LIMIT=-1;
	   
CREATE SCHEMA ecm AUTHORIZATION ecm;

BEGIN;
	CREATE TABLE ECM.USER_SESSION (
	  ID_USER_SESSION BIGINT,
	  TX_LOGIN TEXT NOT NULL,
	  TX_TOKEN TEXT NOT NULL,
	  TX_IP_ADRESS TEXT NOT NULL,
	  TX_BROWSER TEXT NOT NULL,
	  DT_START_OR_REFRESH_SESSION TIMESTAMP DEFAULT NOW(),
	  DT_EXPIRED_SESSION TIMESTAMP,
	  CONSTRAINT PK_USER_SESSION PRIMARY KEY (ID_USER_SESSION)
	);

	ALTER TABLE ECM.USER_SESSION OWNER TO ECM;

	CREATE SEQUENCE ECM.USER_SESSION_SEQ START 1 MINVALUE 1 INCREMENT 1;
	ALTER SEQUENCE ECM.USER_SESSION_SEQ OWNER TO ECM;

	ALTER TABLE ECM.USER_SESSION
	ALTER COLUMN ID_USER_SESSION SET DEFAULT NEXTVAL('ECM.USER_SESSION_SEQ');
COMMIT;


CREATE TABLE ecm.pessoa
(
  id_pessoa bigint NOT NULL,
  tx_nome character varying(100) NOT NULL,
  cs_sexo character varying(1) NOT NULL,
  tx_celular character varying(15),
  tx_cpf character varying(11),
  tx_rg character varying(20),
  dt_data_nascimento timestamp without time zone NOT NULL,
  tx_email character varying(150),
  CONSTRAINT pessoa_pkey PRIMARY KEY (id_pessoa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ecm.pessoa
  OWNER TO ecm;

-- Index: ecm.xakpessoa

-- DROP INDEX ecm.xakpessoa;

CREATE UNIQUE INDEX xakpessoa
  ON ecm.pessoa
  USING btree
  (lower(tx_cpf::text) COLLATE pg_catalog."default");

  
CREATE SEQUENCE ecm.pessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ecm.pessoa_id_seq
  OWNER TO ecm;

CREATE TABLE ecm.usuario
(
  id_usuario bigint NOT NULL,
  tx_login character varying(15) NOT NULL,
  tx_senha character varying(50) NOT NULL,
  cs_status integer NOT NULL,
  tx_mensagem character varying(255),
  dt_data_acesso timestamp without time zone,
  nb_tentativas smallint,
  dt_data_tentativa_login timestamp without time zone,
  CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
  CONSTRAINT pessoa_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES ecm.pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ecm.usuario
  OWNER TO ecm;

-- Index: ecm.xakusuario

-- DROP INDEX ecm.xakusuario;

CREATE UNIQUE INDEX xakusuario
  ON ecm.usuario
  USING btree
  (lower(tx_login::text) COLLATE pg_catalog."default");


-- Insert do usuario Master, SENHA: 123qwe
INSERT INTO ecm.pessoa(id_pessoa, tx_nome, cs_sexo, tx_celular, tx_cpf, tx_rg, dt_data_nascimento, tx_email)
    VALUES (1, 'Master', 'M', null, null, null, '2015-09-01 00:00:00', 'robsonrf@gmail.com');
INSERT INTO ecm.usuario( id_usuario, tx_login, tx_senha, cs_status, tx_mensagem, dt_data_acesso, nb_tentativas, dt_data_tentativa_login)
    VALUES (1, 'master', '46F94C8DE14FB36680850768FF1B7F2A', 1, '', '2015-09-01 08:00:00.000', null, null);
  
CREATE TABLE ecm.tipo_documento
(
  id_tipo_documento bigint NOT NULL,
  tx_tipo_documento character varying(50) NOT NULL,
  cs_ativo character(1) NOT NULL,
  CONSTRAINT tipo_documento_pkey PRIMARY KEY (id_tipo_documento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ecm.tipo_documento
  OWNER TO ecm;

CREATE UNIQUE INDEX ak_tx_tipo_documento
  ON ecm.tipo_documento
  USING btree
  (tx_tipo_documento COLLATE pg_catalog."default");

CREATE SEQUENCE ecm.tipo_documento_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ecm.tipo_documento_seq
  OWNER TO ecm;

  
