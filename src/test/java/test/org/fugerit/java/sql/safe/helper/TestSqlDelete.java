package test.org.fugerit.java.sql.safe.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSqlDelete extends  TestBase {

    @Test
    void testDelete001() {
        int expected = 1;
        String sql = "DELETE FROM sql_safe_test WHERE id = 1";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

    @Test
    void testDelete002() {
        int expected = 0;
        String sql = "DELETE FROM sql_safe_test WHERE id = -1";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

    @Test
    void testDelete003() {
        int expected = 2;
        String sql = "DELETE FROM sql_safe_test WHERE id_group = 2";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

}
