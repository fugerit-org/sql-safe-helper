package org.fugerit.java.sql.safe.helper;

import lombok.Getter;
import lombok.Setter;

public class SqlSafeHelperOutput {

    @Getter @Setter
    private int value;

    @Getter @Setter
    private boolean rollback;

}
