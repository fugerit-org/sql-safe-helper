package test.org.fugerit.java.sql.safe.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TestSqlDelete extends  TestBase {

    @Test
    void testDelete() {
        Arrays.asList(
          new InputModel( "DELETE FROM sql_safe_test WHERE id = 1", false, 1 ),
          new InputModel( "DELETE FROM sql_safe_test WHERE id = -1", false, 0 ),
          new InputModel( "DELETE FROM sql_safe_test WHERE id_group = 2", false, 2 )
        ).forEach( c -> {
            int result = this.testUpdateWorker( c.getExpected(), c.isRollback(), c.getSql() );
            Assertions.assertEquals( c.getExpected(), result );
        });
    }

}
