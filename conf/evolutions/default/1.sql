# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table gtk_deviationdelivery (
  id                            integer not null,
  decription                    varchar(255),
  constraint pk_gtk_deviationdelivery primary key (id)
);
create sequence gtk_DeviationDelivery_seq;

create table gtk_deviationshipment (
  id                            integer not null,
  decription                    varchar(255),
  constraint pk_gtk_deviationshipment primary key (id)
);
create sequence gtk_DeviationShipment_seq;

create table gtk_enterprise (
  id                            integer not null,
  name                          varchar(255),
  sldb                          varchar(255),
  service                       boolean,
  constraint pk_gtk_enterprise primary key (id)
);
create sequence gtk_Enterprise_seq;

create table gtk_normatimeloading (
  identerprise                  integer,
  package_time                  integer,
  commission_time               integer,
  placer_time                   integer
);

create table gtk_planshipment (
  id                            integer,
  name                          varchar(255),
  plan_date                     date
);

create table gtk_planshipmentdetail (
  id                            integer
);

alter table gtk_normatimeloading add constraint fk_gtk_normatimeloading_identerprise foreign key (identerprise) references gtk_enterprise (id) on delete restrict on update restrict;
create index ix_gtk_normatimeloading_identerprise on gtk_normatimeloading (identerprise);


# --- !Downs

alter table gtk_normatimeloading drop constraint if exists fk_gtk_normatimeloading_identerprise;
drop index if exists ix_gtk_normatimeloading_identerprise;

drop table if exists gtk_deviationdelivery;
drop sequence if exists gtk_DeviationDelivery_seq;

drop table if exists gtk_deviationshipment;
drop sequence if exists gtk_DeviationShipment_seq;

drop table if exists gtk_enterprise;
drop sequence if exists gtk_Enterprise_seq;

drop table if exists gtk_normatimeloading;

drop table if exists gtk_planshipment;

drop table if exists gtk_planshipmentdetail;

