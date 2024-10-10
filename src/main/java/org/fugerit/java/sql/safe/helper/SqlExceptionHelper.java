package org.fugerit.java.sql.safe.helper;

import java.sql.SQLException;

public class SqlExceptionHelper {

    private SqlExceptionHelper() {}

    public static SQLException findSQLException(Throwable e) {
        if ( e == null ) {
            return null;
        } else if ( e instanceof SQLException) {
            return (SQLException) e;
        } else {
            return findSQLException( e.getCause() );
        }
    }

}
