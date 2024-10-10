package test.org.fugerit.java.sql.safe.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class InputModel {

    @Getter
    private String sql;

    @Getter
    private boolean rollback;

    @Getter
    private int expected;

}
