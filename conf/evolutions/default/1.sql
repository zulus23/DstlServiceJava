# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table gtk_deviationdelivery (
  id                            integer identity(1,1) not null,
  decription                    varchar(255),
  constraint pk_gtk_deviationdelivery primary key (id)
);

create table gtk_deviationshipment (
  id                            integer identity(1,1) not null,
  decription                    varchar(255),
  constraint pk_gtk_deviationshipment primary key (id)
);

create table gtk_enterprise (
  id                            integer identity(1,1) not null,
  name                          varchar(255),
  sldb                          varchar(255),
  service                       bit default 0,
  constraint pk_gtk_enterprise primary key (id)
);

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

create table gtk_userdstl (
  id                            integer identity(1,1) not null,
  name                          varchar(255),
  password                      varchar(255),
  enterprise                    varchar(255),
  constraint pk_gtk_userdstl primary key (id)
);

alter table gtk_normatimeloading add constraint fk_gtk_normatimeloading_identerprise foreign key (identerprise) references gtk_enterprise (id);
create index ix_gtk_normatimeloading_identerprise on gtk_normatimeloading (identerprise);


# --- !Downs

IF OBJECT_ID('fk_gtk_normatimeloading_identerprise', 'F') IS NOT NULL alter table gtk_normatimeloading drop constraint fk_gtk_normatimeloading_identerprise;
drop index if exists ix_gtk_normatimeloading_identerprise;

IF OBJECT_ID('gtk_deviationdelivery', 'U') IS NOT NULL drop table gtk_deviationdelivery;

IF OBJECT_ID('gtk_deviationshipment', 'U') IS NOT NULL drop table gtk_deviationshipment;

IF OBJECT_ID('gtk_enterprise', 'U') IS NOT NULL drop table gtk_enterprise;

IF OBJECT_ID('gtk_normatimeloading', 'U') IS NOT NULL drop table gtk_normatimeloading;

IF OBJECT_ID('gtk_planshipment', 'U') IS NOT NULL drop table gtk_planshipment;

IF OBJECT_ID('gtk_planshipmentdetail', 'U') IS NOT NULL drop table gtk_planshipmentdetail;

IF OBJECT_ID('gtk_userdstl', 'U') IS NOT NULL drop table gtk_userdstl;

