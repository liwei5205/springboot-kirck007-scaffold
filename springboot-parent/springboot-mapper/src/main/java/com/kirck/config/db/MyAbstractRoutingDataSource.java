package com.kirck.config.db;


import com.kirck.config.datasource.DataSourceContextHolder;
import com.kirck.config.datasource.DataSourceType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


import java.util.concurrent.atomic.AtomicInteger;

public class MyAbstractRoutingDataSource  extends AbstractRoutingDataSource {
    private final int dataSourceNumber;

    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (StringUtils.isNotBlank(typeKey)&&typeKey.equals(DataSourceType.write.getType()))
            return DataSourceType.write.getType();
        // 读 简单负载均衡
        return DataSourceType.read.getType();
    }
}
