package test.org.fugerit.java.sql.safe.helper;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.dao.DAORuntimeException;
import org.fugerit.java.sql.safe.helper.SqlExceptionHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class TestSqlExceptionHelper {

    @Test
    void testFindSqlException() {
        Assertions.assertNull(SqlExceptionHelper.findSQLException( null ));
        Assertions.assertNull(SqlExceptionHelper.findSQLException( new DAOException()));
        Assertions.assertNotNull(SqlExceptionHelper.findSQLException( new DAORuntimeException( new SQLException())));
    }

}
