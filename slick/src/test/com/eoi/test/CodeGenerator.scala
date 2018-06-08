package com.eoi.test

import com.eoi.slick.config.DataBaseConfig
import org.junit.Test

/**
  * Created by tao.zeng on 2018/6/8.
  */
class CodeGenerator {

  @Test
  def codeGenerator(): Unit = {

    val dbConf = DataBaseConfig()
    val jdbcUrl = dbConf.drives().getOrElse("")
    val dbUser = dbConf.user().getOrElse("")
    val dbPassword = dbConf.password().getOrElse("")
    val as = Array[String](//"jdbc:mysql://192.168.31.46:3306/itoaManagement?user=root&password=User@123",
      "slick.jdbc.MySQLProfile", //slick.driver.MySQLDriver,JdbcProfile
      "com.mysql.jdbc.Driver", //com.mysql.jdbc.Driver,MysqlDataSource,com.mysql.cj.jdbc.Driver
      jdbcUrl, "./", "dao", dbUser, dbPassword, "true", "slick.codegen.SourceCodeGenerator", "true")
    slick.codegen.SourceCodeGenerator.main(as)

  }
}
