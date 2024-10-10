# sql-safe-helper

Simple helper to handle update / insert / delete in a SAFE mode.

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](https://github.com/fugerit-org/sql-safe-helper/blob/master/CHANGELOG.md) 
[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/sql-safe-helper.svg)](https://central.sonatype.com/artifact/org.fugerit.java/sql-safe-helper)
[![license](https://img.shields.io/badge/License-Apache%20License%202.0-teal.svg)](https://opensource.org/licenses/Apache-2.0)
[![code of conduct](https://img.shields.io/badge/conduct-Contributor%20Covenant-purple.svg)](https://github.com/fugerit-org/fj-universe/blob/main/CODE_OF_CONDUCT.md)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_sql-safe-helper&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit-org_sql-safe-helper)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_sql-safe-helper&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit-org_sql-safe-helper)
[![GraalVM Ready](https://img.shields.io/badge/GraalVM-Ready-orange?style=plastic)](https://universe.fugerit.org/src/docs/ref/graalvm-ready.html)

[![Java runtime version](https://img.shields.io/badge/run%20on-java%2011+-%23113366.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Java build version](https://img.shields.io/badge/build%20on-java%2011+-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-3.9.0+-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)](https://universe.fugerit.org/src/docs/versions/maven3_9.html)

## Quickstart

Add maven dependency :

```xml
<dependency>
    <groupId>org.fugerit.java</groupId>
    <artifactId>sql-safe-helper</artifactId>
    <version>${sql-safe-helper-version}</version>
</dependency>	
```

Sample usage : 

```java
try ( Connection conn = ... ) {
    String sql = "DELETE FROM sql_safe_test WHERE id = 1";
    int expectedUpdateResult = 1;
    SqlSafeHelperOutput output = facade.update( conn, expectedUpdateResult, sql );
    log.info( "isRollback()   : {}", output.isRollback() );   // true if a rollback has been needed
    log.info( "updateResult() : {}", output.getValue() );     // update result (se only if no roolback) 
    log.info( "actualUpdateResult() : {}", output.getActualValue() );     // update result (se even in case of rollback)
}
```

When executing the update sql, if the result is different from the expected one, 
there will be a rollback for the transaction.
