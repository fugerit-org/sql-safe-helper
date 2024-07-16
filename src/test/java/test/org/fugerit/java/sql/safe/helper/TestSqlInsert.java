package test.org.fugerit.java.sql.safe.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSqlInsert extends  TestBase {

    @Test
    void testInsert001() {
        int expected = 1;
        String sql = "INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 5, 1, 'test 5' )";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

}
