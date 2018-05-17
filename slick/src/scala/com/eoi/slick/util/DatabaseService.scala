package com.eoi.slick.util

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.slf4j.LoggerFactory

class DatabaseService {

  private val logger = LoggerFactory.getLogger(this.getClass)
  val driver = slick.jdbc.MySQLProfile

  import driver.api._

  private val hikariConfig = new HikariConfig()
  val jdbcUrl = "jdbc:mysql://192.168.31.46:3306/zt_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false"
  val dbUser = "root"
  val dbPassword = "User@123"

  hikariConfig.setJdbcUrl(jdbcUrl)
  hikariConfig.setUsername(dbUser)
  hikariConfig.setPassword(dbPassword)
  hikariConfig.setConnectionTimeout(3000)

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
