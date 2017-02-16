# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table hospital (
  id                            bigint not null,
  numero_habitaciones           integer,
  constraint pk_hospital primary key (id)
);
create sequence Hospital;

create table medicion (
  referencia                    bigint auto_increment not null,
  estado                        varchar(255),
  frecuencia                    varchar(255),
  estres                        varchar(255),
  presion                       varchar(255),
  fecha                         varchar(255),
  paciente_documento            bigint,
  constraint pk_medicion primary key (referencia)
);

create table medicos (
  id_medico                     bigint not null,
  nombre_medico                 varchar(255),
  especialidad_medico           varchar(255),
  descripcion_medico            varchar(255),
  constraint pk_medicos primary key (id_medico)
);
create sequence Medico;

create table paciente (
  documento                     bigint not null,
  nombre                        varchar(255),
  tipo_sangre                   varchar(255),
  pais                          varchar(255),
  ciudad                        varchar(255),
  telefono                      bigint,
  celular                       bigint,
  tratamientos                  varchar(255),
  examenes                      varchar(255),
  constraint pk_paciente primary key (documento)
);
create sequence paciente_seq;

create table registro (
  id                            bigint not null,
  sensor_id                     bigint,
  creado_a                      date,
  dato                          double,
  constraint pk_registro primary key (id)
);
create sequence Registro;

create table sensores (
  id                            bigint not null,
  paciente_documento            bigint,
  tipo                          integer,
  fecha                         varchar(255),
  constraint ck_sensores_tipo check (tipo in (0,1,2)),
  constraint pk_sensores primary key (id)
);
create sequence Sensor;

create table urgencia (
  id                            bigint not null,
  latitud                       bigint,
  longitud                      bigint,
  paciente                      bigint,
  constraint pk_urgencia primary key (id)
);
create sequence urgencia_seq;

alter table medicion add constraint fk_medicion_paciente_documento foreign key (paciente_documento) references paciente (documento) on delete restrict on update restrict;
create index ix_medicion_paciente_documento on medicion (paciente_documento);

alter table registro add constraint fk_registro_sensor_id foreign key (sensor_id) references sensores (id) on delete restrict on update restrict;
create index ix_registro_sensor_id on registro (sensor_id);

alter table sensores add constraint fk_sensores_paciente_documento foreign key (paciente_documento) references paciente (documento) on delete restrict on update restrict;
create index ix_sensores_paciente_documento on sensores (paciente_documento);


# --- !Downs

alter table medicion drop constraint if exists fk_medicion_paciente_documento;
drop index if exists ix_medicion_paciente_documento;

alter table registro drop constraint if exists fk_registro_sensor_id;
drop index if exists ix_registro_sensor_id;

alter table sensores drop constraint if exists fk_sensores_paciente_documento;
drop index if exists ix_sensores_paciente_documento;

drop table if exists hospital;
drop sequence if exists Hospital;

drop table if exists medicion;

drop table if exists medicos;
drop sequence if exists Medico;

drop table if exists paciente;
drop sequence if exists paciente_seq;

drop table if exists registro;
drop sequence if exists Registro;

drop table if exists sensores;
drop sequence if exists Sensor;

drop table if exists urgencia;
drop sequence if exists urgencia_seq;

