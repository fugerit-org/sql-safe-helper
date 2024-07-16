package test.org.fugerit.java.sql.safe.helper;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.sql.safe.helper.QueryOutput;
import org.fugerit.java.sql.safe.helper.SqlSafeHelperFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

@Slf4j
class TestSqlQuery extends  TestBase {

    @Test
    void testQuery001() throws DAOException, SQLException {
        try (Connection conn = this.getConnection()) {
            String sql = "SELECT * FROM sql_safe_test";
            SqlSafeHelperFacade facade = new SqlSafeHelperFacade();
            QueryOutput output = facade.query( conn, sql );
            Assertions.assertEquals( 4, output.getRows().size() );
            output.getRowsMeta().stream().forEach( c -> {
                StringBuilder sb = new StringBuilder();
                c.forEach( it -> {
                    sb.append( it.getKey().getName() );
                    sb.append( " = " );
                    sb.append( it.getValue() );
                    sb.append( ", " );
                } );
                log.info( "row : {} - {}", c.size(), sb.toString() );
            } );
        }

    }

}
