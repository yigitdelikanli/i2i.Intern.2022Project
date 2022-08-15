CREATE TABLE SUBSCRIBER(
    SUBSC_ID NUMBER,
    MSISDN NUMBER(11) NOT NULL,
    NAME NVARCHAR2(100) NOT NULL,
    SURNAME NVARCHAR2(100) NOT NULL,
    EMAIL NVARCHAR2(100) NOT NULL,
    PASSWORD NVARCHAR2(20) NOT NULL,
    SDATE DATE DEFAULT SYSDATE,
    STATUS NVARCHAR2(2) DEFAULT 'A'
);

ALTER TABLE SUBSCRIBER ADD(
    CONSTRAINT subsc_pk_id PRIMARY KEY (SUBSC_ID)
);

CREATE SEQUENCE subsc_id_sequence start with 1 increment by 1;

INSERT INTO SUBSCRIBER (SUBSC_ID, MSISDN, NAME, SURNAME, EMAIL, PASSWORD, SDATE, STATUS) VALUES (subsc_id_sequence.nextval ,5398849442,'ÜMÝT','SAMUR','umit.samur97@gmail.com','12345',sysdate,  'A');
INSERT INTO SUBSCRIBER (SUBSC_ID, MSISDN, NAME, SURNAME, EMAIL, PASSWORD, SDATE, STATUS) VALUES (subsc_id_sequence.nextval ,5558847851,'SAÝD','SARITEMUr','saidsaritemur@gmail.com','12345',sysdate,  'A');
INSERT INTO SUBSCRIBER (SUBSC_ID, MSISDN, NAME, SURNAME, EMAIL, PASSWORD, SDATE, STATUS) VALUES (subsc_id_sequence.nextval ,5558847851,'yiðit','yiðit','yiðit@gmail.com','12345',sysdate,DEFAULT);

CREATE TABLE PACKAGE(
    PACKAGE_ID NUMBER,
    PACKAGE_NAME NVARCHAR2(200),
    AMOUNT_VOICE NUMBER,
    AMOUNT_DATA NUMBER,
    AMOUNT_SMS NUMBER,
    DURATION NUMBER
);

ALTER TABLE PACKAGE ADD(
    CONSTRAINT package_id_pk PRIMARY KEY (PACKAGE_ID)
);

CREATE SEQUENCE package_id_sequence start with 1 increment by 1;
INSERT INTO PACKAGE (PACKAGE_ID, PACKAGE_NAME, AMOUNT_VOICE, AMOUNT_DATA, AMOUNT_SMS, DURATION) VALUES (package_id_sequence.nextval ,'EYECELL 5GB','500','5','1000','30');

CREATE TABLE BALANCE(
    SUBSC_ID NUMBER,
    PACKAGE_ID NUMBER,
    BAL_LVL_VOICE NUMBER DEFAULT 0,
    BAL_LVL_SMS NUMBER DEFAULT 0,
    BAL_LVL_DATA NUMBER DEFAULT 0,
    SDATE DATE DEFAULT SYSDATE,
    EDATE DATE DEFAULT SYSDATE
);

ALTER TABLE BALANCE ADD(
    CONSTRAINT subsc_id_fk FOREIGN KEY (SUBSC_ID) REFERENCES SUBSCRIBER(SUBSC_ID),
    CONSTRAINT package_id_fk FOREIGN KEY (PACKAGE_ID) REFERENCES PACKAGE(PACKAGE_ID)
);

INSERT INTO BALANCE VALUES(1,1,DEFAULT,DEFAULT,DEFAULT,SYSDATE,SYSDATE);

SHOW USER
CREATE USER eyecell identified by 12345;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO eyecell;



create or replace PACKAGE package_subscriber IS
    FUNCTION login (U_MSISDN IN NUMBER, U_PASSWORD IN VARCHAR2) RETURN NUMBER;

    FUNCTION get_remaining_voice (p_msisdn subscriber.msisdn%type) RETURN NUMBER;
    FUNCTION get_remaining_data (p_msisdn subscriber.msisdn%type) RETURN NUMBER;
    FUNCTION get_remaining_sms (p_msisdn subscriber.msisdn%type) RETURN NUMBER;

    FUNCTION forgetPassword(P_MSISDN IN SUBSCRIBER.MSISDN%TYPE) RETURN NVARCHAR2;
    PROCEDURE create_subscriber (S_MSISDN IN SUBSCRIBER.MSISDN%TYPE, S_NAME IN SUBSCRIBER.NAME%TYPE, S_SURNAME IN SUBSCRIBER.SURNAME%TYPE, 
                                S_EMAIL IN SUBSCRIBER.EMAIL%TYPE, S_PASSWORD IN SUBSCRIBER.PASSWORD%TYPE, P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE);

END package_subscriber;
    
create or replace PACKAGE BODY package_subscriber IS
    FUNCTION login (U_MSISDN IN NUMBER, U_PASSWORD IN VARCHAR2) RETURN NUMBER 
    AS
        match_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO match_count FROM SUBSCRIBER WHERE MSISDN = U_MSISDN AND password = U_PASSWORD;
        IF match_count = 0 THEN
            RETURN 0;
        ELSIF match_count >= 1 THEN
            RETURN 1;
        END IF;
    END;

    FUNCTION get_remaining_voice(p_msisdn subscriber.msisdn%type) RETURN NUMBER
    AS
        remaining_voice number;
    BEGIN
        SELECT (package.amount_voice - balance.bal_lvl_voice) INTO remaining_voice FROM SUBSCRIBER INNER JOIN BALANCE ON subscriber.subsc_id = balance.subsc_id
                                                    INNER JOIN PACKAGE ON balance.package_id = package.package_id WHERE subscriber.msisdn = p_msisdn;
        return remaining_voice;
    END;

    FUNCTION get_remaining_data(p_msisdn subscriber.msisdn%type) RETURN NUMBER
    AS
        remaining_data number;
    BEGIN
        SELECT (package.amount_data - balance.bal_lvl_data) INTO remaining_data FROM SUBSCRIBER INNER JOIN BALANCE ON subscriber.subsc_id = balance.subsc_id
                                                    INNER JOIN PACKAGE ON balance.package_id = package.package_id WHERE subscriber.msisdn = p_msisdn;
        return remaining_data;
    END;

    FUNCTION get_remaining_sms(p_msisdn subscriber.msisdn%type) RETURN NUMBER
    AS
        remaining_sms number;
    BEGIN
        SELECT (package.amount_sms - balance.bal_lvl_sms) INTO remaining_sms FROM SUBSCRIBER INNER JOIN BALANCE ON subscriber.subsc_id = balance.subsc_id
                                                    INNER JOIN PACKAGE ON balance.package_id = package.package_id WHERE subscriber.msisdn = p_msisdn;
        return remaining_sms;
    END;

    FUNCTION forgetPassword (P_MSISDN IN SUBSCRIBER.MSISDN%TYPE) RETURN NVARCHAR2
    AS
        P_PASSWORD subscriber.PASSWORD%TYPE;
    BEGIN
        SELECT subscriber.password into P_PASSWORD FROM subscriber WHERE  msisdn = P_MSISDN;
        IF P_PASSWORD IS NULL THEN
            RETURN 'Invalid phone number';
        ELSIF P_PASSWORD IS NOT NULL THEN
            RETURN P_PASSWORD;
        END IF;
    END;

    PROCEDURE create_subscriber (S_MSISDN IN SUBSCRIBER.MSISDN%TYPE, S_NAME IN SUBSCRIBER.NAME%TYPE, S_SURNAME IN SUBSCRIBER.SURNAME%TYPE, 
                                S_EMAIL IN SUBSCRIBER.EMAIL%TYPE, S_PASSWORD IN SUBSCRIBER.PASSWORD%TYPE, P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE) AS
        v_package_id number;
        v_package_name nvarchar2(200);
        v_subscriber_sequence_number number := subsc_id_sequence.nextval;
        BEGIN
            SELECT PACKAGE.package_id, PACKAGE.package_name INTO v_package_id, v_package_name FROM package INNER JOIN balance ON package.package_id = balance.package_id WHERE package_name = P_PACKAGE_NAME;

            INSERT INTO SUBSCRIBER (subsc_id,msisdn,name,surname,email,password,sdate,status) 
               VALUES(v_subscriber_sequence_number,s_msisdn,s_name,s_surname,s_email,s_password,SYSDATE,default);

            INSERT INTO BALANCE (subsc_id,package_id,bal_lvl_voice, bal_lvl_sms, bal_lvl_data,sdate,edate) 
               VALUES(v_subscriber_sequence_number, v_package_id, default, default, default, SYSDATE, SYSDATE);

            COMMIT;
        END;
END package_subscriber;


exec package_subscriber.create_subscriber (5513852246,'Efekan','Aydemir','efekanaydemir@gmail.com','12345', 'EYECELL 5GB');  
call package_subscriber.create_subscriber (5141269874,'Gizemli','Kiþi','gizemlikisi@gmail.com','123456', 'Eyecell 7GB'); 

SELECT CASE package_subscriber.login(5398849442,'12345')
    when 1
        then 'Giriþ baþarýlý'
    when 0
        then 'Giriþ baþarýsýz'
    end AS SONUC
from dual;

create or replace PACKAGE package_package IS
    PROCEDURE getAllPackages(recordset OUT SYS_REFCURSOR);
    PROCEDURE insertPackage(P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE, P_AMOUNT_VOICE IN PACKAGE.AMOUNT_VOICE%TYPE, P_AMOUNT_DATA IN PACKAGE.AMOUNT_DATA%TYPE, 
                            P_AMOUNT_SMS IN PACKAGE.AMOUNT_SMS%TYPE, P_DURATION IN PACKAGE.DURATION%TYPE);
END package_package;
    
    
CREATE OR REPLACE PACKAGE BODY package_package IS
    PROCEDURE insertPackage(P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE, P_AMOUNT_VOICE IN PACKAGE.AMOUNT_VOICE%TYPE, P_AMOUNT_DATA IN PACKAGE.AMOUNT_DATA%TYPE, 
                            P_AMOUNT_SMS IN PACKAGE.AMOUNT_SMS%TYPE, P_DURATION IN PACKAGE.DURATION%TYPE) IS
        BEGIN
            INSERT INTO PACKAGE (PACKAGE_ID,PACKAGE_NAME,AMOUNT_VOICE,AMOUNT_DATA,AMOUNT_SMS,DURATION) 
                    VALUES (PACKAGE_ID_SEQUENCE.NEXTVAL, P_PACKAGE_NAME, P_AMOUNT_VOICE, P_AMOUNT_DATA, P_AMOUNT_SMS, P_DURATION);
            COMMIT;
        END;
END package_package;

EXEC package_package.insertPackage('Eyecell 10GB', 1000, 10*1024,1000,30);
CALL package_package.insertPackage('Eyecell 7GB', 750, 7*1024,750,30);


create or replace PACKAGE package_balance IS
    PROCEDURE get_balance(P_MSISDN IN SUBSCRIBER.MSISDN%TYPE, recordset OUT SYS_REFCURSOR);
END package_balance;

create or replace PACKAGE BODY package_balance IS
    PROCEDURE get_balance(P_MSISDN IN SUBSCRIBER.MSISDN%TYPE, recordset OUT SYS_REFCURSOR) IS
        BEGIN
            Open recordset for
            SELECT BAL.* FROM BALANCE BAL INNER JOIN SUBSCRIBER SUBSC ON BAL.SUBSC_ID = SUBSC.SUBSC_ID WHERE SUBSC.MSISDN = P_MSISDN;
        END;
END package_balance;



variable mycursor refcursor;
exec package_balance.get_balance(5398849442, :mycursor);
print mycursor;


exec package_package.getAllPackages(:mycursor);
print mycursor;




SELECT 
     package_subscriber.get_remaining_voice(5398849442) || ' ' || package_subscriber.get_remaining_DATA(5398849442) || ' ' || package_subscriber.get_remaining_SMS(5398849442) AS "INFO"
FROM DUAL;

variable pcts refcursor;
exec pkg_connect_test.proc_superhero(:pcts);
print pcts;



