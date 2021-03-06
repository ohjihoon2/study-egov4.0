CREATE TABLE IDS  (
  TABLE_NAME varchar(60) NOT NULL,
  NEXT_ID numeric(30,0)DEFAULT 0 NOT NULL ,
  CONSTRAINT IDS_PK PRIMARY KEY (TABLE_NAME)
);

CREATE TABLE comtCCMMNCLCODE (
  CL_CODE char(3) NOT NULL,
  CL_CODE_NM varchar(180) ,
  CL_CODE_DC varchar(600) ,
  USE_AT char(1) ,
  FRST_REGIST_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtCCMMNCLCODE_PK PRIMARY KEY (CL_CODE)
) ;

CREATE TABLE comtCCMMNCODE  (
  CODE_ID varchar(18) NOT NULL,
  CODE_ID_NM varchar(180) ,
  CODE_ID_DC varchar(600) ,
  USE_AT char(1) ,
  CL_CODE char(3) ,
  FRST_REGIST_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtCCMMNCODE_PK PRIMARY KEY (CODE_ID),
  CONSTRAINT comtCCMMNCODE_ibfk_1 FOREIGN KEY (CL_CODE) REFERENCES comtCCMMNCLCODE (CL_CODE)
) ;

CREATE TABLE comtCCMMNDETAILCODE  (
  CODE_ID varchar(18) NOT NULL,
  CODE varchar(45) NOT NULL,
  CODE_NM varchar(180) ,
  CODE_DC varchar(600) ,
  USE_AT char(1) ,
  FRST_REGIST_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtCCMMNDETAILCODE_PK PRIMARY KEY (CODE_ID,CODE),
  CONSTRAINT comtCCMMNDETAILCODE_ibfk_1 FOREIGN KEY (CODE_ID) REFERENCES comtCCMMNCODE (CODE_ID)
) ;

CREATE TABLE comtNORGNZTINFO  (
  ORGNZT_ID char(20) DEFAULT '' NOT NULL,
  ORGNZT_NM varchar(60) NOT NULL,
  ORGNZT_DC varchar(300) ,
  CONSTRAINT comtNORGNZTINFO_PK PRIMARY KEY (ORGNZT_ID)
) ;

CREATE TABLE comtNAUTHORGROUPINFO  (
  GROUP_ID char(20) DEFAULT '' NOT NULL,
  GROUP_NM varchar(180) NOT NULL,
  GROUP_CREAT_DE char(60) NOT NULL,
  GROUP_DC varchar(300) ,
  CONSTRAINT comtNAUTHORGROUPINFO_PK PRIMARY KEY (GROUP_ID)
) ;

CREATE TABLE comtNEMPLYRINFO  (
  EMPLYR_ID varchar(60) NOT NULL,
  ORGNZT_ID char(20) ,
  USER_NM varchar(180) NOT NULL,
  PASSWORD varchar(600) NOT NULL,
  EMPL_NO varchar(60) ,
  IHIDNUM varchar(39) ,
  SEXDSTN_CODE char(1) ,
  BRTHDY char(20) ,
  FXNUM varchar(60) ,
  HOUSE_ADRES varchar(300) ,
  PASSWORD_HINT varchar(300) NOT NULL,
  PASSWORD_CNSR varchar(300) NOT NULL,
  HOUSE_END_TELNO varchar(12) ,
  AREA_NO varchar(12) ,
  DETAIL_ADRES varchar(300) ,
  ZIP varchar(18) ,
  OFFM_TELNO varchar(60) ,
  MBTLNUM varchar(60) ,
  EMAIL_ADRES varchar(150) ,
  OFCPS_NM varchar(180) ,
  HOUSE_MIDDLE_TELNO varchar(12) ,
  GROUP_ID char(20) ,
  PSTINST_CODE char(8) ,
  EMPLYR_STTUS_CODE varchar(45) NOT NULL,
  ESNTL_ID char(20) NOT NULL,
  CRTFC_DN_VALUE varchar(60) ,
  SBSCRB_DE DATETIME ,
  CONSTRAINT comtNEMPLYRINFO_PK PRIMARY KEY (EMPLYR_ID),
  CONSTRAINT comtNEMPLYRINFO_ibfk_2 FOREIGN KEY (GROUP_ID) REFERENCES comtNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE,
  CONSTRAINT comtNEMPLYRINFO_ibfk_1 FOREIGN KEY (ORGNZT_ID) REFERENCES comtNORGNZTINFO (ORGNZT_ID) ON DELETE CASCADE
) ;

CREATE TABLE comtNTMPLATINFO  (
  TMPLAT_ID char(20) DEFAULT '' NOT NULL,
  TMPLAT_NM varchar(765) ,
  TMPLAT_COURS varchar(6000) ,
  USE_AT char(1) ,
  TMPLAT_SE_CODE char(6) ,
  FRST_REGISTER_ID varchar(60) ,
  FRST_REGIST_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  CONSTRAINT comtNTMPLATINFO_PK PRIMARY KEY (TMPLAT_ID)
) ;

CREATE TABLE comtNBBSMASTER  (
  BBS_ID char(20) NOT NULL,
  BBS_NM varchar(765) NOT NULL,
  BBS_INTRCN varchar(7200) ,
  BBS_TY_CODE char(6) NOT NULL,
  BBS_ATTRB_CODE char(6) NOT NULL,
  REPLY_POSBL_AT char(1) ,
  FILE_ATCH_POSBL_AT char(1) NOT NULL,
  ATCH_POSBL_FILE_NUMBER numeric(2,0) NOT NULL,
  ATCH_POSBL_FILE_SIZE numeric(8,0) ,
  USE_AT char(1) NOT NULL,
  TMPLAT_ID char(20) ,
  FRST_REGISTER_ID varchar(60) NOT NULL,
  FRST_REGIST_PNTTM DATETIME NOT NULL,
  LAST_UPDUSR_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  CONSTRAINT comtNBBSMASTER_PK PRIMARY KEY (BBS_ID)
) ;

CREATE TABLE comtNBBSUSE  (
  BBS_ID char(20) NOT NULL,
  TRGET_ID char(20) DEFAULT '' NOT NULL,
  USE_AT char(1) NOT NULL,
  REGIST_SE_CODE char(6) ,
  FRST_REGIST_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) NOT NULL,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtNBBSUSE_PK PRIMARY KEY (BBS_ID,TRGET_ID),
  CONSTRAINT comtNBBSUSE_ibfk_1 FOREIGN KEY (BBS_ID) REFERENCES comtNBBSMASTER (BBS_ID)
) ;

CREATE TABLE comtNBBS (
  NTT_ID numeric(20,0) NOT NULL,
  BBS_ID char(20) NOT NULL,
  NTT_NO numeric(20,0) ,
  NTT_SJ varchar(6000) ,
  NTT_CN STRING,
  ANSWER_AT char(1) ,
  PARNTSCTT_NO numeric(10,0) ,
  ANSWER_LC numeric(11) ,
  SORT_ORDR numeric(8,0) ,
  RDCNT numeric(10,0) ,
  USE_AT char(1) NOT NULL,
  NTCE_BGNDE char(20) ,
  NTCE_ENDDE char(20) ,
  NTCR_ID varchar(60) ,
  NTCR_NM varchar(60) ,
  PASSWORD varchar(600) ,
  ATCH_FILE_ID char(20) ,
  FRST_REGIST_PNTTM DATETIME NOT NULL,
  FRST_REGISTER_ID varchar(60) NOT NULL,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtNBBS_PK PRIMARY KEY (NTT_ID,BBS_ID),
  CONSTRAINT comtNBBS_ibfk_1 FOREIGN KEY (BBS_ID) REFERENCES comtNBBSMASTER (BBS_ID)
) ;

CREATE TABLE comtNBBSMASTEROPTN (
  BBS_ID char(20) DEFAULT '' NOT NULL,
  ANSWER_AT char(1) DEFAULT '' NOT NULL,
  STSFDG_AT char(1) DEFAULT '' NOT NULL,
  FRST_REGIST_PNTTM DATETIME DEFAULT SYSDATE NOT NULL,
  LAST_UPDT_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) DEFAULT '' NOT NULL,
  LAST_UPDUSR_ID varchar(60) ,
  CONSTRAINT comtNBBSMASTEROPTN_PK PRIMARY KEY (BBS_ID)
) ;

CREATE TABLE comtNENTRPRSMBER (
  ENTRPRS_MBER_ID varchar(60) DEFAULT '' NOT NULL,
  ENTRPRS_SE_CODE char(15) ,
  BIZRNO varchar(30) ,
  JURIRNO varchar(39) ,
  CMPNY_NM varchar(180) NOT NULL,
  CXFC varchar(150) ,
  ZIP varchar(18) ,
  ADRES varchar(300) ,
  ENTRPRS_MIDDLE_TELNO varchar(12) ,
  FXNUM varchar(60) ,
  INDUTY_CODE char(15) ,
  APPLCNT_NM varchar(150) NOT NULL,
  APPLCNT_IHIDNUM varchar(39) ,
  SBSCRB_DE DATETIME ,
  ENTRPRS_MBER_STTUS varchar(45) ,
  ENTRPRS_MBER_PASSWORD varchar(600) NOT NULL,
  ENTRPRS_MBER_PASSWORD_HINT varchar(300) NOT NULL,
  ENTRPRS_MBER_PASSWORD_CNSR varchar(300) NOT NULL,
  GROUP_ID char(20) ,
  DETAIL_ADRES varchar(300) ,
  ENTRPRS_END_TELNO varchar(12) ,
  AREA_NO varchar(12) ,
  APPLCNT_EMAIL_ADRES varchar(150) ,
  ESNTL_ID char(20) NOT NULL,
  CONSTRAINT comtNENTRPRSMBER_PK PRIMARY KEY (ENTRPRS_MBER_ID),
  CONSTRAINT comtNENTRPRSMBER_ibfk_1 FOREIGN KEY (GROUP_ID) REFERENCES comtNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE
) ;

CREATE TABLE comtNFILE (
  ATCH_FILE_ID char(20) NOT NULL,
  CREAT_DT DATETIME NOT NULL,
  USE_AT char(1) ,
  CONSTRAINT comtNFILE_PK PRIMARY KEY (ATCH_FILE_ID)
) ;

CREATE TABLE comtNFILEDETAIL (
  ATCH_FILE_ID char(20) NOT NULL,
  FILE_SN numeric(10,0) NOT NULL,
  FILE_STRE_COURS varchar(6000) NOT NULL,
  STRE_FILE_NM varchar(765) NOT NULL,
  ORIGNL_FILE_NM varchar(765) ,
  FILE_EXTSN varchar(60) NOT NULL,
  FILE_CN STRING,
  FILE_SIZE numeric(8,0) ,
  CONSTRAINT comtNFILEDETAIL_PK PRIMARY KEY (ATCH_FILE_ID,FILE_SN),
  CONSTRAINT comtNFILEDETAIL_ibfk_1 FOREIGN KEY (ATCH_FILE_ID) REFERENCES comtNFILE (ATCH_FILE_ID)
) ;

CREATE TABLE comtNGNRLMBER (
  MBER_ID varchar(60) DEFAULT '' NOT NULL,
  PASSWORD varchar(600) NOT NULL,
  PASSWORD_HINT varchar(300) NOT NULL,
  PASSWORD_CNSR varchar(300) NOT NULL,
  IHIDNUM varchar(39) ,
  MBER_NM varchar(150) NOT NULL,
  ZIP varchar(18) ,
  ADRES varchar(300) ,
  AREA_NO varchar(12) ,
  MBER_STTUS varchar(45) ,
  DETAIL_ADRES varchar(300) ,
  END_TELNO varchar(12) ,
  MBTLNUM varchar(60) ,
  GROUP_ID char(20) ,
  MBER_FXNUM varchar(60) ,
  MBER_EMAIL_ADRES varchar(150) ,
  MIDDLE_TELNO varchar(12) ,
  SBSCRB_DE DATETIME ,
  SEXDSTN_CODE char(1) ,
  ESNTL_ID char(20) NOT NULL,
  CONSTRAINT comtNGNRLMBER_PK PRIMARY KEY (MBER_ID),
  CONSTRAINT comtNGNRLMBER_ibfk_1 FOREIGN KEY (GROUP_ID) REFERENCES comtNAUTHORGROUPINFO (GROUP_ID) ON DELETE CASCADE
) ;

CREATE TABLE comtNSCHDULINFO (
  SCHDUL_ID char(20) NOT NULL,
  SCHDUL_SE char(1) ,
  SCHDUL_DEPT_ID varchar(60) ,
  SCHDUL_KND_CODE varchar(60) ,
  SCHDUL_BEGINDE char(40) ,
  SCHDUL_ENDDE char(40) ,
  SCHDUL_NM varchar(765) ,
  SCHDUL_CN varchar(7500) ,
  SCHDUL_PLACE varchar(765) ,
  SCHDUL_IPCR_CODE char(1) ,
  SCHDUL_CHARGER_ID varchar(60) ,
  ATCH_FILE_ID char(20) ,
  FRST_REGIST_PNTTM DATETIME ,
  FRST_REGISTER_ID varchar(60) ,
  LAST_UPDT_PNTTM DATETIME ,
  LAST_UPDUSR_ID varchar(60) ,
  REPTIT_SE_CODE char(3) ,
  CONSTRAINT comtNSCHDULINFO_PK PRIMARY KEY (SCHDUL_ID)
) ;
CREATE OR REPLACE VIEW COMVNUSERMASTER ( ESNTL_ID,USER_ID,PASSWORD,USER_NM,USER_ZIP,USER_ADRES,USER_EMAIL,GROUP_ID, USER_SE, ORGNZT_ID ) 
AS  
        SELECT ESNTL_ID, MBER_ID,PASSWORD,MBER_NM,ZIP,ADRES,MBER_EMAIL_ADRES,' ','GNR' AS USER_SE, ' ' ORGNZT_ID
        FROM comtNGNRLMBER
    UNION ALL
        SELECT ESNTL_ID,EMPLYR_ID,PASSWORD,USER_NM,ZIP,HOUSE_ADRES,EMAIL_ADRES,GROUP_ID ,'USR' AS USER_SE, ORGNZT_ID
        FROM comtNEMPLYRINFO
    UNION ALL
        SELECT ESNTL_ID,ENTRPRS_MBER_ID,ENTRPRS_MBER_PASSWORD,CMPNY_NM,ZIP,ADRES,APPLCNT_EMAIL_ADRES,' ' ,'ENT' AS USER_SE, ' ' ORGNZT_ID
        FROM comtNENTRPRSMBER
;