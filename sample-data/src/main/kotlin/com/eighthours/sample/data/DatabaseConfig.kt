package com.eighthours.sample.data

import com.typesafe.config.Config
import com.zaxxer.hikari.HikariDataSource
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import org.seasar.doma.jdbc.tx.TransactionManager
import javax.sql.DataSource

internal class DatabaseConfig(
    private val name: String, private val config: Config
) : org.seasar.doma.jdbc.Config {

    private val dataSource = LocalTransactionDataSource(HikariDataSource().apply {
        jdbcUrl = config.getString("dataSource.url")
        username = config.getString("dataSource.username")
        if (config.hasPath("dataSource.password")) {
            password = config.getString("dataSource.password")
        }
    })

    override fun getDataSourceName(): String {
        return name
    }

    override fun getDataSource(): DataSource {
        return dataSource
    }

    override fun getDialect(): Dialect {
        return Class.forName(config.getString("dialect")).newInstance() as Dialect
    }

    override fun getTransactionManager(): TransactionManager {
        return LocalTransactionManager(dataSource.getLocalTransaction(jdbcLogger))
    }

    override fun getNaming(): Naming {
        return Naming.SNAKE_LOWER_CASE
    }
}
