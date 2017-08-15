--------------------------------------------------------
--  File created - Tuesday-August-15-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table APARTMENTS2
--------------------------------------------------------

  CREATE TABLE "FORZA_JUVE"."APARTMENTS2" 
   (	"APARTMENT_ID" NUMBER(*,0), 
	"BOOKED_FROM" TIMESTAMP (6), 
	"BOOKED_TO" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into FORZA_JUVE.APARTMENTS2
SET DEFINE OFF;
Insert into FORZA_JUVE.APARTMENTS2 (APARTMENT_ID,BOOKED_FROM,BOOKED_TO) values (99,to_timestamp('31-DEC-99 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('31-DEC-99 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
--------------------------------------------------------
--  DDL for Index APARTMENTS_TEST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FORZA_JUVE"."APARTMENTS_TEST_PK" ON "FORZA_JUVE"."APARTMENTS2" ("APARTMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table APARTMENTS2
--------------------------------------------------------

  ALTER TABLE "FORZA_JUVE"."APARTMENTS2" ADD CONSTRAINT "APARTMENTS_TEST_PK" PRIMARY KEY ("APARTMENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "FORZA_JUVE"."APARTMENTS2" MODIFY ("APARTMENT_ID" NOT NULL ENABLE);
