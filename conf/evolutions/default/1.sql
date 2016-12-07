# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table gtk_dstl_deviation (
  typedeviation                 varchar(31) not null,
  id                            integer identity(1,1) not null,
  description                   varchar(255),
  reftosl                       varchar(40),
  constraint pk_gtk_dstl_deviation primary key (id)
);

create table gtk_dstl_enterprise (
  id                            integer identity(1,1) not null,
  name                          varchar(255),
  sldb                          varchar(255),
  servicedstl                   bit default 0,
  belongtoservice               integer,
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

create table gtk_dstl_planshipmentrequesttransport (
  id                            numeric(19) identity(1,1) not null,
  idplan                        numeric(19),
  identerprise                  integer,
  typeshipment                  varchar(255),
  placeshipment                 varchar(255),
  statusdispatcher              varchar(255),
  numberorderdispatcher         varchar(255),
  datecreatedispatcher          datetime2,
  typetransport                 varchar(255),
  timeforloading                integer,
  deliverycompanyplan           varchar(255),
  deliverycompanyfact           varchar(255),
  driver                        varchar(255),
  timeloading                   time,
  backmanager                   varchar(255),
  note                          varchar(255),
  createby                      integer,
  updateby                      integer,
  createrequesttransport        datetime2 not null,
  updaterequesttransport        datetime2 not null,
  constraint pk_gtk_dstl_planshipmentrequesttransport primary key (id)
);

create table gtk_dstl_planshipment (
  id                            numeric(19) identity(1,1) not null,
  name                          varchar(255),
  dateplan                      date,
  idservice                     integer,
  createby                      integer,
  updateby                      integer,
  createplan                    datetime2 not null,
  updateplan                    datetime2 not null,
  constraint pk_gtk_dstl_planshipment primary key (id)
);

create table gtk_dstl_planshipmentitem (
  id                            numeric(19) identity(1,1) not null,
  idplan                        numeric(19),
  identerprise                  integer,
  typeshipment                  varchar(255),
  dateshipmentdispatcher        date,
  iddeviationshipment           integer,
  datedeliverydispatcher        date,
  datedeliveryfact              date,
  iddeviationdelivery           integer,
  existinstore                  bit default 0,
  datetostore                   datetime2,
  placeshipment                 varchar(255),
  statusdispatcher              varchar(255),
  numberorderdispatcher         varchar(255),
  datecreatedispatcher          datetime2,
  co_num                        varchar(255),
  co_line                       integer,
  item                          varchar(255),
  co_name                       varchar(255),
  cust_num                      varchar(255),
  cust_seq                      integer,
  cust_name                     varchar(255),
  placedelivery                 varchar(255),
  co_count                      integer,
  sizepallet                    varchar(255),
  kindpackage                   varchar(255),
  countplace                    integer,
  co_capacity                   varchar(255),
  typetransport                 varchar(255),
  timeforloading                integer,
  deliverycompanyplan           varchar(255),
  deliverycompanyfact           varchar(255),
  driver                        varchar(255),
  numbergate                    integer,
  distancedeliver               float(32),
  costdelivery                  float(32),
  timeloading                   time,
  backmanager                   varchar(255),
  note                          varchar(255),
  createby                      integer,
  updateby                      integer,
  createplanitem                datetime2 not null,
  updateplanitem                datetime2 not null,
  constraint pk_gtk_dstl_planshipmentitem primary key (id)
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
  name                          varchar(255),
  idservicedstl                 integer,
  starttime                     time,
  endtime                       time,
  worktime                      bit default 0,
  constraint pk_gtk_dstl_worktime primary key (id)
);

alter table gtk_dstl_normatimeloading add constraint fk_gtk_dstl_normatimeloading_identerprise foreign key (identerprise) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_normatimeloading_identerprise on gtk_dstl_normatimeloading (identerprise);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_idplan foreign key (idplan) references gtk_dstl_planshipment (id);
create index ix_gtk_dstl_planshipmentrequesttransport_idplan on gtk_dstl_planshipmentrequesttransport (idplan);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_identerprise foreign key (identerprise) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_planshipmentrequesttransport_identerprise on gtk_dstl_planshipmentrequesttransport (identerprise);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyp_3 foreign key (deliverycompanyplan) references gtk_dstl_transportcompany (rowpointer);
create index ix_gtk_dstl_planshipmentrequesttransport_deliverycompanyp_3 on gtk_dstl_planshipmentrequesttransport (deliverycompanyplan);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyf_4 foreign key (deliverycompanyfact) references gtk_dstl_transportcompany (rowpointer);
create index ix_gtk_dstl_planshipmentrequesttransport_deliverycompanyf_4 on gtk_dstl_planshipmentrequesttransport (deliverycompanyfact);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_driver foreign key (driver) references gtk_all_car (driverref);
create index ix_gtk_dstl_planshipmentrequesttransport_driver on gtk_dstl_planshipmentrequesttransport (driver);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_createby foreign key (createby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipmentrequesttransport_createby on gtk_dstl_planshipmentrequesttransport (createby);

alter table gtk_dstl_planshipmentrequesttransport add constraint fk_gtk_dstl_planshipmentrequesttransport_updateby foreign key (updateby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipmentrequesttransport_updateby on gtk_dstl_planshipmentrequesttransport (updateby);

alter table gtk_dstl_planshipment add constraint fk_gtk_dstl_planshipment_idservice foreign key (idservice) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_planshipment_idservice on gtk_dstl_planshipment (idservice);

alter table gtk_dstl_planshipment add constraint fk_gtk_dstl_planshipment_createby foreign key (createby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipment_createby on gtk_dstl_planshipment (createby);

alter table gtk_dstl_planshipment add constraint fk_gtk_dstl_planshipment_updateby foreign key (updateby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipment_updateby on gtk_dstl_planshipment (updateby);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_idplan foreign key (idplan) references gtk_dstl_planshipment (id);
create index ix_gtk_dstl_planshipmentitem_idplan on gtk_dstl_planshipmentitem (idplan);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_identerprise foreign key (identerprise) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_planshipmentitem_identerprise on gtk_dstl_planshipmentitem (identerprise);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_iddeviationshipment foreign key (iddeviationshipment) references gtk_dstl_deviation (id);
create index ix_gtk_dstl_planshipmentitem_iddeviationshipment on gtk_dstl_planshipmentitem (iddeviationshipment);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_iddeviationdelivery foreign key (iddeviationdelivery) references gtk_dstl_deviation (id);
create index ix_gtk_dstl_planshipmentitem_iddeviationdelivery on gtk_dstl_planshipmentitem (iddeviationdelivery);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_deliverycompanyplan foreign key (deliverycompanyplan) references gtk_dstl_transportcompany (rowpointer);
create index ix_gtk_dstl_planshipmentitem_deliverycompanyplan on gtk_dstl_planshipmentitem (deliverycompanyplan);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_deliverycompanyfact foreign key (deliverycompanyfact) references gtk_dstl_transportcompany (rowpointer);
create index ix_gtk_dstl_planshipmentitem_deliverycompanyfact on gtk_dstl_planshipmentitem (deliverycompanyfact);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_driver foreign key (driver) references gtk_all_car (driverref);
create index ix_gtk_dstl_planshipmentitem_driver on gtk_dstl_planshipmentitem (driver);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_createby foreign key (createby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipmentitem_createby on gtk_dstl_planshipmentitem (createby);

alter table gtk_dstl_planshipmentitem add constraint fk_gtk_dstl_planshipmentitem_updateby foreign key (updateby) references gtk_dstl_user (id);
create index ix_gtk_dstl_planshipmentitem_updateby on gtk_dstl_planshipmentitem (updateby);

alter table gtk_dstl_user add constraint fk_gtk_dstl_user_idservice foreign key (idservice) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_user_idservice on gtk_dstl_user (idservice);

alter table gtk_dstl_worktime add constraint fk_gtk_dstl_worktime_idservicedstl foreign key (idservicedstl) references gtk_dstl_enterprise (id);
create index ix_gtk_dstl_worktime_idservicedstl on gtk_dstl_worktime (idservicedstl);


# --- !Downs

IF OBJECT_ID('fk_gtk_dstl_normatimeloading_identerprise', 'F') IS NOT NULL alter table gtk_dstl_normatimeloading drop constraint fk_gtk_dstl_normatimeloading_identerprise;
drop index if exists ix_gtk_dstl_normatimeloading_identerprise;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_idplan', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_idplan;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_idplan;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_identerprise', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_identerprise;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_identerprise;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyp_3', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyp_3;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_deliverycompanyp_3;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyf_4', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_deliverycompanyf_4;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_deliverycompanyf_4;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_driver', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_driver;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_driver;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_createby', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_createby;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_createby;

IF OBJECT_ID('fk_gtk_dstl_planshipmentrequesttransport_updateby', 'F') IS NOT NULL alter table gtk_dstl_planshipmentrequesttransport drop constraint fk_gtk_dstl_planshipmentrequesttransport_updateby;
drop index if exists ix_gtk_dstl_planshipmentrequesttransport_updateby;

IF OBJECT_ID('fk_gtk_dstl_planshipment_idservice', 'F') IS NOT NULL alter table gtk_dstl_planshipment drop constraint fk_gtk_dstl_planshipment_idservice;
drop index if exists ix_gtk_dstl_planshipment_idservice;

IF OBJECT_ID('fk_gtk_dstl_planshipment_createby', 'F') IS NOT NULL alter table gtk_dstl_planshipment drop constraint fk_gtk_dstl_planshipment_createby;
drop index if exists ix_gtk_dstl_planshipment_createby;

IF OBJECT_ID('fk_gtk_dstl_planshipment_updateby', 'F') IS NOT NULL alter table gtk_dstl_planshipment drop constraint fk_gtk_dstl_planshipment_updateby;
drop index if exists ix_gtk_dstl_planshipment_updateby;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_idplan', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_idplan;
drop index if exists ix_gtk_dstl_planshipmentitem_idplan;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_identerprise', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_identerprise;
drop index if exists ix_gtk_dstl_planshipmentitem_identerprise;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_iddeviationshipment', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_iddeviationshipment;
drop index if exists ix_gtk_dstl_planshipmentitem_iddeviationshipment;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_iddeviationdelivery', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_iddeviationdelivery;
drop index if exists ix_gtk_dstl_planshipmentitem_iddeviationdelivery;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_deliverycompanyplan', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_deliverycompanyplan;
drop index if exists ix_gtk_dstl_planshipmentitem_deliverycompanyplan;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_deliverycompanyfact', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_deliverycompanyfact;
drop index if exists ix_gtk_dstl_planshipmentitem_deliverycompanyfact;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_driver', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_driver;
drop index if exists ix_gtk_dstl_planshipmentitem_driver;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_createby', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_createby;
drop index if exists ix_gtk_dstl_planshipmentitem_createby;

IF OBJECT_ID('fk_gtk_dstl_planshipmentitem_updateby', 'F') IS NOT NULL alter table gtk_dstl_planshipmentitem drop constraint fk_gtk_dstl_planshipmentitem_updateby;
drop index if exists ix_gtk_dstl_planshipmentitem_updateby;

IF OBJECT_ID('fk_gtk_dstl_user_idservice', 'F') IS NOT NULL alter table gtk_dstl_user drop constraint fk_gtk_dstl_user_idservice;
drop index if exists ix_gtk_dstl_user_idservice;

IF OBJECT_ID('fk_gtk_dstl_worktime_idservicedstl', 'F') IS NOT NULL alter table gtk_dstl_worktime drop constraint fk_gtk_dstl_worktime_idservicedstl;
drop index if exists ix_gtk_dstl_worktime_idservicedstl;

IF OBJECT_ID('gtk_dstl_deviation', 'U') IS NOT NULL drop table gtk_dstl_deviation;

IF OBJECT_ID('gtk_dstl_enterprise', 'U') IS NOT NULL drop table gtk_dstl_enterprise;

IF OBJECT_ID('gtk_dstl_normatimeloading', 'U') IS NOT NULL drop table gtk_dstl_normatimeloading;

IF OBJECT_ID('gtk_dstl_planshipmentrequesttransport', 'U') IS NOT NULL drop table gtk_dstl_planshipmentrequesttransport;

IF OBJECT_ID('gtk_dstl_planshipment', 'U') IS NOT NULL drop table gtk_dstl_planshipment;

IF OBJECT_ID('gtk_dstl_planshipmentitem', 'U') IS NOT NULL drop table gtk_dstl_planshipmentitem;

IF OBJECT_ID('gtk_dstl_user', 'U') IS NOT NULL drop table gtk_dstl_user;

IF OBJECT_ID('gtk_dstl_worktime', 'U') IS NOT NULL drop table gtk_dstl_worktime;

