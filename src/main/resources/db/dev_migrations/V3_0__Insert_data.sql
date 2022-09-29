INSERT INTO dts."user" (USERNAME,CREATED_BY,CREATED_AT,UPDATED_BY,UPDATED_AT,FIRST_NAME,LAST_NAME,EMAIL,IS_INTERNAL) VALUES
	 ('a.velchev@ds-bg.com','a.velchev@ds-bg.com','2022-07-14 19:24:34.626749+03','a.velchev@ds-bg.com','2022-07-14 19:24:34.626749+03','Alexander','Velchev','a.velchev@ds-bg.com',true),
	 ('testUser','testUser','2022-07-14 19:31:15.592886+03','testUser','2022-07-14 19:31:15.592886+03','test','user',NULL,true);

INSERT INTO dts."user_ldap_groups" (USER_ID,LDAP_GROUP_ID) VALUES
	 (1,4),
	 (1,3),
	 (1,1),
	 (2,4),
	 (2,0);

INSERT INTO dts."future_device" (SERIAL_NUMBER,PRODUCT_ID,CUSTOMER_ID,CREATED_BY,CREATED_AT,UPDATED_BY,UPDATED_AT) VALUES
	 ('BRMSN01','8719030',1,NULL,NULL,NULL,NULL),
	 ('BRMSN01','8719030',4,NULL,NULL,NULL,NULL);
