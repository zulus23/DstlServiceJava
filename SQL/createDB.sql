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
  CanCreatePlan                 bit

);
ALTER TABLE GTK_DSTL_User ADD CONSTRAINT FK_GTK_DSTL_User_IdService foreign key (IdService)
references GTK_DSTL_Enterprise (ID);
CREATE INDEX IX_GTK_DSTL_User_IdService ON GTK_DSTL_User (IdService);

CREATE TABLE GTK_DSTL_UserRole (
  ID                           INTEGER not null,
  IdUser                       INTEGER NOT NULL,
  RoleName                         varchar(30) NOT NULL ,

)
ALTER TABLE GTK_DSTL_UserRole ADD CONSTRAINT PK_GTK_DSTL_User_Id_IdUser PRIMARY KEY(ID,IdUser)
ALTER TABLE GTK_DSTL_UserRole ADD CONSTRAINT FK_GTK_DSTL_User_Id foreign key (IdUser) REFERENCES  GTK_DSTL_User(ID)




CREATE TABLE GTK_DSTL_Deviation (
  ID                            integer not null PRIMARY KEY IDENTITY ,
  Description                   VARCHAR(100),
  TypeDeviation                 VARCHAR(25),
  RefToSL                       UNIQUEIDENTIFIER
)
ALTER  TABLE GTK_DSTL_Deviation ADD CONSTRAINT   UQ_GTK_DSTL_Deviation UNIQUE(RefToSL)
ALTER  TABLE GTK_DSTL_Deviation ADD CONSTRAINT   UQ_GTK_DSTL_DeviationIDType UNIQUE(ID,TypeDeviation)


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

--ALTER TABLE GTK_DSTL_PlanShipmentDetail ADD CONSTRAINT FK_Constraint_IDPLAN foreign key (IDPLAN) REFERENCES GTK_DSTL_PlanShipment (ID);
CREATE TABLE GTK_DSTL_PlanShipment (
   ID                          integer identity(1,1) not null PRIMARY KEY ,
   IdService                INTEGER NOT NULL,
   Name                        VARCHAR(100),
   DatePlan                    DATE NOT NULL,
   CreatePlan                  DATETIME2,
   CreateBy                    INTEGER,
   UpdatePlan                  DATETIME2,
   UpdateBy                    INTEGER

)
ALTER TABLE GTK_DSTL_PlanShipment ADD CONSTRAINT FK_GTK_DSTL_PlanShipment foreign key (IdService)
references GTK_DSTL_Enterprise (ID);
ALTER  TABLE GTK_DSTL_PlanShipment ADD CONSTRAINT   UQ_GTK_DSTL_PlanShipment UNIQUE(IdService,DatePlan)

--CREATE INDEX IX_GTK_DSTL_PlanShipment_IdService ON GTK_DSTL_PlanShipment (IdService);
CREATE INDEX IX_GTK_DSTL_PlanShipment_IdServiceDate ON GTK_DSTL_PlanShipment (IdService,DatePlan);

CREATE TABLE GTK_DSTL_PlanShipmentRequestTransport (
  ID                          integer identity(1,1) not null PRIMARY KEY ,
  IdPlan                      INTEGER NOT NULL,
  IdEnterprise                INTEGER NOT NULL,
  PlaceShipment               VARCHAR(50),
  TypeShipment                VARCHAR(50),
  InPlanLoad                  BIT DEFAULT  0,
  KindPackage                  VARCHAR(50),
  DateShipmentDispatcher      DATE,
  IdDeviationShipment         INTEGER ,
  DateDeliveryDispatcher      DATE,
  DateDeliveryFact            DATE,
  IdDeviationDelivery         INTEGER ,
  StatusDispatcher             VARCHAR(20),
  NumberOrderDispatcher        VARCHAR(20),
  DateCreateDispatcher         DATETIME2,
  TypeTransport                VARCHAR(50),
  TimeForLoading               INTEGER,
  DeliveryCompanyPlan          VARCHAR(50),
  DeliveryCompanyFact          VARCHAR(50),
  Driver                       VARCHAR(50),
  TimeLoading                  TIME,
  BackManager                  VARCHAR(50),
  Note                         VARCHAR(255),
  CreateRequest               DATETIME2,
  CreateBy                     INTEGER,
  UpdateRequest               DATETIME2,
  UpdateBy                     INTEGER
)
ALTER TABLE GTK_DSTL_PlanShipmentRequestTransport ADD CONSTRAINT FK_GTK_DSTL_PlanShipmentRequestTransportEnterprise foreign key (IdEnterprise)
references GTK_DSTL_Enterprise (ID);
ALTER TABLE GTK_DSTL_PlanShipmentRequestTransport ADD CONSTRAINT FK_GTK_DSTL_PlanShipmentRequestTransportPLan foreign key (IdPlan)
references GTK_DSTL_PlanShipment (ID);

ALTER TABLE GTK_DSTL_PlanShipmentRequestTransport ADD CONSTRAINT FK_GTK_DSTL_PlanShipmentRequestTransportDeviationShipment foreign key (IdDeviationShipment)
references GTK_DSTL_Deviation (ID);
ALTER TABLE GTK_DSTL_PlanShipmentRequestTransport ADD CONSTRAINT FK_GTK_DSTL_PlanShipmentRequestTransportDeviationDelivery foreign key (IdDeviationDelivery)
references GTK_DSTL_Deviation (ID);
ALTER TABLE GTK_DSTL_PlanShipmentRequestTransport ADD CONSTRAINT UQ_GTK_DSTL_PlanShipmentRequestTransportByPlan UNIQUE (idPlan,idEnterprise,NumberOrderDispatcher)



CREATE TABLE  GTK_DSTL_PlanShipmentItem(
  ID                          integer identity(1,1) not null PRIMARY KEY ,
  IdRequestTransport          INTEGER NOT NULL,
  ExistInStore                BIT, -- На складе
  DateToStore                  DATETIME2,
  Co_Num                       VARCHAR(25),
  Co_Line                      VARCHAR(25),
  Item                         VARCHAR(25),
  Co_Name                       VARCHAR(255),
  Cust_Num                     VARCHAR(25),
  Cust_Name                     VARCHAR(255),
  Cust_seq                     INTEGER,
  PlaceDelivery                VARCHAR(255),
  Co_Count                     INTEGER,
  SizePallet                   VARCHAR(50),
  KindPackage                  VARCHAR(50),
  CountPlace                   INTEGER,
  TimeForLoading               INTEGER,
  DistanceDeliver              INTEGER,
  CostDelivery                 DECIMAL(15,3),
  CreatePlanItem               DATETIME2,
  CreateBy                     INTEGER,
  UpdatePlanItem               DATETIME2,
  UpdateBy                     INTEGER
)
ALTER TABLE GTK_DSTL_PlanShipmentItem ADD CONSTRAINT FK_GTK_DSTL_PlanShipmentItem foreign key (IdRequestTransport)
references GTK_DSTL_PlanShipmentRequestTransport (ID);




CREATE TABLE  GTK_DSTL_PlanLoad(
  ID INTEGER IDENTITY(1,1) PRIMARY KEY,
  IdService                INTEGER NOT NULL,
  Name VARCHAR(100),
  Gate INTEGER NOT NULL
);

CREATE TABLE  GTK_DSTL_PlanLoadItem(
  ID INTEGER IDENTITY(1,1) PRIMARY KEY,
  IdPlanLoad INTEGER NOT NULL,
  IdPlanShipmentItem INTEGER,
  Name VARCHAR(100),
  ArrivalTruck DATETIME2,
  BeginLoad DATETIME2,
  EndLoad DATETIME2,
  DepartureTruck DATETIME2,
  FactLoad INTEGER,

  DateDeliveryFact            DATE,


);





GRANT SELECT ON dbo.GTK_DSTL_User TO report
GRANT SELECT ON dbo.GTK_DSTL_UserRole TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_Enterprise TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_WorkTime TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_NormaTimeLoading TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_PlanShipment TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_PlanShipmentRequestTransport TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_PlanShipmentItem TO report
GRANT SELECT, INSERT,UPDATE,DELETE ON dbo.GTK_DSTL_Deviation TO report




CREATE VIEW GTK_DSTL_TransportCompany
  AS
  SELECT DISTINCT 'ПРИНТ' AS EnterpriseName,v.vend_num ,v.name,SL_Print.dbo.GTKFormatAddress(v.vend_num,0,'vendaddr') AS address,
    vd.contact,vd.phone,v.RowPointer
  FROM SL_Print.dbo.GTK_CAR_Sprav s
    JOIN SL_Print.dbo.vendaddr v ON v.vend_num = s.Vend_Num
    JOIN SL_Print.dbo.vendor vd ON vd.vend_num = v.vend_num
GRANT SELECT ON dbo.GTK_DSTL_TrasportCompany TO report

GRANT SELECT ON GTK_DSTL_TransportCompany  TO report




CREATE VIEW GTK_ALL_CAR
AS
  SELECT 'ПРИНТ' AS EnterpriseName,v.vend_num ,v.name,SL_Print.dbo.GTKFormatAddress(v.vend_num,0,'vendaddr') AS address,
    vd.contact,vd.phone,s.Driver,s.Phone AS driverPhone,s.CAR_Num AS NumberTruck,
    v.RowPointer AS TransportCompanyRef,s.RowPointer AS DriverRef
  FROM SL_Print.dbo.GTK_CAR_Sprav s
    JOIN SL_Print.dbo.vendaddr v ON v.vend_num = s.Vend_Num
    JOIN SL_Print.dbo.vendor vd ON vd.vend_num = v.vend_num





--                    -javaagent:C:\Users\Zhukov\.ivy2\cache\org.avaje.ebeanorm\avaje-ebeanorm-agent\jars\avaje-ebeanorm-agent-4.9.2.jar

