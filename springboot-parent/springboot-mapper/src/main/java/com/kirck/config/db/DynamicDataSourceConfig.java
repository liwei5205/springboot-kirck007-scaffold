package com.kirck.config.db;

import com.kirck.config.datasource.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DynamicDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfig.class);

    private static final String DB_PREFIX_MASTER = "spring.datasource.master";

    private static final String DB_PREFIX_SLAVE = "spring.datasource.slave";

    @Value("${spring.datasource.master.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     *
     * 有多少个数据源就要配置多少个bean
     * @return
     */

    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = DB_PREFIX_SLAVE)
    public DataSource writeDataSource() {
        logger.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource")
    @Primary
    @ConfigurationProperties(prefix =  DB_PREFIX_MASTER)
    public DataSource readDataSource() {
        logger.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
