package com.eoi.slick.util

import com.eoi.slick.config.DataBaseConfig
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.slf4j.LoggerFactory

class DatabaseService {

  private val logger = LoggerFactory.getLogger(this.getClass)
  val driver = slick.jdbc.MySQLProfile

  import driver.api._

  val database = DataBaseConfig.apply()
  private val hikariConfig = new HikariConfig()
  private val jdbcUrl = database.drives().getOrElse("jdbc:mysql://192.168.0.116:10100/zt_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false")
  private val dbUser = database.user().getOrElse("root")
  private val dbPassword = database.password().getOrElse("root")
  private val timeOut = database.maxConnections().getOrElse(20)

  hikariConfig.setDriverClassName("com.mysql.jdbc.Driver")
  hikariConfig.setJdbcUrl(jdbcUrl)
  hikariConfig.setUsername(dbUser)
  hikariConfig.setPassword(dbPassword)
  hikariConfig.setConnectionTimeout(3000)
  hikariConfig.setMaximumPoolSize(timeOut)

  var dataSource: HikariDataSource = _
  try {
    dataSource = new HikariDataSource(hikariConfig)
  }
  catch {
    case ex: Exception =>
      logger.error("[DB connection error]", ex)
      throw ex
  }

  lazy val db = Database.forDataSource(dataSource, Some(10))
  db.createSession()
}

object DatabaseService {
  val databaseService = new DatabaseService()
}
