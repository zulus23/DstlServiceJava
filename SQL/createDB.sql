/*Предприятия*/


CREATE TABLE GTK_DSTL_Enterprise (
  ID           integer not null PRIMARY KEY IDENTITY ,
  Name         VARCHAR(50),
  SLDB         VARCHAR(50),
  ServiceDstl  bit,

);


CREATE TABLE GTK_DSTL_User (
  ID                            integer not null PRIMARY KEY  IDENTITY ,
  Name                          varchar(30),
  Password                      varchar(30),
  IdService                     integer,

);
ALTER TABLE GTK_DSTL_User ADD CONSTRAINT FK_GTK_DSTL_User_IdService foreign key (IdService)
references GTK_DSTL_Enterprise (ID);
CREATE INDEX IX_GTK_DSTL_User_IdService ON GTK_DSTL_User (IdService);

CREATE TABLE GTK_DSTL_NormaTimeLoading (
  ID                            integer not null PRIMARY KEY IDENTITY ,
  IdEnterprise                  integer,
  PackageTime                   integer,
  CommissionTime                integer,
  PlacerTime                    integer,
);

ALTER TABLE GTK_DSTL_NormaTimeLoading ADD CONSTRAINT  FK_GTK_DSTL_NormaTimeLoading_IdEnterprise FOREIGN KEY (IdEnterprise)
REFERENCES  GTK_DSTL_Enterprise (ID) ;
CREATE INDEX IX_GTK_DSTL_NormaTimeLoading_IdEnterprise ON GTK_DSTL_NormaTimeLoading (IdEnterprise);

CREATE TABLE GTK_DSTL_WorkTime (
  ID                            integer identity(1,1) not null,
  Name                          VARCHAR(70),
  IdServiceDstl                 integer,
  StartTime                     time,
  EndTime                       time,
  WorkTime                      bit default 0,
  constraint PK_GTK_DSTL_WorkTime primary key (id)
);

ALTER TABLE GTK_DSTL_WorkTime ADD CONSTRAINT FK_constraint_IdServiceDstl foreign key (IdServiceDstl) REFERENCES GTK_DSTL_Enterprise (ID);
CREATE INDEX IX_GTK_DSTL_WorkTime_IdServiceDstl on GTK_DSTL_WorkTime (IdServiceDstl);
