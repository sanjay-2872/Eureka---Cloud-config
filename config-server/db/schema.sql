create table PROPERTIES (
  "ID" serial primary key, 
  "CREATED_ON" timestamp ,
  APPLICATION text, 
  PROFILE text, 
  LABEL text, 
  "KEY" text, 
  "VALUE" text
 );


INSERT INTO PROPERTIES ("CREATED_ON", APPLICATION, PROFILE, LABEL, "KEY", "VALUE") VALUES (NULL,'client-app','dev','latest','prop1','value1');
INSERT INTO PROPERTIES ("CREATED_ON", APPLICATION, PROFILE, LABEL, "KEY", "VALUE") VALUES (NULL,'client-app','dev','1.0.1','prop2','value2');