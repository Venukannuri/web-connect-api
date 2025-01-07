-- DROP TABLE CATEGORY_STATUS;
CREATE TABLE CATEGORY_STATUS (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(128) NULL,
	CODE VARCHAR(36) NULL,
	CONSTRAINT CATEGORY_STATUS_PKEY PRIMARY KEY (ID)
);

--DROP TABLE CATEGORY;
CREATE TABLE CATEGORY (
	ID VARCHAR(36) NOT NULL,
	CATEGORY_STATUS_ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	CONSTRAINT CATEGORY_PKEY PRIMARY KEY (ID),
	CONSTRAINT CATEGORY_CATEGORY_STATUS_LNK_FX FOREIGN KEY (CATEGORY_STATUS_ID) REFERENCES CATEGORY_STATUS(ID)
);


--DROP TABLE VENDOR;
CREATE TABLE VENDOR (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(128) NOT NULL,
	ADDRESS VARCHAR(128) NULL,
    latitude numeric(9,6) NULL,
	longitude numeric(9,6) NULL,
	EMAIL VARCHAR(50) NULL,
	PHONE_NUMBER TEXT NULL,
	WEBSITE TEXT NULL,
	DESCRIPTION VARCHAR(50) NULL,
	LOGO VARCHAR(256) NULL,
	STATUS VARCHAR(36),
	START_DATE TIMESTAMPTZ NULL DEFAULT CURRENT_TIMESTAMP,
	CATEGORY_ID VARCHAR(36) NOT NULL,
	CONSTRAINT VENDOR_PK PRIMARY KEY (ID),
	CONSTRAINT CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);



--DROP TABLE DEAL;
CREATE TABLE DEAL (
	ID VARCHAR(36) NOT NULL,
	TITLE VARCHAR(128) NOT NULL,
	DESCRIPTION VARCHAR(60) NULL,
	COUPON_CODE VARCHAR(50) NULL,
	EXPIRY_DATE TIMESTAMPTZ NULL,
	VENDOR_ID VARCHAR(36) NOT NULL,
	CONSTRAINT DEAL_PK PRIMARY KEY (ID),
	CONSTRAINT VENDOR_ID_FK FOREIGN KEY (VENDOR_ID) REFERENCES VENDOR(ID)
);

-- Drop table

-- DROP TABLE public.vendor_history;

CREATE TABLE vendor_history (
	id varchar(36) NOT NULL,
	email varchar(50) NULL,
	search_date timestamptz NULL,
	vendor_id varchar(36) NOT NULL,
	CONSTRAINT vendor_history_pk PRIMARY KEY (id),
	CONSTRAINT vendor_id_fk FOREIGN KEY (vendor_id) REFERENCES vendor(id)
);



INSERT INTO CATEGORY_STATUS
(id, "name", code)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Active', 'ACTIVE');
INSERT INTO CATEGORY_STATUS
(id, "name", code)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Inactive', 'INACTIVE');


INSERT INTO category
(id, category_status_id, "name")
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), (select id from CATEGORY_STATUS where code = 'ACTIVE'), 'Fast Food Restaurants');


INSERT INTO vendor
(id, "name", address, latitude, longitude, email, phone_number, website, logo, status, start_date,CATEGORY_ID)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Tim Hortons', '665 8 St SW Suite 120', '-114.081550', '51.047260', 'demo@demo.ca', '(403) 767-9957', 'https://www.timhortons.ca/', 'https://calgaryparkingauthority.s3.ca-central-1.amazonaws.com/canmore/uat/enforcement/logo.png', 'Active', current_timestamp,(select id from category where "name" = 'Fast Food Restaurants'));

INSERT INTO vendor
(id, "name", address, latitude, longitude, email, phone_number, website, logo, status, start_date,CATEGORY_ID)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Starbucks', '2401 Utah Avenue South, Seattle, Washington, U.S.', '-114.381550', '51.347260', 'demo@demo.ca', '(403) 767-9957', 'http://starbucks.com/', 'https://pbs.twimg.com/profile_images/1109148609218412545/XDVmdQm9_400x400.png', 'Active',  current_timestamp,(select id from category where "name" = 'Fast Food Restaurants'));

INSERT INTO vendor
(id, "name", address, latitude, longitude, email, phone_number, website, logo, status, start_date,CATEGORY_ID)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'CHARCUT', 'Downtown Calgary 101, 899 Centre Street SW Calgary AB Canada', '-114.381550', '51.347260', 'info@charcut.com', '403.984.2180', 'http://www.charcut.com/', 'https://images.squarespace-cdn.com/content/520e55c1e4b08bbe8921ad64/1380811015495-2VEEO0OFZ0ZQBI6BGY85/CHARCUT-Logo.jpg?content-type=image/jpeg', 'Active', current_timestamp,(select id from category where "name" = 'Fast Food Restaurants'));

INSERT INTO vendor
(id, "name", address, latitude, longitude, email, phone_number, website, logo, status, start_date,CATEGORY_ID)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'McDonald''s', '222 8 Ave SW, Calgary, AB', '-114.381550', '51.347260', 'franchise.inquiries@us.mcd.com', '1-888-424-4622', 'https://www.mcdonalds.com/', 'https://www.mcdonalds.com/content/dam/ca/nfl/web/logo/McD-squareLogo.png', 'Active', current_timestamp,(select id from category where "name" = 'Fast Food Restaurants'));

INSERT INTO vendor
(id, "name", address, latitude, longitude, email, phone_number, website, logo, status, start_date,CATEGORY_ID)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Costco', '1075 ST ALBERT TRAIL ST. ALBERT, AB', '-114.381550', '51.347260', 'franchise.inquiries@us.mcd.com', '(780) 544-2000', 'https://www.costco.ca/', 'https://www.costco.ca/wcsstore/CostcoGLOBALSAS/images/Costco_Canada_Logo.png', 'Active', current_timestamp,(select id from category where "name" = 'Fast Food Restaurants'));



INSERT INTO deal
(id, title, description, coupon_code, expiry_date, vendor_id)
VALUES((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'Timbits', '$1 For A Box Of 10 Timbits', 'EXTRA50', null, (select id from vendor where name = 'Tim Hortons'));






