package test.org.fugerit.java.sql.safe.helper;

import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.util.PropsIO;

import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

class TestBase {

    protected Connection getConnection() throws DAOException {
        Properties props = PropsIO.loadFromClassLoaderSafe( "config/db.properties" );
        String url = props.getProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL );
        props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL, url.replace( "sqlsafe", "ss_"+UUID.randomUUID().toString() ) );
        return ConnectionFactoryImpl.newInstance( props ).getConnection();
    }

}
