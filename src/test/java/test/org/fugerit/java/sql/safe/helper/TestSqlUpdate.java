package test.org.fugerit.java.sql.safe.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSqlUpdate extends  TestBase {

    @Test
    void testUpdate001() {
        int expected = 2;
        String sql = "UPDATE sql_safe_test SET DESCRIPTION = 'test update' WHERE id_group = 1";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

    @Test
    void testUpdate002() {
        int expected = 11;
        String sql = "UPDATE sql_safe_test SET DESCRIPTION = 'test update'";
        boolean rollback = true;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( 0, result );
    }

}
