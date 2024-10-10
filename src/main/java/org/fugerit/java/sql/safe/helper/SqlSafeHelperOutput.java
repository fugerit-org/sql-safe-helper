package org.fugerit.java.sql.safe.helper;

import lombok.Getter;
import lombok.Setter;

public class SqlSafeHelperOutput {

    /**
     * Contains the update result, only if it is equals to the expected result.
     */
    @Getter @Setter
    private int value;

    /**
     * It will be <code>true</code> if the update resulted in a roolback.
     */
    @Getter @Setter
    private boolean rollback;

    /**
     * Contains the update result, even if different from expected result. (since 1.2.0)
     */
    @Getter @Setter
    private int actualValue;

}
