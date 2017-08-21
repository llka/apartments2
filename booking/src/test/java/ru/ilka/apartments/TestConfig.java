package ru.ilka.apartments;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ilka.apartments.dao.ApartmentDaoImpl;
import ru.ilka.apartments.logic.ApartmentLogic;


import javax.sql.DataSource;

@Configuration
@DbUnitConfiguration
public class TestConfig {
    @Bean
    ApartmentDaoImpl apartmentDaoImpl(){
        return new ApartmentDaoImpl();
    }

    @Bean
    ApartmentLogic apartmentLogic(){
        return new ApartmentLogic(apartmentDaoImpl());
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(){
        DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
        dbUnitDatabaseConnection.setDatabaseConfig(databaseConfigBean());
        dbUnitDatabaseConnection.setDataSource(dataSource());
        return dbUnitDatabaseConnection;
    }

    @Bean
    public DatabaseConfigBean databaseConfigBean(){
        DatabaseConfigBean databaseConfigBean = new DatabaseConfigBean();
        databaseConfigBean.setCaseSensitiveTableNames(false);
        databaseConfigBean.setQualifiedTableNames(true);
        databaseConfigBean.setSkipOracleRecyclebinTables(true);
        //databaseConfigBean.setDatatypeFactory(new Oracle10DataTypeFactory());
        return databaseConfigBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("FORZA_JUVE");
        dataSource.setPassword("3880490");
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1521/XE");
        return dataSource;
    }
}
