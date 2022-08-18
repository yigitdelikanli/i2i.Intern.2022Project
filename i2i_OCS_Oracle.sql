SHOW USER
CREATE USER eyecell identified by 12345;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO eyecell;

CREATE TABLE SUBSCRIBER(
    SUBSC_ID NUMBER,
    MSISDN VARCHAR2(11) NOT NULL UNIQUE,
    NAME VARCHAR2(100) NOT NULL,
    SURNAME VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL UNIQUE,
    PASSWORD VARCHAR2(20) NOT NULL,
    SDATE DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(2) DEFAULT 'A',
    SECURITY_QUESTION VARCHAR2(200) NOT NULL
);

ALTER TABLE SUBSCRIBER ADD(
    CONSTRAINT subsc_id_pk PRIMARY KEY (SUBSC_ID)
);

CREATE SEQUENCE subsc_id_sequence start with 1 increment by 1;


CREATE TABLE PACKAGE(
    PACKAGE_ID NUMBER,
    PACKAGE_NAME VARCHAR2(200),
    AMOUNT_VOICE NUMBER,
    AMOUNT_DATA NUMBER,
    AMOUNT_SMS NUMBER,
    DURATION NUMBER
);

ALTER TABLE PACKAGE ADD(
    CONSTRAINT package_id_pk PRIMARY KEY (PACKAGE_ID)
);

CREATE SEQUENCE package_id_sequence start with 1 increment by 1;

CREATE TABLE BALANCE(
    SUBSC_ID NUMBER,
    PACKAGE_ID NUMBER,
    BAL_LVL_VOICE NUMBER DEFAULT 0,
    BAL_LVL_SMS NUMBER DEFAULT 0,
    BAL_LVL_DATA NUMBER DEFAULT 0,
    PRICE NUMBER DEFAULT 0,
    SDATE DATE DEFAULT SYSDATE,
    EDATE DATE DEFAULT SYSDATE
);


ALTER TABLE BALANCE ADD(
    CONSTRAINT subsc_id_fk FOREIGN KEY (SUBSC_ID) REFERENCES SUBSCRIBER(SUBSC_ID) ON DELETE CASCADE,
    CONSTRAINT package_id_fk FOREIGN KEY (PACKAGE_ID) REFERENCES PACKAGE(PACKAGE_ID) ON DELETE CASCADE
);


INSERT INTO BALANCE VALUES(1,1,DEFAULT,DEFAULT,DEFAULT,SYSDATE,SYSDATE);

create or replace PACKAGE package_subscriber IS
    FUNCTION login (U_MSISDN IN NUMBER, U_PASSWORD IN VARCHAR2) RETURN NUMBER;
    FUNCTION get_subscriber_id RETURN NUMBER;
    FUNCTION get_user_package (p_msisdn subscriber.msisdn%type) RETURN package.package_name%type;
    FUNCTION get_remaining_voice (p_msisdn subscriber.msisdn%type) RETURN NUMBER;
    FUNCTION get_remaining_data (p_msisdn subscriber.msisdn%type) RETURN NUMBER;
    FUNCTION get_remaining_sms (p_msisdn subscriber.msisdn%type) RETURN NUMBER;

    FUNCTION forget_password(P_MSISDN IN SUBSCRIBER.MSISDN%TYPE) RETURN NVARCHAR2;
    
    PROCEDURE create_subscriber(S_SUBSC_ID IN SUBSCRIBER.SUBSC_ID%TYPE,S_MSISDN IN SUBSCRIBER.MSISDN%TYPE, S_NAME IN SUBSCRIBER.NAME%TYPE, S_SURNAME IN SUBSCRIBER.SURNAME%TYPE, 
                                S_EMAIL IN SUBSCRIBER.EMAIL%TYPE, S_PASSWORD IN SUBSCRIBER.PASSWORD%TYPE, P_PACKAGE_ID IN PACKAGE.PACKAGE_ID%TYPE);

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
    
    FUNCTION get_subscriber_id RETURN NUMBER 
    AS
        u_id NUMBER;
    BEGIN 
        u_id := SUBSC_ID_SEQUENCE.nextval;
    RETURN u_id ;
    END get_subscriber_id;
        
    FUNCTION get_user_package(p_msisdn subscriber.msisdn%type) RETURN package.package_name%type
    AS
        v_package_name package.package_name%type;
    BEGIN
        SELECT package.package_name INTO v_package_name FROM SUBSCRIBER INNER JOIN BALANCE ON subscriber.subsc_id = balance.subsc_id
                                                    INNER JOIN PACKAGE ON balance.package_id = package.package_id WHERE subscriber.msisdn = p_msisdn;
        return v_package_name;
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

    FUNCTION forget_password (P_MSISDN IN SUBSCRIBER.MSISDN%TYPE) RETURN NVARCHAR2
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
      
    PROCEDURE create_subscriber(S_SUBSC_ID IN SUBSCRIBER.SUBSC_ID%TYPE,S_MSISDN IN SUBSCRIBER.MSISDN%TYPE, S_NAME IN SUBSCRIBER.NAME%TYPE, S_SURNAME IN SUBSCRIBER.SURNAME%TYPE, 
                                S_EMAIL IN SUBSCRIBER.EMAIL%TYPE, S_PASSWORD IN SUBSCRIBER.PASSWORD%TYPE, P_PACKAGE_ID IN PACKAGE.PACKAGE_ID%TYPE) AS
        v_package_id number;
        v_package_name nvarchar2(200);
        BEGIN
        
        SELECT package.package_id, package.package_name INTO v_package_id, v_package_name FROM package where package.package_id =  P_PACKAGE_ID; 
            
            INSERT INTO SUBSCRIBER (subsc_id,msisdn,name,surname,email,password,sdate,status) 
               VALUES(S_SUBSC_ID,s_msisdn,s_name,s_surname,s_email,s_password,SYSDATE,default);
            
            INSERT INTO BALANCE (subsc_id,package_id,bal_lvl_voice, bal_lvl_sms, bal_lvl_data,sdate,edate) 
               VALUES(S_SUBSC_ID, v_package_id, default, default, default, SYSDATE, SYSDATE);
               
            COMMIT;
        END;
END package_subscriber;


SELECT CASE package_subscriber.login(5398849442,'12345')
    when 1
        then 'Succesfull login'
    when 0
        then 'Unsuccesfull login'
    end AS SONUC
from dual;

create or replace PACKAGE package_package IS
    PROCEDURE get_all_packages(recordset OUT SYS_REFCURSOR);
    PROCEDURE insert_package(P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE, P_AMOUNT_VOICE IN PACKAGE.AMOUNT_VOICE%TYPE, P_AMOUNT_DATA IN PACKAGE.AMOUNT_DATA%TYPE, 
                            P_AMOUNT_SMS IN PACKAGE.AMOUNT_SMS%TYPE, P_DURATION IN PACKAGE.DURATION%TYPE);
END package_package;
    
    
create or replace PACKAGE BODY package_package IS
    PROCEDURE get_all_packages(recordset OUT SYS_REFCURSOR) IS 
        BEGIN
        open recordset for
            SELECT package.package_id , package.package_name FROM PACKAGE;
        END;
    PROCEDURE insert_package(P_PACKAGE_NAME IN PACKAGE.PACKAGE_NAME%TYPE, P_AMOUNT_VOICE IN PACKAGE.AMOUNT_VOICE%TYPE, P_AMOUNT_DATA IN PACKAGE.AMOUNT_DATA%TYPE, 
                            P_AMOUNT_SMS IN PACKAGE.AMOUNT_SMS%TYPE, P_DURATION IN PACKAGE.DURATION%TYPE) IS
        BEGIN
            INSERT INTO PACKAGE (PACKAGE_ID,PACKAGE_NAME,AMOUNT_VOICE,AMOUNT_DATA,AMOUNT_SMS,DURATION) 
                    VALUES (PACKAGE_ID_SEQUENCE.NEXTVAL, P_PACKAGE_NAME, P_AMOUNT_VOICE, P_AMOUNT_DATA * 1024, P_AMOUNT_SMS, P_DURATION);
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


create or replace PACKAGE package_dmloperations IS
    PROCEDURE update_voice(p_msisdn in subscriber.msisdn%type, amount in number);
    PROCEDURE update_data(p_msisdn in subscriber.msisdn%type, amount in number);
    PROCEDURE update_sms(p_msisdn in subscriber.msisdn%type, amount in number);
    PROCEDURE update_voice(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type);
    PROCEDURE update_data(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type);
    PROCEDURE update_sms(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type);
END package_dmloperations;

create or replace PACKAGE BODY package_dmloperations IS 

    PROCEDURE update_voice(p_msisdn in subscriber.msisdn%type, amount in number) IS
    BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.bal_lvl_voice = b.bal_lvl_voice + amount;
        COMMIT;
    END;

     PROCEDURE update_data(p_msisdn in subscriber.msisdn%type, amount in number) IS
     BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.bal_lvl_data = b.bal_lvl_data + amount;
        COMMIT;
    END;

    PROCEDURE update_sms(p_msisdn in subscriber.msisdn%type, amount in number) IS
     BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.bal_lvl_sms = b.bal_lvl_sms + amount;
        COMMIT;
    END;
    
    
    PROCEDURE update_voice(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type) IS
    BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn AND subsc_id = p_subsc_id 
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.price = b.price + p_price, b.bal_lvl_voice = b.bal_lvl_voice + amount ;
        COMMIT;
    END;
    
    PROCEDURE update_data(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type) IS
    BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn AND subsc_id = p_subsc_id 
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.price = b.price + p_price, b.bal_lvl_data = b.bal_lvl_data + amount ;
        COMMIT;
    END;
    
    PROCEDURE update_sms(p_subsc_id in subscriber.subsc_id%type, p_msisdn in subscriber.msisdn%type, amount in number, p_price in balance.price%type) IS
    BEGIN
        MERGE INTO BALANCE b
            USING (
                SELECT subsc_id FROM subscriber WHERE MSISDN = p_msisdn AND subsc_id = p_subsc_id 
            )s
            ON (b.SUBSC_ID = s.SUBSC_ID)
        WHEN MATCHED THEN
        UPDATE SET b.price = b.price + p_price, b.bal_lvl_sms = b.bal_lvl_sms + amount ;
        COMMIT;
    END;
END package_dmloperations;



