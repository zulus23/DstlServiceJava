/*Предприятия*/


CREATE TABLE GTK_DSTL_Enterprise (
  ID           integer not null PRIMARY KEY IDENTITY ,
  Name         VARCHAR(50),
  SLDB         VARCHAR(50),
  ServiceDstl  bit,
  BelongToService INTEGER DEFAULT  -1
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


CREATE TABLE  GTK_DSTL_PlanLoadDay(
  ID                            integer identity(1,1) not null,
  DatePLan                      DATE,
  IdEnterprise                  INTEGER NOT NULL,

  DateDeliveryDispatcher      DATE,
  DateDeliveryFact            DATE,
  DeviationDelivery            VARCHAR(50),
  OnStore                       BIT, -- На складе
  EnterToStore                  DATETIME,
  PlaceShipment                 VARCHAR(10),
  StatusDispatcher             VARCHAR(20),
  NumberOrderDispatcher        VARCHAR(20),
  DateCreateDispatcher         DATETIME,
  Co_num                       VARCHAR(25),
  Co_line                      VARCHAR(25),
  Item                         VARCHAR(25),
  Co_Name                      VARCHAR(255),
  Грузополучатель              VARCHAR(25),
  PlaceDelivery                VARCHAR(25),
  Co_Capacity                  INTEGER,
  SizePallet                   VARCHAR(50),
  KindPackage                  VARCHAR(50),
  CountPlace                   INTEGER,

  TypeTransport                VARCHAR(50),
  TimeForLoading               INTEGER,
  DeliveryCompanyPlan          VARCHAR(50),
  DeliveryCompanyFact          VARCHAR(50),
  CostDelivery                 DECIMAL(15,3),
  TimeLoading                  TIME,
  BackManager                  VARCHAR(50),
  Note                         VARCHAR(255)
)
CREATE TABLE GTK_DSTL_PlanLoadDayDetail (
   ID                          integer identity(1,1) not null,
   IDPLAN                      INTEGER NOT NULL
)
ALTER TABLE GTK_DSTL_PlanLoadDayDetail ADD CONSTRAINT FK_Constraint_IDPLAN foreign key (IDPLAN) REFERENCES GTK_DSTL_PlanLoadDay (ID);




GRANT SELECT ON dbo.GTK_DSTL_User TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_Enterprise TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_WorkTime TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_NormaTimeLoading TO report






CREATE VIEW GTK_ALL_CAR
AS
  SELECT 'ПРИНТ' AS EnterpriseName,v.vend_num ,v.name,SL_Print.dbo.GTKFormatAddress(v.vend_num,0,'vendaddr') AS address,
    vd.contact,vd.phone,s.Driver,s.Phone AS driverPhone
  FROM SL_Print.dbo.GTK_CAR_Sprav s
    JOIN SL_Print.dbo.vendaddr v ON v.vend_num = s.Vend_Num
    JOIN SL_Print.dbo.vendor vd ON vd.vend_num = v.vend_num


--                    -javaagent:C:\Users\Zhukov\.ivy2\cache\org.avaje.ebeanorm\avaje-ebeanorm-agent\jars\avaje-ebeanorm-agent-4.9.2.jar

