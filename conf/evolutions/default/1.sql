# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table gtk_dstl_enterprise (
  id                            integer identity(1,1) not null,
  name                          varchar(255),
  sldb                          varchar(255),
  servicedstl                   bit default 0,
  constraint pk_gtk_dstl_enterprise primary key (id)
);

create table gtk_dstl_normatimeloading (
  id                            integer identity(1,1) not null,
  identerprise                  integer,
  packagetime                   integer,
  commissiontime                integer,
  placertime                    integer,
  constraint pk_gtk_dstl_normatimeloading primary key (id)
);

create table gtk_planshipment (
  id                            integer,
  name                          varchar(255),
  plan_date                     date
);

create table gtk_planshipmentdetail (
  id                            integer
);

create table gtk_dstl_user (
  id                            integer identity(1,1) not null,
  name                          varchar(255),
  password                      varchar(255),
  idservice                     integer,
  constraint pk_gtk_dstl_user primary key (id)
);

create table gtk_dstl_worktime (
  id                            integer identity(1,1) not null,
  idservicedstl                 integer,
  starttime                     time,
  endtime                       time,
  worktime                      bit default 0,
  constraint pk_gtk_dstl_worktime primary key (id)
);

alter table gtk_dstl_normatimeloading add constraint fk_gtk_dstl_normatimeloading_identerprise foreign key (identerprise) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_normatimeloading_identerprise on gtk_dstl_normatimeloading (identerprise);

alter table gtk_dstl_user add constraint fk_gtk_dstl_user_idservice foreign key (idservice) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_user_idservice on gtk_dstl_user (idservice);

alter table gtk_dstl_worktime add constraint fk_gtk_dstl_worktime_idservicedstl foreign key (idservicedstl) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_worktime_idservicedstl on gtk_dstl_worktime (idservicedstl);


# --- !Downs

IF OBJECT_ID('fk_gtk_dstl_normatimeloading_identerprise', 'F') IS NOT NULL alter table gtk_dstl_normatimeloading drop constraint fk_gtk_dstl_normatimeloading_identerprise;
drop index if exists ix_gtk_dstl_normatimeloading_identerprise;

IF OBJECT_ID('fk_gtk_dstl_user_idservice', 'F') IS NOT NULL alter table gtk_dstl_user drop constraint fk_gtk_dstl_user_idservice;
drop index if exists ix_gtk_dstl_user_idservice;

IF OBJECT_ID('fk_gtk_dstl_worktime_idservicedstl', 'F') IS NOT NULL alter table gtk_dstl_worktime drop constraint fk_gtk_dstl_worktime_idservicedstl;
drop index if exists ix_gtk_dstl_worktime_idservicedstl;

IF OBJECT_ID('gtk_dstl_enterprise', 'U') IS NOT NULL drop table gtk_dstl_enterprise;

IF OBJECT_ID('gtk_dstl_normatimeloading', 'U') IS NOT NULL drop table gtk_dstl_normatimeloading;

IF OBJECT_ID('gtk_planshipment', 'U') IS NOT NULL drop table gtk_planshipment;

IF OBJECT_ID('gtk_planshipmentdetail', 'U') IS NOT NULL drop table gtk_planshipmentdetail;

IF OBJECT_ID('gtk_dstl_user', 'U') IS NOT NULL drop table gtk_dstl_user;

IF OBJECT_ID('gtk_dstl_worktime', 'U') IS NOT NULL drop table gtk_dstl_worktime;

