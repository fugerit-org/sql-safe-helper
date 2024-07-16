package org.fugerit.java.sql.safe.helper;

import org.fugerit.java.core.db.dao.DAOUtils;
import org.fugerit.java.core.db.dao.DAOUtilsNG;
import org.fugerit.java.core.db.dao.OpDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlSafeHelperFacade {

    public SqlSafeHelperOutput update(Connection conn, int expected, String sql ) throws SQLException {
        return this.update( conn, expected, OpDAO.newUpdateOp( sql ) );
    }

    public SqlSafeHelperOutput update(Connection conn, int expected, OpDAO<Long> opDAO) throws SQLException {
        SqlSafeHelperOutput output = new SqlSafeHelperOutput();
        conn.setAutoCommit( false );
        try {
            int updateResult = DAOUtilsNG.update( conn, opDAO );
            if ( updateResult != expected ) {
                conn.rollback();
                output.setRollback( true );
            } else {
                output.setValue( updateResult );
            }
        } finally {
            conn.setAutoCommit( true );
        }
        return output;
    }

}
