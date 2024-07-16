package test.org.fugerit.java.sql.safe.helper;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.dao.DAOUtilsNG;
import org.fugerit.java.core.db.dao.rse.LongRSE;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.sql.safe.helper.SqlSafeHelperFacade;
import org.fugerit.java.sql.safe.helper.SqlSafeHelperOutput;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

@Slf4j
class TestBase {

    protected Connection getConnection() throws DAOException {
        Properties props = PropsIO.loadFromClassLoaderSafe( "config/db.properties" );
        String url = props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL );
        props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL, url.replace( "sqlsafe", "ss_"+UUID.randomUUID().toString() ) );
        return ConnectionFactoryImpl.newInstance( props ).getConnection();
    }

    protected int testWorker( int expected, boolean rollback, String sql ) {
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

}
