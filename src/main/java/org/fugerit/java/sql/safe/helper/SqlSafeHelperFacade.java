package org.fugerit.java.sql.safe.helper;

import org.fugerit.java.core.db.dao.DAOUtils;
import org.fugerit.java.core.db.dao.DAOUtilsNG;
import org.fugerit.java.core.db.dao.OpDAO;
import org.fugerit.java.core.db.dao.RSExtractor;
import org.fugerit.java.core.db.metadata.ColumnModel;
import org.fugerit.java.core.db.metadata.MetaDataUtils;
import org.fugerit.java.core.db.metadata.TableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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

    public QueryOutput query(Connection conn, String sql, Object... fields) throws SQLException {
        QueryOutput output = new QueryOutput();
        ItemRSE rse = new ItemRSE();
        DAOUtilsNG.extraAllFields( conn, output.getRows(), sql, rse, fields );
        output.getColumns().addAll( rse.extractTableModel() );
        return output;
    }

}

class ItemRSE implements RSExtractor<Collection<Object>> {

    private ResultSetMetaData rsmd = null;

    private Collection<ColumnModel> columns;

    @Override
    public Collection<Object> extractNext(ResultSet resultSet) throws SQLException {
        if ( this.rsmd == null ) {
            this.rsmd = resultSet.getMetaData();
            this.columns = new ArrayList<>();
            for ( int k=1; k<=this.rsmd.getColumnCount(); k++ ) {
                ColumnModel columnModel = new ColumnModel();
                columnModel.setName( this.rsmd.getColumnName( k ) );
                columnModel.setJavaType( this.rsmd.getColumnClassName( k ) );
                columnModel.setSize( this.rsmd.getColumnDisplaySize( k ) );
                columnModel.setTypeSql( this.rsmd.getColumnType( k ) );
                columns.add( columnModel );
            }
        }
        Collection<Object> values = new ArrayList<>();
        for ( int k=1; k<=this.rsmd.getColumnCount(); k++ ) {
            values.add( resultSet.getObject(k) );
        }
        return values;
    }

    public Collection<ColumnModel> extractTableModel() {
        return this.columns;
    }

}

