CREATE TABLE sql_safe_test (
    ID BIGINT NOT NULL,
    ID_GROUP BIGINT NOT NULL,
    description VARCHAR(512) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 1, 1, 'test 1' );
INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 2, 1, 'test 2' );
INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 3, 2, 'test 3' );
INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 4, 2, 'test 4' );

