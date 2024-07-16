package test.org.fugerit.java.sql.safe.helper;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.db.dao.DAOUtilsNG;
import org.fugerit.java.core.db.dao.rse.LongRSE;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.sql.safe.helper.SqlSafeHelperFacade;
import org.fugerit.java.sql.safe.helper.SqlSafeHelperOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

@Slf4j
class TestSqlUpdate extends  TestBase {

    private int testWorker( int expected, boolean rollback, String sql ) {
        return SafeFunction.get( () -> {
            SqlSafeHelperFacade facade = new SqlSafeHelperFacade();
            log.info( "testUpdate001" );
            try ( Connection conn = this.getConnection() ) {
                log.info( "int count : {}", DAOUtilsNG.extraOne( conn, "SELECT count(*) FROM sql_safe_test", LongRSE.DEFAULT ));
                SqlSafeHelperOutput output = facade.update( conn, expected, sql );
                Assertions.assertEquals( rollback, output.isRollback() );
                return output.getValue();
            }
        } );
    }

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

    @Test
    void testInsert001() {
        int expected = 1;
        String sql = "INSERT INTO sql_safe_test ( ID, ID_GROUP, DESCRIPTION ) VALUES ( 5, 1, 'test 5' )";
        boolean rollback = false;
        int result = this.testWorker( expected, rollback, sql );
        Assertions.assertEquals( expected, result );
    }

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
