package com.me.ssm.system.data;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author liujiacun
 * @date 2019/2/23
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();

    private static final ThreadLocal<String> urlThreadLocal = new InheritableThreadLocal<>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    public static void setDataSourceUrl(String url) {
        urlThreadLocal.set(url);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    @Override
    public Connection getConnection() throws SQLException {
        DataSource dataSource = determineTargetDataSource();
        BasicDataSource basicDataSource=(BasicDataSource)dataSource;
        basicDataSource.setUrl(urlThreadLocal.get());
        return dataSource.getConnection();
    }
}
