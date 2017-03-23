# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table hospital (
  id                            bigint not null,
  numero_habitaciones           integer,
  constraint pk_hospital primary key (id)
);
create sequence HospitalEntity;

create table mediciones (
  referencia                    bigint not null,
  estado                        varchar(255),
  frecuencia                    varchar(255),
  estres                        varchar(255),
  presion                       varchar(255),
  fecha                         varchar(255),
  paciente_documento            bigint,
  constraint pk_mediciones primary key (referencia)
);
create sequence MedicionEntity;

create table medico (
  id_medico                     bigint not null,
  nombre_medico                 varchar(255),
  especialidad_medico           varchar(255),
  descripcion_medico            varchar(255),
  constraint pk_medico primary key (id_medico)
);
create sequence MedicoEntity;

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
  medico_id_medico              bigint,
  constraint pk_paciente primary key (documento)
);
create sequence PacienteEntity;

create table registro (
  id                            bigint not null,
  sensor_id                     bigint,
  creado_a                      date,
  dato                          float,
  constraint pk_registro primary key (id)
);
create sequence RegistroEntity;

create table sensores (
  id                            bigint not null,
  paciente_documento            bigint,
  tipo                          integer,
  fecha_asignacion              varchar(255),
  constraint ck_sensores_tipo check (tipo in (0,1,2)),
  constraint pk_sensores primary key (id)
);
create sequence SensorEntity;

create table urgencia (
  id                            bigint not null,
  latitud                       bigint,
  longitud                      bigint,
  constraint pk_urgencia primary key (id)
);
create sequence UrgenciaEntity;

alter table mediciones add constraint fk_mediciones_paciente_documento foreign key (paciente_documento) references paciente (documento) on delete restrict on update restrict;
create index ix_mediciones_paciente_documento on mediciones (paciente_documento);

alter table paciente add constraint fk_paciente_medico_id_medico foreign key (medico_id_medico) references medico (id_medico) on delete restrict on update restrict;
create index ix_paciente_medico_id_medico on paciente (medico_id_medico);

alter table registro add constraint fk_registro_sensor_id foreign key (sensor_id) references sensores (id) on delete restrict on update restrict;
create index ix_registro_sensor_id on registro (sensor_id);

alter table sensores add constraint fk_sensores_paciente_documento foreign key (paciente_documento) references paciente (documento) on delete restrict on update restrict;
create index ix_sensores_paciente_documento on sensores (paciente_documento);


# --- !Downs

alter table if exists mediciones drop constraint if exists fk_mediciones_paciente_documento;
drop index if exists ix_mediciones_paciente_documento;

alter table if exists paciente drop constraint if exists fk_paciente_medico_id_medico;
drop index if exists ix_paciente_medico_id_medico;

alter table if exists registro drop constraint if exists fk_registro_sensor_id;
drop index if exists ix_registro_sensor_id;

alter table if exists sensores drop constraint if exists fk_sensores_paciente_documento;
drop index if exists ix_sensores_paciente_documento;

drop table if exists hospital cascade;
drop sequence if exists HospitalEntity;

drop table if exists mediciones cascade;
drop sequence if exists MedicionEntity;

drop table if exists medico cascade;
drop sequence if exists MedicoEntity;

drop table if exists paciente cascade;
drop sequence if exists PacienteEntity;

drop table if exists registro cascade;
drop sequence if exists RegistroEntity;

drop table if exists sensores cascade;
drop sequence if exists SensorEntity;

drop table if exists urgencia cascade;
drop sequence if exists UrgenciaEntity;

